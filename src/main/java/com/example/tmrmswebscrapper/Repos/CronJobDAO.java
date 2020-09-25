/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.tmrmswebscrapper.Repos;

import com.example.tmrmswebscrapper.Entity.cronjobs;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Smr
 */
@Repository
public class CronJobDAO implements CronJobDAOI {
    @PersistenceContext
    @SuppressWarnings("unchecked")
    private EntityManager EM;
    
    @Override
    public Integer addNew(cronjobs cj){
        EM.persist(cj);
        return cj.getId();
    }
    
    @Override
    public cronjobs getCJbyID(Integer ID) {
        String hql = "FROM cronjobs as u WHERE u.id = :cjid";
	List<cronjobs> getSingleRow = EM.createQuery(hql).setParameter("cjid", ID).getResultList();
        return getSingleRow.isEmpty() ? null : getSingleRow.get(0);
    }
    
    @Override
    public boolean getInBetweenTheTime(String sTime, String eTime) {
        String hql = "FROM cronjobs WHERE scheduledtime between :stime and :etime";
	List<cronjobs> getSingleRow = EM.createQuery(hql).setParameter("stime", sTime ).setParameter("etime", eTime ).getResultList();
        return getSingleRow.isEmpty();
    }
    
    @Override
    public cronjobs getByTime(String Time) {
        String hql = "FROM cronjobs WHERE scheduledtime like :time ";
	List<cronjobs> getSingleRow = EM.createQuery(hql).setParameter("time", "%" + Time + "%" ).getResultList();
        return getSingleRow.isEmpty() ? null : getSingleRow.get(0);
    }
    
    @Override
    public Integer updateCJ(cronjobs cj){
        Query query = EM.createNativeQuery("update cronjobs set nameofjob = :nameofjob, filterid = :filterid, scheduledtime = :scheduledtime, routinerunoption = :routinerunoption, scheduleddate = :scheduleddate where id = :id");
        query.setParameter("id", cj.getId());
        query.setParameter("nameofjob", cj.getNameofjob());
        query.setParameter("filterid", cj.getFilterid());
        query.setParameter("scheduledtime", cj.getScheduledtime());
        query.setParameter("routinerunoption", cj.getRoutinerunoption());
        query.setParameter("scheduleddate", cj.getScheduleddate());
        return query.executeUpdate();
    }
    
    @Override
    public boolean deletebyId(Integer id) {
        Query query = EM.createNativeQuery("delete from cronjobs where id = :cjid");
        query.setParameter("cjid", id);
        int count = query.executeUpdate();
        return count > 0;
    }
    
    @Override
    public List<cronjobs> getCronJobs(){
        String hql = "FROM cronjobs u order by u.id DESC";
        return (List<cronjobs>) EM.createQuery(hql).getResultList();
    }
    
}