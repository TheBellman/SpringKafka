# SpringKafka
Simple Java code to demonstrate working with Kafka and Spring Boot (at this time, Spring Boot 3.x)

![Java CI with Maven](https://github.com/TheBellman/SpringKafka/workflows/Java%20CI%20with%20Maven/badge.svg?branch=main)

## Prerequisites
This project assumes that:

 - [Apache Maven 3.8.6](https://maven.apache.org) or better is installed and in the command path;
 - Java 17 or later is installed and available;
 - git is installed and available in the command path.

It also pretty well assumes you have a Kafka cluster available for use. The easiest way to achieve that is to use the [confluentinc/cp-all-in-one](https://github.com/confluentinc/cp-all-in-one) project with Docker (yes, you will need Docker as well) which can run up an ensemble including Kafka and the Confluent schema registry on your desktop. Use of this project is also discussed at [Confluent](https://docs.confluent.io/platform/current/tutorials/build-your-own-demos.html)

## Test and Build
This is simple to build, however you will need Apache Maven and Java installed.

Basically:

```commandline
$ git clone git@github.com:TheBellman/SpringKafka.git
$ cd SpringKafka
$ mvn package
```

all being well, after a few seconds or minutes you should see something like:

```commandline
[INFO] --- maven-jar-plugin:3.3.0:jar (default-jar) @ springkafka ---
[INFO] Building jar: /Users/robert/Projects/Java/SpringKafka/target/springkafka-0.0.1-SNAPSHOT.jar
[INFO] 
[INFO] --- spring-boot-maven-plugin:3.0.0:repackage (repackage) @ springkafka ---
[INFO] Replacing main artifact with repackaged archive
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  2.242 s
[INFO] Finished at: 2022-12-10T19:13:00Z
[INFO] ------------------------------------------------------------------------
```

## Usage
Executing with no other parameters will produce a help message

```shell
% java -jar target/springkafka-1.0-SNAPSHOT.jar           
11:55:19.201 [main] INFO net.parttimepolymath.spring.springkafka.SpringKafkaToyApplication - Application starting

======================================================================
| Spring Kafka Toy - springkafka:1.0-SNAPSHOT
| built   : 2022-12-11T11:54:46Z
======================================================================

2022-12-11T11:55:19.462Z  INFO 30369 --- [           main] n.p.s.s.SpringKafkaToyApplication        : Starting SpringKafkaToyApplication v1.0-SNAPSHOT using Java 17.0.5 with PID 30369 (/Users/robert/Projects/Java/SpringKafka/target/springkafka-1.0-SNAPSHOT.jar started by robert in /Users/robert/Projects/Java/SpringKafka)
2022-12-11T11:55:19.463Z  INFO 30369 --- [           main] n.p.s.s.SpringKafkaToyApplication        : The following 1 profile is active: "development"
2022-12-11T11:55:19.685Z  INFO 30369 --- [           main] n.p.spring.springkafka.VersionConsumer   : Version details : Version(name=Spring Kafka Toy, version=1.0-SNAPSHOT, build=2022-12-11T11:54:46Z, profile=development)
2022-12-11T11:55:19.714Z  INFO 30369 --- [           main] n.p.s.s.SpringKafkaToyApplication        : Started SpringKafkaToyApplication in 0.449 seconds (process running for 0.648)
usage:
 --help                        print this help message
 --bootstrap-server <broker>   initial server to connect to (e.g. localhost:9092) [REQUIRED]
 --consumer                    run as a data consumer
 --producer                    run as a data producer
 --count <count>               number of messages to produce
 --topic                       topic name used

2022-12-11T11:55:19.716Z  INFO 30369 --- [           main] n.p.s.s.SpringKafkaToyApplication        : Application ending
```

and the same if `--help` is specified:

```shell
% java -jar target/springkafka-1.0-SNAPSHOT.jar --help           
11:55:19.201 [main] INFO net.parttimepolymath.spring.springkafka.SpringKafkaToyApplication - Application starting

======================================================================
| Spring Kafka Toy - springkafka:1.0-SNAPSHOT
| built   : 2022-12-11T11:54:46Z
======================================================================

2022-12-11T11:55:19.462Z  INFO 30369 --- [           main] n.p.s.s.SpringKafkaToyApplication        : Starting SpringKafkaToyApplication v1.0-SNAPSHOT using Java 17.0.5 with PID 30369 (/Users/robert/Projects/Java/SpringKafka/target/springkafka-1.0-SNAPSHOT.jar started by robert in /Users/robert/Projects/Java/SpringKafka)
2022-12-11T11:55:19.463Z  INFO 30369 --- [           main] n.p.s.s.SpringKafkaToyApplication        : The following 1 profile is active: "development"
2022-12-11T11:55:19.685Z  INFO 30369 --- [           main] n.p.spring.springkafka.VersionConsumer   : Version details : Version(name=Spring Kafka Toy, version=1.0-SNAPSHOT, build=2022-12-11T11:54:46Z, profile=development)
2022-12-11T11:55:19.714Z  INFO 30369 --- [           main] n.p.s.s.SpringKafkaToyApplication        : Started SpringKafkaToyApplication in 0.449 seconds (process running for 0.648)
usage:
 --help                        print this help message
 --bootstrap-server <broker>   initial server to connect to (e.g. localhost:9092) [REQUIRED]
 --consumer                    run as a data consumer
 --producer                    run as a data producer
 --count <count>               number of messages to produce
 --topic                       topic name used

2022-12-11T11:55:19.716Z  INFO 30369 --- [           main] n.p.s.s.SpringKafkaToyApplication        : Application ending
```

More typically, you will either want it to execute as the message producer:

```shell
% java -jar target/springkafka-1.0-SNAPSHOT.jar --producer --bootstrap-server=localhost:9092 --count=1000
```

or message consumer:

```shell
% java -jar target/springkafka-1.0-SNAPSHOT.jar --consumer --bootstrap-server=localhost:9092
```

For the producer, if `count` is not specified, a default number of messages will be produced. `count` is ignored for the consumer.

## ToDo
To be completed

## License

Copyright 2022 Little Dog Digital

Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License.

You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
