package com.example.demo.dto.response;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MetadataResponse {

	private LocalDateTime requestTime;
	private long currentPage;
	private long dataPage;
	private long totalPage;
	private long totalData;
	
}
