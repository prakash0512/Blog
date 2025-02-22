package com.prakash.blog.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
//@AllArgsConstructor
@NoArgsConstructor

public class ApiResponse {

	public ApiResponse(String message2, boolean success2) {
		// TODO Auto-generated constructor stub
	}
	private String message;
	private boolean success;
}
