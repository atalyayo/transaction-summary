package com.example.demo.dto.response;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;

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
	
	public <T> MetadataResponse(Page<T> data) {
		super();
		this.requestTime = LocalDateTime.now();
		this.currentPage = data.getPageable().getPageNumber() + 1;
		this.dataPage = data.getPageable().getPageSize();
		this.totalPage = data.getTotalPages();
		this.totalData = data.getTotalElements();
	}
	
	
}
