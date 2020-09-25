/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.tmrmswebscrapper.Models;

/**
 *
 * @author Smr
 */
public class CronJob {
    private Integer id;
    
    private String nameOfJob;
    
    private String filterId;
    
    private String filterName;
    
    private String scheduleTime;
    
    private String routineRunOption;
    
    private String scheduledDate;
    
    private String date_;
    
    private String time_;

    private String month_;
    
    private String year_;

    public CronJob(Integer id, String nameOfJob, String filterId, String filterName, String scheduleTime, String routineRunOption, String scheduledDate, String date_, String time_, String month_, String year_) {
        this.id = id;
        this.nameOfJob = nameOfJob;
        this.filterId = filterId;
        this.filterName = filterName;
        this.scheduleTime = scheduleTime;
        this.routineRunOption = routineRunOption;
        this.scheduledDate = scheduledDate;
        this.date_ = date_;
        this.time_ = time_;
        this.month_ = month_;
        this.year_ = year_;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNameOfJob() {
        return nameOfJob;
    }

    public void setNameOfJob(String nameOfJob) {
        this.nameOfJob = nameOfJob;
    }

    public String getFilterId() {
        return filterId;
    }

    public void setFilterId(String filterId) {
        this.filterId = filterId;
    }

    public String getFilterName() {
        return filterName;
    }

    public void setFilterName(String filterName) {
        this.filterName = filterName;
    }

    public String getScheduleTime() {
        return scheduleTime;
    }

    public void setScheduleTime(String scheduleTime) {
        this.scheduleTime = scheduleTime;
    }

    public String getRoutineRunOption() {
        return routineRunOption;
    }

    public void setRoutineRunOption(String routineRunOption) {
        this.routineRunOption = routineRunOption;
    }

    public String getScheduledDate() {
        return scheduledDate;
    }

    public void setScheduledDate(String scheduledDate) {
        this.scheduledDate = scheduledDate;
    }

    public String getDate_() {
        return date_;
    }

    public void setDate_(String date_) {
        this.date_ = date_;
    }

    public String getTime_() {
        return time_;
    }

    public void setTime_(String time_) {
        this.time_ = time_;
    }

    public String getMonth_() {
        return month_;
    }

    public void setMonth_(String month_) {
        this.month_ = month_;
    }

    public String getYear_() {
        return year_;
    }

    public void setYear_(String year_) {
        this.year_ = year_;
    }
    
}
