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
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;
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
public class ImmoWelt {
    private RestTemplate proxyVar;
    
    public ArrayList<PropertyListJSON> GetTotalResultsCount(PropertySvcI pSvc, String rec_manger_id, int regionId, String searchIn, String cityName, String regionName, String searchCat, ArrayList<String> vendortype){
        
        ArrayList<PropertyListJSON> props = new ArrayList<>();

        String cityId = getCityNameAndId(cityName.toLowerCase());
        
        if(cityId.length() > 0){
        
            String totalPostsCount = countProperties(cityId, (searchIn.toLowerCase().contains("buy") ? "1" : "2"), searchCat);
            
            ArrayList<PropertyUrls> Urls = new ArrayList<>();
        
            try{

                double totalRecords = Double.parseDouble((totalPostsCount.isEmpty() ? "0" : totalPostsCount));

                if(totalRecords > 0){

                    double totalPages = Math.ceil(totalRecords / 50);

                    int tp = (int)totalPages;
                    
                    if(tp > 1){

                        for(int i = 0; i < tp; i++){
                            Urls.addAll(getAllLinks(pSvc, (searchIn.toLowerCase().contains("buy") ? "1" : "2"), searchCat, cityName, cityId, i));
                        }

                    }else{
                        Urls.addAll(getAllLinks(pSvc, (searchIn.toLowerCase().contains("buy") ? "1" : "2"), searchCat, cityName, cityId, 0));
                    }

                }
            }catch(NumberFormatException e){
                System.out.println("ImmoWelt Entering in Main Method Exception " + e.getMessage());
            }
            catch(Exception e){
                System.out.println("ImmoWelt Entering in Main Method Exception " + e.getMessage());
            }

            if(Urls.size() > 0){               
                props = GetPropertiesRecord(pSvc, searchIn, rec_manger_id, regionId, regionName, Urls, vendortype);
            }
        }
        
        return props;
    }
    
    private ArrayList<PropertyUrls> getAllLinks(PropertySvcI pSvc, String searchType, String searchCat, String locationName, String locationId, int pageNumber) {
        ArrayList<PropertyUrls> Urls = new ArrayList<>();
        String Url = "https://mobileapi.immowelt.de/estateoffers?fragments=items&regions=" + locationId + "&habitationIndustry=0&salesType=" + searchType + "&estateType=" + searchCat + "&locationAmbit=0&price=0.0:0.0&interiorArea=0.0:0.0&rooms=0.0:0.0&floor=-500.0:-500.0&constructionDate=0:0&resultOrder=createdate%20desc&offset=" + (pageNumber > 0 ? (pageNumber*50) : "0") + "&maxResults=50";
        
        HttpHeaders headersData = new HttpHeaders();
        headersData.add("Accept","application/vnd.immowelt.m.api+json; version=1.0");
        headersData.add("content-type","application/vnd.immowelt.m.api+json; version=1.0");
        headersData.add("Authorization","IWAuth 37AB70A86FEE4A1FA317C618DDEB89DC");
        headersData.add("User-Agent","Apache-HttpClient/UNAVAILABLE (java 1.4)");
        headersData.add("Host","mobileapi.immowelt.de");
        headersData.add("Connection","keep-alive");
        
            String response = errorProneWebRequests(Url, HttpMethod.GET, null, headersData, null);

                if(response != null || response.isEmpty() == false){
                    
                    JSONObject jObj = new JSONObject(response);

                    if(jObj.has("items")){

                        JSONArray jsonarray = jObj.getJSONArray("items");

                        for(int i=0; i < jsonarray.length(); i++){
                            JSONObject jsonobject = jsonarray.getJSONObject(i);
                            String url = jsonobject.has("id") ? jsonobject.getString("id").toString() : "" ;

                            if((pSvc.getPostbyIdAndWebSite(url, "immowelt.ch") <= 0) && (Urls.stream().filter(a -> a.purls.equals(url)).collect(Collectors.toList()).size() <= 0) && url.isEmpty() == false && url.length() > 0){
                                Urls.add(new PropertyUrls(url,locationName, getCategoryName(searchCat), ""));
                            }

                        }

                    }

                }

            System.out.println(Url);
            System.out.println(locationName + " => : " + Urls.size());
            
        return Urls;
    }
    
