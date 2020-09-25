/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.tmrmswebscrapper.Svc;

import com.example.tmrmswebscrapper.Entity.fcategorytypes;
import com.example.tmrmswebscrapper.Entity.fcities;
import com.example.tmrmswebscrapper.Entity.filters;
import com.example.tmrmswebscrapper.Entity.fpropertyfeatures;
import com.example.tmrmswebscrapper.Entity.fpropertytypes;
import com.example.tmrmswebscrapper.Entity.fsellingtypes;
import com.example.tmrmswebscrapper.Entity.fvendortypes;
import com.example.tmrmswebscrapper.Models.FCategoryType;
import com.example.tmrmswebscrapper.Models.FRegion;
import com.example.tmrmswebscrapper.Models.FPropertyFeature;
import com.example.tmrmswebscrapper.Models.FPropertyType;
import com.example.tmrmswebscrapper.Models.FSellingType;
import com.example.tmrmswebscrapper.Models.FVendorType;
import com.example.tmrmswebscrapper.Models.Filter;
import com.example.tmrmswebscrapper.Repos.FiltersDAOI;
import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Lubbow
 */
@Service
@Transactional
public class FiltersSvc implements FiltersSvcI {
    private final FiltersDAOI fDAO;
     
    @Autowired
    public FiltersSvc(FiltersDAOI DAO) {
        this.fDAO = DAO;
    }
    
    @Override
    public Integer addNewF(Filter fm) {
        filters f = new filters();
        
        f.setFid(fm.getId());
        f.setNameoffilter(fm.getNameOfFilter());
        f.setPricefrom(fm.getPriceFrom());
        f.setPriceto(fm.getPriceTo());
        f.setRoomsfrom(fm.getRoomFrom());
        f.setRoomsto(fm.getRoomTo());
        f.setLivingspacefrom(fm.getLivingSpaceFrom());
        f.setLivingspaceto(fm.getLivingSpaceTo());
        f.setFloorspaceplotareafrom(fm.getFloorSpacePlotAreaFrom());
        f.setFloorspaceplotareato(fm.getFloorSpacePlotAreaTo());
        f.setBuiltyearfrom(fm.getBuiltYearFrom());
        f.setBuiltyearto(fm.getBuiltYearTo());
        f.setAvailablefrom(fm.getAvailableFrom());
        f.setAvailableto(fm.getAvailableTo());
        f.setDate_(fm.getDate_());
        f.setTime_(fm.getTime_());
        f.setMonth_(fm.getMonth_());
        f.setYear_(fm.getYear_());
        
        List<fcategorytypes> lfct = new ArrayList();
        fm.getfCategoryType().stream().map((dfm) -> {
            fcategorytypes fct = new fcategorytypes();
            fct.setNameofcat(dfm.getNameOfCat());
            fct.setDate_(dfm.getDate_());
            fct.setTime_(dfm.getTime_());
            fct.setMonth_(dfm.getMonth_());
            fct.setYear_(dfm.getYear_());
            return fct;
        }).forEachOrdered((fct) -> {
            lfct.add(fct);
        });
        f.setFcategorytype(lfct);
        
        List<fcities> lfc = new ArrayList();
        fm.getfRegion().stream().map((dfm) -> {
            fcities fc = new fcities();
            fc.setIdofregion(dfm.getIdOfRegion());
            fc.setDate_(dfm.getDate_());
            fc.setTime_(dfm.getTime_());
            fc.setMonth_(dfm.getMonth_());
            fc.setYear_(dfm.getYear_());
            return fc;
        }).forEachOrdered((fc) -> {
            lfc.add(fc);
        });
        f.setFcities(lfc);
        
        List<fpropertyfeatures> lfpf = new ArrayList();
        fm.getfPropertyFeature().stream().map((dfm) -> {
            fpropertyfeatures fpf = new fpropertyfeatures();
            fpf.setNameoffeature(dfm.getNameOfFeature());
            fpf.setDate_(dfm.getDate_());
            fpf.setTime_(dfm.getTime_());
            fpf.setMonth_(dfm.getMonth_());
            fpf.setYear_(dfm.getYear_());
            return fpf;
        }).forEachOrdered((fpf) -> {
            lfpf.add(fpf);
        });
        f.setFpropertyfeatures(lfpf);
        
        List<fsellingtypes> lfst = new ArrayList();
        fm.getfSellingType().stream().map((dfm) -> {
            fsellingtypes fst = new fsellingtypes();
            fst.setNameofsellingtype(dfm.getNameOfSellingType());
            fst.setDate_(dfm.getDate_());
            fst.setTime_(dfm.getTime_());
            fst.setMonth_(dfm.getMonth_());
            fst.setYear_(dfm.getYear_());
            return fst;
        }).forEachOrdered((fst) -> {
            lfst.add(fst);
        });
        f.setFsellingtype(lfst);
        
        List<fvendortypes> lfvt = new ArrayList();
        fm.getfVendorType().stream().map((dfm) -> {
            fvendortypes fvt = new fvendortypes();
            fvt.setNameofvendortype(dfm.getNameOfVendorType());
            fvt.setDate_(dfm.getDate_());
            fvt.setTime_(dfm.getTime_());
            fvt.setMonth_(dfm.getMonth_());
            fvt.setYear_(dfm.getYear_());
            return fvt;
        }).forEachOrdered((fvt) -> {
            lfvt.add(fvt);
        });
        f.setFvendortype(lfvt);
        
        
        List<fpropertytypes> lfpt = new ArrayList();
        fm.getfPropertyType().stream().map((dfm) -> {
            fpropertytypes fpt = new fpropertytypes();
            fpt.setNameoftype(dfm.getNameOfType());
            fpt.setDate_(dfm.getDate_());
            fpt.setTime_(dfm.getTime_());
            fpt.setMonth_(dfm.getMonth_());
            fpt.setYear_(dfm.getYear_());
            return fpt;
        }).forEachOrdered((fpt) -> {
            lfpt.add(fpt);
        });
        f.setFpropertytype(lfpt);
        
        return this.fDAO.addNewF(f);
    }
    
