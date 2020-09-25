/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.tmrmswebscrapper;

import com.example.tmrmswebscrapper.Models.PropertyCsv;
import com.example.tmrmswebscrapper.Repos.CsvService;
import com.example.tmrmswebscrapper.Svc.PropertySvcI;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import net.sourceforge.tess4j.util.LoadLibs;
import org.apache.poi.common.usermodel.HyperlinkType;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Hyperlink;
import org.apache.poi.ss.usermodel.Picture;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.util.Units;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFClientAnchor;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
/*
 *
 *
 * @author User
 */
@Controller
public class CsvController {
    private static final double PADDING_SIZE = 0;
    private static final int PADDING = Units.toEMU(PADDING_SIZE);
    
    @Autowired
    private CsvService csvRepository;
    @Autowired
    private PropertySvcI pSvc;
    @RequestMapping(value = "/downloadCSV")
    public void doDownloadME(HttpServletRequest request, HttpServletResponse response, @RequestParam String iD) throws IOException {
        
        response.setHeader("Content-disposition", "attachment; filename=properties.xlsx");
        List<PropertyCsv> listp = csvRepository.propsList(iD);
        //build a file from scratch and then download
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Properties");
        Row header = sheet.createRow(0);
        header.createCell(0).setCellValue("Publish Date");
        header.createCell(1).setCellValue("Update Date");
        header.createCell(2).setCellValue("Search Type");
        header.createCell(3).setCellValue("Ms Region");
        header.createCell(4).setCellValue("Category Of Property");
        header.createCell(5).setCellValue("Property Type");
        header.createCell(6).setCellValue("Title Of Ad");
        header.createCell(7).setCellValue("Street Number");
        header.createCell(8).setCellValue("Postal Code");
        header.createCell(9).setCellValue("City");
        header.createCell(10).setCellValue("Sell Price");
        header.createCell(11).setCellValue("Sell Gross Price");
        header.createCell(12).setCellValue("Sell Net Price");
        header.createCell(13).setCellValue("Sell Payment Type");
        header.createCell(14).setCellValue("Rent Price");
        header.createCell(15).setCellValue("Rent Gross Price");
        header.createCell(16).setCellValue("Rent Net Price");
        header.createCell(17).setCellValue("Rent Payment Type");
        header.createCell(18).setCellValue("Number Of Rooms");
        header.createCell(19).setCellValue("Living Space");
        header.createCell(20).setCellValue("Property Floor");
        header.createCell(21).setCellValue("Property Area"); 
        header.createCell(22).setCellValue("Property description"); 
        header.createCell(23).setCellValue("Property Available"); 
        header.createCell(24).setCellValue("Year Built"); 
        header.createCell(25).setCellValue("Name Of Seller"); 
        header.createCell(26).setCellValue("Street Number Of Seller"); 
        header.createCell(27).setCellValue("Postal Code Of Seller"); 
        header.createCell(28).setCellValue("City Of Seller"); 
        header.createCell(29).setCellValue("Phone Of Seller"); 
        header.createCell(30).setCellValue("Mobile Number Of Seller"); 
        header.createCell(31).setCellValue("Email Of Seller"); 
        header.createCell(32).setCellValue("Contact Name"); 
        header.createCell(33).setCellValue("Contact Number");
        header.createCell(34).setCellValue("Agency Name");
        header.createCell(35).setCellValue("Vendor Type");
        header.createCell(36).setCellValue("Url Of Ad");
        int rowNum = 1;
        for (PropertyCsv dfe : listp) {
            
            Row row = sheet.createRow(rowNum++);
            
            row.createCell(0).setCellValue(dfe.getPublishDate());
            row.createCell(1).setCellValue(dfe.getUpdateDate());
            row.createCell(2).setCellValue(dfe.getSearchType());
            row.createCell(3).setCellValue(dfe.getMsRegion());
            row.createCell(4).setCellValue(dfe.getCatOfProperty());
            row.createCell(5).setCellValue(dfe.getPropertyType());
            row.createCell(6).setCellValue(dfe.getTitleOfAdd());
            row.createCell(7).setCellValue(dfe.getStreetNumber());
            row.createCell(8).setCellValue(dfe.getPostalCode());
            row.createCell(9).setCellValue(dfe.getCity());
            row.createCell(10).setCellValue(dfe.getSellPrice());
            row.createCell(11).setCellValue(dfe.getSellGrossPrice());
            row.createCell(12).setCellValue(dfe.getSellNetPrice());
            row.createCell(13).setCellValue(dfe.getSellPaymentType());
            row.createCell(14).setCellValue(dfe.getRentPrice());
            row.createCell(15).setCellValue(dfe.getRentGrossPrice());
            row.createCell(16).setCellValue(dfe.getRentNetPrice());
            row.createCell(17).setCellValue(dfe.getRentPaymentType());
            row.createCell(18).setCellValue(dfe.getNumberOfRooms());
            row.createCell(19).setCellValue(dfe.getLivingSpace());
            row.createCell(20).setCellValue(dfe.getPropertyFloor());
            row.createCell(21).setCellValue(dfe.getPropertyArea()); 
            row.createCell(22).setCellValue(dfe.getPropertyDesc()); 
            row.createCell(23).setCellValue(dfe.getPropertyAvailable()); 
            row.createCell(24).setCellValue(dfe.getYearBuilt()); 
            row.createCell(25).setCellValue(dfe.getNameOfSeller()); 
            row.createCell(26).setCellValue(dfe.getStreetNumberOfSeller()); 
            row.createCell(27).setCellValue(dfe.getPostalCodeOfSeller()); 
            row.createCell(28).setCellValue(dfe.getCityOfSeller()); 
            row.createCell(29).setCellValue(dfe.getPhoneOfSeller()); 
            row.createCell(30).setCellValue(dfe.getMnumberOfSeller()); 
            row.createCell(31).setCellValue(dfe.getEmailOfSeller()); 
            row.createCell(32).setCellValue(dfe.getContactName()); 
            row.createCell(33).setCellValue(dfe.getContactNumber());
            row.createCell(34).setCellValue(dfe.getAgencyName());
            row.createCell(35).setCellValue((dfe.getAgencyName().isEmpty() || dfe.getAgencyName() == null ? "Private vendor / Unassigned vendor" : "Institutional vendor"));
            Hyperlink href = workbook.getCreationHelper().createHyperlink(HyperlinkType.URL);
            href.setAddress(dfe.getUrlOfAd());
            row.createCell(36).setCellValue(dfe.getUrlOfAd());
            row.getCell(36).setHyperlink(href);
            
            CellStyle style = workbook.createCellStyle();
            Font font = workbook.createFont();
            font.setColor(HSSFColor.HSSFColorPredefined.BLUE.getIndex());
            style.setFont(font);
            
            row.getCell(36).setCellStyle(style);
            
            
        }
        workbook.write(response.getOutputStream());
        

//        String csvFileName = "properties.xlsx";
//        response.setContentType("application/vnd.ms-excel");
//        String headerKey = "Content-Disposition";
//        String headerValue = String.format("attachment; filename=\"%s\"",csvFileName);
//        response.setHeader(headerKey, headerValue);
//  
//        List<PropertyCsv> listp = csvRepository.propsList(iD);
//        
//        try (ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(),CsvPreference.STANDARD_PREFERENCE)){
//            String[] header = {"PublishDate", "UpdateDate", "SearchType", "MsRegion", "CatOfProperty", "PropertyType", "TitleOfAdd", "StreetNumber", "PostalCode", "City", "SellPrice", "SellGrossPrice", "SellNetPrice", "SellPaymentType", "RentPrice", "RentGrossPrice", "RentNetPrice", "RentPaymentType", "NumberOfRooms", "LivingSpace", "PropertyFloor", "PropertyArea", "PropertyDesc", "PropertyAvailable", "YearBuilt", "NameOfSeller", "StreetNumberOfSeller", "PostalCodeOfSeller", "CityOfSeller", "PhoneOfSeller", "MnumberOfSeller", "EmailOfSeller", "ContactName", "ContactNumber", "UrlOfAd"}; 
//            csvWriter.writeHeader(header);
//            for (PropertyCsv pList : listp){
//                csvWriter.write(pList,header);
//            }
//        }
    }
    
