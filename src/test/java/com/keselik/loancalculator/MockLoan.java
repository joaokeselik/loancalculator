package com.keselik.loancalculator;

import com.keselik.loancalculator.model.LoanType;

import java.math.BigDecimal;

public class MockLoan implements LoanType {
    private BigDecimal interestRate;

    public MockLoan(double interestRate) {
        this.interestRate = BigDecimal.valueOf(interestRate);
    }

    @Override
    public BigDecimal getInterestRate() {
        return interestRate;
    }
}