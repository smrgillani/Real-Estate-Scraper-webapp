/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.tmrmswebscrapper.Repos;

import com.example.tmrmswebscrapper.Entity.mrcities;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Smr
 */
public interface MrCityDAOI {
    List<mrcities> getMrCities();
    List<mrcities> getFilteredMrCities(int rid);
    List<mrcities> getFilteredMrCities(ArrayList<Integer> rids);
}
