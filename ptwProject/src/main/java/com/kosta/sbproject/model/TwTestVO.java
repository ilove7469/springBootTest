package com.kosta.sbproject.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity   //jpa관리함을 의미
@Table(name = "tw_test")

public class TwTestVO {
	
	@Id
	int num;
	String name;

}
