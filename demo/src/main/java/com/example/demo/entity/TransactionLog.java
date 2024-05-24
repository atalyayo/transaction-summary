package com.example.demo.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "transaction_log")
public class TransactionLog {

	@Id
	private String id;
	
	@Column(name="internal_reference", nullable=false)
	private String internalReference;
	
	@Column(name="external_reference", nullable=false)
	private String externalReference;
	
	@Column(name="account_from", nullable=false)
	private String accountFrom;
	
	@Column(name="account_to", nullable=false)
	private String accountTo;
	
	@Column(name="amount", nullable=false)
	private BigDecimal amount;
	
	@Column(name="remarks", nullable=false)
	private String remarks;
	
	@Column(name="success")
	private Boolean success;
	
	@Column(name="created_date", nullable=false)
	private LocalDate createdDate;
	
	@Column(name="created_by", nullable=false)
	private String createdBy;
	
	@Column(name="modified_date", nullable=false)
	private LocalDateTime modifiedDate;
	
	@Column(name="modified_by", nullable=false)
	private String modifiedBy;
	
}
