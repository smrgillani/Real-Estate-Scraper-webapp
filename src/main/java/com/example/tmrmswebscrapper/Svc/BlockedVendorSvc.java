/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.tmrmswebscrapper.Svc;
import com.example.tmrmswebscrapper.Entity.blocked_vendors;
import com.example.tmrmswebscrapper.Models.BlockedVendor;
import com.example.tmrmswebscrapper.Repos.BlockedVendorsDAOI;
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
public class BlockedVendorSvc implements BlockedVendorSvcI {
    private BlockedVendorsDAOI vDAO;
    @Autowired
    public BlockedVendorSvc(BlockedVendorsDAOI DAO) {
        this.vDAO = DAO;
    }
    
    @Override
    public Integer addNew(BlockedVendor bv) {
        blocked_vendors pe = new blocked_vendors();
        pe.setVendor_name(bv.getVName());
        pe.setVendor_id(bv.getVid());
        pe.setWebsite(bv.getWS());
        return this.vDAO.addNew(pe);
    }
    
    @Override
    public List<BlockedVendor> getFirstFiveVendors(){
        List<blocked_vendors> dfdb =  this.vDAO.getFirstFiveVendors();
        List<BlockedVendor> dtf = new ArrayList<>();
        dfdb.stream().map((dfe) -> {
            BlockedVendor f = new BlockedVendor(Integer.toString(dfe.getId()),dfe.getVendor_name(),dfe.getVendor_id(),dfe.getWebsite());
            return f;
        }).forEachOrdered((f) -> {
            dtf.add(f);
        });
        return dtf;
    }
    
    @Override
    public boolean delById(Integer vId){
        return this.vDAO.deletebyId(vId);
    }
    
    @Override
    public Integer getVbyIdAndWS(String VendorId, String WebSite){
        return this.vDAO.getVbyIdAndWS(VendorId, WebSite);
    }

}
