package com.jipps.hombyme.api.composite.product;

import lombok.Data;

import java.util.List;

@Data
public class ProductAggregate {
    private final int productId;
    private final String name;
    private final int weight;
    private final List<RecommendationSummary> recommendations;
    private final List<ReviewSummary> reviews;
    private final ServiceAddresses serviceAddresses;
}
