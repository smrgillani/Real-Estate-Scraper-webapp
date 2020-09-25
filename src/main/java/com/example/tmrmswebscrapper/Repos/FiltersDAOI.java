/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.tmrmswebscrapper.Repos;
import com.example.tmrmswebscrapper.Entity.fcategorytypes;
import com.example.tmrmswebscrapper.Entity.fcities;
import com.example.tmrmswebscrapper.Entity.filters;
import com.example.tmrmswebscrapper.Entity.fpropertyfeatures;
import com.example.tmrmswebscrapper.Entity.fpropertytypes;
import com.example.tmrmswebscrapper.Entity.fsellingtypes;
import com.example.tmrmswebscrapper.Entity.fvendortypes;
import java.util.List;
/**
 *
 * @author Lubbow
 */
public interface FiltersDAOI {
    Integer addNewF(filters f);
    boolean deletebyId(Integer id);

    filters getFbyID(Integer ID);
    List<filters> getFirstFiveFilters();
    List<filters> getSearchedFilters(String sString);

    Integer addNewCTF(fcategorytypes ctf);
    boolean deleteCTFByName(String Name);

    Integer addNewCF(fcities cf);
    boolean deleteCFByName(String Name);

    Integer addNewPFF(fpropertyfeatures pff);
    boolean deletePFFByName(String Name);

    Integer addNewSTF(fsellingtypes stf);
    boolean deleteSTFByName(String Name);
    
    Integer addNewVTF(fvendortypes vtf);
    boolean deleteVTFByName(String Name);

    Integer addNewPTF(fpropertytypes ptf);
    boolean deletePTFByName(String Name);
    
    filters updateFilter(filters f);
    
}
