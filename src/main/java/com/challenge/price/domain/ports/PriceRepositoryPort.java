package com.challenge.price.domain.ports;

import com.challenge.price.domain.model.Price;

import java.time.LocalDateTime;
import java.util.Optional;

public interface PriceRepositoryPort {

  Optional<Price> findApplicablePrice(Long productId, Long brandId, LocalDateTime date);
}
