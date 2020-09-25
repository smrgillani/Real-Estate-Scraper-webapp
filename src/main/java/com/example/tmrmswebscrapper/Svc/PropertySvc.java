/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.tmrmswebscrapper.Svc;

import com.example.tmrmswebscrapper.Entity.images;
import com.example.tmrmswebscrapper.Entity.properties;
import com.example.tmrmswebscrapper.Models.ImageUrls;
import com.example.tmrmswebscrapper.Models.Property;
import com.example.tmrmswebscrapper.Models.BlockedVendor;
import com.example.tmrmswebscrapper.Repos.PropertyDAOI;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Lubbow
 */
@Service
@Transactional
public class PropertySvc implements PropertySvcI {
    private PropertyDAOI pDAO;
    private BlockedVendorSvcI vDAO;
    @Autowired
    public PropertySvc(PropertyDAOI DAO, BlockedVendorSvcI DAO_) {
        this.pDAO = DAO;
        this.vDAO = DAO_;
    }
    
    @Override
    public Integer addNew(Property p) {
        properties pe = new properties();
        pe.setCate_id(p.getCateId());
        pe.setRegion_id(p.getRegionId());
        pe.setP_id(p.getPostId());
        pe.setV_id(p.getVendorId());
        pe.setPublishdate(p.getPublishDate());
        pe.setUpdatedate(p.getUpdateDate());
        pe.setSearchtype(p.getSearchType());
        pe.setMsregion(p.getMsRegion());
        pe.setCatofproperty(p.getCatOfProperty());
        pe.setPropertytype(p.getPropertyType());
        pe.setTitleofadd(p.getTitleOfAdd());
        pe.setStreetnumber(p.getStreetNumber());
        pe.setPostalcode(p.getPostalCode());
        pe.setCity(p.getcIty());
        pe.setSellprice(p.getSellPrice());
        pe.setSellgrossprice(p.getSellGrossPrice());
        pe.setSellnetprice(p.getSellNetPrice());
        pe.setSellpaymenttype(p.getSellPaymentType());
        pe.setRentprice(p.getRentPrice());
        pe.setRentgrossprice(p.getRentGrossPrice());
        pe.setRentnetprice(p.getRentNetPrice());
        pe.setRentpaymenttype(p.getRentPaymentType());
        pe.setNumberofrooms(p.getNumberOfRooms());
        pe.setLivingspace(p.getLivingSpace());
        pe.setPropertyfloor(p.getPropertyFloor());
        pe.setPropertyarea(p.getPropertyArea());
        pe.setPropertydesc(p.getPropertyDesc());
        pe.setPropertyfeatures(p.getPropertyFeatures());
        pe.setPropertyavailable(p.getPropertyAvailable());
        pe.setYearbuilt(p.getYearBuilt());
        pe.setNameofseller(p.getNameOfSeller());
        pe.setAgencyname(p.getAgencyName());
        pe.setStreetnumberofseller(p.getStreetNumberOfSeller());
        pe.setPostalcodeofseller(p.getPostalCodeOfSeller());
        pe.setCityofseller(p.getCityOfSeller());
        pe.setPhoneofseller(p.getPhoneOfSeller());
        pe.setMnumberofseller(p.getmNumberOfSeller());
        pe.setEmailofseller(p.getEmailOfSeller());
        pe.setContactname(p.getContactName());
        pe.setContactnumber(p.getContactNumber());
        pe.setWebsite(p.getWebSite());
        pe.setUrlofad(p.getUrlOfAd());
        pe.setTime_(p.getTime_());
        pe.setDate_(p.getDate_());
        pe.setMonth_(p.getMonth_());
        pe.setYear_(p.getYear_());
        return this.pDAO.addNew(pe);
    }
    
    @Override
    public void addImageUrls(ArrayList<ImageUrls> p) {
        p.stream().map((dfl) -> {
            images i = new images();
            i.setPid(dfl.getpId());
            i.setUrl(dfl.getUrl());
            return i;
        }).forEachOrdered((i) -> {
            this.pDAO.addImagesLink(i);
        });
    }
    
    @Override
    public Property getSingleProperty(Integer id) {
        properties pe = this.pDAO.getPbyID(id);
        Property p = new Property(pe.getId(), pe.getCate_id() , pe.getRegion_id(), pe.getP_id(), pe.getV_id(), pe.getAgencyname().isEmpty() || pe.getAgencyname()== null ? "Private vendor / Unassigned vendor" : "Institutional vendor" , convertDateToNormalFormat(pe.getPublishdate()), convertDateToNormalFormat(pe.getUpdatedate()), pe.getSearchtype(), pe.getMsregion(), pe.getCatofproperty(), pe.getPropertytype(), pe.getTitleofadd(), pe.getStreetnumber(), pe.getPostalcode(), pe.getCity(), pe.getSellprice(), pe.getSellgrossprice(),pe.getSellnetprice(), pe.getSellpaymenttype(), pe.getRentprice(), pe.getRentgrossprice(), pe.getRentnetprice(), pe.getRentpaymenttype(), pe.getNumberofrooms(), pe.getLivingspace(), pe.getPropertyfloor(), pe.getPropertyarea(), pe.getPropertydesc(), pe.getPropertyfeatures(), pe.getPropertyavailable(),  pe.getYearbuilt(), pe.getNameofseller(), pe.getAgencyname(), pe.getStreetnumberofseller(), pe.getPostalcodeofseller(), pe.getCityofseller(), pe.getPhoneofseller(), pe.getMnumberofseller(), pe.getEmailofseller(), pe.getContactname(), pe.getContactnumber(), pe.getWebsite(), pe.getUrlofad(), pe.getTime_(), convertDateToNormalFormat(pe.getDate_()), pe.getMonth_(), pe.getYear_());
        
        List<images> i = this.pDAO.getIbyID( Integer.toString(p.getId()));
        
        ArrayList<String> urls = new ArrayList<>();
        
        i.forEach((dfl) -> {
            urls.add(dfl.getUrl());
        });
        
        p.setUrls(urls);
        
        return p;
    }
    
