package com.challenge.price.domain.services;

import com.challenge.price.domain.exceptions.PriceNotFoundException;
import com.challenge.price.domain.model.Brand;
import com.challenge.price.domain.model.Price;
import com.challenge.price.domain.model.Product;
import com.challenge.price.domain.ports.PriceRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class PriceServiceUT {

  @Mock
  private PriceRepository priceRepository;

  @InjectMocks
  private PriceService priceService;

  @Test
  void shouldReturnPrice_whenPriceIsFound() {
    Long productId = 35455L;
    Long brandId = 1L;
    LocalDateTime applicationDate = LocalDateTime.of(2020, 10, 10, 0, 0);
    Price price = Price.builder().id(1L)
        .brand(Brand.builder().id(brandId).build())
        .product(Product.builder().id(productId).build())
        .startDate(applicationDate.minusMonths(1)).endDate(applicationDate.plusMonths(1)).build();
    Mockito.when(priceRepository.findApplicablePrices(productId, brandId))
        .thenReturn(List.of(price));

    Price result = priceService
        .findApplicablePrice(applicationDate,
            productId, brandId);

    Assertions.assertNotNull(result);
    Assertions.assertEquals(price, result);
    Mockito.verify(priceRepository).findApplicablePrices(productId, brandId);
  }

  @Test
  void shouldThrowException_whenPriceNotValid() {
    Long productId = 35455L;
    Long brandId = 1L;
    LocalDateTime applicationDate = LocalDateTime.of(2020, 10, 10, 0, 0);
    Price price = Price.builder().id(1L)
        .brand(Brand.builder().id(brandId).build())
        .product(Product.builder().id(productId).build())
        .startDate(applicationDate.plusYears(1)).endDate(applicationDate.plusYears(2)).build();
    Mockito.when(priceRepository.findApplicablePrices(productId, brandId))
        .thenReturn(List.of(price));

    Assertions.assertThrows(PriceNotFoundException.class, () -> {
      priceService.findApplicablePrice(applicationDate, productId, brandId);
    });

    Mockito.verify(priceRepository).findApplicablePrices(productId, brandId);
  }

  @Test
  void shouldThrowException_whenPriceNotFound() {
    Mockito.when(priceRepository.findApplicablePrices(ArgumentMatchers.any(), ArgumentMatchers.any()))
        .thenReturn(Collections.emptyList());

    LocalDateTime applicationDate = LocalDateTime.of(2020, 10, 10, 0, 0);
    Assertions.assertThrows(PriceNotFoundException.class,
        () -> priceService.findApplicablePrice(applicationDate,35455L, 1L));
  }
}