        private ArrayList<PropertyListJSON> GetPropertiesRecord(PropertySvcI pSvc, String ST, String rec_manger_id, int regionId, String regionName, ArrayList<PropertyUrls> urls, ArrayList<String> vendortype){
        ArrayList<PropertyListJSON> properties = new ArrayList<>();
        String Url = "";
        
        HttpHeaders headersData = new HttpHeaders();
        headersData.add("Accept","application/vnd.immowelt.m.api+json; version=1.0");
        headersData.add("content-type","application/vnd.immowelt.m.api+json; version=1.0");
        headersData.add("Authorization","IWAuth 37AB70A86FEE4A1FA317C618DDEB89DC");
        headersData.add("User-Agent","Apache-HttpClient/UNAVAILABLE (java 1.4)");
        headersData.add("Host","mobileapi.immowelt.de");
        headersData.add("Connection","keep-alive");
        
        for(PropertyUrls UrlId:urls){
                        
            try{
                Url = "https://mobileapi.immowelt.de/estateoffers/" + UrlId.getPurls() + "?fragments=estate,offererContact,offererContactForm,advertising";
                String response = errorProneWebRequests(Url, HttpMethod.GET, null, headersData, null);
                
                if(response != null && response.isEmpty() == false){
                
                    JSONObject jObj = new JSONObject(response);
                    
                    String V_id = jObj.has("offererId") ? jObj.getString("offererId").toString() : "";

                    JSONObject companyObj = jObj.has("offererContact") && jObj.isNull("offererContact") == false ? jObj.getJSONObject("offererContact") : new JSONObject();
                    
                    companyObj = companyObj.has("company") && companyObj.isNull("company") == false ? companyObj.getJSONObject("company") : companyObj;
                    
                    String agencyName = companyObj.has("value") ? companyObj.getString("value") : "";
                    
                    String publishDate = "";

                    if((pSvc.getVendorbyIdAndWebSite(V_id, "immowelt.ch") <= 0)){

                        String featuresOfProperty = "";
                        
                        String updateDate = "";

                        String searchType = ST.toLowerCase().contains("buy") ? "SALE" : "RENT";

                        String msRegion = regionName;
                        
                        String catOfProperty = UrlId.getPropertyCategory();

                        String propertyType = UrlId.getPropertyType();
                        
                        JSONObject sjObj = jObj.has("title") && jObj.isNull("title") == false ? jObj.getJSONObject("title") : new JSONObject();

                        String titleOfAdd = sjObj.has("value") ? sjObj.getString("value") : "";
                        
                        sjObj = jObj.has("address") && jObj.isNull("address") == false ? jObj.getJSONObject("address") : new JSONObject();

                        String streetNumber = sjObj.has("street") && sjObj.isNull("street") == false ? sjObj.getString("street") : "";

                        String postalCode = sjObj.has("zip") && sjObj.isNull("zip") == false ? sjObj.getString("zip") : "";

                        String cIty = sjObj.has("city") && sjObj.isNull("city") == false ? sjObj.getString("city") : "";

                        String sellPrice = "";
                        
                        sjObj = jObj.has("price") && jObj.isNull("price") == false ? jObj.getJSONObject("price") : new JSONObject();

                        if(searchType.toLowerCase().contains("sale")){
                            sellPrice = sjObj.has("value") ? Double.toString(sjObj.getDouble("value")) : "0";
                        }

                        String sellGrossPrice = "";
                        String sellNetPrice = "";
                        String sellPaymentType = searchType;

                        int rentPrice = 0;
                        int rentGrossPrice = 0;
                        int rentNetPrice = 0;

                        if(searchType.toLowerCase().contains("rent")){
                            
                            double priceData = sjObj.has("value") ? sjObj.getDouble("value") : 0;

                            rentPrice = (int)priceData;
                            
                            sjObj = jObj.has("priceWarm") && jObj.isNull("priceWarm") == false ? jObj.getJSONObject("priceWarm") : new JSONObject();

                            priceData = sjObj.has("value") ? sjObj.getDouble("value") : 0;

                            rentGrossPrice = rentPrice;
                            rentNetPrice = (int)priceData;
                            
                        }

                        String pId = jObj.has("id") ? jObj.getString("id").toString() : "";

                        String rentPaymentType = searchType;
                        
                        sjObj = jObj.has("rooms") && jObj.isNull("rooms") == false ? jObj.getJSONObject("rooms") : new JSONObject();
                        
                        String numberOfRooms = sjObj.has("value") ? Double.toString(sjObj.getDouble("value")) : "";

                        sjObj = jObj.has("area") && jObj.isNull("area") == false ? jObj.getJSONObject("area") : new JSONObject();
                        
                        String livingSpace = sjObj.has("value") ? Double.toString(sjObj.getDouble("value")) : "";
                        
                        sjObj = jObj.has("floor") && jObj.isNull("floor") == false ? jObj.getJSONObject("floor") : new JSONObject();
                        
                        String propertyFloor = sjObj.has("value") ? Integer.toString(sjObj.getInt("value")) : "";
                        
                        String propertyArea = "";
                        String propertyDesc = "";
                        
                        JSONArray descJsonArray = jObj.has("descriptions") ? jObj.getJSONArray("descriptions") : new JSONArray();
                        
                        for(int a=0; a < descJsonArray.length(); a++){
                            JSONObject innerJsonObject = descJsonArray.getJSONObject(a);
                            
                            propertyDesc = propertyDesc + (innerJsonObject.has("value") ? innerJsonObject.getString("value") : "");
                        }
                        
                        String propertyAvailable = "";
                        
                        sjObj = jObj.has("constructed") && jObj.isNull("constructed") == false ? jObj.getJSONObject("constructed") : new JSONObject();
                                                
                        String yearBuilt = sjObj.has("value") ? sjObj.getString("value") : "";
                        
                        yearBuilt = StringUtils.substringBefore(yearBuilt, "-");
                        
                        String nameOfSeller = "";
                        
                        companyObj = jObj.has("offererContact") ? jObj.getJSONObject("offererContact") : new JSONObject();
                        
                        sjObj = companyObj.has("street") && companyObj.isNull("street") == false ? companyObj.getJSONObject("street") : new JSONObject();
                        
                        String streetNumberOfSeller = sjObj.has("value") ? sjObj.getString("value") : "";
                        
                        sjObj = companyObj.has("zip") ? companyObj.getJSONObject("zip") : new JSONObject();
                        
                        String postalCodeOfSeller = sjObj.has("value") ? sjObj.getString("value") : "";
 
                        sjObj = companyObj.has("city") ? companyObj.getJSONObject("city") : new JSONObject();
                        
                        String cityOfSeller = sjObj.has("value") ? sjObj.getString("value") : "";
                        
                        sjObj = companyObj.has("phone") && companyObj.isNull("phone") == false ? companyObj.getJSONObject("phone") : new JSONObject();
                        
                        String phoneOfSeller = sjObj.has("value") ? sjObj.getString("value") : "";
                        
                        sjObj = companyObj.has("mobile") && companyObj.isNull("mobile") == false ? companyObj.getJSONObject("mobile") : new JSONObject();
                        
                        String mNumberOfSeller = sjObj.has("value") ? sjObj.getString("value") : "";
                        
                        sjObj = companyObj.has("email") && companyObj.isNull("email") == false ? companyObj.getJSONObject("email") : new JSONObject();
                        
                        String emailOfSeller = sjObj.has("value") ? sjObj.getString("value") : "";
                        
                        sjObj = companyObj.has("name") && companyObj.isNull("name") == false ? companyObj.getJSONObject("name") : new JSONObject();
                        
                        String contactName = sjObj.has("value") ? sjObj.getString("value") : "";
                        String contactNumber = "";
                        
                        sjObj = jObj.has("websiteUri") && jObj.isNull("websiteUri") == false ? jObj.getJSONObject("websiteUri") : new JSONObject();
                        
                        String urlOfAd = sjObj.has("value") ? sjObj.getString("value") : "";
                        
                        ArrayList<ImageUrls> iu = new ArrayList<>();

                        JSONArray allImgUrls = jObj.has("images") && jObj.isNull("images") == false ? jObj.getJSONArray("images") : new JSONArray();
                        
                            for(int i=0; i < allImgUrls.length(); i++){
                                JSONObject jsonobject = allImgUrls.getJSONObject(i);
                                if(jsonobject.has("value")){
                                    iu.add(new ImageUrls("",( jsonobject.has("value") ? jsonobject.getString("value") : "" )));
                                }
                                
                            }
                        
                        Date dNow = new Date( );
                        SimpleDateFormat ct = new SimpleDateFormat ("hh:mm a");
                        SimpleDateFormat cd = new SimpleDateFormat ("yyyy-MM-dd");
                        SimpleDateFormat cm = new SimpleDateFormat ("MMM");
                        SimpleDateFormat cy = new SimpleDateFormat ("yyyy");
                        
                        publishDate = cd.format(dNow);
                        updateDate = cd.format(dNow);

                        if(searchType.toLowerCase().contains("rent")){

                            Property p = new Property(0 , rec_manger_id, regionId, pId, V_id , agencyName.isEmpty() || agencyName == null ? "Private vendor / Unassigned vendor" : "Institutional vendor" , publishDate, updateDate, searchType, msRegion, catOfProperty, propertyType, titleOfAdd, streetNumber, postalCode, cIty, sellPrice, sellGrossPrice, sellNetPrice, sellPaymentType, Integer.toString(rentPrice), Integer.toString(rentGrossPrice), Integer.toString(rentNetPrice), rentPaymentType, numberOfRooms, livingSpace, propertyFloor, propertyArea, propertyDesc, featuresOfProperty, propertyAvailable, yearBuilt, nameOfSeller, agencyName, streetNumberOfSeller, postalCodeOfSeller, cityOfSeller, phoneOfSeller, mNumberOfSeller, emailOfSeller, contactName, contactNumber, "immowelt.ch", urlOfAd, ct.format(dNow), cd.format(dNow), cm.format(dNow), cy.format(dNow));
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

                            Property p = new Property(0 , rec_manger_id, regionId, pId, V_id, agencyName.isEmpty() || agencyName == null ? "Private vendor / Unassigned vendor" : "Institutional vendor", publishDate, updateDate, searchType, msRegion, catOfProperty, propertyType, titleOfAdd, streetNumber, postalCode, cIty, sellPrice, sellGrossPrice, sellNetPrice, sellPaymentType, Integer.toString(rentPrice), Integer.toString(rentGrossPrice), Integer.toString(rentNetPrice), rentPaymentType, numberOfRooms, livingSpace, propertyFloor, propertyArea, propertyDesc, featuresOfProperty, propertyAvailable, yearBuilt, nameOfSeller, agencyName, streetNumberOfSeller, postalCodeOfSeller, cityOfSeller, phoneOfSeller, mNumberOfSeller, emailOfSeller, contactName, contactNumber, "immowelt.ch", urlOfAd, ct.format(dNow), cd.format(dNow), cm.format(dNow), cy.format(dNow));
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

                            Property p = new Property(0 , rec_manger_id, regionId, pId, V_id , agencyName.isEmpty() || agencyName == null ? "Private vendor / Unassigned vendor" : "Institutional vendor" , publishDate, updateDate, searchType, msRegion, catOfProperty, propertyType, titleOfAdd, streetNumber, postalCode, cIty, sellPrice, sellGrossPrice, sellNetPrice, sellPaymentType, Integer.toString(rentPrice), Integer.toString(rentGrossPrice), Integer.toString(rentNetPrice), rentPaymentType, numberOfRooms, livingSpace, propertyFloor, propertyArea, propertyDesc, featuresOfProperty, propertyAvailable, yearBuilt, nameOfSeller, agencyName, streetNumberOfSeller, postalCodeOfSeller, cityOfSeller, phoneOfSeller, mNumberOfSeller, emailOfSeller, contactName, contactNumber, "immowelt.ch", urlOfAd, ct.format(dNow), cd.format(dNow), cm.format(dNow), cy.format(dNow));
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
                
            }
            catch (JSONException ex) {
                System.out.println("Immo Welt Single Post Page Site Request JSON Parsing Error : " + ex.getMessage() + " => " + Url);
            }
            catch (Exception ex) {
                System.out.println("Immo Welt Single Post Page Site Request Error : " + ex.getMessage() + " => " + Url);
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
    
    public String getCategoryName(String categoryNumber){
        
        Map<String,String> categoriesData = new HashMap<>();
        
        categoriesData.put("1","Flat");
        categoriesData.put("2","House");
        categoriesData.put("3","Property");
        categoriesData.put("8","Yield object");
        categoriesData.put("9","Other");
        categoriesData.put("13","Garage / parking space");
        categoriesData.put("15","Temporary living");
        categoriesData.put("16","Shared apartment");
        categoriesData.put("17","Type house");
        
        return categoriesData.get(categoryNumber) != null ? categoriesData.get(categoryNumber) : "Flat";
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
    
    public String errorProneWebRequests(String Url, HttpMethod method, MultiValueMap<String, String> formData, HttpHeaders headersData, String payloadData){
               
        String result = null;
        
        RestTemplate restTemplate = null;
        
        try {
                        
            restTemplate = this.proxyVar;
                        
            HttpEntity entity = new HttpEntity(formData, headersData);
            
            ResponseEntity<String> response = restTemplate.exchange(Url, method, entity, String.class);
            
            result = response.getBody();
                        
        } catch (RestClientException ex) {
        
            System.out.println("ImmoWelt Error From Error Prone Request Normal " + ex.getMessage() + " => " + Url);
        
                try {

                    this.proxyVar = setupProxy();
                    
                    restTemplate = this.proxyVar;
                    
                    HttpEntity entity = new HttpEntity(formData, headersData);
                        
                    ResponseEntity<String> response = restTemplate.exchange(Url, method, entity, String.class);

                    result = response.getBody();
                    
                } catch (Exception ex1) {
                    System.out.println("ImmoWelt Error From Error Prone Request Abnormal " + ex1.getMessage() + " => " + Url);
                }    
        }
        
        return (result != null ? result : null);
    }
    
    public String countProperties(String locationId, String searchType, String CategoryType){
        
        String str = "";
        
        HttpHeaders headersData = new HttpHeaders();
        headersData.add("Accept","application/vnd.immowelt.m.api+json; version=1.0");
        headersData.add("content-type","application/vnd.immowelt.m.api+json; version=1.0");
        headersData.add("Authorization","IWAuth 37AB70A86FEE4A1FA317C618DDEB89DC");
        headersData.add("User-Agent","Apache-HttpClient/UNAVAILABLE (java 1.4)");
        headersData.add("Host","mobileapi.immowelt.de");
        headersData.add("Connection","keep-alive");
        
        String url = "https://mobileapi.immowelt.de/estateoffers?regions=" + locationId + "&habitationIndustry=0&salesType=" + searchType + "&estateType=" + CategoryType + "&locationAmbit=0&price=0.0:0.0&interiorArea=0.0:0.0&rooms=0.0:0.0&floor=-500.0:-500.0&constructionDate=0:0&resultOrder=relevanz&offset=0&maxResults=1";
      
        try{
            
            String response = errorProneWebRequests(url, HttpMethod.GET, null, headersData, null);
                        
            JSONObject jObj = new JSONObject(response);
            
            str = jObj.has("count") ? Integer.toString(jObj.getInt("count")) : "";
        
        }catch(JSONException e){
            System.out.println("ImmoWelt Json Parsing Error While getting Properties Count : " + e.getMessage());
        }catch(Exception e){
            System.out.println("ImmoWelt Error While getting Properties Count : " + e.getMessage());
        }

        return str;
    }
    
    public String getCityNameAndId(String cityName){
        
        String url = "https://mobileapi.immowelt.de/regions/autocomplete?name=" + cityName + "&ancestor=134&maxResults=200";
        
        HttpHeaders headersData = new HttpHeaders();
        headersData.add("Accept","application/vnd.immowelt.m.api+json; version=1.0");
        headersData.add("content-type","application/vnd.immowelt.m.api+json; version=1.0");
        headersData.add("Authorization","IWAuth 37AB70A86FEE4A1FA317C618DDEB89DC");
        headersData.add("User-Agent","Apache-HttpClient/UNAVAILABLE (java 1.4)");
        headersData.add("Host","mobileapi.immowelt.de");
        headersData.add("Connection","keep-alive");
        
        String str = "";
        
        try{
            
            this.proxyVar = setupProxy();
            
            String response = errorProneWebRequests(url, HttpMethod.GET, null, headersData, null);
            
            JSONObject jObj = new JSONObject(response);            
            
            if(jObj.has("items")){
            
                JSONArray jsonarray = jObj.getJSONArray("items");

                for(int i=0; i < jsonarray.length(); i++){
                    JSONObject jsonobject = jsonarray.getJSONObject(i);
                    if(jsonobject.getString("name").toLowerCase().contains(cityName) && Integer.toString(jsonobject.getInt("type")).equals("128")){
                        str = jsonobject.has("id") ? jsonobject.getString("id") : "";
                        break;
                    }  
                }

                if(str.isEmpty() && jsonarray.length() == 1){
                    JSONObject jsonobject = jsonarray.getJSONObject(0);
                    if(Integer.toString(jsonobject.getInt("type")).toLowerCase().equals("128")){
                        str = jsonobject.has("id") ? jsonobject.getString("id") : "";
                    }
                }
                
            }
        
        }catch (JSONException e) {
            System.out.println("ImmoWelt Json Parsing Error While getting id's of City : " + e.getMessage());
        }catch(Exception e){
            System.out.println("ImmoWelt Error Exception While getting id's of City : " + e.getMessage());
        }
        
        return str;
    }
}
