package com.keselik.loancalculator;

import com.keselik.loancalculator.model.LoanType;

import java.math.BigDecimal;

public class MockLoan implements LoanType {
    private BigDecimal interestRate;

    public MockLoan(BigDecimal interestRate) {
        this.interestRate = interestRate;
    }

    @Override
    public BigDecimal getInterestRate() {
        return interestRate;
    }
}