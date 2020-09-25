/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.tmrmswebscrapper.Repos;

import com.example.tmrmswebscrapper.Entity.images;
import com.example.tmrmswebscrapper.Entity.properties;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Lubbow
 */

@Repository
public class PropertyDAO implements PropertyDAOI {
    @PersistenceContext
    @SuppressWarnings("unchecked")
    private EntityManager EM;
    
    @Override
    public properties getPbyID(Integer ID) {
        String hql = "FROM properties as u WHERE u.id = :fid";
	List<properties> getSingleRow = EM.createQuery(hql).setParameter( "fid", ID).getResultList();
        return getSingleRow.isEmpty() ? null : getSingleRow.get(0);
    }
    
    @Override
    public List<properties> getFlaggedProperties() {
        String hql = "FROM properties as u WHERE u.flaged_p is not null";
	List<properties> getRows = EM.createQuery(hql).getResultList();
        return getRows.isEmpty() ? null : getRows;
    }
    
    @Override
    public Integer getPbyIdAndWebSite(String PostId, String WebSite) {
        String hql = "SELECT COUNT(*) FROM properties WHERE p_id = :pid and website = :ws";
        return ((Number) EM.createNativeQuery(hql).setParameter( "pid", PostId).setParameter( "ws", WebSite).getSingleResult()).intValue();
    }
    
    @Override
    public List<images> getIbyID(String ID){
        String hql = "FROM images u WHERE u.pid = :fid";
	return (List<images>) EM.createQuery(hql).setParameter( "fid", ID).getResultList();
    }
    
    @Override
    public boolean flagById(String id){
        Query query = EM.createNativeQuery("update properties set flaged_p = '1' where id = :id");
        query.setParameter("id", id);
        int count = query.executeUpdate();
        return count > 0;
    }
    
    @Override
    public boolean unflagById(String id){
        Query query = EM.createNativeQuery("update properties set flaged_p = NULL where id = :id");
        query.setParameter("id", id);
        int count = query.executeUpdate();
        return count > 0;
    }

    @Override
    public Integer addNew(properties p) {
        
        int vtr = getPbyIdAndWebSite(p.getP_id(),p.getWebsite());       

        if(vtr <= 0){
            EM.persist(p);
            vtr = p.getId();
        }else{
            vtr = 0;
        }
        
        EM.flush();
        return vtr;
    }
    
    @Override
    public void addImagesLink(images p) {
        EM.persist(p);
        //EM.flush();
        //System.out.println("flushed");
    }

    @Override
    public boolean deletebyId(Integer id) {
        Query query = EM.createNativeQuery("delete from properties where id = :pid");
        query.setParameter("pid", id);
        int count = query.executeUpdate();
        return count > 0;
    }
    
//    @Override
//    public List<properties> getPropertiesByIdsParameter(ArrayList<Integer> ids){
//        String hql = "FROM properties u where u.id in (:ids) order by u.id desc";        
//        return (List<properties>) EM.createQuery(hql).setParameter("ids", ids).getResultList();
//    }
    
