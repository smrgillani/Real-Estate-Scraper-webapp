/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.tmrmswebscrapper.Svc;

import com.example.tmrmswebscrapper.Models.MsRegion;
import java.util.List;

/**
 *
 * @author Smr
 */
public interface RegionSvcI {
    List<MsRegion> getMsRegions();
    MsRegion getMsRegionByName(String nameOfRegion);
    MsRegion getMsRegionById(int idOfRegion);
}
