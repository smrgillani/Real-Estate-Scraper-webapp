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
 * @author Lubbow
 */
public class ImmoScout {

    private RestTemplate proxyVar;
    
    public ArrayList<PropertyListJSON> GetTotalResultsCount(PropertySvcI pSvc, String rec_manger_id, int regionId, String searchIn, String cityName, String regionName, String searchCat, ArrayList<String> vendortype, String adPublishFrom, String adPublishTo){
        
        ArrayList<PropertyListJSON> props = new ArrayList<>();
        ArrayList<PropertyUrls> Urls = new ArrayList<>();
        
        try{
            
            this.proxyVar = setupProxy();
            
            String getcityId = checkIfCityExist(cityName);
            
            if(getcityId.isEmpty() == false && getcityId != null){
                
                if(searchIn.toLowerCase().equals("rent")){
                    searchIn = "1";
                }else if(searchIn.toLowerCase().equals("buy")){
                    searchIn = "2";
                }

                String getTotalRecordsNum = GetTotalRecordsNum(getcityId, searchIn, searchCat);

                if(getTotalRecordsNum.isEmpty() == false && getTotalRecordsNum != null){
                    int total_number_of_rec = Integer.valueOf(getTotalRecordsNum);
                    if(total_number_of_rec > 0){
                        if(total_number_of_rec > 24){

                            double totalPosts = Double.valueOf(total_number_of_rec);
                            double totalPages = Math.ceil(totalPosts / 24);
                            int tp = (int)totalPages;

                            for(int i = 1; i <= tp; i++){
                                Urls.addAll(getAllLinks(Integer.toString(i), "24", searchIn, getcityId, regionName, searchCat));
                            }

                        }else{
                            Urls.addAll(getAllLinks("1", "24", searchIn, getcityId, regionName, searchCat));
                        }
                    }
                }

                if(Urls.size() > 0){
                    props = GetPropertiesRecord(pSvc, rec_manger_id, regionId, Urls, searchIn, regionName, searchCat, vendortype, adPublishFrom, adPublishTo);
                }
            
            }
        
        }catch(Exception e){
            System.out.println("ImmoScout Proxy Setup Initializing Error : " + e.getMessage());
        }
       
        return props;
        
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
    }
    
    public String errorProneWebRequests(String Url, HttpMethod method, HttpHeaders headersData, String payloadData){
               
        String result = null;
        
        RestTemplate restTemplate = null;
        
        try {
                        
            restTemplate = this.proxyVar;
            
            HttpEntity entity = new HttpEntity(headersData);
                        
            ResponseEntity<String> response = restTemplate.exchange(Url, method, entity, String.class);
            
            result = response.getBody();
                        
        } catch (RestClientException ex) {
        
            System.out.println("Error From Error Prone Request Normal " + ex.getMessage());
            
            if(ex.getMessage().contains("403") && ex.getMessage().contains("Forbidden") ){
                
                try {

                    this.proxyVar = setupProxy();
                    
                    restTemplate = this.proxyVar;
                    
                    HttpEntity entity = new HttpEntity(headersData);
                        
                    ResponseEntity<String> response = restTemplate.exchange(Url, method, entity, String.class);

                    result = response.getBody();
                    
                } catch (Exception ex1) {
                    System.out.println("Error From Error Prone Request Abnormal " + ex.getMessage());
                }
                
            }
            
        }
        
        return (result != null ? result : null);
    }
    
