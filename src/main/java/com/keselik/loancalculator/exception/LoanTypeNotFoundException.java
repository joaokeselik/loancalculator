package com.keselik.loancalculator.exception;

import com.keselik.loancalculator.model.LoanType;

public class LoanTypeNotFoundException extends RuntimeException {

    private final LoanType loanType;

    public LoanTypeNotFoundException(LoanType loanType, String message) {
        super(message);
        this.loanType = loanType;
    }

    public LoanType getLoanType() {
        return loanType;
    }
}
