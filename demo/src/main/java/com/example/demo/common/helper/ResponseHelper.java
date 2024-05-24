package com.example.demo.common.helper;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;

public class ResponseHelper extends ResponseEntity {

	public ResponseHelper(MultiValueMap headers, HttpStatusCode status) {
		super(headers, status);
		// TODO Auto-generated constructor stub
	}



}
