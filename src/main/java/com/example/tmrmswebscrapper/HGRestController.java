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
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import static org.junit.Assert.assertEquals;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Syed Musa Gillani
 */
@RestController
public class HGRestController {
    private Document doc;
    private String rec_manger_id = ""; 
    @Autowired
    private PropertyRepository propRepository;
    @Autowired
    private PropertyService propRepository_;
    
    @RequestMapping("/hgrequest")
    @SuppressWarnings("empty-statement")
    public ArrayList<Property> index(@RequestParam String searchIn,@RequestParam String category, @RequestParam String cities, @RequestParam(defaultValue="") String pricefrom,@RequestParam(defaultValue="") String priceto, @RequestParam(defaultValue="") String roomfrom, @RequestParam(defaultValue="") String roomto , @RequestParam(defaultValue="") String radius, @RequestParam(defaultValue="") String type, @RequestParam(defaultValue="") String livingspacefrom, @RequestParam(defaultValue="") String livingspaceto, @RequestParam(defaultValue="") String yearbuiltfrom, @RequestParam(defaultValue="") String yearbuiltto, @RequestParam(defaultValue="") String floor, @RequestParam(defaultValue="") String available , @RequestParam(defaultValue="") String features) throws IOException {
        String doc_ = null;
        Elements selectrecordbox = null;
        Elements contentOfPage = null;
        Element pagination = null;
        ArrayList<PropertyUrls> urls = new ArrayList<>();
        ArrayList<Property> properties = new ArrayList<>();
        try {
            System.out.print("https://www.homegate.ch/" + searchIn + "/" + category + "/cities/matching-list?tab=list&o=sortToplisting-desc" + (pricefrom != null && pricefrom.isEmpty() == false ? "&ag=" + pricefrom : "") + (priceto != null && priceto.isEmpty() == false ? "&ah=" + priceto : "")  + (roomfrom != null && roomfrom.isEmpty() == false ? "&ac=" + roomfrom : "")  + (roomto != null && roomto.isEmpty() == false ? "&ad=" + roomto : "") + (radius != null && radius.isEmpty() == false && cities.contains(",") == false ? "&be=" + radius : "") + (type != null && type.isEmpty() == false ? "&aw=" + type : "") + (livingspacefrom != null && livingspacefrom.isEmpty() == false ? "&ak=" + livingspacefrom : "") + (livingspaceto != null && livingspaceto.isEmpty() == false ? "&al=" + livingspaceto : "") + (yearbuiltfrom != null && yearbuiltfrom.isEmpty() == false? "&bf=" + yearbuiltfrom : "") + (yearbuiltto != null && yearbuiltto.isEmpty() == false ? "&bg=" + yearbuiltto : "") + (floor != null && floor.isEmpty() == false ? "&ax=" + floor : "") + (available != null && available.isEmpty() == false ? "&av=" + available : "") + (features != null && features.isEmpty() == false ? "&an=" + features : "") + (cities != null && cities.isEmpty() == false ? "&am=" + cities : ""));
            doc = Jsoup.connect("https://www.homegate.ch/" + searchIn + "/" + category + "/cities/matching-list?tab=list&o=sortToplisting-desc" + (pricefrom != null && pricefrom.isEmpty() == false ? "&ag=" + pricefrom : "") + (priceto != null && priceto.isEmpty() == false ? "&ah=" + priceto : "")  + (roomfrom != null && roomfrom.isEmpty() == false ? "&ac=" + roomfrom : "")  + (roomto != null && roomto.isEmpty() == false ? "&ad=" + roomto : "") + (radius != null && radius.isEmpty() == false && cities.contains(",") == false ? "&be=" + radius : "") + (type != null && type.isEmpty() == false ? "&aw=" + type : "") + (livingspacefrom != null && livingspacefrom.isEmpty() == false ? "&ak=" + livingspacefrom : "") + (livingspaceto != null && livingspaceto.isEmpty() == false ? "&al=" + livingspaceto : "") + (yearbuiltfrom != null && yearbuiltfrom.isEmpty() == false? "&bf=" + yearbuiltfrom : "") + (yearbuiltto != null && yearbuiltto.isEmpty() == false ? "&bg=" + yearbuiltto : "") + (floor != null && floor.isEmpty() == false ? "&ax=" + floor : "") + (available != null && available.isEmpty() == false ? "&av=" + available : "") + (features != null && features.isEmpty() == false ? "&an=" + features : "") + (cities != null && cities.isEmpty() == false ? "&am=" + cities : "")).timeout(100000).get();
            System.out.print("https://www.homegate.ch/" + searchIn + "/" + category + "/cities/matching-list?tab=list&o=sortToplisting-desc" + (pricefrom != null && pricefrom.isEmpty() == false ? "&ag=" + pricefrom : "") + (priceto != null && priceto.isEmpty() == false ? "&ah=" + priceto : "")  + (roomfrom != null && roomfrom.isEmpty() == false ? "&ac=" + roomfrom : "")  + (roomto != null && roomto.isEmpty() == false ? "&ad=" + roomto : "") + (radius != null && radius.isEmpty() == false && cities.contains(",") == false ? "&be=" + radius : "") + (type != null && type.isEmpty() == false ? "&aw=" + type : "") + (livingspacefrom != null && livingspacefrom.isEmpty() == false ? "&ak=" + livingspacefrom : "") + (livingspaceto != null && livingspaceto.isEmpty() == false ? "&al=" + livingspaceto : "") + (yearbuiltfrom != null && yearbuiltfrom.isEmpty() == false? "&bf=" + yearbuiltfrom : "") + (yearbuiltto != null && yearbuiltto.isEmpty() == false ? "&bg=" + yearbuiltto : "") + (floor != null && floor.isEmpty() == false ? "&ax=" + floor : "") + (available != null && available.isEmpty() == false ? "&av=" + available : "") + (features != null && features.isEmpty() == false ? "&an=" + features : "") + (cities != null && cities.isEmpty() == false ? "&am=" + cities : ""));
            contentOfPage = doc.select(".paginator-counter");
            pagination = contentOfPage.select("span").get(1);
            
            if(pagination.text() != null){
                ArrayList<PropertyUrls> urlsSub = new ArrayList<>();
                for (int i = 1; i <= Integer.parseInt((pagination.text() != null ? pagination.text() : "0")); i++) {
                    doc = Jsoup.connect("https://www.homegate.ch/" + searchIn + "/" + category + "/cities/matching-list?tab=list&o=sortToplisting-desc&am=" + cities + "&ep="+i).timeout(100000).get();
                    selectrecordbox = doc.select(".box-row-wrapper");
                    urlsSub = geturls(selectrecordbox);
                    urls.addAll(urlsSub);
                }
            }else{
                    selectrecordbox = doc.select(".box-row-wrapper");
                    urls = geturls(selectrecordbox);
            }
            
            for(PropertyUrls pu:urls){
                doc = Jsoup.connect(pu.getPurls()).timeout(100000).get();
                contentOfPage = doc.select(".detail");
                String titleOfad = doc.select(".detail-meta-title").text();
                Property propertyinfo = getPInfo(contentOfPage , pu.getPurls(), titleOfad , searchIn , (searchIn.equals("rent") ? getCateOfAd(category) : getCateOfAdBuy(category)));
                properties.add(propertyinfo);
            }
            
        } catch (HttpStatusException ex) {
            doc_ = "not founded";
            assertEquals(404, ex.getStatusCode());
        }
        
        Date dNow = new Date( );
        SimpleDateFormat ct = new SimpleDateFormat ("hh:mm:ss a zzz");
        SimpleDateFormat cd = new SimpleDateFormat ("E yyyy.MM.dd");
        SimpleDateFormat cm = new SimpleDateFormat ("MMM");
        SimpleDateFormat cy = new SimpleDateFormat ("yyyy");
        rec_manger_id = String.valueOf(propRepository_.createDateTime(cd.format(dNow) + " " + ct.format(dNow) , "www.homegate.ch"));
        
        if(properties.size() > 0){
            for(Property rp:properties){
                properties ps = new properties();
                ps.setCate_id(rec_manger_id);
//                ps.setPublishDate(rp.getPublishDate());
//                ps.setUpdateDate(rp.getUpdateDate());
//                ps.setMsRegion(rp.getMsRegion());
//                ps.setCatOfProperty((searchIn.equals("rent") ? getCateOfAd(category) : getCateOfAdBuy(category)));
//                ps.setObjectType(searchIn);
//                ps.setTitleOfAdd(rp.getTitleOfAdd());
//                ps.setStreetNumber(rp.getStreetNumber());
//                ps.setPostalCode(rp.getPostalCode());
//                ps.setcIty(cities);
                //ps.setcAnton(rp.getcAnton());
//                ps.setSellPrice(rp.getSellPrice());
                //ps.setGrossPrice(rp.getGrossPrice());
                //ps.setNetPrice(rp.getNetPrice());
//                ps.setNumberOfRooms(rp.getNumberOfRooms());
//                ps.setYearBuilt(rp.getYearBuilt());
//                ps.setCatOfAd((searchIn.equals("rent") ? getCateOfAd(category) : getCateOfAdBuy(category)));
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
        coa.put("real-estate","Apartment house");
	coa.put("apartment","Apartment");
	coa.put("house","House, chalet, rustico");
	coa.put("furnished-dwelling","Furnished dwelling");
	coa.put("parking-place-garage","Parking space, garage");
	coa.put("office","Office");
	coa.put("industrial-object","Commercial &amp; industry");
	coa.put("storage-room","Storage room");
        String rtv = coa.get(name).toString();
        return (rtv == null ? "" : rtv);
    }
    
    private String getCateOfAdBuy(String name){
        Map coa = new HashMap();
        coa.put("apartment","Apartment");
        coa.put("house","House, chalet, rustico");
        coa.put("real-estate","Apartment &amp; house");
        coa.put("plot","Building plot");
        coa.put("multi-family-house","Multi-family house");
        coa.put("commercial-and-residential-building","Commercial and residential building");
        coa.put("parking-place-garage","Parking space, garage");
        coa.put("commercial","Commercial, Office, Storage room");
        String rtv = coa.get(name).toString();
        return (rtv == null ? "" : rtv);
    }
    
    private ArrayList<PropertyUrls> geturls(Elements e){
        System.out.println("getting urls");
        ArrayList<PropertyUrls> urls = new ArrayList<>();
        String linkElement = "";
        for (Element headline : e) {
                linkElement = headline.select("a").get(1).attr("href");
                //urls.add(new PropertyUrls("https://www.homegate.ch/" + linkElement));
        }
        return urls;
    }
    
    private Property getPInfo(Elements e, String url , String title , String pType , String pCat){
        System.out.println("getting info from urls");
        String address = e.select(".detail-address-link").first().text();
        
        Elements price = e.select(".detail-price").select("ul > li");
        
        String grossPrice = "";
        
        String netPrice = "";
        
        for(Element pr_:price){
            String linkElement = pr_.text().toLowerCase();
            if(linkElement.contains("rent")){
                    if(linkElement.contains("chf")){
                        grossPrice = linkElement.substring(linkElement.indexOf("chf") + 3);
                    }else{
                        grossPrice = linkElement.replace("rent","");
                        grossPrice = (grossPrice.contains("chf") ? grossPrice.replace("chf","") : grossPrice);
                    }
            }
            
            if(linkElement.contains("net rent")){
                    if(linkElement.contains("chf")){
                        netPrice = linkElement.substring(linkElement.indexOf("chf")+ 3);
                    }else{
                        netPrice = linkElement.replace("net rent","");
                        netPrice = (grossPrice.contains("chf") ? grossPrice.replace("chf","") : grossPrice);
                    }
            }
            
            if(linkElement.contains("selling price")){
                
                    if(linkElement.contains("chf")){
                        netPrice = linkElement.substring(linkElement.indexOf("chf")+ 3);
                    }else{
                        netPrice = linkElement.replace("selling price","");
                        netPrice = (grossPrice.contains("chf") ? grossPrice.replace("chf","") : grossPrice);
                    }
            }
        }
        
        
        Elements feature = e.select(".detail-key-data");
        Elements contactDetailsDiv = e.select(".detail-contact");
        String contactPerson = "";
        String phoneNumber = "";
        String cellNumber = "";
        if(contactDetailsDiv != null && contactDetailsDiv.size() > 0 && contactDetailsDiv.size() < 2){
            if(contactDetailsDiv.get(0).select("p > span").size() > 1){
                contactPerson = contactDetailsDiv.get(0).select("p > span").get(1).text();
                phoneNumber = contactDetailsDiv.get(0).select(".show-number-container").select("i[class=\"icon icon-phone-landline\"] + span").text();
                cellNumber = contactDetailsDiv.get(0).select(".show-number-container").select("i[class=\"icon icon-phone-cell\"] + span").text();
            }else{
                contactPerson = contactDetailsDiv.get(0).select("p > span").text();
            }
        }else if (contactDetailsDiv.size() > 1 && contactDetailsDiv.size() < 3){
            contactPerson = contactDetailsDiv.get(1).select("p > span").get(1).text();
            phoneNumber = contactDetailsDiv.get(1).select(".show-number-container").select("i[class=\"icon icon-phone-landline\"] + span").text();
            cellNumber = contactDetailsDiv.get(1).select(".show-number-container").select("i[class=\"icon icon-phone-cell\"] + span").text();
        }
        
        Elements features = feature.select("ul > li");
        String rooms = "";
        for(Element fe:features){
            if(fe.select("span").size() > 0){
                String linkElement = fe.select("span").get(0).text().toLowerCase();
                if(linkElement.equals("room") || linkElement.equals("rooms")){
                    rooms = fe.select("span").get(1).text();
                    break;
                }
            }
        }

        //Property info = new Property("","","",pCat,pType,title,address,"","","",grossPrice,grossPrice,netPrice,rooms,"","","","","","", phoneNumber , cellNumber ,"", contactPerson ,"",url);
        return null;
    }
}
