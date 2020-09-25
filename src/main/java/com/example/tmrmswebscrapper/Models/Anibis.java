/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.tmrmswebscrapper.Models;

import com.example.tmrmswebscrapper.Svc.PropertySvcI;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
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
public class Anibis {
    private RestTemplate proxyVar;
    
    public ArrayList<PropertyListJSON> GetTotalResultsCount(PropertySvcI pSvc, String rec_manger_id, int regionId, String searchIn, String cityName, String regionName, String searchCat, ArrayList<String> vendortype){
                
        ArrayList<PropertyListJSON> props = new ArrayList<>();

        ArrayList<String> cityIds = getCityNameAndId(cityName.toLowerCase());
        
        if(cityIds.size() > 0){
                                    
            ArrayList<PropertyUrls> Urls = new ArrayList<>();
            
            for (String el : cityIds) {
                
               Urls.addAll(getAllLinks(pSvc, (searchIn.toLowerCase().contains("buy") ? "immobilier-immobilier-ventes--438" : "immobilier-immobilier-locations--410"), searchCat, cityName));
            
            }
                        
            if(Urls.size() > 0){                
                props = GetPropertiesRecord(pSvc, searchIn, rec_manger_id, regionId, regionName, Urls, vendortype);
            }
            
        }
        
        return props;
    }
    
