/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.tmrmswebscrapper.Models;

import com.example.tmrmswebscrapper.Svc.PropertySvcI;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author Smr
 */
public class NabHome {
    private RestTemplate proxyVar;
    
    public ArrayList<PropertyListJSON> GetTotalResultsCount(PropertySvcI pSvc, String rec_manger_id, int regionId, String searchIn, String cityName, String regionName, String searchCat, ArrayList<String> vendortype){
                
        ArrayList<PropertyListJSON> props = new ArrayList<>();

        ArrayList<String> cityIds = getCityNameAndId(cityName.toLowerCase());
        
        if(cityIds.size() > 0){
                        
            ArrayList<PropertyUrls> Urls = new ArrayList<>();

            for (String el : cityIds) {
                
               Urls.addAll(getAllLinks(pSvc, (searchIn.toLowerCase().contains("buy") ? "2" : "1"), searchCat, cityName, el));
            
            }
                        
            if(Urls.size() > 0){
                props = GetPropertiesRecord(pSvc, searchIn, rec_manger_id, regionId, regionName, Urls, vendortype);
            }
            
        }
        
        return props;
    }
    
    private ArrayList<PropertyUrls> getAllLinks(PropertySvcI pSvc, String searchType, String searchCat, String locationName, String locationId) {
        ArrayList<PropertyUrls> Urls = new ArrayList<>();
        String Url = "https://www.nabhome.ch/index.php?eID=nabhomeis24_search&t=" + searchType + "&s=" + searchCat +  "&l-title=" + locationName + "&r=0&addParams=&l=" + locationId + "&lng=de&nrf=0&suf=&nrt=0&sut=&pf=0&pt=0&se=1&pty=0&slf=0&slt=0&spf=&spt=&naf=&nat=&t-title=Mieten&s-title=Wohnung%2C%20Haus&description=NABHOME%3A%20Wohnung%2C%20Haus%20zum%20Mieten%20in%20Jonen&ps=30&pn=1&isNewQuery=1";
        
        HttpHeaders headersData = new HttpHeaders();
        headersData.add("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.116 Safari/537.36");
        
        String response = "";
            
            try{
                
                response = errorProneWebRequests(Url, HttpMethod.GET, null, headersData, null);
                
                if(response != null || response.isEmpty() == false){
                                        
                    JSONObject jObj = new JSONObject(response);
                    
                    jObj = jObj.has("PropertiesList") && jObj.isNull("PropertiesList") == false ? jObj.getJSONObject("PropertiesList") : jObj;
                    
                    if(jObj.has("TotalMatches") && jObj.isNull("TotalMatches") == false){
                                                
                        JSONArray rowsData = jObj.getJSONArray("Properties");
                                                
                        for(int i=0; i < rowsData.length(); i++){
                            
                            JSONObject jsonobject = rowsData.getJSONObject(i);
                            
                            String url = jsonobject.getString("URL");
                            
                            jsonobject = jsonobject.has("PropertyDetails") ? jsonobject.getJSONObject("PropertyDetails") : jsonobject;
                                                                                                            
                            String url_ = jsonobject.getBigInteger("PropertyID").toString();
                                                    
                            final String _url = url;
                                                    
                            if((url.isEmpty() == false && url.length() > 0) && (pSvc.getPostbyIdAndWebSite(url_, "nabhome.ch") <= 0) && (Urls.stream().filter(a -> a.purls.equals(_url)).collect(Collectors.toList()).size() <= 0)){
                                Urls.add(new PropertyUrls(url,locationName, getCategoryName(searchCat), ""));
                            }
                        
                        }
                        
                        boolean checkRowCount = jObj.has("TotalMatches");
                        
                        if(checkRowCount && jObj.getInt("TotalMatches") > 30){
                                                        
                            double totalRecords = jObj.getInt("TotalMatches");
                            
                            double totalPages = Math.ceil(totalRecords / 30);
                            
                            int tp = (int)totalPages;
                            
                            if(tp > 1){

                                for(int i = 2; i <= tp; i++){
                                    
                                    Url = "https://www.nabhome.ch/index.php?eID=nabhomeis24_search&t=" + searchType + "&s=" + searchCat +  "&l-title=" + locationName + "&r=0&addParams=&l=" + locationId + "&lng=de&nrf=0&suf=&nrt=0&sut=&pf=0&pt=0&se=1&pty=0&slf=0&slt=0&spf=&spt=&naf=&nat=&t-title=Mieten&s-title=Wohnung%2C%20Haus&description=NABHOME%3A%20Wohnung%2C%20Haus%20zum%20Mieten%20in%20Jonen&ps=30&pn=" + i + "&isNewQuery=1";
                                                                        
                                    response = errorProneWebRequests(Url, HttpMethod.GET, null, headersData, null);
                                    
                                    if(response != null || response.isEmpty() == false){
                                        
                                        jObj = new JSONObject(response);
                                        
                                        jObj = jObj.has("PropertiesList") && jObj.isNull("PropertiesList") == false ? jObj.getJSONObject("PropertiesList") : jObj;
                                        
                                        if(jObj.has("TotalMatches") && jObj.isNull("TotalMatches") == false){
                                            
                                            rowsData = jObj.getJSONArray("Properties");
                                            
                                            for(int j=0; j < rowsData.length(); j++){
                            
                                                JSONObject jsonobject = rowsData.getJSONObject(j);

                                                String url = jsonobject.getString("URL");

                                                jsonobject = jsonobject.has("PropertyDetails") ? jsonobject.getJSONObject("PropertyDetails") : jsonobject;
                                                
                                                String url_ = jsonobject.getBigInteger("PropertyID").toString();

                                                final String _url = url;

                                                if((url.isEmpty() == false && url.length() > 0) && (pSvc.getPostbyIdAndWebSite(url_, "nabhome.ch") <= 0) && (Urls.stream().filter(a -> a.purls.equals(_url)).collect(Collectors.toList()).size() <= 0)){
                                                    Urls.add(new PropertyUrls(url,locationName, getCategoryName(searchCat), ""));
                                                }

                                            }
                                            
                                        }
                                        
                                    }
                                    
                                }

                            }
                            
                        }
                                             
                    }

                }
                
            }
            catch(JSONException e){
                System.out.println(" Nab Home Get All Links Json Parsing Error => " + e.getMessage());
            }
            catch(Exception e){
                System.out.println(" Nab Home Get All Links General Error => " + e.getMessage());        
            }

//            System.out.println(Url);
//            System.out.println(locationName + " => : " + Urls.size());
            
        return Urls;
    }
    
