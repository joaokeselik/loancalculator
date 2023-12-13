package com.keselik.loancalculator.model;

import java.math.BigDecimal;

public class LoanPayment {
    private int month;
    private BigDecimal monthlyPayment;
    private BigDecimal principal;
    private BigDecimal interest;
    private BigDecimal remainingAmount;

    public LoanPayment(int month, BigDecimal monthlyPayment, BigDecimal principal, BigDecimal interest, BigDecimal remainingAmount) {
        this.month = month;
        this.monthlyPayment = monthlyPayment;
        this.principal = principal;
        this.interest = interest;
        this.remainingAmount = remainingAmount;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public BigDecimal getMonthlyPayment() {
        return monthlyPayment;
    }

    public void setMonthlyPayment(BigDecimal monthlyPayment) {
        this.monthlyPayment = monthlyPayment;
    }

    public BigDecimal getPrincipal() {
        return principal;
    }

    public void setPrincipal(BigDecimal principal) {
        this.principal = principal;
    }

    public BigDecimal getInterest() {
        return interest;
    }

    public void setInterest(BigDecimal interest) {
        this.interest = interest;
    }

    public BigDecimal getRemainingAmount() {
        return remainingAmount;
    }

    public void setRemainingAmount(BigDecimal remainingAmount) {
        this.remainingAmount = remainingAmount;
    }
}
