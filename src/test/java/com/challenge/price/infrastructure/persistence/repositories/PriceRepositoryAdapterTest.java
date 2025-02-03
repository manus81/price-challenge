package com.challenge.price.infrastructure.persistence.repositories;

import com.challenge.price.domain.model.Price;
import com.challenge.price.infrastructure.mappers.PriceMapper;
import com.challenge.price.infrastructure.persistence.entities.BrandEntity;
import com.challenge.price.infrastructure.persistence.entities.PriceEntity;
import com.challenge.price.infrastructure.persistence.entities.PriceListEntity;
import com.challenge.price.infrastructure.persistence.entities.ProductEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PriceRepositoryAdapterTest {

  @Mock
  private JpaPriceRepository jpaPriceRepository;

  @Mock
  private PriceMapper priceMapper;

  @InjectMocks
  private PriceRepositoryAdapter priceRepositoryAdapter;

  private Long productId = 101L;
  private Long brandId = 202L;
  private LocalDateTime date = LocalDateTime.now();

  @Test
  void testFindApplicablePrice_whenPriceFound() {
    PriceEntity priceEntity = PriceEntity.builder()
        .id(1L)
        .product(ProductEntity.builder().id(101L).build())
        .brand(BrandEntity.builder().id(202L).build())
        .priceList(PriceListEntity.builder().id(303L).build())
        .build();
    priceEntity.setPrice(100.0);
    priceEntity.setCurrency("USD");

    Price price = new Price(1L, null, null, null, date, date, 1, 100.0, "USD");

    when(jpaPriceRepository.findApplicablePrice(productId, brandId, date)).thenReturn(Optional.of(priceEntity));
    when(priceMapper.toDomain(priceEntity)).thenReturn(price);

    Optional<Price> result = priceRepositoryAdapter.findApplicablePrice(productId, brandId, date);

    assertTrue(result.isPresent());
    assertEquals(1L, result.get().getId());
    assertEquals(100.0, result.get().getPrice());
    assertEquals("USD", result.get().getCurrency());

    verify(jpaPriceRepository, times(1)).findApplicablePrice(productId, brandId, date);
    verify(priceMapper, times(1)).toDomain(priceEntity);
  }

  @Test
  void testFindApplicablePrice_whenPriceNotFound() {
    when(jpaPriceRepository.findApplicablePrice(productId, brandId, date)).thenReturn(Optional.empty());

    Optional<Price> result = priceRepositoryAdapter.findApplicablePrice(productId, brandId, date);

    assertFalse(result.isPresent());

    verify(jpaPriceRepository, times(1)).findApplicablePrice(productId, brandId, date);
    verify(priceMapper, never()).toDomain(any());
  }
}
