/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.tmrmswebscrapper;

import com.example.tmrmswebscrapper.Models.PropertyList;
import com.example.tmrmswebscrapper.Repos.PropertyService;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author User
 */
@Controller
public class ELController {
    @Autowired
    private PropertyService propRepository;
    
    @RequestMapping("/exportproperties")
    public String page(Model model) {
        //ModelAndView model = new ModelAndView("export");
	//model.addObject("lists", propRepository_.getListOfDatesTime());
        
        ArrayList<PropertyList> pl = propRepository.getListOfDatesTime();
        //model.addAttribute("lists", pl.size() > 20 ? pl.subList(0, 20): pl);
        model.addAttribute("lists", pl);
        return "exportproperties";
    }
    
}
