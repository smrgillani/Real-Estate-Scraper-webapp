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
public class PTHomeGate {
    public String keyValue;
    public String subchildKey;
    public String childKey;
    public String parentKey;

    public PTHomeGate(String keyValue, String subchildKey, String childKey, String parentKey) {
        this.keyValue = keyValue;
        this.subchildKey = subchildKey;
        this.childKey = childKey;
        this.parentKey = parentKey;
    }

    public String getKeyValue() {
        return keyValue;
    }

    public void setKeyValue(String keyValue) {
        this.keyValue = keyValue;
    }

    public String getSubchildKey() {
        return subchildKey;
    }

    public void setSubchildKey(String subchildKey) {
        this.subchildKey = subchildKey;
    }

    public String getChildKey() {
        return childKey;
    }

    public void setChildKey(String childKey) {
        this.childKey = childKey;
    }

    public String getParentKey() {
        return parentKey;
    }

    public void setParentKey(String parentKey) {
        this.parentKey = parentKey;
    }
    

    
}
