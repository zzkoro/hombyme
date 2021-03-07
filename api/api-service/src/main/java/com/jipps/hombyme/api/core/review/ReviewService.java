package com.jipps.hombyme.api.core.review;

import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import java.util.List;

public interface ReviewService {

    @PostMapping(
        value = "/review",
        consumes = "application/json",
        produces = "application/json"
    )
    Review createReview(@RequestBody Review body);

    @GetMapping(
            value = "/review",
            produces = "application/json"
    )
    Flux<Review> getReviews(@RequestParam(value = "productId", required = true) int productId);

    @DeleteMapping(value = "/review")
    void deleteReviews(@RequestParam(value = "productId", required = true) int productId);
}
