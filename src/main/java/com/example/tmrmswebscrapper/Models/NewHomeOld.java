/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.tmrmswebscrapper.Models;

import com.example.tmrmswebscrapper.Svc.PropertySvcI;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;
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
import org.jsoup.Connection.Method;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
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
public class NewHomeOld {
    
    private RestTemplate proxyVar;
        
    public ArrayList<PropertyListJSON> GetTotalResultsCount(PropertySvcI pSvc, String rec_manger_id, int regionId, String searchIn, String cityName, String regionName, String searchCat, ArrayList<String> vendortype){
                
        String propertyCategory = getCategoryNameUrlParam(searchCat);
        ArrayList<PropertyListJSON> props = new ArrayList<>();

        Map<String,String> cityNameAndId = getCityNameAndId(cityName.toLowerCase());
        
        if(cityNameAndId.size() > 0){
        
            String totalPostsCount = countLocationAndCategoryposts(cityNameAndId.get("cityName").replaceAll(Pattern.quote("-"), " "), cityNameAndId.get("cityId"), searchCat, (searchIn.toLowerCase().contains("buy") ? "1" : "2"));
            
            ArrayList<PropertyUrls> Urls = new ArrayList<>();
            String url = "[\"" + cityNameAndId.get("cityId") + "\"]";
        
            try{

                double totalRecords = Double.parseDouble((totalPostsCount.isEmpty() ? "0" : totalPostsCount));

                if(totalRecords > 0){

                    double totalPages = Math.ceil(totalRecords / 20);

                    int tp = (int)totalPages;
                    
                    this.proxyVar = setupProxy();

                    if(tp > 1){

                        for(int i = 0; i < tp; i++){
                            Urls.addAll(getAllLinks(pSvc, (searchIn.toLowerCase().contains("buy") ? "buying" : "renting"), propertyCategory, cityNameAndId.get("cityName"), URLEncoder.encode(url, "UTF-8"), i, vendortype));
                        }

                    }else{

                        Urls.addAll(getAllLinks(pSvc, (searchIn.toLowerCase().contains("buy") ? "buying" : "renting"), propertyCategory, cityNameAndId.get("cityName"), URLEncoder.encode(url, "UTF-8" ), 0, vendortype));
                    }

                }
            }catch(IOException e){

            }catch(Exception e){

            }

            if(Urls.size() > 0){
                props = GetPropertiesRecord(pSvc, searchIn, rec_manger_id, regionId, regionName, Urls, vendortype);
            }
        
        }
        
        return props;
    }
    
