package com.challenge.price.application.services;

import com.challenge.price.application.services.ApplicablePriceService;
import com.challenge.price.domain.model.Brand;
import com.challenge.price.domain.model.Price;
import com.challenge.price.domain.model.Product;
import com.challenge.price.domain.ports.PriceRepositoryPort;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class ApplicablePriceServiceTest {

  @Mock
  private PriceRepositoryPort priceRepositoryPort;

  @InjectMocks
  private ApplicablePriceService applicablePriceService;

  @Test
  void shouldReturnPrice_whenPriceIsFound() {
    Long productId = 35455L;
    Long brandId = 1L;
    LocalDateTime date = LocalDateTime.of(2020, 10, 10, 0, 0);
    Price price = Price.builder().id(1L)
        .brand(Brand.builder().id(brandId).build())
        .product(Product.builder().id(productId).build())
        .startDate(date.minusMonths(1)).endDate(date.plusMonths(1)).build();
    Mockito.when(priceRepositoryPort.findApplicablePrice(productId, brandId, date))
        .thenReturn(Optional.of(price));

    var result = applicablePriceService.findApplicablePrice(productId, brandId, date);

    Assertions.assertNotNull(result);
    Assertions.assertTrue(result.isPresent());
    Assertions.assertEquals(price, result.get());
    Mockito.verify(priceRepositoryPort).findApplicablePrice(productId, brandId, date);
  }

  @Test
  void shouldReturnPrice_whenPriceIsNotFound() {
    Long productId = 35455L;
    Long brandId = 1L;
    LocalDateTime date = LocalDateTime.of(2020, 10, 10, 0, 0);
    Mockito.when(priceRepositoryPort.findApplicablePrice(productId, brandId, date))
        .thenReturn(Optional.empty());

    var result = applicablePriceService.findApplicablePrice(productId, brandId, date);

    Assertions.assertNotNull(result);
    Assertions.assertTrue(result.isEmpty());
    Mockito.verify(priceRepositoryPort).findApplicablePrice(productId, brandId, date);
  }
}
