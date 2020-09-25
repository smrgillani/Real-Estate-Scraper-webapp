/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.tmrmswebscrapper.Models;

import java.util.ArrayList;

/**
 *
 * @author Syed Musa Gillani
 */
public class Property {
    public int Id;
    public String CateId;
    public int regionId;
    public String postId;
    public String vendorId;
    public String vendorType;
    public String publishDate;
    public String updateDate;
    public String searchType;
    public String msRegion;
    public String catOfProperty;
    public String propertyType;
    public String titleOfAdd;
    public String streetNumber;
    public String postalCode;
    public String cIty;
    public String sellPrice;
    public String sellGrossPrice;
    public String sellNetPrice;
    public String sellPaymentType;
    public String rentPrice;
    public String rentGrossPrice;
    public String rentNetPrice;
    public String rentPaymentType;
    public String numberOfRooms;
    public String livingSpace;
    public String propertyFloor;
    public String propertyArea;
    public String propertyDesc;
    public String propertyFeatures;
    public String propertyAvailable;
    public String yearBuilt;
    public String nameOfSeller;
    public String agencyName;
    public String streetNumberOfSeller;
    public String postalCodeOfSeller;
    public String cityOfSeller;
    public String phoneOfSeller;
    public String mNumberOfSeller;
    public String emailOfSeller; 
    public String contactName;
    public String contactNumber;
    public String webSite;
    public String urlOfAd;
    public String time_;
    public String date_;
    public String month_;
    public String year_;
    public ArrayList<String> urls;

