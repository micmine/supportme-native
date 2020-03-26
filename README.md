# supportme-native

## General conditions

The goal is to build a support system for companies that want to move away from phone support or reduce it. But who needs a stable and fast system. 

A support system for all companies and products.
An easy way to help the customer with problems.

The following objects are to be implemented. There should be users who have further information Users also have login data. Users can be in a group.
These groups can have permission. Users can be in a chat. There should be predefined messages.

# dependencies
- java OpenJDK 11 (11.0.6 tested)

## development setup
### database
install [docker](https://www.docker.com/) and setup a database like so
```bash
docker run --name postgres-supportme-native -e POSTGRES_PASSWORD=pass -d postgres
```
And start psql shell
```bash
docker exec -it postgres-supportme-native psql -U postgres
```
and create the dadabase with the sql/create.sql
#### Use an existing database 
use a existing postgres database and remove line "10" of ./run.sh and change the credentials in "ch.iso.m120.model.Database" and create the dadabase with the sql/create.sql

### build 
There is a helper that also starts the database ./run.sh
Or just execute
```bash
gradle clean eclipse run
```
If you don't have Gradle installed you can use ./gradlew or on windows gradlew.bat



