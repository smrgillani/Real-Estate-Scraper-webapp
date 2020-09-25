/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.tmrmswebscrapper.Repos;

import com.example.tmrmswebscrapper.Entity.blocked_vendors;
import java.util.List;

/**
 *
 * @author Lubbow
 */
public interface BlockedVendorsDAOI {
    Integer addNew(blocked_vendors p);
    Integer getVbyIdAndWS(String VendorId, String WebSite);
    List<blocked_vendors> getFirstFiveVendors();
    boolean deletebyId(Integer id);
}
