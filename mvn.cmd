@echo off
docker run --rm -it ^
  -v %cd%:/app ^
  -v maven-repo:/root/.m2 ^
  -w /app ^
  maven:3.8.4-openjdk-11 mvn %*