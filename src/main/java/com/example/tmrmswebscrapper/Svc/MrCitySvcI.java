/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.tmrmswebscrapper.Svc;

import com.example.tmrmswebscrapper.Models.MrCity;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Smr
 */
public interface MrCitySvcI {
    List<MrCity> getMrCities();
    List<MrCity> getFilteredMrCities(int rid);
    List<MrCity> getFilteredMrCities(ArrayList<Integer> rids);
}
