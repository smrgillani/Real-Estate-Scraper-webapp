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
public class MsRegion {
    public String Id;
    public String FName;
    public String TotalCities;
    
    public String getId() {
        return Id;
    }

    public void setId(String Id) {
        this.Id = Id;
    }

    public String getFName() {
        return FName;
    }

    public void setFName(String FName) {
        this.FName = FName;
    }

    public String getTotalCities() {
        return TotalCities;
    }

    public void setTotalCities(String TotalCities) {
        this.TotalCities = TotalCities;
    }

    public MsRegion(String Id, String FName, String TotalCities) {
        this.Id = Id;
        this.FName = FName;
        this.TotalCities = TotalCities;
    }
}
