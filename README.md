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
To be completed

## ToDo
To be completed

## License

Copyright 2022 Little Dog Digital

Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License.

You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
