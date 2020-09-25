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
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author Smr
 */
public class NewHome {
    
    private RestTemplate proxyVar;
    
    public ArrayList<PropertyListJSON> GetTotalResultsCount(PropertySvcI pSvc, String rec_manger_id, int regionId, String searchIn, String cityName, String regionName, String searchCat, ArrayList<String> vendortype){
        
        ArrayList<PropertyListJSON> props = new ArrayList<>();

        Map<String,String> cityNameAndId = getCityNameAndId(cityName.toLowerCase());
        
        if(cityNameAndId.size() > 0){
        
            String totalPostsCount = countProperties(cityNameAndId.get("cityId"), (searchIn.toLowerCase().contains("buy") ? "1" : "2"));
            
            ArrayList<PropertyUrls> Urls = new ArrayList<>();
            String url = cityNameAndId.get("cityId");
        
            //try{

                double totalRecords = Double.parseDouble((totalPostsCount.isEmpty() ? "0" : totalPostsCount));

                if(totalRecords > 0){

                    double totalPages = Math.ceil(totalRecords / 20);

                    int tp = (int)totalPages;
                    
                    if(tp > 1){

                        for(int i = 0; i < tp; i++){
                            Urls.addAll(getAllLinks(pSvc, (searchIn.toLowerCase().contains("buy") ? "1" : "2"), cityNameAndId.get("cityName"), url, i, vendortype));
                        }

                    }else{
                        Urls.addAll(getAllLinks(pSvc, (searchIn.toLowerCase().contains("buy") ? "1" : "2"), cityNameAndId.get("cityName"), url, 0, vendortype));
                    }

                }
//            }catch(NumberFormatException e){
//                System.out.println("new Home Entering in Main Method Exception " + e.getMessage());
//            }catch(Exception e){
//                System.out.println("new Home Entering in Main Method Exception " + e.getMessage());
//            }

            if(Urls.size() > 0){
                props = GetPropertiesRecord(pSvc, searchIn, rec_manger_id, regionId, regionName, Urls, vendortype);
            }
        }
        
        return props;
    }
    
    private ArrayList<PropertyUrls> getAllLinks(PropertySvcI pSvc, String searchType, String locationName, String locationId, int pageNumber, ArrayList<String> vendortype) {
        ArrayList<PropertyUrls> Urls = new ArrayList<>();
        
        HttpHeaders headersData = new HttpHeaders();
        headersData.add("Accept","*/*");
        headersData.add("Accept-Encoding","gzip, deflate, br");
        headersData.add("Accept-Language","en-US,en;q=0.9");
        headersData.add("Referer","https://www.newhome.ch/en/home");
        headersData.add("sec-fetch-dest","empty");
        headersData.add("sec-fetch-mode","cors");
        headersData.add("sec-fetch-site","same-origin");
        headersData.add("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.132 Safari/537.36");
        headersData.add("x-requested-with","XMLHttpRequest");
        
        System.out.println("into the getting all links ");
        
        String Url = "https://service.newhome.ch/api/json/reply/SearchListingRequest?offerType=" + searchType + "&location=" + locationId +  "&rowCount=20&skipCount=" + (pageNumber > 0 ? (pageNumber*20) : "0") + "&order=0";
        
        try{
            
            String response = errorProneWebRequests(Url, HttpMethod.GET, headersData, null);

                if(response != null || response.isEmpty() == false){
                    
                    JSONObject jObj = new JSONObject(response);

                    if(jObj.has("entries")){

                        JSONArray jsonarray = jObj.getJSONArray("entries");

                        for(int i=0; i < jsonarray.length(); i++){
                            JSONObject jsonobject = jsonarray.getJSONObject(i);
                            String url = jsonobject.has("immocode") ? jsonobject.getBigInteger("immocode").toString() : "" ;

                            if((pSvc.getPostbyIdAndWebSite(url, "newhome.ch") <= 0) && (Urls.stream().filter(a -> a.purls.equals(url)).collect(Collectors.toList()).size() <= 0) && url.isEmpty() == false && url.length() > 0 && GetVendorTypeVerified(vendortype, jsonobject.has("contactCompanyName"))){
                                Urls.add(new PropertyUrls(url,locationName, jsonobject.has("propertyType") ? getCategoryName(Integer.toString(jsonobject.getInt("propertyType"))) : "", jsonobject.has("propertySubType") ? getPropertyTypeName(Integer.toString(jsonobject.getInt("propertySubType"))) : ""));
                            }

                        }

                    }

                }
                
        }catch(NumberFormatException e){
            System.out.println("new Home Entering in get All Links Method Number Format Exception Exception " + e.getMessage());
        }catch(JSONException e){
            System.out.println("new Home Entering in get All Links Method JSON Exception " + e.getMessage());
        }catch(Exception e){
            System.out.println("new Home Entering in get All Links Method General Exception " + e.getMessage());
        }


        System.out.println(Url);
        System.out.println(locationName + " => : " + Urls.size());
            
        return Urls;
    }
    
