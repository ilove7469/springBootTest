package com.kosta.sbproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kosta.sbproject.model.WebBoard;
import com.kosta.sbproject.model.WebBoardReply;
import com.kosta.sbproject.service.WebBoardReplyService;

@RestController
@RequestMapping("/replies/*")  
public class WebBoardReplyController {
	
	@Autowired
	WebBoardReplyService service;
	
	//특정board의 모든 댓글조회
	@GetMapping("/board/{bno}")
	public ResponseEntity<List<WebBoardReply>> selectAll(@PathVariable Long bno) {
		WebBoard board = WebBoard.builder().bno(bno).build();
		return new ResponseEntity<>(service.selectAll(board), HttpStatus.OK);
	}
	
	//특정 댓글 조회
	@GetMapping("/{rno}")
	public ResponseEntity<WebBoardReply> viewReply(@PathVariable Long rno) {
		return new ResponseEntity<>(service.selectById(rno), HttpStatus.OK);
	}
	
	//특정board의 댓글을 입력하고 재조회
	@PostMapping("/{bno}")
	public ResponseEntity<List<WebBoardReply>> addReply(@PathVariable Long bno,
			@RequestBody WebBoardReply reply) { 
		
		WebBoard board = WebBoard.builder().bno(bno).build();
		reply.setBoard(board);
		service.updateOrInsert(reply);
		
		return new ResponseEntity<>(service.selectAll(board), HttpStatus.CREATED);
	}
	
	
	//수정
	@PutMapping("/{bno}")
	public ResponseEntity<List<WebBoardReply>> updateReply(@PathVariable Long bno,
			@RequestBody WebBoardReply reply) { 
		
		WebBoard board = WebBoard.builder().bno(bno).build();
		reply.setBoard(board);
		service.updateOrInsert(reply);
		
		return new ResponseEntity<>(service.selectAll(board), HttpStatus.OK);
	}
	
	
	@DeleteMapping("/{bno}/{rno}")
	public ResponseEntity<List<WebBoardReply>> deleteByRno(@PathVariable Long rno,
			@PathVariable Long bno) { 

		service.deleteReply(rno);
		
		WebBoard board = WebBoard.builder().bno(bno).build();
		return new ResponseEntity<>(service.selectAll(board), HttpStatus.OK);
	}
	
	
}
