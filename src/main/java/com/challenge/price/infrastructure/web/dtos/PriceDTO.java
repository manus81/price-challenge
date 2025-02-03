package com.challenge.price.infrastructure.web.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PriceDTO {
  private Long productId;
  private Long brandId;
  private Long priceListId;
  private LocalDateTime startDate;
  private LocalDateTime endDate;
  private Double price;
  private String currency;
}