    public Property(int Id, String CateId, int regionId, String postId, String vendorId, String vendorType, String publishDate, String updateDate, String searchType, String msRegion, String catOfProperty, String propertyType, String titleOfAdd, String streetNumber, String postalCode, String cIty, String sellPrice, String sellGrossPrice, String sellNetPrice, String sellPaymentType, String rentPrice, String rentGrossPrice, String rentNetPrice, String rentPaymentType, String numberOfRooms, String livingSpace, String propertyFloor, String propertyArea, String propertyDesc, String propertyFeatures, String propertyAvailable, String yearBuilt, String nameOfSeller, String agencyName, String streetNumberOfSeller, String postalCodeOfSeller, String cityOfSeller, String phoneOfSeller, String mNumberOfSeller, String emailOfSeller, String contactName, String contactNumber, String webSite, String urlOfAd, String time_, String date_, String month_, String year_) {
        this.Id = Id;
        this.CateId = CateId;
        this.regionId = regionId;
        this.postId = postId;
        this.vendorId = vendorId;
        this.vendorType = vendorType;
        this.publishDate = publishDate;
        this.updateDate = updateDate;
        this.searchType = searchType;
        this.msRegion = msRegion;
        this.catOfProperty = catOfProperty;
        this.propertyType = propertyType;
        this.titleOfAdd = titleOfAdd;
        this.streetNumber = streetNumber;
        this.postalCode = postalCode;
        this.cIty = cIty;
        this.sellPrice = sellPrice;
        this.sellGrossPrice = sellGrossPrice;
        this.sellNetPrice = sellNetPrice;
        this.sellPaymentType = sellPaymentType;
        this.rentPrice = rentPrice;
        this.rentGrossPrice = rentGrossPrice;
        this.rentNetPrice = rentNetPrice;
        this.rentPaymentType = rentPaymentType;
        this.numberOfRooms = numberOfRooms;
        this.livingSpace = livingSpace;
        this.propertyFloor = propertyFloor;
        this.propertyArea = propertyArea;
        this.propertyDesc = propertyDesc;
        this.propertyFeatures = propertyFeatures;
        this.propertyAvailable = propertyAvailable;
        this.yearBuilt = yearBuilt;
        this.agencyName = agencyName;
        this.nameOfSeller = nameOfSeller;
        this.streetNumberOfSeller = streetNumberOfSeller;
        this.postalCodeOfSeller = postalCodeOfSeller;
        this.cityOfSeller = cityOfSeller;
        this.phoneOfSeller = phoneOfSeller;
        this.mNumberOfSeller = mNumberOfSeller;
        this.emailOfSeller = emailOfSeller;
        this.contactName = contactName;
        this.contactNumber = contactNumber;
        this.webSite = webSite;
        this.urlOfAd = urlOfAd;
        this.time_ = time_;
        this.date_ = date_;
        this.month_ = month_;
        this.year_ = year_;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getCateId() {
        return CateId;
    }

    public void setCateId(String CateId) {
        this.CateId = CateId;
    }

    public int getRegionId() {
        return regionId;
    }

    public void setRegionId(int regionId) {
        this.regionId = regionId;
    }
    
    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public String getVendorId() {
        return vendorId;
    }

    public void setVendorId(String vendorId) {
        this.vendorId = vendorId;
    }

    public String getVendorType() {
        return vendorType;
    }

    public void setVendorType(String vendorType) {
        this.vendorType = vendorType;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public String getSearchType() {
        return searchType;
    }

    public void setSearchType(String searchType) {
        this.searchType = searchType;
    }

    public String getMsRegion() {
        return msRegion;
    }

    public void setMsRegion(String msRegion) {
        this.msRegion = msRegion;
    }

    public String getCatOfProperty() {
        return catOfProperty;
    }

    public void setCatOfProperty(String catOfProperty) {
        this.catOfProperty = catOfProperty;
    }

    public String getPropertyType() {
        return propertyType;
    }

    public void setPropertyType(String propertyType) {
        this.propertyType = propertyType;
    }

    public String getTitleOfAdd() {
        return titleOfAdd;
    }

    public void setTitleOfAdd(String titleOfAdd) {
        this.titleOfAdd = titleOfAdd;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getcIty() {
        return cIty;
    }

    public void setcIty(String cIty) {
        this.cIty = cIty;
    }

    public String getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(String sellPrice) {
        this.sellPrice = sellPrice;
    }

    public String getSellGrossPrice() {
        return sellGrossPrice;
    }

    public void setSellGrossPrice(String sellGrossPrice) {
        this.sellGrossPrice = sellGrossPrice;
    }

    public String getSellNetPrice() {
        return sellNetPrice;
    }

    public void setSellNetPrice(String sellNetPrice) {
        this.sellNetPrice = sellNetPrice;
    }

    public String getSellPaymentType() {
        return sellPaymentType;
    }

    public void setSellPaymentType(String sellPaymentType) {
        this.sellPaymentType = sellPaymentType;
    }

    public String getRentPrice() {
        return rentPrice;
    }

    public void setRentPrice(String rentPrice) {
        this.rentPrice = rentPrice;
    }

    public String getRentGrossPrice() {
        return rentGrossPrice;
    }

    public void setRentGrossPrice(String rentGrossPrice) {
        this.rentGrossPrice = rentGrossPrice;
    }

    public String getRentNetPrice() {
        return rentNetPrice;
    }

    public void setRentNetPrice(String rentNetPrice) {
        this.rentNetPrice = rentNetPrice;
    }

    public String getRentPaymentType() {
        return rentPaymentType;
    }

    public void setRentPaymentType(String rentPaymentType) {
        this.rentPaymentType = rentPaymentType;
    }

    public String getNumberOfRooms() {
        return numberOfRooms;
    }

    public void setNumberOfRooms(String numberOfRooms) {
        this.numberOfRooms = numberOfRooms;
    }

    public String getLivingSpace() {
        return livingSpace;
    }

    public void setLivingSpace(String livingSpace) {
        this.livingSpace = livingSpace;
    }

    public String getPropertyFloor() {
        return propertyFloor;
    }

    public void setPropertyFloor(String propertyFloor) {
        this.propertyFloor = propertyFloor;
    }

    public String getPropertyArea() {
        return propertyArea;
    }

    public void setPropertyArea(String propertyArea) {
        this.propertyArea = propertyArea;
    }

    public String getPropertyDesc() {
        return propertyDesc;
    }

    public void setPropertyDesc(String propertyDesc) {
        this.propertyDesc = propertyDesc;
    }

    public String getPropertyFeatures() {
        return propertyFeatures;
    }

    public void setPropertyFeatures(String propertyFeatures) {
        this.propertyFeatures = propertyFeatures;
    }

    public String getPropertyAvailable() {
        return propertyAvailable;
    }

    public void setPropertyAvailable(String propertyAvailable) {
        this.propertyAvailable = propertyAvailable;
    }

    public String getYearBuilt() {
        return yearBuilt;
    }

    public void setYearBuilt(String yearBuilt) {
        this.yearBuilt = yearBuilt;
    }

    public String getNameOfSeller() {
        return nameOfSeller;
    }

    public void setNameOfSeller(String nameOfSeller) {
        this.nameOfSeller = nameOfSeller;
    }

    public String getAgencyName() {
        return agencyName;
    }

    public void setAgencyName(String agencyName) {
        this.agencyName = agencyName;
    }
    
    public String getStreetNumberOfSeller() {
        return streetNumberOfSeller;
    }

    public void setStreetNumberOfSeller(String streetNumberOfSeller) {
        this.streetNumberOfSeller = streetNumberOfSeller;
    }

    public String getPostalCodeOfSeller() {
        return postalCodeOfSeller;
    }

    public void setPostalCodeOfSeller(String postalCodeOfSeller) {
        this.postalCodeOfSeller = postalCodeOfSeller;
    }

    public String getCityOfSeller() {
        return cityOfSeller;
    }

    public void setCityOfSeller(String cityOfSeller) {
        this.cityOfSeller = cityOfSeller;
    }

    public String getPhoneOfSeller() {
        return phoneOfSeller;
    }

    public void setPhoneOfSeller(String phoneOfSeller) {
        this.phoneOfSeller = phoneOfSeller;
    }

    public String getmNumberOfSeller() {
        return mNumberOfSeller;
    }

    public void setmNumberOfSeller(String mNumberOfSeller) {
        this.mNumberOfSeller = mNumberOfSeller;
    }

    public String getEmailOfSeller() {
        return emailOfSeller;
    }

    public void setEmailOfSeller(String emailOfSeller) {
        this.emailOfSeller = emailOfSeller;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getWebSite() {
        return webSite;
    }

    public void setWebSite(String webSite) {
        this.webSite = webSite;
    }

    public String getUrlOfAd() {
        return urlOfAd;
    }

    public void setUrlOfAd(String urlOfAd) {
        this.urlOfAd = urlOfAd;
    }

    public String getTime_() {
        return time_;
    }

    public void setTime_(String time_) {
        this.time_ = time_;
    }

    public String getDate_() {
        return date_;
    }

    public void setDate_(String date_) {
        this.date_ = date_;
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

    public ArrayList<String> getUrls() {
        return urls;
    }

    public void setUrls(ArrayList<String> urls) {
        this.urls = urls;
    }

    
    
}
