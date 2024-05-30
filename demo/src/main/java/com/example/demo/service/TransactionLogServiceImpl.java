package com.example.demo.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.common.helper.FormatHelper;
import com.example.demo.common.helper.PagingHelper;
import com.example.demo.dto.response.BaseResponse;
import com.example.demo.dto.response.MetadataResponse;
import com.example.demo.dto.response.SummaryResponse;
import com.example.demo.dto.response.TransactionLogResponse;
import com.example.demo.entity.TransactionLog;
import com.example.demo.repository.TransactionLogRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class TransactionLogServiceImpl implements TransactionLogService {

	private final TransactionLogRepository transactionLogRepo;

	@Override
	public BaseResponse<List<TransactionLogResponse>> getAll(Integer page, Integer limit) {
		PageRequest pageable = PagingHelper.pageRequest(page, limit);
		Page<TransactionLog> results = transactionLogRepo.findAllBySuccessTrue(pageable);
		List<TransactionLogResponse> responses = results.getContent().stream().map(this::mapToResponse).toList();
		
		return new BaseResponse<>(responses, new MetadataResponse(results));
	}

	private TransactionLogResponse mapToResponse(TransactionLog transactionLog) {
		TransactionLogResponse response = new TransactionLogResponse();
		BeanUtils.copyProperties(transactionLog, response);
		response.setTransactionTime(FormatHelper.dateTimeFormat(transactionLog.getModifiedDate()));
		response.setReference(transactionLog.getExternalReference());

		return response;
	}

	@Override
	public BaseResponse<SummaryResponse> getSummaryByAccountNumber(String accNumber) {
		if(validateAccountNumberIsExist(accNumber)) {
			return new BaseResponse<>(transactionLogRepo.summaryByAccountNumber(accNumber));
		} else {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Account Number Not Found");
		}
		
	}

	@Override
	public boolean validateAccountNumberIsExist(String accNumber) {
		return transactionLogRepo.existsByAccountFromOrAccountTo(accNumber, accNumber);
	}

	@Override
	public List<TransactionLog> findAll() {
		return transactionLogRepo.findAll();
	}
	
}
