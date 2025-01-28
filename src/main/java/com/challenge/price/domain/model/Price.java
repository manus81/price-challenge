package com.challenge.price.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "PRICES")
public class Price {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "BRAND_ID")
  private Brand brand;

  @ManyToOne
  @JoinColumn(name = "PRODUCT_ID")
  private Product product;

  @ManyToOne
  @JoinColumn(name = "PRICE_LIST_ID")
  private PriceList priceList;

  private LocalDateTime startDate;

  private LocalDateTime endDate;

  private int priority;

  private double price;

  private String currency;
}
