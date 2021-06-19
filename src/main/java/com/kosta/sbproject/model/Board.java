package com.kosta.sbproject.model;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
@Entity
@Table(name="tbl_boards")
public class Board {
	@Id //필수 PK
	@GeneratedValue(strategy = GenerationType.AUTO)
	Long bno;
	
	String title;
	String writer;
	String content;
	
	@CreationTimestamp
	Timestamp regdate;
	
	@UpdateTimestamp
	Timestamp updatedate;
}

//변수이름에 대문자가 나오면 테이블에 칼럼이름에 _(under score)가 들어간다...
//repository에서 변수이름 사용시 주의(findBy대문자 시작 그리고 변수이름그대로) : findByRegdate