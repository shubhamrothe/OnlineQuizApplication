package com.quizapp.payloads;

import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
public class RoleDto {

	 private Long id;
	 
	 @NotEmpty
	 private String name;
	    
	    
}
