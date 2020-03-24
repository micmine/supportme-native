#!/bin/bash

if [ -z $1 ]; then
	if [ "$1" == "--clean" ];then
		rm -rf ./build
	fi
fi

docker start postgres-supportme-native && gradle clean eclipse run
