/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.tmrmswebscrapper.Models;

import java.util.ArrayList;

/**
 *
 * @author Smr
 */
public class JsonData {
    public ArrayList<PropertyListJSON> rData;
    public String rMessage;
    public String rStatus;

    public ArrayList<PropertyListJSON> getrData() {
        return rData;
    }

    public void setrData(ArrayList<PropertyListJSON> rData) {
        this.rData = rData;
    }

    public String getrMessage() {
        return rMessage;
    }

    public void setrMessage(String rMessage) {
        this.rMessage = rMessage;
    }

    public String getrStatus() {
        return rStatus;
    }

    public void setrStatus(String rStatus) {
        this.rStatus = rStatus;
    }
    
    
    
}
