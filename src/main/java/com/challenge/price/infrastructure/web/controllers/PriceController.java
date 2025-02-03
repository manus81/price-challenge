package com.challenge.price.infrastructure.web.controllers;

import com.challenge.price.application.usecase.ApplicablePriceUseCase;
import com.challenge.price.infrastructure.mappers.PriceMapper;
import com.challenge.price.infrastructure.web.dtos.PriceDTO;
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
  private final ApplicablePriceUseCase applicablePriceUseCase;
  private final PriceMapper priceMapper;

  @GetMapping("/applicable")
  public ResponseEntity<PriceDTO> getApplicablePrice(
      @RequestParam("date")
      @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") LocalDateTime date,
      @RequestParam("productId") Long productId,
      @RequestParam("brandId") Long brandId) {

    return applicablePriceUseCase.findApplicablePrice(productId, brandId, date)
        .map(price -> ResponseEntity.ok(priceMapper.toDTO(price)))
        .orElseGet(() -> ResponseEntity.noContent().build());
  }
}
