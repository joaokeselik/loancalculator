package com.keselik.loancalculator.config;

import com.keselik.loancalculator.model.CarLoan;
import com.keselik.loancalculator.model.HomeLoan;
import com.keselik.loancalculator.model.LoanType;
import com.keselik.loancalculator.model.PersonalLoan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
public class LoanConfig {

    private final Map<String, LoanType> loanTypes;

    @Autowired
    public LoanConfig(Map<String, LoanType> loanTypes) {
        this.loanTypes = loanTypes;
    }

    @Bean
    @Qualifier("home")
    public LoanType home() {
        return new HomeLoan();
    }

    @Bean
    @Qualifier("car")
    public LoanType car() {
        return new CarLoan();
    }

    @Bean
    @Qualifier("personal")
    public LoanType personal() {
        return new PersonalLoan();
    }
}