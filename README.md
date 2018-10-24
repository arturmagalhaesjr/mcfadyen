McFadyen Fullstack Challenge
==========


[![Build Status](https://travis-ci.org/nezkal/mcfadyen.svg?branch=master)](https://travis-ci.org/nezkal/mcfadyen)
[![Coverage Status](https://coveralls.io/repos/github/nezkal/mcfadyen/badge.svg)](https://coveralls.io/github/nezkal/mcfadyen)

# Continuous Integration Pipeline 

[![CI Diagram](https://raw.githubusercontent.com/nezkal/mcfadyen/master/McFadyenCI.png)](https://raw.githubusercontent.com/nezkal/mcfadyen/master/McFadyenCI.png)


## Documentation API Reference

Oracle Apiary Documentation: [https://mcfadyenchallenge.docs.apiary.io](https://mcfadyenchallenge.docs.apiary.io)

# How to install:

## Maven

System requirements:
> * JDK 8

* Clone this project
* Execute ``` ./mvnw clean spring-boot:run ```

This project will execute on localhost:8080

## Docker Compose / Docker Swarm

You can run this app with Docker Compose or Docker Swarm as well:

Compose

``` docker-compose up -d ```

Swarm
``` bash 
docker swarm init
docker stack deploy --compose-file=docker-compose.yml
```


## Docker Build Image

System requirements:
> Docker

You can create your own image to scale this project

First of all, you have to change the prefix in pom file

``` <docker.image.prefix>nezkal</docker.image.prefix>  ```

* Execute ``` ./mvnw install dockerfile:build ```

After that, the Docker's image will be created on **prefix/artifactId**

***Note: You need to be logged in Docker Hub by "docker login"***

So, you can push by docker push <prefix/artifactId> or run the container: ``` docker run -p 8080:8080 -t prefix/artifactId ```

Thanks

