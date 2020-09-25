/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.tmrmswebscrapper.Repos;

import com.example.tmrmswebscrapper.Entity.mrcities;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Smr
 */
@Repository
public class MrCityDAO implements MrCityDAOI{
    @PersistenceContext
    @SuppressWarnings("unchecked")
    private EntityManager EM;
    
    @Override
    public List<mrcities> getFilteredMrCities(int rid){
        String hql = "FROM mrcities where rid = :rid order by id DESC";
        return (List<mrcities>) EM.createQuery(hql).setParameter("rid", rid).getResultList();
    }
    
    @Override
    public List<mrcities> getMrCities(){
        String hql = "FROM mrcities order by rid ASC";
        return (List<mrcities>) EM.createQuery(hql).getResultList();
    }
    
    @Override
    public List<mrcities> getFilteredMrCities(ArrayList<Integer> rids){
        String hql = "FROM mrcities where rid in (:rids)";
        return (List<mrcities>) EM.createQuery(hql).setParameter("rids", rids).getResultList();
    }
}
