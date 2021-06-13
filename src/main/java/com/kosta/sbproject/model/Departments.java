package com.kosta.sbproject.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity 
@Table
public class Departments {

	@Id
	int department_id;
	String department_name;
	int manager_id;
	int location_id;
	
}