    @Override
    public List<properties> searchPropertyByParameters(ArrayList<String> searchIn, ArrayList<Integer> regions, ArrayList<String> searchCat, ArrayList<String> websiteModule, ArrayList<String> vendortype, ArrayList<String> propertytype, ArrayList<String> features, String pricefrom, String priceto, String roomfrom, String roomto, String livingspacefrom, String livingspaceto, String floorspacefrom, String flooorspaceto, String yearbuiltfrom, String yearbuiltto, String availablefrom, String availableto, String adpublishfrom, String adpublishto, String recorddatefrom, String recorddateto, String recordtimefrom, String recordtimeto, String Options){
        //String hql = "FROM properties u where (u.searchtype like :searchIn) and (u.region_id in (:rids)) and (u.catofproperty in (:searchCat)) and " + getPublishDateString(adpublishfrom, adpublishto) + getRecordTimeString(recordtimefrom, recordtimeto) + "  (u.propertytype like :propertytype or u.propertyfeatures like :features " + (searchIn.toLowerCase().contains("rent") ? "or u.rentprice >= :pricefrom or u.rentprice <= :priceto" : "or u.sellprice >= :pricefrom or u.sellprice <= :priceto") + " or u.numberofrooms >= :roomfrom or u.numberofrooms <= :roomto or u.livingspace >= :livingspacefrom or u.livingspace <= :livingspaceto or u.propertyfloor >= :floorspacefrom or u.propertyfloor <= :flooorspaceto or u.yearbuilt >= :yearbuiltfrom or u.yearbuilt <= :yearbuiltto " + GetVendorTypeVerified(vendortype) + ") " + (Options.contains("fp") ? "and (u.flaged_p is not null)" : "and (u.flaged_p is null)") + " order by u.id desc";        
        //return (List<properties>) EM.createQuery(hql).setParameter("searchIn", "%" + (searchIn.contains("Buy") ? "sale" : "rent") + "%").setParameter("rids", regions).setParameter("searchCat", searchCat).setParameter("propertytype", "%" + propertytype + "%").setParameter("features", "%" + features + "%").setParameter("pricefrom", pricefrom).setParameter("priceto", priceto).setParameter("roomfrom", roomfrom).setParameter("roomto", roomto).setParameter("livingspacefrom", livingspacefrom).setParameter("livingspaceto", livingspaceto).setParameter("floorspacefrom", floorspacefrom).setParameter("flooorspaceto", flooorspaceto).setParameter("yearbuiltfrom", yearbuiltfrom).setParameter("yearbuiltto", yearbuiltto).getResultList();
        ArrayList<String> listToBuiltSqlQuery = new ArrayList<>();
        String _getUpdatedSearchIn = getUpdatedSearchIn(searchIn);
        String _getUpdatedRegionIds = getUpdatedRegionIds(regions);
        String _getUpdatedPropCats = getUpdatedPropCats(searchCat);
        String _getUpdatedWebMod = getUpdatedWebMods(websiteModule);
        String _getUpdatedPropTypes = getUpdatedPropTypes(propertytype);
        String _getUpdatedPropFeatures = getUpdatedPropFeatures(features);
        
        String _getUpdatedPropPrices = (searchIn.stream().filter(a -> a.equals("Buy")).collect(Collectors.toList()).size() > 0 && searchIn.stream().filter(a -> a.equals("Rent")).collect(Collectors.toList()).size() > 0 ? " (u.rentprice between "+ pricefrom +" and " + priceto + ") and (u.sellprice between "+ pricefrom +" and " + priceto + ") " : (searchIn.stream().filter(a -> a.equals("Buy")).collect(Collectors.toList()).size() > 0 ? "(u.sellprice between "+ pricefrom +" and " + priceto + ")" : (searchIn.stream().filter(a -> a.equals("Rent")).collect(Collectors.toList()).size() > 0 ? "(u.rentprice between "+ pricefrom +" and " + priceto + ")" : "")));
        String _getUpdatedPropRooms = " (u.numberofrooms between " + roomfrom + " and " + roomto + ") " ;
        
        String _getUpdatedPropLivingSpace = " (u.livingspace between " + livingspacefrom + " and " + livingspaceto + ") " ;
        
        String _getUpdatedPropFloorSpace = " (u.propertyfloor between " + floorspacefrom + " and " + flooorspaceto + ") " ;
        
        String _getUpdatedPropYearBuilt = " (u.yearbuilt between " + yearbuiltfrom + " and " + yearbuiltto + ") " ;
        
        String _getUpdatedPropVendorOption = GetVendorTypeVerified(vendortype);
        
        String _getUpdatedPublishDate = getPublishDateString(adpublishfrom, adpublishto);
        
        String _getUpdatedRecordDate = getRecordDateString(recorddatefrom, recorddateto);
        
        String _getUpdatedRecordTime = getRecordTimeString(recordtimefrom, recordtimeto);
        
        String _getUpdatedFlaggedProps = (Options.contains("fp") ? " (u.flaged_p is not null) " : " (u.flaged_p is null) ") + " order by u.id desc";
        
        if(_getUpdatedSearchIn != null && _getUpdatedSearchIn.isEmpty() == false){
            listToBuiltSqlQuery.add(_getUpdatedSearchIn);
        }
        
        if(_getUpdatedRegionIds != null && _getUpdatedRegionIds.isEmpty() == false){
            listToBuiltSqlQuery.add(_getUpdatedRegionIds);
        }
        
        if(_getUpdatedPropCats != null && _getUpdatedPropCats.isEmpty() == false){
            listToBuiltSqlQuery.add(_getUpdatedPropCats);
        }
        
        if(_getUpdatedWebMod != null && _getUpdatedWebMod.isEmpty() == false){
            listToBuiltSqlQuery.add(_getUpdatedWebMod);
        }
        
        if(_getUpdatedPropTypes != null && _getUpdatedPropTypes.isEmpty() == false){
            listToBuiltSqlQuery.add(_getUpdatedPropTypes);
        }
        
        if(_getUpdatedPropFeatures != null && _getUpdatedPropFeatures.isEmpty() == false){
            listToBuiltSqlQuery.add(_getUpdatedPropFeatures);
        }
        
        if((_getUpdatedPropPrices != null && _getUpdatedPropPrices.isEmpty() == false) && (pricefrom != null && pricefrom.isEmpty() == false) && (priceto != null && priceto.isEmpty() == false)){
            listToBuiltSqlQuery.add(_getUpdatedPropPrices);
        }
        
        if((_getUpdatedPropRooms != null && _getUpdatedPropRooms.isEmpty() == false) && (roomfrom != null && roomfrom.isEmpty() == false) && (roomto != null && roomto.isEmpty() == false)){
            listToBuiltSqlQuery.add(_getUpdatedPropRooms);
        }
        
        if((_getUpdatedPropLivingSpace != null && _getUpdatedPropLivingSpace.isEmpty() == false) && (livingspacefrom != null && livingspacefrom.isEmpty() == false) && (livingspaceto != null && livingspaceto.isEmpty() == false)){
            listToBuiltSqlQuery.add(_getUpdatedPropLivingSpace);
        }
        
        if((_getUpdatedPropFloorSpace != null && _getUpdatedPropFloorSpace.isEmpty() == false) && (floorspacefrom != null && floorspacefrom.isEmpty() == false) && (flooorspaceto != null && flooorspaceto.isEmpty() == false)){
            listToBuiltSqlQuery.add(_getUpdatedPropFloorSpace);
        }
        
        if((_getUpdatedPropYearBuilt != null && _getUpdatedPropYearBuilt.isEmpty() == false) && (yearbuiltfrom != null && yearbuiltfrom.isEmpty() == false) && (yearbuiltto != null && yearbuiltto.isEmpty() == false)){
            listToBuiltSqlQuery.add(_getUpdatedPropYearBuilt);
        }
        
        if((_getUpdatedPropVendorOption != null && _getUpdatedPropVendorOption.isEmpty() == false) && (vendortype != null && vendortype.size() > 0)){
            listToBuiltSqlQuery.add(_getUpdatedPropVendorOption);
        }
        
        if(_getUpdatedPublishDate != null && _getUpdatedPublishDate.isEmpty() == false){
            listToBuiltSqlQuery.add(_getUpdatedPublishDate);
        }
        
        if(_getUpdatedRecordDate != null && _getUpdatedRecordDate.isEmpty() == false){
            listToBuiltSqlQuery.add(_getUpdatedRecordDate);
        }
        
        if(_getUpdatedRecordTime != null && _getUpdatedRecordTime.isEmpty() == false){
            listToBuiltSqlQuery.add(_getUpdatedRecordTime);
        }
        
        if(_getUpdatedFlaggedProps != null && _getUpdatedFlaggedProps.isEmpty() == false){
            listToBuiltSqlQuery.add(_getUpdatedFlaggedProps);
        }
        
        String builtSqlQuery = listToBuiltSqlQuery.stream().map(n -> String.valueOf(n)).collect(Collectors.joining(" and ", "", ""));
                
        String hql = "FROM properties u where " + builtSqlQuery + " ";
        
        System.out.println("Output : " + hql);
                        
        return (List<properties>) EM.createQuery(hql).getResultList();
    
    }
    
