/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.tmrmswebscrapper.Svc;

import com.example.tmrmswebscrapper.Models.CronJob;
import java.util.List;

/**
 *
 * @author Smr
 */
public interface CronJobSvcI {
    Integer createCronJob(CronJob cj);
    CronJob getCronJob(Integer id);
    Integer updateCronJob(CronJob cj);
    boolean deleteById(Integer id);
    boolean verifyByTimeInBetween(String sTime, String eTime);
    List<CronJob> getCronJobs();
    CronJob getFilterIdByTime(String Time);
}