    @Override
    public List<Property> getProperties(ArrayList<String> searchIn, ArrayList<Integer> regions, ArrayList<String> searchCat, ArrayList<String> websiteModule, ArrayList<String> vendortype, ArrayList<String> propertytype, ArrayList<String> features, String pricefrom, String priceto, String roomfrom, String roomto, String livingspacefrom, String livingspaceto, String floorspacefrom, String flooorspaceto, String yearbuiltfrom, String yearbuiltto, String availablefrom, String availableto, String adpublishfrom, String adpublishto, String recorddatefrom, String recorddateto, String recordtimefrom, String recordtimeto, String Options) {
        List<properties> epl = this.pDAO.searchPropertyByParameters(searchIn, regions, searchCat, websiteModule, vendortype, propertytype, features, pricefrom, priceto, roomfrom, roomto, livingspacefrom, livingspaceto, floorspacefrom, flooorspaceto, yearbuiltfrom, yearbuiltto, availablefrom, availableto, adpublishfrom, adpublishto, recorddatefrom, recorddateto, recordtimefrom, recordtimeto, Options);
        List<Property> mpl = new ArrayList<>();
        
        epl.forEach((pe) ->{
           Property p = new Property(pe.getId(), pe.getCate_id() , pe.getRegion_id(), pe.getP_id(), pe.getV_id(), pe.getAgencyname().isEmpty() || pe.getAgencyname()== null ? "Private vendor / Unassigned vendor" : "Institutional vendor" , convertDateToNormalFormat(pe.getPublishdate()), convertDateToNormalFormat(pe.getUpdatedate()), pe.getSearchtype(), pe.getMsregion(), pe.getCatofproperty(), pe.getPropertytype(), pe.getTitleofadd(), pe.getStreetnumber(), pe.getPostalcode(), pe.getCity(), pe.getSellprice(), pe.getSellgrossprice(),pe.getSellnetprice(), pe.getSellpaymenttype(), pe.getRentprice(), pe.getRentgrossprice(), pe.getRentnetprice(), pe.getRentpaymenttype(), pe.getNumberofrooms(), pe.getLivingspace(), pe.getPropertyfloor(), pe.getPropertyarea(), pe.getPropertydesc(), pe.getPropertyfeatures(), pe.getPropertyavailable(),  pe.getYearbuilt(), pe.getNameofseller(), pe.getAgencyname(), pe.getStreetnumberofseller(), pe.getPostalcodeofseller(), pe.getCityofseller(), pe.getPhoneofseller(), pe.getMnumberofseller(), pe.getEmailofseller(), pe.getContactname(), pe.getContactnumber(), pe.getWebsite(), pe.getUrlofad(), pe.getTime_(), convertDateToNormalFormat(pe.getDate_()), pe.getMonth_(), pe.getYear_());
           
           List<images> i = this.pDAO.getIbyID( Integer.toString(p.getId()));
        
           ArrayList<String> urls = new ArrayList<>();

           i.forEach((dfl) -> {
                urls.add(dfl.getUrl());
           });
            
           p.setUrls(urls);
        
           mpl.add(p);  
        });
        
        return mpl;
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
    
    @Override
    public boolean delById(Integer pId) {
        return this.pDAO.deletebyId(pId);
    }
    
    @Override
    public List<BlockedVendor> getFirstFiveVendors(){
        return this.vDAO.getFirstFiveVendors();
    }
    
    @Override
    public boolean delVById(Integer vId){
        return this.vDAO.delById(vId);
    }
    
    @Override
    public boolean flagById(String pId){
        return this.pDAO.flagById(pId);
    }
    
    @Override
    public boolean unflagById(String pId){
        return this.pDAO.unflagById(pId);
    }
    
    @Override
    public Integer getPostbyIdAndWebSite(String PostId, String WebSite) {
        return this.pDAO.getPbyIdAndWebSite(PostId, WebSite);
    }
    
    @Override
    public Integer getVendorbyIdAndWebSite(String VendorId, String WebSite) {
        return this.vDAO.getVbyIdAndWS(VendorId, WebSite);
    }
    
    @Override
    public Integer addVendorbyIdAndWebSite(BlockedVendor md){
        return this.vDAO.addNew(md);
    }
    
    @Override
    public List<String> getPropertyCategory(){
        List<String> mcl = new ArrayList<>();
        
        List<String> dcl = this.pDAO.GetAllCategories();
        
        if(dcl != null){
            dcl.forEach((i) ->{
                mcl.add(i);
            });
        }
        
        return mcl;
    }
    
    @Override
    public List<String> getPropertyTypes(){
        List<String> mcl = new ArrayList<>();
        
        List<String> dcl = this.pDAO.GetAllPropTypes();
        
        if(dcl != null){
            dcl.forEach((i) ->{
                mcl.add(i);
            });
        }
        
        return mcl;
    }
    
    @Override
    public List<String> getPropertyFeatures(){
        List<String> mcl = new ArrayList<>();
        
        List<String> dcl = this.pDAO.GetAllPropFeatures();
        
        if(dcl != null){
            dcl.forEach((i) ->{
                mcl.add(i);
            });
        }
        
        return mcl;
    }
    
}
