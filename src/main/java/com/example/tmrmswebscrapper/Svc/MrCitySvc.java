/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.tmrmswebscrapper.Svc;

import com.example.tmrmswebscrapper.Entity.mrcities;
import com.example.tmrmswebscrapper.Models.MrCity;
import com.example.tmrmswebscrapper.Repos.MrCityDAOI;
import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Smr
 */
@Service
@Transactional
public class MrCitySvc implements MrCitySvcI{
    private MrCityDAOI mrDAO;
    @Autowired
    public MrCitySvc(MrCityDAOI cDAO) {
        this.mrDAO = cDAO;
    }
    
    @Override
    public List<MrCity> getMrCities(){
        List<mrcities> dfdb =  this.mrDAO.getMrCities();
        List<MrCity> dtf = new ArrayList<>();
        dfdb.stream().map((dfe) -> {
            MrCity f = new MrCity(Integer.toString(dfe.getId()), Integer.toString(dfe.getMsregions().getId()), dfe.getFull_name(), dfe.getMsregions().getFull_name());
            return f;
        }).forEachOrdered((f) -> {
            dtf.add(f);
        });
        return dtf;
    }
    
    @Override
    public List<MrCity> getFilteredMrCities(int rid){
        List<mrcities> dfdb =  this.mrDAO.getFilteredMrCities(rid);
        List<MrCity> dtf = new ArrayList<>();
        dfdb.stream().map((dfe) -> {
            MrCity f = new MrCity(Integer.toString(dfe.getId()), Integer.toString(dfe.getMsregions().getId()), dfe.getFull_name(), dfe.getMsregions().getFull_name());
            return f;
        }).forEachOrdered((f) -> {
            dtf.add(f);
        });
        return dtf;
    }
    
    @Override
    public List<MrCity> getFilteredMrCities(ArrayList<Integer> rids){
        List<mrcities> dfdb =  this.mrDAO.getFilteredMrCities(rids);
        List<MrCity> dtf = new ArrayList<>();
        dfdb.stream().map((dfe) -> {
            MrCity f = new MrCity(Integer.toString(dfe.getId()), Integer.toString(dfe.getMsregions().getId()), dfe.getFull_name(), dfe.getMsregions().getFull_name());
            return f;
        }).forEachOrdered((f) -> {
            dtf.add(f);
        });
        return dtf;
    }
}
