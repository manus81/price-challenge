package com.challenge.price.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Price {

  private Long id;

  private Brand brand;

  private Product product;

  private PriceList priceList;

  private LocalDateTime startDate;

  private LocalDateTime endDate;

  private int priority;

  private double price;

  private String currency;
}