    public String getUpdatedSearchIn(ArrayList<String> searchIn){
        String dtr = "";
        
        if(searchIn != null && searchIn.size() > 0){
            ArrayList<String> listToReturn = new ArrayList<>();

            searchIn.forEach((param) -> {
                listToReturn.add(param.contains("Buy") ? "'sale'" : "'" + param.toLowerCase() + "'");
            });

            dtr = listToReturn.stream().map(n -> String.valueOf(n)).collect(Collectors.joining(",", "", ""));
            
            dtr = "(u.searchtype in (" + dtr + "))";
        }
        
        return dtr;
    }
    
    public String getUpdatedRegionIds(ArrayList<Integer> regionIds){
        String dtr = "";
        
        if(regionIds != null && regionIds.size() > 0){
            ArrayList<Integer> listToReturn = new ArrayList<>();

            regionIds.forEach((param) -> {
                listToReturn.add(param);
            });

            dtr = listToReturn.stream().map(n -> String.valueOf(n)).collect(Collectors.joining(",", "", ""));
            
            dtr = "(u.region_id in (" + dtr + "))";
        }
        
        return dtr;
    }
    
    public String getUpdatedPropCats(ArrayList<String> websiteModule){
        String dtr = "";
        
        if(websiteModule != null && websiteModule.size() > 0){
            ArrayList<String> listToReturn = new ArrayList<>();

            websiteModule.forEach((param) -> {
                listToReturn.add("'" + param.toLowerCase() + "'");
            });

            dtr = listToReturn.stream().map(n -> String.valueOf(n)).collect(Collectors.joining(",", "", ""));
            
            dtr = "(u.catofproperty in (" + dtr + "))";
        }
        
        return dtr;
    }
    
