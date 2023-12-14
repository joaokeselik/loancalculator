package com.keselik.loancalculator.model;

import org.springframework.stereotype.Component;

@Component
public class CarLoan implements LoanType {

    private static final double INTEREST_RATE = 4.5;

    @Override
    public double getInterestRate() {
        return INTEREST_RATE;
    }

}
