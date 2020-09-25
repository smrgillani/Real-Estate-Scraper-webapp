/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.tmrmswebscrapper.Repos;

import com.example.tmrmswebscrapper.Entity.cronjobs;
import java.util.List;

/**
 *
 * @author Smr
 */
public interface CronJobDAOI {
    Integer addNew(cronjobs cj);
    cronjobs getCJbyID(Integer ID);
    boolean getInBetweenTheTime(String sTime, String eTime);
    cronjobs getByTime(String Time);
    Integer updateCJ(cronjobs cj);
    boolean deletebyId(Integer id);
    List<cronjobs> getCronJobs();
}