    @RequestMapping(value = "/downloadXLSX")
    public void doDownloadME(HttpServletRequest request, HttpServletResponse response, @RequestParam ArrayList<Integer> ids) throws IOException {
        
        response.setHeader("Content-disposition", "attachment; filename=properties.xlsx");
        
        List<PropertyCsv> listp = new ArrayList<>();
        
        for (Integer dfe : ids) {
            PropertyCsv ent = csvRepository.singleProp(Integer.toString(dfe));
            listp.add(ent);
        }
        
        //build a file from scratch and then download
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Properties");
        Row header = sheet.createRow(0);
        header.createCell(0).setCellValue("Publish Date");
        header.createCell(1).setCellValue("Update Date");
        header.createCell(2).setCellValue("Search Type");
        header.createCell(3).setCellValue("Ms Region");
        header.createCell(4).setCellValue("Category Of Property");
        header.createCell(5).setCellValue("Property Type");
        header.createCell(6).setCellValue("Title Of Ad");
        header.createCell(7).setCellValue("Street Number");
        header.createCell(8).setCellValue("Postal Code");
        header.createCell(9).setCellValue("City");
        header.createCell(10).setCellValue("Sell Price");
        header.createCell(11).setCellValue("Sell Gross Price");
        header.createCell(12).setCellValue("Sell Net Price");
        header.createCell(13).setCellValue("Sell Payment Type");
        header.createCell(14).setCellValue("Rent Price");
        header.createCell(15).setCellValue("Rent Gross Price");
        header.createCell(16).setCellValue("Rent Net Price");
        header.createCell(17).setCellValue("Rent Payment Type");
        header.createCell(18).setCellValue("Number Of Rooms");
        header.createCell(19).setCellValue("Living Space");
        header.createCell(20).setCellValue("Property Floor");
        header.createCell(21).setCellValue("Property Area"); 
        header.createCell(22).setCellValue("Property description"); 
        header.createCell(23).setCellValue("Property Available"); 
        header.createCell(24).setCellValue("Year Built"); 
        header.createCell(25).setCellValue("Name Of Seller"); 
        header.createCell(26).setCellValue("Street Number Of Seller"); 
        header.createCell(27).setCellValue("Postal Code Of Seller"); 
        header.createCell(28).setCellValue("City Of Seller"); 
        header.createCell(29).setCellValue("Phone Of Seller"); 
        header.createCell(30).setCellValue("Mobile Number Of Seller"); 
        header.createCell(31).setCellValue("Email Of Seller"); 
        header.createCell(32).setCellValue("Contact Name"); 
        header.createCell(33).setCellValue("Contact Number");
        header.createCell(34).setCellValue("Agency Name");
        header.createCell(35).setCellValue("Vendor Type");
        header.createCell(36).setCellValue("Url Of Ad");
        int rowNum = 1;
        for (PropertyCsv dfe : listp) {
            
            try{
                
            
                Row row = sheet.createRow(rowNum++);

                row.createCell(0).setCellValue(dfe.getPublishDate());
                row.createCell(1).setCellValue(dfe.getUpdateDate());
                row.createCell(2).setCellValue(dfe.getSearchType());
                row.createCell(3).setCellValue(dfe.getMsRegion());
                row.createCell(4).setCellValue(dfe.getCatOfProperty());
                row.createCell(5).setCellValue(dfe.getPropertyType());
                row.createCell(6).setCellValue(dfe.getTitleOfAdd());
                row.createCell(7).setCellValue(dfe.getStreetNumber());
                row.createCell(8).setCellValue(dfe.getPostalCode());
                row.createCell(9).setCellValue(dfe.getCity());
                row.createCell(10).setCellValue(dfe.getSellPrice());
                row.createCell(11).setCellValue(dfe.getSellGrossPrice());
                row.createCell(12).setCellValue(dfe.getSellNetPrice());
                row.createCell(13).setCellValue(dfe.getSellPaymentType());
                row.createCell(14).setCellValue(dfe.getRentPrice());
                row.createCell(15).setCellValue(dfe.getRentGrossPrice());
                row.createCell(16).setCellValue(dfe.getRentNetPrice());
                row.createCell(17).setCellValue(dfe.getRentPaymentType());
                row.createCell(18).setCellValue(dfe.getNumberOfRooms());
                row.createCell(19).setCellValue(dfe.getLivingSpace());
                row.createCell(20).setCellValue(dfe.getPropertyFloor());
                row.createCell(21).setCellValue(dfe.getPropertyArea()); 
                row.createCell(22).setCellValue(dfe.getPropertyDesc()); 
                row.createCell(23).setCellValue(dfe.getPropertyAvailable()); 
                row.createCell(24).setCellValue(dfe.getYearBuilt());
                
                if(dfe.getUrlOfAd().contains("urbanhome.ch")){
                    if(dfe.getNameOfSeller() != null && dfe.getNameOfSeller().isEmpty() == false){
                        drawImageInCell((XSSFWorkbook) workbook, (XSSFSheet) sheet, 0, 0, getImgText(dfe.getNameOfSeller()), 25, row.getRowNum(), 1, 1);
                        sheet.setColumnWidth(25, 7000);
                    }
                }else{
                    row.createCell(25).setCellValue(dfe.getNameOfSeller());
                }
                
                row.createCell(26).setCellValue(dfe.getStreetNumberOfSeller()); 
                row.createCell(27).setCellValue(dfe.getPostalCodeOfSeller()); 
                row.createCell(28).setCellValue(dfe.getCityOfSeller()); 
                
                if(dfe.getUrlOfAd().contains("urbanhome.ch")){
                    if(dfe.getPhoneOfSeller() != null && dfe.getPhoneOfSeller().isEmpty() == false){
                        drawImageInCell((XSSFWorkbook) workbook, (XSSFSheet) sheet, 0, 0, getImgText(dfe.getPhoneOfSeller()), 29, row.getRowNum(), 1, 1);
                        sheet.setColumnWidth(29, 7000);
                    }
                }else{
                    row.createCell(29).setCellValue(dfe.getPhoneOfSeller());
                }
                
                if(dfe.getUrlOfAd().contains("urbanhome.ch")){
                    if(dfe.getMnumberOfSeller() != null && dfe.getMnumberOfSeller().isEmpty() == false){
                        drawImageInCell((XSSFWorkbook) workbook, (XSSFSheet) sheet, 0, 0, getImgText(dfe.getMnumberOfSeller()), 30, row.getRowNum(), 1, 1);
                        sheet.setColumnWidth(30, 7000);
                    }
                }else{
                    row.createCell(30).setCellValue(dfe.getMnumberOfSeller());
                }
                
                row.createCell(31).setCellValue(dfe.getEmailOfSeller()); 
                
                if(dfe.getUrlOfAd().contains("urbanhome.ch")){
                    if(dfe.getContactName() != null && dfe.getContactName().isEmpty() == false){
                        drawImageInCell((XSSFWorkbook) workbook, (XSSFSheet) sheet, 0, 0, getImgText(dfe.getContactName()), 32, row.getRowNum(), 1, 1);
                        sheet.setColumnWidth(32, 7000);
                    }
                }else{
                    row.createCell(32).setCellValue(dfe.getContactName());
                }

                if(dfe.getUrlOfAd().contains("urbanhome.ch")){
                    if(dfe.getContactNumber() != null && dfe.getContactNumber().isEmpty() == false){
                        drawImageInCell((XSSFWorkbook) workbook, (XSSFSheet) sheet, 0, 0, getImgText(dfe.getContactNumber()), 33, row.getRowNum(), 1, 1);
                        sheet.setColumnWidth(33, 7000);
                    }
                }else{
                    row.createCell(33).setCellValue(dfe.getContactNumber());
                }
                
                row.createCell(34).setCellValue(dfe.getAgencyName());
                row.createCell(35).setCellValue((dfe.getAgencyName().isEmpty() || dfe.getAgencyName() == null ? "Private vendor / Unassigned vendor" : "Institutional vendor"));
                Hyperlink href = workbook.getCreationHelper().createHyperlink(HyperlinkType.URL);
                href.setAddress(dfe.getUrlOfAd().replaceAll(" ","%20"));
                row.createCell(36).setCellValue(dfe.getUrlOfAd().replaceAll(" ","%20"));
                row.getCell(36).setHyperlink(href);

                CellStyle style = workbook.createCellStyle();
                Font font = workbook.createFont();
                font.setColor(HSSFColor.HSSFColorPredefined.BLUE.getIndex());
                style.setFont(font);

                row.getCell(36).setCellStyle(style);
            
            }catch(Exception e){
                System.out.println(" Error " + e.getMessage());
            }
            
            
        }
        workbook.write(response.getOutputStream());
    }
    
