/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.tmrmswebscrapper.Models;

import com.example.tmrmswebscrapper.Svc.PropertySvcI;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
/**
 *
 * @author Lubbow
 */
public class HomeGate {
    
    public ArrayList<PropertyListJSON> GetTotalResultsCount(PropertySvcI pSvc, String rec_manger_id, int regionId, ArrayList<String> searchIn, ArrayList<String> cityName, String regionName, ArrayList<String> searchCat, ArrayList<String> vendortype, String adPublishFrom, String adPublishTo){
        
        String Url = "https://www.homegate.ch/rs/real-estates?";
        
        Map<String,String> Pmap = new HashMap<>();
        Pmap.put("Accept","application/json");
        Pmap.put("Authorization","Basic aGdfYW5kcm9pZDo2VmNVTZjZUNGVGs4ZEZt");
        Pmap.put("Host","www.homegate.ch");
        Pmap.put("Connection","Keep-Alive");
        Pmap.put("Accept-Encoding","gzip");
        Pmap.put("user-agent","okhttp/3.11.0");
        
        ArrayList<PropertyUrls> Urls = new ArrayList<>();
        ArrayList<PropertyListJSON> props = new ArrayList<>();
        
        searchIn.forEach((dfsl) -> {
            
            if(dfsl.toLowerCase().contains("rent") || dfsl.toLowerCase().contains("buy")){
                searchCat.forEach((dfcl) -> {
                    ArrayList<String> locations_n = CL(cityName);

                    locations_n.forEach((dfl) -> {
                                                  
                            String MainUrlGetter = "";
                            
                            if(dfsl.toLowerCase().contains("rent")){
                                MainUrlGetter = Url + "cht=" + dfcl;                                
                            }else if(dfsl.toLowerCase().contains("buy")){
                                MainUrlGetter = Url + "cht=" + dfcl;
                            }
                            
                            if(MainUrlGetter != null && MainUrlGetter.isEmpty() == false){
                                Urls.addAll(getAllLinks(MainUrlGetter, dfcl, dfl, Pmap));
                            }
                            
                    });
                });
            }
        });
        
        if(Urls.size() > 0){
            props = GetPropertiesRecord(pSvc, rec_manger_id, regionName, regionId, Urls, vendortype, adPublishFrom, adPublishTo);
        }
        
        return props;
    }
    
    private ArrayList<PropertyUrls> getAllLinks(String Url, String PropertyCategory, String Location, Map<String,String> Pmap){
        ArrayList<PropertyUrls> Urls = new ArrayList<>();
        String _Url = Url + "&loc=" + Location.trim() + "&nrs=10000&ver=3&lan=en";
               _Url = _Url + "&rfi=advertisementId,title,street,zip,city,geoLocation,numberRooms,sellingPrice,surfaceLiving,objectTypeLabel,currency,listingType,contactPerson,contactPhone,interestedFormType,offerType,externalUrls,pictures";
                    
                    try {
                        Document doc;
                        doc = Jsoup.connect(_Url).maxBodySize(0).timeout(1000000).headers(Pmap).ignoreContentType(true).get();
                        String _response = doc.text();
                            
                            if(_response != null || _response.isEmpty() == false){
                                
                                JSONObject ob = new JSONObject(_response);
                                    if(ob.has("resultCount")){
                                        if(ob.getInt("resultCount") > 0){
                                            JSONArray jsonarray = ob.getJSONArray("items");
                                                if(jsonarray != null && jsonarray.length() > 0){
                                                    for(int i=0; i < jsonarray.length(); i++){
                                                          JSONObject jsonobject = jsonarray.getJSONObject(i);
                                                          Urls.add(new PropertyUrls(jsonobject.getBigInteger("advertisementId").toString(),Location, getPropertyCat(PropertyCategory), ""));
                                                    }
                                                }
                                        }
                                    }
                            }
                    } catch (IOException ex) {
                        System.out.println("Home Gate Main Posts Page Site Request Error : " + ex.getMessage());
                    }catch (JSONException e) {
                        System.out.println("Home Gate Json Parsing Error : " + e.getMessage());
                    }
                    
            System.out.println(_Url);
            System.out.println(Location + " => : " + Urls.size());
        return Urls;
    }
    
