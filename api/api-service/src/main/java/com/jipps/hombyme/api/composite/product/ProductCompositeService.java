package com.jipps.hombyme.api.composite.product;

import com.jipps.hombyme.api.composite.product.ProductAggregate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

public interface ProductCompositeService {
    @GetMapping(value = "/product-composite/{productId}",
                produces = "application/json")
    ProductAggregate getProduct(@PathVariable int productId);
}
