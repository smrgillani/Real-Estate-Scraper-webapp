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
@Table(name = "mrcities")
public class mrcities {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
        
    @Column(name = "fullname")
    private String full_name;

    @ManyToOne()
    @JoinColumn(name = "rid")
    @JsonIgnore    
    private msregions msregions;
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public msregions getMsregions() {
        return msregions;
    }

    public void setMsregions(msregions msregions) {
        this.msregions = msregions;
    }
}