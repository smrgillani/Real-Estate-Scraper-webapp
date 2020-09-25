/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.tmrmswebscrapper.Repos;

import com.example.tmrmswebscrapper.Entity.blocked_vendors;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Lubbow
 */
@Repository
public class BlockedVendorsDAO implements BlockedVendorsDAOI {
    @PersistenceContext
    @SuppressWarnings("unchecked")
    private EntityManager EM;
    
    @Override
    public Integer addNew(blocked_vendors p){
        EM.persist(p);
        return p.getId();
    }
    
    @Override
    public Integer getVbyIdAndWS(String VendorId, String WebSite){
        String hql = "FROM blocked_vendors as u WHERE u.vendor_id = :vid and u.website = :ws";
	List<blocked_vendors> getSingleRow = EM.createQuery(hql).setParameter( "vid", VendorId).setParameter( "ws", WebSite).getResultList();
        return getSingleRow.isEmpty() ? 0 : getSingleRow.get(0).getId();
    }
    
    @Override
    public List<blocked_vendors> getFirstFiveVendors(){
        String hql = "FROM blocked_vendors u order by u.id desc";
        return (List<blocked_vendors>) EM.createQuery(hql).getResultList();
    }
    
    @Override
    public boolean deletebyId(Integer id) {
        Query query = EM.createNativeQuery("delete from blocked_vendors where id = :vid");
        query.setParameter("vid", id);
        int count = query.executeUpdate();
        return count > 0;
    }
}
