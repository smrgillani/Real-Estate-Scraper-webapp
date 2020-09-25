/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.tmrmswebscrapper;

import com.example.tmrmswebscrapper.Models.Anibis;
import com.example.tmrmswebscrapper.Models.FCategoryType;
import com.example.tmrmswebscrapper.Models.FRegion;
import com.example.tmrmswebscrapper.Models.FPropertyFeature;
import com.example.tmrmswebscrapper.Models.FPropertyType;
import com.example.tmrmswebscrapper.Models.FSellingType;
import com.example.tmrmswebscrapper.Models.FVendorType;
import com.example.tmrmswebscrapper.Models.Filter;
import com.example.tmrmswebscrapper.Models.HomeGate;
import com.example.tmrmswebscrapper.Models.Property;
import com.example.tmrmswebscrapper.Models.PropertyListJSON;
import com.example.tmrmswebscrapper.Repos.PropertyService;
import com.example.tmrmswebscrapper.Svc.PropertySvcI;
import com.example.tmrmswebscrapper.Svc.FiltersSvcI;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.tmrmswebscrapper.Models.BlockedVendor;
import com.example.tmrmswebscrapper.Models.CronJob;
import com.example.tmrmswebscrapper.Models.ImmoScout;
import com.example.tmrmswebscrapper.Models.ImmoStreet;
import com.example.tmrmswebscrapper.Models.ImmoWelt;
import com.example.tmrmswebscrapper.Models.JsonData;
import com.example.tmrmswebscrapper.Models.MrCity;
import com.example.tmrmswebscrapper.Models.MsRegion;
import com.example.tmrmswebscrapper.Models.NabHome;
import com.example.tmrmswebscrapper.Models.NewHome;
import com.example.tmrmswebscrapper.Models.Tutti;
import com.example.tmrmswebscrapper.Models.UrbanHome;
import com.example.tmrmswebscrapper.Svc.BlockedVendorSvcI;
import com.example.tmrmswebscrapper.Svc.CronJobSvcI;
import com.example.tmrmswebscrapper.Svc.MrCitySvcI;
import com.example.tmrmswebscrapper.Svc.RegionSvcI;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.util.Arrays;
import java.util.Calendar;
import java.util.regex.Pattern;
import javax.imageio.ImageIO;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import net.sourceforge.tess4j.util.LoadLibs;
import org.springframework.ui.Model;

/**
 *
 * @author Lubbow
 */
@RestController
public class TestingController {
    @Autowired
    private PropertySvcI pSvc;
    @Autowired
    private BlockedVendorSvcI vSvc;
    @Autowired
    private PropertyService propRepository_;
    private Document doc;
    @Autowired
    private FiltersSvcI fSvc;
    @Autowired
    private RegionSvcI rSvc;
    @Autowired
    private MrCitySvcI rcSvc;
    @Autowired
    private CronJobSvcI cjSvc;
    
