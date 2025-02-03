package com.challenge.price.infrastructure.persistence.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "PRICES")
public class PriceEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "BRAND_ID")
  private BrandEntity brand;

  @ManyToOne
  @JoinColumn(name = "PRODUCT_ID")
  private ProductEntity product;

  @ManyToOne
  @JoinColumn(name = "PRICE_LIST_ID")
  private PriceListEntity priceList;

  private LocalDateTime startDate;

  private LocalDateTime endDate;

  private int priority;

  private double price;

  private String currency;
}
