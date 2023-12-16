package com.keselik.loancalculator.controller;

import com.keselik.loancalculator.model.LoanPaymentPlan;
import com.keselik.loancalculator.model.LoanType;
import com.keselik.loancalculator.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import java.math.BigDecimal;
import java.util.Map;

import com.keselik.loancalculator.exception.LoanTypeNotFoundException;

@Controller
@SessionAttributes("loanType")
public class LoanController {

    private final LoanService loanService;
    private final Map<String, LoanType> loanTypes;

    @Autowired
    public LoanController(LoanService loanService, Map<String, LoanType> loanTypes) {
        this.loanService = loanService;
        this.loanTypes = loanTypes;
    }

    @RequestMapping(value = "/loan/{loanType}", method = RequestMethod.GET)
    public String showLoanForm(@PathVariable String loanType, Model model) {
        if (loanTypes.get(loanType) == null) {
            throw new LoanTypeNotFoundException(loanType, "Loan type not found: " + loanType);
        }
        model.addAttribute("loanType", loanType);

        return loanType + "-loan-form";
    }

    @PostMapping("/calculate")
    public String calculateLoan(@RequestParam BigDecimal loanAmount, @RequestParam int paybackYears, Model model, SessionStatus sessionStatus) {
        String loanType = (String) model.getAttribute("loanType");
        LoanPaymentPlan paymentPlan = loanService.calculateLoan(loanAmount, paybackYears, loanTypes.get(loanType));
        model.addAttribute("paymentPlan", paymentPlan);
        sessionStatus.setComplete();

        return "loan-result";
    }
}
