package com.challenge.price.application.services;

import com.challenge.price.application.usecase.ApplicablePriceUseCase;
import com.challenge.price.domain.model.Price;
import com.challenge.price.domain.ports.PriceRepositoryPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ApplicablePriceService implements ApplicablePriceUseCase {

  private final PriceRepositoryPort priceRepositoryPort;

  public Optional<Price> findApplicablePrice(Long productId, Long brandId, LocalDateTime date) {
    return priceRepositoryPort.findApplicablePrice(productId, brandId, date);
  }
}
