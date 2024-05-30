package com.example.demo.unit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.example.demo.dto.response.BaseResponse;
import com.example.demo.dto.response.TransactionLogResponse;
import com.example.demo.entity.TransactionLog;
import com.example.demo.repository.TransactionLogRepository;
import com.example.demo.service.TransactionLogServiceImpl;

@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)
class TransactionLogServiceTest {

	@Mock
	private TransactionLogRepository transactionLogRepository;
	
	@InjectMocks
	private TransactionLogServiceImpl transactionLogService;
	
	@Test
	void findAllTest() {
		TransactionLog tl1 = new TransactionLog("id1", "INREF00001", "REFEX00001", "0077777777", "2663287564935332", BigDecimal.valueOf(287000), "TOPUPOVO", true, LocalDate.now(), "Tester", LocalDateTime.now(), "Tester");
		TransactionLog tl2 = new TransactionLog("id2", "INREF00001", "REFEX00001", "0077777777", "2663287564935332", BigDecimal.valueOf(287000), "TOPUPOVO", true, LocalDate.now(), "Tester", LocalDateTime.now(), "Tester");
		
		given(transactionLogRepository.findAll()).willReturn(List.of(tl1, tl2));
		List<TransactionLog> list = transactionLogService.findAll();
	      
	    assertThat(list).isNotNull().hasSize(2);
	}

	@Test
	void getAllTest() {
		TransactionLog tl1 = new TransactionLog("id1", "INREF00001", "REFEX00001", "0077777777", "2663287564935332", BigDecimal.valueOf(287000), "TOPUPOVO", true, LocalDate.now(), "Tester", LocalDateTime.now(), "Tester");
		TransactionLog tl2 = new TransactionLog("id2", "INREF00001", "REFEX00001", "0077777777", "2663287564935332", BigDecimal.valueOf(287000), "TOPUPOVO", true, LocalDate.now(), "Tester", LocalDateTime.now(), "Tester");
		
		Pageable pageable = PageRequest.of(0, 10);
		Page<TransactionLog> tlPage = new PageImpl<>(List.of(tl1, tl2), pageable, 0);
		
		Mockito.when(transactionLogRepository.findAllBySuccessTrue(pageable)).thenReturn(tlPage);
		
		BaseResponse<List<TransactionLogResponse>> results = transactionLogService.getAll(1, 10);
		
		assertEquals("0077777777", results.getData().get(0).getAccountFrom());
		assertEquals(1, results.getMetadata().getCurrentPage());
		assertEquals(10, results.getMetadata().getDataPage());
		assertEquals(2, results.getMetadata().getTotalData());
		assertEquals(1, results.getMetadata().getTotalPage());
	}

}
