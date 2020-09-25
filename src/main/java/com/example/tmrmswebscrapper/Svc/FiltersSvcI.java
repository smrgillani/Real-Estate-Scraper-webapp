/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.tmrmswebscrapper.Svc;
import com.example.tmrmswebscrapper.Models.Filter;
import java.util.List;

/**
 *
 * @author Lubbow
 */
public interface FiltersSvcI {
    Integer addNewF(Filter fm);
    Filter getFById(Integer id);
    List<Filter> getFirstFiveFilters();
    List<Filter> getSearchedFilters(String sP);
    boolean deleteFilterById(Integer id);
    Integer updateFilter(Filter fm, List<String> fCategoryTypeToDel, List<String> fCityToDel, List<String> fPropertyFeatureToDel, List<String> fSellingTypeToDel, List<String> fVendorTypeToDel, List<String> fPropertyTypeToDel);
}
