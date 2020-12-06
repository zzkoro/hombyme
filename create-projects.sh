#!/usr/bin/env bash

mkdir microservices
cd microservices

spring init \
--boot-version=2.4.0 \
--build=gradle \
--java-version=11 \
--packaging=jar \
--name=product-service \
--package-name=com.jipps.hombyme.product \
--groupId=com.jipps.hombyme.product \
--dependencies=actuator,webflux \
--version=1.0.0-SNAPSHOT \
product-service

spring init \
--boot-version=2.4.0 \
--build=gradle \
--java-version=11 \
--packaging=jar \
--name=review-service \
--package-name=com.jipps.hombyme.review \
--groupId=com.jipps.hombyme.review \
--dependencies=actuator,webflux \
--version=1.0.0-SNAPSHOT \
review-service

spring init \
--boot-version=2.4.0 \
--build=gradle \
--java-version=11 \
--packaging=jar \
--name=recommendation-service \
--package-name=com.jipps.hombyme.recommendation \
--groupId=com.jipps.hombyme.recommendation \
--dependencies=actuator,webflux \
--version=1.0.0-SNAPSHOT \
recommendation-service

spring init \
--boot-version=2.4.0 \
--build=gradle \
--java-version=11 \
--packaging=jar \
--name=product-composite-service \
--package-name=com.jipps.hombyme.composite.product \
--groupId=com.jipps.hombyme.composite.product \
--dependencies=actuator,webflux \
--version=1.0.0-SNAPSHOT \
product-composite-service

cd ..