    @RequestMapping(value = "/downloadSCSV")
    public void doDownloadSE(HttpServletRequest request,HttpServletResponse response , @RequestParam String iD) throws IOException {

        response.setHeader("Content-disposition", "attachment; filename=property.xlsx");
        PropertyCsv dfe = csvRepository.singleProp(iD);
        //build a file from scratch and then download
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Property");
        Row header = sheet.createRow(0);
        header.createCell(0).setCellValue("Publish Date");
        header.createCell(1).setCellValue("Update Date");
        header.createCell(2).setCellValue("Search Type");
        header.createCell(3).setCellValue("Ms Region");
        header.createCell(4).setCellValue("Category Of Property");
        header.createCell(5).setCellValue("Property Type");
        header.createCell(6).setCellValue("Title Of Ad");
        header.createCell(7).setCellValue("Street Number");
        header.createCell(8).setCellValue("Postal Code");
        header.createCell(9).setCellValue("City");
        header.createCell(10).setCellValue("Sell Price");
        header.createCell(11).setCellValue("Sell Gross Price");
        header.createCell(12).setCellValue("Sell Net Price");
        header.createCell(13).setCellValue("Sell Payment Type");
        header.createCell(14).setCellValue("Rent Price");
        header.createCell(15).setCellValue("Rent Gross Price");
        header.createCell(16).setCellValue("Rent Net Price");
        header.createCell(17).setCellValue("Rent Payment Type");
        header.createCell(18).setCellValue("Number Of Rooms");
        header.createCell(19).setCellValue("Living Space");
        header.createCell(20).setCellValue("Property Floor");
        header.createCell(21).setCellValue("Property Area"); 
        header.createCell(22).setCellValue("Property description"); 
        header.createCell(23).setCellValue("Property Available"); 
        header.createCell(24).setCellValue("Year Built"); 
        header.createCell(25).setCellValue("Name Of Seller"); 
        header.createCell(26).setCellValue("Street Number Of Seller"); 
        header.createCell(27).setCellValue("Postal Code Of Seller"); 
        header.createCell(28).setCellValue("City Of Seller"); 
        header.createCell(29).setCellValue("Phone Of Seller"); 
        header.createCell(30).setCellValue("Mobile Number Of Seller"); 
        header.createCell(31).setCellValue("Email Of Seller"); 
        header.createCell(32).setCellValue("Contact Name"); 
        header.createCell(33).setCellValue("Contact Number");
        header.createCell(34).setCellValue("Agency Name");
        header.createCell(35).setCellValue("Vendor Type");
        header.createCell(36).setCellValue("Url Of Ad");
            
            Row row = sheet.createRow(1);
            
            row.createCell(0).setCellValue(dfe.getPublishDate());
            row.createCell(1).setCellValue(dfe.getUpdateDate());
            row.createCell(2).setCellValue(dfe.getSearchType());
            row.createCell(3).setCellValue(dfe.getMsRegion());
            row.createCell(4).setCellValue(dfe.getCatOfProperty());
            row.createCell(5).setCellValue(dfe.getPropertyType());
            row.createCell(6).setCellValue(dfe.getTitleOfAdd());
            row.createCell(7).setCellValue(dfe.getStreetNumber());
            row.createCell(8).setCellValue(dfe.getPostalCode());
            row.createCell(9).setCellValue(dfe.getCity());
            row.createCell(10).setCellValue(dfe.getSellPrice());
            row.createCell(11).setCellValue(dfe.getSellGrossPrice());
            row.createCell(12).setCellValue(dfe.getSellNetPrice());
            row.createCell(13).setCellValue(dfe.getSellPaymentType());
            row.createCell(14).setCellValue(dfe.getRentPrice());
            row.createCell(15).setCellValue(dfe.getRentGrossPrice());
            row.createCell(16).setCellValue(dfe.getRentNetPrice());
            row.createCell(17).setCellValue(dfe.getRentPaymentType());
            row.createCell(18).setCellValue(dfe.getNumberOfRooms());
            row.createCell(19).setCellValue(dfe.getLivingSpace());
            row.createCell(20).setCellValue(dfe.getPropertyFloor());
            row.createCell(21).setCellValue(dfe.getPropertyArea()); 
            row.createCell(22).setCellValue(dfe.getPropertyDesc()); 
            row.createCell(23).setCellValue(dfe.getPropertyAvailable()); 
            row.createCell(24).setCellValue(dfe.getYearBuilt()); 
            
            if(dfe.getUrlOfAd().contains("urbanhome.ch")){
                if(dfe.getNameOfSeller() != null && dfe.getNameOfSeller().isEmpty() == false){
                    drawImageInCell((XSSFWorkbook) workbook, (XSSFSheet) sheet, 0, 0, getImgText(dfe.getNameOfSeller()), 25, row.getRowNum(), 1, 1);
                    sheet.setColumnWidth(25, 7000);
                }
            }else{
                row.createCell(25).setCellValue(dfe.getNameOfSeller());
            }
            
            row.createCell(26).setCellValue(dfe.getStreetNumberOfSeller()); 
            row.createCell(27).setCellValue(dfe.getPostalCodeOfSeller()); 
            row.createCell(28).setCellValue(dfe.getCityOfSeller()); 
            
            if(dfe.getUrlOfAd().contains("urbanhome.ch")){
                if(dfe.getPhoneOfSeller() != null && dfe.getPhoneOfSeller().isEmpty() == false){
                    drawImageInCell((XSSFWorkbook) workbook, (XSSFSheet) sheet, 0, 0, getImgText(dfe.getPhoneOfSeller()), 29, row.getRowNum(), 1, 1);
                    sheet.setColumnWidth(29, 7000);
                }
            }else{
                row.createCell(29).setCellValue(dfe.getPhoneOfSeller());
            }
            
            if(dfe.getUrlOfAd().contains("urbanhome.ch")){
                if(dfe.getMnumberOfSeller() != null && dfe.getMnumberOfSeller().isEmpty() == false){
                    drawImageInCell((XSSFWorkbook) workbook, (XSSFSheet) sheet, 0, 0, getImgText(dfe.getMnumberOfSeller()), 30, row.getRowNum(), 1, 1);
                    sheet.setColumnWidth(30, 7000);
                }
            }else{
                row.createCell(30).setCellValue(dfe.getMnumberOfSeller());
            }
            
            row.createCell(31).setCellValue(dfe.getEmailOfSeller());  

            if(dfe.getUrlOfAd().contains("urbanhome.ch")){
                if(dfe.getContactName() != null && dfe.getContactName().isEmpty() == false){
                    drawImageInCell((XSSFWorkbook) workbook, (XSSFSheet) sheet, 0, 0, getImgText(dfe.getContactName()), 32, row.getRowNum(), 1, 1);
                    sheet.setColumnWidth(32, 7000);
                }
            }else{
                row.createCell(32).setCellValue(dfe.getContactName());
            }
            
            if(dfe.getUrlOfAd().contains("urbanhome.ch")){
                if(dfe.getContactNumber() != null && dfe.getContactNumber().isEmpty() == false){
                    drawImageInCell((XSSFWorkbook) workbook, (XSSFSheet) sheet, 0, 0, getImgText(dfe.getContactNumber()), 33, row.getRowNum(), 1, 1);
                    sheet.setColumnWidth(33, 7000);
                }
            }else{
                row.createCell(33).setCellValue(dfe.getContactNumber());
            }
            
            row.createCell(34).setCellValue(dfe.getAgencyName());
            row.createCell(35).setCellValue((dfe.getAgencyName().isEmpty() || dfe.getAgencyName() == null ? "Private vendor / Unassigned vendor" : "Institutional vendor"));
            Hyperlink href = workbook.getCreationHelper().createHyperlink(HyperlinkType.URL);
            href.setAddress(dfe.getUrlOfAd());
            row.createCell(36).setCellValue(dfe.getUrlOfAd());
            row.getCell(36).setHyperlink(href);
            
            CellStyle style = workbook.createCellStyle();
            Font font = workbook.createFont();
            font.setColor(HSSFColor.HSSFColorPredefined.BLUE.getIndex());
            style.setFont(font);
            
            row.getCell(36).setCellStyle(style);
            
            sheet.setColumnWidth(30, 7000);
            
                     
            workbook.write(response.getOutputStream());


//        String csvFileName = "property.xlsx";
//        response.setContentType("application/vnd.ms-excel");
//        String headerKey = "Content-Disposition";
//        String headerValue = String.format("attachment; filename=\"%s\"",csvFileName);
//        response.setHeader(headerKey, headerValue);
//  
//        PropertyCsv listp = csvRepository.singleProp(iD);
//        
//        try (ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(),CsvPreference.STANDARD_PREFERENCE)){
//            String[] header = {"PublishDate", "UpdateDate", "SearchType", "MsRegion", "CatOfProperty", "PropertyType", "TitleOfAdd", "StreetNumber", "PostalCode", "City", "SellPrice", "SellGrossPrice", "SellNetPrice", "SellPaymentType", "RentPrice", "RentGrossPrice", "RentNetPrice", "RentPaymentType", "NumberOfRooms", "LivingSpace", "PropertyFloor", "PropertyArea", "PropertyDesc", "PropertyAvailable", "YearBuilt", "NameOfSeller", "StreetNumberOfSeller", "PostalCodeOfSeller", "CityOfSeller", "PhoneOfSeller", "MnumberOfSeller", "EmailOfSeller", "ContactName", "ContactNumber", "UrlOfAd"}; 
//            csvWriter.writeHeader(header);
//            csvWriter.write(listp,header);
//        }
    }
    
