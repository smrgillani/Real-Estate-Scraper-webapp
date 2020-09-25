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
public class MrCity {
    public String Id;
    public String MrId;
    public String FName;
    public String RFName;

    public String getId() {
        return Id;
    }

    public void setId(String Id) {
        this.Id = Id;
    }

    public String getMrId() {
        return MrId;
    }

    public void setMrId(String MrId) {
        this.MrId = MrId;
    }

    public String getFName() {
        return FName;
    }

    public void setFName(String FName) {
        this.FName = FName;
    }

    public String getRFName() {
        return RFName;
    }

    public void setRFName(String RFName) {
        this.RFName = RFName;
    }

    public MrCity(String Id, String MrId, String FName, String RFName) {
        this.Id = Id;
        this.MrId = MrId;
        this.FName = FName;
        this.RFName = RFName;
    }
}
