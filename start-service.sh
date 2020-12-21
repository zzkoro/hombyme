#!/usr/bin/env bash

start_cmp() {
 java -jar microservices/product-composite-service/build/libs/*.jar &
}
 
start_prd() {
 java -jar microservices/product-service/build/libs/*.jar &
}
 
start_rec() {
 java -jar microservices/recommendation-service/build/libs/*.jar &
}
 
start_rev() {
 java -jar microservices/review-service/build/libs/*.jar &
}

case "$1" in
  cmp)
    start_cmp
    RET_STATUS=$? 
    ;;
  prd)
    start_prd
    RET_STATUS=$?
    ;;
  rec)
    start_rec
    RET_STATUS=$?
    ;;
  rev)
    start_rev
    RET_STATUS=$?
    ;;
  *)
    echo "Usage: start-service.sh {cmp|prd|rec|rev}" >&2
    exit 1
    ;;
esac

exit 0

