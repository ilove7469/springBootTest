package com.kosta.sbproject.controller;

import java.io.BufferedOutputStream;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kosta.sbproject.model.Tw_test;
import com.kosta.sbproject.service.TwTestService;

import lombok.extern.java.Log;

@Log
@RestController
//@Controller
public class twTestController {
	
	@Autowired 
	TwTestService TwTestService;
	 
	@GetMapping("/twlist")
	public List<Tw_test> deptAll(Model model) {
		//model.addAttribute("deptlist", TwTestService.findAll());
		//System.out.println(model);
		return TwTestService.findAll();
	}
	
	
	@RequestMapping(value="/exceldownload.do")
    public void excelDownload( HttpServletRequest request ,HttpServletResponse response ,HttpSession session, Tw_test param) throws Exception {
        
        OutputStream out = null;
        
        try {
            HSSFWorkbook workbook = TwTestService.listExcelDownload(param);
            
            response.reset();
            response.setHeader("Content-Disposition", "attachment;filename=stbcs_history.xls");
            response.setContentType("application/vnd.ms-excel");
            out = new BufferedOutputStream(response.getOutputStream());
            
            workbook.write(out);
            out.flush();
            
        } catch (Exception e) {
           // logger.error("exception during downloading excel file : {}", e);
        } finally {
            if(out != null) out.close();
        }    
    }

}
