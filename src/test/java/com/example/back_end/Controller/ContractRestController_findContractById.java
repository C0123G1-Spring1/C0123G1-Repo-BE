package com.example.back_end.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ContractRestController_findContractById {
    @Autowired
    private MockMvc mockMvc;
    @Test
    public void findContractById_id_1() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/employee/contract/findContractById/{id}","null"))
                .andDo(print()).andExpect(status().is4xxClientError());
    }
    @Test
    public void findContractById_id_2() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/employee/contract/findContractById/{id}",""))
                .andDo(print()).andExpect(status().is4xxClientError());
    }
    @Test
    public void findContractById_id_3() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/employee/contract/findContractById/{id}","16"))
                .andDo(print()).andExpect(status().is4xxClientError());
    }
    @Test
    public void findContractById_id_4() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/employee/contract/findContractById/{id}","2"))
                .andDo(print()).andExpect(status().is2xxSuccessful());
    }
}
