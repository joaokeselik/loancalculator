package com.keselik.loancalculator.exception;

import com.keselik.loancalculator.model.CarLoan;
import com.keselik.loancalculator.model.HousingLoan;
import com.keselik.loancalculator.model.LoanType;
import com.keselik.loancalculator.model.PersonalLoan;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleIllegalArgumentExceptionException(HttpServletRequest request, IllegalArgumentException ex, Model model) {
        model.addAttribute("errorMessage", ex.getMessage());
        LoanType loanType = (LoanType) request.getAttribute("loanType");
        return getLoanForm(loanType);
    }

    @ExceptionHandler(org.springframework.web.method.annotation.MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleMethodArgumentTypeMismatch(HttpServletRequest request, org.springframework.web.method.annotation.MethodArgumentTypeMismatchException ex, Model model) {
        model.addAttribute("errorMessage", "Invalid input. Please provide a valid value for " + ex.getName());
        LoanType loanType = (LoanType) request.getAttribute("loanType");
        return getLoanForm(loanType);
    }

    private String getLoanForm(LoanType loanType) {
        if (loanType instanceof HousingLoan) {
            return "housing-loan-form";
        } else if (loanType instanceof CarLoan) {
            return "car-loan-form";
        } else if (loanType instanceof PersonalLoan) {
            return "personal-loan-form";
        } else {
            return "housing-loan-form";
        }
    }

}