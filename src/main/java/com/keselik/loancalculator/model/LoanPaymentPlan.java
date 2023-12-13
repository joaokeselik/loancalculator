package com.keselik.loancalculator.model;


import java.util.ArrayList;
import java.util.List;

public class LoanPaymentPlan {
    private List<LoanPayment> payments;

    public LoanPaymentPlan() {
        this.payments = new ArrayList<>();
    }

    public void setPayments(List<LoanPayment> payments) {
        this.payments = payments;
    }

    public void addPayment(LoanPayment payment) {
        payments.add(payment);
    }

    public List<LoanPayment> getPayments() {
        return payments;
    }
}
