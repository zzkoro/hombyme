package com.jipps.hombyme.composite.product;

import com.jipps.hombyme.composite.product.services.ProductCompositeIntegration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@ComponentScan("com.jipps")
public class ProductCompositeServiceApplication {

	@Bean
	RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@Autowired
	ProductCompositeIntegration integration;


	public static void main(String[] args) {
		SpringApplication.run(ProductCompositeServiceApplication.class, args);
	}

}
