package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.response.BaseResponse;
import com.example.demo.dto.response.SummaryResponse;
import com.example.demo.dto.response.TransactionLogResponse;
import com.example.demo.entity.TransactionLog;

public interface TransactionLogService {
	
	BaseResponse<List<TransactionLogResponse>> getAll(Integer page, Integer limit);
	
	BaseResponse<SummaryResponse> getSummaryByAccountNumber(String accNumber);
	
	boolean validateAccountNumberIsExist(String accNumber);
	
	List<TransactionLog> findAll();

}
