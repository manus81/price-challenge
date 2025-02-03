package com.challenge.price.infrastructure.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
class PriceControllerIT {

  @Autowired
  private MockMvc mockMvc;

  @Test
  void shouldReturnNotFound_whenNoPrices() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.get("/prices/applicable")
            .param("productId", "9999")
            .param("brandId", "1")
            .param("date", "2020-06-14T00:00"))
        .andExpect(MockMvcResultMatchers.status().isNoContent());
  }

  // Test 1: petición a las 10:00 del día 14 del producto 35455 para la brand 1 (ZARA)
  @Test
  void shouldReturnPrices_whenValidRequest1() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.get("/prices/applicable")
            .param("productId", "35455")
            .param("brandId", "1")
            .param("date", "2020-06-14T10:00"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.price").value(35.50))
        .andExpect(MockMvcResultMatchers.jsonPath("$.currency").value("EUR"));
  }

  // Test 2: petición a las 16:00 del día 14 del producto 35455 para la brand 1 (ZARA)
  @Test
  void shouldReturnPrices_whenValidRequest2() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.get("/prices/applicable")
            .param("productId", "35455")
            .param("brandId", "1")
            .param("date", "2020-06-14T16:00"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.price").value(25.45))
        .andExpect(MockMvcResultMatchers.jsonPath("$.currency").value("EUR"));
  }

  // Test 3: petición a las 21:00 del día 14 del producto 35455 para la brand 1 (ZARA)
  @Test
  void shouldReturnPrices_whenValidRequest3() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.get("/prices/applicable")
            .param("productId", "35455")
            .param("brandId", "1")
            .param("date", "2020-06-14T21:00"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.price").value(35.5))
        .andExpect(MockMvcResultMatchers.jsonPath("$.currency").value("EUR"));
  }

  // Test 4: petición a las 10:00 del día 15 del producto 35455 para la brand 1 (ZARA)
  @Test
  void shouldReturnPrices_whenValidRequest4() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.get("/prices/applicable")
            .param("productId", "35455")
            .param("brandId", "1")
            .param("date", "2020-06-15T10:00"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.price").value(30.5))
        .andExpect(MockMvcResultMatchers.jsonPath("$.currency").value("EUR"));
  }

  // Test 5: petición a las 21:00 del día 16 del producto 35455 para la brand 1 (ZARA)
  @Test
  void shouldReturnPrices_whenValidRequest5() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.get("/prices/applicable")
            .param("productId", "35455")
            .param("brandId", "1")
            .param("date", "2020-06-16T21:00"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.price").value(38.95))
        .andExpect(MockMvcResultMatchers.jsonPath("$.currency").value("EUR"));
  }
}
