package com.challenge.price.domain.ports;

import com.challenge.price.domain.model.Price;

import java.util.List;

public interface PriceRepository {

  List<Price> findApplicablePrices(Long productId, Long brandId);
}
