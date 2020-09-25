/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.tmrmswebscrapper.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Syed Musa Gillani
 */

@Entity
@Table(name = "properties")
public class properties{
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    @Column(name = "cate_id")
    private String cate_id;
    
    @Column(name = "region_id")
    private int region_id;
    
    @Column(name = "p_id", unique = true, nullable = false)
    private String p_id;
    
    @Column(name = "v_id")
    private String v_id;
    
    @Column(name = "publishdate")
    private String publishdate;
    
    @Column(name = "updatedate")
    private String updatedate;
    
    @Column(name = "searchtype")
    private String searchtype;
    
    @Column(name = "msregion")
    private String msregion;
    
    @Column(name = "catofproperty")
    private String catofproperty;
    
    @Column(name = "propertytype")
    private String propertytype;
    
    @Column(name = "titleofadd")
    private String titleofadd;
    
    @Column(name = "streetnumber")
    private String streetnumber;
    
    @Column(name = "postalcode")
    private String postalcode;
    
    @Column(name = "city")
    private String city;
    
    @Column(name = "sellprice")
    private String sellprice;
    
    @Column(name = "sellgrossprice")
    private String sellgrossprice;
    
    @Column(name = "sellnetprice")
    private String sellnetprice;
    
    @Column(name = "sellpaymenttype")
    private String sellpaymenttype;
    
    @Column(name = "rentprice")
    private String rentprice;
    
    @Column(name = "rentgrossprice")
    private String rentgrossprice;
    
    @Column(name = "rentnetprice")
    private String rentnetprice;
    
    @Column(name = "rentpaymenttype")
    private String rentpaymenttype;
    
    @Column(name = "numberofrooms")
    private String numberofrooms;
    
    @Column(name = "livingspace")
    private String livingspace;
    
    @Column(name = "propertyfloor")
    private String propertyfloor;
    
    @Column(name = "propertyarea")
    private String propertyarea;
    
    @Column(name = "propertyfeatures")
    private String propertyfeatures;
    
    @Column(name = "propertydesc")
    private String propertydesc;
    
    @Column(name = "propertyavailable")
    private String propertyavailable;
    
    @Column(name = "yearbuilt")
    private String yearbuilt;
    
    @Column(name = "nameofseller")
    private String nameofseller;
    
    @Column(name = "agencyname")
    private String agencyname;
    
    @Column(name = "streetnumberofseller")
    private String streetnumberofseller;
    
    @Column(name = "postalcodeofseller")
    private String postalcodeofseller;
    
    @Column(name = "cityofseller")
    private String cityofseller;
    
    @Column(name = "phoneofseller")
    private String phoneofseller;
    
    @Column(name = "mnumberofseller")
    private String mnumberofseller;
    
    @Column(name = "emailofseller")
    private String emailofseller;
    
    @Column(name = "contactname")
    private String contactname;
    
    @Column(name = "contactnumber")
    private String contactnumber;
    
    @Column(name = "website")
    private String website;
    
    @Column(name = "urlofad")
    private String urlofad;
    
    @Column(name = "flaged_p")
    private String flaged_p;

    @Column(name = "time_")
    private String time_;
    
    @Column(name = "date_")
    private String date_;
    
    @Column(name = "month_")
    private String month_;
    
    @Column(name = "year_")
    private String year_;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCate_id() {
        return cate_id;
    }

    public void setCate_id(String cate_id) {
        this.cate_id = cate_id;
    }

    public int getRegion_id() {
        return region_id;
    }

    public void setRegion_id(int region_id) {
        this.region_id = region_id;
    }
    
    public String getP_id() {
        return p_id;
    }

    public void setP_id(String p_id) {
        this.p_id = p_id;
    }
    
    public String getV_id() {
        return v_id;
    }

    public void setV_id(String v_id) {
        this.v_id = v_id;
    }

    public String getPublishdate() {
        return publishdate;
    }

    public void setPublishdate(String publishdate) {
        this.publishdate = publishdate;
    }

    public String getUpdatedate() {
        return updatedate;
    }

    public void setUpdatedate(String updatedate) {
        this.updatedate = updatedate;
    }

    public String getSearchtype() {
        return searchtype;
    }

    public void setSearchtype(String searchtype) {
        this.searchtype = searchtype;
    }

    public String getMsregion() {
        return msregion;
    }

    public void setMsregion(String msregion) {
        this.msregion = msregion;
    }

    public String getCatofproperty() {
        return catofproperty;
    }

    public void setCatofproperty(String catofproperty) {
        this.catofproperty = catofproperty;
    }

    public String getPropertytype() {
        return propertytype;
    }

    public void setPropertytype(String propertytype) {
        this.propertytype = propertytype;
    }

    public String getTitleofadd() {
        return titleofadd;
    }

    public void setTitleofadd(String titleofadd) {
        this.titleofadd = titleofadd;
    }

    public String getStreetnumber() {
        return streetnumber;
    }

