package com.keselik.loancalculator.exception;

public class LoanTypeNotFoundException extends RuntimeException {

    private final String loanType;

    public LoanTypeNotFoundException(String loanType, String message) {
        super(message);
        this.loanType = loanType;
    }

    public String getLoanType() {
        return loanType;
    }
}