    private ArrayList<PropertyUrls> getAllLinks(PropertySvcI pSvc, String searchType, String searchCat, String locationName) {
        ArrayList<PropertyUrls> Urls = new ArrayList<>();
        String Url = "https://www.anibis.ch/fr/" + searchType + "/advertlist.aspx?aidl=" + searchCat +  "&loc=" + locationName;
        Document doc;
        
        HttpHeaders headersData = new HttpHeaders();
        headersData.add("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.116 Safari/537.36");
        
        String response = "";
            
            try{
                
                this.proxyVar = setupProxy();
                
                response = errorProneWebRequests(Url, HttpMethod.GET, null, headersData, null);
                
                if(response != null && response.isEmpty() == false){
                    
                    doc = Jsoup.parse(response);
                    response = doc.select("script[id=state]").html();
                    
                    ArrayList<String> links = getUrlsFromResp(response);
                    
                    if(links.isEmpty() == false){
                        
                        for(String el : links){
                                                        
                            String url = el;
                            
                            String url_ = getPropertyId(url);
                            
                            final String _url = url;
                            
                            if((url.isEmpty() == false && url.length() > 0) && (pSvc.getPostbyIdAndWebSite(url_, "anibis.ch") <= 0) && (Urls.stream().filter(a -> a.purls.equals(_url)).collect(Collectors.toList()).size() <= 0)){
                                Urls.add(new PropertyUrls(url,locationName, getCategoryName(searchCat), ""));
                            }
                            
                        }
                        
                        String _totalRecords = getTotalCount(response);

                        if(_totalRecords != null && _totalRecords.length() > 0){
                                                        
                            double totalRecords = Integer.parseInt(_totalRecords);
                            
                            double totalPages = Math.ceil(totalRecords / 20);
                            
                            int tp = (int)totalPages;
                            
                            if(tp > 1){

                                for(int i = 2; i <= tp; i++){
                                    
                                    Url = "https://www.anibis.ch/fr/" + searchType + "/advertlist.aspx?aidl=" + searchCat +  "&loc=" + locationName + "&pi=" + i;
                                                                        
                                    response = errorProneWebRequests(Url, HttpMethod.GET, null, headersData, null);
                                    
                                    if(response != null || response.isEmpty() == false){
                                        
                                        doc = Jsoup.parse(response);
                                        
                                        response = doc.select("script[id=state]").html();
                                        links = getUrlsFromResp(response);
                                        
                                        if(links.isEmpty() == false){
                                            
                                            for(String el : links){
                                                        
                                                String url = el;

                                                String url_ = getPropertyId(url);

                                                final String _url = url;

                                                if((url.isEmpty() == false && url.length() > 0) && (pSvc.getPostbyIdAndWebSite(url_, "anibis.ch") <= 0) && (Urls.stream().filter(a -> a.purls.equals(_url)).collect(Collectors.toList()).size() <= 0)){
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
                System.out.println(" Anibis Get All Links Json Parsing Error => " + e.getMessage());
            }
            catch(Exception e){
                System.out.println(" Anibis Get All Links General Error => " + e.getMessage());        
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
                
                System.out.println(" In the Loop Getting Data " + UrlId.getPurls());
                    
                String response = errorProneWebRequests("https://www.anibis.ch" + URLDecoder.decode(UrlId.getPurls(), "UTF-8"), HttpMethod.GET, null, headers, null);

                if(response != null && response.isEmpty() == false){
                                        
                    doc = Jsoup.parse(response);
                    
                    Elements mainElement = doc.select("div#root");
                    
                    Elements mainScript = doc.select("script[id=state]");

                    String agencyName = "";
                    
                    String V_id = "";
                    
                    agencyName = "";
                    
                    V_id = getVendorId(mainScript.html());;
                                        
                    agencyName = GetVendorTypeVerified(vendortype, agencyName);
                                        
                    String publishDate = "";
                    
                    if((pSvc.getVendorbyIdAndWebSite(V_id, "anibis.ch") <= 0)){
                        
                        String featuresOfProperty = "";
                        
                        String updateDate = "";

                        String searchType = ST.toLowerCase().contains("buy") ? "SALE" : "RENT";

                        String msRegion = regionName;
                        
                        String catOfProperty = UrlId.getPropertyCategory();

                        String propertyType = "";
                        
                        String titleOfAdd = mainElement != null && mainElement.select("div.cahxkj-0.sc-1645p4v-0.iJmBG > h1") != null ? doc.select("div.cahxkj-0.sc-1645p4v-0.iJmBG > h1").text() : "";
                                                
                        String streetNumber = getStreetAddress(mainScript.html());
                        
                        String zipAndcity = getZipAndCity(mainScript.html());

                        String postalCode = zipAndcity.replaceAll("\\D+","");

                        String cIty = zipAndcity.replaceAll("\\d","");
                        
                        Elements rowsTopRightSide = mainElement != null && mainElement.select("ul.sc-128qeee-0 > li") != null ? mainElement.select("ul.sc-128qeee-0 > li") : null;
                        
                        String sellPrice = "";
                        
                        if(searchType.toLowerCase().contains("sale")){
                            sellPrice = getPrice(mainScript.html());
                        }

                        String sellGrossPrice = "";
                        String sellNetPrice = "";
                        String sellPaymentType = searchType;

                        int rentPrice = 0;
                        int rentGrossPrice = 0;
                        int rentNetPrice = 0;

                        if(searchType.toLowerCase().contains("rent")){
                            
                            String priceData = getPrice(mainScript.html());
     
                            rentPrice = Integer.parseInt(priceData != null && priceData.isEmpty() == false && priceData.length() > 0 ? priceData : "0");

                            rentGrossPrice = Integer.parseInt(priceData != null && priceData.isEmpty() == false && priceData.length() > 0 ? priceData : "0");
                            rentNetPrice = 0;
                            
                        }

                        String pId = getPropertyId(UrlId.getPurls());

                        String rentPaymentType = searchType;
                                                
                        String numberOfRooms = getValueFromList(rowsTopRightSide, "Pièces".toLowerCase()) != null ? getValueFromList(rowsTopRightSide, "Pièces".toLowerCase()) : "0";
                                                
                        String livingSpace = getValueFromList(rowsTopRightSide, "Surface habitable".toLowerCase()) != null ? getValueFromList(rowsTopRightSide, "Surface habitable".toLowerCase()) : "0";
                        
                        featuresOfProperty = getValueFromList(rowsTopRightSide, "Caractéristiques".toLowerCase()) != null ? getValueFromList(rowsTopRightSide, "Caractéristiques".toLowerCase()) : "";
                        
                        featuresOfProperty = featuresOfProperty.replaceAll("/", ",");
                                                
                        String propertyFloor = "";
                        String propertyArea = "";
                        
                        String propertyDesc = mainElement != null && mainElement.select("div.urtii3-0.ckoeFm") != null ? mainElement.select("div.urtii3-0.ckoeFm").text() : "";
                                                
                        String propertyAvailable = "";

                        String yearBuilt = "";
                        
                        String nameOfSeller = getNameOfSeller(mainScript.html());
                                                
                        String streetNumberOfSeller = ""; ;
                        String postalCodeOfSeller = "";
                        String cityOfSeller = "";
                        
                        String phoneOfSeller = mainElement != null && mainElement.select("div.cahxkj-0.cgy19m-0.IBByZ") != null ? doc.select("div.cahxkj-0.cgy19m-0.IBByZ").text().replaceAll("\\D+","") : "";
                                                
                        String mNumberOfSeller = "";
                                                
                        String emailOfSeller = "";
                                                
                        String contactName = "";
                        
                        String contactNumber = "";
                        
                        String urlOfAd = "https://www.anibis.ch" + UrlId.getPurls();
                        
                        ArrayList<ImageUrls> iu = new ArrayList<>();
                        
                        String imgArrayData = getImgsArrayString(mainScript.html());
                        
                        String imgBaseUrl = getImgsBaseUrl(imgArrayData);
                        
                        imgBaseUrl = imgBaseUrl.replace("\\u002F", "/");
                        
                        String imgsList = getImgsList(imgArrayData);
                        
                        String[] _imgsList = imgsList.split("\",\"");
                                                
                        for(String el : _imgsList){
                            iu.add(new ImageUrls("",el.replace("\\u002F", "/").replace("[size]", "1024x768")));
                        }
                                                
                        Date dNow = new Date( );
                        SimpleDateFormat ct = new SimpleDateFormat ("hh:mm a");
                        SimpleDateFormat cd = new SimpleDateFormat ("yyyy-MM-dd");
                        SimpleDateFormat cm = new SimpleDateFormat ("MMM");
                        SimpleDateFormat cy = new SimpleDateFormat ("yyyy");
                        
                        publishDate = cd.format(dNow);
                        updateDate = cd.format(dNow);

                        if(searchType.toLowerCase().contains("rent")){

                            Property p = new Property(0 , rec_manger_id, regionId, pId, V_id , agencyName.isEmpty() || agencyName == null ? "Private vendor / Unassigned vendor" : "Institutional vendor" , publishDate, updateDate, searchType, msRegion, catOfProperty, propertyType, titleOfAdd, streetNumber, postalCode, cIty, sellPrice, sellGrossPrice, sellNetPrice, sellPaymentType, Integer.toString(rentPrice), Integer.toString(rentGrossPrice), Integer.toString(rentNetPrice), rentPaymentType, numberOfRooms, livingSpace, propertyFloor, propertyArea, propertyDesc, featuresOfProperty, propertyAvailable, yearBuilt, nameOfSeller, agencyName, streetNumberOfSeller, postalCodeOfSeller, cityOfSeller, phoneOfSeller, mNumberOfSeller, emailOfSeller, contactName, contactNumber, "anibis.ch", urlOfAd, ct.format(dNow), cd.format(dNow), cm.format(dNow), cy.format(dNow));
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

                            Property p = new Property(0 , rec_manger_id, regionId, pId, V_id, agencyName.isEmpty() || agencyName == null ? "Private vendor / Unassigned vendor" : "Institutional vendor", publishDate, updateDate, searchType, msRegion, catOfProperty, propertyType, titleOfAdd, streetNumber, postalCode, cIty, sellPrice, sellGrossPrice, sellNetPrice, sellPaymentType, Integer.toString(rentPrice), Integer.toString(rentGrossPrice), Integer.toString(rentNetPrice), rentPaymentType, numberOfRooms, livingSpace, propertyFloor, propertyArea, propertyDesc, featuresOfProperty, propertyAvailable, yearBuilt, nameOfSeller, agencyName, streetNumberOfSeller, postalCodeOfSeller, cityOfSeller, phoneOfSeller, mNumberOfSeller, emailOfSeller, contactName, contactNumber, "anibis.ch", urlOfAd, ct.format(dNow), cd.format(dNow), cm.format(dNow), cy.format(dNow));
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

                            Property p = new Property(0 , rec_manger_id, regionId, pId, V_id , agencyName.isEmpty() || agencyName == null ? "Private vendor / Unassigned vendor" : "Institutional vendor" , publishDate, updateDate, searchType, msRegion, catOfProperty, propertyType, titleOfAdd, streetNumber, postalCode, cIty, sellPrice, sellGrossPrice, sellNetPrice, sellPaymentType, Integer.toString(rentPrice), Integer.toString(rentGrossPrice), Integer.toString(rentNetPrice), rentPaymentType, numberOfRooms, livingSpace, propertyFloor, propertyArea, propertyDesc, featuresOfProperty, propertyAvailable, yearBuilt, nameOfSeller, agencyName, streetNumberOfSeller, postalCodeOfSeller, cityOfSeller, phoneOfSeller, mNumberOfSeller, emailOfSeller, contactName, contactNumber, "anibis.ch", urlOfAd, ct.format(dNow), cd.format(dNow), cm.format(dNow), cy.format(dNow));
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
                
            }catch (NumberFormatException ex) {
                System.out.println("Anibis Single Post Number Formating Error : " + ex.getMessage() + " == " + UrlId.getPurls());
            }catch (UnsupportedEncodingException ex){
                System.out.println("Anibis Single Post Unsupported Encoding Error : " + ex.getMessage() + " == " + UrlId.getPurls());
            }catch (Exception ex) {
                System.out.println("Anibis Single Post Page Site Request Error : " + ex.getMessage() + " == " + UrlId.getPurls());
            }
            
        }
        
        return properties;
    }
    
    public String getStreetAddress(String resp){
        String str = "";
        
        if(resp != null && resp.isEmpty() == false){
            
            Pattern p = Pattern.compile("\"street\":\"(.*?)\",\"geoLocation\":");
            
            Matcher m = p.matcher(resp);
                        
            if(m.find()) {
                str = m.group(1);
            }
            
       }

        return str;
    }
    
    public String getZipAndCity(String resp){
        String str = "";
        
        if(resp != null && resp.isEmpty() == false){
            
            Pattern p = Pattern.compile(",\"zipCity\":\"(.*?)\",\"street\":\"");
            
            Matcher m = p.matcher(resp);
                        
            if(m.find()) {
                str = m.group(1);
            }
            
       }

        return str;
    }
    
    public String getPrice(String resp){
        String str = "";
        
        if(resp != null && resp.isEmpty() == false){
            
            Pattern p = Pattern.compile("\"price\":(.*?),\"formattedPrice\":");
            
            Matcher m = p.matcher(resp);
                        
            if(m.find()) {
                str = m.group(1);
            }
            
       }

        return str;
    }
    
    public String getNameOfSeller(String resp){
        String str = "";
        
        if(resp != null && resp.isEmpty() == false){
            
            Pattern p = Pattern.compile(",\"name\":\"(.*?)\",\"registrationDate\":");
            
            Matcher m = p.matcher(resp);
                        
            if(m.find()) {
                str = m.group(1);
            }
            
       }

        return str;
    }
    
    public ArrayList<String> getUrlsFromResp(String resp){
        ArrayList<String> str = new ArrayList<>();
        
        if(resp != null && resp.isEmpty() == false){
            
            Pattern p = Pattern.compile("\"url\":(.*?),\"id\":");
            
            Matcher m = p.matcher(resp);
            
            while(m.find()) {
                String _str = m.group(1);
                _str = _str.replaceAll("u002F", "/");
                _str = _str.replace("\\", "");
                _str = _str.replace("\"", "");
                str.add(_str);
            }
            
       }

        return str;
    }
    
    public String getTotalCount(String resp){
        String str = "";
        
        if(resp != null && resp.isEmpty() == false){
            
            Pattern p = Pattern.compile("\"h1Template\":(.*?),\"categoryContent\":");
            
            Matcher m = p.matcher(resp);
                        
            if(m.find()) {
                str = m.group(1).replaceAll("\\D+","");
            }
            
       }

        return str;
    }
    
    public String getPropertyId(String url){
        String str = "";
        
        if(url != null && url.isEmpty() == false){
            
            Pattern p = Pattern.compile("--\\d+.aspx");
            
            Matcher m = p.matcher(url);
            
            if(m.find()) {
                str = m.group(0).replaceAll("\\D+","");;
            }
            
       }

        return str;
    }

    public String getValueFromList(Elements elms, String key){
        String str = "";
        
        for(Element elm:elms){
            
            String _key = elm.select("div.sc-1fkfkei-0").text();
            
            if(_key.toLowerCase().contains(key)){
                str = elm.select("div.gwnqr5-0").text();
                break;
            }
            
        }
        
        return str;
    }
    
    public String getImgsArrayString(String resp){
        String str = "";
        
        if(resp != null && resp.isEmpty() == false){
            
            Pattern p = Pattern.compile(",\"imageData\":(.*?)\\},\"title\":\"");
            
            Matcher m = p.matcher(resp);
                        
            if(m.find()) {
                str = m.group(1);
            }
            
       }

        return str;
    }
    
    public String getImgsBaseUrl(String resp){
        String str = "";
        
        if(resp != null && resp.isEmpty() == false){
            
            Pattern p = Pattern.compile("\\{\"baseUrl\":\"(.*?)\",\"images\":\\[\"");
            
            Matcher m = p.matcher(resp);
                        
            if(m.find()) {
                str = m.group(1);
            }
            
       }

        return str;
    }
    
    public String getImgsList(String resp){
        String str = "";
        
        if(resp != null && resp.isEmpty() == false){
            
            Pattern p = Pattern.compile("\"images\":\\[\"(.*?)\"\\]");
            
            Matcher m = p.matcher(resp);
                        
            if(m.find()) {
                str = m.group(1);
            }
            
       }

        return str;
    }
     
    public String getCategoryName(String categoryParam){
        
        Map<String,String> categoriesData = new HashMap<>();
        
        categoriesData.put("866","Apartment");
        categoriesData.put("867","House");
        categoriesData.put("868","Park Place");
        categoriesData.put("869","Trade / Industry");
        categoriesData.put("870","Land to build");
        categoriesData.put("871","Gastronomy");
        categoriesData.put("15237","Agriculture");
        categoriesData.put("15238","Other");
        
        return categoriesData.get(categoryParam) != null ? categoriesData.get(categoryParam) : "Apartment";
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
            
            Pattern p = Pattern.compile(",\"seller\":\\{\"id\":(.*?),\"name\":\"");
            
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
        
        final String username = "";
        final String password = "";
        final String proxyUrl = "127.0.0.1";
        final int port = 21218;
        
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
        
            System.out.println("Anibis Error From Error Prone Request Normal " + ex.getMessage() + " => " + payloadData);
        
                try {

                    this.proxyVar = setupProxy();
                    
                    restTemplate = this.proxyVar;
                    
                    HttpEntity entity = new HttpEntity(formData, headersData);
                        
                    ResponseEntity<String> response = restTemplate.exchange(Url, method, entity, String.class);

                    result = response.getBody();
                    
                } catch (Exception ex1) {
                    System.out.println("Anibis Home Error From Error Prone Request Abnormal " + ex1.getMessage() + " => " + payloadData);
                }    
        }catch (Exception ex) {
        
            System.out.println("Anibis Home Error From Error Prone Request Normal " + ex.getMessage() + " => " + payloadData);
        
                try {

                    this.proxyVar = setupProxy();
                    
                    restTemplate = this.proxyVar;
                    
                    HttpEntity entity = new HttpEntity(formData, headersData);
                        
                    ResponseEntity<String> response = restTemplate.exchange(Url, method, entity, String.class);

                    result = response.getBody();
                    
                } catch (Exception ex1) {
                    System.out.println("Anibis Home Error From Error Prone Request Abnormal " + ex1.getMessage() + " => " + payloadData);
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
                
                String response = errorProneWebRequests("https://api.anibis.ch/v1/fr/geo/search?searchMode=zipOrCity&searchValue=" + cityName, HttpMethod.GET, null, headersData, null);
                
                if(response.isEmpty() == false && response != null){
                                                                                
                    JSONArray jaObj = new JSONArray(response);
                    
                    for(int i=0; i < jaObj.length(); i++){
                        JSONObject jsonobject = jaObj.getJSONObject(i);
                        if(jsonobject.getString("city").toLowerCase().contains(cityName)){
                            if(jsonobject.has("value")){
                                cityIds.add(jsonobject.getString("value"));
                                break;
                            }
                        }  
                    }
                }
                
        }
        catch(JSONException e){
            
            System.out.println("Anibis City List JSON Parsing Error Exception : " + e.getMessage());
        
        }
        catch(Exception e){
        
            System.out.println("Anibis City List Requesting Error Exception : " + e.getMessage());
        
        }
        
        return cityIds;
    }    
    
}