    public void setStreetnumber(String streetnumber) {
        this.streetnumber = streetnumber;
    }

    public String getPostalcode() {
        return postalcode;
    }

    public void setPostalcode(String postalcode) {
        this.postalcode = postalcode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getSellprice() {
        return sellprice;
    }

    public void setSellprice(String sellprice) {
        this.sellprice = sellprice;
    }

    public String getSellgrossprice() {
        return sellgrossprice;
    }

    public void setSellgrossprice(String sellgrossprice) {
        this.sellgrossprice = sellgrossprice;
    }

    public String getSellnetprice() {
        return sellnetprice;
    }

    public void setSellnetprice(String sellnetprice) {
        this.sellnetprice = sellnetprice;
    }

    public String getSellpaymenttype() {
        return sellpaymenttype;
    }

    public void setSellpaymenttype(String sellpaymenttype) {
        this.sellpaymenttype = sellpaymenttype;
    }

    public String getRentprice() {
        return rentprice;
    }

    public void setRentprice(String rentprice) {
        this.rentprice = rentprice;
    }

    public String getRentgrossprice() {
        return rentgrossprice;
    }

    public void setRentgrossprice(String rentgrossprice) {
        this.rentgrossprice = rentgrossprice;
    }

    public String getRentnetprice() {
        return rentnetprice;
    }

    public void setRentnetprice(String rentnetprice) {
        this.rentnetprice = rentnetprice;
    }

    public String getRentpaymenttype() {
        return rentpaymenttype;
    }

    public void setRentpaymenttype(String rentpaymenttype) {
        this.rentpaymenttype = rentpaymenttype;
    }

    public String getNumberofrooms() {
        return numberofrooms;
    }

    public void setNumberofrooms(String numberofrooms) {
        this.numberofrooms = numberofrooms;
    }

    public String getLivingspace() {
        return livingspace;
    }

    public void setLivingspace(String livingspace) {
        this.livingspace = livingspace;
    }

    public String getPropertyfloor() {
        return propertyfloor;
    }

    public void setPropertyfloor(String propertyfloor) {
        this.propertyfloor = propertyfloor;
    }

    public String getPropertyarea() {
        return propertyarea;
    }

    public void setPropertyarea(String propertyarea) {
        this.propertyarea = propertyarea;
    }

    public String getPropertydesc() {
        return propertydesc;
    }

    public void setPropertydesc(String propertydesc) {
        this.propertydesc = propertydesc;
    }

    public String getPropertyfeatures() {
        return propertyfeatures;
    }

    public void setPropertyfeatures(String propertyfeatures) {
        this.propertyfeatures = propertyfeatures;
    }

    public String getPropertyavailable() {
        return propertyavailable;
    }

    public void setPropertyavailable(String propertyavailable) {
        this.propertyavailable = propertyavailable;
    }

    public String getYearbuilt() {
        return yearbuilt;
    }

    public void setYearbuilt(String yearbuilt) {
        this.yearbuilt = yearbuilt;
    }

    public String getNameofseller() {
        return nameofseller;
    }

    public void setNameofseller(String nameofseller) {
        this.nameofseller = nameofseller;
    }

    public String getAgencyname() {
        return agencyname;
    }

    public void setAgencyname(String agencyname) {
        this.agencyname = agencyname;
    }

    public String getStreetnumberofseller() {
        return streetnumberofseller;
    }

    public void setStreetnumberofseller(String streetnumberofseller) {
        this.streetnumberofseller = streetnumberofseller;
    }

    public String getPostalcodeofseller() {
        return postalcodeofseller;
    }

    public void setPostalcodeofseller(String postalcodeofseller) {
        this.postalcodeofseller = postalcodeofseller;
    }

    public String getCityofseller() {
        return cityofseller;
    }

    public void setCityofseller(String cityofseller) {
        this.cityofseller = cityofseller;
    }

    public String getPhoneofseller() {
        return phoneofseller;
    }

    public void setPhoneofseller(String phoneofseller) {
        this.phoneofseller = phoneofseller;
    }

    public String getMnumberofseller() {
        return mnumberofseller;
    }

    public void setMnumberofseller(String mnumberofseller) {
        this.mnumberofseller = mnumberofseller;
    }

    public String getEmailofseller() {
        return emailofseller;
    }

    public void setEmailofseller(String emailofseller) {
        this.emailofseller = emailofseller;
    }

    public String getContactname() {
        return contactname;
    }

    public void setContactname(String contactname) {
        this.contactname = contactname;
    }

    public String getContactnumber() {
        return contactnumber;
    }

    public void setContactnumber(String contactnumber) {
        this.contactnumber = contactnumber;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getUrlofad() {
        return urlofad;
    }

    public void setUrlofad(String urlofad) {
        this.urlofad = urlofad;
    }

    public String getFlaged_p() {
        return flaged_p;
    }
    
    public void setFlaged_p(String flaged_p) {
        this.flaged_p = flaged_p;
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
    
}
