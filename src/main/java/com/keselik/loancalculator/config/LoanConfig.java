package com.keselik.loancalculator.config;

import com.keselik.loancalculator.model.CarLoan;
import com.keselik.loancalculator.model.HousingLoan;
import com.keselik.loancalculator.model.LoanType;
import com.keselik.loancalculator.model.PersonalLoan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoanConfig {

    @Bean
    @Qualifier("housing")
    public LoanType housing() {
        return new HousingLoan();
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