    @Override
    public Integer updateFilter(Filter fm, List<String> fCategoryTypeToDel, List<String> fCityToDel, List<String> fPropertyFeatureToDel, List<String> fSellingTypeToDel, List<String> fVendorTypeToDel, List<String> fPropertyTypeToDel){
        
        filters f = new filters();
        
        f.setFid(fm.getId());
        f.setNameoffilter(fm.getNameOfFilter());
        f.setPricefrom(fm.getPriceFrom());
        f.setPriceto(fm.getPriceTo());
        f.setRoomsfrom(fm.getRoomFrom());
        f.setRoomsto(fm.getRoomTo());
        f.setLivingspacefrom(fm.getLivingSpaceFrom());
        f.setLivingspaceto(fm.getLivingSpaceTo());
        f.setFloorspaceplotareafrom(fm.getFloorSpacePlotAreaFrom());
        f.setFloorspaceplotareato(fm.getFloorSpacePlotAreaTo());
        f.setBuiltyearfrom(fm.getBuiltYearFrom());
        f.setBuiltyearto(fm.getBuiltYearTo());
        f.setAvailablefrom(fm.getAvailableFrom());
        f.setAvailableto(fm.getAvailableTo());
        
        f = this.fDAO.updateFilter(f);
        
        if(f == null){
            return 0;
        }else{
            
            for(FCategoryType dfm : fm.getfCategoryType()){
                fcategorytypes fct = new fcategorytypes();
                fct.setFilter(f);
                fct.setNameofcat(dfm.getNameOfCat());
                fct.setDate_(dfm.getDate_());
                fct.setTime_(dfm.getTime_());
                fct.setMonth_(dfm.getMonth_());
                fct.setYear_(dfm.getYear_());
                this.fDAO.addNewCTF(fct);
            }
            
            fCategoryTypeToDel.forEach((dfm) -> {
                this.fDAO.deleteCTFByName(dfm);
            });

            for(FRegion dfm : fm.getfRegion()){
                fcities fc = new fcities();
                fc.setFilter(f);
                fc.setIdofregion(dfm.getIdOfRegion());
                fc.setDate_(dfm.getDate_());
                fc.setTime_(dfm.getTime_());
                fc.setMonth_(dfm.getMonth_());
                fc.setYear_(dfm.getYear_());
                this.fDAO.addNewCF(fc);
            }
            
            fCityToDel.forEach((dfm) -> {
                this.fDAO.deleteCFByName(dfm);
            });

            for(FPropertyFeature dfm : fm.getfPropertyFeature()){
                fpropertyfeatures fpf = new fpropertyfeatures();
                fpf.setFilter(f);
                fpf.setNameoffeature(dfm.getNameOfFeature());
                fpf.setDate_(dfm.getDate_());
                fpf.setTime_(dfm.getTime_());
                fpf.setMonth_(dfm.getMonth_());
                fpf.setYear_(dfm.getYear_());
                this.fDAO.addNewPFF(fpf);
            }
            
            fPropertyFeatureToDel.forEach((dfm) -> {
                this.fDAO.deletePFFByName(dfm);
            });

            for(FSellingType dfm:fm.getfSellingType()){
                fsellingtypes fst = new fsellingtypes();
                fst.setFilter(f);
                fst.setNameofsellingtype(dfm.getNameOfSellingType());
                fst.setDate_(dfm.getDate_());
                fst.setTime_(dfm.getTime_());
                fst.setMonth_(dfm.getMonth_());
                fst.setYear_(dfm.getYear_());
                this.fDAO.addNewSTF(fst);
            }

            fSellingTypeToDel.forEach((dfm) -> {
                this.fDAO.deleteSTFByName(dfm);
            });
            
            for(FVendorType dfm : fm.getfVendorType()){
                fvendortypes fvt = new fvendortypes();
                fvt.setFilter(f);
                fvt.setNameofvendortype(dfm.getNameOfVendorType());
                fvt.setDate_(dfm.getDate_());
                fvt.setTime_(dfm.getTime_());
                fvt.setMonth_(dfm.getMonth_());
                fvt.setYear_(dfm.getYear_());
                this.fDAO.addNewVTF(fvt);
            }
            
            fVendorTypeToDel.forEach((dfm) -> {
                this.fDAO.deleteVTFByName(dfm);
            });

            for(FPropertyType dfm : fm.getfPropertyType()){
                fpropertytypes fpt = new fpropertytypes();
                fpt.setFilter(f);
                fpt.setNameoftype(dfm.getNameOfType());
                fpt.setDate_(dfm.getDate_());
                fpt.setTime_(dfm.getTime_());
                fpt.setMonth_(dfm.getMonth_());
                fpt.setYear_(dfm.getYear_());
                this.fDAO.addNewPTF(fpt);
            }
            
            fPropertyTypeToDel.forEach((dfm) -> {
                this.fDAO.deletePTFByName(dfm);
            });
            
            return f.getFid();
        }
    }
    
