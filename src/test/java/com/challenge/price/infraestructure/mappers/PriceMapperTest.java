package com.challenge.price.infraestructure.mappers;

import com.challenge.price.domain.model.Brand;
import com.challenge.price.domain.model.Price;
import com.challenge.price.domain.model.PriceList;
import com.challenge.price.domain.model.Product;
import com.challenge.price.infrastructure.mappers.PriceMapper;
import com.challenge.price.infrastructure.persistence.entities.BrandEntity;
import com.challenge.price.infrastructure.persistence.entities.PriceEntity;
import com.challenge.price.infrastructure.persistence.entities.PriceListEntity;
import com.challenge.price.infrastructure.persistence.entities.ProductEntity;
import com.challenge.price.infrastructure.web.dtos.PriceDTO;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class PriceMapperTest {

  private final PriceMapper priceMapper = Mappers.getMapper(PriceMapper.class);

  @Test
  void testToDomain() {
    PriceEntity priceEntity = PriceEntity.builder()
        .id(1L)
        .product(ProductEntity.builder().id(101L).build())
        .brand(BrandEntity.builder().id(202L).build())
        .priceList(PriceListEntity.builder().id(303L).build())
        .build();
    priceEntity.setPrice(100.0);
    priceEntity.setCurrency("USD");

    Price price = priceMapper.toDomain(priceEntity);

    assertNotNull(price);
    assertEquals(1L, price.getId());
    assertNotNull(price.getProduct());
    assertEquals(101L, price.getProduct().getId()); // Assuming Product has a getId() method
    assertNotNull(price.getBrand());
    assertEquals(202L, price.getBrand().getId());   // Assuming Brand has a getId() method
    assertNotNull(price.getPriceList());
    assertEquals(303L, price.getPriceList().getId()); // Assuming PriceList has a getId() method
    assertEquals(100.0, price.getPrice());
    assertEquals("USD", price.getCurrency());
  }

  @Test
  void testToDTO() {
    Price price = Price.builder()
        .id(1L)
        .product(Product.builder().id(101L).build())
        .brand(Brand.builder().id(202L).build())
        .priceList(PriceList.builder().id(303L).build())
        .build();
    price.setPrice(100.0);
    price.setCurrency("USD");

    PriceDTO priceDTO = priceMapper.toDTO(price);

    assertNotNull(priceDTO);
    assertEquals(101L, priceDTO.getProductId());
    assertEquals(202L, priceDTO.getBrandId());
    assertEquals(303L, priceDTO.getPriceListId());
    assertEquals(100.0, priceDTO.getPrice());
    assertEquals("USD", priceDTO.getCurrency());
  }
}
