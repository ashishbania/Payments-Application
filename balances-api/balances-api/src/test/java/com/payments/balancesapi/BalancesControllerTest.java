package com.payments.balancesapi;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class BalancesControllerTest {
  @Autowired private MockMvc mockMvc;

  private static String asJsonString(final Object obj) {
    try {
      final ObjectMapper mapper = new ObjectMapper();
      final String jsonContent = mapper.writeValueAsString(obj);
      return jsonContent;
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @Test
  public void testGetAllTransactions() throws Exception {
    mockMvc.perform(get("/balance/")).andExpect(status().isOk());
    //            .andExpect(jsonPath("$", hasSize(2)))
    //            .andExpect(jsonPath("$[0].id", is(1)))
    //            .andExpect(jsonPath("$[0].name", is("Item 1")))
    //            .andExpect(jsonPath("$[1].id", is(2)))
    //            .andExpect(jsonPath("$[1].name", is("Item 2")));
  }

  @Test
  void testGetTransactionsById() throws Exception {
    mockMvc.perform(get("/balance/1")).andExpect(status().isOk());
  }

  @Test
  void addAccounts() throws Exception {
    Balances balance1 =
        new Balances(100, 100, "5048375892149526", "$789.8", "USD", "Debit", "Rejected");
    MvcResult mvcResult =
        mockMvc
            .perform(
                post("/balance/")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(asJsonString(balance1)))
            .andExpect(status().isOk())
            .andReturn();
  }

  @Test
  void removeAccountByID() throws Exception {
    int transactionIdToDelete = 1;
    mockMvc
        .perform(delete("/balance/1").contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andReturn();
  }
}
