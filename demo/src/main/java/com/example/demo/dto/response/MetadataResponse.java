package com.example.demo.dto.response;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;

import com.example.demo.common.helper.FormatHelper;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MetadataResponse {

	private String requestTime;
	private long currentPage;
	private long dataPage;
	private long totalPage;
	private long totalData;
	
	public <T> MetadataResponse(Page<T> data) {
		super();
		this.requestTime = FormatHelper.dateTimeFormat(LocalDateTime.now());
		this.currentPage = data.getPageable().getPageNumber() + 1L;
		this.dataPage = data.getPageable().getPageSize();
		this.totalPage = data.getTotalPages();
		this.totalData = data.getTotalElements();
	}
	
	
}