    private ArrayList<PropertyUrls> getAllLinks(String pageNumber, String totalRecsPp, String searchIn, String cityId, String regionName, String searchCat){
        ArrayList<PropertyUrls> Urls = new ArrayList<>();
        
        HttpHeaders Pmap = new HttpHeaders();
        Pmap.add("Host", "rest-api.immoscout24.ch");
        Pmap.add("Connection", "keep-alive");
        Pmap.add("Accept", "text/plain, application/json, text/json");
        Pmap.add("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/81.0.4044.138 Safari/537.36");
        Pmap.add("is24-meta-pagenumber", pageNumber);
        Pmap.add("is24-meta-pagesize", totalRecsPp);
        Pmap.add("Origin", "https://www.immoscout24.ch");
        Pmap.add("Sec-Fetch-Site", "same-site");
        Pmap.add("Sec-Fetch-Mode", "cors");
        Pmap.add("Sec-Fetch-Dest", "empty");
        Pmap.add("Referer", "https://www.immoscout24.ch/en");
        Pmap.add("Accept-Encoding", "gzip, deflate, br");
        Pmap.add("Accept-Language", "en-US,en;q=0.9");
        
        String _Url = "https://rest-api.immoscout24.ch/v4/en/properties?l=" + cityId + "&s=" + searchCat + "&t=" + searchIn;
                    
            try {
                //Document doc;
                //doc = Jsoup.connect(_Url).maxBodySize(0).timeout(1000000).headers(Pmap).ignoreContentType(true).get();
                String _response = errorProneWebRequests(_Url, HttpMethod.GET, Pmap, null);
                //String _response = doc.text();
                            
                if(_response != null || _response.isEmpty() == false){
                                
                    JSONObject ob = new JSONObject(_response);
                        if(ob.has("pagingInfo")){
                            JSONObject subObj = ob.getJSONObject("pagingInfo");
                            if(subObj.has("pageSize")){
                                if(subObj.getInt("pageSize") > 0){
                                    JSONArray jsonarray = ob.getJSONArray("properties");
                                        if(jsonarray != null && jsonarray.length() > 0){
                                            for(int i=0; i < jsonarray.length(); i++){
                                                JSONObject jsonobject = jsonarray.getJSONObject(i);
                                                Urls.add(new PropertyUrls(jsonobject.getBigInteger("id").toString(), regionName, getCategoryName(searchCat), ""));
                                            }
                                        }
                                }
                            }
                        }
                }
            } catch (JSONException e) {
                System.out.println("ImmoScout Json Parsing Error : " + e.getMessage());
            }catch (Exception ex) {
                System.out.println("ImmoScout Main Posts Page Site Request Error : " + ex.getMessage());
            }
                    
            System.out.println(_Url);
            System.out.println(cityId + " => : " + Urls.size());
        return Urls;
    }
    
    public String checkIfCityExist(String CityName){
        String str = "";
        
        HttpHeaders Pmap = new HttpHeaders();
        Pmap.add("Host", "rest-api.immoscout24.ch");
        Pmap.add("Connection", "keep-alive");
        Pmap.add("Accept", "text/plain, application/json, text/json");
        Pmap.add("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/81.0.4044.138 Safari/537.36");
        Pmap.add("X-OriginalUrl", "http%3A%2F%2Fwww.immoscout24.ch%2Fen");
        Pmap.add("Origin", "https://www.immoscout24.ch");
        Pmap.add("Sec-Fetch-Site", "same-site");
        Pmap.add("Sec-Fetch-Mode", "cors");
        Pmap.add("Sec-Fetch-Dest", "empty");
        Pmap.add("Referer", "https://www.immoscout24.ch/en");
        Pmap.add("Accept-Encoding", "gzip, deflate, br");
        Pmap.add("Accept-Language", "en-US,en;q=0.9");
        String _Url = "";
        try {
            _Url = "https://rest-api.immoscout24.ch/v4/en/locations?term="+CityName.toLowerCase();            
            String rv = errorProneWebRequests(_Url, HttpMethod.GET, Pmap, null);
            
            try {
                    JSONArray jsonarray = new JSONArray(rv);
                    for(int i=0; i < jsonarray.length(); i++){
                        JSONObject jsonobject = jsonarray.getJSONObject(i);
                        str = jsonobject.getNumber("id").toString();
                        break;
                    }
            } catch (JSONException e) {
                   System.out.println(" ImmoScout Get City Ids Json Parsing Error : " + e.getMessage() + " >> " + _Url);
            }
            
        }catch(Exception ex){
            System.out.println(" ImmoScout Get City Ids IO Exception : " + ex.getMessage()+ " >> " + _Url);
        }
        
       return str;
    }
    
