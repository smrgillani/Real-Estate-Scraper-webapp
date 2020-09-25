/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.tmrmswebscrapper.Repos;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import com.example.tmrmswebscrapper.Entity.msregions;
/**
 *
 * @author Smr
 */
@Repository
public class MsRegionDAO implements MsRegionDAOI {
    @PersistenceContext
    @SuppressWarnings("unchecked")
    private EntityManager EM;
    
    @Override
    public List<msregions> getMsRegions(){
        String hql = "FROM msregions u order by u.rid asc";
        return (List<msregions>) EM.createQuery(hql).getResultList();
    }
    
    @Override
    public msregions getRegionByName(String nameOfRegion){
        String hql = "FROM msregions WHERE fullname = :rName";
	List<msregions> getSingleRow = EM.createQuery(hql).setParameter("rName", nameOfRegion).getResultList();
        return getSingleRow.isEmpty() ? null : getSingleRow.get(0);
    }
    
    @Override
    public msregions getRegionById(int idOfRegion){
        String hql = "FROM msregions WHERE rid = :rId";
	List<msregions> getSingleRow = EM.createQuery(hql).setParameter("rId", idOfRegion).getResultList();
        return getSingleRow.isEmpty() ? null : getSingleRow.get(0);
    }
    
    @Override
    public int totalCitiesInRegion(int ID){
        String hql = "SELECT COUNT(DISTINCT(fullname)) FROM mrcities WHERE rid = :rid";
        return ((Number) EM.createNativeQuery(hql).setParameter("rid", ID).getSingleResult()).intValue();
    }
}
