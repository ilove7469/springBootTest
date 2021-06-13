package com.kosta.sbproject.service;

import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kosta.sbproject.model.Tw_test;
import com.kosta.sbproject.persistence.TwTestRepository;

@Service
public class TwTestService {

	@Autowired
	TwTestRepository twRepo;
	
	public List<Tw_test> findAll() {
		return (List<Tw_test>)twRepo.findAll();
	}
	
	
	 public HSSFWorkbook listExcelDownload(Tw_test param) throws Exception {
	        
	        
	        HSSFWorkbook workbook = new HSSFWorkbook();
	        
	        HSSFSheet sheet = workbook.createSheet("엑셀시트명");
	        
	        HSSFRow row = null;
	        
	        HSSFCell cell = null;
	        
	        //param.setPager(false);
	        //param.setNullText(NULL_TEXT);
	       // param.setSeparator(DELI_EXCEL);
	        List<Tw_test> list = (List<Tw_test>) twRepo.findAll();
	        
	        row = sheet.createRow(0);
	        String[] headerKey = {"칼럼1", "칼럼2"};
	        
	        for(int i=0; i<headerKey.length; i++) {
	            cell = row.createCell(i);
	            cell.setCellValue(headerKey[i]);
	        }
	        
	        for(int i=0; i<list.size(); i++) {
	            row = sheet.createRow(i + 1);
	            Tw_test vo = list.get(i);
	            
	            cell = row.createCell(0);
	            cell.setCellValue(vo.getName());
	            
	            cell = row.createCell(1);
	            cell.setCellValue(vo.getNum());

	        }
	        
	        return workbook;
	    }
	 
}