    @Override
    public List<Filter> getFirstFiveFilters(){
        List<filters> dfdb =  this.fDAO.getFirstFiveFilters();
        List<Filter> dtf = new ArrayList<>();
        dfdb.stream().map((dfe) -> {
            Filter f = new Filter();
            f.setId(dfe.getFid());
            f.setNameOfFilter(dfe.getNameoffilter());
            return f;
        }).forEachOrdered((f) -> {
            dtf.add(f);
        });
        return dtf;
    }
    
    @Override
    public List<Filter> getSearchedFilters(String sP){
        List<filters> dfdb =  this.fDAO.getSearchedFilters(sP);
        List<Filter> dtf = new ArrayList<>();
        dfdb.stream().map((dfe) -> {
            Filter f = new Filter();
            f.setId(dfe.getFid());
            f.setNameOfFilter(dfe.getNameoffilter());
            return f;
        }).forEachOrdered((f) -> {
            dtf.add(f);
        });
        return dtf;
    }
    
    
    @Override
    public boolean deleteFilterById(Integer id){
       return this.fDAO.deletebyId(id);
    }
    
    @Override
    public Filter getFById(Integer id){
        
        filters f = this.fDAO.getFbyID(id);
        Filter fm = new Filter();
        if(f != null){
            fm.setId(f.getFid());
            fm.setNameOfFilter(f.getNameoffilter());
            fm.setPriceFrom(f.getPricefrom());
            fm.setPriceTo(f.getPriceto());
            fm.setRoomFrom(f.getRoomsfrom());
            fm.setRoomTo(f.getRoomsto());
            fm.setLivingSpaceFrom(f.getLivingspacefrom());
            fm.setLivingSpaceTo(f.getLivingspaceto());
            fm.setFloorSpacePlotAreaFrom(f.getFloorspaceplotareafrom());
            fm.setFloorSpacePlotAreaTo(f.getFloorspaceplotareato());
            fm.setBuiltYearFrom(f.getBuiltyearfrom());
            fm.setBuiltYearTo(f.getBuiltyearto());
            fm.setAvailableFrom(f.getAvailablefrom());
            fm.setAvailableTo(f.getAvailableto());
            fm.setDate_(f.getDate_());
            fm.setTime_(f.getTime_());
            fm.setMonth_(f.getMonth_());
            fm.setYear_(f.getYear_());

            List<FCategoryType> lfct = new ArrayList();
            f.getFcategorytype().stream().map((dfm) -> new FCategoryType(0,dfm.getNameofcat(),dfm.getDate_(),dfm.getTime_(),dfm.getMonth_(),dfm.getYear_())).forEachOrdered((fct) -> {
                lfct.add(fct);
            });
            fm.setfCategoryType(lfct);

            List<FRegion> lfc = new ArrayList();
            f.getFcities().stream().map((dfm) -> new FRegion(0,dfm.getIdofregion(),dfm.getDate_(),dfm.getTime_(),dfm.getMonth_(),dfm.getYear_())).forEachOrdered((fct) -> {
                lfc.add(fct);
            });
            fm.setfRegion(lfc);

            List<FPropertyFeature> lfpf = new ArrayList();
            f.getFpropertyfeatures().stream().map((dfm) -> new FPropertyFeature(0,dfm.getNameoffeature(),dfm.getDate_(),dfm.getTime_(),dfm.getMonth_(),dfm.getYear_())).forEachOrdered((fpf) -> {
                lfpf.add(fpf);
            });
            fm.setfPropertyFeature(lfpf);

            List<FSellingType> lfst = new ArrayList();
            f.getFsellingtype().stream().map((dfm) -> new FSellingType(0,dfm.getNameofsellingtype(),dfm.getDate_(),dfm.getTime_(),dfm.getMonth_(),dfm.getYear_())).forEachOrdered((fst) -> {
                lfst.add(fst);
            });
            fm.setfSellingType(lfst);

            List<FVendorType> lfvt = new ArrayList();
            f.getFvendortype().stream().map((dfm) -> new FVendorType(0,dfm.getNameofvendortype(),dfm.getDate_(),dfm.getTime_(),dfm.getMonth_(),dfm.getYear_())).forEachOrdered((fvt) -> {
                lfvt.add(fvt);
            });
            fm.setfVendorType(lfvt);

            List<FPropertyType> lfpt = new ArrayList();
            f.getFpropertytype().stream().map((dfm) -> {
                FPropertyType fpt = new FPropertyType(0,dfm.getNameoftype(),dfm.getDate_(),dfm.getTime_(),dfm.getMonth_(),dfm.getYear_());
                return fpt;
            }).forEachOrdered((fpt) -> {
                lfpt.add(fpt);
            });
            fm.setfPropertyType(lfpt);
        }
        
        return fm;
    }
}
