/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.tmrmswebscrapper.Models;

import com.example.tmrmswebscrapper.Svc.PropertySvcI;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
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
import org.json.JSONArray;
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
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

/**
 *
 * @author Smr
 */
public class Comparis {
    
    private RestTemplate proxyVar;
    
    public ArrayList<PropertyListJSON> GetTotalResultsCount(PropertySvcI pSvc, String rec_manger_id, int regionId, String searchIn, String cityName, String regionName, String searchCat, ArrayList<String> vendortype){
                
        ArrayList<PropertyListJSON> props = new ArrayList<>();
                
        ArrayList<PropertyUrls> Urls = new ArrayList<>();
        
            try{
                
                this.proxyVar = setupProxy();
                
                String totalPostsCount = countProperties(cityName, (searchIn.toLowerCase().contains("buy") ? "20" : "10"));

                double totalRecords = Double.parseDouble((totalPostsCount.isEmpty() ? "0" : totalPostsCount));

                if(totalRecords > 0){

                    Urls.addAll(getAllLinks(pSvc, (searchIn.toLowerCase().contains("buy") ? "20" : "10"), cityName, 0));

                }
            }catch(IOException e){
                System.out.println("Comparis Entering in Main Method IO Exception " + e.getMessage());
            }catch(Exception e){
                System.out.println("Comparis Entering in Main Method Exception " + e.getMessage());
            }

        if(Urls.size() > 0){
            props = GetPropertiesRecord(pSvc, searchIn, rec_manger_id, regionId, regionName, Urls, vendortype);
        }
        
        return props;
    }
    
    private ArrayList<PropertyUrls> getAllLinks(PropertySvcI pSvc, String searchType, String cityName, int pageNumber) {
        ArrayList<PropertyUrls> Urls = new ArrayList<>();
        
        final String Url = "https://en.comparis.ch/immobilien/api/v1/singlepage/resultitems?requestObject={\"Header\":{\"Language\":\"en\"},\"SearchParams\":{\"DealType\":" + searchType + ",\"SiteId\":0,\"RootPropertyTypes\":[],\"PropertyTypes\":[],\"RoomsFrom\":null,\"RoomsTo\":null,\"FloorSearchType\":0,\"LivingSpaceFrom\":null,\"LivingSpaceTo\":null,\"PriceFrom\":null,\"PriceTo\":null,\"ComparisPointsMin\":0,\"AdAgeMax\":0,\"AdAgeInHoursMax\":null,\"Keyword\":\"\",\"WithImagesOnly\":false,\"WithPointsOnly\":null,\"Radius\":null,\"MinAvailableDate\":null,\"MinChangeDate\":\"1753-01-01T00:00:00\",\"LocationSearchString\":\"" + cityName + "\",\"Sort\":11,\"HasBalcony\":false,\"HasTerrace\":false,\"HasFireplace\":false,\"HasDishwasher\":false,\"HasWashingMachine\":false,\"HasLift\":false,\"HasParking\":false,\"PetsAllowed\":false,\"MinergieCertified\":false,\"WheelchairAccessible\":false,\"LowerLeftLatitude\":null,\"LowerLeftLongitude\":null,\"UpperRightLatitude\":null,\"UpperRightLongitude\":null},\"Page\":" + pageNumber + " }";

        HttpHeaders headersData = new HttpHeaders();
        headersData.add("Accept","*/*");
//        headersData.add("Accept-Encoding","gzip, deflate, br");
        headersData.add("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36");
        headersData.add("Host","en.comparis.ch");
        headersData.add("Sec-Fetch-Dest","empty");
        headersData.add("Sec-Fetch-Mode","cors");
        headersData.add("Sec-Fetch-Site","same-origin");
        headersData.add("Connection","keep-alive");
        
            String response = errorProneWebRequests(Url, HttpMethod.GET, headersData, null);

                if(response != null || response.isEmpty() == false){
                                        
                    JSONObject jObj = new JSONObject(response);

                    if(jObj.has("AdIdList")){

                        JSONArray jsonarray = jObj.getJSONArray("AdIdList");

                        for(int i=0; i < jsonarray.length(); i++){
                            String url = jsonarray.getBigInteger(i).toString();
                            
                            if((url != null && url.isEmpty() == false && url.length() > 0) && (pSvc.getPostbyIdAndWebSite(url, "comparis.ch") <= 0) && (Urls.stream().filter(a -> a.purls.equals(url)).collect(Collectors.toList()).size() <= 0)){
                                Urls.add(new PropertyUrls(url,cityName, "", ""));
                            }

                        }
                        
                    }

                }


                System.out.println(Url);
                System.out.println(cityName + " => : " + Urls.size());
            
        return Urls;
    }
        