     private ArrayList<PropertyListJSON> GetPropertiesRecord(PropertySvcI pSvc, String ST, String rec_manger_id, int regionId, String regionName, ArrayList<PropertyUrls> urls, ArrayList<String> vendortype){
        ArrayList<PropertyListJSON> properties = new ArrayList<>();
        Document doc;
        
        HttpHeaders headers = new HttpHeaders();
        headers.add("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.132 Safari/537.36");
        
        for(PropertyUrls UrlId:urls){
                                    
            try{
                                
                String response = errorProneWebRequests("https://www.nabhome.ch" + UrlId.getPurls(), HttpMethod.GET, null, headers, null);

                if(response != null && response.isEmpty() == false){
                    
                    Element providerDetails = null;
                    Element contactDetails = null;
                    
                    doc = Jsoup.parse(response);
                    
                    int getAgencyCount = doc.select("div.o-agency").size();
                    
                    String agencyName = "";
                    
                    String V_id = "";
                    
                    if(getAgencyCount > 1){
                        
                        for(Element el : doc.select("div.o-agency")){
                         
                            if(el.text().contains("Anbieter")){
                                providerDetails = el;
                            }else if(el.text().contains("Kontakt für")){
                                contactDetails = el;
                            }
                            
                        }
                        
                    }else if(getAgencyCount > 0){
                        
                        Element tempElm = doc.select("div.o-agency").first();
                        
                        if(tempElm.text().contains("Anbieter")){
                                providerDetails = tempElm;
                        }else if(tempElm.text().contains("Kontakt für")){
                                contactDetails = tempElm;
                        }
                        
                    }
                    
                    agencyName = providerDetails != null ? (providerDetails.select("p").first().text() != null ? providerDetails.select("p").first().text() : "") : "";
                    
                    V_id = providerDetails != null ? (providerDetails.select("img").first() != null ? providerDetails.select("img").first().attr("data-src") : "") : "";
                    
                    V_id = V_id != null && V_id.isEmpty() == false ? getVendorId(V_id) : "0";
                                        
                    agencyName = GetVendorTypeVerified(vendortype, agencyName);
                                        
                    String publishDate = "";
                    
                    if((pSvc.getVendorbyIdAndWebSite(V_id, "nabhome.ch") <= 0)){
                        
                        String featuresOfProperty = "";
                        
                        String updateDate = "";

                        String searchType = ST.toLowerCase().contains("buy") ? "SALE" : "RENT";

                        String msRegion = regionName;
                        
                        String catOfProperty = UrlId.getPropertyCategory();

                        String propertyType = "";
                        
                        String titleOfAdd = doc.select("h1.c-detail__heading") != null ? doc.select("h1.c-detail__heading").text() : "";
                                                                                                
                        String fullAddress = doc.select("span.o-lightboxmap__address").html();
                        
                        String[] fa = fullAddress.split("<br>");

                        String streetNumber = fa[0] != null ? fa[0] : "";

                        String postalCode = fa.length > 1 ? fa[1].replaceAll("\\D+","") : "";

                        String cIty = fa.length > 1 ? fa[1].replaceAll("\\d","") : "";
                                                
                        Elements rowTopSide = doc.select("div.c-detail__row.c-detail__row--top");
                        
                        Elements rowsTopRightSide = rowTopSide != null ? rowTopSide.select("div.c-detail__col.c-detail__col--right") : null;
                        
                        rowsTopRightSide = rowsTopRightSide != null ? rowsTopRightSide.select("div.o-infobox__row") : null;
                        
                        String sellPrice = "";
                        
                        if(searchType.toLowerCase().contains("sale")){
                            sellPrice = getValueFromList(rowsTopRightSide, " Kaufpreis".toLowerCase()).replaceAll("\\D+","");
                        }

                        String sellGrossPrice = "";
                        String sellNetPrice = "";
                        String sellPaymentType = searchType;

                        int rentPrice = 0;
                        int rentGrossPrice = 0;
                        int rentNetPrice = 0;

                        if(searchType.toLowerCase().contains("rent")){
                            
                            String priceData = getValueFromList(rowsTopRightSide, "Nettomiete".toLowerCase()) != null ? getValueFromList(rowsTopRightSide, "Nettomiete".toLowerCase()).replaceAll("\\D+","") : "0";
     
                            rentPrice = Integer.parseInt(priceData != null && priceData.isEmpty() == false && priceData.length() > 0 ? priceData : "0");

                            priceData = getValueFromList(rowsTopRightSide, "Bruttomiete".toLowerCase()) != null ? getValueFromList(rowsTopRightSide, "Bruttomiete".toLowerCase()).replaceAll("\\D+","") : "0";

                            rentGrossPrice = Integer.parseInt(priceData != null && priceData.isEmpty() == false && priceData.length() > 0 ? priceData : "0");
                            rentNetPrice = rentPrice;
                            
                        }

                        String pId = getValueFromList(rowsTopRightSide, "Objekt N​r".toLowerCase()) != null ? getValueFromList(rowsTopRightSide, "Objekt N​r.".toLowerCase()).replaceAll("\\D+","") : "0";

                        String rentPaymentType = searchType;
                                                
                        String numberOfRooms = getValueFromList(rowsTopRightSide, "Zimmer".toLowerCase()) != null ? getValueFromList(rowsTopRightSide, "Zimmer".toLowerCase()) : "0";

                        String livingSpace = getValueFromList(rowsTopRightSide, "Wohnfläche".toLowerCase()) != null ? getValueFromList(rowsTopRightSide, "Wohnfläche".toLowerCase()) : "0";
                        String propertyFloor = "";
                        String propertyArea = "";
                        
                        Elements rowBottom = doc.select("div.c-detail__row.c-detail__row--bottom > div.c-detail__col.c-detail__col--left > div.o-expandtext > div.o-expandtext__text");
                        
                        String propertyDesc = rowBottom != null ? rowBottom.text() : "";
                                                
                        String propertyAvailable = "";

                        String yearBuilt = getValueFromList(rowsTopRightSide, "Baujahr".toLowerCase()) != null ? getValueFromList(rowsTopRightSide, "Baujahr".toLowerCase()) : "0";
                        
                        Elements providorInformation = providerDetails != null ? providerDetails.select("p.o-paragraph.o-paragraph--no-margin-bottom") : null;
                        
                        String nameOfSeller = providorInformation != null && providorInformation.size() > 3 ? ( providorInformation.get(1).text() != null ? providorInformation.get(1).text() : "" ) : "";
                                                
                        String streetNumberOfSeller = providorInformation != null && providorInformation.size() > 2 ? ( providorInformation.get(providorInformation.size() - 2).text() != null ? providorInformation.get(providorInformation.size() - 2).text() : "" ) : ""; ;
                        String postalCodeOfSeller = providorInformation != null && providorInformation.size() > 2 ? ( providorInformation.get(providorInformation.size() - 1).text() != null ? providorInformation.get(providorInformation.size() - 1).text().replaceAll("\\D+","") : "" ) : "";
                        String cityOfSeller = providorInformation != null && providorInformation.size() > 2 ? ( providorInformation.get(providorInformation.size() - 1).text() != null ? providorInformation.get(providorInformation.size() - 1).text().replaceAll("\\d","") : "" ) : "";
                        
                        String phoneOfSeller = providerDetails != null ? ( providerDetails.select("div.o-phonenumber > a") != null ? providerDetails.select("div.o-phonenumber > a").text() : "" ) : "";
                                                
                        String mNumberOfSeller = "";
                                                
                        String emailOfSeller = "";
                                                
                        String contactName = contactDetails != null ? ( contactDetails.select("p.o-paragraph.o-paragraph--no-margin-bottom") != null ? contactDetails.select("p.o-paragraph.o-paragraph--no-margin-bottom").text() : "" ) : "";
                        
                        String contactNumber = contactDetails != null ? ( contactDetails.select("div.o-phonenumber > a") != null ? contactDetails.select("div.o-phonenumber > a").text() : "" ) : "";
                        
                        String urlOfAd = "https://www.nabhome.ch" + UrlId.getPurls();
                        
                        ArrayList<ImageUrls> iu = new ArrayList<>();
                        
                        Elements allImgUrls = doc.select("div.o-gallery__slide.swiper-slide");
                        
                        allImgUrls = allImgUrls != null ? allImgUrls.select("img") : allImgUrls;
                                                                        
                        if(allImgUrls != null && allImgUrls.size() > 0){
                            allImgUrls.forEach((link) -> {
                                iu.add(new ImageUrls("",link.attr("data-large")));
                            });
                        }
                        
                        Date dNow = new Date( );
                        SimpleDateFormat ct = new SimpleDateFormat ("hh:mm a");
                        SimpleDateFormat cd = new SimpleDateFormat ("yyyy-MM-dd");
                        SimpleDateFormat cm = new SimpleDateFormat ("MMM");
                        SimpleDateFormat cy = new SimpleDateFormat ("yyyy");
                        
                        publishDate = cd.format(dNow);
                        updateDate = cd.format(dNow);

                        if(searchType.toLowerCase().contains("rent")){

                            Property p = new Property(0 , rec_manger_id, regionId, pId, V_id , agencyName.isEmpty() || agencyName == null ? "Private vendor / Unassigned vendor" : "Institutional vendor" , publishDate, updateDate, searchType, msRegion, catOfProperty, propertyType, titleOfAdd, streetNumber, postalCode, cIty, sellPrice, sellGrossPrice, sellNetPrice, sellPaymentType, Integer.toString(rentPrice), Integer.toString(rentGrossPrice), Integer.toString(rentNetPrice), rentPaymentType, numberOfRooms, livingSpace, propertyFloor, propertyArea, propertyDesc, featuresOfProperty, propertyAvailable, yearBuilt, nameOfSeller, agencyName, streetNumberOfSeller, postalCodeOfSeller, cityOfSeller, phoneOfSeller, mNumberOfSeller, emailOfSeller, contactName, contactNumber, "nabhome.ch", urlOfAd, ct.format(dNow), cd.format(dNow), cm.format(dNow), cy.format(dNow));
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
                                properties.add(new PropertyListJSON(Integer.toString(p.getId()), p.getTitleOfAdd(), (p.getRentPrice() != null && p.getRentPrice().equals("0") == false ? p.getRentPrice() : p.getRentGrossPrice() != null && p.getRentGrossPrice().equals("0") == false ? p.getRentGrossPrice() : p.getRentPrice()), p.getPostalCode() , UrlId.getmRegion() ,convertDateToNormalFormat(p.getDate_()) ,p.getTime_()));

                            }

                        }else if(searchType.toLowerCase().contains("sale")){

                            Property p = new Property(0 , rec_manger_id, regionId, pId, V_id, agencyName.isEmpty() || agencyName == null ? "Private vendor / Unassigned vendor" : "Institutional vendor", publishDate, updateDate, searchType, msRegion, catOfProperty, propertyType, titleOfAdd, streetNumber, postalCode, cIty, sellPrice, sellGrossPrice, sellNetPrice, sellPaymentType, Integer.toString(rentPrice), Integer.toString(rentGrossPrice), Integer.toString(rentNetPrice), rentPaymentType, numberOfRooms, livingSpace, propertyFloor, propertyArea, propertyDesc, featuresOfProperty, propertyAvailable, yearBuilt, nameOfSeller, agencyName, streetNumberOfSeller, postalCodeOfSeller, cityOfSeller, phoneOfSeller, mNumberOfSeller, emailOfSeller, contactName, contactNumber, "nabhome.ch", urlOfAd, ct.format(dNow), cd.format(dNow), cm.format(dNow), cy.format(dNow));
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

                            Property p = new Property(0 , rec_manger_id, regionId, pId, V_id , agencyName.isEmpty() || agencyName == null ? "Private vendor / Unassigned vendor" : "Institutional vendor" , publishDate, updateDate, searchType, msRegion, catOfProperty, propertyType, titleOfAdd, streetNumber, postalCode, cIty, sellPrice, sellGrossPrice, sellNetPrice, sellPaymentType, Integer.toString(rentPrice), Integer.toString(rentGrossPrice), Integer.toString(rentNetPrice), rentPaymentType, numberOfRooms, livingSpace, propertyFloor, propertyArea, propertyDesc, featuresOfProperty, propertyAvailable, yearBuilt, nameOfSeller, agencyName, streetNumberOfSeller, postalCodeOfSeller, cityOfSeller, phoneOfSeller, mNumberOfSeller, emailOfSeller, contactName, contactNumber, "nabhome.ch", urlOfAd, ct.format(dNow), cd.format(dNow), cm.format(dNow), cy.format(dNow));
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
                
                }
                
            } catch (NumberFormatException ex) {
                System.out.println("Nab Home Single Post Number Formating Error : " + ex.getMessage());
            }catch (Exception ex) {
                System.out.println("Nab Home Single Post Page Site Request Error : " + ex.getMessage());
            }
            
        }
        
        return properties;
    }
     
    public String getValueFromList(Elements elms, String key){
        String str = "";
        
        for(Element elm:elms){
            
            String _key = elm.select("div.o-infobox__col.o-infobox__col--left").text();
            
            if(_key.toLowerCase().contains(key)){
                str = elm.select("div.o-infobox__col.o-infobox__col--right").text();
                break;
            }
            
        }
        
        return str;
    }
     
    public String getPropertyId(String url){
        String str = "";
        
        if(url != null && url.isEmpty() == false){
            
            Pattern p = Pattern.compile("/\\d+-");
            
            Matcher m = p.matcher(url);
            
            if(m.find()) {
                str = m.group(0);
                str = str != null && str.isEmpty() == false ? str.replaceAll("/","") : str;
                str = str != null && str.isEmpty() == false ? str.replaceAll("-","") : str;
            }
            
       }

        return str;
    }
     
    public String getCategoryName(String categoryParam){
        
        Map<String,String> categoriesData = new HashMap<>();
        
        categoriesData.put("1","Apartment and House");
        categoriesData.put("2","Apartment");
        categoriesData.put("3","House");
        categoriesData.put("4","Plot");
        categoriesData.put("5","Parking");
        categoriesData.put("7","Office, Trade, Industry");
        categoriesData.put("8","Other objects");
        categoriesData.put("9","Apartment building");
        categoriesData.put("10","Agriculture");
        
        return categoriesData.get(categoryParam) != null ? categoriesData.get(categoryParam) : "Apartment and House";
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
     
     public String GetVendorTypeVerified(ArrayList<String> vendorType, String data){
        String str = "";
        
        if((vendorType.stream().filter(a -> a.equals("Unassigned vendor")).collect(Collectors.toList()).size() > 0 || vendorType.stream().filter(a -> a.equals("Private vendor")).collect(Collectors.toList()).size() > 0) && (vendorType.stream().filter(a -> a.equals("Institutional vendor")).collect(Collectors.toList()).isEmpty())){
            str = "";
        }else if((vendorType.stream().filter(a -> a.equals("Unassigned vendor")).collect(Collectors.toList()).isEmpty() && vendorType.stream().filter(a -> a.equals("Private vendor")).collect(Collectors.toList()).isEmpty()) && vendorType.stream().filter(a -> a.equals("Institutional vendor")).collect(Collectors.toList()).size() > 0){
            str = data;
        }else if((vendorType.stream().filter(a -> a.equals("Unassigned vendor")).collect(Collectors.toList()).size() > 0 || vendorType.stream().filter(a -> a.equals("Private vendor")).collect(Collectors.toList()).size() > 0) && (vendorType.stream().filter(a -> a.equals("Institutional vendor")).collect(Collectors.toList()).size() > 0)){
            str = data;
        }else if(vendorType.isEmpty()){
            str = data;
        }
        
        return str;
    }
        
    public String getVendorId(String url){
        String str = "";
        
        if(url != null && url.isEmpty() == false){
            
            Pattern p = Pattern.compile("memberlogos/(.*?)-R.jpg");
            
            Matcher m = p.matcher(url);
            
            if(m.find()) {
                str = m.group(1);
            }
            
       }

        return str;
    }
    
    private RestTemplate setupProxy() throws Exception {
//        final String username = "mtyj2dhq";
//        final String password = "KIXd0iNSLjCo6GpC";
//        final String proxyUrl = "proxy.proxy-cheap.com";
//        final int port = 31112;
//        
//        CredentialsProvider credsProvider = new BasicCredentialsProvider();
//        credsProvider.setCredentials( 
//            new AuthScope(proxyUrl, port), 
//            new UsernamePasswordCredentials(username, password)
//        );
//
//        HttpHost myProxy = new HttpHost(proxyUrl, port);
//        HttpClientBuilder clientBuilder = HttpClientBuilder.create();
//
//        clientBuilder.setProxy(myProxy).setDefaultCredentialsProvider(credsProvider).disableCookieManagement();
//
//        HttpClient httpClient = clientBuilder.build();
//        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
//        factory.setHttpClient(httpClient);
//
//        return new RestTemplate(factory);
        return new RestTemplate();
    }
    
    public String getMainTypeGroup(){
        return "";
    }
    
    public String errorProneWebRequests(String Url, HttpMethod method, MultiValueMap<String, String> formData, HttpHeaders headersData, String payloadData){
               
        String result = null;
        
        RestTemplate restTemplate = null;
        
        try {
                        
//            restTemplate = this.proxyVar;
            
            restTemplate = setupProxy();
                        
            HttpEntity entity = new HttpEntity( (formData != null ? formData : payloadData), headersData);
            
            ResponseEntity<String> response = restTemplate.exchange(Url, method, entity, String.class);
            
            result = response.getBody();
                        
        } catch (RestClientException ex) {
        
            System.out.println("Nab Home Error From Error Prone Request Normal " + ex.getMessage() + " => " + payloadData);
        
                try {

                    this.proxyVar = setupProxy();
                    
                    restTemplate = this.proxyVar;
                    
                    HttpEntity entity = new HttpEntity(formData, headersData);
                        
                    ResponseEntity<String> response = restTemplate.exchange(Url, method, entity, String.class);

                    result = response.getBody();
                    
                } catch (Exception ex1) {
                    System.out.println("Nab Home Error From Error Prone Request Abnormal " + ex1.getMessage() + " => " + payloadData);
                }    
        }catch (Exception ex) {
        
            System.out.println("Nab Home Error From Error Prone Request Normal " + ex.getMessage() + " => " + payloadData);
        
                try {

                    this.proxyVar = setupProxy();
                    
                    restTemplate = this.proxyVar;
                    
                    HttpEntity entity = new HttpEntity(formData, headersData);
                        
                    ResponseEntity<String> response = restTemplate.exchange(Url, method, entity, String.class);

                    result = response.getBody();
                    
                } catch (Exception ex1) {
                    System.out.println("Nab Home Error From Error Prone Request Abnormal " + ex1.getMessage() + " => " + payloadData);
                }    
        }
        
        return (result != null ? result : null);
    }
    
    private ArrayList<String> getCityNameAndId(String cityName){
                
        HttpHeaders headersData = new HttpHeaders();
        headersData.add("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.116 Safari/537.36");
        
        ArrayList<String> cityIds = new ArrayList<>();
            
        try{
                
                this.proxyVar = setupProxy();
                
                String response = errorProneWebRequests("https://www.nabhome.ch/index.php?eID=nabhomeis24_autocomplete&l=" + cityName + "&r=0&contentType=application%2Fx-www-form-urlencoded%3B%20charset%3DUTF-8", HttpMethod.GET, null, headersData, null);
                
                if(response.isEmpty() == false && response != null){
                                                            
                    JSONArray jaObj = new JSONArray(response);
                    
                    for(int i=0; i < jaObj.length(); i++){
                        JSONObject jsonobject = jaObj.getJSONObject(i);
                        if(jsonobject.getString("Label").toLowerCase().contains(cityName)){
                            if(jsonobject.has("Id")){
                                cityIds.add(jsonobject.getString("Id"));
                            }
                        }  
                    }
                }
                
        }
        catch(JSONException e){
            
            System.out.println("Nab Home City List JSON Parsing Error Exception : " + e.getMessage());
        
        }
        catch(Exception e){
        
            System.out.println("Nab Home City List Requesting Error Exception : " + e.getMessage());
        
        }
        
        return cityIds;
    }
}
