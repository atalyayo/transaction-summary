package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.response.BaseResponse;
import com.example.demo.dto.response.SummaryResponse;
import com.example.demo.dto.response.TransactionLogResponse;
import com.example.demo.service.TransactionLogService;

@RestController
@RequestMapping("/api")
public class TransactionController {

	@Autowired
	private TransactionLogService transactionLogService;

	@GetMapping(value = "/transaction")
	public ResponseEntity<BaseResponse<List<TransactionLogResponse>>> getTransactions(@RequestParam int page) {
		Pageable paging = PageRequest.of(page, 20);
		return ResponseEntity.ok(transactionLogService.getAll(paging));
	}

	@GetMapping(value = "/summary/{accNumber}")
	public ResponseEntity<BaseResponse<SummaryResponse>> getTotalTransactionByAccNumber(@PathVariable String accNumber) {
		return ResponseEntity.ok(transactionLogService.getSummaryByAccountNumber(accNumber));
	}

}
