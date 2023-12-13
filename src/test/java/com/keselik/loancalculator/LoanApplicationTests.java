package com.keselik.loancalculator;

import com.keselik.loancalculator.model.HousingLoan;
import com.keselik.loancalculator.model.LoanPaymentPlan;
import com.keselik.loancalculator.model.LoanType;
import com.keselik.loancalculator.service.LoanService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class LoanServiceTest {

	@Autowired
	private LoanService loanService;

	@Test
	void calculateLoan() {
		BigDecimal loanAmount = BigDecimal.valueOf(100000);
		int paybackYears = 10;
		LoanType loanType = new HousingLoan();

		LoanPaymentPlan paymentPlan = loanService.calculateLoan(loanAmount, paybackYears, loanType);

		assertNotNull(paymentPlan);
		// Add more assertions based on your requirements
	}

	//TODO: add tests
}
