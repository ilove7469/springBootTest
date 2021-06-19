package com.kosta.sbproject.persistence;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.kosta.sbproject.model.WebBoard;
import com.kosta.sbproject.model.WebBoardReply;

public interface WebBoardReplyRepository 
				extends CrudRepository<WebBoardReply, Long>{

	public List<WebBoardReply> findByBoard(WebBoard board);
	
}
