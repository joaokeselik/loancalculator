package com.keselik.loancalculator.model;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class CarLoan implements LoanType {

    private static final BigDecimal INTEREST_RATE = BigDecimal.valueOf(4.5);

    @Override
    public BigDecimal getInterestRate() {
        return INTEREST_RATE;
    }

}
