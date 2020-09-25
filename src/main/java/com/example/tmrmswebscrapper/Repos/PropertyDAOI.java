/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.tmrmswebscrapper.Repos;

import com.example.tmrmswebscrapper.Entity.images;
import com.example.tmrmswebscrapper.Entity.properties;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Lubbow
 */
public interface PropertyDAOI {
    properties getPbyID(Integer ID);
    Integer addNew(properties p);
    void addImagesLink(images p);
    List<images> getIbyID(String ID);
    boolean deletebyId(Integer id);
    boolean flagById(String id);
    boolean unflagById(String id);
    List<properties> getFlaggedProperties();
    Integer getPbyIdAndWebSite(String PostId, String WebSite);
    //List<properties> getPropertiesByIdsParameter(ArrayList<Integer> ids);
    List<properties> searchPropertyByParameters(ArrayList<String> searchIn, ArrayList<Integer> regions, ArrayList<String> searchCat, ArrayList<String> websiteModule, ArrayList<String> vendortype, ArrayList<String> propertytype, ArrayList<String> features, String pricefrom, String priceto, String roomfrom, String roomto, String livingspacefrom, String livingspaceto, String floorspacefrom, String flooorspaceto, String yearbuiltfrom, String yearbuiltto, String availablefrom, String availableto, String adpublishfrom, String adpublishto, String recorddatefrom, String recorddateto, String recordtimefrom, String recordtimeto, String Options);
    List<String> GetAllCategories();
    List<String> GetAllPropTypes();
    List<String> GetAllPropFeatures();
}