    public String getUpdatedWebMods(ArrayList<String> searchCat){
        String dtr = "";
        
        if(searchCat != null && searchCat.size() > 0){
            ArrayList<String> listToReturn = new ArrayList<>();

            searchCat.forEach((param) -> {
                listToReturn.add("'" + param.toLowerCase() + "'");
            });

            dtr = listToReturn.stream().map(n -> String.valueOf(n)).collect(Collectors.joining(",", "", ""));
            
            dtr = "(u.website in (" + dtr + "))";
        }
        
        return dtr;
    }
    
    public String getUpdatedPropTypes(ArrayList<String> propTypes){
        String dtr = "";
        
        if(propTypes != null && propTypes.size() > 0){
            ArrayList<String> listToReturn = new ArrayList<>();

            propTypes.forEach((param) -> {
                listToReturn.add("'" + param.toLowerCase() + "'");
            });

            dtr = listToReturn.stream().map(n -> String.valueOf(n)).collect(Collectors.joining(",", "", ""));
            
            dtr = "(u.propertytype in (" + dtr + "))";
        }
        
        return dtr;
    }
    
    public String getUpdatedPropFeatures(ArrayList<String> propFeatures){
        String dtr = "";
        
        if(propFeatures != null && propFeatures.size() > 0){
            ArrayList<String> listToReturn = new ArrayList<>();

            propFeatures.forEach((param) -> {
                listToReturn.add("'" + param.toLowerCase() + "'");
            });

            dtr = listToReturn.stream().map(n -> String.valueOf(n)).collect(Collectors.joining(",", "", ""));
            
            dtr = "(u.propertyfeatures in (" + dtr + "))";
        }
        
        return dtr;
    }
    
