/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.tmrmswebscrapper.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Smr
 */
@Entity
@Table(name = "cronjobs")
public class cronjobs {
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
    
    @Column(name = "nameofjob")
    private String nameofjob;
    
    @Column(name = "filterid")
    private Integer filterid;
    
    @Column(name = "scheduledtime")
    private String scheduledtime;
    
    @Column(name = "routinerunoption")
    private Integer routinerunoption;
    
    @Column(name = "scheduleddate")
    private String scheduleddate;
    
    @Column(name = "date_")
    private String date_;

    @Column(name = "time_")
    private String time_;

    @Column(name = "month_")
    private String month_;
    
    @Column(name = "year_")
    private String year_;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNameofjob() {
        return nameofjob;
    }

    public void setNameofjob(String nameofjob) {
        this.nameofjob = nameofjob;
    }

    public Integer getFilterid() {
        return filterid;
    }

    public void setFilterid(Integer filterid) {
        this.filterid = filterid;
    }

    public String getScheduledtime() {
        return scheduledtime;
    }

    public void setScheduledtime(String scheduledtime) {
        this.scheduledtime = scheduledtime;
    }

    public Integer getRoutinerunoption() {
        return routinerunoption;
    }

    public void setRoutinerunoption(Integer routinerunoption) {
        this.routinerunoption = routinerunoption;
    }

    public String getScheduleddate() {
        return scheduleddate;
    }

    public void setScheduleddate(String scheduleddate) {
        this.scheduleddate = scheduleddate;
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
