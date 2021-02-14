package com.jipps.hombyme.review;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT, properties = {
		"spring.datasource.url=jdbc:h2:mem:review-db"
})
class ReviewServiceApplicationTests {

	@Test
	void contextLoads() {
	}

}
