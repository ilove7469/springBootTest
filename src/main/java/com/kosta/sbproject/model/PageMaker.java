package com.kosta.sbproject.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import lombok.Getter;
import lombok.ToString;
import lombok.extern.java.Log;

//교재 285페이지
@Getter
@ToString(exclude = "pageList")
@Log

	public class PageMaker<T> {
	
		private Page<T> result;
		private Pageable prevPage; //이전으로 이동하는데 필요한 정보를 가짐
		private Pageable nextPage;
		private Pageable currentPage;
		private int currentPageNum;  //화면에 보이는 1부터 시작하는 페이지번호
		private int totalPageNum;
		private List<Pageable> pageList; //내가 이동될때 숫자가 달라져야한다에 대한 것....
		
		public PageMaker(Page<T> result) {
			this.result = result;
			this.currentPage = result.getPageable();
			this.currentPageNum = currentPage.getPageNumber()+1;
			this.totalPageNum = result.getTotalPages();
			this.pageList = new ArrayList<Pageable>();
			calcPage();
		}
		public void calcPage() { //pagelist의 갯수를 default로 10개로 잡음
			int tempEndNum = (int)(Math.ceil(currentPageNum/10.0)*10);
			int startNum = tempEndNum - 9;   //10-9니깐 1 .. 1페이지부터 나온다는 의미.. 
			Pageable startPage = this.currentPage;
			for(int i = startNum; i<this.currentPageNum; i++) {
				startPage = startPage.previousOrFirst();
			}
			this.prevPage = startPage.getPageNumber()<=0?null:startPage.previousOrFirst();
			log.info("tempEndNum:" + tempEndNum);
			log.info("totalPageNum:" + totalPageNum);

			if(this.totalPageNum<tempEndNum) {
				tempEndNum = this.totalPageNum;
				this.nextPage = null;
			}
			
			for(int i = startNum; i<=tempEndNum; i++) {
				pageList.add(startPage);
				startPage = startPage.next();
			}
			this.nextPage = startPage.getPageNumber()+1 < totalPageNum?startPage:null;
		}
	}

