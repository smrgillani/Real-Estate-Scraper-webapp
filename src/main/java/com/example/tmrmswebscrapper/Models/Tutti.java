/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.tmrmswebscrapper.Models;

import com.example.tmrmswebscrapper.Svc.PropertySvcI;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Connection;
import org.jsoup.Connection.Method;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 *
 * @author Smr
 */
public class Tutti {
    public ArrayList<PropertyListJSON> GetTotalResultsCount(PropertySvcI pSvc, String rec_manger_id, int regionId, String searchIn, String cityName, String regionName, ArrayList<String> vendortype, String adPublishFrom, String adPublishTo){
        
        ArrayList<PropertyListJSON> props = new ArrayList<>();
        String getcityName = checkIfCityExist(cityName);
        
        if(getcityName != null && getcityName.length() > 0){
        
            String totalPostCount = getTotalPostCount(getcityName, searchIn);
        
            if(totalPostCount != null && totalPostCount.length() > 0){
                props = GetPropertiesRecord(pSvc, searchIn, rec_manger_id, regionId, getcityName, regionName, totalPostCount, vendortype, adPublishFrom, adPublishTo);
            }
        
        }
        
        return props.size() > 0 ? props : null ;
    }
        
    private ArrayList<PropertyListJSON> GetPropertiesRecord(PropertySvcI pSvc, String ST, String rec_manger_id, int regionId, String CityId, String regionName, String totalPostCount, ArrayList<String> vendortype, String adPublishFrom, String adPublishTo){
        ArrayList<PropertyListJSON> properties = new ArrayList<>();
        
        double totalPosts = Double.parseDouble(totalPostCount);
        
        double totalPages = Math.ceil(totalPosts / 100);
        
        int tp = (int)totalPages;
        
        if(tp > 1){
        
        Document doc;
        
        Map<String,String> headersData = new HashMap<>();
        headersData.put("content-type","application/json;charset=UTF-8");
        
        for(int i = 0; i < tp; i++){
            
            Connection.Response res = null;
            
            try{
                
                String categories = "\"apartment\",\"house\",\"flat_share\",\"holiday_object\",\"ground\",\"parking\",\"commercial\"";
            
                String jsonPayload = "{\"real_estate_types\":[" + categories + "],\"transaction_type\":\"" + ST.toLowerCase() + "\",\"localities\":[\"" + CityId + "\"],\"aggregated\":true,\"paging\":{\"page\":" + i +",\"size\":100}}";
            
                String response = Jsoup.connect("https://www.tutti.ch/api/v10/mapsearch/real_estate_search.json").maxBodySize(0).headers(headersData).ignoreContentType(true).method(Method.POST).requestBody(jsonPayload).execute().body();
               
                JSONObject obj = new JSONObject(response);
                
                JSONArray jsonarray = obj.getJSONArray("items");
                
                if(jsonarray != null && jsonarray.length() > 0){
                    
                    for(int c=0; c < jsonarray.length(); c++){
                        
                        JSONObject jsonobject = jsonarray.getJSONObject(c);
                
                        String V_id = (jsonobject.has("user_alias") ? jsonobject.getString("user_alias") : "");

                        String agencyName = (jsonobject.has("user_alias") ? jsonobject.getString("user_alias") : "");
                        
                        agencyName = GetVendorTypeVerified(vendortype, agencyName);

                        String publishDate = (jsonobject.has("epoch_time") ? jsonobject.getBigInteger("epoch_time").toString() : "");

                        boolean pbc = adPublishFrom.isEmpty() || adPublishTo.isEmpty() ? true : publishDateCheck(publishDate, adPublishFrom, adPublishTo);
                                                
                        boolean vendorVerify = (jsonobject.has("company_ad") ? jsonobject.getBoolean("company_ad") : false);
                        
                        boolean vendorAprExist = (jsonobject.has("company_ad") ? true : false);
                        
                        if(pSvc.getVendorbyIdAndWebSite(V_id, "tutti.ch") <= 0 && (vendortype.size() == 0 || ( vendortype.size() > 0 && GetVendorTypeCondition(vendortype, (vendorAprExist ? (vendorVerify ? "-" :"") : "")) > 0)) && pbc){
                                                
                            String featuresOfProperty = "";
                            
                            String updateDate = "";
                    
                            String searchType = ST.toLowerCase().contains("buy") ? "sale" : "rent";
                    
                            String msRegion = regionName;
                    
                            JSONObject propCatInfo = (jsonobject.has("category_info") ? jsonobject.getJSONObject("category_info") : null);
                            
                            String catOfProperty = (propCatInfo.has("name") ? getCategoryName(propCatInfo.getString("name")) : "");
                            
                            String propertyType = "";
                    
                            String titleOfAdd = (jsonobject.has("subject") ? jsonobject.getString("subject") : "");
                    
                            JSONArray paramArray = jsonobject.getJSONArray("parameters");
                            
                            String streetNumber = getValuesFromParamArray(paramArray, "address");
                    
                            String postalCode = getValuesFromParamArray(paramArray, "zipcode");
                    
                            String cIty = getValuesFromParamArray(paramArray, "subarea");
                    
                            String sellPrice = "";

                            if(searchType.toLowerCase().contains("sale")){
                                sellPrice = getValuesFromParamArray(paramArray, "price");
                                sellPrice = (sellPrice != null && sellPrice.isEmpty() == false) ? sellPrice.replaceAll("\\D+","") : sellPrice;
                            }
                    
                            String sellGrossPrice = "";
                            String sellNetPrice = "";
                            String sellPaymentType = searchType;
                    
                            int rentPrice = 0;
                            int rentGrossPrice = 0;
                            int rentNetPrice = 0;
                    
                            if(searchType.toLowerCase().contains("rent")){
                                String priceData = getValuesFromParamArray(paramArray, "price");
                                
                                priceData = (priceData != null && priceData.isEmpty() == false) ? priceData.replaceAll("\\D+","") : priceData;
                                
                                rentPrice = Integer.parseInt(priceData != null && priceData.isEmpty() == false && priceData.length() > 0 ? priceData : "0");

                                rentGrossPrice = rentPrice;
                                rentNetPrice = rentPrice;
                            }
                    
                            String pId = (jsonobject.has("id") ? jsonobject.getString("id") : "");
                            
                            String rentPaymentType = searchType;
                            String numberOfRooms = getValuesFromParamArray(paramArray, "rooms");
                    
                            String livingSpace = getValuesFromParamArray(paramArray, "size");
                            livingSpace = livingSpace.length() > 0 ? livingSpace.replaceAll("\\D+","") : livingSpace;
                            String propertyFloor = "";
                            String propertyArea = "";
                            String propertyDesc = (jsonobject.has("body") ? jsonobject.getString("body") : "");
                            String propertyAvailable = "";

                            try{

                                if(propertyAvailable != null && propertyAvailable.isEmpty() == false){
                                    SimpleDateFormat adDateFormatter = new SimpleDateFormat("dd.MM.yyyy");
                                    Date date = adDateFormatter.parse(propertyAvailable);

                                    SimpleDateFormat cd_ = new SimpleDateFormat ("yyyy.MM.dd");
                                    propertyAvailable = cd_.format(date);
                                }

                            }catch (ParseException ex) {

                                System.out.println("New Home Single Post Page Site Parse Date Error : " + propertyAvailable);
                                propertyAvailable = "";

                            }
                    
                            String yearBuilt = "";
                            String nameOfSeller = (jsonobject.has("user_alias") ? jsonobject.getString("user_alias") : "");
                            String streetNumberOfSeller = "";
                            String postalCodeOfSeller = "";
                            String cityOfSeller = "";
                            String phoneOfSeller = (jsonobject.has("phone_hash") ? getPhoneNumber(pId, jsonobject.getString("phone_hash")) : "");
                            
                            String mNumberOfSeller = "";
                            String emailOfSeller = ""; 
                            String contactName = "";
                            String contactNumber = "";
                            String urlOfAd = (jsonobject.has("subject") ? jsonobject.getString("subject").replaceAll("-", "").toLowerCase() : "");
                            urlOfAd = urlOfAd.isEmpty() ? urlOfAd : urlOfAd.replaceAll("[^a-zA-Z0-9]", "-");
                            
                            urlOfAd = "https://www.tutti.ch/de/vi/zuerich/wiedikon/immobilien/wohnungen/" + urlOfAd + "/" + pId;
                                        
                            ArrayList<ImageUrls> iu = new ArrayList<>();

                            if(jsonobject.has("image_names")){
                                
                                JSONArray imgArray = jsonobject.getJSONArray("image_names");
                                
                                    if(imgArray != null && imgArray.length() > 0){
                                          for(int x=0; x < imgArray.length(); x++){
                                              String imgObject = imgArray.get(x).toString();
                                              iu.add(new ImageUrls("","https://c.tutti.ch/images/" + imgObject));
                                          }
                                    }
                            }

                            Date dNow = new Date( );
                            SimpleDateFormat ct = new SimpleDateFormat ("hh:mm a");
                            SimpleDateFormat cd = new SimpleDateFormat ("yyyy-MM-dd");
                            SimpleDateFormat cm = new SimpleDateFormat ("MMM");
                            SimpleDateFormat cy = new SimpleDateFormat ("yyyy");
                            
                            if(publishDate != null && publishDate.isEmpty() == false){
                                long unix_seconds = Long.parseLong(publishDate);
                                Date date = new Date(unix_seconds*1000L);
                                SimpleDateFormat cd_ = new SimpleDateFormat ("yyyy-MM-dd");
                                publishDate = cd_.format(date);
                            }else{
                                publishDate = cd.format(dNow);
                            }
                            
                            updateDate = cd.format(dNow);

                            if(searchType.toLowerCase().contains("rent")){

                                Property p = new Property(0 , rec_manger_id, regionId, pId, V_id , agencyName.isEmpty() || agencyName == null ? "Private vendor / Unassigned vendor" : "Institutional vendor" , publishDate, updateDate, searchType, msRegion, catOfProperty, propertyType, titleOfAdd, streetNumber, postalCode, cIty, sellPrice, sellGrossPrice, sellNetPrice, sellPaymentType, Integer.toString(rentPrice), Integer.toString(rentGrossPrice), Integer.toString(rentNetPrice), rentPaymentType, numberOfRooms, livingSpace, propertyFloor, propertyArea, propertyDesc, featuresOfProperty, propertyAvailable, yearBuilt, nameOfSeller, agencyName, streetNumberOfSeller, postalCodeOfSeller, cityOfSeller, phoneOfSeller, mNumberOfSeller, emailOfSeller, contactName, contactNumber, "tutti.ch", urlOfAd, ct.format(dNow), cd.format(dNow), cm.format(dNow), cy.format(dNow));
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
                                    properties.add(new PropertyListJSON(Integer.toString(p.getId()), p.getTitleOfAdd(), (p.getRentPrice() != null && p.getRentPrice().equals("0") == false ? p.getRentPrice() : p.getRentGrossPrice() != null && p.getRentGrossPrice().equals("0") == false ? p.getRentGrossPrice() : p.getRentPrice()), p.getPostalCode() , msRegion, convertDateToNormalFormat(p.getDate_()) ,p.getTime_()));

                                }

                            }else if(searchType.toLowerCase().contains("sale")){

                                Property p = new Property(0 , rec_manger_id, regionId, pId, V_id, agencyName.isEmpty() || agencyName == null ? "Private vendor / Unassigned vendor" : "Institutional vendor", publishDate, updateDate, searchType, msRegion, catOfProperty, propertyType, titleOfAdd, streetNumber, postalCode, cIty, sellPrice, sellGrossPrice, sellNetPrice, sellPaymentType, Integer.toString(rentPrice), Integer.toString(rentGrossPrice), Integer.toString(rentNetPrice), rentPaymentType, numberOfRooms, livingSpace, propertyFloor, propertyArea, propertyDesc, featuresOfProperty, propertyAvailable, yearBuilt, nameOfSeller, agencyName, streetNumberOfSeller, postalCodeOfSeller, cityOfSeller, phoneOfSeller, mNumberOfSeller, emailOfSeller, contactName, contactNumber, "tutti.ch", urlOfAd, ct.format(dNow), cd.format(dNow), cm.format(dNow), cy.format(dNow));
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
                                    properties.add(new PropertyListJSON(Integer.toString(p.getId()), p.getTitleOfAdd(), p.getSellPrice(), p.getPostalCode() , msRegion, convertDateToNormalFormat(p.getDate_()) ,p.getTime_()));

                                }

                            }else{

                                Property p = new Property(0 , rec_manger_id, regionId, pId, V_id , agencyName.isEmpty() || agencyName == null ? "Private vendor / Unassigned vendor" : "Institutional vendor" , publishDate, updateDate, searchType, msRegion, catOfProperty, propertyType, titleOfAdd, streetNumber, postalCode, cIty, sellPrice, sellGrossPrice, sellNetPrice, sellPaymentType, Integer.toString(rentPrice), Integer.toString(rentGrossPrice), Integer.toString(rentNetPrice), rentPaymentType, numberOfRooms, livingSpace, propertyFloor, propertyArea, propertyDesc, featuresOfProperty, propertyAvailable, yearBuilt, nameOfSeller, agencyName, streetNumberOfSeller, postalCodeOfSeller, cityOfSeller, phoneOfSeller, mNumberOfSeller, emailOfSeller, contactName, contactNumber, "tutti.ch", urlOfAd, ct.format(dNow), cd.format(dNow), cm.format(dNow), cy.format(dNow));
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
                                    properties.add(new PropertyListJSON(Integer.toString(p.getId()), p.getTitleOfAdd(), (p.getRentPrice() != null && p.getRentPrice().equals("0") == false ? p.getRentPrice() : p.getRentGrossPrice() != null && p.getRentGrossPrice().equals("0") == false ? p.getRentGrossPrice() : p.getRentPrice() != null && p.getRentPrice().equals("0") == false ? p.getRentPrice() : p.sellPrice), p.getPostalCode() , msRegion, convertDateToNormalFormat(p.getDate_()) ,p.getTime_()));

                                }

                            }
                    
                        }

                    }
                }
                
            } catch (IOException ex) {
                System.out.println("Tutti All Posts Page Site Request Error : " + ex.getMessage());
                break;
            }
            
        }
        
        }
        
        return properties;
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
    
    public String getPhoneNumber(String pId, String phoneHash){
        String vtr = "";
        try{
            
            String response = Jsoup.connect("https://www.tutti.ch/api/v10/item/phoneview.json?item_id=" + pId + "&phone_hash=" + phoneHash).maxBodySize(0).ignoreContentType(true).execute().body();
            
            String rv = response;
            try{
                
                JSONObject obj = new JSONObject(rv);
                
                vtr = String.valueOf(obj.has("number") ? obj.getString("number") : "0");
            
            }catch (JSONException e) {
                System.out.println("Tutti Get Property Phone Number Json Parsing Exception : " + e.getMessage());
            }
            
        }catch(IOException ex){
            System.out.println("Tutti Get Property Phone Number Exception : " + ex.getMessage());
        }
        
        return vtr;
    }
    
    public String getValuesFromParamArray(JSONArray dataArray, String conValue){
        String dtr = "";
        if(dataArray != null){
            for(int c=0; c < dataArray.length(); c++){
                JSONObject jsonobject = dataArray.getJSONObject(c);
                if(jsonobject.getString("id").toLowerCase().equals(conValue)){
                    dtr = (jsonobject.has("value") ? jsonobject.getString("value") : "");
                    break;
                }
            }
        }
        
        return dtr;
    }
    
    public String getCategoryName(String categoryParam){
        
        Map<String,String> categoriesData = new HashMap<>();
        
        categoriesData.put("Wohnungen","Apartments");
        categoriesData.put("Häuser","Houses");
        categoriesData.put("WG-Zimmer","Shared room");
        categoriesData.put("Ferienobjekte","Vacation objects");
        categoriesData.put("Grundstücke","Land");
        categoriesData.put("Parkplätze","Parking spaces");
        categoriesData.put("Gewerbeobjekte","Commercial properties");
                        
        return categoriesData.get(categoryParam) != null ? categoriesData.get(categoryParam) : "";
    }
    
    public boolean publishDateCheck(String adDate, String givenFromDate, String givenToDate){
        boolean str = false;
                
        try{
            SimpleDateFormat adDateFormatter = new SimpleDateFormat("dd.MM.yyyy");
            long unix_seconds = Long.parseLong(adDate);
            Date _adDate_ = new Date(unix_seconds*1000L);
            
            SimpleDateFormat incomingDatesFormatter = new SimpleDateFormat("dd.MM.yyyy");
            Date _givenFromDate_ = incomingDatesFormatter.parse(givenFromDate);
            Date _givenToDate_ = incomingDatesFormatter.parse(givenToDate);
                        
            str = _adDate_.compareTo(_givenFromDate_) >= 0 && _adDate_.compareTo(_givenToDate_) <= 0;
            
        }catch(ParseException e){
            str = true;
        }
        
        return str;
    }
    
    public Integer GetVendorTypeCondition(ArrayList<String> vendorType, String dataCondition){
        int str = 0;
        
        if((vendorType.stream().filter(a -> a.equals("Unassigned vendor")).collect(Collectors.toList()).size() > 0 || vendorType.stream().filter(a -> a.equals("Private vendor")).collect(Collectors.toList()).size() > 0) && (vendorType.stream().filter(a -> a.equals("Institutional vendor")).collect(Collectors.toList()).isEmpty()) && (dataCondition.isEmpty())){
            str = 1;
        }else if((vendorType.stream().filter(a -> a.equals("Unassigned vendor")).collect(Collectors.toList()).isEmpty() && vendorType.stream().filter(a -> a.equals("Private vendor")).collect(Collectors.toList()).isEmpty()) && vendorType.stream().filter(a -> a.equals("Institutional vendor")).collect(Collectors.toList()).size() > 0 && (dataCondition.isEmpty() == false)){
            str = 1;
        }else if((vendorType.stream().filter(a -> a.equals("Unassigned vendor")).collect(Collectors.toList()).size() > 0 || vendorType.stream().filter(a -> a.equals("Private vendor")).collect(Collectors.toList()).size() > 0) && (vendorType.stream().filter(a -> a.equals("Institutional vendor")).collect(Collectors.toList()).size() > 0) && (dataCondition.isEmpty() || dataCondition.isEmpty() == false)){
            str = 1;
        }
        
        return str;
    }
    
    public String GetVendorTypeVerified(ArrayList<String> vendorType, String data){
        String str = "";
        
        if((vendorType.stream().filter(a -> a.equals("Unassigned vendor")).collect(Collectors.toList()).size() > 0 || vendorType.stream().filter(a -> a.equals("Private vendor")).collect(Collectors.toList()).size() > 0) && (vendorType.stream().filter(a -> a.equals("Institutional vendor")).collect(Collectors.toList()).isEmpty())){
            str = "";
        }else if((vendorType.stream().filter(a -> a.equals("Unassigned vendor")).collect(Collectors.toList()).isEmpty() && vendorType.stream().filter(a -> a.equals("Private vendor")).collect(Collectors.toList()).isEmpty()) && vendorType.stream().filter(a -> a.equals("Institutional vendor")).collect(Collectors.toList()).size() > 0){
            str = data;
        }else if((vendorType.stream().filter(a -> a.equals("Unassigned vendor")).collect(Collectors.toList()).size() > 0 || vendorType.stream().filter(a -> a.equals("Private vendor")).collect(Collectors.toList()).size() > 0) && (vendorType.stream().filter(a -> a.equals("Institutional vendor")).collect(Collectors.toList()).size() > 0)){
            str = data;
        }
        
        return str;
    }
    
    public String getJson(String CityId, String SearchType){
        String vtr = "";
        try{
            Map<String,String> Pmap = new HashMap<>();
            Pmap.put("Content-Type","application/json;charset=UTF-8");
            
            String categories = "\"apartment\",\"house\",\"flat_share\",\"holiday_object\",\"ground\",\"parking\",\"commercial\"";
            
            String jsonPayload = "{\"real_estate_types\":[" + categories + "],\"transaction_type\":\"" + SearchType.toLowerCase() + "\",\"localities\":[\"" + CityId + "\"],\"count_only\":true}";
            
            String response = Jsoup.connect("https://www.tutti.ch/api/v10/mapsearch/real_estate_search.json").maxBodySize(0).headers(Pmap).ignoreContentType(true).method(Method.POST).requestBody(jsonPayload).execute().body();
            
            String rv = response;
            try{
                
                JSONObject obj = new JSONObject(rv);
                
                vtr = String.valueOf(obj.has("search_total") ? obj.getBigInteger("search_total") : 0);
            
            }catch (JSONException e) {
                System.out.println("Tutti Total Post Count Json Parsing Exception : " + e.getMessage());
            }
            
        }catch(IOException ex){
            System.out.println("Tutti Total Post Count Exception : " + ex.getMessage());
        }
        
        return vtr;
    }
    
    public String getTotalPostCount(String CityId, String SearchType){
        String vtr = "";
        try{
            Map<String,String> Pmap = new HashMap<>();
            Pmap.put("Content-Type","application/json;charset=UTF-8");
            
            String categories = "\"apartment\",\"house\",\"flat_share\",\"holiday_object\",\"ground\",\"parking\",\"commercial\"";
            
            String jsonPayload = "{\"real_estate_types\":[" + categories + "],\"transaction_type\":\"" + SearchType.toLowerCase() + "\",\"localities\":[\"" + CityId + "\"],\"count_only\":true}";
                        
            String response = Jsoup.connect("https://www.tutti.ch/api/v10/mapsearch/real_estate_search.json").maxBodySize(0).headers(Pmap).ignoreContentType(true).method(Method.POST).requestBody(jsonPayload).execute().body();
            
            String rv = response;
            try{
                
                JSONObject obj = new JSONObject(rv);
                
                vtr = String.valueOf(obj.has("search_total") ? obj.getBigInteger("search_total") : 0);
            
            }catch (JSONException e) {
                System.out.println("Tutti Total Post Count Json Parsing Exception : " + e.getMessage());
            }
            
        }catch(IOException ex){
            System.out.println("Tutti Total Post Count Exception : " + ex.getMessage());
        }
        
        return vtr;
    }
    
    public String checkIfCityExist(String CityName){
        String vtr = "";
        try{
            Map<String,String> Pmap = new HashMap<>();
            Pmap.put("Content-Type","application/json; charset=utf-8");
            Document doc;
                        
            doc = Jsoup.connect("https://www.tutti.ch/api/v10/mapsearch/localities.json?q=" + CityName).headers(Pmap).ignoreContentType(true).get();
            String rv = doc.text();
            try{
                
                JSONObject obj = new JSONObject(rv);
                
                JSONArray jsonarray = obj.getJSONArray("localities");
                
                if(jsonarray != null && jsonarray.length() > 0){
                    for(int i=0; i < jsonarray.length(); i++){
                        JSONObject jsonobject = jsonarray.getJSONObject(i);
                        if(jsonobject.getString("type").equals("city")){
                            vtr = jsonobject.getString("id");
                            break;
                        }
                    }
                }
            
            }catch (JSONException e) {
                System.out.println("Tutti City List Json Parsing Exception : " + e.getMessage());
            }
            
        }catch(IOException ex){
            System.out.println("Tutti check If City Exist Exception : " + ex.getMessage());
        }
        
        return vtr;
    }
}
