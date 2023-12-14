package com.keselik.loancalculator;

import com.keselik.loancalculator.model.LoanType;

public class MockLoan implements LoanType {
    private double interestRate;

    public MockLoan(double interestRate) {
        this.interestRate = interestRate;
    }

    @Override
    public double getInterestRate() {
        return interestRate;
    }
}