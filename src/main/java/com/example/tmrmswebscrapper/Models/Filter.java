/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.tmrmswebscrapper.Models;

import java.util.List;

/**
 *
 * @author Lubbow
 */
public class Filter {
    private Integer id;
    private String nameOfFilter;
    private String priceFrom;
    private String priceTo;
    private String roomFrom;
    private String roomTo;
    private String livingSpaceFrom;
    private String livingSpaceTo;
    private String floorSpacePlotAreaFrom;
    private String floorSpacePlotAreaTo;
    private String builtYearFrom;
    private String builtYearTo;
    private String availableFrom;
    private String availableTo;
    private String date_;
    private String time_;
    private String month_;
    private String year_;

    private List<FCategoryType> fCategoryType;
    
    private List<FRegion> fRegion;
    
    private List<FPropertyFeature> fPropertyFeature;
    
    private List<FSellingType> fSellingType;
    
    private List<FVendorType> fVendorType;
    
    private List<FPropertyType> fPropertyType;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNameOfFilter() {
        return nameOfFilter;
    }

    public void setNameOfFilter(String nameOfFilter) {
        this.nameOfFilter = nameOfFilter;
    }

    public String getPriceFrom() {
        return priceFrom;
    }

    public void setPriceFrom(String priceFrom) {
        this.priceFrom = priceFrom;
    }

    public String getPriceTo() {
        return priceTo;
    }

    public void setPriceTo(String priceTo) {
        this.priceTo = priceTo;
    }

    public String getRoomFrom() {
        return roomFrom;
    }

    public void setRoomFrom(String roomFrom) {
        this.roomFrom = roomFrom;
    }

    public String getRoomTo() {
        return roomTo;
    }

    public void setRoomTo(String roomTo) {
        this.roomTo = roomTo;
    }
    
    public String getLivingSpaceFrom() {
        return livingSpaceFrom;
    }

    public void setLivingSpaceFrom(String livingSpaceFrom) {
        this.livingSpaceFrom = livingSpaceFrom;
    }

    public String getLivingSpaceTo() {
        return livingSpaceTo;
    }

    public void setLivingSpaceTo(String livingSpaceTo) {
        this.livingSpaceTo = livingSpaceTo;
    }

    public String getFloorSpacePlotAreaFrom() {
        return floorSpacePlotAreaFrom;
    }

    public void setFloorSpacePlotAreaFrom(String floorSpacePlotAreaFrom) {
        this.floorSpacePlotAreaFrom = floorSpacePlotAreaFrom;
    }

    public String getFloorSpacePlotAreaTo() {
        return floorSpacePlotAreaTo;
    }

    public void setFloorSpacePlotAreaTo(String floorSpacePlotAreaTo) {
        this.floorSpacePlotAreaTo = floorSpacePlotAreaTo;
    }

    public String getBuiltYearFrom() {
        return builtYearFrom;
    }

    public void setBuiltYearFrom(String builtYearFrom) {
        this.builtYearFrom = builtYearFrom;
    }

    public String getBuiltYearTo() {
        return builtYearTo;
    }

    public void setBuiltYearTo(String builtYearTo) {
        this.builtYearTo = builtYearTo;
    }

    public String getAvailableFrom() {
        return availableFrom;
    }

    public void setAvailableFrom(String availableFrom) {
        this.availableFrom = availableFrom;
    }

    public String getAvailableTo() {
        return availableTo;
    }

    public void setAvailableTo(String availableTo) {
        this.availableTo = availableTo;
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

    public List<FCategoryType> getfCategoryType() {
        return fCategoryType;
    }

    public void setfCategoryType(List<FCategoryType> fCategoryType) {
        this.fCategoryType = fCategoryType;
    }

    public List<FRegion> getfRegion() {
        return fRegion;
    }

    public void setfRegion(List<FRegion> fRegion) {
        this.fRegion = fRegion;
    }
    
    public List<FPropertyFeature> getfPropertyFeature() {
        return fPropertyFeature;
    }

    public void setfPropertyFeature(List<FPropertyFeature> fPropertyFeature) {
        this.fPropertyFeature = fPropertyFeature;
    }

    public List<FSellingType> getfSellingType() {
        return fSellingType;
    }

    public void setfSellingType(List<FSellingType> fSellingType) {
        this.fSellingType = fSellingType;
    }

    public List<FVendorType> getfVendorType() {
        return fVendorType;
    }

    public void setfVendorType(List<FVendorType> fVendorType) {
        this.fVendorType = fVendorType;
    }

    public List<FPropertyType> getfPropertyType() {
        return fPropertyType;
    }

    public void setfPropertyType(List<FPropertyType> fPropertyType) {
        this.fPropertyType = fPropertyType;
    }
}
