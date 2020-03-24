#!/bin/bash

docker start postgres-supportme-native && rm -rf build/ && gradle clean run
