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
@Table
public class DeptVO {

	@Id
	int department_id;
	String department_name;
	int manager_id;
	int location_id;
	
}
