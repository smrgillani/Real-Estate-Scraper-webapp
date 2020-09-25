/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.tmrmswebscrapper;

import com.example.tmrmswebscrapper.Entity.properties;
import com.example.tmrmswebscrapper.Models.Property;
import com.example.tmrmswebscrapper.Models.PropertyUrls;
import com.example.tmrmswebscrapper.Repos.PropertyRepository;
import com.example.tmrmswebscrapper.Repos.PropertyService;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.stream.Collectors;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
/**
 *
 * @author User
 */
@RestController
public class ISRestController {
    private Document doc;
    private String rec_manger_id = ""; 
    @Autowired
    private PropertyRepository propRepository;
    @Autowired
    private PropertyService propRepository_;
    
    @RequestMapping("/isrequest")
    @SuppressWarnings("empty-statement")
    public ArrayList<Property> index(@RequestParam String searchIn,@RequestParam String category, @RequestParam String cities, @RequestParam(defaultValue="") String pricefrom,@RequestParam(defaultValue="") String priceto, @RequestParam(defaultValue="") String roomfrom, @RequestParam(defaultValue="") String roomto , @RequestParam(defaultValue="") String radius, @RequestParam(defaultValue="") String type, @RequestParam(defaultValue="") String livingspacefrom, @RequestParam(defaultValue="") String livingspaceto, @RequestParam(defaultValue="") String yearbuiltfrom, @RequestParam(defaultValue="") String yearbuiltto, @RequestParam(defaultValue="") String floor, @RequestParam(defaultValue="") String available , @RequestParam(defaultValue="") String features) throws IOException{
        String doc_ = null;
        ArrayList<PropertyUrls> urls = new ArrayList<>();
        ArrayList<Property> properties = new ArrayList<>();
        ArrayList<String> cityIds = new ArrayList<>();
        
        String[] req_cities = cities.split(",");
        
        for (String name : req_cities)  
        { 
                cityIds.add(GetCityId(name));
        }
                
        String GetTotalRecordsNum = "";
        GetTotalRecordsNum = GetTotalRecordsNum(cityIds, searchIn, category, (radius != null && radius.isEmpty() == false && cities.contains(",") == false ? "&r=" + radius : "") , (pricefrom != null && pricefrom.isEmpty() == false ? "&pf=" + pricefrom : "") , (priceto != null && priceto.isEmpty() == false ? "&pt=" + priceto : "")  , (roomfrom != null && roomfrom.isEmpty() == false ? "&nrf=" + roomfrom : "")  , (roomto != null && roomto.isEmpty() == false ? "&nrt=" + roomto : "") , (type != null && type.isEmpty() == false ? "&pty=" + type : "") , (livingspacefrom != null && livingspacefrom.isEmpty() == false ? "&slf=" + livingspacefrom : "") , (livingspaceto != null && livingspaceto.isEmpty() == false ? "&slt=" + livingspaceto : "") , (floor != null && floor.isEmpty() == false ? "&fl=" + floor : "") , (available != null && available.isEmpty() == false ? "&at=" + available : ""));        
        
        if(GetTotalRecordsNum.isEmpty() == false && GetTotalRecordsNum != null){
            int total_number_of_rec = Integer.valueOf(GetTotalRecordsNum);
            if(total_number_of_rec > 0){
                if(total_number_of_rec > 24){
                    
                    double counter = Math.ceil((double)total_number_of_rec / 24);
                    int count_checker = 0;
                    System.out.println(GetTotalRecordsNum + " Total > " + counter);
                    for(int i = 1;i <= counter;i++){
                        properties.addAll(GetPropertiesRecord(i , (total_number_of_rec - count_checker > 24 ? 24 : total_number_of_rec - count_checker) , cityIds, searchIn, category, (radius != null && radius.isEmpty() == false && cities.contains(",") == false ? "&r=" + radius : "") , (pricefrom != null && pricefrom.isEmpty() == false ? "&pf=" + pricefrom : "") , (priceto != null && priceto.isEmpty() == false ? "&pt=" + priceto : "")  , (roomfrom != null && roomfrom.isEmpty() == false ? "&nrf=" + roomfrom : "")  , (roomto != null && roomto.isEmpty() == false ? "&nrt=" + roomto : "") , (type != null && type.isEmpty() == false ? "&pty=" + type : "") , (livingspacefrom != null && livingspacefrom.isEmpty() == false ? "&slf=" + livingspacefrom : "") , (livingspaceto != null && livingspaceto.isEmpty() == false ? "&slt=" + livingspaceto : "") , (floor != null && floor.isEmpty() == false ? "&fl=" + floor : "") , (available != null && available.isEmpty() == false ? "&at=" + available : "")));
                        count_checker = count_checker + 24;
                    }
                }else{
                       properties.addAll(GetPropertiesRecord(1 , 24 , cityIds, searchIn, category, (radius != null && radius.isEmpty() == false && cities.contains(",") == false ? "&r=" + radius : "") , (pricefrom != null && pricefrom.isEmpty() == false ? "&pf=" + pricefrom : "") , (priceto != null && priceto.isEmpty() == false ? "&pt=" + priceto : "")  , (roomfrom != null && roomfrom.isEmpty() == false ? "&nrf=" + roomfrom : "")  , (roomto != null && roomto.isEmpty() == false ? "&nrt=" + roomto : "") , (type != null && type.isEmpty() == false ? "&pty=" + type : "") , (livingspacefrom != null && livingspacefrom.isEmpty() == false ? "&slf=" + livingspacefrom : "") , (livingspaceto != null && livingspaceto.isEmpty() == false ? "&slt=" + livingspaceto : "") , (floor != null && floor.isEmpty() == false ? "&fl=" + floor : "") , (available != null && available.isEmpty() == false ? "&at=" + available : "")));
                }
                
            }
        }
        
        Date dNow = new Date( );
        SimpleDateFormat ct = new SimpleDateFormat ("hh:mm:ss a zzz");
        SimpleDateFormat cd = new SimpleDateFormat ("E yyyy.MM.dd");
        SimpleDateFormat cm = new SimpleDateFormat ("MMM");
        SimpleDateFormat cy = new SimpleDateFormat ("yyyy");
        rec_manger_id = String.valueOf(propRepository_.createDateTime(cd.format(dNow) + " " + ct.format(dNow) , "www.immoscout24.ch"));
        
        if(properties.size() > 0){
            for(Property rp:properties){
                properties ps = new properties();
                ps.setCate_id(rec_manger_id);
//                ps.setPublishDate(rp.getPublishDate());
//                ps.setUpdateDate(rp.getUpdateDate());
//                ps.setMsRegion(rp.getMsRegion());
//                ps.setCatOfProperty((searchIn.equals("1") ? "rent" : searchIn.equals("2") ? "buy" : "general"));
//                ps.setObjectType((searchIn.equals("1") ? "rent" : searchIn.equals("2") ? "buy" : "general"));
//                ps.setTitleOfAdd(rp.getTitleOfAdd());
//                ps.setStreetNumber(rp.getStreetNumber());
//                ps.setPostalCode(rp.getPostalCode());
//                ps.setcIty(rp.getcIty());
//                ps.setcAnton(rp.getcAnton());
////                ps.setSellPrice(rp.getSellPrice());
////                ps.setGrossPrice(rp.getGrossPrice());
////                ps.setNetPrice(rp.getNetPrice());
//                ps.setNumberOfRooms(rp.getNumberOfRooms());
//                ps.setYearBuilt(rp.getYearBuilt());
//                ps.setCatOfAd(getCateOfAd(category));
//                ps.setNameOfSeller(rp.getNameOfSeller());
//                ps.setStreetNumberOfSeller(rp.getStreetNumberOfSeller());
//                ps.setPostalCodeOfSeller(rp.getPostalCodeOfSeller());
//                ps.setCityOfSeller(cities);
//                ps.setPhoneOfSeller(rp.getPhoneOfSeller());
//                ps.setmNumberOfSeller(rp.getmNumberOfSeller());
//                ps.setEmailOfSeller(rp.getEmailOfSeller());
//                ps.setContactName(rp.getContactName());
//                ps.setContactNumber(rp.getContactNumber());
//                ps.setUrlOfAd(rp.getUrlOfAd());
                ps.setTime_(ct.format(dNow));
                ps.setDate_(cd.format(dNow));
                ps.setMonth_(cm.format(dNow));
                ps.setYear_(cy.format(dNow));
                propRepository.save(ps);
            }
        }
        
        return properties;    
    }
    
