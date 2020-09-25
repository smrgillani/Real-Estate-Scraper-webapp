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
import java.util.regex.Matcher;
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
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author Smr
 */
public class ImmoStreet {
    private RestTemplate proxyVar;
        
    public ArrayList<PropertyListJSON> GetTotalResultsCount(PropertySvcI pSvc, String rec_manger_id, int regionId, String searchIn, String cityName, String regionName, String searchCat){
                
        ArrayList<PropertyListJSON> props = new ArrayList<>();
            
        ArrayList<PropertyUrls> Urls = new ArrayList<>();
        
            try{
                                
                this.proxyVar = setupProxy();
                
                String totalPostsCount = countLocationAndCategoryposts(cityName, searchCat, searchIn);
                
                double totalRecords = Double.parseDouble((totalPostsCount.isEmpty() ? "0" : totalPostsCount));
                                
                if(totalRecords > 0){
                    
                    double totalPages = Math.ceil(totalRecords / 20);

                    int tp = (int)totalPages;
                    
                    if(tp > 1){

                        for(int i = 1; i <= tp; i++){
                            Urls.addAll(getAllLinks(pSvc, searchIn, searchCat, cityName, i));
                        }

                    }else{

                        Urls.addAll(getAllLinks(pSvc, searchIn, searchCat, cityName, 1));
                    }
                    
                }
            }
            catch(IOException e){
                System.out.println("ImmoStreet Start Initializing IO Exception " + e.getMessage());
            }
            catch(Exception e){
                System.out.println("ImmoStreet Start Initializing Exception " + e.getMessage());
            }

            if(Urls.size() > 0){
                props = GetPropertiesRecord(pSvc, searchIn, rec_manger_id, regionId, regionName, Urls);
            }
        
        return props;
    }
    
    private ArrayList<PropertyUrls> getAllLinks(PropertySvcI pSvc, String searchType, String propertyCategory, String cityName, int pageNumber){
        ArrayList<PropertyUrls> Urls = new ArrayList<>();
        String Url = "https://www.immostreet.ch/de/" + (searchType.toLowerCase().equals("rent") ? "mieten" : "kaufen") + "/"+ getCategoryNameUrlParam(propertyCategory) +"/in-" + cityName + "/" + (pageNumber > 1 ? "?pageNum=" + pageNumber : "");
                
        HttpHeaders headersData = new HttpHeaders();
        headersData.add("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9");
        headersData.add("Accept-Language","en-US,en;q=0.9");
        headersData.add("sec-fetch-dest","document");
        headersData.add("sec-fetch-mode","navigate");
        headersData.add("sec-fetch-site","none");
        headersData.add("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.132 Safari/537.36");
        
        String response = errorProneWebRequests(Url, HttpMethod.GET, null, headersData, null);
        
                if(response != null || response.isEmpty() == false){                    
                    
                    Document doc = Jsoup.parse(response);
                    
                    Elements allUrls = doc.select("section.content").select("article.results-item").select("a.link");
                                        
                    for (Element link : allUrls) {
                        
                        String url = link.attr("href");
                                                
                        String propertyId = url.length() > 0 ? getPropertyId(url) : "";
                        
                        if(propertyId != null && propertyId.isEmpty() == false){
                            
                            if((pSvc.getPostbyIdAndWebSite(propertyId, "immostreet.ch") <= 0) && (Urls.stream().filter(a -> a.purls.equals(url)).collect(Collectors.toList()).size() <= 0)){
                                Urls.add(new PropertyUrls(url,cityName, "", ""));
                            }
                            
                        }
                    }
                }
                
            System.out.println(Url);
            System.out.println(cityName + " => : " + Urls.size());
        return Urls;
    }
    
