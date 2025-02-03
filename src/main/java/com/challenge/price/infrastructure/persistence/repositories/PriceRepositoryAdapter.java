package com.challenge.price.infrastructure.persistence.repositories;

import com.challenge.price.domain.model.Price;
import com.challenge.price.domain.ports.PriceRepositoryPort;
import com.challenge.price.infrastructure.mappers.PriceMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;

@Component
@AllArgsConstructor
public class PriceRepositoryAdapter implements PriceRepositoryPort {

  private final JpaPriceRepository jpaPriceRepository;
  private final PriceMapper priceMapper;

  @Override
  public Optional<Price> findApplicablePrice(Long productId, Long brandId, LocalDateTime date) {
    return jpaPriceRepository.findApplicablePrice(productId, brandId, date)
        .map(priceMapper::toDomain);
  }
}
