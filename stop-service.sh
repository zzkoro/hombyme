#!/usr/bin/env bash

stop_cmp() {
 pid=`pgrep -f product-composite-service`
 if [ -n "$pid" ]; then
   kill -9 $pid
 fi
}
 
stop_prd() {
 pid=`pgrep -f product-service`
 if [ -n "$pid" ]; then
   kill -9 $pid
 fi
}
 
stop_rec() {
 pid=`pgrep -f recommendation-service`
 if [ -n  "$pid" ]; then
   kill -9 $pid
 fi
}
 
stop_rev() {
 pid=`pgrep -f review-service`
 if [ -n  "$pid" ]; then
   kill -9 $pid
 fi
}

stop_all() {
 stop_cmp
 stop_prd
 stop_rec
 stop_rev
}

case "$1" in
  cmp)
    stop_cmp
    RET_STATUS=$? 
    ;;
  prd)
    stop_prd
    RET_STATUS=$?
    ;;
  rec)
    stop_rec
    RET_STATUS=$?
    ;;
  rev)
    stop_rev
    RET_STATUS=$?
    ;;
  all)
    stop_all
    RET_STATUS=$?
    ;;
  *)
    echo "Usage: stop-service.sh {cmp|prd|rec|rev|all}" >&2
    exit 1
    ;;
esac

exit 0

