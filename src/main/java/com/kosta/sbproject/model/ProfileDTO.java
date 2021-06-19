package com.kosta.sbproject.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@ToString
@EqualsAndHashCode(of="fno")
@Table(name="tbl_profile")
public class ProfileDTO {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)  //시퀀스
	Long fno;
	String fname;
	boolean currenYn;
	
	@ManyToOne  //FK
	MemberDTO member; //member_mid
	
}
