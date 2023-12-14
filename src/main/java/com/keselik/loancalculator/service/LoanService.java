package com.keselik.loancalculator.service;

import com.keselik.loancalculator.exception.LoanTypeNotFoundException;
import com.keselik.loancalculator.model.LoanPayment;
import com.keselik.loancalculator.model.LoanPaymentPlan;
import com.keselik.loancalculator.model.LoanType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;

@Service
public class LoanService {
    private final Map<String, LoanType> loanTypes;

    @Autowired
    public LoanService(Map<String, LoanType> loanTypes) {
        this.loanTypes = loanTypes;
    }

    public LoanPaymentPlan calculateLoan(BigDecimal loanAmount, int paybackYears, String loanType) {
        validateInputParameters(loanAmount, paybackYears);

        /*
        System.out.println("Content of the Map:");
        for (Map.Entry<String, LoanType> entry : loanTypes.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
        */

        LoanType selectedLoanType = loanTypes.get(loanType);


        double interestRate = selectedLoanType.getInterestRate();

        int months = paybackYears * 12;
        BigDecimal monthlyInterestRate = BigDecimal.valueOf(interestRate/100/12);
        BigDecimal monthlyPayment = calculateMonthlyPayment(loanAmount, monthlyInterestRate, months);

        LoanPaymentPlan paymentPlan = new LoanPaymentPlan();
        BigDecimal remainingAmount = loanAmount;

        for (int month = 1; month <= months; month++) {



            BigDecimal interest = remainingAmount.multiply(monthlyInterestRate);
            BigDecimal principal = monthlyPayment.subtract(interest);


            remainingAmount = remainingAmount.subtract(principal);

            //TODO: the remainingAmount becomes negative with
            // Loan Amount:
            //1000000
            //
            //Payback Years:
            //20

            LoanPayment payment = new LoanPayment(month, monthlyPayment, principal, interest, remainingAmount);
            paymentPlan.addPayment(payment);
        }

        return paymentPlan;
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
            return loanAmount.divide(BigDecimal.valueOf(months), 2, RoundingMode.HALF_UP);
        }

        BigDecimal temp = BigDecimal.ONE.add(monthlyInterestRate).pow(months);
        BigDecimal monthlyPayment = loanAmount.multiply(monthlyInterestRate).multiply(temp)
                .divide(temp.subtract(BigDecimal.ONE), 2, RoundingMode.HALF_UP);

        return monthlyPayment;
    }

}