    @RequestMapping("/testing")
    @SuppressWarnings("empty-statement")
    public String page() {
        String url = "https://www.homegate.ch/rs/real-estates?cht=rentflat&loc=Bern Mittelland_region&nrs=10000&ver=3&lan=en&rfi=advertisementId,title,street,zip,city,geoLocation,numberRooms,sellingPrice,surfaceLiving,objectTypeLabel,currency,listingType,contactPerson,contactPhone,interestedFormType,offerType,externalUrls,pictures";
        Map<String,String> Pmap = new HashMap<>();
        Pmap.put("Accept","application/json");
        Pmap.put("Authorization","Basic aGdfYW5kcm9pZDo2VmNHVTZjZUNGVGs4ZEZt");
        Pmap.put("Host","www.homegate.ch");
        Pmap.put("Connection","Keep-Alive");
        Pmap.put("Accept-Encoding","gzip");
        Pmap.put("user-agent","okhttp/3.11.0");

        Map<String,String> Cmap = new HashMap<>();
        
        Map<String,String> Dmap = new HashMap<>();
        
        Map<String, String> cookies = new HashMap<>();
        String rtr = "";
                
        try {
             
            doc = Jsoup.connect(url).headers(Pmap).ignoreContentType(true).get();
            rtr = doc.text();
//            cookies.putAll(res.cookies());
//            System.out.println(cookies);
//            Pmap.clear();
//            
//            Pmap.put("accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
//            Pmap.put("accept-encoding","gzip, deflate, br");
//            Pmap.put("accept-language","en-US,en;q=0.9");
//            Pmap.put("cache-control","max-age=0");
//            Pmap.put("content-length","484");
//            Pmap.put("content-type","application/x-www-form-urlencoded");
//            Pmap.put("origin","https://www.homegate.ch");
//            Pmap.put("referer","https://www.homegate.ch/en?0");
//            Pmap.put("upgrade-insecure-requests","1");
//            Pmap.put("user-agent","Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/72.0.3626.121 Safari/537.36");
//        
//            System.out.println("Size of Pmap" + Pmap.size());
//            System.out.println("Size of Cookies" + cookies.size());
//            
//            
//            
//            Dmap.put("searchForm_hf_0","");
//            Dmap.put("offerType","radio0");
//            Dmap.put("search-criteria-ghost",""); 
//            Dmap.put("searchIn","Bern Mittelland [Region], Hochdorf [Region],");
//            Dmap.put("searchObjectCategory","APARTMENT_AND_HOUSE");
//            Dmap.put("priceRangeField:AminField",""); 
//            Dmap.put("priceRangeField:maxField","");
//            Dmap.put("roomRangeField:minField","");
//            Dmap.put("roomRangeField:maxField","");
//            Dmap.put("searchButtonUpper","");
//            Dmap.put("surfaceLivingRangeField:minField",""); 
//            Dmap.put("surfaceLivingRangeField:maxField","");
//            Dmap.put("yearBuiltRangeField:minField","");
//            Dmap.put("yearBuiltRangeField:maxField","");
//            Dmap.put("floorField:floor","");
//            Dmap.put("availableFromField:availableFrom","");
//            
//            System.out.println("Size of Data" + Dmap.size());
//            
//            url = "https://www.homegate.ch/en?4-1.IFormSubmitListener-search-searchForm";
//            
//            System.out.println("Url is " + url);
//            
//            res = Jsoup.connect(url).method(Method.POST).headers(Pmap).cookies(cookies).data(Dmap).execute();
//            
//            System.out.println("Responded Header is " + res.headers());
//            System.out.println("Responded Cookies Are " + res.cookies());
//            
//            cookies.clear();
//            cookies.putAll(res.cookies());
            
        } catch (IOException ex) {
            Logger.getLogger(TestingController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rtr;
    }
    
    @RequestMapping("/getproperty")
    @SuppressWarnings("empty-statement")
    public Property test(@RequestParam int id){
        return pSvc.getSingleProperty(id);
    }
    
    @RequestMapping("/deleteproperty")
    @SuppressWarnings("empty-statement")
    public boolean deleteProperty(@RequestParam int id){
        return pSvc.delById(id);
    }
    
    @RequestMapping("/flagproperty")
    @SuppressWarnings("empty-statement")
    public boolean flagProperty(@RequestParam int id){
        return pSvc.flagById(Integer.toString(id));
    }
    
    @RequestMapping("/unflagproperty")
    @SuppressWarnings("empty-statement")
    public boolean unflagProperty(@RequestParam int id){
        return pSvc.unflagById(Integer.toString(id));
    }
    
    @RequestMapping("/flagvendor")
    @SuppressWarnings("empty-statement")
    public Integer flagVendor(@RequestParam String id,@RequestParam String website,@RequestParam String name){
        if(pSvc.getVendorbyIdAndWebSite(id, website) <= 0 ){
            return pSvc.addVendorbyIdAndWebSite(new BlockedVendor("", name, id, website));
        }else{
            return 0;
        }
    }
    
    @RequestMapping("/checkflagvendor")
    @SuppressWarnings("empty-statement")
    public Integer checkflagVendor(@RequestParam String id,@RequestParam String website){
        return pSvc.getVendorbyIdAndWebSite(id, website);
    }
    
    @RequestMapping("/unflagvendor")
    public boolean handlePostRequest(@RequestParam int id) {
        return vSvc.delById(id);
    }
    
    @RequestMapping("/tests")
    @SuppressWarnings("empty-statement")
    public ArrayList<PropertyListJSON> test(@RequestParam String searchIn, @RequestParam Integer regionid, @RequestParam String cityName, @RequestParam String regionName, @RequestParam String searchCat, @RequestParam(defaultValue="") ArrayList<String> vendortype, @RequestParam(defaultValue="") ArrayList<String> propertytype, @RequestParam(defaultValue="") ArrayList<String> features, @RequestParam(defaultValue="") String pricefrom,@RequestParam(defaultValue="") String priceto, @RequestParam(defaultValue="") String roomfrom, @RequestParam(defaultValue="") String roomto, @RequestParam(defaultValue="") String livingspacefrom, @RequestParam(defaultValue="") String livingspaceto, @RequestParam(defaultValue="") String floorspacefrom, @RequestParam(defaultValue="") String flooorspaceto, @RequestParam(defaultValue="") String yearbuiltfrom, @RequestParam(defaultValue="") String yearbuiltto, @RequestParam(defaultValue="") String availablefrom, @RequestParam(defaultValue="") String availableto, @RequestParam(defaultValue="") String adpublishfrom, @RequestParam(defaultValue="") String adpublishto) {
        Date dNow = new Date();
        SimpleDateFormat ct = new SimpleDateFormat ("hh:mm:ss");
        SimpleDateFormat cd = new SimpleDateFormat ("yyyy.MM.dd");
        SimpleDateFormat cm = new SimpleDateFormat ("MMM");
        SimpleDateFormat cy = new SimpleDateFormat ("yyyy");
        
        String rec_manger_id = String.valueOf(propRepository_.createDateTime(cd.format(dNow) + " " + ct.format(dNow) , "www.homegate.ch"));            

        ArrayList<PropertyListJSON> dtr = new Tutti().GetTotalResultsCount(pSvc,rec_manger_id, regionid, searchIn, cityName, regionName, vendortype, adpublishfrom.replaceAll(Pattern.quote("-"), "."), adpublishto.replaceAll(Pattern.quote("-"), "."));
        
        return dtr;
    }
    
    @RequestMapping("/homegatemod")
    @SuppressWarnings("empty-statement")
    public JsonData homegatemod(@RequestParam ArrayList<String> searchIn, @RequestParam Integer regionid, @RequestParam ArrayList<String> cityName, @RequestParam String regionName, @RequestParam ArrayList<String> searchCat, @RequestParam(defaultValue="") ArrayList<String> vendortype, @RequestParam(defaultValue="") String adpublishfrom, @RequestParam(defaultValue="") String adpublishto) {
        JsonData dtr = new JsonData();
        
        try{
            Date dNow = new Date();
            SimpleDateFormat ct = new SimpleDateFormat ("hh:mm:ss");
            SimpleDateFormat cd = new SimpleDateFormat ("yyyy.MM.dd");
            SimpleDateFormat cm = new SimpleDateFormat ("MMM");
            SimpleDateFormat cy = new SimpleDateFormat ("yyyy");

            String rec_manger_id = String.valueOf(propRepository_.createDateTime(cd.format(dNow) + " " + ct.format(dNow) , "www.homegate.ch"));            
            ArrayList<PropertyListJSON> ltr = new HomeGate().GetTotalResultsCount(pSvc,rec_manger_id, regionid, searchIn, cityName, regionName, searchCat, vendortype, adpublishfrom.replaceAll(Pattern.quote("-"), "."), adpublishto.replaceAll(Pattern.quote("-"), "."));
            dtr.setrData(ltr);
            dtr.setrMessage("");
            dtr.setrStatus("success");
            
        }catch(NullPointerException e){
            dtr.setrData(null);
            dtr.setrMessage("not able to load data due to error ");
            dtr.setrStatus("error");
        }catch(Exception e){
            dtr.setrData(null);
            dtr.setrMessage("not able to load data due to error ");
            dtr.setrStatus("error");
        }
        
        return dtr;

    }
    
    @RequestMapping("/newhomemod")
    @SuppressWarnings("empty-statement")
    public JsonData newhomemod(@RequestParam String searchIn, @RequestParam Integer regionid, @RequestParam String cityName, @RequestParam String regionName, @RequestParam String searchCat, @RequestParam(defaultValue="") ArrayList<String> vendortype, @RequestParam(defaultValue="") String adpublishfrom, @RequestParam(defaultValue="") String adpublishto) {
        JsonData dtr = new JsonData();
        
        try{
        
            Date dNow = new Date();
            SimpleDateFormat ct = new SimpleDateFormat ("hh:mm:ss");
            SimpleDateFormat cd = new SimpleDateFormat ("yyyy.MM.dd");
            SimpleDateFormat cm = new SimpleDateFormat ("MMM");
            SimpleDateFormat cy = new SimpleDateFormat ("yyyy");

            String rec_manger_id = String.valueOf(propRepository_.createDateTime(cd.format(dNow) + " " + ct.format(dNow) , "www.newhome.ch"));
            ArrayList<PropertyListJSON> ltr = new NewHome().GetTotalResultsCount(pSvc,rec_manger_id, regionid, searchIn, cityName, regionName, searchCat, vendortype);
            dtr.setrData(ltr);
            dtr.setrMessage("");
            dtr.setrStatus("success");
            
        }catch(NullPointerException e){
            dtr.setrData(null);
            dtr.setrMessage("not able to load data due to error ");
            dtr.setrStatus("error");
        }catch(Exception e){
            dtr.setrData(null);
            dtr.setrMessage("not able to load data due to error ");
            dtr.setrStatus("error");
        }
        
        return dtr;
    }
    
    @RequestMapping("/tuttimod")
    @SuppressWarnings("empty-statement")
    public JsonData tuttimod(@RequestParam String searchIn, @RequestParam Integer regionid, @RequestParam String cityName, @RequestParam String regionName, @RequestParam String searchCat, @RequestParam(defaultValue="") ArrayList<String> vendortype, @RequestParam(defaultValue="") String adpublishfrom, @RequestParam(defaultValue="") String adpublishto) {
        JsonData dtr = new JsonData();
        
        try{
            
            Date dNow = new Date();
            SimpleDateFormat ct = new SimpleDateFormat ("hh:mm:ss");
            SimpleDateFormat cd = new SimpleDateFormat ("yyyy.MM.dd");
            SimpleDateFormat cm = new SimpleDateFormat ("MMM");
            SimpleDateFormat cy = new SimpleDateFormat ("yyyy");

            String rec_manger_id = String.valueOf(propRepository_.createDateTime(cd.format(dNow) + " " + ct.format(dNow) , "www.tutti.ch"));
            ArrayList<PropertyListJSON> ltr = new Tutti().GetTotalResultsCount(pSvc,rec_manger_id, regionid, searchIn, cityName, regionName, vendortype, adpublishfrom.replaceAll(Pattern.quote("-"), "."), adpublishto.replaceAll(Pattern.quote("-"), "."));
            dtr.setrData(ltr);
            dtr.setrMessage("");
            dtr.setrStatus("success");
            
        }catch(NullPointerException e){
            dtr.setrData(null);
            dtr.setrMessage("not able to load data due to error ");
            dtr.setrStatus("error");
        }catch(Exception e){
            dtr.setrData(null);
            dtr.setrMessage("not able to load data due to error ");
            dtr.setrStatus("error");
        }
        
        return dtr;
    }
    
    @RequestMapping("/immoscoutmod")
    @SuppressWarnings("empty-statement")
    public JsonData immoscout(@RequestParam String searchIn, @RequestParam Integer regionid, @RequestParam String cityName, @RequestParam String regionName, @RequestParam String searchCat, @RequestParam(defaultValue="") ArrayList<String> vendortype, @RequestParam(defaultValue="") String adpublishfrom, @RequestParam(defaultValue="") String adpublishto) {
        JsonData dtr = new JsonData();
        
        try{
            
            Date dNow = new Date();
            SimpleDateFormat ct = new SimpleDateFormat ("hh:mm:ss");
            SimpleDateFormat cd = new SimpleDateFormat ("yyyy.MM.dd");
            SimpleDateFormat cm = new SimpleDateFormat ("MMM");
            SimpleDateFormat cy = new SimpleDateFormat ("yyyy");

            String rec_manger_id = String.valueOf(propRepository_.createDateTime(cd.format(dNow) + " " + ct.format(dNow) , "www.immoscout24.ch"));        
            ArrayList<PropertyListJSON> ltr = new ImmoScout().GetTotalResultsCount(pSvc,rec_manger_id, regionid, searchIn, cityName, regionName, searchCat, vendortype, adpublishfrom.replaceAll(Pattern.quote("-"), "."), adpublishto.replaceAll(Pattern.quote("-"), "."));
            dtr.setrData(ltr);
            dtr.setrMessage("");
            dtr.setrStatus("success");
            
        }catch(NullPointerException e){
            dtr.setrData(null);
            dtr.setrMessage("not able to load data due to error ");
            dtr.setrStatus("error");
        }catch(Exception e){
            dtr.setrData(null);
            dtr.setrMessage("not able to load data due to error ");
            dtr.setrStatus("error");
        }
        
        return dtr;
    }
    
    @RequestMapping("/immostreetmod")
    @SuppressWarnings("empty-statement")
    public JsonData immostreet(@RequestParam String searchIn, @RequestParam Integer regionid, @RequestParam String cityName, @RequestParam String regionName, @RequestParam String searchCat, @RequestParam(defaultValue="") ArrayList<String> vendortype, @RequestParam(defaultValue="") String adpublishfrom, @RequestParam(defaultValue="") String adpublishto) {
        JsonData dtr = new JsonData();
        ArrayList<PropertyListJSON> ltr = new ArrayList<>();
        
        try{
            
            Date dNow = new Date();
            SimpleDateFormat ct = new SimpleDateFormat ("hh:mm:ss");
            SimpleDateFormat cd = new SimpleDateFormat ("yyyy.MM.dd");
            SimpleDateFormat cm = new SimpleDateFormat ("MMM");
            SimpleDateFormat cy = new SimpleDateFormat ("yyyy");

            String rec_manger_id = String.valueOf(propRepository_.createDateTime(cd.format(dNow) + " " + ct.format(dNow) , "www.immostreet.ch"));        
            ltr = new ImmoStreet().GetTotalResultsCount(pSvc,rec_manger_id, regionid, searchIn, cityName, regionName, searchCat);
                
            dtr.setrData(ltr);
            dtr.setrMessage("");
            dtr.setrStatus("success");
            
        }catch(NullPointerException e){
            dtr.setrData(ltr);
            dtr.setrMessage("not able to load data due to error ");
            dtr.setrStatus("error");
        }catch(Exception e){
            dtr.setrData(ltr);
            dtr.setrMessage("not able to load data due to error ");
            dtr.setrStatus("error");
        }
        
        return dtr;
    }
    
    @RequestMapping("/immoweltmod")
    @SuppressWarnings("empty-statement")
    public JsonData immowelt(@RequestParam String searchIn, @RequestParam Integer regionid, @RequestParam String cityName, @RequestParam String regionName, @RequestParam String searchCat, @RequestParam(defaultValue="") ArrayList<String> vendortype, @RequestParam(defaultValue="") String adpublishfrom, @RequestParam(defaultValue="") String adpublishto) {
        JsonData dtr = new JsonData();
        ArrayList<PropertyListJSON> ltr = new ArrayList<>();
        
        try{
            
            Date dNow = new Date();
            SimpleDateFormat ct = new SimpleDateFormat ("hh:mm:ss");
            SimpleDateFormat cd = new SimpleDateFormat ("yyyy.MM.dd");
            SimpleDateFormat cm = new SimpleDateFormat ("MMM");
            SimpleDateFormat cy = new SimpleDateFormat ("yyyy");

            String rec_manger_id = String.valueOf(propRepository_.createDateTime(cd.format(dNow) + " " + ct.format(dNow) , "www.immowelt.ch"));        
            ltr = new ImmoWelt().GetTotalResultsCount(pSvc,rec_manger_id, regionid, searchIn, cityName, regionName, searchCat, vendortype);
                
            dtr.setrData(ltr);
            dtr.setrMessage("");
            dtr.setrStatus("success");
            
        }catch(NullPointerException e){
            dtr.setrData(ltr);
            dtr.setrMessage("not able to load data due to error ");
            dtr.setrStatus("error");
        }catch(Exception e){
            dtr.setrData(ltr);
            dtr.setrMessage("not able to load data due to error ");
            dtr.setrStatus("error");
        }
        
        return dtr;
    }
    
    @RequestMapping("/urbanhomemod")
    @SuppressWarnings("empty-statement")
    public JsonData urbanhome(@RequestParam String searchIn, @RequestParam Integer regionid, @RequestParam String cityName, @RequestParam String regionName, @RequestParam String searchCat, @RequestParam(defaultValue="") ArrayList<String> vendortype, @RequestParam(defaultValue="") String adpublishfrom, @RequestParam(defaultValue="") String adpublishto) {
                
        JsonData dtr = new JsonData();
        ArrayList<PropertyListJSON> ltr = new ArrayList<>();
        
        try{
            
            Date dNow = new Date();
            SimpleDateFormat ct = new SimpleDateFormat ("hh:mm:ss");
            SimpleDateFormat cd = new SimpleDateFormat ("yyyy.MM.dd");
            SimpleDateFormat cm = new SimpleDateFormat ("MMM");
            SimpleDateFormat cy = new SimpleDateFormat ("yyyy");

            String rec_manger_id = String.valueOf(propRepository_.createDateTime(cd.format(dNow) + " " + ct.format(dNow) , "www.urbanhome.ch"));        
            ltr = new UrbanHome().GetTotalResultsCount(pSvc,"1", rec_manger_id, regionid, searchIn, cityName, regionName, searchCat, vendortype);
                
            dtr.setrData(ltr);
            dtr.setrMessage("");
            dtr.setrStatus("success");
            
        }catch(NullPointerException e){
            dtr.setrData(ltr);
            dtr.setrMessage("not able to load data due to error ");
            dtr.setrStatus("error");
        }catch(Exception e){
        
            dtr.setrData(ltr);
            dtr.setrMessage("not able to load data due to error ");
            dtr.setrStatus("error");
        }
        
        return dtr;
    }
    
    @RequestMapping("/nabhomemod")
    @SuppressWarnings("empty-statement")
    public JsonData nabhome(@RequestParam String searchIn, @RequestParam Integer regionid, @RequestParam String cityName, @RequestParam String regionName, @RequestParam String searchCat, @RequestParam(defaultValue="") ArrayList<String> vendortype, @RequestParam(defaultValue="") String adpublishfrom, @RequestParam(defaultValue="") String adpublishto) {
                
        JsonData dtr = new JsonData();
        ArrayList<PropertyListJSON> ltr = new ArrayList<>();
        
        try{
            
            Date dNow = new Date();
            SimpleDateFormat ct = new SimpleDateFormat ("hh:mm:ss");
            SimpleDateFormat cd = new SimpleDateFormat ("yyyy.MM.dd");
            SimpleDateFormat cm = new SimpleDateFormat ("MMM");
            SimpleDateFormat cy = new SimpleDateFormat ("yyyy");

            String rec_manger_id = String.valueOf(propRepository_.createDateTime(cd.format(dNow) + " " + ct.format(dNow) , "www.nabhome.ch"));        
            ltr = new NabHome().GetTotalResultsCount(pSvc, rec_manger_id, regionid, searchIn, cityName, regionName, searchCat, vendortype);
                
            dtr.setrData(ltr);
            dtr.setrMessage("");
            dtr.setrStatus("success");
            
        }catch(NullPointerException e){
            dtr.setrData(ltr);
            dtr.setrMessage("not able to load data due to error ");
            dtr.setrStatus("error");
        }catch(Exception e){
        
            dtr.setrData(ltr);
            dtr.setrMessage("not able to load data due to error ");
            dtr.setrStatus("error");
        }
        
        return dtr;
    }
    
    @RequestMapping("/anibismod")
    @SuppressWarnings("empty-statement")
    public JsonData anibis(@RequestParam String searchIn, @RequestParam Integer regionid, @RequestParam String cityName, @RequestParam String regionName, @RequestParam String searchCat, @RequestParam(defaultValue="") ArrayList<String> vendortype, @RequestParam(defaultValue="") String adpublishfrom, @RequestParam(defaultValue="") String adpublishto) {
                
        JsonData dtr = new JsonData();
        ArrayList<PropertyListJSON> ltr = new ArrayList<>();
        
        try{
            
            Date dNow = new Date();
            SimpleDateFormat ct = new SimpleDateFormat ("hh:mm:ss");
            SimpleDateFormat cd = new SimpleDateFormat ("yyyy.MM.dd");
            SimpleDateFormat cm = new SimpleDateFormat ("MMM");
            SimpleDateFormat cy = new SimpleDateFormat ("yyyy");

            String rec_manger_id = String.valueOf(propRepository_.createDateTime(cd.format(dNow) + " " + ct.format(dNow) , "www.anibis.ch"));        
            ltr = new Anibis().GetTotalResultsCount(pSvc, rec_manger_id, regionid, searchIn, cityName, regionName, searchCat, vendortype);
                
            dtr.setrData(ltr);
            dtr.setrMessage("");
            dtr.setrStatus("success");
            
        }catch(NullPointerException e){
            dtr.setrData(ltr);
            dtr.setrMessage("not able to load data due to error ");
            dtr.setrStatus("error");
        }catch(Exception e){
        
            dtr.setrData(ltr);
            dtr.setrMessage("not able to load data due to error ");
            dtr.setrStatus("error");
        }
        
        return dtr;
    }
    
    public String getImgText() {
      String imgText = "";
      
      try{
        
      ITesseract instance = new Tesseract();
      
      File tessDataFolder = LoadLibs.extractTessResources("tessdata");

      instance.setDatapath(tessDataFolder.getAbsolutePath());
      
      
      try
      {
        URL url = new URL("https://www.urbanhome.ch/public/dynamic/97cd9105b01059405e00dc205125ae3d.gif?637294894305597903");
        BufferedImage c = ImageIO.read(url);
        imgText = instance.doOCR(c);
      }
      catch (MalformedURLException ex) {
          Logger.getLogger(TestingController.class.getName()).log(Level.SEVERE, null, ex);
      }
      catch (IOException ex) {
          Logger.getLogger(TestingController.class.getName()).log(Level.SEVERE, null, ex);
      }
      catch (TesseractException ex) {
          Logger.getLogger(TestingController.class.getName()).log(Level.SEVERE, null, ex);
      }
      
      }catch(Exception e){
          
      }
      
      return imgText;
      
    }
    
    @RequestMapping("/propertycategories")
    @SuppressWarnings("empty-statement")
    public List<String> propertycategories() {
        List<String> dtr = pSvc.getPropertyCategory();
        return dtr != null ? dtr : null;
    }
    
    @RequestMapping("/propertytypes")
    @SuppressWarnings("empty-statement")
    public List<String> propertytypes() {
        List<String> dtr = pSvc.getPropertyTypes();
        return dtr != null ? dtr : null;
    }
    
    @RequestMapping("/propertyfeatures")
    @SuppressWarnings("empty-statement")
    public List<String> propertyfeatures() {
        
        List<String> dtr = pSvc.getPropertyFeatures();
        List<String> _dtr = new ArrayList();
        
        if(dtr != null && dtr.size() > 0){
            dtr.forEach((i) ->{
                if(i != null && i.isEmpty() == false){
                    List<String> dtr_ = Arrays.asList(i.split(","));
                    if(dtr_ != null && dtr_.size() > 0){
                        _dtr.addAll(dtr_);
                    }
                }
            });
        }
        
        List<String> data = new ArrayList();
        
        if(_dtr != null && _dtr.size() > 0){
            _dtr.forEach((i) ->{
                if (!data.contains(i)) { 
                    data.add(i); 
                }
            });
        }
        
        return data != null ? data : null;
    }
    
    @RequestMapping("/offlinefp")
    @SuppressWarnings("empty-statement")
    public ArrayList<PropertyListJSON> offlinefp(@RequestParam ArrayList<String> searchIn,@RequestParam(defaultValue="") ArrayList<Integer> regions,@RequestParam ArrayList<String> searchCat, @RequestParam ArrayList<String> websiteModule, @RequestParam(defaultValue="") ArrayList<String> vendortype, @RequestParam(defaultValue="") ArrayList<String> propertytype, @RequestParam(defaultValue="") ArrayList<String> features, @RequestParam(defaultValue="") String pricefrom,@RequestParam(defaultValue="") String priceto, @RequestParam(defaultValue="") String roomfrom, @RequestParam(defaultValue="") String roomto, @RequestParam(defaultValue="") String livingspacefrom, @RequestParam(defaultValue="") String livingspaceto, @RequestParam(defaultValue="") String floorspacefrom, @RequestParam(defaultValue="") String flooorspaceto, @RequestParam(defaultValue="") String yearbuiltfrom, @RequestParam(defaultValue="") String yearbuiltto, @RequestParam(defaultValue="") String availablefrom, @RequestParam(defaultValue="") String availableto, @RequestParam(defaultValue="") String adpublishfrom, @RequestParam(defaultValue="") String adpublishto, @RequestParam(defaultValue="") String recorddatefrom, @RequestParam(defaultValue="") String recorddateto, @RequestParam(defaultValue="") String recordtimefrom, @RequestParam(defaultValue="") String recordtimeto) {
        ArrayList<PropertyListJSON> dtr = new ArrayList<>();
        pSvc.getProperties(searchIn, regions, searchCat, websiteModule, vendortype, propertytype, features, pricefrom, priceto, roomfrom, roomto, livingspacefrom, livingspaceto, floorspacefrom, flooorspaceto, yearbuiltfrom, yearbuiltto, availablefrom, availableto, adpublishfrom, adpublishto, recorddatefrom, recorddateto, recordtimefrom, recordtimeto, "fp").forEach((p)->{
            dtr.add(new PropertyListJSON(Integer.toString(p.getId()), p.getTitleOfAdd(), (p.getRentPrice() != null && p.getRentPrice().equals("0") == false ? p.getRentPrice() : p.getRentGrossPrice() != null && p.getRentGrossPrice().equals("0") == false ? p.getRentGrossPrice() : p.getRentPrice() != null && p.getRentPrice().equals("0") == false ? p.getRentPrice() : p.sellPrice), p.getPostalCode() , p.getMsRegion() ,p.getDate_() ,p.getTime_()));
        });
        return dtr;
    }
    
    @RequestMapping("/totalcitiesofregion")
    public MsRegion totalCitiesOfRegion(Model model, @RequestParam(defaultValue="") String rName){
        return rSvc.getMsRegionByName(rName);
    }
    
    @RequestMapping("/getnameofregion")
    public MsRegion getNameOfRegion(Model model,@RequestParam(defaultValue="0") Integer rId){
        return rSvc.getMsRegionById(rId);
    }
    
    @RequestMapping("/getcitiesofregion")
    public List<MrCity> getCitiesOfRegion(Model model,@RequestParam(defaultValue="0") Integer rId){
        return rcSvc.getFilteredMrCities(rId);
    }
    
    @RequestMapping("/getcitiesofregionids")
    public List<MrCity> getCitiesOfRegionIds(Model model,@RequestParam(defaultValue="") ArrayList<Integer> rIds){
        if(rIds != null){
            return rcSvc.getFilteredMrCities(rIds);
        }else{
            return null;
        }
    }
    
    @RequestMapping("/offlinep")
    @SuppressWarnings("empty-statement")
    public ArrayList<PropertyListJSON> offlineProperties(@RequestParam ArrayList<String> searchIn,@RequestParam ArrayList<Integer> regions,@RequestParam ArrayList<String> searchCat, @RequestParam ArrayList<String> websiteModule, @RequestParam(defaultValue="") ArrayList<String> vendortype, @RequestParam(defaultValue="") ArrayList<String> propertytype, @RequestParam(defaultValue="") ArrayList<String> features, @RequestParam(defaultValue="") String pricefrom,@RequestParam(defaultValue="") String priceto, @RequestParam(defaultValue="") String roomfrom, @RequestParam(defaultValue="") String roomto, @RequestParam(defaultValue="") String livingspacefrom, @RequestParam(defaultValue="") String livingspaceto, @RequestParam(defaultValue="") String floorspacefrom, @RequestParam(defaultValue="") String flooorspaceto, @RequestParam(defaultValue="") String yearbuiltfrom, @RequestParam(defaultValue="") String yearbuiltto, @RequestParam(defaultValue="") String availablefrom, @RequestParam(defaultValue="") String availableto, @RequestParam(defaultValue="") String adpublishfrom, @RequestParam(defaultValue="") String adpublishto, @RequestParam(defaultValue="") String recorddatefrom, @RequestParam(defaultValue="") String recorddateto, @RequestParam(defaultValue="") String recordtimefrom, @RequestParam(defaultValue="") String recordtimeto) {
        ArrayList<PropertyListJSON> dtr = new ArrayList<>();
                
        pSvc.getProperties(searchIn, regions, searchCat, websiteModule, vendortype, propertytype, features, pricefrom, priceto, roomfrom, roomto, livingspacefrom, livingspaceto, floorspacefrom, flooorspaceto, yearbuiltfrom, yearbuiltto, availablefrom, availableto, adpublishfrom, adpublishto, recorddatefrom, recorddateto, recordtimefrom, recordtimeto, "").forEach((p)->{
            dtr.add(new PropertyListJSON(Integer.toString(p.getId()), p.getTitleOfAdd(), (p.getRentPrice() != null && p.getRentPrice().equals("0") == false ? p.getRentPrice() : p.getRentGrossPrice() != null && p.getRentGrossPrice().equals("0") == false ? p.getRentGrossPrice() : p.getRentPrice() != null && p.getRentPrice().equals("0") == false ? p.getRentPrice() : p.sellPrice), p.getPostalCode() , p.getMsRegion() ,p.getPublishDate() ,p.getTime_()));
        });
        
        return dtr;
    }
    
    @RequestMapping("/etests")
    @SuppressWarnings("empty-statement")
    public String addNewFilter(@RequestParam String filterName, @RequestParam(defaultValue="") String priceFrom, @RequestParam(defaultValue="") String priceTo, @RequestParam(defaultValue="") String roomFrom, @RequestParam(defaultValue="") String roomTo, @RequestParam(defaultValue="") String livingSpaceFrom, @RequestParam(defaultValue="") String livingSpaceTo, @RequestParam(defaultValue="") String floorSpacePlotAreaFrom, @RequestParam(defaultValue="") String floorSpacePlotAreaTo, @RequestParam(defaultValue="") String builtYearFrom, @RequestParam(defaultValue="") String builtYearTo, @RequestParam(defaultValue="") String availableFrom, @RequestParam(defaultValue="") String availableTo, @RequestParam(defaultValue="") ArrayList<String> fCategoryType, @RequestParam(defaultValue="") ArrayList<String> fCity, @RequestParam(defaultValue="") ArrayList<String> fPropertyFeature, @RequestParam(defaultValue="") ArrayList<String> fSellingType, @RequestParam(defaultValue="") ArrayList<String> fPropertyType, @RequestParam(defaultValue="") ArrayList<String> fVendorType) {
        Filter f = new Filter();
        
        if(filterName != null && filterName.isEmpty() == false){
        
        f.setNameOfFilter(filterName);
        
        f.setPriceFrom(priceFrom);
        f.setPriceTo(priceTo);
        
        f.setRoomFrom(roomFrom);
        f.setRoomTo(roomTo);
        
        f.setLivingSpaceFrom(livingSpaceFrom);
        f.setLivingSpaceTo(livingSpaceTo);
        
        f.setFloorSpacePlotAreaFrom(floorSpacePlotAreaFrom);
        f.setFloorSpacePlotAreaTo(floorSpacePlotAreaTo);
        
        f.setBuiltYearFrom(builtYearFrom);
        f.setBuiltYearTo(builtYearTo);
        
        f.setAvailableFrom(availableFrom);
        f.setAvailableTo(availableTo);
        
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat timeFormat = new SimpleDateFormat("hh:mm:ss");
        DateFormat monthFormat = new SimpleDateFormat("MMM");
	DateFormat yearFormat = new SimpleDateFormat("yyyy");
        Date date = new Date();
        
        f.setDate_(dateFormat.format(date));
        f.setTime_(timeFormat.format(date));
        f.setMonth_(monthFormat.format(date));
        f.setYear_(yearFormat.format(date));
        
        if(fCategoryType != null){
            List<FCategoryType> lfct = new ArrayList();
            fCategoryType.forEach((dfr) -> {
                lfct.add(new FCategoryType(0,dfr,dateFormat.format(date),timeFormat.format(date),monthFormat.format(date),yearFormat.format(date)));
            });
            f.setfCategoryType(lfct);
        }
        
        if(fCity != null){
            List<FRegion> lfc = new ArrayList();
            fCity.forEach((dfr) -> {
                lfc.add(new FRegion(0,dfr,dateFormat.format(date),timeFormat.format(date),monthFormat.format(date),yearFormat.format(date)));
            });
            f.setfRegion(lfc);
        }
        
        if(fPropertyFeature != null){
            List<FPropertyFeature> lfpf = new ArrayList();
            fPropertyFeature.forEach((dfr) -> {
                lfpf.add(new FPropertyFeature(0,dfr,dateFormat.format(date),timeFormat.format(date),monthFormat.format(date),yearFormat.format(date)));
            });
            f.setfPropertyFeature(lfpf);
        }
        
        if(fSellingType != null){
            List<FSellingType> lfst = new ArrayList();
            fSellingType.forEach((dfr) -> {
                lfst.add(new FSellingType(0,dfr,dateFormat.format(date),timeFormat.format(date),monthFormat.format(date),yearFormat.format(date)));
            });
            f.setfSellingType(lfst);
        }
        
        if(fVendorType != null){
            List<FVendorType> lfvt = new ArrayList();
            fVendorType.forEach((dfr) -> {
                lfvt.add(new FVendorType(0,dfr,dateFormat.format(date),timeFormat.format(date),monthFormat.format(date),yearFormat.format(date)));
            });
            f.setfVendorType(lfvt);
        }
        
        if(fPropertyType != null){
            List<FPropertyType> lfpt = new ArrayList();
            fPropertyType.forEach((dfr) -> {
                lfpt.add(new FPropertyType(0,dfr,dateFormat.format(date),timeFormat.format(date),monthFormat.format(date),yearFormat.format(date)));
            });
            f.setfPropertyType(lfpt);
        }
                
        int ii = fSvc.addNewF(f);
        
        return (ii > 0 ? "true" : "false");
        
        }else{
            return "Please Enter Name Of The Filter.";
        }   
    }
    
    @RequestMapping("/savecronjob")
    @SuppressWarnings("empty-statement")
    public String saveCronJob(@RequestParam(defaultValue="") String cronJobName, @RequestParam(defaultValue="0") Integer filterId, @RequestParam(defaultValue="") String scheduleTime, @RequestParam(defaultValue="0") Integer runningRoutineOption, @RequestParam(defaultValue="") String scheduleDate) throws ParseException{
        
        if(cronJobName == null || cronJobName.isEmpty()){
            return "Please enter the name of Job.";
        }else if(filterId == 0){
            return "Please select a filter.";
        }else if(scheduleTime == null || scheduleTime.isEmpty()){
            return "Please enter the schedule time for job.";
        }else if(runningRoutineOption == 0){
            return "Please select a Running Routine Option.";
        }else if(runningRoutineOption == 2 && (scheduleDate == null || scheduleDate.isEmpty())){
            return "Please enter the schedule date for job";
        }else{
            
            if(cjSvc.verifyByTimeInBetween(getTimeWithAddOrSubMins(scheduleTime, -5), getTimeWithAddOrSubMins(scheduleTime, 5)) == false){
                return "There are already jobs exist with less than -5 and +5 minutes differences with your current given time, Choose a different time";
            }else{
                CronJob cj = new CronJob(0, cronJobName, Integer.toString(filterId), "", scheduleTime, Integer.toString(runningRoutineOption), scheduleDate, "", "", "", "");
                int rr = cjSvc.createCronJob(cj);
                return (rr > 0 ? "true" : "false");
            }
            
        }
    }
    
    private String getTimeWithAddOrSubMins(String scheduleTime, Integer MinsToAddOrSub) throws ParseException{
        String myTime = scheduleTime;
        SimpleDateFormat df = new SimpleDateFormat("hh:mm aa");
        Date d = df.parse(myTime); 
        Calendar cal = Calendar.getInstance();
        cal.setTime(d);
        cal.add(Calendar.MINUTE, MinsToAddOrSub);
        String str = df.format(cal.getTime());
        return str;
    }
    
    @RequestMapping("/updatecronjob")
    @SuppressWarnings("empty-statement")
    public String updateCronJob(@RequestParam(defaultValue="0") Integer cronJobId, @RequestParam(defaultValue="") String cronJobName, @RequestParam(defaultValue="0") Integer filterId, @RequestParam(defaultValue="") String scheduleTime, @RequestParam(defaultValue="0") Integer runningRoutineOption, @RequestParam(defaultValue="") String scheduleDate){
        
        if(cronJobId == 0){
            return "No any cron job selected to update.";
        }else if(cronJobName == null || cronJobName.isEmpty()){
            return "Please enter the name of Job to update.";
        }else if(filterId == 0){
            return "Please select a filter to update.";
        }else if(scheduleTime == null || scheduleTime.isEmpty()){
            return "Please enter the schedule time for job to update.";
        }else if(runningRoutineOption == 0){
            return "Please select a Running Routine Option to update.";
        }else if(runningRoutineOption == 2 && (scheduleDate == null || scheduleDate.isEmpty())){
            return "Please enter the schedule date for job to update";
        }else{
            CronJob cj = new CronJob(cronJobId, cronJobName, Integer.toString(filterId), "", scheduleTime, Integer.toString(runningRoutineOption), scheduleDate, "", "", "", "");
            int rr = cjSvc.updateCronJob(cj);
            return (rr > 0 ? "true" : "false");
        }
    }
    
    @RequestMapping("/getcronjob")
    @SuppressWarnings("empty-statement")
    public CronJob getCronJob(@RequestParam(defaultValue="0") Integer cronJobId){
        return cjSvc.getCronJob(cronJobId);
    }
    
    @RequestMapping("/getfilteridbytime")
    @SuppressWarnings("empty-statement")
    public Integer getFilterIdByTime(@RequestParam(defaultValue="") String time){
        
        CronJob cj = cjSvc.getFilterIdByTime(time);
        
        if(cj != null){
            if(cj.getRoutineRunOption().equals("2")){
                SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
                Date date = new Date();
                String dateToday = formatter.format(date);
                
                if(cj.getScheduledDate().equals(dateToday)){
                    return Integer.parseInt(cj.getFilterId());
                }else{
                    return 0; 
                }
                
            }else{
                return Integer.parseInt(cj.getFilterId());
            }
        }else{
            return 0;   
        }        
    }
    
    @RequestMapping("/delcronjob")
    @SuppressWarnings("empty-statement")
    public boolean delCronJob(@RequestParam(defaultValue="0") Integer cronJobId){
        return cjSvc.deleteById(cronJobId);
    }
    
    @RequestMapping("/updatefilter")
    @SuppressWarnings("empty-statement")
    public String updateFilter(@RequestParam Integer filterId, @RequestParam String filterName, @RequestParam(defaultValue="") String priceFrom, @RequestParam(defaultValue="") String priceTo, @RequestParam(defaultValue="") String roomFrom, @RequestParam(defaultValue="") String roomTo, @RequestParam(defaultValue="") String livingSpaceFrom, @RequestParam(defaultValue="") String livingSpaceTo, @RequestParam(defaultValue="") String floorSpacePlotAreaFrom, @RequestParam(defaultValue="") String floorSpacePlotAreaTo, @RequestParam(defaultValue="") String builtYearFrom, @RequestParam(defaultValue="") String builtYearTo, @RequestParam(defaultValue="") String availableFrom, @RequestParam(defaultValue="") String availableTo, @RequestParam(defaultValue="") ArrayList<String> fCategoryTypeToUp, @RequestParam(defaultValue="") ArrayList<String> fCategoryTypeToDel, @RequestParam(defaultValue="") ArrayList<String> fCityToUp, @RequestParam(defaultValue="") ArrayList<String> fCityToDel, @RequestParam(defaultValue="") ArrayList<String> fPropertyFeatureToUp, @RequestParam(defaultValue="") ArrayList<String> fPropertyFeatureToDel, @RequestParam(defaultValue="") ArrayList<String> fSellingTypeToUp, @RequestParam(defaultValue="") ArrayList<String> fSellingTypeToDel, @RequestParam(defaultValue="") ArrayList<String> fPropertyTypeToUp, @RequestParam(defaultValue="") ArrayList<String> fPropertyTypeToDel, @RequestParam(defaultValue="") ArrayList<String> fVendorTypeToUp, @RequestParam(defaultValue="") ArrayList<String> fVendorTypeToDel) {
        Filter f = new Filter();
        
        f.setId(filterId);
        f.setNameOfFilter(filterName);
        
        f.setPriceFrom(priceFrom);
        f.setPriceTo(priceTo);
        
        f.setRoomFrom(roomFrom);
        f.setRoomTo(roomTo);
        
        f.setLivingSpaceFrom(livingSpaceFrom);
        f.setLivingSpaceTo(livingSpaceTo);
        
        f.setFloorSpacePlotAreaFrom(floorSpacePlotAreaFrom);
        f.setFloorSpacePlotAreaTo(floorSpacePlotAreaTo);
        
        f.setBuiltYearFrom(builtYearFrom);
        f.setBuiltYearTo(builtYearTo);
        
        f.setAvailableFrom(availableFrom);
        f.setAvailableTo(availableTo);
        
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat timeFormat = new SimpleDateFormat("hh:mm:ss");
        DateFormat monthFormat = new SimpleDateFormat("MMM");
	DateFormat yearFormat = new SimpleDateFormat("yyyy");
        Date date = new Date();
        
        f.setDate_(dateFormat.format(date));
        f.setTime_(timeFormat.format(date));
        f.setMonth_(monthFormat.format(date));
        f.setYear_(yearFormat.format(date));
        
        if(fCategoryTypeToUp != null){
            List<FCategoryType> lfct = new ArrayList();
            fCategoryTypeToUp.forEach((dfr) -> {
                lfct.add(new FCategoryType(0,dfr,dateFormat.format(date),timeFormat.format(date),monthFormat.format(date),yearFormat.format(date)));
            });
            f.setfCategoryType(lfct);
        }
        
        if(fCityToUp != null){
            List<FRegion> lfc = new ArrayList();
            fCityToUp.forEach((dfr) -> {
                lfc.add(new FRegion(0,dfr,dateFormat.format(date),timeFormat.format(date),monthFormat.format(date),yearFormat.format(date)));
            });
            f.setfRegion(lfc);
        }
        
        if(fPropertyFeatureToUp != null){
            List<FPropertyFeature> lfpf = new ArrayList();
            fPropertyFeatureToUp.forEach((dfr) -> {
                lfpf.add(new FPropertyFeature(0,dfr,dateFormat.format(date),timeFormat.format(date),monthFormat.format(date),yearFormat.format(date)));
            });
            f.setfPropertyFeature(lfpf);
        }
        
        if(fSellingTypeToUp != null){
            List<FSellingType> lfst = new ArrayList();
            fSellingTypeToUp.forEach((dfr) -> {
                lfst.add(new FSellingType(0,dfr,dateFormat.format(date),timeFormat.format(date),monthFormat.format(date),yearFormat.format(date)));
            });
            f.setfSellingType(lfst);
        }
        
        if(fVendorTypeToUp != null){
            List<FVendorType> lfvt = new ArrayList();
            fVendorTypeToUp.forEach((dfr) -> {
                lfvt.add(new FVendorType(0,dfr,dateFormat.format(date),timeFormat.format(date),monthFormat.format(date),yearFormat.format(date)));
            });
            f.setfVendorType(lfvt);
        }
        
        if(fPropertyTypeToUp != null){
            List<FPropertyType> lfpt = new ArrayList();
            fPropertyTypeToUp.forEach((dfr) -> {
                lfpt.add(new FPropertyType(0,dfr,dateFormat.format(date),timeFormat.format(date),monthFormat.format(date),yearFormat.format(date)));
            });
            f.setfPropertyType(lfpt);
        }
        
        int ii = fSvc.updateFilter(f, fCategoryTypeToDel, fCityToDel, fPropertyFeatureToDel, fSellingTypeToDel, fVendorTypeToDel, fPropertyTypeToDel);
        
        return (ii > 0 ? "true" : "false");
        
    }
    
    @RequestMapping("/delfilter")
    @SuppressWarnings("empty-statement")
    public boolean delFilterById(@RequestParam Integer filterId) {        
        return fSvc.deleteFilterById(filterId);
    }
    
    @RequestMapping("/getfilter")
    @SuppressWarnings("empty-statement")
    public Filter getFilterById(@RequestParam Integer filterId) {        
        return fSvc.getFById(filterId);
    }
    
    @RequestMapping("/loadfirstfivefilters")
    @SuppressWarnings("empty-statement")
    public String loadFirstFiveFilters() {
        String rsp = "";
        
        for(Filter dfm: fSvc.getFirstFiveFilters()){
            rsp = rsp + "{\"id\":\"" + dfm.getId() + "\",\"name\":\"" + dfm.getNameOfFilter() + "\"},";
        }
        
        rsp = "[" + (rsp.length() > 0 ? rsp.substring(0,rsp.length() - 1) : "") + "]";
        
        return rsp;
    }
    
    @RequestMapping("/loadsearchedfilters")
    @SuppressWarnings("empty-statement")
    public String loadSearchedFilters(@RequestParam String sp) {
        String rsp = "";
        
        for(Filter dfm: fSvc.getSearchedFilters(sp)){
            rsp = rsp + "{\"id\":\"" + dfm.getId() + "\",\"name\":\"" + dfm.getNameOfFilter() + "\"},";
        }
        
        rsp = "[" + (rsp.length() > 0 ? rsp.substring(0,rsp.length() - 1) : "") + "]";
        
        return rsp;
    }
}