#!/usr/bin/env bash

echo "PORT=$PORT"
export MICRONAUT_SERVER_PORT=$PORT
echo "MICRONAUT_SERVER_PORT=$MICRONAUT_SERVER_PORT"

cat /root/banner.txt

java -jar /root/server.jar