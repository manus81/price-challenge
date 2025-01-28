package com.challenge.price.infrastructure.adapters.web.mappers;

import com.challenge.price.domain.model.Price;
import com.challenge.price.infrastructure.adapters.web.dtos.PriceDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingInheritanceStrategy;

@Mapper(componentModel = "spring", mappingInheritanceStrategy = MappingInheritanceStrategy.EXPLICIT)
public interface PriceMapper {
  @Mapping(source = "product.id", target = "productId")
  @Mapping(source = "brand.id", target = "brandId")
  @Mapping(source = "priceList.id", target = "priceListId")
  PriceDTO toDTO(Price price);
}
