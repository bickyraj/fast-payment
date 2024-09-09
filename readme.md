## Requirements
- java version 17
- gradle version > 8.5
- docker
- python3
- airflow

## Project Setup

    ./gradlew clean build

## Setup Docker

    docker-compose up -d

## Starting Application

- (intellij) in the services tab run all services.

## Configure Keycloak
- create a new realm fast-payment or any and update the environment file.

## Database Setup
- create a database eg. "fast_payment_db" or any and update the environment file.