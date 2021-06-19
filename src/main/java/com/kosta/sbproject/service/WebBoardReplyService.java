package com.kosta.sbproject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kosta.sbproject.model.WebBoard;
import com.kosta.sbproject.model.WebBoardReply;
import com.kosta.sbproject.persistence.WebBoardReplyRepository;

@Service
public class WebBoardReplyService{

	@Autowired
	WebBoardReplyRepository repo;
	
	public List<WebBoardReply> selectAll(WebBoard board) {
		return (List<WebBoardReply>)repo.findByBoard(board);
	}
	
	public WebBoardReply selectById(Long rno) {
		return repo.findById(rno).get();
	}
	
	
	//수정과 입력 동시
	public WebBoardReply updateOrInsert(WebBoardReply reply) {
		return repo.save(reply);
	}
	
	
	public int deleteReply(Long rno) {
		int ret = 0;
		try {
			repo.deleteById(rno);
			ret = 1;
		} catch (Exception ex) {
		}
		return ret;
	}
	
}
