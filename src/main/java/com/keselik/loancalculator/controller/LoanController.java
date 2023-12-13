package com.keselik.loancalculator.controller;


import com.keselik.loancalculator.model.HousingLoan;
import com.keselik.loancalculator.model.LoanPaymentPlan;
import com.keselik.loancalculator.model.LoanType;
import com.keselik.loancalculator.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@Controller
public class LoanController {

    private final LoanService loanService;

    @Autowired
    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    @RequestMapping(value="/loan", method = RequestMethod.GET)
    public String showLoanForm() {
        return "loan-form";
    }

    @PostMapping("/calculate")
    public String calculateLoan(@RequestParam BigDecimal loanAmount, @RequestParam int paybackYears, Model model) {
        LoanType loanType = new HousingLoan();
        LoanPaymentPlan paymentPlan = loanService.calculateLoan(loanAmount, paybackYears, loanType);
        model.addAttribute("paymentPlan", paymentPlan);

        return "loan-result";
    }
}
