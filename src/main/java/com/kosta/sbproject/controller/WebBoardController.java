package com.kosta.sbproject.controller;

import java.io.BufferedOutputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kosta.sbproject.model.PageMaker;
import com.kosta.sbproject.model.PageVO;
import com.kosta.sbproject.model.WebBoard;
import com.kosta.sbproject.service.WebBoardService;

@Controller
public class WebBoardController {

	@Autowired
	WebBoardService service;
	 
	@GetMapping("/webboard/boardlist")   //방법2 PageVO pagevo
	public void selectAll(Model model,PageVO pagevo , HttpServletRequest request ) {
		
		//방법1
//		Map<String, ?> flashMap =RequestContextUtils.getInputFlashMap(request);
//        PageVO pagevo2 = null;
//        if(flashMap!=null) {
//            
//            pagevo =(PageVO)flashMap.get("pagevo");
//            System.out.println("flashMap pagevo :" + pagevo );
//        }
		
		Page<WebBoard> result = service.seletAll(pagevo);
		//List<WebBoard> boardlist = result.getContent();
	
//		//잘오는지 확인
//		boardlist.forEach(b->{
//			System.out.println(b);
//		});
//		System.out.println("한페이지의 사이즈"+result.getSize());
//		System.out.println("전체 페이지"+result.getTotalPages());
//		//여기 까지 잘오는지 확인
		
		model.addAttribute("boardResult",result);//페이징이 되어있는 리스트
	
		model.addAttribute("pagevo",pagevo);
		
		model.addAttribute("result",new PageMaker<>(result)); //페이지 숫자보여주는거
	}
	
	
	
	
	@GetMapping("/webboard/register")
	public void boardRegister() {
		
	}
	
	@PostMapping("/webboard/register")
	public String boardRegisterPost(WebBoard board, RedirectAttributes rttr) {
		//System.out.println(board);
		
		WebBoard ins_board = service.insertBoard(board);
		
		//리다이렉트할때 가져가고싶고    addFlashAttribute:주소창에 보여주고싶지않다....
		rttr.addFlashAttribute("resultMessage", ins_board==null?"입력실패":"입력성공");
		
		return "redirect:/webboard/boardlist";
	}
			
			
			
			
			
	
	@GetMapping("/webboard/boarddetail")
	public void selectById(Model model, Long bno, PageVO pagevo) {
		
		model.addAttribute("board",service.selectById(bno));
		model.addAttribute("pagevo", pagevo);
		
	}
	
	
	@GetMapping("/webboard/delete")
	public String boardDelete(Long bno,  RedirectAttributes rttr) {
		int ret = service.deleteBoard(bno);
		System.out.println("삭제:" + ret);
		rttr.addFlashAttribute("resultMessage", ret==0?"삭제실패":"삭제성공");
		return "redirect:/webboard/boardlist";
	}
	
	@PostMapping("/webboard/update")
	public String boardUpdate(WebBoard board , RedirectAttributes rttr,
			Integer page, Integer size, 
			String type, String keyword){
		WebBoard update_board = service.updateBoard(board);
		System.out.println("수정board:" + update_board);
		rttr.addFlashAttribute("resultMessage", update_board==null?"수정실패":"수정성공");
		

		//방법1...주소창에 안보이기 
		/*
				PageVO pagevo = PageVO.builder()
						.page(page).size(size).type(type).keyword(keyword)
						.build();
				rttr.addFlashAttribute("pagevo", pagevo);
		*/
		
		//방법2...주소창에 보이기ㅣ
				String param = "page=" + page + "&size=" + size + "&type="+type + "&keyword=" + keyword;
				return "redirect:/webboard/boardlist?"+param;
				 
			}

	
	//엑셀다운로드
	@RequestMapping("/webboard/exceldownload")
    public void excelDownload(Model model,PageVO pagevo , HttpServletRequest request ,HttpServletResponse response ,HttpSession session, WebBoard param) throws Exception {
        
		 System.out.println("--------------------엑셀확인------------------");
		 System.out.println(pagevo.getKeyword() +"----------------"+pagevo.getType());
		 
        OutputStream out = null;
        
        try {
        	
        	XSSFWorkbook workbook = service.listExcelDownload(param, model, pagevo);
            
            // 테이블 헤더용 스타일
            CellStyle headStyle = workbook.createCellStyle();

            // 가는 경계선을 가집니다.
            headStyle.setBorderTop(BorderStyle.THIN);
            headStyle.setBorderBottom(BorderStyle.THIN);
            headStyle.setBorderLeft(BorderStyle.THIN);
            headStyle.setBorderRight(BorderStyle.THIN);

            // 배경색은 노란색입니다.
            headStyle.setFillForegroundColor(HSSFColor.LIGHT_YELLOW.index);
           // headStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);

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
           //logger.error("exception during downloading excel file : {}", e);
        } finally {
            if(out != null) out.close();
        }    
    }
	//엑셀다운로드 여기까지

	
	
}
