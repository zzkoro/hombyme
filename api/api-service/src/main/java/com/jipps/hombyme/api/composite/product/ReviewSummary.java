package com.jipps.hombyme.api.composite.product;

import lombok.Data;

@Data
public class ReviewSummary {

    private final int reviewId;
    private final String author;
    private final String subject;
}
