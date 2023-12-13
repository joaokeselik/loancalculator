package com.keselik.loancalculator.model;

public class HousingLoan implements LoanType {

    private static final double INTEREST_RATE = 3.5;

    @Override
    public double getInterestRate() {
        return INTEREST_RATE;
    }

}
