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
public class PropertyListJSON {
    public String Id;
    public String Title;
    public String Price;
    public String Zip;
    public String Region;
    public String Date_;
    public String Time_;

    public PropertyListJSON(String Id, String Title, String Price, String Zip, String Region, String Date_, String Time_) {
        this.Id = Id;
        this.Title = Title;
        this.Price = Price;
        this.Zip = Zip;
        this.Region = Region;
        this.Date_ = Date_;
        this.Time_ = Time_;
    }

    public String getId() {
        return Id;
    }

    public void setId(String Id) {
        this.Id = Id;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String Title) {
        this.Title = Title;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String Price) {
        this.Price = Price;
    }

    public String getZip() {
        return Zip;
    }

    public void setZip(String Zip) {
        this.Zip = Zip;
    }

    public String getRegion() {
        return Region;
    }

    public void setRegion(String Region) {
        this.Region = Region;
    }

    public String getDate_() {
        return Date_;
    }

    public void setDate_(String Date_) {
        this.Date_ = Date_;
    }

    public String getTime_() {
        return Time_;
    }

    public void setTime_(String Time_) {
        this.Time_ = Time_;
    }
    
}
