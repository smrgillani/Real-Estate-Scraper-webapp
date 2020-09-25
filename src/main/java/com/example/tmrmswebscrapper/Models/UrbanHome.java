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
public class UrbanHome {
    
    private RestTemplate proxyVar;
    
    public ArrayList<PropertyListJSON> GetTotalResultsCount(PropertySvcI pSvc, String mainTypeGroup, String rec_manger_id, int regionId, String searchIn, String cityName, String regionName, String searchCat, ArrayList<String> vendortype){
                
        ArrayList<PropertyListJSON> props = new ArrayList<>();

        ArrayList<String> cityIds = getCityNameAndId(cityName.toLowerCase());
        
        if(cityIds.size() > 0){
                        
            ArrayList<PropertyUrls> Urls = new ArrayList<>();
            
            for (String el : cityIds) {
                
                Urls.addAll(getAllLinks(pSvc, mainTypeGroup, (searchIn.toLowerCase().contains("buy") ? "1" : "2"), searchCat, cityName, el));
                
            }
                        
            if(Urls.size() > 0){               
                props = GetPropertiesRecord(pSvc, searchIn, rec_manger_id, regionId, regionName, Urls, vendortype);
            }
            
        }
        
        return props;
    }
    
    private ArrayList<PropertyUrls> getAllLinks(PropertySvcI pSvc, String mainTypeGroup, String searchType, String searchCat, String locationName, String locationId) {
        ArrayList<PropertyUrls> Urls = new ArrayList<>();
        String Url = "https://www.urbanhome.ch/Search/DoSearch";
        
        HttpHeaders headersData = new HttpHeaders();
        headersData.add("content-type","application/json");
        
        String payLoad = "{\"settings\":{\"MainTypeGroup\":\"" + mainTypeGroup + "\",\"Category\":\"" + searchType + "\",\"CountryID\":\"1\",\"AdvancedSearchOpen\":\"false\",\"MailID\":\"\",\"PayType\":\"" + searchType + "\",\"Type\":\"" + searchCat + "\",\"RoomsMin\":\"0\",\"RoomsMax\":\"0\",\"PriceMin\":\"0\",\"PriceMax\":\"0\",\"Regions\":[\"" + locationId + "\"],\"SubTypes\":[\"0\"],\"SizeMin\":\"0\",\"SizeMax\":\"0\",\"Available\":\"\",\"NoAgreement\":\"false\",\"FloorRange\":\"0\",\"RentalPeriod\":\"0\",\"equipmentgroups\":[],\"Email\":\"\",\"Interval\":\"0\",\"SubscriptionType1\":\"true\",\"SubscriptionType2\":\"true\",\"SubscriptionType4\":\"true\",\"SubscriptionType8\":\"true\",\"SubscriptionType128\":\"true\",\"SubscriptionType512\":\"true\"},\"manual\":false,\"skip\":0,\"reset\":true,\"position\":0,\"iframe\":0,\"defaultTitle\":true,\"saveSettings\":true,\"code\":\":)\"}";
        
            String response = "";
            
            try{
                
                response = errorProneWebRequests(Url, HttpMethod.POST, null, headersData, payLoad);
                
                if(response != null || response.isEmpty() == false){
                                        
                    JSONObject jObj = new JSONObject(response);
                    
                    if(jObj.has("Rows") && jObj.isNull("Rows") == false){
                                                
                        String rowsData = jObj.getString("Rows");
                        
                        Document doc = Jsoup.parse(rowsData);
                        
                        Elements allUrls = doc.select("li");
                        
                        for (Element link : allUrls) {
                            
                            String url = link.select("a").attr("href");
                            
                            if(url != null && url.isEmpty() == false ){
                                                    
                                url = url.replaceAll("\"", "");
                                                    
                                url = url.replace("\\", "");
                                                    
                                String url_ = getPropertyId(url);
                                                    
                                final String _url = url;
                                                    
                                if((url.isEmpty() == false && url.length() > 0) && (pSvc.getPostbyIdAndWebSite(url_, "urbanhome.ch") <= 0) && (Urls.stream().filter(a -> a.purls.equals(_url)).collect(Collectors.toList()).size() <= 0)){
                                    Urls.add(new PropertyUrls(url, locationName, getCategoryName(searchCat), ""));
                                }
                                                    
                            }
                        
                        }
                        
                        boolean checkRowCount = jObj.has("Count");
                        
                        if(checkRowCount && jObj.getInt("Count") > 27){
                                                        
                            double totalRecords = jObj.getInt("Count");
                            
                            double totalPages = Math.ceil(totalRecords / 27);
                            
                            int tp = (int)totalPages;
                            
                            if(tp > 1){

                                for(int i = 1; i <= tp; i++){
                                    
                                    payLoad = "{\"settings\":{\"MainTypeGroup\":\"" + mainTypeGroup + "\",\"Category\":\"" + searchType + "\",\"CountryID\":\"1\",\"AdvancedSearchOpen\":\"false\",\"MailID\":\"\",\"PayType\":\"" + searchType + "\",\"Type\":\"" + searchCat + "\",\"RoomsMin\":\"0\",\"RoomsMax\":\"0\",\"PriceMin\":\"0\",\"PriceMax\":\"0\",\"Regions\":[\"" + locationId + "\"],\"SubTypes\":[\"0\"],\"SizeMin\":\"0\",\"SizeMax\":\"0\",\"Available\":\"\",\"NoAgreement\":\"false\",\"FloorRange\":\"0\",\"RentalPeriod\":\"0\",\"equipmentgroups\":[],\"Email\":\"\",\"Interval\":\"0\",\"SubscriptionType1\":\"true\",\"SubscriptionType2\":\"true\",\"SubscriptionType4\":\"true\",\"SubscriptionType8\":\"true\",\"SubscriptionType128\":\"true\",\"SubscriptionType512\":\"true\"},\"manual\":false,\"skip\":" + (i*27) + ",\"reset\":true,\"position\":0,\"iframe\":0,\"defaultTitle\":true,\"saveSettings\":true,\"code\":\":)\"}";
                                    
                                    response = errorProneWebRequests(Url, HttpMethod.POST, null, headersData, payLoad);
                                    
                                    if(response != null || response.isEmpty() == false){
                                        
                                        jObj = new JSONObject(response);
                                        
                                        if(jObj.has("Rows") && jObj.isNull("Rows") == false){
                                            
                                            rowsData = jObj.getString("Rows");
                                            
                                            doc = Jsoup.parse(rowsData);
                                            
                                            allUrls = doc.select("li");
                                            
                                            for (Element link : allUrls) {
                                                
                                                String url = link.select("a").attr("href");
                                                
                                                if(url != null && url.isEmpty() == false ){
                                                    
                                                    url = url.replaceAll("\"", "");
                                                    
                                                    url = url.replace("\\", "");
                                                    
                                                    String url_ = getPropertyId(url);
                                                    
                                                    final String _url = url;
                                                    
                                                    if((url.isEmpty() == false && url.length() > 0) && (pSvc.getPostbyIdAndWebSite(url_, "urbanhome.ch") <= 0) && (Urls.stream().filter(a -> a.purls.equals(_url)).collect(Collectors.toList()).size() <= 0)){
                                                        Urls.add(new PropertyUrls(url, locationName, getCategoryName(searchCat), ""));
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
                
            }
            catch(JSONException e){
                System.out.println(" Get All Links Json Parsing Error => " + e.getMessage());
            }
            catch(Exception e){
                System.out.println(" Get All Links General Error => " + e.getMessage());        
            }

//            System.out.println(Url);
//            System.out.println(locationName + " => : " + Urls.size());
            
        return Urls;
    }
    
     private ArrayList<PropertyListJSON> GetPropertiesRecord(PropertySvcI pSvc, String ST, String rec_manger_id, int regionId, String regionName, ArrayList<PropertyUrls> urls, ArrayList<String> vendortype){
        ArrayList<PropertyListJSON> properties = new ArrayList<>();
        Document doc;
        
        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9");
//        headers.add("Accept-Encoding","gzip, deflate, br");
        headers.add("Accept-Language","en-US,en;q=0.9");
        headers.add("sec-fetch-dest","document");
        headers.add("sec-fetch-mode","navigate");
        headers.add("sec-fetch-site","none");
        headers.add("upgrade-insecure-requests","1");
        headers.add("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.132 Safari/537.36");
        
        for(PropertyUrls UrlId:urls){
                                    
            try{
                                
                String response = errorProneWebRequests(UrlId.getPurls(), HttpMethod.GET, null, headers, null);

                if(response != null && response.isEmpty() == false){
                    
                    doc = Jsoup.parse(response);
                                        
                    Elements verifyItsAgency = doc.select("a#logo1");
                    
                    String agencyName = verifyItsAgency != null ? verifyItsAgency.select("img").attr("title") : "";
                    
                    String V_id = verifyItsAgency != null ? verifyItsAgency.attr("href").replace("/", "") : "";
                    
                    agencyName = GetVendorTypeVerified(vendortype, agencyName);
                    
                    String publishDate = "";
                    
                    if((pSvc.getVendorbyIdAndWebSite(V_id, "urbanhome.ch") <= 0)){
                        
                        String featuresOfProperty = "";
                        
                        Elements features = doc.select("div#xGd");
                        
                        features = features != null ? features.select("div.cb") : features;
                        
                        features = features != null ? features.select("div.fl.m71 ~ div.a.d") : features;
                        
                        features = features != null ? features.select("ul.pb15") : features;

                        if(features.size() > 0){
                            
                            ArrayList<String> featuresOfP = new ArrayList<>();
                            
                            for (Element el : features) {
                                
                                Elements _el = el.select("li > ul");
                                                                
                                for (Element el_ : _el.select("li")) {

                                    el_.select("span.mr2").remove();
                                    
                                    featuresOfP.add(el_.text());
                                                                    
                                }
                            }
                            
                            if(featuresOfP.size() > 0){
                            
                                featuresOfProperty = featuresOfP.stream().map(n -> String.valueOf(n)).collect(Collectors.joining(",", "", ""));
                                                                
                            }
                            
                        }
                                                
                        String updateDate = "";

                        String searchType = ST.toLowerCase().contains("buy") ? "SALE" : "RENT";

                        String msRegion = regionName;
                        
                        String catOfProperty = UrlId.getPropertyCategory();

                        String propertyType = "";
                                                
                        Elements getadTitle = doc.select("div#report ~ div.cb > div > h2");
                        
                        String titleOfAdd = getadTitle != null && getadTitle.isEmpty() == false ? getadTitle.text() : "";
                                                                        
                        Element fullAddress = doc.select("span[itemprop='address']").first();

                        String streetNumber = fullAddress != null ? fullAddress.select("span[itemprop='streetAddress']").text() : "";

                        String postalCode = fullAddress != null ? fullAddress.select("span[itemprop='postalCode']").text() : "";

                        String cIty = fullAddress != null ? fullAddress.select("span[itemprop='addressLocality']").text() : "";

                        String sellPrice = "";
                        
                        Elements propertyGeneralInfo = doc.select("div#xInfos");

                        if(searchType.toLowerCase().contains("sale")){
                            
                            Element getPrice = propertyGeneralInfo != null ? propertyGeneralInfo.select("ul").first() : null;
                            
                            getPrice = getPrice != null ? getPrice.select("li:not(.cb.pt15)").first() : null;
                            
                            sellPrice = getPrice != null ? getPrice.select("h2").text().replaceAll("\\D+","") : "0";
                        }

                        String sellGrossPrice = "";
                        String sellNetPrice = "";
                        String sellPaymentType = searchType;

                        int rentPrice = 0;
                        int rentGrossPrice = 0;
                        int rentNetPrice = 0;

                        if(searchType.toLowerCase().contains("rent")){
                            
                            Element getPrice = propertyGeneralInfo != null ? propertyGeneralInfo.select("ul").first() : null;
                            
                            getPrice = getPrice != null ? getPrice.select("li:not(.cb.pt15)").first() : null;
                            
                            String priceData = getPrice != null ? getPrice.select("h2").text().replaceAll("\\D+","") : "0";

                            rentPrice = Integer.parseInt(priceData != null && priceData.isEmpty() == false && priceData.length() > 0 ? priceData : "0");

                            priceData = getPrice != null ? getPrice.select("div.cb").first().text().replaceAll("\\D+","") : "0";

                            rentGrossPrice = Integer.parseInt(priceData != null && priceData.isEmpty() == false && priceData.length() > 0 ? priceData : "0");
                            rentNetPrice = rentPrice;
                        }

                        String pId = getPropertyId(UrlId.getPurls());

                        String rentPaymentType = searchType;
                        
                        Element getGeneralInfo = propertyGeneralInfo != null ? propertyGeneralInfo.select("ul").first() : null;
                        
                        Element _getGeneralInfo = getGeneralInfo != null ? getGeneralInfo.select("li.cb.pt15").first() : null;
                        
                        Elements getGeneralInfo_ = _getGeneralInfo != null ? _getGeneralInfo.select("div.cb") : null;
                        
                        String numberOfRooms = getValueFromList(getGeneralInfo_, "Zimmer:");

                        String livingSpace = getValueFromList(getGeneralInfo_, "Wohnfläche:");
                        String propertyFloor = getValueFromList(getGeneralInfo_, "Etagen im Haus:");
                        String propertyArea = "";
                        
                        Elements getadDesc = doc.select("div#report ~ div.cb");
                        
                        getadDesc.select("script").remove();
                        getadDesc.select("div.a.d").remove();
                                                
                        String propertyDesc = getadDesc != null ? getadDesc.text() : "";
                        String propertyAvailable = "";

                        String yearBuilt = getValueFromList(getGeneralInfo_, "Baujahr:");
                        
                        Element _propertyGeneralInfo = null;
                        
                        for (Element el_ : doc.select("div#xInfos > div.cb")) {
                            
                            if(el_.text().contains("Kontakt")){
                                el_.select("div#contactform").remove();
                                _propertyGeneralInfo = el_;
                            }
                            
                        }
                                                
                        Elements contactdetails = _propertyGeneralInfo != null ? _propertyGeneralInfo.select("div.fl") : null;
                        
                        String nameOfSeller = contactdetails != null ? contactdetails.select("img.cb").attr("src") : "";
                        
                        Elements agencyLocation = doc.select("div#partner");
                        
                        String streetNumberOfSeller = agencyLocation != null ? (agencyLocation.select("span[itemprop='streetAddress']") != null ? agencyLocation.select("span[itemprop='streetAddress']").text() : "") : "" ;
                        String postalCodeOfSeller = agencyLocation != null ? (agencyLocation.select("span[itemprop='postalCode']") != null ? agencyLocation.select("span[itemprop='postalCode']").text().replaceAll("\\D+","") : "") : "" ;
                        String cityOfSeller = agencyLocation != null ? (agencyLocation.select("span[itemprop='addressLocality']") != null ? agencyLocation.select("span[itemprop='addressLocality']").text() : "") : "" ;
                        
                        String phoneOfSeller = contactdetails != null ? contactdetails.select("img#imgShowPhone1").attr("src") : "";;
                        
                        contactdetails = _propertyGeneralInfo != null ? _propertyGeneralInfo.select("div.cb.pt15") : null;
                        
                        String mNumberOfSeller = contactdetails != null ? ( contactdetails.select("div#divShowPhoneContainer").first() != null ? contactdetails.select("div#divShowPhoneContainer").first().select("img#imgShowPhone2").attr("src") : "") : "";
                                                
                        String emailOfSeller = "";
                                                
                        String contactName = _propertyGeneralInfo != null ? ( _propertyGeneralInfo.select("div.cb.pt15 > img.cb.db") != null ? _propertyGeneralInfo.select("div.cb.pt15 > img.cb.db").attr("src") : "" ) : "";
                                                
                        Element _contactdetails = contactdetails != null && contactdetails.select("div#divShowPhoneContainer") != null && contactdetails.select("div#divShowPhoneContainer").size() > 1 ? contactdetails.select("div#divShowPhoneContainer").get(1) : null;
                        
                        String contactNumber = _contactdetails != null ? ( _contactdetails.select("img#imgShowPhone3") != null ? _contactdetails.select("img#imgShowPhone3").attr("src") : "" ) : "";
                        
                        String urlOfAd = UrlId.getPurls();
                        
                        ArrayList<ImageUrls> iu = new ArrayList<>();
                        
                        Elements allImgUrls = doc.select("div#xGd");
                        
                        allImgUrls = allImgUrls != null ? ( allImgUrls.select("div.cb").first() != null ? allImgUrls.select("div.cb").first().select("div#th > ul > li > a") : allImgUrls ) : allImgUrls;
                        
                        if(allImgUrls != null && allImgUrls.size() > 0){
                            allImgUrls.forEach((link) -> {
                                iu.add(new ImageUrls("","https://www.urbanhome.ch/" + link.attr("href")));
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

                            Property p = new Property(0 , rec_manger_id, regionId, pId, V_id , agencyName.isEmpty() || agencyName == null ? "Private vendor / Unassigned vendor" : "Institutional vendor" , publishDate, updateDate, searchType, msRegion, catOfProperty, propertyType, titleOfAdd, streetNumber, postalCode, cIty, sellPrice, sellGrossPrice, sellNetPrice, sellPaymentType, Integer.toString(rentPrice), Integer.toString(rentGrossPrice), Integer.toString(rentNetPrice), rentPaymentType, numberOfRooms, livingSpace, propertyFloor, propertyArea, propertyDesc, featuresOfProperty, propertyAvailable, yearBuilt, nameOfSeller, agencyName, streetNumberOfSeller, postalCodeOfSeller, cityOfSeller, phoneOfSeller, mNumberOfSeller, emailOfSeller, contactName, contactNumber, "urbanhome.ch", urlOfAd, ct.format(dNow), cd.format(dNow), cm.format(dNow), cy.format(dNow));
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

                            Property p = new Property(0 , rec_manger_id, regionId, pId, V_id, agencyName.isEmpty() || agencyName == null ? "Private vendor / Unassigned vendor" : "Institutional vendor", publishDate, updateDate, searchType, msRegion, catOfProperty, propertyType, titleOfAdd, streetNumber, postalCode, cIty, sellPrice, sellGrossPrice, sellNetPrice, sellPaymentType, Integer.toString(rentPrice), Integer.toString(rentGrossPrice), Integer.toString(rentNetPrice), rentPaymentType, numberOfRooms, livingSpace, propertyFloor, propertyArea, propertyDesc, featuresOfProperty, propertyAvailable, yearBuilt, nameOfSeller, agencyName, streetNumberOfSeller, postalCodeOfSeller, cityOfSeller, phoneOfSeller, mNumberOfSeller, emailOfSeller, contactName, contactNumber, "urbanhome.ch", urlOfAd, ct.format(dNow), cd.format(dNow), cm.format(dNow), cy.format(dNow));
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

                            Property p = new Property(0 , rec_manger_id, regionId, pId, V_id , agencyName.isEmpty() || agencyName == null ? "Private vendor / Unassigned vendor" : "Institutional vendor" , publishDate, updateDate, searchType, msRegion, catOfProperty, propertyType, titleOfAdd, streetNumber, postalCode, cIty, sellPrice, sellGrossPrice, sellNetPrice, sellPaymentType, Integer.toString(rentPrice), Integer.toString(rentGrossPrice), Integer.toString(rentNetPrice), rentPaymentType, numberOfRooms, livingSpace, propertyFloor, propertyArea, propertyDesc, featuresOfProperty, propertyAvailable, yearBuilt, nameOfSeller, agencyName, streetNumberOfSeller, postalCodeOfSeller, cityOfSeller, phoneOfSeller, mNumberOfSeller, emailOfSeller, contactName, contactNumber, "urbanhome.ch", urlOfAd, ct.format(dNow), cd.format(dNow), cm.format(dNow), cy.format(dNow));
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
                System.out.println("Urban Home Single Post Number Formating Error : " + ex.getMessage());
            }catch (Exception ex) {
                System.out.println("Urban Home Single Post Page Site Request Error : " + ex.getMessage());
            }
            
        }
        
        return properties;
    }
     
     public String getValueFromList(Elements elms, String key){
        String str = "";
        
        for(Element elm:elms){
            String _key = elm.text();
            
            if(_key.contains(key)){
                str = _key.replaceAll(key, "");
                break;
            }
            
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
    
    public String getCategoryName(String categoryName){
        
        Map<String,String> categoriesData = new HashMap<>();
        
        categoriesData.put("1","Flat");
        categoriesData.put("2","Flat Share");
        categoriesData.put("4","House");
        categoriesData.put("8","Parking Spot");
        categoriesData.put("128","Property");
        categoriesData.put("512","Vacation home");
        
        return categoriesData.get(categoryName) != null ? categoriesData.get(categoryName) : "Flat";
    }
    
    public String getMainTypeGroup(){
        return "";
    }
    
    public String getMainCategory(){
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
        
            System.out.println("Urban Home Error From Error Prone Request Normal " + ex.getMessage() + " => " + payloadData);
        
                try {

                    this.proxyVar = setupProxy();
                    
                    restTemplate = this.proxyVar;
                    
                    HttpEntity entity = new HttpEntity(formData, headersData);
                        
                    ResponseEntity<String> response = restTemplate.exchange(Url, method, entity, String.class);

                    result = response.getBody();
                    
                } catch (Exception ex1) {
                    System.out.println("Urban Home Error From Error Prone Request Abnormal " + ex1.getMessage() + " => " + payloadData);
                }    
        }catch (Exception ex) {
        
            System.out.println("Urban Home Error From Error Prone Request Normal " + ex.getMessage() + " => " + payloadData);
        
                try {

                    this.proxyVar = setupProxy();
                    
                    restTemplate = this.proxyVar;
                    
                    HttpEntity entity = new HttpEntity(formData, headersData);
                        
                    ResponseEntity<String> response = restTemplate.exchange(Url, method, entity, String.class);

                    result = response.getBody();
                    
                } catch (Exception ex1) {
                    System.out.println("Urban Home Error From Error Prone Request Abnormal " + ex1.getMessage() + " => " + payloadData);
                }    
        }
        
        return (result != null ? result : null);
    }
    
    private ArrayList<String> getCityNameAndId(String cityName){
        
        HttpHeaders headersData = new HttpHeaders();
        headersData.add("content-type","application/json");
        
        ArrayList<String> cityIds = new ArrayList<>();
            
        try{
                
                this.proxyVar = setupProxy();
                
                String response = errorProneWebRequests("https://www.urbanhome.ch/Region/GetSearchItems", HttpMethod.POST, null, headersData, "{\"countryID\":0}");
                
                if(response.isEmpty() == false && response != null){
                                        
                    JSONArray jaObj = new JSONArray(response);
                    
                    for(int i=0; i < jaObj.length(); i++){
                        JSONObject jsonobject = jaObj.getJSONObject(i);
                        if(jsonobject.getString("DisplayText").toLowerCase().endsWith(cityName) && Character.isDigit(jsonobject.getString("DisplayText").charAt(0))){
                            if(jsonobject.has("Key")){
                                cityIds.add(jsonobject.getBigInteger("Key").toString());
                            }
                        }  
                    }
                }
        }
        catch(JSONException e){
            System.out.println("Urban Home City List JSON Parsing Error Exception : " + e.getMessage());
        }
        catch(Exception e){
            System.out.println("Urban Home City List Requesting Error Exception : " + e.getMessage());
        }
        
        return cityIds;
    }
}
