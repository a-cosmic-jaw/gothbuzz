#!/usr/bin/env bash

cat /root/banner.txt

echo
echo
echo "PORT=$PORT"
export MICRONAUT_SERVER_PORT=$PORT
echo "MICRONAUT_SERVER_PORT=$MICRONAUT_SERVER_PORT"
echo
echo

java -jar /root/server.jar