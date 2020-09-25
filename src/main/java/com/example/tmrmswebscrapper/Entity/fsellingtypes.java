/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.tmrmswebscrapper.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Lubbow
 */
@Entity
@Table(name = "fsellingtypes")
public class fsellingtypes {
         
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
    
    @Column(name = "nameofsellingtype")
    private String nameofsellingtype;
    
    @Column(name = "date_")
    private String date_;

    @Column(name = "time_")
    private String time_;

    @Column(name = "month_")
    private String month_;
    
    @Column(name = "year_")
    private String year_;
    
    @ManyToOne()
    @JoinColumn(name = "fid")
    @JsonIgnore    
    private filters filter;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNameofsellingtype() {
        return nameofsellingtype;
    }

    public void setNameofsellingtype(String nameofsellingtype) {
        this.nameofsellingtype = nameofsellingtype;
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

    public filters getFilter() {
        return filter;
    }

    public void setFilter(filters filter) {
        this.filter = filter;
    }

}
