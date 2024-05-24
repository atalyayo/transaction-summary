package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.dto.response.SummaryResponse;
import com.example.demo.entity.TransactionLog;

@Repository
public interface TransactionLogRepository
		extends JpaRepository<TransactionLog, Long>, JpaSpecificationExecutor<TransactionLog> {

	@Query("SELECT new com.example.demo.dto.response.SummaryResponse(tl.accountFrom, SUM(tl.amount)) "
			  + "FROM TransactionLog tl WHERE tl.accountFrom = :accNumber AND tl.success GROUP BY tl.accountFrom")
	SummaryResponse summaryByAccountNumber(String accNumber);
}