    private String getCateOfAd(String name){
        Map coa = new HashMap();
        coa.put("1","Flat, House");
        coa.put("2","Flat");
        coa.put("3","House");
        coa.put("4","Plot");
        coa.put("5","Parking space");
        coa.put("9","Multi-family residential");
        coa.put("7","Office, Commerce, Industry");
        coa.put("10","Agriculture");
        coa.put("8","Other objects");
        String rtv = coa.get(name).toString();
        return (rtv == null ? "" : rtv);
    }
    
    private String GetCityId(String name){
        String rv = "";
        try {
            doc = Jsoup.connect("https://react-api.immoscout24.ch/v2/en/locations?term="+name.toLowerCase()).userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/72.0.3626.109 Safari/537.36").header("Upgrade-Insecure-Requests","1").header("Host" , "react-api.immoscout24.ch").header("Connection", "keep-alive").header("Cache-Control","max-age=0").header("Accept-Language","en-US,en;q=0.9").header("Accept-Encoding" , "gzip, deflate, br").header("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8").ignoreContentType(true).timeout(10000).get();
            rv = doc.text();
            
            try {
                    JSONArray jsonarray = new JSONArray(rv);
                    for(int i=0; i < jsonarray.length(); i++){
                        JSONObject jsonobject = jsonarray.getJSONObject(i);
                        rv = jsonobject.getNumber("id").toString();
                        break;
                    }
            } catch (JSONException e) {
                   System.out.println("Json Parsing Error : " + e.getMessage());
            }
            
        }catch(HttpStatusException ex) {
            System.out.println(" Get City Ids : " + ex.getMessage());
        }catch(IOException ex){
            System.out.println(" Get City Ids : " + ex.getMessage());
        }
        
       return rv;
    }
    
