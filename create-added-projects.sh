#!/usr/bin/env bash

mkdir api
cd api

spring init \
--boot-version=2.4.0 \
--build=gradle \
--java-version=11 \
--packaging=jar \
--name=api-service \
--package-name=com.jipps.hombyme.api \
--groupId=com.jipps.hombyme.api \
--dependencies=actuator,webflux \
--version=1.0.0-SNAPSHOT \
api-service

cd ../

mkdir util
cd util

spring init \
--boot-version=2.4.0 \
--build=gradle \
--java-version=11 \
--packaging=jar \
--name=util-service \
--package-name=com.jipps.hombyme.util \
--groupId=com.jipps.hombyme.util \
--dependencies=actuator,webflux \
--version=1.0.0-SNAPSHOT \
util-service

cd ..