    public String getPublishDateString(String adpublishfrom, String adpublishto){
        
        String str = "";        
        
        if(adpublishfrom.isEmpty() == false && adpublishto.isEmpty() == false){
            str = " (u.publishdate between '" + convertDateFormat(adpublishfrom) + "' and '" + convertDateFormat(adpublishto) + "') ";
        }
        
        return str;
    }
    
    public String getRecordDateString(String recordDateFrom, String recordDateTo){
        
        String str = "";        
        
        if(recordDateFrom.isEmpty() == false && recordDateTo.isEmpty() == false){
            str = " (u.date_ between '" + convertDateFormat(recordDateFrom) + "' and '" + convertDateFormat(recordDateTo) + "') ";
        }
        
        return str;
    }
    
    public String convertDateFormat(String adDate){
        String str = "";
        
        try{
            
            SimpleDateFormat incomingDateFormat = new SimpleDateFormat("dd-MM-yyyy");
            Date _adDate_ = incomingDateFormat.parse(adDate);
            
            SimpleDateFormat newDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            str = newDateFormat.format(_adDate_);
            
        }catch(ParseException e){
            SimpleDateFormat newDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date dNow = new Date();
            str = newDateFormat.format(dNow);
        }
        
        return str;
    }
    
    public String getRecordTimeString(String recordTimeFrom, String recordTimeTo){
        
        String str = "";        
        
        if(recordTimeFrom.isEmpty() == false && recordTimeTo.isEmpty() == false){
            str = " (u.time_ between '" + recordTimeFrom + "' and '" + recordTimeTo + "') ";
        }
        
        return str;
    }
    
    public String GetVendorTypeVerified(ArrayList<String> vendorType){
        
        String str = "";
        
        if((vendorType.stream().filter(a -> a.equals("Unassigned vendor")).collect(Collectors.toList()).size() > 0 || vendorType.stream().filter(a -> a.equals("Private vendor")).collect(Collectors.toList()).size() > 0) && (vendorType.stream().filter(a -> a.equals("Institutional vendor")).collect(Collectors.toList()).isEmpty())){
            str = " (u.agencyname = '') ";
        }else if((vendorType.stream().filter(a -> a.equals("Unassigned vendor")).collect(Collectors.toList()).isEmpty() && vendorType.stream().filter(a -> a.equals("Private vendor")).collect(Collectors.toList()).isEmpty()) && vendorType.stream().filter(a -> a.equals("Institutional vendor")).collect(Collectors.toList()).size() > 0){
            str = " (u.agencyname != '') ";
        }else if((vendorType.stream().filter(a -> a.equals("Unassigned vendor")).collect(Collectors.toList()).size() > 0 || vendorType.stream().filter(a -> a.equals("Private vendor")).collect(Collectors.toList()).size() > 0) && (vendorType.stream().filter(a -> a.equals("Institutional vendor")).collect(Collectors.toList()).size() > 0)){
            str = "";
        }
                
        return str;
    }
    
    @Override
    public List<String> GetAllCategories(){
        String hql = "SELECT DISTINCT catofproperty FROM properties";
	List<String> getRows = EM.createQuery(hql).getResultList();
        return getRows.isEmpty() ? null : getRows;
    }
    
    @Override
    public List<String> GetAllPropTypes(){
        String hql = "SELECT DISTINCT propertytype FROM properties";
	List<String> getRows = EM.createQuery(hql).getResultList();
        return getRows.isEmpty() ? null : getRows;
    }
    
    @Override
    public List<String> GetAllPropFeatures(){
        String hql = "SELECT DISTINCT propertyfeatures FROM properties";
	List<String> getRows = EM.createQuery(hql).getResultList();
        return getRows.isEmpty() ? null : getRows;
    }
    
    //@Override
    //public user getUserbyemailpassword(String email, String password) {
    //String hql = "FROM user as u WHERE u.email = :email and u.password = :password";
    //List<user> getSingleRow = EM.createQuery(hql).setParameter( "email", email).setParameter("password", password).getResultList();
    //return getSingleRow.isEmpty() ? null : getSingleRow.get(0);
    //}
}
