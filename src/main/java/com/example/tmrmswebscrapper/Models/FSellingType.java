/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.tmrmswebscrapper.Models;

/**
 *
 * @author Lubbow
 */
public class FSellingType {
    
    private Integer id;
    
    private String nameOfSellingType;
    
    private String date_;
    
    private String time_;
    
    private String month_;
    
    private String year_;

    public FSellingType(Integer id, String nameOfSellingType, String date_, String time_, String month_, String year_) {
        this.id = id;
        this.nameOfSellingType = nameOfSellingType;
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

    public String getNameOfSellingType() {
        return nameOfSellingType;
    }

    public void setNameOfSellingType(String nameOfSellingType) {
        this.nameOfSellingType = nameOfSellingType;
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