    private ArrayList<PropertyListJSON> GetPropertiesRecord(PropertySvcI pSvc, String rec_manger_id, String regionName, int regionId, ArrayList<PropertyUrls> urls, ArrayList<String> vendortype, String adPublishFrom, String adPublishTo){
        ArrayList<PropertyListJSON> properties = new ArrayList<>();
        Document doc;
        Map<String,String> Pmap = new HashMap<>();
        Pmap.put("Accept","application/json");
        Pmap.put("Authorization","Basic aGdfYW5kcm9pZDo2VmNVTZjZUNGVGs4ZEZt");
        Pmap.put("Host","www.homegate.ch");
        Pmap.put("Connection","Keep-Alive");
        Pmap.put("Accept-Encoding","gzip");
        Pmap.put("user-agent","okhttp/3.11.0");

        for(PropertyUrls UrlId:urls){
            //if(pSvc.getPostbyIdAndWebSite(UrlId.getPurls(), "homegate.ch") <= 0){
                try {
                    doc = Jsoup.connect("https://www.homegate.ch/rs/real-estates/" + UrlId.getPurls() + "?lan=en&cli=android&ver=3").headers(Pmap).ignoreContentType(true).get();
                    String rv = doc.text();
                    try {
                        JSONObject obj = new JSONObject(rv);
                        
                        String V_id = (obj.has("agencyId") ? obj.getString("agencyId") : "");
                        String agencyName = (obj.has("agencyName") ? obj.getString("agencyName") : "");
                        String publishDate = (obj.has("timestampStr") ? obj.getString("timestampStr") : "");
                        
                        boolean pbc = adPublishFrom.isEmpty() || adPublishTo.isEmpty() ? true : publishDateCheck(publishDate, adPublishFrom, adPublishTo);
                        
                        if(pSvc.getVendorbyIdAndWebSite(V_id, "homegate.ch") <= 0 && (vendortype.size() == 0 || ( vendortype.size() > 0 && GetVendorTypeVerified(vendortype, agencyName) > 0)) && pbc){
                            String featuresOfProperty = "";
                            if(obj.has("equipment")){
                                JSONArray jsonarray = obj.getJSONArray("equipment");
                                    if(jsonarray != null && jsonarray.length() > 0){
                                        ArrayList<String> featuresOfP = new ArrayList<>();
                                          for(int i=0; i < jsonarray.length(); i++){
                                              JSONObject jsonobject = jsonarray.getJSONObject(i);
                                              featuresOfP.add((jsonobject.has("label") ? jsonobject.getString("label") : ""));
                                          }
                                          featuresOfProperty = featuresOfP.stream().map(n -> String.valueOf(n)).collect(Collectors.joining(",", "", ""));
                                    }
                            }
                            
                            publishDate = publishDate.isEmpty() == false ? convertDateFormat(publishDate) : publishDate;
                            
                            String updateDate = (obj.has("timestampStr") ? obj.getString("timestampStr") : "");
                            
                            updateDate = updateDate.isEmpty() == false ? convertDateFormat(updateDate) : updateDate;
                            
                            String searchType = (obj.has("offerType") ? obj.getString("offerType") : "") ;
                            String msRegion = regionName;
                            //String catOfProperty = (obj.has("objectCategory") ? obj.getString("objectCategory") : "") ;
                            String catOfProperty = UrlId.getPropertyCategory();
                            //String propertyType = (obj.has("objectTypeLabel") ? obj.getString("objectTypeLabel") : "") ;
                            String propertyType = UrlId.getPropertyType() != null ? UrlId.getPropertyType() : (obj.has("objectTypeLabel") ? obj.getString("objectTypeLabel") : "");                            
                            String titleOfAdd = (obj.has("title") ? obj.getString("title") : "") ;
                            String streetNumber = (obj.has("propertyStreet") ? obj.getString("propertyStreet") : "") ;
                            String postalCode = (obj.has("propertyZip") ? obj.getString("propertyZip") : "") ;
                            String cIty = (obj.has("propertyCityname") ? obj.getString("propertyCityname") : "");
                            String sellPrice = "";

                            if(searchType.toLowerCase().contains("sale")){
                                sellPrice = (obj.has("sellingPrice") ? Integer.toString(obj.getInt("sellingPrice")) : "") ;
                            }

                            String sellGrossPrice = "";
                            String sellNetPrice = "";
                            String sellPaymentType = (obj.has("priceUnit") ? obj.getString("priceUnit") : "");

                            int rentPrice = 0;
                            int rentGrossPrice = 0;
                            int rentNetPrice = 0;

                            if(searchType.toLowerCase().contains("rent")){
                                rentPrice = (obj.has("sellingPrice") ? obj.getInt("sellingPrice") : 0) ;
                                rentGrossPrice = (obj.has("rentNet") ? obj.getInt("rentNet") : 0) + (obj.has("rentExtra") ? obj.getInt("rentExtra") : 0);
                                rentNetPrice = (obj.has("rentNet") ? obj.getInt("rentNet") : 0) ;
                            }

                            String rentPaymentType = (obj.has("priceUnit") ? obj.getString("priceUnit") : "");
                            String numberOfRooms = (obj.has("numberRooms") ? Integer.toString(obj.getInt("numberRooms")) : "") ;

                            String livingSpace = (obj.has("surfaceLiving") ? Integer.toString(obj.getInt("surfaceLiving")) : "");
                            String propertyFloor = (obj.has("floor") ? Integer.toString(obj.getInt("floor")) : "");
                            String propertyArea = (obj.has("surfaceProperty") ? Integer.toString(obj.getInt("surfaceProperty")) : "");
                            String propertyDesc = (obj.has("adDescription") ? obj.getString("adDescription") : "");
                            String propertyAvailable = (obj.has("availableFrom") ? obj.getBigInteger("availableFrom").toString() : "");

                            if(propertyAvailable != null && propertyAvailable.isEmpty() == false){
                                Timestamp ts=new Timestamp(Long.parseLong(propertyAvailable));  
                                Date date=new Date(ts.getTime());
                                SimpleDateFormat cd_ = new SimpleDateFormat ("dd-MM-yyyy");
                                propertyAvailable = cd_.format(date);
                            }

                            String yearBuilt = (obj.has("yearBuilt") ? Integer.toString(obj.getInt("yearBuilt")) : "") ;
                            String nameOfSeller = (obj.has("contactPerson") ? obj.getString("contactPerson") : "") ;
                            //String agencyName = (obj.has("agencyName") ? obj.getString("agencyName") : "") ;
                            String streetNumberOfSeller = (obj.has("agencyStreet") ? obj.getString("agencyStreet") : "") ;
                            String postalCodeOfSeller = (obj.has("agencyZip") ? obj.getString("agencyZip") : "") ;
                            String cityOfSeller = (obj.has("agencyCityname") ? obj.getString("agencyCityname") : "") ;
                            String phoneOfSeller = (obj.has("agencyPhoneDay") ? obj.getString("agencyPhoneDay") : "") ;
                            String mNumberOfSeller = (obj.has("agencyMobile") ? obj.getString("agencyMobile") : "");
                            String emailOfSeller = (obj.has("agencyEmail") ? obj.getString("agencyEmail") : ""); 
                            String contactName = (obj.has("visitName") ? obj.getString("visitName") : "");
                            String contactNumber = (obj.has("visitPhone") ? obj.getString("visitPhone") : "");
                            String urlOfAd = (obj.has("objectUrl") ? obj.getString("objectUrl") : "");
                            
                            if(urlOfAd.contains("buy")){
                                urlOfAd = urlOfAd.replaceAll(Pattern.quote("buy"), "kaufen");
                            }
                            
                            if(urlOfAd.contains("rent")){
                                urlOfAd = urlOfAd.replaceAll(Pattern.quote("rent"), "mieten");
                            }
                            
                            
                            ArrayList<ImageUrls> iu = new ArrayList<>();

                            if(obj.has("realEstatePictures")){
                                JSONArray jsonarray = obj.getJSONArray("realEstatePictures");
                                    if(jsonarray != null && jsonarray.length() > 0){
                                          for(int i=0; i < jsonarray.length(); i++){
                                              JSONObject jsonobject = jsonarray.getJSONObject(i);
                                              String iUrls = (jsonobject.has("url") ? jsonobject.getString("url") : "");
                                              iu.add(new ImageUrls("",iUrls));
                                          }
                                    }
                            }

                            Date dNow = new Date();
                            SimpleDateFormat ct = new SimpleDateFormat ("hh:mm a");
                            SimpleDateFormat cd = new SimpleDateFormat ("yyyy-MM-dd");
                            SimpleDateFormat cm = new SimpleDateFormat ("MMM");
                            SimpleDateFormat cy = new SimpleDateFormat ("yyyy");

                            if(searchType.toLowerCase().contains("rent")){
                                Property p = new Property(0 , rec_manger_id, regionId, UrlId.getPurls() , V_id , agencyName.isEmpty() || agencyName == null ? "Private vendor / Unassigned vendor" : "Institutional vendor" , publishDate, updateDate, searchType, msRegion, catOfProperty, propertyType, titleOfAdd, streetNumber, postalCode, cIty, sellPrice, sellGrossPrice, sellNetPrice, sellPaymentType, Integer.toString(rentPrice), Integer.toString(rentGrossPrice), Integer.toString(rentNetPrice), rentPaymentType, numberOfRooms, livingSpace, propertyFloor, propertyArea, propertyDesc, featuresOfProperty, propertyAvailable, yearBuilt, nameOfSeller, agencyName, streetNumberOfSeller, postalCodeOfSeller, cityOfSeller, phoneOfSeller, mNumberOfSeller, emailOfSeller, contactName, contactNumber, "homegate.ch", urlOfAd, ct.format(dNow), cd.format(dNow), cm.format(dNow), cy.format(dNow));
                                int gri = pSvc.addNew(p);

                                if(gri > 0){
                                    
                                    if(iu.size() > 0){
                                        ArrayList<ImageUrls> siu = new ArrayList<>();
                                        iu.forEach((dfl) -> {
                                            siu.add(new ImageUrls(Integer.toString(gri),dfl.getUrl()));
                                        });

                                        pSvc.addImageUrls(siu);
                                    }

                                    p.setId(gri);
                                    properties.add(new PropertyListJSON(Integer.toString(p.getId()), p.getTitleOfAdd(), (p.getRentPrice() != null && p.getRentPrice().equals("0") == false ? p.getRentPrice() : p.getRentGrossPrice() != null && p.getRentGrossPrice().equals("0") == false ? p.getRentGrossPrice() : p.getRentPrice()), p.getPostalCode() , UrlId.getmRegion() , convertDateToNormalFormat(p.getDate_()) ,p.getTime_()));
                                
                                }
                            }else if(searchType.toLowerCase().contains("sale")){
                                Property p = new Property(0 , rec_manger_id, regionId, UrlId.getPurls(), V_id, agencyName.isEmpty() || agencyName == null ? "Private vendor / Unassigned vendor" : "Institutional vendor", publishDate, updateDate, searchType, msRegion, catOfProperty, propertyType, titleOfAdd, streetNumber, postalCode, cIty, sellPrice, sellGrossPrice, sellNetPrice, sellPaymentType, Integer.toString(rentPrice), Integer.toString(rentGrossPrice), Integer.toString(rentNetPrice), rentPaymentType, numberOfRooms, livingSpace, propertyFloor, propertyArea, propertyDesc, featuresOfProperty, propertyAvailable, yearBuilt, nameOfSeller, agencyName, streetNumberOfSeller, postalCodeOfSeller, cityOfSeller, phoneOfSeller, mNumberOfSeller, emailOfSeller, contactName, contactNumber, "homegate.ch", urlOfAd, ct.format(dNow), cd.format(dNow), cm.format(dNow), cy.format(dNow));
                                int gri = pSvc.addNew(p);
                                
                                if(gri > 0){

                                    if(iu.size() > 0){
                                        ArrayList<ImageUrls> siu = new ArrayList<>();
                                        iu.forEach((dfl) -> {
                                            siu.add(new ImageUrls(Integer.toString(gri),dfl.getUrl()));
                                        });

                                        pSvc.addImageUrls(siu);
                                    }

                                    p.setId(gri);
                                    properties.add(new PropertyListJSON(Integer.toString(p.getId()), p.getTitleOfAdd(), p.getSellPrice(), p.getPostalCode() , UrlId.getmRegion() ,convertDateToNormalFormat(p.getDate_()) ,p.getTime_()));
                                
                                }
                            }else{
                                Property p = new Property(0 , rec_manger_id, regionId, UrlId.getPurls(), V_id , agencyName.isEmpty() || agencyName == null ? "Private vendor / Unassigned vendor" : "Institutional vendor" , publishDate, updateDate, searchType, msRegion, catOfProperty, propertyType, titleOfAdd, streetNumber, postalCode, cIty, sellPrice, sellGrossPrice, sellNetPrice, sellPaymentType, Integer.toString(rentPrice), Integer.toString(rentGrossPrice), Integer.toString(rentNetPrice), rentPaymentType, numberOfRooms, livingSpace, propertyFloor, propertyArea, propertyDesc, featuresOfProperty, propertyAvailable, yearBuilt, nameOfSeller, agencyName, streetNumberOfSeller, postalCodeOfSeller, cityOfSeller, phoneOfSeller, mNumberOfSeller, emailOfSeller, contactName, contactNumber, "homegate.ch", urlOfAd, ct.format(dNow), cd.format(dNow), cm.format(dNow), cy.format(dNow));
                                int gri = pSvc.addNew(p);
                                
                                if(gri > 0){
                                
                                    if(iu.size() > 0){
                                        ArrayList<ImageUrls> siu = new ArrayList<>();
                                        iu.forEach((dfl) -> {
                                            siu.add(new ImageUrls(Integer.toString(gri),dfl.getUrl()));
                                        });

                                        pSvc.addImageUrls(siu);
                                    }

                                    p.setId(gri);
                                    properties.add(new PropertyListJSON(Integer.toString(p.getId()), p.getTitleOfAdd(), (p.getRentPrice() != null && p.getRentPrice().equals("0") == false ? p.getRentPrice() : p.getRentGrossPrice() != null && p.getRentGrossPrice().equals("0") == false ? p.getRentGrossPrice() : p.getRentPrice() != null && p.getRentPrice().equals("0") == false ? p.getRentPrice() : p.sellPrice), p.getPostalCode() , UrlId.getmRegion() ,convertDateToNormalFormat(p.getDate_()) ,p.getTime_()));
                                    
                                }
                            }
                        }
                    } catch (JSONException e) {
                           System.out.println("Home Gate Single Proprty Json Parsing Error : " + e.getMessage());
                    }

                }catch(HttpStatusException ex) {
                    System.out.println("HomeGate Sub Posts Http Status Exception : " + ex.getMessage());
                }catch(IOException ex){
                    System.out.println("HomeGate IO Exception : " + ex.getMessage());
                }
            //}
        }
        
       return properties;
    }
    
