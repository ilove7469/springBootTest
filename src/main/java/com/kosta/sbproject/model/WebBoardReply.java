package com.kosta.sbproject.model;

import java.sql.Timestamp;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString(exclude = "board")//무한루프 방지
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of="rno")
@Table(name="tbl_web_replies")
public class WebBoardReply {
	@Id //필수 PK
	@GeneratedValue(strategy = GenerationType.AUTO) //시퀀스
	Long rno;
	
	String reply;
	String replyer;
	
	@CreationTimestamp
	Timestamp regdate;  //작성날짜
	
	@UpdateTimestamp
	Timestamp updatedate;    //수정날짜
	
	//여러개의 댓글은 하나의 게시글을 참조한다. 
	@ManyToOne //댓글이 여러개 있는데 다 하나의 게시글에 포함되어있다
	WebBoard board; //fk   tbl_web_replies 테이블에 board_bno 칼럼추가 ...   bno는 보드에 키값...
}
