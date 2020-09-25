/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.tmrmswebscrapper.Repos;


import com.example.tmrmswebscrapper.Models.PropertyList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;



/**
 *
 * @author User
 */
@Repository
public class PropertyService{

  private final static String INSERT_SQL = "insert into property_manager (datetime,website) values (?,?)";

  @Autowired
  JdbcTemplate jdbcTemplate;
  public long createDateTime(String dateTime , String webSite) {
    KeyHolder key = new GeneratedKeyHolder();
    jdbcTemplate.update((Connection connection) -> {
        final PreparedStatement ps = connection.prepareStatement(INSERT_SQL,Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, dateTime);
        ps.setString(2, webSite);
        return ps;
    }, key);
    
//    jdbcTemplate.query(
//                "SELECT id, first_name, last_name FROM customers WHERE first_name = ?", new Object[] { "Josh" },
//                (rs, rowNum) -> new Customer(rs.getLong("id"), rs.getString("first_name"), rs.getString("last_name"))
//        ).forEach(customer -> log.info(customer.toString()));
    return key.getKey().longValue();
  }
  
  @Autowired
  JdbcTemplate jdbcTemplatee;
  public ArrayList<PropertyList> getListOfDatesTime() {
    ArrayList<PropertyList> list = new ArrayList<>();
    
    final String sql = "SELECT * FROM property_manager";
    
    List<Map<String, Object>> rows = jdbcTemplatee.queryForList(sql);
    rows.stream().map((row) -> new PropertyList((int)(row.get("id")),(String)row.get("datetime"),(String)row.get("website"))).forEachOrdered(list::add);
    
//    select with parameter
//    jdbcTemplate.query("SELECT * FROM property_manager WHERE first_name = ?", new Object[] { "Josh" },
//                (rs, rowNum) -> new Customer(rs.getLong("id"), rs.getString("first_name"), rs.getString("last_name"))
//        ).forEach(customer -> log.info(customer.toString()));
    return list;
  }
  
}