    public String convertDateFormat(String adDate){
        String str = "";
        
        try{
            
            SimpleDateFormat incomingDateFormat = new SimpleDateFormat("dd.MM.yyyy");
            Date _adDate_ = incomingDateFormat.parse(adDate);
            
            SimpleDateFormat newDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            str = newDateFormat.format(_adDate_);
            
        }catch(ParseException e){
            SimpleDateFormat newDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date dNow = new Date();
            str = newDateFormat.format(dNow);
        }
        
        return str;
    }
    
    public String convertDateToNormalFormat(String dbDate){
        String str = "";
        
        try{
            
            SimpleDateFormat incomingDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date _dbDate_ = incomingDateFormat.parse(dbDate);
            
            SimpleDateFormat newDateFormat = new SimpleDateFormat("dd-MM-yyyy");
            str = newDateFormat.format(_dbDate_);
            
        }catch(ParseException e){
            str = "";
        }
        
        return str;
    }
    
    public boolean publishDateCheck(String adDate, String givenFromDate, String givenToDate){
        boolean str = false;
                
        try{
            SimpleDateFormat adDateFormatter = new SimpleDateFormat("dd.MM.yyyy");
            Date _adDate_ = adDateFormatter.parse(adDate);
            
            SimpleDateFormat incomingDatesFormatter = new SimpleDateFormat("dd.MM.yyyy");
            Date _givenFromDate_ = incomingDatesFormatter.parse(givenFromDate);
            Date _givenToDate_ = incomingDatesFormatter.parse(givenToDate);
                        
            str = _adDate_.compareTo(_givenFromDate_) >= 0 && _adDate_.compareTo(_givenToDate_) <= 0;
            
        }catch(ParseException e){
            str = true;
        }
        
        return str;
    }
    
