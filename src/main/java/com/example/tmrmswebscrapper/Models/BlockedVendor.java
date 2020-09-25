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
public class BlockedVendor {
    public String Id;
    public String VName;
    public String Vid;
    public String WS;

    public BlockedVendor(String Id, String VName, String Vid, String WS) {
        this.Id = Id;
        this.VName = VName;
        this.Vid = Vid;
        this.WS = WS;
    }

    public String getId() {
        return Id;
    }

    public void setId(String Id) {
        this.Id = Id;
    }

    public String getVName() {
        return VName;
    }

    public void setVName(String VName) {
        this.VName = VName;
    }

    public String getVid() {
        return Vid;
    }

    public void setVid(String Vid) {
        this.Vid = Vid;
    }

    public String getWS() {
        return WS;
    }

    public void setWS(String WS) {
        this.WS = WS;
    }

    
}
