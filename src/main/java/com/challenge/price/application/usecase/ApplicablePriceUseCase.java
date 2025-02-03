package com.challenge.price.application.usecase;

import com.challenge.price.domain.model.Price;

import java.time.LocalDateTime;
import java.util.Optional;

public interface ApplicablePriceUseCase {

 Optional<Price> findApplicablePrice(Long productId, Long brandId, LocalDateTime date);

}
