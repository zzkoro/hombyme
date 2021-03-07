package com.jipps.hombyme.composite.product;

import com.jipps.hombyme.api.composite.product.ProductAggregate;
import com.jipps.hombyme.api.core.product.Product;
import com.jipps.hombyme.api.event.Event;
import com.jipps.hombyme.composite.product.services.ProductCompositeIntegration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.cloud.stream.test.binder.MessageCollector;
import org.springframework.http.HttpStatus;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.concurrent.BlockingQueue;

import static com.jipps.hombyme.api.event.Event.Type.CREATE;
import static com.jipps.hombyme.composite.product.IsSameEvent.sameEventExceptCreatedAt;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.cloud.stream.test.matcher.MessageQueueMatcher.receivesPayloadThat;
import static org.springframework.http.HttpStatus.OK;
import static reactor.core.publisher.Mono.just;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class MessagingTests {
    private static final int PRODUCT_ID_OK = 1;
    private static final int PRODUCT_ID_NOT_FOUND = 2;
    private static final int PRODUCT_ID_INVALID = 3;

    @LocalServerPort
    private int port;

    private WebTestClient client;

    @Autowired
    private ProductCompositeIntegration.MessageSources channels;

    @Autowired
    private MessageCollector collector;

    BlockingQueue<Message<?>> queueProducts = null;
    BlockingQueue<Message<?>> queueRecommendations = null;
    BlockingQueue<Message<?>> queueReviews = null;

    @BeforeEach
    void beforeEach() {
        this.client = WebTestClient.bindToServer()
                .baseUrl("http://localhost:" + port)
                //	.filter(documentationConfiguration(restDocumentation))
                .build();

        queueProducts = getQueue(channels.outputProducts());
        queueRecommendations = getQueue(channels.outputRecommendations());
        queueReviews = getQueue(channels.outputReviews());
    }

    @DisplayName("상품정보 생성하기1 - OK")
    @Test
    void createCompositeProduct1() {

        ProductAggregate compositeProduct = new ProductAggregate(1, "name", 1, null, null, null);
        postAndVerifyProduct(compositeProduct, OK);

        // Assert one expected new product events queued up
        assertEquals(1, queueProducts.size());

        Event<Integer, Product> expectedEvent = new Event(CREATE, compositeProduct.getProductId(), new Product(compositeProduct.getProductId(), compositeProduct.getName(), compositeProduct.getWeight(), null));
        assertThat(queueProducts, is(receivesPayloadThat(sameEventExceptCreatedAt(expectedEvent))));


    }

    private void postAndVerifyProduct(ProductAggregate compositeProduct, HttpStatus expectedStatus) {
        client.post()
                .uri("/product-composite")
                .body(just(compositeProduct), ProductAggregate.class)
                .exchange()
                .expectStatus().isEqualTo(expectedStatus);
    }

    private BlockingQueue<Message<?>> getQueue(MessageChannel messageChannel) {
        return collector.forChannel(messageChannel);
    }
}
