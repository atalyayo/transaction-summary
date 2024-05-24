package com.example.demo.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.example.demo.dto.response.BaseResponse;
import com.example.demo.dto.response.SummaryResponse;
import com.example.demo.dto.response.TransactionLogResponse;

public interface TransactionLogService {
	
	BaseResponse<List<TransactionLogResponse>> getAll(Pageable pageable);
	
	BaseResponse<SummaryResponse> getSummaryByAccountNumber(String accNumber);

}
