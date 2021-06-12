package com.kosta.sbproject.persistence;

import org.springframework.cglib.core.Predicate;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;

import com.fasterxml.jackson.databind.util.ArrayBuilders.BooleanBuilder;
import com.kosta.sbproject.model.TwTestVO;

public interface TwRepository extends CrudRepository<TwTestVO, Integer>
,QuerydslPredicateExecutor<TwTestVO>{

	
	
	public default Predicate makePredicate1(String type, String keyword) {
		
		BooleanBuilder builder = new BooleanBuilder();
		QTwTestVO company = QTwTestVO.tw_test;
		builder.and(company.companyNo.gt(0));
		if(type==null) return builder;
		switch (type) {
		case "companyName":
			builder.and(company.companyName.like("%" + keyword + "%"));  //and title like '%?%'
			break;	
		case "companyLicense":
			builder.and(company.companyLicense.like("%" + keyword + "%"));  //and title like '%?%'
			break;
		case "companyBoss":
			builder.and(company.companyBoss.like("%" + keyword + "%"));  //and title like '%?%'
			break;

		default:
			break;
		}
		//System.out.println("----------------"builder);
		return builder;
		
	}
	
	
}
