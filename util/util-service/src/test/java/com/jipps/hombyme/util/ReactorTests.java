package com.jipps.hombyme.util;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;

public class ReactorTests {

    @Test
    void testFlux() {
        List<Integer> list = new ArrayList<>();

        Flux.just(1,2,3,4)
                .filter(n -> n % 2 == 0)
                .map(n -> n * 2)
                .log()
                .subscribe(n -> list.add(n));

        assertThat(list, Matchers.contains(4, 8));
    }

    @Test
    void testFluxBlocking() {
        List<Integer> list = Flux.just(1,2,3,4)
                .filter(n -> n % 2 == 0)
                .map(n -> n * 2)
                .log()
                .collectList()
                .block();

        assertThat(list, Matchers.contains(4, 8));
    }
}
