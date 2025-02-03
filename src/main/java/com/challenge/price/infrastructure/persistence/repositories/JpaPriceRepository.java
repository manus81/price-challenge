package com.challenge.price.infrastructure.persistence.repositories;

import com.challenge.price.infrastructure.persistence.entities.PriceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface JpaPriceRepository extends JpaRepository<PriceEntity, Long> {

  @Query("""
          SELECT p FROM PriceEntity p
          WHERE p.product.id = :productId
          AND p.brand.id = :brandId
          AND :date BETWEEN p.startDate AND p.endDate
          ORDER BY p.priority DESC LIMIT 1
      """)
  Optional<PriceEntity> findApplicablePrice(@Param("productId") Long productId, @Param("brandId") Long brandId,
                                             @Param("date") LocalDateTime date);
}
