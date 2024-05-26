package com.example.demo.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.response.BaseResponse;
import com.example.demo.dto.response.SummaryResponse;
import com.example.demo.dto.response.TransactionLogResponse;
import com.example.demo.service.TransactionLogService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/api")
public class TransactionController {

	private final TransactionLogService transactionLogService;

	@GetMapping(value = "/transaction")
	public ResponseEntity<BaseResponse<List<TransactionLogResponse>>> getTransactions(Integer page, Integer limit) {
		return ResponseEntity.ok(transactionLogService.getAll(page, limit));
	}

	@GetMapping(value = "/summary/{accNumber}")
	public ResponseEntity<BaseResponse<SummaryResponse>> getTotalTransactionByAccNumber(
			@PathVariable String accNumber) {
		return ResponseEntity.ok(transactionLogService.getSummaryByAccountNumber(accNumber));
	}

}
