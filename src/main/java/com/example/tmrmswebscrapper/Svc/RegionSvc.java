/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.tmrmswebscrapper.Svc;

import com.example.tmrmswebscrapper.Entity.msregions;
import com.example.tmrmswebscrapper.Models.MsRegion;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;
import com.example.tmrmswebscrapper.Repos.MsRegionDAOI;
import java.util.List;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
/**
 *
 * @author Smr
 */
@Service
@Transactional
public class RegionSvc implements RegionSvcI {
    private MsRegionDAOI mDAO;
    @Autowired
    public RegionSvc(MsRegionDAOI DAO) {
        this.mDAO = DAO;
    }
    
    @Override
    public List<MsRegion> getMsRegions(){
        List<msregions> dfdb =  this.mDAO.getMsRegions();
        List<MsRegion> dtf = new ArrayList<>();
        dfdb.stream().map((dfe) -> {
            MsRegion f = new MsRegion(Integer.toString(dfe.getId()),dfe.getFull_name(), Integer.toString(this.mDAO.totalCitiesInRegion(dfe.getId())));
            return f;
        }).forEachOrdered((f) -> {
            dtf.add(f);
        });
        return dtf;
    }
    
    @Override
    public MsRegion getMsRegionByName(String nameOfRegion){
        msregions dfdb =  this.mDAO.getRegionByName(nameOfRegion);
        MsRegion dtf = new MsRegion(Integer.toString(dfdb.getId()), dfdb.getFull_name(),Integer.toString(this.mDAO.totalCitiesInRegion(dfdb.getId())));
        return dtf;
    }
    
    @Override
    public MsRegion getMsRegionById(int idOfRegion){
        msregions dfdb =  this.mDAO.getRegionById(idOfRegion);
        MsRegion dtf = new MsRegion(Integer.toString(dfdb.getId()), dfdb.getFull_name(),Integer.toString(this.mDAO.totalCitiesInRegion(dfdb.getId())));
        return dtf;
    }
}
