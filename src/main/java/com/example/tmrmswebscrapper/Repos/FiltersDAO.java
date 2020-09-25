/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.tmrmswebscrapper.Repos;

import com.example.tmrmswebscrapper.Entity.filters;
import com.example.tmrmswebscrapper.Entity.fcategorytypes;
import com.example.tmrmswebscrapper.Entity.fcities;
import com.example.tmrmswebscrapper.Entity.fpropertyfeatures;
import com.example.tmrmswebscrapper.Entity.fpropertytypes;
import com.example.tmrmswebscrapper.Entity.fsellingtypes;
import com.example.tmrmswebscrapper.Entity.fvendortypes;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
/**
 *
 * @author Lubbow
 */
@Repository
public class FiltersDAO implements FiltersDAOI{
    @PersistenceContext
    @SuppressWarnings("unchecked")
    private EntityManager EM;
    
    @Autowired
    private EntityManagerFactory emf;
    
    @Override
    public Integer addNewF(filters f){
        
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        
        em.persist(f);
        
        f.getFcategorytype().stream().map((dfe) -> {
            dfe.setFilter(f);
            return dfe;
        }).forEachOrdered((dfe) -> {
            em.persist(dfe);
        });
        
        f.getFcities().stream().map((dfe) -> {
            dfe.setFilter(f);
            return dfe;
        }).forEachOrdered((dfe) -> {
            em.persist(dfe);
        });
        
        f.getFpropertyfeatures().stream().map((dfe) -> {
            dfe.setFilter(f);
            return dfe;
        }).forEachOrdered((dfe) -> {
            em.persist(dfe);
        });
        
        f.getFsellingtype().stream().map((dfe) -> {
            dfe.setFilter(f);
            return dfe;
        }).forEachOrdered((dfe) -> {
            em.persist(dfe);
        });
        
        
        f.getFvendortype().stream().map((dfe) -> {
            dfe.setFilter(f);
            return dfe;
        }).forEachOrdered((dfe) -> {
            em.persist(dfe);
        });
        
        f.getFpropertytype().stream().map((dfe) -> {
            dfe.setFilter(f);
            return dfe;
        }).forEachOrdered((dfe) -> {
            em.persist(dfe);
        });
        
        tx.commit();
        em.close();
        
        return f.getFid();
        
    }
    
    @Override
    public Integer addNewCTF(fcategorytypes ctf){
        EM.persist(ctf);
        return ctf.getId();
    }
    
    @Override
    public boolean deleteCTFByName(String Name){
        Query query = EM.createNativeQuery("delete from fcategorytypes where nameofcat = :name");
        query.setParameter("name", Name);
        int count = query.executeUpdate();
        return count > 0;
    }
    
    @Override
    public filters updateFilter(filters f){
        Query query = EM.createNativeQuery("update filters set nameoffilter =:nameoffilter, pricefrom =:pricefrom, priceto =:priceto, roomsfrom =:roomsfrom, roomsto =:roomsto, livingspacefrom =:livingspacefrom, livingspaceto =:livingspaceto, floorspaceplotareafrom =:floorspaceplotareafrom, floorspaceplotareato =:floorspaceplotareato, builtyearfrom =:builtyearfrom, builtyearto =:builtyearto, availablefrom =:availablefrom, availableto =:availableto where fid = :idoffilter");
        
        query.setParameter("idoffilter", f.getFid());
        
        query.setParameter("nameoffilter", f.getNameoffilter());
        query.setParameter("pricefrom", f.getPricefrom());
        query.setParameter("priceto", f.getPriceto());
        
        query.setParameter("roomsfrom", f.getRoomsfrom());
        query.setParameter("roomsto", f.getRoomsto());
        
        query.setParameter("livingspacefrom", f.getLivingspacefrom());
        query.setParameter("livingspaceto", f.getLivingspaceto());
        
        query.setParameter("floorspaceplotareafrom", f.getFloorspaceplotareafrom());
        query.setParameter("floorspaceplotareato", f.getFloorspaceplotareato());
        
        query.setParameter("builtyearfrom", f.getBuiltyearfrom());
        query.setParameter("builtyearto", f.getBuiltyearto());
        
        query.setParameter("availablefrom", f.getAvailablefrom());
        query.setParameter("availableto", f.getAvailableto());
        
        int count = query.executeUpdate();
        return count > 0 ? f : null;
    }
    
    @Override
    public Integer addNewCF(fcities cf){
        EM.persist(cf);
        return cf.getId();
    }
    
    @Override
    public boolean deleteCFByName(String Name){
        Query query = EM.createNativeQuery("delete from fcities where idofregion = :name");
        query.setParameter("name", Name);
        int count = query.executeUpdate();
        return count > 0;
    }
    
    @Override
    public Integer addNewPFF(fpropertyfeatures pff){
        EM.persist(pff);
        return pff.getId();
    }
    
    @Override
    public boolean deletePFFByName(String Name){
        Query query = EM.createNativeQuery("delete from fpropertyfeatures where nameoffeature = :name");
        query.setParameter("name", Name);
        int count = query.executeUpdate();
        return count > 0;
    }
    
    @Override
    public Integer addNewSTF(fsellingtypes stf){
        EM.persist(stf);
        return stf.getId();
    }
    
    @Override
    public boolean deleteSTFByName(String Name){
        Query query = EM.createNativeQuery("delete from fsellingtypes where nameofsellingtype = :name");
        query.setParameter("name", Name);
        int count = query.executeUpdate();
        return count > 0;
    }
    
    @Override
    public Integer addNewVTF(fvendortypes vtf){
        EM.persist(vtf);
        return vtf.getId();
    }
    
    @Override
    public boolean deleteVTFByName(String Name){
        Query query = EM.createNativeQuery("delete from fvendortypes where nameofvendortype = :name");
        query.setParameter("name", Name);
        int count = query.executeUpdate();
        return count > 0;
    }
    
    @Override
    public Integer addNewPTF(fpropertytypes ptf){
        EM.persist(ptf);
        return ptf.getId();
    }
    
    @Override
    public boolean deletePTFByName(String Name){
        Query query = EM.createNativeQuery("delete from fpropertytypes where nameoftype = :name");
        query.setParameter("name", Name);
        int count = query.executeUpdate();
        return count > 0;
    }
    
    @Override
    public boolean deletebyId(Integer id){
        Query query = EM.createNativeQuery("delete from filters where fid = :fid");
        query.setParameter("fid", id);
        int count = query.executeUpdate();
        return count > 0;
    }
    
    @Override
    public List<filters> getFirstFiveFilters(){
        String hql = "FROM filters u order by u.fid desc";
        return (List<filters>) EM.createQuery(hql).getResultList();
        //return (List<filters>) EM.createQuery(hql).setMaxResults(5).getResultList();
    }
    
    @Override
    public List<filters> getSearchedFilters(String sString){
        String hql = "FROM filters u where u.nameoffilter like CONCAT('%',:sstrng,'%') order by u.fid desc";
        return (List<filters>) EM.createQuery(hql).setParameter( "sstrng", sString).getResultList();
    }
    
    @Override
    public filters getFbyID(Integer ID){
        String hql = "FROM filters as u WHERE u.fid = :fid";
	List<filters> getSingleRow = EM.createQuery(hql).setParameter("fid", ID).getResultList();
        return getSingleRow.isEmpty() ? null : getSingleRow.get(0);
    }
}
