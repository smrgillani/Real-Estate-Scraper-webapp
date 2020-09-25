/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.tmrmswebscrapper.Repos;

import java.util.List;
import com.example.tmrmswebscrapper.Entity.msregions;
/**
 *
 * @author Smr
 */
public interface MsRegionDAOI {
    List<msregions> getMsRegions();
    int totalCitiesInRegion(int ID);
    msregions getRegionByName(String nameOfRegion);
    msregions getRegionById(int idOfRegion);
}
