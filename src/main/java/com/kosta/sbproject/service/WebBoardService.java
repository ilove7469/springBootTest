package com.kosta.sbproject.service;



import java.util.List;

import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.kosta.sbproject.model.PageVO;
import com.kosta.sbproject.model.WebBoard;
import com.kosta.sbproject.persistence.WebBoardRepository;
import com.querydsl.core.types.Predicate;

@Service
public class WebBoardService {

	
	@Autowired
	WebBoardRepository repo;  //repo로 인서트
	
	
	  //동적조회 페이지 vo사용
	public Page<WebBoard> seletAll(PageVO pvo) {  //conditionRetrieve11복사
		//null테스트는 괄호안에 null, null 넣으면된다.
		Predicate p = repo.makePredicate1(pvo.getType(),pvo.getKeyword()); 
	
		//makePaging(방향, sort할field)
		Pageable pageable = pvo.makePaging(0, "bno");
		
		Page<WebBoard> result = repo.findAll(p, pageable);
		return result;
		 
	}
	
	public WebBoard selectById(Long bno) {
		return repo.findById(bno).get();
	}
	
	
	public WebBoard insertBoard(WebBoard board) {
		return repo.save(board);
	}
	
	public WebBoard updateBoard(WebBoard board) {
		return repo.save(board);
	}
	
	public int deleteBoard(Long bno) {
		
		int ret=0;
		
		try {
		repo.deleteById(bno);
		ret=1;
		}catch(Exception ex) {
		
		}
		return ret;
	}
	
	//엑셀다운로드
	 public XSSFWorkbook listExcelDownload(WebBoard param, Model model,PageVO pvo) throws Exception {
	        
		 System.out.println("--------------------엑셀확인------------------");
		 System.out.println(pvo.getKeyword() +"----------------"+pvo.getType());
		 
		 XSSFWorkbook workbook = new XSSFWorkbook();
	        
		 XSSFSheet sheet = workbook.createSheet("엑셀시트명");
	        
		 XSSFRow row = null;
	        
	        XSSFCell cell = null;
	        
	        
	        
	        
	        //param.setPager(false);
	        //param.setNullText(NULL_TEXT);
	       // param.setSeparator(DELI_EXCEL);
	        Predicate p = repo.makePredicate1(pvo.getType(),pvo.getKeyword()); 
			System.out.println("동적조회 p :"+p);
	        
	        List<WebBoard> list = (List<WebBoard>) repo.findAll(p);
	        
	        System.out.println(list);
	        
	        row = sheet.createRow(0);
	        String[] headerKey = {"게시판번호", "제목", "작성자", "등록일"};
	        
	        for(int i=0; i<headerKey.length; i++) {
	            cell = row.createCell(i);
	            cell.setCellValue(headerKey[i]);
	        }
	        
	        for(int i=0; i<list.size(); i++) {
	            row = sheet.createRow(i + 1);
	            WebBoard vo = list.get(i);
	           
	            cell = row.createCell(0);
	            cell.setCellValue(vo.getBno());
	            
	            cell = row.createCell(1);
	            cell.setCellValue(vo.getTitle());
	            
	            cell = row.createCell(2);
	            cell.setCellValue(vo.getWriter());
	            
	            cell = row.createCell(3);
	            cell.setCellValue(vo.getRegdate().toString());
	         

	        }
	        
	        return workbook;
	    }
	 //엑셀다운로드
	 
}
