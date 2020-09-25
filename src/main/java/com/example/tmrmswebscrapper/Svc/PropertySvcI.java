/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.tmrmswebscrapper.Svc;

import com.example.tmrmswebscrapper.Models.ImageUrls;
import com.example.tmrmswebscrapper.Models.Property;
import com.example.tmrmswebscrapper.Models.BlockedVendor;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Lubbow
 */
public interface PropertySvcI {
    Integer addNew(Property p);
    void addImageUrls(ArrayList<ImageUrls> p);
    Property getSingleProperty(Integer id);
    boolean delById(Integer pId);
    boolean flagById(String pId);
    boolean unflagById(String pId);
    Integer getPostbyIdAndWebSite(String PostId, String WebSite);
    Integer getVendorbyIdAndWebSite(String VendorId, String WebSite);
    //boolean addVendorbyIdAndWebSite(BlockedVendor bv);
    Integer addVendorbyIdAndWebSite(BlockedVendor md);
    boolean delVById(Integer vId);
    List<BlockedVendor> getFirstFiveVendors();
    List<Property> getProperties(ArrayList<String> searchIn, ArrayList<Integer> regions, ArrayList<String> searchCat, ArrayList<String> websiteModule, ArrayList<String> vendortype, ArrayList<String> propertytype, ArrayList<String> features, String pricefrom, String priceto, String roomfrom, String roomto, String livingspacefrom, String livingspaceto, String floorspacefrom, String flooorspaceto, String yearbuiltfrom, String yearbuiltto, String availablefrom, String availableto, String adpublishfrom, String adpublishto, String recorddatefrom, String recorddateto, String recordtimefrom, String recordtimeto, String Options);
    List<String> getPropertyCategory();
    List<String> getPropertyTypes();
    List<String> getPropertyFeatures();
}
