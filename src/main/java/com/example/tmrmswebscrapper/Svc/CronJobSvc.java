/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.tmrmswebscrapper.Svc;

import com.example.tmrmswebscrapper.Entity.cronjobs;
import com.example.tmrmswebscrapper.Models.CronJob;
import com.example.tmrmswebscrapper.Repos.CronJobDAOI;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
public class CronJobSvc implements CronJobSvcI {
    private final CronJobDAOI cjDAO;
    
    @Autowired
    private FiltersSvcI fSvc;
    
    @Autowired
    public CronJobSvc(CronJobDAOI DAO) {
        this.cjDAO = DAO;
    }
    
    @Override
    public Integer createCronJob(CronJob cj){
        
        Date dNow = new Date( );
        SimpleDateFormat ct = new SimpleDateFormat ("hh:mm:ss a");
        SimpleDateFormat cd = new SimpleDateFormat ("yyyy.MM.dd");
        SimpleDateFormat cm = new SimpleDateFormat ("MMM");
        SimpleDateFormat cy = new SimpleDateFormat ("yyyy");
                            
        cronjobs o = new cronjobs();
        o.setNameofjob(cj.getNameOfJob());
        o.setFilterid(Integer.parseInt(cj.getFilterId()));
        o.setScheduledtime(cj.getScheduleTime());
        o.setRoutinerunoption(Integer.parseInt(cj.getRoutineRunOption()));
        o.setScheduleddate(cj.getScheduledDate());
        o.setDate_(cd.format(dNow)); 
        o.setTime_(ct.format(dNow)); 
        o.setMonth_(cm.format(dNow)); 
        o.setYear_(cy.format(dNow));
        
        return cjDAO.addNew(o);
    }
    
    @Override
    public Integer updateCronJob(CronJob cj){
        
        cronjobs o = new cronjobs();
        o.setId(cj.getId());
        o.setNameofjob(cj.getNameOfJob());
        o.setFilterid(Integer.parseInt(cj.getFilterId()));
        o.setScheduledtime(cj.getScheduleTime());
        o.setRoutinerunoption(Integer.parseInt(cj.getRoutineRunOption()));
        o.setScheduleddate(cj.getScheduledDate());
        
        return cjDAO.updateCJ(o);
    }
    
    @Override
    public boolean deleteById(Integer id){        
        return cjDAO.deletebyId(id);
    }
    
    @Override
    public boolean verifyByTimeInBetween(String sTime, String eTime){
        sTime = sTime.startsWith("0") ? sTime.substring(1) : sTime;
        eTime = eTime.startsWith("0") ? eTime.substring(1) : eTime;
        return cjDAO.getInBetweenTheTime(sTime, eTime);
    }
    
    @Override
    public CronJob getFilterIdByTime(String Time){
        
        System.out.println("Time before zero skip" + Time);
        
        Time = Time.startsWith("0") ? Time.substring(1) : Time;
        cronjobs dfe = cjDAO.getByTime(Time);
        
        System.out.println("Time after zero skipped" + Time);
        
        CronJob cj = null;
        
        if(dfe != null){
            cj = new CronJob(dfe.getId(), dfe.getNameofjob(), Integer.toString(dfe.getFilterid()), fSvc.getFById(dfe.getFilterid()).getNameOfFilter(), dfe.getScheduledtime(), (Integer.toString(dfe.getRoutinerunoption()).equals("1") ? "Everyday" : "On Upcoming Specific Date"), dfe.getScheduleddate(), dfe.getDate_(), dfe.getTime_(), dfe.getMonth_(), dfe.getYear_());
        }
        
        return cj;
    }
    
    @Override
    public CronJob getCronJob(Integer id){
        cronjobs dfe = cjDAO.getCJbyID(id);
        CronJob cj = new CronJob(dfe.getId(), dfe.getNameofjob(), Integer.toString(dfe.getFilterid()), fSvc.getFById(dfe.getFilterid()).getNameOfFilter(), dfe.getScheduledtime(), (Integer.toString(dfe.getRoutinerunoption()).equals("1") ? "Everyday" : "On Upcoming Specific Date"), dfe.getScheduleddate(), dfe.getDate_(), dfe.getTime_(), dfe.getMonth_(), dfe.getYear_());
        return cj;
    }
    
    @Override
    public List<CronJob> getCronJobs(){
        List<cronjobs> dfdb =  this.cjDAO.getCronJobs();
        List<CronJob> dtf = new ArrayList<>();
        dfdb.stream().map((dfe) -> {
            CronJob f = new CronJob(dfe.getId(), dfe.getNameofjob(), Integer.toString(dfe.getFilterid()), fSvc.getFById(dfe.getFilterid()).getNameOfFilter(), dfe.getScheduledtime(), (Integer.toString(dfe.getRoutinerunoption()).equals("1") ? "Everyday" : "On Upcoming Specific Date"), dfe.getScheduleddate(), "", "", "", "");
            return f;
        }).forEachOrdered((f) -> {
            dtf.add(f);
        });
        return dtf;
    }
}