    private String GetTotalRecordsNum(ArrayList<String> CityIds ,String searchIn ,String propCat ,String radius ,String pricefrom ,String priceto ,String roomfrom , String roomto , String type , String livingspacefrom , String livingspaceto , String floor ,String available){

        String rv = "";
        String cities = CityIds.stream().map(n -> String.valueOf(n)).collect(Collectors.joining(",", "", ""));
        try {
            doc = Jsoup.connect("https://react-api.immoscout24.ch/v2/en/properties/searchmetadata?s=" + propCat + "&t= " + searchIn + " &l=" + cities + radius + pricefrom + priceto + roomfrom + roomto + type + livingspacefrom + livingspaceto + floor + available).userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/72.0.3626.109 Safari/537.36").header("Upgrade-Insecure-Requests","1").header("Host" , "react-api.immoscout24.ch").header("Connection", "keep-alive").header("Cache-Control","max-age=0").header("Accept-Language","en-US,en;q=0.9").header("Accept-Encoding" , "gzip, deflate, br").header("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8").ignoreContentType(true).timeout(10000).get();
            rv = doc.text();
            
            try {
                    JSONObject obj = new JSONObject(rv);
                    rv = obj.getNumber("totalMatches").toString();
            } catch (JSONException e) {
                   System.out.println("Json Parsing Error : " + e.getMessage());
            }
            
        }catch(HttpStatusException ex) {
            System.out.println(" Get Total Records Number : " + ex.getMessage());
        }catch(IOException ex){
            System.out.println(" Get Total Records Number : " + ex.getMessage());
        }
        return rv;
    }
    
    private ArrayList<Property> GetPropertiesRecord(int page , int num_of_rec , ArrayList<String> CityIds , String searchIn, String propCat , String radius , String pricefrom , String priceto , String roomfrom , String roomto , String type , String livingspacefrom , String livingspaceto , String floor ,String available ){
        ArrayList<Property> properties = new ArrayList<>();
        String cities = CityIds.stream().map(n -> String.valueOf(n)).collect(Collectors.joining(",", "", ""));
        
        try {
            doc = Jsoup.connect("https://react-api.immoscout24.ch/v2/en/properties?s=" + propCat + "&t=" + searchIn + "&l=" + cities + radius + pricefrom + priceto + roomfrom + roomto + type + livingspacefrom + livingspaceto + floor + available).userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/72.0.3626.109 Safari/537.36").header("is24-meta-pagenumber",Integer.toString(page)).header("is24-meta-pagesize",Integer.toString(num_of_rec)).header("Origin" , "react-api.immoscout24.ch").header("X-OriginalClientIp", "10.10.180.1").header("Accept","text/plain, application/json, text/json").ignoreContentType(true).timeout(10000).get();
            String rv = doc.text();
            try {
                    JSONObject obj = new JSONObject(rv);
                    JSONArray jsonarray = obj.getJSONArray("properties");
                    if(jsonarray != null && jsonarray.length() > 0){
                        for(int i=0; i < jsonarray.length(); i++){
                            JSONObject jsonobject = jsonarray.getJSONObject(i);
                            String title = (jsonobject.has("title") ? jsonobject.getString("title") : "");
                            String address = (jsonobject.has("street") ? jsonobject.getString("street") : "");
                            String grossPrice = (jsonobject.has("price") ? jsonobject.getNumber("price").toString() : "");
                            String zip = (jsonobject.has("zip") ? jsonobject.getString("zip") : "");
                            String rooms = (jsonobject.has("numberOfRooms") ? jsonobject.getNumber("numberOfRooms").toString() : "");
                            String city = (jsonobject.has("cityName") ? jsonobject.getString("cityName") : "");
                            JSONObject agency = (jsonobject.has("agency") ? jsonobject.getJSONObject("agency"): new JSONObject());
                            String phoneNumber = "";
                            String cellNumber = "";
                            String contactPerson = "";
                            String url = "https://www.immoscout24.ch" + (jsonobject.has("propertyDetailUrl") ? jsonobject.getString("propertyDetailUrl"): "");
                            phoneNumber = (agency.has("companyPhoneBusiness") ? agency.getString("companyPhoneBusiness") : "");
                            cellNumber = (agency.has("companyPhoneBusiness") ? agency.getString("companyPhoneBusiness") : "");
                            contactPerson = (agency.has("firstName") ? agency.getString("firstName") : "") + (agency.has("lastName") ? agency.getString("lastName") : "");
                            //properties.add(new Property("","","","","",title,address,zip,city,"",grossPrice,grossPrice,grossPrice,rooms,"","","","","","", phoneNumber , cellNumber ,"", contactPerson ,"",url));
                        }
                    }
            } catch (JSONException e) {
                   System.out.println("Json Parsing Error : " + e.getMessage());
            }
            
        }catch(HttpStatusException ex) {
            System.out.println(ex.getMessage());
        }catch(IOException ex){
            System.out.println(ex.getMessage());
        }
        
       return properties;
    }
}
