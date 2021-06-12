package com.kosta.sbproject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.kosta.sbproject.model.TwTestVO;
import com.kosta.sbproject.persistence.TwRepository;
import com.querydsl.core.types.Predicate;


@Service
public class TwTestService {
	
	@Autowired
	TwRepository twrepo;
	
	public List<TwTestVO> selectAll(){
		return (List<TwTestVO>)twrepo.findAll();
	}
	
	public Page<TwTestVO> selectAll(PageVO pvo) {  //conditionRetrieve11복사
		//null테스트는 괄호안에 null, null 넣으면된다.
		Predicate p = twrepo.makePredicate(pvo.getType(),pvo.getKeyword()); 
	
		//makePaging(방향, sort할field)
		Pageable pageable = pvo.makePaging(0, "companyNo");
		
		Page<TwTestVO> result = twrepo.findAll(p, pageable);
		return result;
		 
	}

}