    public boolean GetVendorTypeVerified(ArrayList<String> vendorType, boolean conditon){
        boolean str = false;
        
        if((vendorType.stream().filter(a -> a.equals("Unassigned vendor")).collect(Collectors.toList()).size() > 0 || vendorType.stream().filter(a -> a.equals("Private vendor")).collect(Collectors.toList()).size() > 0) && (vendorType.stream().filter(a -> a.equals("Institutional vendor")).collect(Collectors.toList()).isEmpty()) && conditon == false){
            str = true;
        }else if((vendorType.stream().filter(a -> a.equals("Unassigned vendor")).collect(Collectors.toList()).isEmpty() && vendorType.stream().filter(a -> a.equals("Private vendor")).collect(Collectors.toList()).isEmpty()) && vendorType.stream().filter(a -> a.equals("Institutional vendor")).collect(Collectors.toList()).size() > 0 && conditon){
            str = true;
        }else if((vendorType.stream().filter(a -> a.equals("Unassigned vendor")).collect(Collectors.toList()).size() > 0 || vendorType.stream().filter(a -> a.equals("Private vendor")).collect(Collectors.toList()).size() > 0) && (vendorType.stream().filter(a -> a.equals("Institutional vendor")).collect(Collectors.toList()).size() > 0) && (conditon == false || conditon)){
            str = true;
        }else if(vendorType.size() <= 0) {
            str = true;
        }
        
        return str;
    }
    
