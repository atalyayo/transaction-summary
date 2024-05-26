package com.example.demo.dto.response;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TransactionLogResponse {

	private String transactionTime;
	private String accountFrom;
	private String accountTo;
	private BigDecimal amount;
	private String remarks;
	private String reference;
	
}
