/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.tmrmswebscrapper;

import com.example.tmrmswebscrapper.Svc.BlockedVendorSvcI;
import com.example.tmrmswebscrapper.Svc.CronJobSvcI;
import com.example.tmrmswebscrapper.Svc.MrCitySvcI;
import com.example.tmrmswebscrapper.Svc.RegionSvcI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author MusaRaza
 */
@Controller
public class GController {
    @Autowired
    private BlockedVendorSvcI bvSvc;
    @Autowired
    private RegionSvcI rSvc;
    @Autowired
    private MrCitySvcI rcSvc;
    @Autowired
    private CronJobSvcI cjSvc;
    
    @RequestMapping("/")
    public String index() {
        return "index";
    }
    
    @RequestMapping("/autoservice")
    public String autoService() {
        return "autoservice";
    }
    
    @RequestMapping("/flaggedvendors")
    public String flaggedVendors(Model model){
        model.addAttribute("lists", bvSvc.getFirstFiveVendors());
        return "flaggedvendors";
    }
    
    @RequestMapping("/flaggedproperties")
    public String flaggedProperties(){
        return "flaggedproperties";
    }
    
    @RequestMapping("/offlineproperties")
    public String offlineProperties(){
        return "offlineproperties";
    }
    
    @RequestMapping("/msregions")
    public String msRegions(Model model){
        model.addAttribute("lists", rSvc.getMsRegions());
        return "msregions";
    }
    
    @RequestMapping("/cronjobs")
    public String cronJobs(Model model){
        model.addAttribute("lists", cjSvc.getCronJobs());
        return "cronjobs";
    }
    
    @RequestMapping("/msregionscities")
    public String msRegionsCities(Model model,@RequestParam(defaultValue="0") Integer rid){
        
        if(rid == 0){
            model.addAttribute("lists", rcSvc.getMrCities());
            model.addAttribute("show", false);
        }else{
            model.addAttribute("lists", rcSvc.getFilteredMrCities(rid));
            model.addAttribute("show", true);
        }
        
        return "msregionscities";
    }
}