    public String getPropertyCat(String searchCat){
        
        Map<String,String> map = new HashMap<>(); 
        
        map.put("purchflat", "Apartment");
        map.put("purchfaofh", "Apartment and House");
        map.put("purchofh", "House, Chalet, Rustico");
        map.put("purchland", "Building plot");
        map.put("purchbof", "Multi-Family House");
        map.put("purchofhbobj", "Commercial and Residential Building");
        map.put("purchgarage", "Parking Space, Garage");
        map.put("purchbobj", "Commercial, Office, Storage Room");
        map.put("rentflat","Apartment and House");
        map.put("rentflatonly","Apartment");
        map.put("rentofh","House, Chalet, Rustico");
        map.put("rentfurnflat","Furnished Dwelling");
        map.put("rentgarage","Parking Space, Garage");
        map.put("rentoffice","Office");
        map.put("rentbobj","Commercial and Industry");
        map.put("rentstorage","Storage Room");
        
        return map.get(searchCat) != null ? map.get(searchCat) : "";
    }
            
    public Integer GetVendorTypeVerified(ArrayList<String> vendorType, String dataCondition){
        int str = 0;
        
        for (String dfl : vendorType)  
        { 
            if(dfl.equals("Unassigned vendor") && (dataCondition.isEmpty() || dataCondition == null)){
                str++;
            }else if(dfl.equals("Private vendor") && (dataCondition.isEmpty() || dataCondition == null)){
                str++;
            }else if(dfl.equals("Institutional vendor") && dataCondition.isEmpty() == false && dataCondition != null){
                str++;
            } 
        }
        
        return str;
    }    
    
