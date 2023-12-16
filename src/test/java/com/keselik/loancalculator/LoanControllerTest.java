package com.keselik.loancalculator;

import com.keselik.loancalculator.controller.LoanController;
import com.keselik.loancalculator.service.LoanService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

@SpringBootTest
@AutoConfigureMockMvc
class LoanControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private LoanService loanService;

    @Autowired
    private LoanController loanController;

    @Test
    void testShowLoanForm_ValidLoanType_ShouldReturnLoanFormPage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/loan/home"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("home-loan-form"));
    }

    @Test
    void testShowLoanForm_WithNonExistingLoanType() throws Exception {
        String nonExistingLoanType = "nonexistingloantype";
        mockMvc.perform(MockMvcRequestBuilders.get("/loan/{loanType}", nonExistingLoanType))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.view().name("loan-type-not-found-form"));
    }

    @Test
    void testCalculateLoan_ValidInput_ShouldReturnLoanResultPage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/calculate")
                        .param("loanAmount", "100000")
                        .param("paybackYears", "10")
                        .flashAttr("loanType", "home")  // Pass the mock LoanType as a flash attribute
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("loan-result"))
                .andExpect(model().attributeExists("paymentPlan"))
                .andExpect(model().attribute("loanType", "home"));
    }

    @Test
    void testCalculateLoan_InvalidInput_ShouldReturnBadRequest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/calculate")
                        .param("loanAmount", "-100000")
                        .param("paybackYears", "10")
                        .param("loanType", "home")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }
}
