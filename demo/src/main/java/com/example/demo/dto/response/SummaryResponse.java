package com.example.demo.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.example.demo.common.helper.FormatHelper;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SummaryResponse {

	private String requestTime;
	private String accountNumber;
	private BigDecimal totalNumber;
	
	public SummaryResponse(String accountNumber, BigDecimal totalNumber) {
		this.requestTime = FormatHelper.dateTimeFormat(LocalDateTime.now());
		this.accountNumber = accountNumber;
		this.totalNumber = totalNumber;
	}
	
}
