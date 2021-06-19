package com.kosta.sbproject.model;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString(exclude = "replies")//무한루프 방지
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name="tbl_webboards")
public class WebBoard {
	
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
	
	
	@JsonIgnore //무한루프 방지 338페이지 
	//맵드바이 자식이 있으면 못지운다 메여있다 그런의미.. 메여있으니 못지움.....
	@OneToMany(mappedBy = "board",
			cascade = CascadeType.ALL,
			fetch = FetchType.LAZY
			)//fetch = FetchType.EAGER
	List<WebBoardReply> replies;
	
	
	
}


//mappedBy는 메여있다.. WebBoardReply의 board속성이 참조하고있다..
//참조하고있어서 자유롭지 못하다.... 자식이 있으면 부모는 지울 수 없다. 

//양방향일때는 조인칼럼이 아니라 맵드바이를 써야한다.. 

//cascade = CascadeType.ALL 하나를 통해서 다른테이블에 다수를 넣어야할때 이걸 써야한다. 1이 건드려지면 n도 건드리고 싶다....

//fetch = FetchType.EAGER  eager로 해놓으면 너무 많은 일을 해야하는데 원할때만하는것 트랜잭셔널.....
//fetch좀 헷갈림..
//fetch = FetchType.EAGER : select시에 연관관계 table도 select한다.
//방법1.eager  :  부모가 셀렉트할때 다른것도 다 셀렉트
//방법2.lazy ->@transctinal, @commit   :  transctinal로 요청한것만... 인서트같은거 반영도해야한다면 commit

//QWebBoard.java, QWebBoardReply.java  동적 쿼리를 하기위해 필요
//pom.xml에 플러그인이 자동으로 해줌....
