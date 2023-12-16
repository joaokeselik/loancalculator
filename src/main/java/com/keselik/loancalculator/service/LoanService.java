package com.keselik.loancalculator.service;

import com.keselik.loancalculator.model.LoanPayment;
import com.keselik.loancalculator.model.LoanPaymentPlan;
import com.keselik.loancalculator.model.LoanType;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

@Service
public class LoanService {

    public LoanPaymentPlan calculateLoan(BigDecimal loanAmount, int paybackYears, LoanType loanType) {
        validateInputParameters(loanAmount, paybackYears);

        BigDecimal interestRate = loanType.getInterestRate();
        int months = paybackYears * 12;
        BigDecimal monthlyInterestRate = interestRate.divide(BigDecimal.valueOf(100 * 12), MathContext.DECIMAL128);
        BigDecimal monthlyPayment = calculateMonthlyPayment(loanAmount, monthlyInterestRate, months);

        LoanPaymentPlan paymentPlan = new LoanPaymentPlan();
        BigDecimal remainingAmount = loanAmount;

        for (int month = 1; month <= months; month++) {
            BigDecimal interest = calculateInterest(remainingAmount, monthlyInterestRate);
            BigDecimal principal = calculatePrincipal(monthlyPayment, interest);
            remainingAmount = calculateRemainingAmount(remainingAmount, principal);

            if (month == months && (remainingAmount.compareTo(BigDecimal.ZERO) != 0)) {
                remainingAmount = BigDecimal.ZERO;
            }

            LoanPayment payment = new LoanPayment(month, monthlyPayment, principal, interest, remainingAmount);
            paymentPlan.addPayment(payment);
        }
        return paymentPlan;
    }

    private BigDecimal calculateInterest(BigDecimal remainingAmount, BigDecimal monthlyInterestRate) {
        return remainingAmount.multiply(monthlyInterestRate);
    }

    private BigDecimal calculatePrincipal(BigDecimal monthlyPayment, BigDecimal interest) {
        return monthlyPayment.subtract(interest);
    }

    private BigDecimal calculateRemainingAmount(BigDecimal remainingAmount, BigDecimal principal) {
        return remainingAmount.subtract(principal);
    }

    private void validateInputParameters(BigDecimal loanAmount, int paybackYears) {
        if (loanAmount.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Loan amount must be non-negative");
        }

        if (paybackYears <= 0) {
            throw new IllegalArgumentException("Payback years must be greater than zero");
        }
    }

    private BigDecimal calculateMonthlyPayment(BigDecimal loanAmount, BigDecimal monthlyInterestRate, int months) {
        if (monthlyInterestRate.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Interest rate must be non-negative");
        }

        if (monthlyInterestRate.compareTo(BigDecimal.ZERO) == 0) {
            return loanAmount.divide(BigDecimal.valueOf(months), 1000, RoundingMode.HALF_EVEN);
        }

        BigDecimal compoundFactor = BigDecimal.ONE.add(monthlyInterestRate).pow(months);
        BigDecimal monthlyPayment = loanAmount.multiply(monthlyInterestRate).multiply(compoundFactor)
                .divide(compoundFactor.subtract(BigDecimal.ONE), 1000, RoundingMode.HALF_EVEN);

        return monthlyPayment;
    }

}
