/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.tmrmswebscrapper.Repos;

import com.example.tmrmswebscrapper.Models.PropertyCsv;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 *
 * @author User
 */
@Repository
public class CsvService {
    
  @Autowired
  JdbcTemplate jdbcTemplate;
  public ArrayList<PropertyCsv> propsList(String id) {
    ArrayList<PropertyCsv> list = new ArrayList<>();
    PropertyCsv Obj = null;
    final String sql = "SELECT * FROM properties WHERE cate_id = ?";
    
    List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql , new Object[]{id});
    for(Map<String, Object> row : rows){
        SimpleDateFormat newDateFormat = new SimpleDateFormat("dd.MM.yyyy");
        Date publishDate = (Date) row.get("publishdate");
        Date updateDate = (Date) row.get("updatedate");
        Date reacordDate = (Date) row.get("date_");
        list.add(new PropertyCsv(newDateFormat.format(publishDate).toString(), newDateFormat.format(updateDate).toString(), (String)row.get("searchtype"), (String)row.get("msregion"), (String)row.get("catofproperty"), (String)row.get("propertytype"), (String)row.get("titleofadd"), (String)row.get("streetnumber"), (String)row.get("postalcode"), (String)row.get("city"), (String)row.get("sellprice"), (String)row.get("sellgrossprice"), (String)row.get("sellnetprice"), (String)row.get("sellpaymenttype"), (String)row.get("rentprice"), (String)row.get("rentgrossprice"), (String)row.get("rentnetprice"),(String)row.get("rentpaymenttype"), (String)row.get("numberofrooms"), (String)row.get("livingspace"), (String)row.get("propertyfloor"), (String)row.get("propertyarea"), (String)row.get("propertydesc"), (String)row.get("propertyavailable"), (String)row.get("yearbuilt"), (String)row.get("nameofseller"), (String)row.get("streetnumberofseller"), (String)row.get("postalcodeofseller"), (String)row.get("cityofseller"), (String)row.get("phoneofseller"), (String)row.get("mnumberofseller"), (String)row.get("emailofseller"), (String)row.get("contactname"), (String)row.get("contactnumber"), (String)row.get("agencyname"), (String)row.get("urlofad"), newDateFormat.format(reacordDate).toString()));
    }
 
    
    return list;
  }
  
  @Autowired
  JdbcTemplate jdbcTemplatee;
  public PropertyCsv singleProp(String id) {
    PropertyCsv Obj = null;
    
    final String sql = "SELECT * FROM properties WHERE id = ?";
    
    List<Map<String, Object>> rows = jdbcTemplatee.queryForList(sql , new Object[]{id});
    
    for(Map<String, Object> row : rows){
        SimpleDateFormat newDateFormat = new SimpleDateFormat("dd.MM.yyyy");
        Date publishDate = (Date) row.get("publishdate");
        Date updateDate = (Date) row.get("updatedate");
        Date reacordDate = (Date) row.get("date_");
        Obj = new PropertyCsv(newDateFormat.format(publishDate).toString(), newDateFormat.format(updateDate).toString(), (String)row.get("searchtype"), (String)row.get("msregion"), (String)row.get("catofproperty"), (String)row.get("propertytype"), (String)row.get("titleofadd"), (String)row.get("streetnumber"), (String)row.get("postalcode"), (String)row.get("city"), (String)row.get("sellprice"), (String)row.get("sellgrossprice"), (String)row.get("sellnetprice"), (String)row.get("sellpaymenttype"), (String)row.get("rentprice"), (String)row.get("rentgrossprice"), (String)row.get("rentnetprice"),(String)row.get("rentpaymenttype"), (String)row.get("numberofrooms"), (String)row.get("livingspace"), (String)row.get("propertyfloor"), (String)row.get("propertyarea"), (String)row.get("propertydesc"), (String)row.get("propertyavailable"), (String)row.get("yearbuilt"), (String)row.get("nameofseller"), (String)row.get("streetnumberofseller"), (String)row.get("postalcodeofseller"), (String)row.get("cityofseller"), (String)row.get("phoneofseller"), (String)row.get("mnumberofseller"), (String)row.get("emailofseller"), (String)row.get("contactname"), (String)row.get("contactnumber"), (String)row.get("agencyname"), (String)row.get("urlofad"), newDateFormat.format(reacordDate).toString());
    }
    
    return Obj;
  }
}
