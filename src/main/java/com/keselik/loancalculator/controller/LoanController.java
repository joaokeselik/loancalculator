package com.keselik.loancalculator.controller;


import com.keselik.loancalculator.model.LoanPaymentPlan;
import com.keselik.loancalculator.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import java.math.BigDecimal;

@Controller
@SessionAttributes("loanType")
public class LoanController {

    private final LoanService loanService;

    @Autowired
    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    @RequestMapping(value = "/loan/{loanType}", method = RequestMethod.GET)
    public String showLoanForm(@PathVariable String loanType, Model model) {
        model.addAttribute("loanType", loanType);
        return loanType + "-loan-form";
    }

    @PostMapping("/calculate")
    public String calculateLoan(@RequestParam BigDecimal loanAmount, @RequestParam int paybackYears, Model model, SessionStatus sessionStatus) {
        String loanType = (String) model.getAttribute("loanType");
        LoanPaymentPlan paymentPlan = loanService.calculateLoan(loanAmount, paybackYears, loanType);
        model.addAttribute("paymentPlan", paymentPlan);
        sessionStatus.setComplete();

        return "loan-result";
    }
}
