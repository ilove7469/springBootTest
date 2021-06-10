package com.kosta.sbproject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kosta.sbproject.model.TwTestVO;
import com.kosta.sbproject.persistence.TwRepository;


@Service
public class TwTestService {
	
	@Autowired
	TwRepository twrepo;
	
	public List<TwTestVO> selectAll(){
		return (List<TwTestVO>)twrepo.findAll();
	}

}
