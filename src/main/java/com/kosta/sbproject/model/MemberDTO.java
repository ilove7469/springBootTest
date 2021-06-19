package com.kosta.sbproject.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString

@AllArgsConstructor
@Builder
@NoArgsConstructor

//비교할때 쓴다... 만약 안쓰면 주소로 비교하게된다.. 뉴하면 계속 달라진다... 
@EqualsAndHashCode(of = {"mid","mname"})   //mid,mname만 같으면 같은걸로 취급한다.
//괄호 안쓰면 모두 같아아야한다....


@Entity
@Table(name="tbl_members")//db에들어가는 테이블 명 
public class MemberDTO {
	
	@Id
	String mid;
	String mname;
	String mpassword;
	@Enumerated(EnumType.STRING)
	MemberRoleEnumType mrole;
	
}
