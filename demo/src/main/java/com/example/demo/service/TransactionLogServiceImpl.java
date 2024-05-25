package com.example.demo.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.example.demo.common.helper.PagingHelper;
import com.example.demo.dto.response.BaseResponse;
import com.example.demo.dto.response.MetadataResponse;
import com.example.demo.dto.response.SummaryResponse;
import com.example.demo.dto.response.TransactionLogResponse;
import com.example.demo.entity.TransactionLog;
import com.example.demo.repository.TransactionLogRepository;

@Service
public class TransactionLogServiceImpl implements TransactionLogService {

	@Autowired
	private TransactionLogRepository transactionLogRepo;

	@Override
	public BaseResponse<List<TransactionLogResponse>> getAll(Integer page, Integer limit) {
		PageRequest pageable = PagingHelper.pageRequest(page, limit);
		Page<TransactionLog> results = transactionLogRepo.findAllBySuccessTrue(pageable).orElseThrow();
		List<TransactionLogResponse> responses = results.getContent().stream().map(this::mapToResponse).toList();
		
		return new BaseResponse<List<TransactionLogResponse>>(responses, new MetadataResponse(results));
	}

	private TransactionLogResponse mapToResponse(TransactionLog transactionLog) {
		TransactionLogResponse response = new TransactionLogResponse();
		BeanUtils.copyProperties(transactionLog, response);
		response.setTransactionTime(transactionLog.getModifiedDate());
		response.setReference(transactionLog.getExternalReference());

		return response;
	}

	@Override
	public BaseResponse<SummaryResponse> getSummaryByAccountNumber(String accNumber) {
		if(validateAccountNumberIsExist(accNumber)) {
			return new BaseResponse<SummaryResponse>(transactionLogRepo.summaryByAccountNumber(accNumber));
		} else {
			return new BaseResponse<SummaryResponse>(new Exception("Account Number Not Found"));
		}
		
	}

	@Override
	public boolean validateAccountNumberIsExist(String accNumber) {
		return transactionLogRepo.existsByAccountFromOrAccountTo(accNumber, accNumber);
	}
	
}
