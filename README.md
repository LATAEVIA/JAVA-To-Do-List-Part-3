# _JAVA To Do List Part 3_

#### _Practice building a Java application with two models and creating both the back-end and front-end of a RESTful database-backed Spark application that is fully tested with both unit tests and integration tests by rebuilding [JAVA-To-Do-List-Part-2] (https://github.com/LATAEVIA/JAVA-To-Do-List-Part-2) using One-to-Many RESTful routing., 05/07/2016_

#### By _**LaTaevia**_

## Description

_Java application where users can create tasks for a to do list, organize tasks by category, and assign multiple tasks to each category, while assigning multiple categories to each task. All data is stored in a server and can be viewed even after the application is refreshed or closed.._

## Prerequisites

You will need the following properly installed on your computer.

* [Gradle](https://gradle.org/gradle-download/)
* PostgreSQL on Mac with HomeBrew `brew install postgres` 
* [PostgreSQL (All OS)] (http://www.enterprisedb.com/products-services-training/pgdownload#windows)

## Installation

* `git clone https://github.com/LATAEVIA/JAVA-To-Do-List-Part-3.git`
* Change into the new directory
* In a new terminal window/tab `postgres` to launch postgres
* In another new terminal window/tab `psql` to launch psql
* In psql window/tab `CREATE DATABASE to_do;`
* In the terminal window/tab `psql to_do < to_do.sql`
* In psql window/tab `CREATE DATABASE to_do_test WITH TEMPLATE to_do;`
* `gradle build`

## Running / Development

* `gradle run`
* Visit app at [http://localhost:4567](http://localhost:4567).

## Technologies Used

* _HTML_
* _CSS_
* _Bootstrap_
* _Java_
* _Gradle_
* _Spark_
* _Velocity_
* _FluentLenium_
* _PostgreSQL_

### License

*This software is licensed under the MIT license*

Copyright (c) 2016 **_LaTaevia_**
