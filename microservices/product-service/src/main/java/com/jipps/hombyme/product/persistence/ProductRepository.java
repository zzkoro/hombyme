package com.jipps.hombyme.product.persistence;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface ProductRepository extends PagingAndSortingRepository<ProductEntity, String> {
    @Transactional(readOnly = true)
    Optional<ProductEntity> findByProductId(int productId);
}
