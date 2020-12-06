package com.jipps.hombyme.api.core.product;

import lombok.Data;

@Data
public class Product {
    private final int productId;
    private final String name;
    private final int weight;
    private final String serviceAddress;

}
