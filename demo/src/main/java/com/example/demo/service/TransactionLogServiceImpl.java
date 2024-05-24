package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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
	public BaseResponse<List<TransactionLogResponse>> getAll(Pageable pageable) {
		Page<TransactionLog> resultList = transactionLogRepo.findAll(pageable);

		MetadataResponse metadata = new MetadataResponse();
		metadata.setRequestTime(LocalDateTime.now());
		metadata.setCurrentPage(resultList.getPageable().getPageNumber());
		metadata.setDataPage(resultList.getPageable().getPageSize());
		metadata.setTotalPage(resultList.getTotalPages());
		metadata.setTotalData(resultList.getTotalElements());

		return new BaseResponse<List<TransactionLogResponse>>(
				resultList.getContent().stream().map(this::mapToResponse).toList(), metadata);
	}

	private TransactionLogResponse mapToResponse(TransactionLog transactionLog) {
		TransactionLogResponse response = new TransactionLogResponse();
		BeanUtils.copyProperties(transactionLog, response);
		response.setTransactionTime(transactionLog.getModifiedDate());
		response.setReference("external_reference");

		return response;
	}

	@Override
	public BaseResponse<SummaryResponse> getSummaryByAccountNumber(String accNumber) {
		return new BaseResponse<SummaryResponse>(
				transactionLogRepo.summaryByAccountNumber(accNumber));
	}

}
