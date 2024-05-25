package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.dto.response.SummaryResponse;
import com.example.demo.entity.TransactionLog;

@Repository
public interface TransactionLogRepository
		extends JpaRepository<TransactionLog, Long>, JpaSpecificationExecutor<TransactionLog> {

	Optional<Page<TransactionLog>> findAllBySuccessTrue(Pageable pageable);
	
	@Query(""" 
			SELECT new com.example.demo.dto.response.SummaryResponse(:accNumber, 
				COALESCE(SUM(tl.amount) filter (WHERE tl.accountTo = :accNumber), 0)
				- COALESCE(SUM(tl.amount) filter (WHERE tl.accountFrom = :accNumber), 0)) 
			FROM TransactionLog tl 
			WHERE (tl.accountFrom = :accNumber OR tl.accountTo = :accNumber) AND tl.success """)
	SummaryResponse summaryByAccountNumber(String accNumber);
	
	boolean existsByAccountFromOrAccountTo(String accFrom, String accTo);
}
