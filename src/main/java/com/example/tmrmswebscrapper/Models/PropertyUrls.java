/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.tmrmswebscrapper.Models;

/**
 *
 * @author Syed Musa Gillani
 */
public class PropertyUrls {
    public String purls;
    public String mRegion;
    public String PropertyCategory;
    public String PropertyType;

    public PropertyUrls(String purls, String mRegion, String PropertyCategory, String PropertyType) {
        this.purls = purls;
        this.mRegion = mRegion;
        this.PropertyCategory = PropertyCategory;
        this.PropertyType = PropertyType;
    }

    public String getPurls() {
        return purls;
    }

    public void setPurls(String purls) {
        this.purls = purls;
    }

    public String getmRegion() {
        return mRegion;
    }

    public void setmRegion(String mRegion) {
        this.mRegion = mRegion;
    }

    public String getPropertyCategory() {
        return PropertyCategory;
    }

    public void setPropertyCategory(String PropertyCategory) {
        this.PropertyCategory = PropertyCategory;
    }

    public String getPropertyType() {
        return PropertyType;
    }

    public void setPropertyType(String PropertyType) {
        this.PropertyType = PropertyType;
    }
    
}