    private ArrayList<PropertyListJSON> GetPropertiesRecord(PropertySvcI pSvc, String ST, String rec_manger_id, int regionId, String regionName, ArrayList<PropertyUrls> urls, ArrayList<String> vendortype){
        ArrayList<PropertyListJSON> properties = new ArrayList<>();
        String Url = "";
        
        HttpHeaders headersData = new HttpHeaders();
        headersData.add("Accept","*/*");
        headersData.add("Accept-Encoding","gzip, deflate, br");
        headersData.add("Accept-Language","en-US,en;q=0.9");
        headersData.add("Referer","https://www.newhome.ch/en/home");
        headersData.add("sec-fetch-dest","empty");
        headersData.add("sec-fetch-mode","cors");
        headersData.add("sec-fetch-site","same-origin");
        headersData.add("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.132 Safari/537.36");
        headersData.add("x-requested-with","XMLHttpRequest");
        
        for(PropertyUrls UrlId:urls){
                        
            try{
                Url = "https://service.newhome.ch/api/json/reply/ListingDetailRequest?languageIso=en&immocode=" + UrlId.getPurls();
                String response = errorProneWebRequests(Url, HttpMethod.GET, headersData, null);
                
                if(response != null && response.isEmpty() == false){
                                    
                    JSONObject jObj = new JSONObject(response);
                    
                    jObj = jObj.getJSONObject("detail");

                    String V_id = jObj.has("contactProviderId") ? jObj.getBigInteger("contactProviderId").toString() : "";

                    String agencyName = jObj.has("contactCompany") ? jObj.getString("contactCompany") : "";

                    agencyName = GetVendorTypeVerified(vendortype, agencyName);
                    
                    String publishDate = "";

                    if((pSvc.getVendorbyIdAndWebSite(V_id, "newhome.ch") <= 0)){

                        String featuresOfProperty = "";
                        
                        String updateDate = "";

                        String searchType = ST.toLowerCase().contains("buy") ? "SALE" : "RENT";

                        String msRegion = regionName;
                        
                        String catOfProperty = UrlId.getPropertyCategory();

                        String propertyType = UrlId.getPropertyType();

                        String titleOfAdd = jObj.has("title") ? jObj.getString("title") : "";

                        String streetNumber = jObj.has("street") ? jObj.getString("street") : "";

                        String postalCode = jObj.has("postalCode") ? jObj.getString("postalCode") : "";

                        String cIty = jObj.has("city") ? jObj.getString("city") : "";

                        String sellPrice = "";
                        
                        JSONObject priceObj = jObj.has("price") ? jObj.getJSONObject("price") : new JSONObject();

                        if(searchType.toLowerCase().contains("sale")){
                            sellPrice = priceObj.has("price") ? Double.toString(priceObj.getDouble("price")) : "0";
                        }

                        String sellGrossPrice = "";
                        String sellNetPrice = "";
                        String sellPaymentType = searchType;

                        int rentPrice = 0;
                        int rentGrossPrice = 0;
                        int rentNetPrice = 0;

                        if(searchType.toLowerCase().contains("rent")){
                            
                            double priceData = priceObj.has("price") ? priceObj.getDouble("price") : 0;

                            rentPrice = (int)priceData;

                            priceData = priceObj.has("priceNet") ? priceObj.getDouble("priceNet") : 0;

                            rentGrossPrice = rentPrice;
                            rentNetPrice = (int)priceData;
                            
                        }

                        String pId = jObj.has("immocode") ? jObj.getBigInteger("immocode").toString() : "";

                        String rentPaymentType = searchType;
                        
                        JSONObject subDetailsObj = jObj.has("detail") ? jObj.getJSONObject("detail") : new JSONObject();
                        
                        String numberOfRooms = subDetailsObj.has("rooms") ? Double.toString(subDetailsObj.getDouble("rooms")) : "";

                        String livingSpace = subDetailsObj.has("livingArea") ? Double.toString(subDetailsObj.getDouble("livingArea")) : "";
                        String propertyFloor = subDetailsObj.has("floorsInBuildung") ? Integer.toString(subDetailsObj.getInt("floorsInBuildung")) : "";
                        String propertyArea = "";
                        String propertyDesc = jObj.has("descriptionTextPlain") ? jObj.getString("descriptionTextPlain") : "";
                        String propertyAvailable = "";
                        String yearBuilt = subDetailsObj.has("constructionYear") ? Integer.toString(subDetailsObj.getInt("constructionYear")) : "";
                        
                        String nameOfSeller = jObj.has("contactFirstname") ? jObj.getString("contactFirstname") : "";
                        nameOfSeller =  nameOfSeller + (jObj.has("contactLastname") ? jObj.getString("contactLastname") : "");
                        
                        String streetNumberOfSeller = jObj.has("contactStreet") ? jObj.getString("contactStreet") : "";
                        String postalCodeOfSeller = jObj.has("contactPostalCode") ? jObj.getString("contactPostalCode") : "";
                        String cityOfSeller = jObj.has("contactCity") ? jObj.getString("contactCity") : "";
                        
                        String phoneOfSeller = jObj.has("contactPhoneOffice") ? jObj.getString("contactPhoneOffice") : "";
                        String mNumberOfSeller = jObj.has("contactPhoneMobile") ? jObj.getString("contactPhoneMobile") : "";
                        
                        String emailOfSeller = ""; 
                        String contactName = jObj.has("contactVisitName") ? jObj.getString("contactVisitName") : "";
                        String contactNumber = jObj.has("contactVisitPhone") ? jObj.getString("contactVisitPhone") : "";
                        
                        contactNumber = contactNumber.isEmpty() ? jObj.has("contactPhonePrivate") ? jObj.getString("contactPhonePrivate") : "" : contactNumber;
                        
                        String propertyTypeForUrl = jObj.has("propertyType") ? Integer.toString(jObj.getInt("propertyType")) : "";
                        
                        String urlOfAd = "https://www.newhome.ch/de/" + (ST.toLowerCase().contains("buy") ? "kaufen" : "mieten") + "/immobilien/" + getCategoryNameForUrl(propertyTypeForUrl) + "/ort-" + UrlId.getmRegion() + "/detail/" + pId;
                        
                        ArrayList<ImageUrls> iu = new ArrayList<>();

                        JSONArray allImgUrls = jObj.has("images") ? jObj.getJSONArray("images") : new JSONArray();
                        
                            for(int i=0; i < allImgUrls.length(); i++){
                                JSONObject jsonobject = allImgUrls.getJSONObject(i);
                                
                                if(jsonobject.has("imageFormats")){
                                    JSONArray imageFormatsURL = jsonobject.getJSONArray("imageFormats");
                                    
                                    if(imageFormatsURL != null && imageFormatsURL.length() > 0){
                                        jsonobject = imageFormatsURL.getJSONObject(0);
                                        String url = jsonobject.has("url") ? jsonobject.getString("url") : "";
                                        
                                        if(url.isEmpty() == false && url != null){
                                            iu.add(new ImageUrls("",url));
                                        }
                                        
                                    }
                                    
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

                            Property p = new Property(0 , rec_manger_id, regionId, pId, V_id , agencyName.isEmpty() || agencyName == null ? "Private vendor / Unassigned vendor" : "Institutional vendor" , publishDate, updateDate, searchType, msRegion, catOfProperty, propertyType, titleOfAdd, streetNumber, postalCode, cIty, sellPrice, sellGrossPrice, sellNetPrice, sellPaymentType, Integer.toString(rentPrice), Integer.toString(rentGrossPrice), Integer.toString(rentNetPrice), rentPaymentType, numberOfRooms, livingSpace, propertyFloor, propertyArea, propertyDesc, featuresOfProperty, propertyAvailable, yearBuilt, nameOfSeller, agencyName, streetNumberOfSeller, postalCodeOfSeller, cityOfSeller, phoneOfSeller, mNumberOfSeller, emailOfSeller, contactName, contactNumber, "newhome.ch", urlOfAd, ct.format(dNow), cd.format(dNow), cm.format(dNow), cy.format(dNow));
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

                            Property p = new Property(0 , rec_manger_id, regionId, pId, V_id, agencyName.isEmpty() || agencyName == null ? "Private vendor / Unassigned vendor" : "Institutional vendor", publishDate, updateDate, searchType, msRegion, catOfProperty, propertyType, titleOfAdd, streetNumber, postalCode, cIty, sellPrice, sellGrossPrice, sellNetPrice, sellPaymentType, Integer.toString(rentPrice), Integer.toString(rentGrossPrice), Integer.toString(rentNetPrice), rentPaymentType, numberOfRooms, livingSpace, propertyFloor, propertyArea, propertyDesc, featuresOfProperty, propertyAvailable, yearBuilt, nameOfSeller, agencyName, streetNumberOfSeller, postalCodeOfSeller, cityOfSeller, phoneOfSeller, mNumberOfSeller, emailOfSeller, contactName, contactNumber, "newhome.ch", urlOfAd, ct.format(dNow), cd.format(dNow), cm.format(dNow), cy.format(dNow));
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

                            Property p = new Property(0 , rec_manger_id, regionId, pId, V_id , agencyName.isEmpty() || agencyName == null ? "Private vendor / Unassigned vendor" : "Institutional vendor" , publishDate, updateDate, searchType, msRegion, catOfProperty, propertyType, titleOfAdd, streetNumber, postalCode, cIty, sellPrice, sellGrossPrice, sellNetPrice, sellPaymentType, Integer.toString(rentPrice), Integer.toString(rentGrossPrice), Integer.toString(rentNetPrice), rentPaymentType, numberOfRooms, livingSpace, propertyFloor, propertyArea, propertyDesc, featuresOfProperty, propertyAvailable, yearBuilt, nameOfSeller, agencyName, streetNumberOfSeller, postalCodeOfSeller, cityOfSeller, phoneOfSeller, mNumberOfSeller, emailOfSeller, contactName, contactNumber, "newhome.ch", urlOfAd, ct.format(dNow), cd.format(dNow), cm.format(dNow), cy.format(dNow));
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
                System.out.println("New Home Single Post Page JSON Parsing Error : " + ex.getMessage() + " => " + Url);
            }
            catch (Exception ex) {
                System.out.println("New Home Single Post Page Site Request Error : " + ex.getMessage() + " => " + Url);
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
        }else{
            str = data;
        }
        
        return str;
    }
    
    private RestTemplate setupProxy() throws Exception {
        final String username = "mtyj2dhq";
        final String password = "KIXd0iNSLjCo6GpC";
        final String proxyUrl = "proxy.proxy-cheap.com";
        final int port = 31112;

        CredentialsProvider credsProvider = new BasicCredentialsProvider();
        credsProvider.setCredentials( 
            new AuthScope(proxyUrl, port), 
            new UsernamePasswordCredentials(username, password)
        );

        HttpHost myProxy = new HttpHost(proxyUrl, port);
        HttpClientBuilder clientBuilder = HttpClientBuilder.create();

        clientBuilder.setProxy(myProxy).setDefaultCredentialsProvider(credsProvider).disableCookieManagement();

        HttpClient httpClient = clientBuilder.build();
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
        factory.setHttpClient(httpClient);

        return new RestTemplate(factory);
//        return new RestTemplate();
    }
    
    public String errorProneWebRequests(String Url, HttpMethod method, HttpHeaders headersData, String payloadData){
               
        String result = null;
        
        RestTemplate restTemplate = null;
        
        try {
                        
            restTemplate = setupProxy();
            
            HttpEntity entity = new HttpEntity(headersData);
                        
            ResponseEntity<String> response = restTemplate.exchange(Url, method, entity, String.class);
            
            result = response.getBody();
                        
        } catch (RestClientException ex) {
        
            System.out.println("Error From Error Prone Request Normal " + ex.getMessage() + " " + Url);
            
                try {

                    restTemplate = setupProxy();
                    
                    HttpEntity entity = new HttpEntity(headersData);
                        
                    ResponseEntity<String> response = restTemplate.exchange(Url, method, entity, String.class);

                    result = response.getBody();
                    
                } catch (Exception ex1) {
                    System.out.println("Error From Error Prone Request Abnormal " + ex1.getMessage() + " " + Url);
                }
            
        }
        catch (Exception ex) {
        
            System.out.println("Error From Error Prone Request Normal " + ex.getMessage() + " " + Url);
            
                try {

                    restTemplate = setupProxy();
                    
                    HttpEntity entity = new HttpEntity(headersData);
                        
                    ResponseEntity<String> response = restTemplate.exchange(Url, method, entity, String.class);

                    result = response.getBody();
                    
                } catch (Exception ex1) {
                    System.out.println("Error From Error Prone Request Abnormal " + ex1.getMessage() + " " + Url);
                }
            
        }
        
        return (result != null ? result : null);
    }
    
    public Map<String,String> getCityNameAndId(String cityName){
        
        HttpHeaders headersData = new HttpHeaders();
        headersData.add("Accept","*/*");
        headersData.add("Accept-Encoding","gzip, deflate, br");
        headersData.add("Accept-Language","en-US,en;q=0.9");
        headersData.add("Referer","https://www.newhome.ch/en/home");
        headersData.add("sec-fetch-dest","empty");
        headersData.add("sec-fetch-mode","cors");
        headersData.add("sec-fetch-site","same-origin");
        headersData.add("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.132 Safari/537.36");
        headersData.add("x-requested-with","XMLHttpRequest");
        
        String url = "https://service.newhome.ch/api/json/reply/SearchLocationRequest?keyword=" + cityName + "&languageIso=en";
                
        Map<String,String> str = new HashMap<>();
        
        try{
            
            String response = errorProneWebRequests(url, HttpMethod.GET, headersData, null);
            
            JSONObject jObj = new JSONObject(response);
            
            if(jObj.has("locations")){
            
                JSONArray jsonarray = jObj.getJSONArray("locations");

                for(int i=0; i < jsonarray.length(); i++){
                    JSONObject jsonobject = jsonarray.getJSONObject(i);
                    if(jsonobject.getString("displayName").toLowerCase().equals(cityName) && Integer.toString(jsonobject.getInt("locationType")).equals("1")){
                        str.put("cityId",jsonobject.getString("identifier"));
                        str.put("cityName",cityName);
                        break;
                    }  
                }

                if(str.isEmpty() && jsonarray.length() == 1){
                    JSONObject jsonobject = jsonarray.getJSONObject(0);
                    if(Integer.toString(jsonobject.getInt("locationType")).toLowerCase().equals("1")){
                        str.put("cityId",jsonobject.getString("identifier"));
                        str.put("cityName",jsonobject.getString("displayName"));
                    }
                }
                
            }
        
        }catch (JSONException e) {
            System.out.println("New Home Json Parsing Error While getting id's of Cities : " + e.getMessage());
        }catch(Exception e){
            System.out.println("New Home Exception Error While getting id's of Cities : " + e.getMessage());
        }
        
        return str;
    }
    
    public String countProperties(String locationId, String searchType){
        
        HttpHeaders headersData = new HttpHeaders();
        headersData.add("Accept","*/*");
        headersData.add("Accept-Encoding","gzip, deflate, br");
        headersData.add("Accept-Language","en-US,en;q=0.9");
        headersData.add("Referer","https://www.newhome.ch/en/home");
        headersData.add("sec-fetch-dest","empty");
        headersData.add("sec-fetch-mode","cors");
        headersData.add("sec-fetch-site","same-origin");
        headersData.add("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.132 Safari/537.36");
        headersData.add("x-requested-with","XMLHttpRequest");
        
        String str = "";
        
        String url = "https://service.newhome.ch/api/json/reply/GetAdvertSearchCountRequest?offerType=" + searchType + "&location=" + locationId + "&rowCount=20&skipCount=0";
      
        try{
            
            String response = errorProneWebRequests(url, HttpMethod.GET, headersData, null);
                        
            JSONObject jObj = new JSONObject(response);
            
            str = jObj.has("totalResultCount") ? Integer.toString(jObj.getInt("totalResultCount")) : "";
            
            System.out.println(" Properties Count : " + str);
        
        }catch(Exception e){
            System.out.println("New Home Error While getting Properties Count : " + e.getMessage());
        }

        return str;
    }
    
    public String getCategoryName(String categoryNumber){
        
        Map<String,String> categoriesData = new HashMap<>();
        
        categoriesData.put("100","Apartment or house");
        categoriesData.put("1","House");
        categoriesData.put("2","Apartment");
        categoriesData.put("3","Apartment building");
        categoriesData.put("4","Commercial/office");
        categoriesData.put("5","Building land/plot");
        categoriesData.put("6","Parking space");
        categoriesData.put("200","Business Apartments (Sponsored Content)");
                        
        return categoriesData.get(categoryNumber) != null ? categoriesData.get(categoryNumber) : "Apartment or house";
    }
    
    public String getCategoryNameForUrl(String categoryNumber){
        
        Map<String,String> categoriesData = new HashMap<>();
        
        categoriesData.put("100","haus-wohnung");
        categoriesData.put("1","haus");
        categoriesData.put("2","wohnung");
        categoriesData.put("3","mehrfamilienhaus");
        categoriesData.put("4","gewerbe");
        categoriesData.put("5","bauland");
        categoriesData.put("6","parkplatz");
        categoriesData.put("200","business-apartment");
                        
        return categoriesData.get(categoryNumber) != null ? categoriesData.get(categoryNumber) : "";
    }
    
    public String getPropertyTypeName(String propertyTypeNumber){
        
        Map<String,String> typesData = new HashMap<>();
        
        typesData.put("111","Chalet");
        typesData.put("112","Château");
        typesData.put("103","Corner building");
        typesData.put("101","Detached house");
        typesData.put("102","Double apartment building");
        typesData.put("107","Farmhouse");
        typesData.put("108","Holiday home");
        typesData.put("104","Middle house");
        typesData.put("110","Rustic");
        typesData.put("105","Stepped building");
        typesData.put("109","Terraced house");
        typesData.put("106","Villa");
        
        typesData.put("201","Apartment");
        typesData.put("209","Apartment in a new building");
        typesData.put("210","Apartment in an old building");
        typesData.put("202","Attic apartment");
        typesData.put("211","Furnished apartment");
        typesData.put("207","Holiday apartment");
        typesData.put("208","Loft");
        typesData.put("204","Maisonette");
        typesData.put("203","Penthouse apartment");
        typesData.put("206","Studio/bedsit");
        typesData.put("205","Terrace apartment");
        
        typesData.put("403","Agricultural building");
        typesData.put("408","Commercial building");
        typesData.put("409","Commercial premises");
        typesData.put("410","Hotel");
        typesData.put("407","Manufacturing rooms");
        typesData.put("404","Medical practice");
        typesData.put("402","Office");
        typesData.put("401","Residential and commercial building");
        typesData.put("411","Restaurant");
        typesData.put("406","Salesrooms");
        typesData.put("413","Studio");
        typesData.put("405","Warehouse");
        typesData.put("412","Workshop / hobby room");
        
        typesData.put("501","Building land");
        typesData.put("503","Building land mixed construction zone");
        typesData.put("505","Commercial building land");
        typesData.put("504","Farmland");
        typesData.put("502","Industrial building land");
        
        typesData.put("603","Covered parking space");
        typesData.put("602","Garage");
        typesData.put("605","Motorcycle parking space");
        typesData.put("604","Uncovered parking space");
        typesData.put("601","Underground garage");
                        
        return typesData.get(propertyTypeNumber) != null ? typesData.get(propertyTypeNumber) : "";
    }
}