        private ArrayList<PropertyListJSON> GetPropertiesRecord(PropertySvcI pSvc, String ST, String rec_manger_id, int regionId, String regionName, ArrayList<PropertyUrls> urls){
        ArrayList<PropertyListJSON> properties = new ArrayList<>();
        Document doc;
        
        HttpHeaders headersData = new HttpHeaders();
        headersData.add("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9");
        headersData.add("Accept-Language","en-US,en;q=0.9");
        headersData.add("sec-fetch-dest","document");
        headersData.add("sec-fetch-mode","navigate");
        headersData.add("sec-fetch-site","none");
        headersData.add("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.132 Safari/537.36");
        
        Elements elms;
        
        for(PropertyUrls UrlId:urls){
                      
            try{
                
                String Url = "https://www.immostreet.ch" + UrlId.getPurls();
                
                String response = errorProneWebRequests(Url, HttpMethod.GET, null, headersData, null);
                
                if(response != null && response.isEmpty() == false){
                    
                    doc = Jsoup.parse(response);
                    
                    String propertyEnUrl = doc.select("a[data-gtm-id='header-language-change-to-en']").attr("href");
                    
                    String getTextForVenId = doc.select("a[data-gtm-id='detail-contact-show-all-ads-of-this-contact']").attr("href");

                    String V_id = (getTextForVenId != null && getTextForVenId.equals("") == false) ? StringUtils.substringBetween(getTextForVenId, "clientid-", "?sort=") : "";
                    
                    elms = doc.select("div#detail-contact");
                    
                    elms = elms != null ? elms.select("address.company-address") : null;
                    
                    String agencyName = elms.select("span.company") != null ? elms.select("span.company").text() : "";
                    
                    String publishDate = "";
                    
                    if((pSvc.getVendorbyIdAndWebSite(V_id, "immostreet.ch") <= 0)){

                        String resp = errorProneWebRequests("https://www.immostreet.ch" + propertyEnUrl, HttpMethod.GET, null, headersData, null);
                    
                        if(resp != null && resp.isEmpty() == false){
                            
                            doc = Jsoup.parse(resp);
                        
                            String featuresOfProperty = "";
                            
                            elms = doc.select("div.detail-features");
                            
                            elms = elms != null ? elms.select("ul.features") : null;

                            Elements allFeatures = (elms.select("li") != null ? elms.select("li") : null);

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
                            
                            
                            elms = doc.select("section.content");
                            
                            elms = elms != null ? elms.select("header.detail-box.detail-header") : null;
                            
                            elms = elms != null ? elms.select("div.header") : null;
                            
                            String propertyType = (elms.select("span.category") != null ? elms.select("span.category").text() : "");
                            
                            
                            elms = doc.select("div.detail-box.detail-summary");
                            
                            elms = elms != null ? elms.select("div#detail-description") : null;

                            String titleOfAdd = (elms.select("h2.title") != null ? elms.select("h2.title").text() : "");
                            
                            
                            elms = doc.select("li[data-gtm-id='detail-google-maps-link']");

                            String streetNumber = (elms.select("span.info") != null ? elms.select("span.info").text() : "");

                            
                            String postalCode = (doc.select("li[data-gtm-id='detail-google-maps-link']") != null ? doc.select("li[data-gtm-id='detail-google-maps-link']").text() : "");
                            
                            postalCode = StringUtils.substringAfterLast(postalCode, ",").replaceAll("\\D+","");
                            
                            
                            String cIty = (doc.select("li[data-gtm-id='detail-google-maps-link']") != null ? doc.select("li[data-gtm-id='detail-google-maps-link']").text() : "");
                            
                            cIty = StringUtils.substringAfterLast(cIty, ",").replaceAll("\\d","");
                            
                            
                            String sellPrice = "";

                            if(searchType.toLowerCase().contains("sale")){
                                
                                elms = doc.select("div.detail-prices");
                                
                                elms = elms != null ? elms.select("ul.properties.detail-properties") : null;
                                
                                String priceData = (elms.select("li:nth-child(1)") != null ? elms.select("li:nth-child(1)").text().replaceAll("\\D+","") : "0");
                                
                                sellPrice = priceData.isEmpty() == false && priceData != null && priceData.length() > 0 ? priceData: "0";
                            }

                            String sellGrossPrice = "";
                            String sellNetPrice = "";
                            String sellPaymentType = searchType;

                            int rentPrice = 0;
                            int rentGrossPrice = 0;
                            int rentNetPrice = 0;

                            if(searchType.toLowerCase().contains("rent")){
                                
                                elms = doc.select("div.detail-prices");
                                
                                elms = elms != null ? elms.select("ul.properties.detail-properties") : null;
                                
                                String _priceData = (elms.select("li:nth-child(1)") != null ? elms.select("li:nth-child(1)").select("span.value").text().replaceAll("\\D+","") : "");
                                
                                double priceData = Double.parseDouble(_priceData.isEmpty() == false && _priceData != null && _priceData.length() > 0 ? _priceData : "0");

                                rentPrice = (int)priceData;
                                
                                _priceData = (elms.select("li:nth-child(2)") != null ? elms.select("li:nth-child(2)").select("span.value").text().replaceAll("\\D+","") : ""); 
                                priceData = Double.parseDouble(_priceData.isEmpty() == false && _priceData != null && _priceData.length() > 0 ? _priceData : "0");

                                rentGrossPrice = rentPrice;
                                rentNetPrice = (int)priceData;
                                
                            }

                            String pId = (doc.select("section.content") != null ? doc.select("section.content").attr("data-ad-id") : "");

                            String rentPaymentType = searchType;
                            
                            Elements detailAttributes = doc.select("div.detail-attributes");
                            detailAttributes = detailAttributes != null ? detailAttributes.select("ul.properties.detail-properties") : null;
                            detailAttributes = detailAttributes != null ? detailAttributes.select("li") : null;
                            
                            String numberOfRooms = getValueFromList(detailAttributes, "rooms");
                            String livingSpace = getValueFromList(detailAttributes, "living space");
                            String propertyFloor = getValueFromList(detailAttributes, "floor");
                            String propertyArea = "";
                            
                            elms = doc.select("div.detail-box.detail-summary");
                            
                            elms = elms != null ? elms.select("div#detail-description") : null;
                            
                            String propertyDesc = (elms.select("div.description") != null ? elms.select("div.description").text() : "");
                            String propertyAvailable = getValueFromList(detailAttributes, "available from");

                            try{

                                if(propertyAvailable != null && propertyAvailable.isEmpty() == false){
                                    
                                    SimpleDateFormat adDateFormatter = new SimpleDateFormat("dd.MM.yyyy");
                                    Date date = adDateFormatter.parse(propertyAvailable);

                                    SimpleDateFormat cd_ = new SimpleDateFormat ("yyyy-MM-dd");
                                    propertyAvailable = cd_.format(date);
                                    
                                }

                            }catch (ParseException ex) {

                                System.out.println("Immo Street Single Post Page Site Parse Date Error : " + propertyAvailable);
                                propertyAvailable = "";

                            }

                            String yearBuilt = getValueFromList(detailAttributes, "year built");
                            
                            elms = doc.select("section.content");
                            
                            elms = elms != null ? elms.select("div#detail-contact") : null;
                            
                            elms = elms != null ? elms.select("div.detail-agency.detail-curtain") : null;
                            
                            Elements sellerInfo = elms != null ? elms.select("address.company-address.address") : null;
                            
                            Element sellerName = sellerInfo.select("span ~ *").eq(0).first() != null && sellerInfo.select("span ~ *").eq(2).first() != null ? sellerInfo.select("span ~ *").eq(0).first() : null;

                            Node _sellerName = sellerName != null ? sellerName.nextSibling() : null;
                            
                            Element sellerStreetAddr = sellerInfo.select("span ~ *").eq(1).first() != null && sellerInfo.select("span ~ *").eq(2).first() != null ? sellerInfo.select("span ~ *").eq(1).first() : ( sellerInfo.select("span ~ *").eq(0).first() != null && sellerInfo.select("span ~ *").eq(1).first() != null ? sellerInfo.select("span ~ *").eq(0).first() : null);

                            Node _sellerStreetAddr = sellerStreetAddr != null ? sellerStreetAddr.nextSibling() : null;
                            
                            Element sellerPostalAndCity = sellerInfo.select("span ~ *").eq(2).first() != null ? sellerInfo.select("span ~ *").eq(2).first() : (sellerInfo.select("span ~ *").eq(1).first() != null ? sellerInfo.select("span ~ *").eq(1).first() : (sellerInfo.select("span ~ *").eq(0).first() != null ? sellerInfo.select("span ~ *").eq(0).first() : null) );

                            Node _sellerPostalAndCity = sellerPostalAndCity != null ? sellerPostalAndCity.nextSibling() : null;
                            
                            String nameOfSeller = _sellerName != null  ? _sellerName.toString() : "";
                            String streetNumberOfSeller = _sellerStreetAddr != null ? _sellerStreetAddr.toString() : "";
                            String postalCodeOfSeller = _sellerPostalAndCity != null ? getSellerPostalCode(_sellerPostalAndCity.toString()) : "";
                            String cityOfSeller = _sellerPostalAndCity != null ? _sellerPostalAndCity.toString().replaceAll("\\d", "") : "";
                            
                            String phoneOfSeller = (elms.select("div[data-gtm-id='detail-contact-show-phone'] > a") != null ? elms.select("div[data-gtm-id='detail-contact-show-phone'] > a").text() : "");
                            
                            String mNumberOfSeller = (elms.select("div[data-gtm-id='detail-contact-show-mobile-phone'] > a") != null ? elms.select("div[data-gtm-id='detail-contact-show-mobile-phone'] > a").text() : "");

                            String emailOfSeller = "";
                            
                            elms = doc.select("section.content");
                            
                            elms = elms != null ? elms.select("div#detail-contact") : null;
                            
                            elms = elms != null ? elms.select("div.detail-visit.detail-curtain") : null;
                            
                            String contactName = (elms.select("p.name") != null ? elms.select("p.name").text() : "");
                            String contactNumber = (elms.select("div[data-gtm-id='detail-visit-show-phone'] > a") != null ? elms.select("div[data-gtm-id='detail-visit-show-phone'] > a").text() : "");

                            String urlOfAd = "https://www.immostreet.ch" + UrlId.getPurls();

                            ArrayList<ImageUrls> iu = new ArrayList<>();
                            
                            elms = doc.select("section.content");
                            
                            elms = elms != null ? elms.select("header.detail-box.detail-header") : null;
                            
                            Elements allImgUrls = (elms.select("div.slide") != null ? elms.select("div.slide") : null);
                            
                            if(allImgUrls != null && allImgUrls.size() > 0){
                                allImgUrls.forEach((link) -> {
                                    iu.add(new ImageUrls("",link.select("img").attr("data-image-src")));
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

                                Property p = new Property(0 , rec_manger_id, regionId, pId, V_id , agencyName.isEmpty() || agencyName == null ? "Private vendor / Unassigned vendor" : "Institutional vendor" , publishDate, updateDate, searchType, msRegion, catOfProperty, propertyType, titleOfAdd, streetNumber, postalCode, cIty, sellPrice, sellGrossPrice, sellNetPrice, sellPaymentType, Integer.toString(rentPrice), Integer.toString(rentGrossPrice), Integer.toString(rentNetPrice), rentPaymentType, numberOfRooms, livingSpace, propertyFloor, propertyArea, propertyDesc, featuresOfProperty, propertyAvailable, yearBuilt, nameOfSeller, agencyName, streetNumberOfSeller, postalCodeOfSeller, cityOfSeller, phoneOfSeller, mNumberOfSeller, emailOfSeller, contactName, contactNumber, "immostreet.ch", urlOfAd, ct.format(dNow), cd.format(dNow), cm.format(dNow), cy.format(dNow));
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

                                Property p = new Property(0 , rec_manger_id, regionId, pId, V_id, agencyName.isEmpty() || agencyName == null ? "Private vendor / Unassigned vendor" : "Institutional vendor", publishDate, updateDate, searchType, msRegion, catOfProperty, propertyType, titleOfAdd, streetNumber, postalCode, cIty, sellPrice, sellGrossPrice, sellNetPrice, sellPaymentType, Integer.toString(rentPrice), Integer.toString(rentGrossPrice), Integer.toString(rentNetPrice), rentPaymentType, numberOfRooms, livingSpace, propertyFloor, propertyArea, propertyDesc, featuresOfProperty, propertyAvailable, yearBuilt, nameOfSeller, agencyName, streetNumberOfSeller, postalCodeOfSeller, cityOfSeller, phoneOfSeller, mNumberOfSeller, emailOfSeller, contactName, contactNumber, "immostreet.ch", urlOfAd, ct.format(dNow), cd.format(dNow), cm.format(dNow), cy.format(dNow));
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

                                Property p = new Property(0 , rec_manger_id, regionId, pId, V_id , agencyName.isEmpty() || agencyName == null ? "Private vendor / Unassigned vendor" : "Institutional vendor" , publishDate, updateDate, searchType, msRegion, catOfProperty, propertyType, titleOfAdd, streetNumber, postalCode, cIty, sellPrice, sellGrossPrice, sellNetPrice, sellPaymentType, Integer.toString(rentPrice), Integer.toString(rentGrossPrice), Integer.toString(rentNetPrice), rentPaymentType, numberOfRooms, livingSpace, propertyFloor, propertyArea, propertyDesc, featuresOfProperty, propertyAvailable, yearBuilt, nameOfSeller, agencyName, streetNumberOfSeller, postalCodeOfSeller, cityOfSeller, phoneOfSeller, mNumberOfSeller, emailOfSeller, contactName, contactNumber, "immostreet.ch", urlOfAd, ct.format(dNow), cd.format(dNow), cm.format(dNow), cy.format(dNow));
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
                
            } 
            catch (NumberFormatException ex) {
                System.out.println("Immo Street Single Post Page Site Request Number Format Exception Error : " + ex.getMessage());
            }
            catch (Exception ex) {
                System.out.println("Immo Street Single Post Page Site Request Error : " + ex.getMessage());
            }
            
        }
        
        return properties;
    }
        
    public String getValueFromList(Elements elms, String key){
        String str = "";
        
        for(Element elm:elms){
            String _key = elm.select("span.key").text();
            
            if(_key.toLowerCase().contains(key)){
                str = elm.select("span.value").text();
                break;
            }
            
        }
        
        return str;
    }
    
    public String getSellerPostalCode(String data){
        String str = "";
        
        if(data != null && data.isEmpty() == false){
            
            Pattern p = Pattern.compile("\\d+");
            
            Matcher m = p.matcher(data);
            
            if(m.find()) {
                str = m.group(0);
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
    
    public String getCategoryNameUrlParam(String categoryParam){
        
        Map<String,String> categoriesData = new HashMap<>();
        
        categoriesData.put("apartment-and-house","wohnung-und-haus");
        categoriesData.put("apartment","wohnung");
        categoriesData.put("house","haus");
        categoriesData.put("building-plot","bauland");
        categoriesData.put("commercial-and-residential","wohn-und-geschaeftshaus");
        categoriesData.put("multi-family-house","mehrfamilienhaus");
        categoriesData.put("parking-place","parkplatz");
        categoriesData.put("trade-industry","gewerbe-industrie");
        categoriesData.put("furnished-flat","moebliertes-wohnobjekt");
        categoriesData.put("offices","buero");
        categoriesData.put("storage","lager");
        
        return categoriesData.get(categoryParam) != null ? categoriesData.get(categoryParam) : "wohnung-und-haus";
    }
    
    public String getPropertyId(String url){
        String str = "";
        
        if(url != null && url.isEmpty() == false){
            
            Pattern p = Pattern.compile("/\\d+/");
            
            Matcher m = p.matcher(url);
            
            if(m.find()) {
                str = m.group(0);
                str = str != null && str.isEmpty() == false ? str.replaceAll("/","") : str;
            }
            
       }

        return str;
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
        
            System.out.println("Immo Street Error From Error Prone Request Normal " + ex.getMessage() + " => " + Url);
        
                try {

                    this.proxyVar = setupProxy();
                    
                    restTemplate = this.proxyVar;
                    
                    HttpEntity entity = new HttpEntity(formData, headersData);
                        
                    ResponseEntity<String> response = restTemplate.exchange(Url, method, entity, String.class);

                    result = response.getBody();
                    
                } catch (Exception ex1) {
                    System.out.println("Immo Street Error From Error Prone Request Abnormal " + ex1.getMessage() + " => " + Url);
                }    
        }
        
        return (result != null ? result : null);
    }
    
    public String countLocationAndCategoryposts(String cityName, String categoryName, String searchType){
        
        String str = "0";
        
        String url = "https://immostreet.ch/en/api/search-ajax/" + searchType.toLowerCase();
        
        HttpHeaders headersData = new HttpHeaders();
        headersData.add("Accept","application/json, text/javascript, */*; q=0.01");
        headersData.add("Accept-Language","en-US,en;q=0.9");
        headersData.add("content-type","application/x-www-form-urlencoded");
        headersData.add("origin","https://immostreet.ch");
        headersData.add("Referer","https://immostreet.ch/en/");
        headersData.add("sec-fetch-dest","empty");
        headersData.add("sec-fetch-mode","cors");
        headersData.add("sec-fetch-site","same-origin");
        headersData.add("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.132 Safari/537.36");
        headersData.add("x-requested-with","XMLHttpRequest");
        headersData.add("Connection","keep-alive");
        
        
        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("coords","");
        formData.add("coords-placeholder","[My Location]");
        formData.add("postal_code","");
        formData.add("locationType","place");
        formData.add("locationTypeLabel","City");
        formData.add("locationUrlNames","[{\"language\":\"en\",\"name\":\"" + cityName + "\"}]");
        formData.add("locationName",cityName);
        formData.add("suggestion-selected","true");
        formData.add("urlParameters","");
        formData.add("category",categoryName);
        formData.add("search_query",cityName);
        formData.add("category",categoryName);
        formData.add("subcategory","");
        formData.add("price_from","");
        formData.add("price_to","");
        formData.add("rooms_from","");
        formData.add("rooms_to","");
        formData.add("surface_from","");
        formData.add("surface_to","");
        formData.add("surfaceproperty_from","");
        formData.add("surfaceproperty_to","");
        formData.add("surfaceusable_from","");
        formData.add("surfaceusable_to","");
        formData.add("floor","");
        formData.add("distance","");
        formData.add("advertisementId","");
        formData.add("changed_attribute","search_query");
                
        try{
            
                String response = errorProneWebRequests(url, HttpMethod.POST, formData, headersData, null);
                                
                if(response != null && response.isEmpty() == false){
                    JSONObject jObj = new JSONObject(response);
                    
                    jObj = jObj.has("data") ? jObj.getJSONObject("data") : new JSONObject();
                                                            
                    str = jObj.has("resultCount") ? Integer.toString(jObj.getInt("resultCount")) : "0";                    
                }
        
        }catch(JSONException e){
            System.out.println("Immo Street Post Count JSON Parse Error : " + e.getMessage());
        }catch(Exception e){
            System.out.println("Immo Street Post Count Http Request Error : " + e.getMessage());
        }

        return str;
    }
}
