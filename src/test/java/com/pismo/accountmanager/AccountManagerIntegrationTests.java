package com.pismo.accountmanager;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.pismo.accountmanager.dto.AccountsDto;
import com.pismo.accountmanager.dto.TransactionsDto;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AccountManagerIntegrationTests {

    @Autowired
    private MockMvc mockMvc;

    @Order(1)
    @Test
    void contextLoads() {
    }

    @Order(2)
    @Test
    void listOperationsTypesSuccess() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/operationsTypes")
                        .contentType(MediaType.valueOf("application/json")))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value("4"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].operationTypeId").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].description").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].type").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].operationTypeId").value("1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].description").value("COMPRA A VISTA"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].type").value("DEBITO"));

    }

    @Order(3)
    @Test
    void createAccountSuccess() throws Exception {
        AccountsDto accountsDto = new AccountsDto(null, "14546541554");

        mockMvc.perform(MockMvcRequestBuilders.post("/accounts")
                        .contentType(MediaType.valueOf("application/json"))
                        .content(asJsonString(accountsDto)))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.accountId").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.documentNumber").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.accountId").value("5"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.documentNumber").value("14546541554"));

    }


    @Order(4)
    @Test
    void findAccountSuccess() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/accounts/5")
                        .contentType(MediaType.valueOf("application/json")))
                .andExpect(status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.jsonPath("$.accountId").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.documentNumber").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.accountId").value("5"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.documentNumber").value("14546541554"));

    }


    @Order(4)
    @Test
    void createAccountError() throws Exception {
        AccountsDto accountsDto = new AccountsDto(null, "");

        mockMvc.perform(MockMvcRequestBuilders.post("/accounts")
                        .contentType(MediaType.valueOf("application/json"))
                        .content(asJsonString(accountsDto)))
                .andExpect(status().isInternalServerError());
    }

    @Order(5)
    @Test
    void createAccountConflict() throws Exception {
        AccountsDto accountsDto = new AccountsDto(null, "12345678978");

        mockMvc.perform(MockMvcRequestBuilders.post("/accounts")
                        .contentType(MediaType.valueOf("application/json"))
                        .content(asJsonString(accountsDto)))
                .andExpect(status().isConflict());
    }



    @Order(6)
    @Test
    void findAccountNotFound() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/accounts/8")
                        .contentType(MediaType.valueOf("application/json")))
                .andExpect(status().isNotFound());

    }


    @Order(7)
    @Test
    void createTransactionSuccess() throws Exception {
        TransactionsDto transactionsDto = new TransactionsDto(null, 1l, 1l, BigDecimal.valueOf(50.5), LocalDateTime.now());

        mockMvc.perform(MockMvcRequestBuilders.post("/transactions")
                        .contentType(MediaType.valueOf("application/json"))
                        .content(asJsonString(transactionsDto)))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.transactionId").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.operationTypeId").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.accountId").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.amount").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.transactionId").value("4"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.operationTypeId").value("1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.accountId").value("1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.amount").value("50.5"));

    }

    @Order(8)
    @Test
    void findTransactionSuccess() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/transactions/4")
                        .contentType(MediaType.valueOf("application/json")))
                .andExpect(status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.jsonPath("$.transactionId").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.operationTypeId").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.accountId").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.amount").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.transactionId").value("4"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.operationTypeId").value("1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.accountId").value("1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.amount").value("50.5"));

    }


    @Order(9)
    @Test
    void createTransactionError() throws Exception {
        TransactionsDto transactionsDto = new TransactionsDto(null, null, 1l, BigDecimal.valueOf(50.5), LocalDateTime.now());

        mockMvc.perform(MockMvcRequestBuilders.post("/transactions")
                        .contentType(MediaType.valueOf("application/json"))
                        .content(asJsonString(transactionsDto)))
                .andExpect(status().isInternalServerError());
    }

    @Order(10)
    @Test
    void findTransactionNotFound() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/transactions/8")
                        .contentType(MediaType.valueOf("application/json")))
                .andExpect(status().isNotFound());
    }

    public static String asJsonString(final Object obj) {
        try {
            final ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule());

            return objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