    private String GetTotalRecordsNum(String CityId ,String searchIn ,String searchCat){

        String rv = "";
        
        HttpHeaders Pmap = new HttpHeaders();
        Pmap.add("Host", "rest-api.immoscout24.ch");
        Pmap.add("Connection", "keep-alive");
        Pmap.add("Accept", "text/plain, application/json, text/json");
        Pmap.add("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/81.0.4044.138 Safari/537.36");
        Pmap.add("X-OriginalUrl", "http%3A%2F%2Fwww.immoscout24.ch%2Fen");
        Pmap.add("Origin", "https://www.immoscout24.ch");
        Pmap.add("Sec-Fetch-Site", "same-site");
        Pmap.add("Sec-Fetch-Mode", "cors");
        Pmap.add("Sec-Fetch-Dest", "empty");
        Pmap.add("Referer", "https://www.immoscout24.ch/en");
        Pmap.add("Accept-Encoding", "gzip, deflate, br");
        Pmap.add("Accept-Language", "en-US,en;q=0.9");
        String _Url = "";
        try {
            _Url = "https://rest-api.immoscout24.ch/v4/en/properties/searchmetadata?s=" + searchCat + "&t=" + searchIn + "&l=" + CityId;
            rv = errorProneWebRequests(_Url, HttpMethod.GET, Pmap, null);
            
            try {
                    JSONObject obj = new JSONObject(rv);
                    rv = obj.getNumber("totalMatches").toString();
            } catch (JSONException e) {
                System.out.println(" ImmoScout Get Total Records Number Json Parsing Exception : " + e.getMessage()+ " >> " + _Url);
            }
            
        }catch(Exception ex){
            System.out.println(" ImmoScout Get Total Records Number Exception : " + ex.getMessage()+ " >> " + _Url);
        }
        return rv;
    }

