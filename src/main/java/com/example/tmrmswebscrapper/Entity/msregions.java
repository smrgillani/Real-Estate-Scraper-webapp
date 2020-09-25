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
 * @author Lubbow
 */
@Entity
@Table(name = "msregions")
public class msregions {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "rid")
    private Integer rid;
    
    @Column(name = "fullname")
    private String full_name;

    public Integer getId() {
        return rid;
    }

    public void setId(Integer id) {
        this.rid = id;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }
}
