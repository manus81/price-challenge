package com.challenge.price.application.usecase;

import com.challenge.price.domain.model.Price;
import com.challenge.price.domain.services.PriceService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@AllArgsConstructor
@Service
public class PriceUseCase {
  private final PriceService priceService;

  public Price findApplicablePrices(LocalDateTime applicationDate, Long productId, Long brandId) {
    return priceService.findApplicablePrice(applicationDate, productId, brandId);
  }
}
