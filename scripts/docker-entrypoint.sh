#!/usr/bin/env bash

#docker build --no-cache --build-arg PORT=8100 --progress=plain -f Dockerfile.backendApi .
#docker images | head
#docker run -p8200:8300 -e PORT=8300 <latest container>

cat /root/banner.txt

echo
echo
echo "PORT=$PORT"
export MICRONAUT_SERVER_PORT=$PORT
echo "MICRONAUT_SERVER_PORT=$MICRONAUT_SERVER_PORT"
echo
echo

java -jar /root/server.jar