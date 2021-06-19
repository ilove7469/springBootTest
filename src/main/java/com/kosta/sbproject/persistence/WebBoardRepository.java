package com.kosta.sbproject.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;

import com.kosta.sbproject.model.QWebBoard;
import com.kosta.sbproject.model.WebBoard;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;


//인터페이스 : 상수 + 추상메서드들
//static method, default method(재정의가능)
public interface WebBoardRepository 
//어떤 보드?   키가 뭔데???
extends CrudRepository<WebBoard, Long>,
QuerydslPredicateExecutor<WebBoard>{
	//QuerydslPredicateExecutor 조건 조회 가능...

	//jpql 문법
	@Query("select b.title, count(r)"
			+ " from WebBoard b left outer join b.replies r"
			+ " group by b.title order by b.title")
				
	//모든 보드 몇건인지?..
	public List<Object[]> getBoardReplyCount();
	//2개 출력 dto없으니 오브젝트 배열로 받음
	
	
	//Predicate,BooleanBuilder 쿼리dsl
	public default Predicate makePredicate() {
		BooleanBuilder builder = new BooleanBuilder(); //맞냐 틀리냐...?
		QWebBoard board = QWebBoard.webBoard;
		builder.and(board.bno.gt(0)); //and bno>0
		return builder;
	}
	
	
	//Predicate,BooleanBuilder 쿼리dsl
	public default Predicate makePredicate1(String type, String keyword) {
		//빌더 동적 쿼리 기본셋팅 예를 들어 셀렉트가 빌더
		BooleanBuilder builder = new BooleanBuilder(); //맞냐 틀리냐...?
		//QWebBoard board 가 from board
		QWebBoard board = QWebBoard.webBoard;
		//where 절들을 뒤쪽에 추가하는 구문
		builder.and(board.bno.gt(0)); //and bno>0
		if(type==null) return builder;
		switch (type) {
		case "title":
			builder.and(board.title.like("%" + keyword + "%"));  //and title like '%?%'
			break;	
		case "content":
			builder.and(board.content.like("%" + keyword + "%"));  //and title like '%?%'
			break;
		case "writer":
			builder.and(board.writer.like("%" + keyword + "%"));  //and title like '%?%'
			break;

		default:
			break;
		}
		return builder;
	}
	
	
}
