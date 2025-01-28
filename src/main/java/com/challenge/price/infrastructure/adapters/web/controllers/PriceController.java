package com.challenge.price.infrastructure.adapters.web.controllers;

import com.challenge.price.application.usecase.PriceUseCase;
import com.challenge.price.domain.model.Price;
import com.challenge.price.infrastructure.adapters.web.dtos.PriceDTO;
import com.challenge.price.infrastructure.adapters.web.mappers.PriceMapper;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@AllArgsConstructor
@RestController
@RequestMapping("/prices")
class PriceController {
  private final PriceUseCase priceService;
  private final PriceMapper priceMapper;

  @GetMapping("/applicable")
  public ResponseEntity<PriceDTO> getApplicablePrice(
      @RequestParam("applicationDate")
      @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") LocalDateTime applicationDate,
      @RequestParam("productId") Long productId,
      @RequestParam("brandId") Long brandId) {

    Price price = priceService.findApplicablePrices(applicationDate, productId, brandId);

    return ResponseEntity.ok(priceMapper.toDTO(price));
  }
}
