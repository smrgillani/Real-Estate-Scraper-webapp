/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.tmrmswebscrapper.Entity;

import java.util.List;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Lubbow
 */
@Entity
@Table(name = "filters")
public class filters {
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "fid")
    private Integer fid;
        
    @Column(name = "nameoffilter")
    private String nameoffilter;
    
    @Column(name = "pricefrom")
    private String pricefrom;
    
    @Column(name = "priceto")
    private String priceto;
    
    @Column(name = "roomsfrom")
    private String roomsfrom;
    
    @Column(name = "roomsto")
    private String roomsto;
    
    @Column(name = "livingspacefrom")
    private String livingspacefrom;
    
    @Column(name = "livingspaceto")
    private String livingspaceto;
    
    @Column(name = "floorspaceplotareafrom")
    private String floorspaceplotareafrom;
    
    @Column(name = "floorspaceplotareato")
    private String floorspaceplotareato;
    
    @Column(name = "builtyearfrom")
    private String builtyearfrom;
    
    @Column(name = "builtyearto")
    private String builtyearto;
    
    @Column(name = "availablefrom")
    private String availablefrom;
    
    @Column(name = "availableto")
    private String availableto;
    
    @Column(name = "date_")
    private String date_;

    @Column(name = "time_")
    private String time_;

    @Column(name = "month_")
    private String month_;
    
    @Column(name = "year_")
    private String year_;    
    
    @OneToMany(targetEntity = fcategorytypes.class, orphanRemoval = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "fid")
    private List<fcategorytypes> fcategorytype;
    
    @OneToMany(targetEntity = fcities.class, orphanRemoval = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "fid")
    private List<fcities> fcities;
    
    @OneToMany(targetEntity = fpropertyfeatures.class, orphanRemoval = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "fid")
    private List<fpropertyfeatures> fpropertyfeatures;
    
    @OneToMany(targetEntity = fsellingtypes.class, orphanRemoval = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "fid")
    private List<fsellingtypes> fsellingtype;
    
    @OneToMany(targetEntity = fvendortypes.class, orphanRemoval = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "fid")
    private List<fvendortypes> fvendortype;
    
    @OneToMany(targetEntity = fpropertytypes.class, orphanRemoval = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "fid")
    private List<fpropertytypes> fpropertytype;

    public Integer getFid() {
        return fid;
    }

    public void setFid(Integer fid) {
        this.fid = fid;
    }

    public String getNameoffilter() {
        return nameoffilter;
    }

    public void setNameoffilter(String nameoffilter) {
        this.nameoffilter = nameoffilter;
    }

    public String getPricefrom() {
        return pricefrom;
    }

    public void setPricefrom(String pricefrom) {
        this.pricefrom = pricefrom;
    }

    public String getPriceto() {
        return priceto;
    }

    public void setPriceto(String priceto) {
        this.priceto = priceto;
    }

    public String getRoomsfrom() {
        return roomsfrom;
    }

    public void setRoomsfrom(String roomsfrom) {
        this.roomsfrom = roomsfrom;
    }

    public String getRoomsto() {
        return roomsto;
    }

    public void setRoomsto(String roomsto) {
        this.roomsto = roomsto;
    }
    
    public String getLivingspacefrom() {
        return livingspacefrom;
    }

    public void setLivingspacefrom(String livingspacefrom) {
        this.livingspacefrom = livingspacefrom;
    }

    public String getLivingspaceto() {
        return livingspaceto;
    }

    public void setLivingspaceto(String livingspaceto) {
        this.livingspaceto = livingspaceto;
    }

    public String getFloorspaceplotareafrom() {
        return floorspaceplotareafrom;
    }

    public void setFloorspaceplotareafrom(String floorspaceplotareafrom) {
        this.floorspaceplotareafrom = floorspaceplotareafrom;
    }

    public String getFloorspaceplotareato() {
        return floorspaceplotareato;
    }

    public void setFloorspaceplotareato(String floorspaceplotareato) {
        this.floorspaceplotareato = floorspaceplotareato;
    }

    public String getBuiltyearfrom() {
        return builtyearfrom;
    }

    public void setBuiltyearfrom(String builtyearfrom) {
        this.builtyearfrom = builtyearfrom;
    }

    public String getBuiltyearto() {
        return builtyearto;
    }

    public void setBuiltyearto(String builtyearto) {
        this.builtyearto = builtyearto;
    }

    public String getAvailablefrom() {
        return availablefrom;
    }

    public void setAvailablefrom(String availablefrom) {
        this.availablefrom = availablefrom;
    }

    public String getAvailableto() {
        return availableto;
    }

    public void setAvailableto(String availableto) {
        this.availableto = availableto;
    }

    public String getDate_() {
        return date_;
    }

    public void setDate_(String date_) {
        this.date_ = date_;
    }

    public String getTime_() {
        return time_;
    }

    public void setTime_(String time_) {
        this.time_ = time_;
    }

    public String getMonth_() {
        return month_;
    }

    public void setMonth_(String month_) {
        this.month_ = month_;
    }

    public String getYear_() {
        return year_;
    }

    public void setYear_(String year_) {
        this.year_ = year_;
    }

    public List<fcategorytypes> getFcategorytype() {
        return fcategorytype;
    }

    public void setFcategorytype(List<fcategorytypes> fcategorytype) {
        this.fcategorytype = fcategorytype;
    }

    public List<fcities> getFcities() {
        return fcities;
    }

    public void setFcities(List<fcities> fcities) {
        this.fcities = fcities;
    }

    public List<fpropertyfeatures> getFpropertyfeatures() {
        return fpropertyfeatures;
    }

    public void setFpropertyfeatures(List<fpropertyfeatures> fpropertyfeatures) {
        this.fpropertyfeatures = fpropertyfeatures;
    }

    public List<fsellingtypes> getFsellingtype() {
        return fsellingtype;
    }

    public void setFsellingtype(List<fsellingtypes> fsellingtype) {
        this.fsellingtype = fsellingtype;
    }

    public List<fvendortypes> getFvendortype() {
        return fvendortype;
    }

    public void setFvendortype(List<fvendortypes> fvendortype) {
        this.fvendortype = fvendortype;
    }

    public List<fpropertytypes> getFpropertytype() {
        return fpropertytype;
    }

    public void setFpropertytype(List<fpropertytypes> fpropertytype) {
        this.fpropertytype = fpropertytype;
    }
    
}
