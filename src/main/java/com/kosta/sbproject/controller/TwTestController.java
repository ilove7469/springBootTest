package com.kosta.sbproject.controller;

import java.io.BufferedOutputStream;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kosta.sbproject.model.Tw_test;
import com.kosta.sbproject.service.TwTestService;

import lombok.extern.java.Log;

@Log
//@RestController
@Controller
public class TwTestController {
	
	@Autowired 
	TwTestService TwTestService;
	 
	@GetMapping("/twlist")
	public List<Tw_test> deptAll(Model model) {
		//model.addAttribute("deptlist", TwTestService.findAll());
		//System.out.println(model);
		return TwTestService.findAll();
	}
	
	@GetMapping("/twinsert")
	public void twinsert() {
		
	}
	
	@PostMapping("/twinsert")
	public void twinsertPost(Tw_test board, RedirectAttributes rttr) {
		//System.out.println(board);
		
		TwTestService.insertBoard(board);
		
		//return "redirect:/twlist";
	}
	
	
	@RequestMapping(value="/exceldownload")
    public void excelDownload( HttpServletRequest request ,HttpServletResponse response ,HttpSession session, Tw_test param) throws Exception {
        
        OutputStream out = null;
        
        try {
            HSSFWorkbook workbook = TwTestService.listExcelDownload(param);
            
            
            // 테이블 헤더용 스타일
            CellStyle headStyle = workbook.createCellStyle();

            // 가는 경계선을 가집니다.
            headStyle.setBorderTop(BorderStyle.THIN);
            headStyle.setBorderBottom(BorderStyle.THIN);
            headStyle.setBorderLeft(BorderStyle.THIN);
            headStyle.setBorderRight(BorderStyle.THIN);

            // 배경색은 노란색입니다.
            headStyle.setFillForegroundColor(HSSFColor.LIGHT_YELLOW.index);
            headStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);

            // 데이터는 가운데 정렬합니다.
            headStyle.setAlignment(HorizontalAlignment.CENTER);

            // 데이터용 경계 스타일 테두리만 지정

            CellStyle bodyStyle = workbook.createCellStyle();
            bodyStyle.setBorderTop(BorderStyle.THIN);
            bodyStyle.setBorderBottom(BorderStyle.THIN);
            bodyStyle.setBorderLeft(BorderStyle.THIN);
            bodyStyle.setBorderRight(BorderStyle.THIN);

	
            
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