    private RestTemplate requestViaProxy() throws Exception {
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
    
    public Map<String,String> getCityNameAndId(String cityName){
        
        String url = "https://www.newhome.ch/service/api/search/SucheOrt?type=public&keyword=" + cityName + "&languageIso=en";
        
        Map<String,String> headersData = new HashMap<>();
        headersData.put("Accept","*/*");
        headersData.put("Accept-Encoding","gzip, deflate, br");
        headersData.put("Accept-Language","en-US,en;q=0.9");
        headersData.put("Referer","https://www.newhome.ch/en/home");
        headersData.put("sec-fetch-dest","empty");
        headersData.put("sec-fetch-mode","cors");
        headersData.put("sec-fetch-site","same-origin");
        headersData.put("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.132 Safari/537.36");
        headersData.put("x-requested-with","XMLHttpRequest");
        
        Map<String,String> str = new HashMap<>();
        
        try{
            
            Document doc;
            doc = Jsoup.connect(url).maxBodySize(0).timeout(1000000).headers(headersData).ignoreContentType(true).get();
            String _response_ = doc.text();
                        
            JSONArray jsonarray = new JSONArray(_response_);
                        
            for(int i=0; i < jsonarray.length(); i++){
                JSONObject jsonobject = jsonarray.getJSONObject(i);
                if(jsonobject.getString("Name").toLowerCase().equals(cityName)){
                    str.put("cityId",jsonobject.getString("Id"));
                    str.put("cityName",cityName.replaceAll(Pattern.quote(" "), "-"));
                    break;
                }  
            }
            
            if(str.isEmpty() && jsonarray.length() == 1){
                JSONObject jsonobject = jsonarray.getJSONObject(0);
                if(jsonobject.getString("Kategorie").toLowerCase().equals("city")){
                    str.put("cityId",jsonobject.getString("Id"));
                    str.put("cityName",jsonobject.getString("Name").replaceAll(Pattern.quote(" "), "-"));
                }
            }
        
        }catch(IOException e){
            
        }catch (JSONException e) {
            System.out.println("New Home Json Parsing Error While getting id's of Cities : " + e.getMessage());
        }
        
        return str;
    }
    
    private ArrayList<PropertyUrls> getAllLinks(PropertySvcI pSvc, String searchType, String propertyCategory, String locationName, String locationId, int pageNumber, ArrayList<String> vendortype){
        ArrayList<PropertyUrls> Urls = new ArrayList<>();
        String Url = "https://www.newhome.ch/de/" + (searchType.toLowerCase().equals("rent") ? "mieten" : "kaufen") + "/suchen/"+ propertyCategory +"/ort-" + locationName + "/liste?wo=" + locationId + (pageNumber > 0 ? "&p=" + pageNumber : "");

        System.out.println("URL => " + Url);
        
        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9");
        headers.add("Accept-Encoding","gzip, deflate, br");
        headers.add("Accept-Language","en-US,en;q=0.9");
        headers.add("sec-fetch-dest","document");
        headers.add("sec-fetch-mode","navigate");
        headers.add("sec-fetch-site","none");
        headers.add("upgrade-insecure-requests","1");
        headers.add("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.132 Safari/537.36");
                
                String response = errorProneWebRequests(Url, HttpMethod.GET, headers, null);
                                            
                if(response != null || response.isEmpty() == false){
                    Document doc = Jsoup.parse(response) ;
                    String vendorTypeCheck = GetVendorTypeVerified(vendortype);

                    Elements allUrls = doc.getElementsByClass("object-list").select(vendorTypeCheck).select("div.details").select("a");
                    
                    for (Element link : allUrls) {
                        
                        String url = link.attr("href");
                        
                        Map<String, String> urlPrams = getUrlValues(url);
                        
                        String propertyId = urlPrams.size() > 0 ? urlPrams.get("id") : "";
                        
                        if((pSvc.getPostbyIdAndWebSite(propertyId, "newhome.ch") <= 0) && (Urls.stream().filter(a -> a.purls.equals(url)).collect(Collectors.toList()).size() <= 0)){
                            Urls.add(new PropertyUrls(url,locationName.replaceAll(Pattern.quote("-"), " "), getCategoryName(propertyCategory), ""));
                        }
                    }
                }
                
            System.out.println(Url);
            System.out.println(locationName + " => : " + Urls.size());
        return Urls;
    }
    
    public Map<String, String> getUrlValues(String url){
        
        int i = url.indexOf("?");
        Map<String, String> paramsMap = new HashMap<>();
        
        try{
            
            if (i > -1) {
                String searchURL = url.substring(url.indexOf("?") + 1);
                String params[] = searchURL.split("&");

                for (String param : params) {
                    String temp[] = param.split("=");
                    paramsMap.put(temp[0], java.net.URLDecoder.decode(temp[1], "UTF-8"));
                }
            }
            
        }catch(UnsupportedEncodingException ex){
            
        }

        return paramsMap;
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
    
    private ArrayList<PropertyListJSON> GetPropertiesRecord(PropertySvcI pSvc, String ST, String rec_manger_id, int regionId, String regionName, ArrayList<PropertyUrls> urls, ArrayList<String> vendortype){
        ArrayList<PropertyListJSON> properties = new ArrayList<>();
        Document doc;
        
        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9");
        headers.add("Accept-Encoding","gzip, deflate, br");
        headers.add("Accept-Language","en-US,en;q=0.9");
        headers.add("sec-fetch-dest","document");
        headers.add("sec-fetch-mode","navigate");
        headers.add("sec-fetch-site","none");
        headers.add("upgrade-insecure-requests","1");
        headers.add("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.132 Safari/537.36");
        
        for(PropertyUrls UrlId:urls){
                        
            try{
                
                String response = errorProneWebRequests(UrlId.getPurls(), HttpMethod.GET, headers, null);
                
                if(response != null && response.isEmpty() == false){
                
                    doc = Jsoup.parse(response);

                    Elements elements = doc.getElementsByTag("script");

                    String getTextForVenId = elements.stream().filter(e -> e.data().contains("dimension5") && e.childNodes().size() > 0)
                    .findFirst().map(e -> e.childNode(0).outerHtml()).orElse(StringUtils.EMPTY);

                    String V_id = (getTextForVenId != null && getTextForVenId.equals("") == false) ? StringUtils.substringBetween(getTextForVenId, "{'dimension5':'", "'}]; (function(") : "";

                    Elements verifyItsAgency = doc.getElementById("ctl00_cphContent_ctl00_ctl00_ucKontakt_dAnbieter").select("a");

                    String agencyName = verifyItsAgency != null ? (doc.getElementById("ctl00_cphContent_ctl00_ctl00_ucKontakt_lFirmaName") != null ? doc.getElementById("ctl00_cphContent_ctl00_ctl00_ucKontakt_lFirmaName").text() : "") : "" ;

                    agencyName = GetVendorTypeVerified(vendortype, agencyName);
                    
                    String publishDate = "";

                    if((pSvc.getVendorbyIdAndWebSite(V_id, "newhome.ch") <= 0)){

                        String featuresOfProperty = "";

                        Elements allFeatures = (doc.select("div#ctl00_cphContent_ctl00_ctl00_csAusstattung") != null ? doc.select("div#ctl00_cphContent_ctl00_ctl00_csAusstattung").select("div.col-xs-12.col-sm-6") : null);

                        if(allFeatures.size() > 0){
                            
                            ArrayList<String> featuresOfP = new ArrayList<>();
                            
                            for (Element el : allFeatures) {
                                featuresOfP.add(el.text());
                            }
                            
                            featuresOfProperty = featuresOfP.stream().map(n -> String.valueOf(n)).collect(Collectors.joining(",", "", ""));
                            
                        }
                        
                        String updateDate = "";

                        String searchType = ST.toLowerCase().contains("buy") ? "SALE" : "RENT";

                        String msRegion = regionName;
                        
                        String catOfProperty = UrlId.getPropertyCategory();

                        String propertyType = (doc.getElementById("ctl00_cphContent_ctl00_ctl00_ctl35_fiUnterobjektart") != null ? doc.getElementById("ctl00_cphContent_ctl00_ctl00_ctl35_fiUnterobjektart").select("div.col-right").text() : "");

                        String titleOfAdd = (doc.select("div.content-section.overview.group") != null ? doc.select("div.content-section.overview.group").select("h1").text() : "");

                        String streetNumber = (doc.select("span[itemprop='streetAddress']") != null ? doc.select("span[itemprop='streetAddress']").text().replace("On request", "") : "");

                        String postalCode = (doc.select("span[itemprop='postalCode']") != null ? doc.select("span[itemprop='postalCode']").text() : "");

                        String cIty = (doc.select("span[itemprop='addressLocality']") != null ? doc.select("span[itemprop='addressLocality']").text() : "");

                        String sellPrice = "";

                        if(searchType.toLowerCase().contains("sale")){
                            sellPrice = (doc.select("small + strong.price") != null ? doc.select("small + strong.price").text().replaceAll("\\D+","") : "0");
                        }

                        String sellGrossPrice = "";
                        String sellNetPrice = "";
                        String sellPaymentType = searchType;

                        int rentPrice = 0;
                        int rentGrossPrice = 0;
                        int rentNetPrice = 0;

                        if(searchType.toLowerCase().contains("rent")){
                            String priceData = (doc.getElementById("ctl00_cphContent_ctl00_ctl00_ctl35_fiNettoMieteProMonat") != null ? doc.getElementById("ctl00_cphContent_ctl00_ctl00_ctl35_fiNettoMieteProMonat").select("div.col-right").text().replaceAll("\\D+","") : "0");

                            rentPrice = Integer.parseInt(priceData != null && priceData.isEmpty() == false && priceData.length() > 0 ? priceData : "0");

                            priceData = doc.getElementById("ctl00_cphContent_ctl00_ctl00_ctl34_dPreis") != null ? doc.getElementById("ctl00_cphContent_ctl00_ctl00_ctl34_dPreis").select("strong").text().replaceAll("\\D+","") : "0";

                            rentGrossPrice = Integer.parseInt(priceData != null && priceData.isEmpty() == false && priceData.length() > 0 ? priceData : "0");
                            rentNetPrice = rentPrice;
                        }

                        String pId = (doc.getElementById("ctl00_cphContent_ctl00_ctl00_ctl35_fiImmocode") != null ? doc.getElementById("ctl00_cphContent_ctl00_ctl00_ctl35_fiImmocode").select("div.col-right").text() : "");

                        String rentPaymentType = searchType;
                        String numberOfRooms = (doc.select("span[itemprop='numberOfRooms']") != null ? doc.select("span[itemprop='numberOfRooms']").text() : "");

                        String livingSpace = (doc.select("span[itemprop='floorSize']") != null ? doc.select("span[itemprop='floorSize']").text().replaceAll("\\D+","") : "");
                        String propertyFloor = (doc.getElementById("ctl00_cphContent_ctl00_ctl00_ctl35_fiEtagen") != null ? doc.getElementById("ctl00_cphContent_ctl00_ctl00_ctl35_fiEtagen").select("div.col-right").text() : "");
                        String propertyArea = "";
                        String propertyDesc = (doc.getElementById("dDescription") != null ? doc.getElementById("dDescription").text() : "");
                        String propertyAvailable = (doc.getElementById("ctl00_cphContent_ctl00_ctl00_ctl35_fiBezug") != null ? doc.getElementById("ctl00_cphContent_ctl00_ctl00_ctl35_fiBezug").select("div.col-right").text() : "");

                        try{

                            if(propertyAvailable != null && propertyAvailable.isEmpty() == false){
                                SimpleDateFormat adDateFormatter = new SimpleDateFormat("dd.MM.yyyy");
                                Date date = adDateFormatter.parse(propertyAvailable);

                                SimpleDateFormat cd_ = new SimpleDateFormat ("yyyy-MM-dd");
                                propertyAvailable = cd_.format(date);
                            }

                        }catch (ParseException ex) {

                            System.out.println("New Home Single Post Page Site Parse Date Error : " + propertyAvailable);
                            propertyAvailable = "";

                        }

                        String yearBuilt = (doc.getElementById("ctl00_cphContent_ctl00_ctl00_ctl34_Label1") != null ? doc.getElementById("ctl00_cphContent_ctl00_ctl00_ctl34_Label1").text() : "");
                        String nameOfSeller = (doc.getElementById("ctl00_cphContent_ctl00_ctl00_ucKontakt_lFirmaName") != null ? doc.getElementById("ctl00_cphContent_ctl00_ctl00_ucKontakt_lFirmaName").text() : "");
                        
                        nameOfSeller =  nameOfSeller.isEmpty() ? (doc.getElementById("ctl00_cphContent_ctl00_ctl00_ucKontakt_lName") != null ? doc.getElementById("ctl00_cphContent_ctl00_ctl00_ucKontakt_lName").text() : "") : nameOfSeller;
                        
                        String streetNumberOfSeller = (doc.getElementById("ctl00_cphContent_ctl00_ctl00_ucKontakt_lStrasse") != null ? doc.getElementById("ctl00_cphContent_ctl00_ctl00_ucKontakt_lStrasse").text() : "");
                        String postalCodeOfSeller = (doc.getElementById("ctl00_cphContent_ctl00_ctl00_ucKontakt_lOrt") != null ? doc.getElementById("ctl00_cphContent_ctl00_ctl00_ucKontakt_lOrt").text().replaceAll("\\D+","") : "");
                        String cityOfSeller = (doc.getElementById("ctl00_cphContent_ctl00_ctl00_ucKontakt_lOrt") != null ? doc.getElementById("ctl00_cphContent_ctl00_ctl00_ucKontakt_lOrt").text().replaceAll("\\d","") : "");
                        String phoneOfSeller = (doc.getElementById("ctl00_cphContent_ctl00_ctl00_ucKontaktModal_mdKontaktAnrufen_ctl06_fiTelefonGeschaeft") != null ? doc.getElementById("ctl00_cphContent_ctl00_ctl00_ucKontaktModal_mdKontaktAnrufen_ctl06_fiTelefonGeschaeft").select("div.col-right").text() : "");
                        
                        phoneOfSeller = (phoneOfSeller.isEmpty()) ? (doc.getElementById("ctl00_cphContent_ctl00_ctl00_ucKontaktModal_mdKontaktAnrufen_ctl06_fiTelefonGeschaeft") != null ? doc.getElementById("ctl00_cphContent_ctl00_ctl00_ucKontaktModal_mdKontaktAnrufen_ctl06_fiTelefonGeschaeft").select("div.col-right").text() : "") : phoneOfSeller;
                        
                        phoneOfSeller = (phoneOfSeller.isEmpty()) ? (doc.getElementById("ctl00_cphContent_ctl00_ctl00_ucKontaktModal_mdKontaktAnrufen_ctl06_TelefonPrivat") != null ? doc.getElementById("ctl00_cphContent_ctl00_ctl00_ucKontaktModal_mdKontaktAnrufen_ctl06_TelefonPrivat").select("div.col-right").text() : "") : phoneOfSeller;
                        
                        phoneOfSeller = (phoneOfSeller.isEmpty()) ? (doc.getElementById("ctl00_cphContent_ctl00_ctl00_ucKontaktModal_mdKontaktAnrufen_ctl06_dPhone") != null ? doc.getElementById("ctl00_cphContent_ctl00_ctl00_ucKontaktModal_mdKontaktAnrufen_ctl06_dPhone").select("div.col-right").text() : "") : phoneOfSeller;
                        
                        String mNumberOfSeller = (doc.getElementById("ctl00_cphContent_ctl00_ctl00_ucKontaktModal_mdKontaktAnrufen_ctl06_fiTelefonMobile") != null ? doc.getElementById("ctl00_cphContent_ctl00_ctl00_ucKontaktModal_mdKontaktAnrufen_ctl06_fiTelefonMobile").select("div.col-right").text() : "");
                        
                        String emailOfSeller = ""; 
                        String contactName = (doc.getElementById("ctl00_cphContent_ctl00_ctl00_ucKontaktModal_mdKontaktAnrufen_ctl06_fBesichtigung") != null ? doc.getElementById("ctl00_cphContent_ctl00_ctl00_ucKontaktModal_mdKontaktAnrufen_ctl06_fBesichtigung").select("p").text() : "");
                        String contactNumber = (doc.getElementById("ctl00_cphContent_ctl00_ctl00_ucKontaktModal_mdKontaktAnrufen_ctl06_fiTelefonBesichtigungKontakt_label") != null ? doc.getElementById("ctl00_cphContent_ctl00_ctl00_ucKontaktModal_mdKontaktAnrufen_ctl06_fiTelefonBesichtigungKontakt_label").select("div.col-right").text() : "");
                        
                        contactNumber = (contactNumber.isEmpty()) ? (doc.getElementById("ctl00_cphContent_ctl00_ctl00_ucKontaktModal_mdKontaktAnrufen_ctl06_fiTelefonBesichtigungKontakt") != null ? doc.getElementById("ctl00_cphContent_ctl00_ctl00_ucKontaktModal_mdKontaktAnrufen_ctl06_fiTelefonBesichtigungKontakt").select("div.col-right").text() : "") : contactNumber;
                        
                        String urlOfAd = UrlId.purls;
                        
                        ArrayList<ImageUrls> iu = new ArrayList<>();

                        Elements allImgUrls = (doc.select("div#ctl00_cphContent_ctl00_ctl00_dSliderGallery") != null ? doc.select("div#ctl00_cphContent_ctl00_ctl00_dSliderGallery").select("img") : null);

                        if(allImgUrls != null && allImgUrls.size() > 0){
                            allImgUrls.forEach((link) -> {
                                iu.add(new ImageUrls("",link.attr("data-lazy")));
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
                
            } catch (Exception ex) {
                System.out.println("New Home Single Post Page Site Request Error : " + ex.getMessage());
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
    
    public String getCategoryNameUrlParam(String categoryNumber){
        
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
    
    public String GetVendorTypeVerified(ArrayList<String> vendorType){
        String str = "";
        
        if((vendorType.stream().filter(a -> a.equals("Unassigned vendor")).collect(Collectors.toList()).size() > 0 || vendorType.stream().filter(a -> a.equals("Private vendor")).collect(Collectors.toList()).size() > 0) && (vendorType.stream().filter(a -> a.equals("Institutional vendor")).collect(Collectors.toList()).isEmpty())){
            str = "div:not(.provider).item";
        }else if((vendorType.stream().filter(a -> a.equals("Unassigned vendor")).collect(Collectors.toList()).isEmpty() && vendorType.stream().filter(a -> a.equals("Private vendor")).collect(Collectors.toList()).isEmpty()) && vendorType.stream().filter(a -> a.equals("Institutional vendor")).collect(Collectors.toList()).size() > 0){
            str = "div.item.provider";
        }else if((vendorType.stream().filter(a -> a.equals("Unassigned vendor")).collect(Collectors.toList()).size() > 0 || vendorType.stream().filter(a -> a.equals("Private vendor")).collect(Collectors.toList()).size() > 0) && (vendorType.stream().filter(a -> a.equals("Institutional vendor")).collect(Collectors.toList()).size() > 0)){
            str = "div.item";
        }
        
        return str.isEmpty() ? "div.item" : str;
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
    
    public String getCategoryName(String categoryParam){
        
        Map<String,String> categoriesData = new HashMap<>();
        
        categoriesData.put("house-apartment","House and apartment");
        categoriesData.put("house","House");
        categoriesData.put("apartment","Apartment");
        categoriesData.put("apartment-building","Apartment building");
        categoriesData.put("business","Business");
        categoriesData.put("building-land","Building land/plot");
        categoriesData.put("parking-space","Parking space");
        categoriesData.put("business-apartment","Business Apartments");
                        
        return categoriesData.get(categoryParam) != null ? categoriesData.get(categoryParam) : "";
    }
    
    public String countLocationAndCategoryposts(String location, String locationId, String categoryId, String searchType){
        
        String str = "";
        
        String url = "https://www.newhome.ch/service/api/search/SuchCount";
        
        Map<String,String> headersData = new HashMap<>();
        headersData.put("Accept","*/*");
        headersData.put("Accept-Encoding","gzip, deflate, br");
        headersData.put("Accept-Language","en-US,en;q=0.9");
        headersData.put("content-type","application/json; charset=UTF-8");
        headersData.put("origin","https://www.newhome.ch");
        headersData.put("Referer","https://www.newhome.ch/en/home");
        headersData.put("sec-fetch-dest","empty");
        headersData.put("sec-fetch-mode","cors");
        headersData.put("sec-fetch-site","same-origin");
        headersData.put("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.132 Safari/537.36");
        headersData.put("x-requested-with","XMLHttpRequest");
        
        String jsonPayload = "{\"SucheWo\":[{\"Bezeichnung\":\"" + location + "\",\"Value\":\"" + locationId + "\"}],\"RegionIds\":null,\"BenutzerIds\":null,\"Umkreis\":\"0\",\"ObjektartCode\":"+ categoryId +",\"AngebotsartCode\":" + searchType + ",\"UnterobjektartCode\":\"\",\"Verfuegbarkeitstyp\":\"0\",\"Verfuegbarkeitsdatum\":null,\"PreisMin\":\"\",\"PreisMax\":\"\",\"PreisM2Min\":\"\",\"PreisM2Max\":\"\",\"ZimmerMin\":\"\",\"ZimmerMax\":\"\",\"WohnflaecheMin\":\"\",\"WohnflaecheMax\":\"\",\"GrundstueckMin\":\"\",\"GrundstueckMax\":\"\",\"MFHBaujahrAb\":\"\",\"MFHBruttorendidteAb\":\"\",\"BaulandFlaecheMin\":\"\",\"BaulandFlaecheMax\":\"\",\"GewerbeNutzflacheMin\":\"\",\"GewerbeNutzflacheMax\":\"\",\"GewerbeBodenbelastungMin\":\"\",\"GewerbeBodenbelastungMax\":\"\",\"GewerbeRaumhoeheMin\":\"\",\"GewerbeRaumhoeheMax\":\"\",\"PPFahrzeugeMin\":\"\",\"PPFahrzeugeMax\":\"\",\"GarageParkplatz\":false,\"BalkonTerrasse\":false,\"VollErschlossen\":false,\"Duschen\":false,\"Parkplatz\":false,\"Garage\":false,\"Gleisanschluss\":false,\"Lift\":false,\"Rollstuhlgaenig\":false,\"Minergie\":false,\"Parterre\":false,\"HaustiereGestattet\":false,\"Wirtschaftsfoerderung\":false,\"Altbau\":false,\"Neubau\":false}";
                
        try{
            
            String response = Jsoup.connect(url).maxBodySize(0).timeout(1000000).headers(headersData).ignoreContentType(true).method(Method.POST).requestBody(jsonPayload).execute().body();
            
            str = response.isEmpty() ? "0" : response;
        
        }catch(IOException e){

        }

        return str;
    }
    
}
