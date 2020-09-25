/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.tmrmswebscrapper.Models;

/**
 *
 * @author User
 */
public class PropertyList {
    public int id;
    public String DateTime;
    public String WebSite;

    public PropertyList(int id, String DateTime, String WebSite) {
        this.id = id;
        this.DateTime = DateTime;
        this.WebSite = WebSite;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDateTime() {
        return DateTime;
    }

    public void setDateTime(String DateTime) {
        this.DateTime = DateTime;
    }

    public String getWebSite() {
        return WebSite;
    }

    public void setWebSite(String WebSite) {
        this.WebSite = WebSite;
    }
    
    
    
}
