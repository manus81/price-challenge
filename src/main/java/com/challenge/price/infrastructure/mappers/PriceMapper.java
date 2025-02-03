package com.challenge.price.infrastructure.mappers;

import com.challenge.price.domain.model.Price;
import com.challenge.price.infrastructure.persistence.entities.PriceEntity;
import com.challenge.price.infrastructure.web.dtos.PriceDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingInheritanceStrategy;

@Mapper(componentModel = "spring", mappingInheritanceStrategy = MappingInheritanceStrategy.EXPLICIT)
public interface PriceMapper {

  Price toDomain(PriceEntity price);

  @Mapping(source = "product.id", target = "productId")
  @Mapping(source = "brand.id", target = "brandId")
  @Mapping(source = "priceList.id", target = "priceListId")
  PriceDTO toDTO(Price price);
}