    public void drawImageInCell(XSSFWorkbook wb, XSSFSheet sheet, int cellW, int cellH, BufferedImage imgI, int col, int row, int colSize, int rowSize){
        try{
            BufferedImage img = imgI;
            int[] anchorArray = calCellAnchor(Units.pixelToPoints(cellW), Units.pixelToPoints(cellH), img.getWidth(), img.getHeight());
            XSSFClientAnchor anchor = new XSSFClientAnchor(anchorArray[0], anchorArray[1], anchorArray[2],
            anchorArray[3], (short) col, row, (short) (col + colSize), row + rowSize);

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(img, "gif", baos);
            baos.flush();
            byte[] imageInByte = baos.toByteArray();
            baos.close();

            int index = wb.addPicture(imageInByte, XSSFWorkbook.PICTURE_TYPE_JPEG);
            sheet.createDrawingPatriarch().createPicture(anchor, index);
      
        }catch(IOException ex){
            System.out.println("Error");
        }catch(Exception ex){
            System.out.println("Error");
        }
    }
    
    public static int[] calCellAnchor(double cellX, double cellY, int imgX, int imgY) {
        return calCoordinate(true, cellX, cellY, imgX, imgY);
    }
    
    private static int[] calCoordinate(boolean fixTop, double cellX, double cellY, int imgX, int imgY) {
        double ratio = ((double) imgX) / imgY;
        int x = (int) Math.round(Units.toEMU(cellY - 2 * PADDING_SIZE) * ratio);
        x = (Units.toEMU(cellX) - x) / 2;
        if (x < PADDING) {
            return calCoordinate(false, cellY, cellX, imgY, imgX);
        }
        return calDirection(fixTop, x);
    }
    
    private static int[] calDirection(boolean fixTop, int x) {
        if (fixTop) {
            return new int[] { x, PADDING, -x, -PADDING };
        } else {
            return new int[] { PADDING, x, -PADDING, -x };
        }
    }

    
    public BufferedImage getImgText(String valueStr){
        
        BufferedImage c = null;
        
        try
        {
            URL url = new URL("https://www.urbanhome.ch" + valueStr);
            c = ImageIO.read(url);
        }
        catch (MalformedURLException ex) {
            System.out.println("Error");
        }
        catch (IOException ex) {
            System.out.println("Error");
        }catch (Exception ex) {
            System.out.println("Error");
        }
        
        return c;
      
    }
    
}