    private ArrayList<PropertyListJSON> GetPropertiesRecord(PropertySvcI pSvc, String ST, String rec_manger_id, int regionId, String regionName, ArrayList<PropertyUrls> urls, ArrayList<String> vendortype){
        ArrayList<PropertyListJSON> properties = new ArrayList<>();
        Document doc;
        
        HttpHeaders headersData = new HttpHeaders();
        headersData.add("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9");
//        headersData.add("accept-encoding","gzip, deflate, br");
        headersData.add("Accept-Language","en-US,en;q=0.9");
        headersData.add("Accept-Language","en-US,en;q=0.9");
        headersData.add("sec-fetch-dest","document");
        headersData.add("sec-fetch-mode","navigate");
        headersData.add("sec-fetch-site","none");
        headersData.add("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.132 Safari/537.36");
        
        Elements elms;
        Element elm;
        
        for(PropertyUrls UrlId:urls){
                        
            try{
                
                String Url = "https://en.comparis.ch/immobilien/marktplatz/details/show/" + UrlId.getPurls();
                
                String response = errorProneWebRequests(Url, HttpMethod.GET, headersData, null);
                
                if(response != null && response.isEmpty() == false){
                    
                    doc = Jsoup.parse(response);
                    
                    Elements agencyInfo = doc.select("section.content-section");
                    
                    agencyInfo = (agencyInfo != null ? agencyInfo.select("div#div_Description ~ div.show-for-small.show-for-medium") : null);
                    
                    agencyInfo = (agencyInfo != null ? agencyInfo.select("div.contact-info") : null);
                    
                    agencyInfo = (agencyInfo != null ? agencyInfo.select("span") : null);
                    
                    Elements agencyInfoContacts = (agencyInfo != null ? agencyInfo.select("span[data-wt-fa-label='Click - Phone Call']") : null);
                    
                    String V_id = (agencyInfo != null && agencyInfo.size() > 0 ? ( agencyInfo.eq((agencyInfo.size() > 3 ? 1 : ( agencyInfo.size() > 2 ? 0 : 0 ))).text() != null ? agencyInfo.eq((agencyInfo.size() > 3 ? 1 : ( agencyInfo.size() > 2 ? 0 : 0 ))).text() : "") : "");
                                        
                    String agencyName = (V_id != null && V_id.isEmpty() == false && V_id.length() > 0 ? V_id : "");
                                        
                    String publishDate = "";
                    
                    if((pSvc.getVendorbyIdAndWebSite(V_id, "comparis.ch") <= 0)){
                        
                            String featuresOfProperty = "";
                            
                            String updateDate = "";

                            String searchType = ST.toLowerCase().contains("buy") ? "SALE" : "RENT";

                            String msRegion = regionName;
                            
                            Elements attrElms = doc.select("section.content-section");
                    
                            attrElms = (attrElms != null ? attrElms.select("dl.attributes-grid") : null);

                            attrElms = (attrElms != null ? attrElms.select("div.column") : null);

                            String catOfProperty = getValueFromList(attrElms, "property type");
                            
                            String propertyType = "";
                            
                            
                            elms = doc.select("section.content-section");
                            
                            elms = elms != null ? elms.select("div#gtm-tracking-container") : null;
                            elms = elms != null ? elms.select("div.title") : null;

                            String titleOfAdd = (elms.select("h2") != null ? elms.select("h2").text() : "");
                            
                            elms = doc.select("section.content-section");
                            
                            elms = elms != null ? elms.select("div#gtm-tracking-container") : null;
                            
                            String streetNumber = (elms.select("h3.text-green") != null ? ( elms.select("h3.text-green").text().split(",").length > 0 ? elms.select("h3.text-green").text().split(",")[0] : elms.select("h3.text-green").text() ) : "");

                            String postalCode = (elms.select("h3.text-green") != null ? ( elms.select("h3.text-green").text().split(",").length > 1 ? elms.select("h3.text-green").text().split(",")[1] : elms.select("h3.text-green").text() ) : "");
                            
                            postalCode = postalCode.replaceAll("\\D+","");
                            
                            String cIty = (elms.select("h3.text-green") != null ? ( elms.select("h3.text-green").text().split(",").length > 1 ? elms.select("h3.text-green").text().split(",")[1] : elms.select("h3.text-green").text() ) : "");
                            
                            cIty = cIty.replaceAll("\\d","");
                                                        
                            String sellPrice = "";

                            if(searchType.toLowerCase().contains("sale")){                                
                                sellPrice = getValueFromList(attrElms, "purchase price").replaceAll("\\D+","");
                            }
                            
                            String sellGrossPrice = "";
                            String sellNetPrice = "";
                            String sellPaymentType = searchType;

                            int rentPrice = 0;
                            int rentGrossPrice = 0;
                            int rentNetPrice = 0;

                            if(searchType.toLowerCase().contains("rent")){
                                
                                String _priceData = getValueFromList(attrElms, "rent per month").replaceAll("\\D+","");
                                
                                double priceData = Double.parseDouble(_priceData.isEmpty() == false && _priceData != null && _priceData.length() > 0 ? _priceData : "0");

                                rentPrice = (int)priceData;
                                
                                _priceData = getValueFromList(attrElms, "rent per month (without charges)").replaceAll("\\D+",""); 
                                priceData = Double.parseDouble(_priceData.isEmpty() == false && _priceData != null && _priceData.length() > 0 ? _priceData : "0");

                                rentGrossPrice = rentPrice;
                                rentNetPrice = (int)priceData;
                                
                            }
                            
                            String pId = UrlId.getPurls();

                            String rentPaymentType = searchType;
                            
                            String numberOfRooms = getValueFromList(attrElms, "rooms");
                            String livingSpace = getValueFromList(attrElms, "living space");
                            String propertyFloor = getValueFromList(attrElms, "floor").replaceAll("\\D+","");
                            String propertyArea = "";
                            
                            elms = doc.select("section.content-section");
                                                        
                            String propertyDesc = (elms.select("div#div_Description") != null ? elms.select("div#div_Description").text() : "");
                            String propertyAvailable = getValueFromList(attrElms, "available");

                            try{

                                if(propertyAvailable != null && propertyAvailable.isEmpty() == false){
                                    
                                    SimpleDateFormat adDateFormatter = new SimpleDateFormat("dd/MM/yyyy");
                                    Date date = adDateFormatter.parse(propertyAvailable);

                                    SimpleDateFormat cd_ = new SimpleDateFormat ("yyyy-MM-dd");
                                    propertyAvailable = cd_.format(date);
                                    
                                }

                            }catch (ParseException ex) {

                                System.out.println("Comparis Single Post Page Site Parse Date Error : " + propertyAvailable);
                                propertyAvailable = "";

                            }

                            String yearBuilt = getValueFromList(attrElms, "year of construction");
                            
                            
                            String nameOfSeller = (agencyInfo != null && agencyInfo.size() > 0 ? ( agencyInfo.size() > 3 ? agencyInfo.eq(0).text() : "" ) : "");
                            String streetNumberOfSeller = (agencyInfo != null && agencyInfo.size() > 0 ? (agencyInfo.eq( (agencyInfo.size() > 3 ? 2 : ( agencyInfo.size() > 2 ? 1 : ( agencyInfo.size() > 1 ? 0 : -1)))).text() != null ? agencyInfo.eq( (agencyInfo.size() > 3 ? 2 : ( agencyInfo.size() > 2 ? 1 : ( agencyInfo.size() > 1 ? 0 : -1)))).text() : "")  : "");
                            String postalCodeOfSeller = agencyInfo != null && agencyInfo.size() > 0 ? getSellerPostalCode(agencyInfo.eq(agencyInfo.size() - 1).text()) : "";
                            String cityOfSeller = agencyInfo != null && agencyInfo.size() > 0 ? agencyInfo.eq(agencyInfo.size() - 1).text().replaceAll("\\d", "") : "";
                            
                            String phoneOfSeller = agencyInfoContacts != null ? agencyInfoContacts.eq(0).text() : "";
                            
                            String mNumberOfSeller = agencyInfoContacts != null ? (agencyInfoContacts.size() > 1 ? agencyInfoContacts.eq(1).text(): "") : "";

                            String emailOfSeller = "";
                            
                            elms = doc.select("section.content-section");
                            
                            elms = elms != null ? elms.select("div#gtm-tracking-container") : null;
                            
                            elms = elms != null ? elms.select("div[data-btm-anchor='stickContactFormAnchorBottom:bottom']") : null;
                            
                            elms = elms == null ? elms.select("div[data-top-anchor='stickContactFormAnchorTop']") : elms;
                            
                            Elements contactInfos = elms != null ? elms.select("div[class='contact-info']") : null;
                            
                            String contactName = "";
                            String contactNumber = "";
                            
                            if(contactInfos.size() > 0 && contactInfos.size() == 1){
                                
                                contactName = (contactInfos.select("p.contact-tel") != null ? contactInfos.select("p:not(.contact-tel)").text() : "");
                                contactNumber = (contactInfos.select("span[data-wt-fa-label='Click - Phone Call']") != null ? elms.select("span[data-wt-fa-label='Click - Phone Call']").text() : "");
                                
                                if(contactName.contains("@")){
                                    emailOfSeller = contactName;
                                    contactName = "";
                                }
                                
                            }else if(contactInfos.size() > 1){
 
                                contactName = (contactInfos.select("p.contact-tel") != null ? (contactInfos.select("p:not(.contact-tel)").size() > 1 ? (contactInfos.select("p:not(.contact-tel)").eq(0).text() + ", " + contactInfos.select("p:not(.contact-tel)").eq(1).text()) : contactInfos.select("p:not(.contact-tel)").text()) : "");
                                contactNumber = (contactInfos.select("span[data-wt-fa-label='Click - Phone Call']") != null ? (elms.select("span[data-wt-fa-label='Click - Phone Call']").size() > 1 ? (elms.select("span[data-wt-fa-label='Click - Phone Call']").eq(0).text() + ", " +elms.select("span[data-wt-fa-label='Click - Phone Call']").eq(1).text() ) : elms.select("span[data-wt-fa-label='Click - Phone Call']").text() ) : "");
                                
                                if(contactName.contains("@")){
                                    emailOfSeller = contactName;
                                    contactName = "";
                                }
                                
                            }

                            String urlOfAd = "https://en.comparis.ch/immobilien/marktplatz/details/show/" + UrlId.getPurls();

                            ArrayList<ImageUrls> iu = new ArrayList<>();
                            
                            elms = doc.select("section.content-section");
                            
                            elms = elms != null ? elms.select("div#gtm-tracking-container") : null;
                            
                            elms = elms != null ? elms.select("div.image-gallery-combo") : null;
                                                        
                            Elements allImgUrls = (elms.select("img.sp-image") != null ? elms.select("img.sp-image") : null);
                            
                            if(allImgUrls != null && allImgUrls.size() > 0){
                                allImgUrls.forEach((link) -> {
                                    iu.add(new ImageUrls("",link.attr("data-src")));
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

                                Property p = new Property(0 , rec_manger_id, regionId, pId, V_id , agencyName.isEmpty() || agencyName == null ? "Private vendor / Unassigned vendor" : "Institutional vendor" , publishDate, updateDate, searchType, msRegion, catOfProperty, propertyType, titleOfAdd, streetNumber, postalCode, cIty, sellPrice, sellGrossPrice, sellNetPrice, sellPaymentType, Integer.toString(rentPrice), Integer.toString(rentGrossPrice), Integer.toString(rentNetPrice), rentPaymentType, numberOfRooms, livingSpace, propertyFloor, propertyArea, propertyDesc, featuresOfProperty, propertyAvailable, yearBuilt, nameOfSeller, agencyName, streetNumberOfSeller, postalCodeOfSeller, cityOfSeller, phoneOfSeller, mNumberOfSeller, emailOfSeller, contactName, contactNumber, "comparis.ch", urlOfAd, ct.format(dNow), cd.format(dNow), cm.format(dNow), cy.format(dNow));
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

                                Property p = new Property(0 , rec_manger_id, regionId, pId, V_id, agencyName.isEmpty() || agencyName == null ? "Private vendor / Unassigned vendor" : "Institutional vendor", publishDate, updateDate, searchType, msRegion, catOfProperty, propertyType, titleOfAdd, streetNumber, postalCode, cIty, sellPrice, sellGrossPrice, sellNetPrice, sellPaymentType, Integer.toString(rentPrice), Integer.toString(rentGrossPrice), Integer.toString(rentNetPrice), rentPaymentType, numberOfRooms, livingSpace, propertyFloor, propertyArea, propertyDesc, featuresOfProperty, propertyAvailable, yearBuilt, nameOfSeller, agencyName, streetNumberOfSeller, postalCodeOfSeller, cityOfSeller, phoneOfSeller, mNumberOfSeller, emailOfSeller, contactName, contactNumber, "comparis.ch", urlOfAd, ct.format(dNow), cd.format(dNow), cm.format(dNow), cy.format(dNow));
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

                                Property p = new Property(0 , rec_manger_id, regionId, pId, V_id , agencyName.isEmpty() || agencyName == null ? "Private vendor / Unassigned vendor" : "Institutional vendor" , publishDate, updateDate, searchType, msRegion, catOfProperty, propertyType, titleOfAdd, streetNumber, postalCode, cIty, sellPrice, sellGrossPrice, sellNetPrice, sellPaymentType, Integer.toString(rentPrice), Integer.toString(rentGrossPrice), Integer.toString(rentNetPrice), rentPaymentType, numberOfRooms, livingSpace, propertyFloor, propertyArea, propertyDesc, featuresOfProperty, propertyAvailable, yearBuilt, nameOfSeller, agencyName, streetNumberOfSeller, postalCodeOfSeller, cityOfSeller, phoneOfSeller, mNumberOfSeller, emailOfSeller, contactName, contactNumber, "comparis.ch", urlOfAd, ct.format(dNow), cd.format(dNow), cm.format(dNow), cy.format(dNow));
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
                            
                            //break;
                            
                    }
                    
                }
                
            } 
            catch (NumberFormatException ex) {
                System.out.println("Comparis Single Post Page Site Request Number Format Exception Error : " + ex.getMessage());
            }
            catch (Exception ex) {
                System.out.println("Comparis Single Post Page Site Request Error : " + ex.getMessage());
            }
            
        }
        
        return properties;
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
        
    public String getValueFromList(Elements elms, String key){
        String str = "";
        
        for(Element elm:elms){
            
            String _key = elm.select("dt.label-text").text();
            
            if(_key.toLowerCase().contains(key)){
                str = elm.select("dd").text();
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
    
    public String errorProneWebRequests(String Url, HttpMethod method, HttpHeaders headersData, LinkedMultiValueMap<String, String> paramsData){
               
        String result = null;
        
        RestTemplate restTemplate = null;
        
        try {
                        
            restTemplate = this.proxyVar;
            
            HttpEntity entity = new HttpEntity(headersData);
            
            URI Uri = UriComponentsBuilder.fromUriString(Url).build().encode().toUri();
            
            ResponseEntity<String> response = restTemplate.exchange(Uri, method, entity, String.class);
            
            result = response.getBody();
                        
        } catch (RestClientException ex) {
        
            System.out.println("Error From Error Prone Request Normal " + ex.getMessage()+ " => " + Url);
            
                try {

                    this.proxyVar = setupProxy();
                    
                    restTemplate = this.proxyVar;
                    
                    HttpEntity entity = new HttpEntity(headersData);
                        
                    ResponseEntity<String> response = restTemplate.exchange(Url, method, entity, String.class);

                    result = response.getBody();
                    
                } catch (Exception ex1) {
                    System.out.println("Error From Error Prone Request Abnormal " + ex1.getMessage()+ " => " + Url);
                }
            
        }
        
        return (result != null ? result : null);
    }
    
    public String countProperties(String cityName, String searchType){
        
        String str = "";
                
        try{
            
           final String url = "https://www.comparis.ch/immobilien/api/mobile/resultcount?requestObject={\"Page\":0,\"SearchCriteria\":{\"DealType\":"+ searchType +",\"LocationSearchString\":\""+ cityName + "\",\"SearchOrderKey\":3,\"SearchTrigger\":\"SearchButtonClick\",\"SearchType\":\"LocationStringSearch\"},\"Header\":{\"Language\":\"en\"}}";
            
           HttpHeaders headersData = new HttpHeaders();
           headersData.add("Accept","application/vnd.comparis.immobilien.v2+json");
//            headersData.add("Accept-Encoding","gzip");
           headersData.add("User-Agent","okhttp/3.10.0");
           headersData.add("DeviceApplicationGUID","81eb2747-495a-4139-906d-72d68fabc64e");
           headersData.add("Device","Android");
           headersData.add("Platform","Android");
           headersData.add("PlatformVersion","5.1.1");
           headersData.add("Model","HUAWEI ATH-UL01");
           headersData.add("BundleName","ch.comparis.immoapp");
           headersData.add("BundleVersion","7.10");
                        
           String response = errorProneWebRequests(url, HttpMethod.GET, headersData, null);
           
           if(response.isEmpty() == false && response != null && response.length() > 0){
               JSONObject jObj = new JSONObject(response);
               str = jObj.has("ResultCount") ? Integer.toString(jObj.getInt("ResultCount")) : "";
           }
           
        }catch(JSONException e){
            System.out.println("Comparis Count Properties Error While Parsing JSON : " + e.getMessage());
        }
        catch(Exception e){
            System.out.println("Comparis Error While getting Properties Count : " + e.getMessage());
        }

        return str;
    }
}
