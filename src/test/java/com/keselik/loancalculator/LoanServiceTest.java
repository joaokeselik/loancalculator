package com.keselik.loancalculator;

import com.keselik.loancalculator.model.LoanPayment;
import com.keselik.loancalculator.model.LoanPaymentPlan;
import com.keselik.loancalculator.model.LoanType;
import com.keselik.loancalculator.service.LoanService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class LoanServiceTest {

	@Mock
	private Map<String, LoanType> loanTypes;

	@InjectMocks
	private LoanService loanService;

	@Test
	void testPaymentPlanNotNull() {
		BigDecimal loanAmount = BigDecimal.valueOf(100000);
		int paybackYears = 10;
		String loanType = "home";

		LoanType mockLoanType = new MockLoan(BigDecimal.valueOf(3.5));
		when(loanTypes.get(loanType)).thenReturn(mockLoanType);

		LoanPaymentPlan paymentPlan = loanService.calculateLoan(loanAmount, paybackYears, mockLoanType);
		assertNotNull(paymentPlan);
	}

	@Test
	void testCalculateLoanWithInvalidInput() {
		BigDecimal invalidLoanAmount = BigDecimal.valueOf(-1000);
		int invalidPaybackYears = -5;
		String loanType = "car";

		LoanType mockLoanType = new MockLoan(BigDecimal.valueOf(4.5));
		when(loanTypes.get(loanType)).thenReturn(mockLoanType);

		assertThrows(IllegalArgumentException.class,
				() -> loanService.calculateLoan(invalidLoanAmount, invalidPaybackYears, mockLoanType));
	}

	@Test
	void testPaymentsSize() {
		BigDecimal loanAmount = BigDecimal.valueOf(100000);
		int paybackYears = 10;
		String loanType = "personal";

		LoanType mockLoanType = new MockLoan(BigDecimal.valueOf(6.5));
		when(loanTypes.get(loanType)).thenReturn(mockLoanType);

		LoanPaymentPlan paymentPlan = loanService.calculateLoan(loanAmount, paybackYears, mockLoanType);
		assertNotNull(paymentPlan);
		List<LoanPayment> payments = paymentPlan.getPayments();

		assertEquals(paybackYears * 12, payments.size());
	}
}