    private ArrayList<PropertyListJSON> GetPropertiesRecord(PropertySvcI pSvc, String rec_manger_id, int regionId, ArrayList<PropertyUrls> urls, String searchIn, String regionName, String searchCat, ArrayList<String> vendortype, String adPublishFrom, String adPublishTo){
        ArrayList<PropertyListJSON> properties = new ArrayList<>();
        Document doc;
        
        for(PropertyUrls UrlId:urls){
                try {
                    String _Url = "https://rest-api.immoscout24.ch/v4/de/properties/" + UrlId.getPurls();
                    String rv = errorProneWebRequests(_Url, HttpMethod.GET, null, null);
                    try {
                        JSONObject obj = new JSONObject(rv);
                                                
                        obj = obj.getJSONObject("propertyDetails");
                        
                        String V_id = (obj.has("accountId") ? Long.toString(obj.getLong("accountId")) : "");
                        JSONObject agencyObj = (obj.has("agency") ? obj.getJSONObject("agency") : null);
                        
                        String agencyName = (agencyObj != null && agencyObj.has("companyName1") ? agencyObj.getString("companyName1") : "");
                        String publishDate = (obj.has("lastPublished") ? obj.getString("lastPublished") : "");
                        
                        boolean pbc = adPublishFrom.isEmpty() || adPublishTo.isEmpty() ? true : publishDateCheck(publishDate, adPublishFrom, adPublishTo);
                        
                        if(pSvc.getVendorbyIdAndWebSite(V_id, "immoscout24.ch") <= 0 && (vendortype.size() == 0 || ( vendortype.size() > 0 && GetVendorTypeVerified(vendortype, agencyName) > 0)) && pbc){
                            String featuresOfProperty = "";
                                                        
                            publishDate = publishDate.isEmpty() == false ? convertDateFormat(publishDate) : publishDate;
                            
                            String updateDate = (obj.has("lastModified") ? obj.getString("lastModified") : "");
                            
                            updateDate = updateDate.isEmpty() == false ? convertDateFormat(updateDate) : updateDate;
                            
                            String searchType = (obj.has("offerTypeId") ?  Integer.toString(obj.getInt("offerTypeId")) : "");
                            
                            searchType = GetOfferType(searchType);
                            
                            String msRegion = UrlId.getmRegion();
                            //String catOfProperty = (obj.has("objectCategory") ? obj.getString("objectCategory") : "") ;
                            String catOfProperty = UrlId.getPropertyCategory();
                            //String propertyType = (obj.has("objectTypeLabel") ? obj.getString("objectTypeLabel") : "") ;
                            String propertyType = UrlId.getPropertyType() != null ? UrlId.getPropertyType() : (obj.has("objectTypeLabel") ? obj.getString("objectTypeLabel") : "");                            
                            String titleOfAdd = (obj.has("title") ? obj.getString("title") : "") ;
                            String streetNumber = (obj.has("street") ? obj.getString("street") : "") ;
                            String postalCode = (obj.has("zip") ? obj.getString("zip") : "") ;
                            String cIty = (obj.has("cityName") ? obj.getString("cityName") : "");
                            String sellPrice = "";

                            if(searchType.toLowerCase().contains("sale")){
                                sellPrice = (obj.has("sellingPrice") ? Integer.toString(obj.getInt("sellingPrice")) : "") ;
                            }

                            String sellGrossPrice = "";
                            String sellNetPrice = "";
                            String sellPaymentType = "";

                            int rentPrice = 0;
                            int rentGrossPrice = 0;
                            int rentNetPrice = 0;

                            if(searchType.toLowerCase().contains("rent")){
                                rentPrice = (obj.has("price") ? obj.getInt("price") : 0) ;
                                rentGrossPrice = (obj.has("price") ? obj.getInt("price") : 0);
                                rentNetPrice = (obj.has("netPrice") ? obj.getInt("netPrice") : 0) ;
                            }

                            String rentPaymentType = "";
                            String numberOfRooms = (obj.has("numberOfRooms") ? Integer.toString(obj.getInt("numberOfRooms")) : "") ;
                            String livingSpace = (obj.has("surfaceUsable") ? Integer.toString(obj.getInt("surfaceUsable")) : "");

                            JSONObject floorObj = (obj.has("attributesSize") ? obj.getJSONObject("attributesSize") : null);
                            String propertyFloor = (floorObj != null && floorObj.has("floor") ? Integer.toString(floorObj.getInt("floor")) : "");
                            String propertyArea = "";
                            String propertyDesc = (obj.has("description") ? obj.getString("description") : "");
                            String propertyAvailable = (obj.has("availableFrom") ? obj.getString("availableFrom").toString() : "");
                            
                            try{
                                if(propertyAvailable != null && propertyAvailable.isEmpty() == false){
                                    propertyAvailable = propertyAvailable.length() > 10 ? propertyAvailable.substring(0, 10): propertyAvailable;
                                    SimpleDateFormat incomingDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                                    Date _propertyAvailable_ = incomingDateFormat.parse(propertyAvailable);

                                    SimpleDateFormat newDateFormat = new SimpleDateFormat("dd-MM-yyyy");
                                    propertyAvailable = newDateFormat.format(_propertyAvailable_);
                                }
                            }catch (ParseException ex) {
                                System.out.println("ImmoScout Single Proprty Available Date Parsing Error : " + ex.getMessage());
                            }
                            
                            JSONObject yearBuiltObj = (obj.has("attributes") ? obj.getJSONObject("attributes") : null);
                            String yearBuilt = (yearBuiltObj != null && yearBuiltObj.has("yearBuilt") ? Integer.toString(yearBuiltObj.getInt("yearBuilt")) : "") ;
                            String nameOfSeller = (obj.has("visitName") ? obj.getString("visitName") : "") ;
                            //String agencyName = (obj.has("agencyName") ? obj.getString("agencyName") : "") ;
                            String streetNumberOfSeller = (agencyObj != null && agencyObj.has("agencyObj") ? agencyObj.getString("agencyObj") : "") ;
                            String postalCodeOfSeller = (agencyObj != null && agencyObj.has("companyZip") ? agencyObj.getString("companyZip") : "") ;
                            String cityOfSeller = (agencyObj != null && agencyObj.has("companyCity") ? agencyObj.getString("companyCity") : "") ;
                            String phoneOfSeller = (agencyObj != null && agencyObj.has("companyPhoneBusiness") ? agencyObj.getString("companyPhoneBusiness") : "") ;
                            String mNumberOfSeller = (agencyObj != null && agencyObj.has("companyPhoneMobile") ? agencyObj.getString("companyPhoneMobile") : "");
                            String emailOfSeller = ""; 
                            String contactName = (obj.has("visitName") ? obj.getString("visitName") : "");
                            String contactNumber = (obj.has("visitPhone") ? obj.getString("visitPhone") : "");
                            String urlOfAd = (obj.has("propertyDetailUrl") ? obj.getString("propertyDetailUrl") : "");
                            urlOfAd = "https://www.immoscout24.ch" + urlOfAd;
                            
                            ArrayList<ImageUrls> iu = new ArrayList<>();

                            if(obj.has("images")){
                                JSONArray jsonarray = obj.getJSONArray("images");
                                    if(jsonarray != null && jsonarray.length() > 0){
                                          for(int i=0; i < jsonarray.length(); i++){
                                              JSONObject jsonobject = jsonarray.getJSONObject(i);
                                              String iUrls = (jsonobject.has("url") ? jsonobject.getString("url") : "");
                                              iUrls = iUrls.replaceAll(Pattern.quote("{width}x{height}"), "1280x960");
                                              iUrls = iUrls.replaceAll(Pattern.quote("{resizemode}"), "3");
                                              iUrls = iUrls.replaceAll(Pattern.quote("{quality}"), "90");
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
                                Property p = new Property(0 , rec_manger_id, regionId, UrlId.getPurls() , V_id , agencyName.isEmpty() || agencyName == null ? "Private vendor / Unassigned vendor" : "Institutional vendor" , publishDate, updateDate, searchType, msRegion, catOfProperty, propertyType, titleOfAdd, streetNumber, postalCode, cIty, sellPrice, sellGrossPrice, sellNetPrice, sellPaymentType, Integer.toString(rentPrice), Integer.toString(rentGrossPrice), Integer.toString(rentNetPrice), rentPaymentType, numberOfRooms, livingSpace, propertyFloor, propertyArea, propertyDesc, featuresOfProperty, propertyAvailable, yearBuilt, nameOfSeller, agencyName, streetNumberOfSeller, postalCodeOfSeller, cityOfSeller, phoneOfSeller, mNumberOfSeller, emailOfSeller, contactName, contactNumber, "immoscout24.ch", urlOfAd, ct.format(dNow), cd.format(dNow), cm.format(dNow), cy.format(dNow));
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
                                Property p = new Property(0 , rec_manger_id, regionId, UrlId.getPurls(), V_id, agencyName.isEmpty() || agencyName == null ? "Private vendor / Unassigned vendor" : "Institutional vendor", publishDate, updateDate, searchType, msRegion, catOfProperty, propertyType, titleOfAdd, streetNumber, postalCode, cIty, sellPrice, sellGrossPrice, sellNetPrice, sellPaymentType, Integer.toString(rentPrice), Integer.toString(rentGrossPrice), Integer.toString(rentNetPrice), rentPaymentType, numberOfRooms, livingSpace, propertyFloor, propertyArea, propertyDesc, featuresOfProperty, propertyAvailable, yearBuilt, nameOfSeller, agencyName, streetNumberOfSeller, postalCodeOfSeller, cityOfSeller, phoneOfSeller, mNumberOfSeller, emailOfSeller, contactName, contactNumber, "immoscout24.ch", urlOfAd, ct.format(dNow), cd.format(dNow), cm.format(dNow), cy.format(dNow));
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
                                Property p = new Property(0 , rec_manger_id, regionId, UrlId.getPurls(), V_id , agencyName.isEmpty() || agencyName == null ? "Private vendor / Unassigned vendor" : "Institutional vendor" , publishDate, updateDate, searchType, msRegion, catOfProperty, propertyType, titleOfAdd, streetNumber, postalCode, cIty, sellPrice, sellGrossPrice, sellNetPrice, sellPaymentType, Integer.toString(rentPrice), Integer.toString(rentGrossPrice), Integer.toString(rentNetPrice), rentPaymentType, numberOfRooms, livingSpace, propertyFloor, propertyArea, propertyDesc, featuresOfProperty, propertyAvailable, yearBuilt, nameOfSeller, agencyName, streetNumberOfSeller, postalCodeOfSeller, cityOfSeller, phoneOfSeller, mNumberOfSeller, emailOfSeller, contactName, contactNumber, "immoscout24.ch", urlOfAd, ct.format(dNow), cd.format(dNow), cm.format(dNow), cy.format(dNow));
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
                           System.out.println("ImmoScout Single Proprty Json Parsing Error : " + e.getMessage());
                    }

                }catch(Exception ex){
                    System.out.println("ImmoScout Single Property Read Exception : " + ex.getMessage());
                }
       }
       
       return properties;
    }
    
    public Integer GetVendorTypeVerified(ArrayList<String> vendorType, String dataCondition){
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
    
    public String GetOfferType(String dataCondition){
        String str = "";
        
        switch (dataCondition) {
            case "1":
                str = "RENT";
                break;
            case "2":
                str = "SALE";
                break;
            default:
                str = "SALE";
                break;
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
    
    public String convertDateFormat(String adDate){
        String str = "";
        
        if(adDate.isEmpty() == false && adDate.length() > 0){
            
            adDate = adDate.length() > 10 ? adDate.substring(0, 10): adDate;
            
            try{
                SimpleDateFormat incomingDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date _adDate_ = incomingDateFormat.parse(adDate);

                SimpleDateFormat newDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                str = newDateFormat.format(_adDate_);

            }catch(ParseException e){
                SimpleDateFormat newDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date dNow = new Date();
                str = newDateFormat.format(dNow);
            }
        }else{
            SimpleDateFormat newDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date dNow = new Date();
            str = newDateFormat.format(dNow);
        }
        
        return str;
    }
    
    public String getCategoryName(String categoryParam){
        
        Map<String,String> categoriesData = new HashMap<>();
        
        categoriesData.put("1","Flat, House");
        categoriesData.put("2","Flat");
        categoriesData.put("3","House");
        categoriesData.put("135","New building");
        categoriesData.put("136","Furnished residential property");
        categoriesData.put("4","Plot");
        categoriesData.put("5","Parking spaces");
        categoriesData.put("9","Multi-family residential");
        categoriesData.put("7","Office, Commerce, Industry");
        categoriesData.put("10","Agriculture");
        categoriesData.put("8","Other objects");
                        
        return categoriesData.get(categoryParam) != null ? categoriesData.get(categoryParam) : "Other objects";
    }
    
    public boolean publishDateCheck(String adDate, String givenFromDate, String givenToDate){
        boolean str = false;
        
        if(adDate.isEmpty() == false && adDate.length() > 0){
            
            adDate = adDate.length() > 10 ? adDate.substring(0, 10): adDate;
        
            try{
                SimpleDateFormat adDateFormatter = new SimpleDateFormat("yyyy-MM-dd");
                Date _adDate_ = adDateFormatter.parse(adDate);

                SimpleDateFormat incomingDatesFormatter = new SimpleDateFormat("dd.MM.yyyy");
                Date _givenFromDate_ = incomingDatesFormatter.parse(givenFromDate);
                Date _givenToDate_ = incomingDatesFormatter.parse(givenToDate);

                str = _adDate_.compareTo(_givenFromDate_) >= 0 && _adDate_.compareTo(_givenToDate_) <= 0;

            }catch(ParseException e){
                str = true;
            }
            
        }else{
            str = true;
        }
        
        return str;
    }
        
}