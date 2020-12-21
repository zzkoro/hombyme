package com.jipps.hombyme.composite.product;

import com.jipps.hombyme.api.core.product.Product;
import com.jipps.hombyme.api.core.recommendation.Recommendation;
import com.jipps.hombyme.api.core.review.Review;
import com.jipps.hombyme.composite.product.services.ProductCompositeIntegration;
import com.jipps.hombyme.util.exceptions.InvalidInputException;
import com.jipps.hombyme.util.exceptions.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import static java.util.Collections.singletonList;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class ProductCompositeServiceApplicationTests {
	private static final int PRODUCT_ID_OK = 1;
	private static final int PRODUCT_ID_NOT_FOUND = 2;
	private static final int PRODUCT_ID_INVALID = 3;

	@LocalServerPort
	private int port;

	private WebTestClient client;

	@MockBean
	private ProductCompositeIntegration compositeIntegration;

	@BeforeEach
	void beforeEach() {

		this.client = WebTestClient.bindToServer()
				.baseUrl("http://localhost:" + port)
			//	.filter(documentationConfiguration(restDocumentation))
				.build();

		when(compositeIntegration.getProduct(PRODUCT_ID_OK)).
				thenReturn(new Product(PRODUCT_ID_OK, "name", 1, "mock-address"));
		when(compositeIntegration.getRecommendations(PRODUCT_ID_OK)).
				thenReturn(singletonList(new Recommendation(PRODUCT_ID_OK, 1, "author", 1, "content", "mock-address")));
		when(compositeIntegration.getReviews(PRODUCT_ID_OK)).
				thenReturn(singletonList(new Review(PRODUCT_ID_OK, 1, "author", "subject", "content", "mock-address")));

		when(compositeIntegration.getProduct(PRODUCT_ID_NOT_FOUND)).
				thenThrow(new NotFoundException("NOT FOUND: " + PRODUCT_ID_NOT_FOUND));
		when(compositeIntegration.getProduct(PRODUCT_ID_INVALID)).
				thenThrow(new InvalidInputException("INVALID: " + PRODUCT_ID_INVALID));

	}


	@Test
	void contextLoads() {
	}

	@DisplayName("ID로 상품정보 가져오기 - OK")
	@Test
	void getProductById() {
		client.get()
				.uri("/product-composite/" + PRODUCT_ID_OK)
				.accept(MediaType.APPLICATION_JSON)
				.exchange()
				.expectStatus().isOk()
				.expectHeader().contentType(MediaType.APPLICATION_JSON)
				.expectBody()
				.jsonPath("$.productId").isEqualTo(PRODUCT_ID_OK)
				.jsonPath("$.recommendations.length()").isEqualTo(1)
				.jsonPath("$.reviews.length()").isEqualTo(1);
	}

	@DisplayName("상품정보 없음 오류")
	@Test
	void getProductNotFound() {
		client.get()
				.uri("/product-composite/" + PRODUCT_ID_NOT_FOUND)
				.accept(MediaType.APPLICATION_JSON)
				.exchange()
				.expectStatus().isNotFound()
				.expectHeader().contentType(MediaType.APPLICATION_JSON)
				.expectBody()
				.jsonPath("$.path").isEqualTo("/product-composite/" + PRODUCT_ID_NOT_FOUND)
				.jsonPath("$.message").isEqualTo("NOT FOUND: " + PRODUCT_ID_NOT_FOUND);
	}

}