    public String PropertyTypeList(String pT,String Category, String type){
        ArrayList<PTHomeGate> lopt=new ArrayList<>();
        lopt.add(new PTHomeGate("APPT,1","Appartment","Apartment and House , Apartment" , "buy , rent"));
        lopt.add(new PTHomeGate("APPT,2","Duplex","Apartment and House , Apartment", "buy , rent"));
        lopt.add(new PTHomeGate("APPT,3","Attic Flat","Apartment and House , Apartment", "buy , rent"));
        lopt.add(new PTHomeGate("APPT,4","Roof Flat","Apartment and House , Apartment", "buy , rent"));
        lopt.add(new PTHomeGate("APPT,5","Studio","Apartment and House , Apartment", "buy , rent"));
        lopt.add(new PTHomeGate("APPT,6","Single Room","Apartment and House , Apartment", "buy , rent"));
        lopt.add(new PTHomeGate("APPT,7","Furnished flat","Apartment and House , Apartment", "buy"));
        lopt.add(new PTHomeGate("APPT,8","Terrace Flat","Apartment and House , Apartment", "buy , rent"));
        lopt.add(new PTHomeGate("APPT,9","Bachelor Flat","Apartment and House , Apartment", "buy , rent"));
        lopt.add(new PTHomeGate("APPT,10","Loft","Apartment and House , Apartment", "buy , rent"));
        lopt.add(new PTHomeGate("APPT,11","Mansard","Apartment and House , Apartment", "buy , rent"));
        lopt.add(new PTHomeGate("HOUSE,1","Single House","Apartment and House","buy , rent"));
        
        lopt.add(new PTHomeGate("HOUSE,2","Row House","Apartment and House , House, Chalet, Rustico","buy , rent"));
        lopt.add(new PTHomeGate("HOUSE,3","Bifamiliar House","Apartment and House , House, Chalet, Rustico","buy , rent"));
        lopt.add(new PTHomeGate("HOUSE,4","Terrace House","Apartment and House , House, Chalet, Rustico","buy , rent"));
        lopt.add(new PTHomeGate("HOUSE,5","Villa","Apartment and House , House, Chalet, Rustico","buy , rent"));
        lopt.add(new PTHomeGate("HOUSE,6","Farm House","Apartment and House , House, Chalet, Rustico","buy , rent"));
        lopt.add(new PTHomeGate("HOUSE,9","Cave House / earthen house","Apartment and House , House, Chalet, Rustico","buy , rent"));
        lopt.add(new PTHomeGate("HOUSE,10","Castle","Apartment and House , House, Chalet, Rustico","buy , rent"));
        lopt.add(new PTHomeGate("HOUSE,11","Granny Flat","Apartment and House , House, Chalet, Rustico","buy , rent"));
        lopt.add(new PTHomeGate("HOUSE,12","Chalet","Apartment and House , House, Chalet, Rustico","buy , rent"));
        lopt.add(new PTHomeGate("HOUSE,13","Rustic House","Apartment and House , House, Chalet, Rustico","buy , rent"));
        lopt.add(new PTHomeGate("SECONDARY,0","Hobby Room","Apartment and House","buy , rent"));
        lopt.add(new PTHomeGate("SECONDARY,1","Cellar compartment","Apartment and House","buy , rent"));
        lopt.add(new PTHomeGate("SECONDARY,2","Attic compartment","Apartment and House","buy , rent"));
        lopt.add(new PTHomeGate("GARDEN,0","Alottment Garden","Apartment and House","rent"));
        
        lopt.add(new PTHomeGate("PARK,1","Open Slot","Parking Space, Garage","buy , rent"));
        lopt.add(new PTHomeGate("PARK,2","Covered Slot","Parking Space, Garage","buy , rent"));
        lopt.add(new PTHomeGate("PARK,3","Single Garage","Parking Space, Garage","buy , rent"));
        lopt.add(new PTHomeGate("PARK,4","Double Garage","Parking Space, Garage","buy , rent"));
        lopt.add(new PTHomeGate("PARK,5","Underground Garage","Parking Space, Garage","buy , rent"));
        lopt.add(new PTHomeGate("PARK,7","Boat dry Dock","Parking Space, Garage","buy , rent"));
        lopt.add(new PTHomeGate("PARK,8","Boat Landing Stage","Parking Space, Garage","buy , rent"));
        lopt.add(new PTHomeGate("PARK,9","Bike Covered Parking","Parking Space, Garage","buy , rent"));
        lopt.add(new PTHomeGate("PARK,10","Bike Outdoor Parking","Parking Space, Garage","buy , rent"));
        lopt.add(new PTHomeGate("PARK,11","Horse box","Parking Space, Garage","buy"));
        lopt.add(new PTHomeGate("PARK,12","Boat Mooring","Parking Space, Garage","buy , rent"));
        
        lopt.add(new PTHomeGate("AGRI,1","Agricultural Installation","Commercial, Office, Storage Room","buy"));
        lopt.add(new PTHomeGate("AGRI,2","Mountain Farm","Commercial, Office, Storage Room","buy"));
        lopt.add(new PTHomeGate("AGR,3","Farm","Commercial, Office, Storage Room","buy"));
        lopt.add(new PTHomeGate("GASTRO,1","Hotel","Commercial, Office, Storage Room","buy"));
        lopt.add(new PTHomeGate("GASTRO,2","Restaurant","Commercial, Office, Storage Room","buy"));
        lopt.add(new PTHomeGate("GASTRO,3","Coffeehouse","Commercial, Office, Storage Room","buy"));
        lopt.add(new PTHomeGate("GASTRO,4","Bar","Commercial, Office, Storage Room","buy"));
        lopt.add(new PTHomeGate("GASTRO,5","Club / Disco","Commercial, Office, Storage Room","buy"));
        lopt.add(new PTHomeGate("GASTRO,6","Casino","Commercial, Office, Storage Room","buy"));
        lopt.add(new PTHomeGate("GASTRO,7","Movie / theater","Commercial, Office, Storage Room","buy"));
        lopt.add(new PTHomeGate("GASTRO,8","Squash / Badminton","Commercial, Office, Storage Room","buy"));
        lopt.add(new PTHomeGate("GASTRO,9","Indoor tennis courts","Commercial, Office, Storage Room","buy"));
        lopt.add(new PTHomeGate("GASTRO,10","Tennis court","Commercial, Office, Storage Room","buy"));
        lopt.add(new PTHomeGate("GASTRO,11","Sports Hall","Commercial, Office, Storage Room","buy"));
        lopt.add(new PTHomeGate("GASTRO,12","Campground / Tent Camping","Commercial, Office, Storage Room","buy"));
        lopt.add(new PTHomeGate("GASTRO,13","Outdoor Swimming pool","Commercial, Office, Storage Room","buy"));
        lopt.add(new PTHomeGate("GASTRO,14","Indoor Swimming pool","Commercial, Office, Storage Room","buy"));
        lopt.add(new PTHomeGate("GASTRO,15","Golf course","Commercial, Office, Storage Room","buy"));
        lopt.add(new PTHomeGate("GASTRO,16","Motel","Commercial, Office, Storage Room","buy"));
        lopt.add(new PTHomeGate("GASTRO,17","Pub","Commercial, Office, Storage Room","buy"));
        lopt.add(new PTHomeGate("INDUS,2","Shop","Commercial, Office, Storage Room","buy"));
        lopt.add(new PTHomeGate("INDUS,3","Advertising area","Commercial, Office, Storage Room","buy"));
        lopt.add(new PTHomeGate("INDUS,4","Commercial","Commercial, Office, Storage Room","buy"));
        lopt.add(new PTHomeGate("INDUS,5","Storage room","Commercial, Office, Storage Room","buy"));
        lopt.add(new PTHomeGate("INDUS,6","Practice","Commercial, Office, Storage Room","buy"));
        lopt.add(new PTHomeGate("INDUS,7","Kiosk","Commercial, Office, Storage Room","buy"));
        lopt.add(new PTHomeGate("INDUS,8","Gardening","Commercial, Office, Storage Room","buy"));
        lopt.add(new PTHomeGate("INDUS,9","Fuel Station","Commercial, Office, Storage Room","buy"));
        lopt.add(new PTHomeGate("INDUS,10","Garage","Commercial, Office, Storage Room","buy"));
        lopt.add(new PTHomeGate("INDUS,11","Cheese Factory","Commercial, Office, Storage Room","buy"));
        lopt.add(new PTHomeGate("INDUS,12","Butcher","Commercial, Office, Storage Room","buy"));
        lopt.add(new PTHomeGate("INDUS,13","Bakery","Commercial, Office, Storage Room","buy"));
        lopt.add(new PTHomeGate("INDUS,14","Hairdresser","Commercial, Office, Storage Room","buy"));
        lopt.add(new PTHomeGate("INDUS,15","Shopping Centre","Commercial, Office, Storage Room","buy"));
        lopt.add(new PTHomeGate("INDUS,16","Factory","Commercial, Office, Storage Room","buy"));
        lopt.add(new PTHomeGate("INDUS,17","Industrial object","Commercial, Office, Storage Room","buy"));
        lopt.add(new PTHomeGate("INDUS,18","Arcade","Commercial, Office, Storage Room","buy"));
        lopt.add(new PTHomeGate("INDUS,19","Atelier","Commercial, Office, Storage Room","buy"));
        lopt.add(new PTHomeGate("INDUS,20","Living / commercial building","Commercial, Office, Storage Room","buy"));
        lopt.add(new PTHomeGate("INDUS,21","Library","Commercial, Office, Storage Room","buy"));
        lopt.add(new PTHomeGate("INDUS,22","Hospital","Commercial, Office, Storage Room","buy"));
        lopt.add(new PTHomeGate("INDUS,23","Laboratory","Commercial, Office, Storage Room","buy"));
        lopt.add(new PTHomeGate("INDUS,24","Mini-golf course","Commercial, Office, Storage Room","buy"));
        lopt.add(new PTHomeGate("INDUS,25","nursing home","Commercial, Office, Storage Room","buy"));
        lopt.add(new PTHomeGate("INDUS,26","Riding hall","Commercial, Office, Storage Room","buy"));
        lopt.add(new PTHomeGate("INDUS,27","Sanatorium","Commercial, Office, Storage Room","buy"));
        lopt.add(new PTHomeGate("INDUS,28","Workshop","Commercial, Office, Storage Room","buy"));
        lopt.add(new PTHomeGate("INDUS,29","Party Room","Commercial, Office, Storage Room","buy"));
        lopt.add(new PTHomeGate("INDUS,30","Sauna","Commercial, Office, Storage Room","buy"));
        lopt.add(new PTHomeGate("INDUS,31","Solarium","Commercial, Office, Storage Room","buy"));
        lopt.add(new PTHomeGate("INDUS,32","Carpentry shop","Commercial, Office, Storage Room","buy"));
        lopt.add(new PTHomeGate("INDUS,33","Old-age home","Commercial, Office, Storage Room","buy"));
        lopt.add(new PTHomeGate("INDUS,34","Department store","Commercial, Office, Storage Room","buy"));
        lopt.add(new PTHomeGate("INDUS,35","Home","Commercial, Office, Storage Room","buy"));
        lopt.add(new PTHomeGate("INDUS,36","Display Window","Commercial, Office, Storage Room","buy"));
        lopt.add(new PTHomeGate("INDUS,37","Parking garage","Commercial, Office, Storage Room","buy"));
        lopt.add(new PTHomeGate("INDUS,38","Parking surface","Commercial, Office, Storage Room","buy"));
        
        lopt.add(new PTHomeGate("AGRI,1","Agricultural Installation","Commercial and Industry","rent"));
        lopt.add(new PTHomeGate("AGRI,2","Mountain Farm","Commercial and Industry","rent"));
        lopt.add(new PTHomeGate("AGR,3","Farm","Commercial and Industry","rent"));
        lopt.add(new PTHomeGate("GASTRO,1","Hotel","Commercial and Industry","rent"));
        lopt.add(new PTHomeGate("GASTRO,2","Restaurant","Commercial and Industry","rent"));
        lopt.add(new PTHomeGate("GASTRO,3","Coffeehouse","Commercial and Industry","rent"));
        lopt.add(new PTHomeGate("GASTRO,4","Bar","Commercial and Industry","rent"));
        lopt.add(new PTHomeGate("GASTRO,5","Club / Disco","Commercial and Industry","rent"));
        lopt.add(new PTHomeGate("GASTRO,6","Casino","Commercial and Industry","rent"));
        lopt.add(new PTHomeGate("GASTRO,7","Movie / theater","Commercial and Industry","rent"));
        lopt.add(new PTHomeGate("GASTRO,8","Squash / Badminton","Commercial and Industry","rent"));
        lopt.add(new PTHomeGate("GASTRO,9","Indoor tennis courts","Commercial and Industry","rent"));
        lopt.add(new PTHomeGate("GASTRO,10","Tennis court","Commercial and Industry","rent"));
        lopt.add(new PTHomeGate("GASTRO,11","Sports Hall","Commercial and Industry","rent"));
        lopt.add(new PTHomeGate("GASTRO,12","Campground / Tent Camping","Commercial and Industry","rent"));
        lopt.add(new PTHomeGate("GASTRO,13","Outdoor Swimming pool","Commercial and Industry","rent"));
        lopt.add(new PTHomeGate("GASTRO,14","Indoor Swimming pool","Commercial and Industry","rent"));
        lopt.add(new PTHomeGate("GASTRO,15","Golf course","Commercial and Industry","rent"));
        lopt.add(new PTHomeGate("GASTRO,16","Motel","Commercial and Industry","rent"));
        lopt.add(new PTHomeGate("GASTRO,17","Pub","Commercial and Industry","rent"));
        lopt.add(new PTHomeGate("INDUS,2","Shop","Commercial and Industry","rent"));
        lopt.add(new PTHomeGate("INDUS,3","Advertising area","Commercial and Industry","rent"));
        lopt.add(new PTHomeGate("INDUS,4","Commercial","Commercial and Industry","rent"));
        lopt.add(new PTHomeGate("INDUS,6","Practice","Commercial and Industry","rent"));
        lopt.add(new PTHomeGate("INDUS,7","Kiosk","Commercial and Industry","rent"));
        lopt.add(new PTHomeGate("INDUS,8","Gardening","Commercial and Industry","rent"));
        lopt.add(new PTHomeGate("INDUS,9","Fuel Station","Commercial and Industry","rent"));
        lopt.add(new PTHomeGate("INDUS,10","Garage","Commercial and Industry","rent"));
        lopt.add(new PTHomeGate("INDUS,11","Cheese Factory","Commercial and Industry","rent"));
        lopt.add(new PTHomeGate("INDUS,12","Butcher","Commercial and Industry","rent"));
        lopt.add(new PTHomeGate("INDUS,13","Bakery","Commercial and Industry","rent"));
        lopt.add(new PTHomeGate("INDUS,14","Hairdresser","Commercial and Industry","rent"));
        lopt.add(new PTHomeGate("INDUS,15","Shopping Centre","Commercial and Industry","rent"));
        lopt.add(new PTHomeGate("INDUS,16","Factory","Commercial and Industry","rent"));
        lopt.add(new PTHomeGate("INDUS,17","Industrial object","Commercial and Industry","rent"));
        lopt.add(new PTHomeGate("INDUS,18","Arcade","Commercial and Industry","rent"));
        lopt.add(new PTHomeGate("INDUS,19","Atelier","Commercial and Industry","rent"));
        lopt.add(new PTHomeGate("INDUS,20","Living / commercial building","Commercial and Industry","rent"));
        lopt.add(new PTHomeGate("INDUS,21","Library","Commercial and Industry","rent"));
        lopt.add(new PTHomeGate("INDUS,22","Hospital","Commercial and Industry","rent"));
        lopt.add(new PTHomeGate("INDUS,23","Laboratory","Commercial and Industry","rent"));
        lopt.add(new PTHomeGate("INDUS,24","Mini-golf course","Commercial and Industry","rent"));
        lopt.add(new PTHomeGate("INDUS,25","nursing home","Commercial and Industry","rent"));
        lopt.add(new PTHomeGate("INDUS,26","Riding hall","Commercial and Industry","rent"));
        lopt.add(new PTHomeGate("INDUS,27","Sanatorium","Commercial and Industry","rent"));
        lopt.add(new PTHomeGate("INDUS,28","Workshop","Commercial and Industry","rent"));
        lopt.add(new PTHomeGate("INDUS,29","Party Room","Commercial and Industry","rent"));
        lopt.add(new PTHomeGate("INDUS,30","Sauna","Commercial and Industry","rent"));
        lopt.add(new PTHomeGate("INDUS,31","Solarium","Commercial and Industry","rent"));
        lopt.add(new PTHomeGate("INDUS,32","Carpentry shop","Commercial and Industry","rent"));
        lopt.add(new PTHomeGate("INDUS,33","Old-age home","Commercial and Industry","rent"));
        lopt.add(new PTHomeGate("INDUS,34","Department store","Commercial and Industry","rent"));
        lopt.add(new PTHomeGate("INDUS,35","Home","Commercial and Industry","rent"));
        lopt.add(new PTHomeGate("INDUS,36","Display Window","Commercial and Industry","rent"));
        lopt.add(new PTHomeGate("INDUS,37","Parking garage","Commercial and Industry","rent"));
        lopt.add(new PTHomeGate("INDUS,38","Parking surface","Commercial and Industry","rent"));
        
        lopt.add(new PTHomeGate("PROP,1","Building land","Building plot","buy"));
        lopt.add(new PTHomeGate("PROP,2","Agricultural land","Building plot","buy"));
        lopt.add(new PTHomeGate("PROP,3","Commercial land","Building plot","buy"));
        lopt.add(new PTHomeGate("PROP,4","Industrial land","Building plot","buy"));
        
        String str = "";
        
            for(PTHomeGate dfdl:lopt){
             if(dfdl.getSubchildKey().contains(pT) && dfdl.getChildKey().contains(Category) && dfdl.getParentKey().contains(type)){
              if(str.isEmpty() || str == null){
                  str = "&obt=" + dfdl.getKeyValue();
                  break;
              }else{
                  str = str + "," + dfdl.getKeyValue();
                  break;
              }   
             }
            }
        
        return str;
    }
    
    private ArrayList<String> CL(ArrayList<String> ll){
        ArrayList<String> cl = new ArrayList<>(); 
            for(String dfl:ll){
                cl.add(dfl);
            }
        return cl;
    }
    
    private String CCRZ(){
        Document doc;
        String StrnUrl = "https://www.homegate.ch/rs/ver-49/geo-areas?lan=en";
            try{
                doc = Jsoup.connect(StrnUrl).ignoreContentType(true).get();
                if(doc.text().isEmpty() == false && doc.text() != null){
                    StrnUrl = doc.text();
                }
            }catch(IOException e){
                System.out.println("Home Gate City List Requesting Error : " + e.getMessage());
            }
        return StrnUrl;
    }
}
