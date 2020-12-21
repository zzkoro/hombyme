package com.jipps.hombyme.api.core.product;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    private int productId;
    private String name;
    private int weight;
    private String serviceAddress;

}
