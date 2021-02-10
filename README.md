# Getting Started with App

## Prerequisite
Install Gradle

## Setup
Clone the project \
Open the terminal \
Browser to the project directory \
Run the following command
### `./gradlew run`

### `Base Server URL : (http://localhost:8080)`

## Available API 

http://localhost:8080/product \
Fetches all the Products from the Rest Api 

A method to get the cheaper or expensive product\
http://localhost:8080/product/price/cheaper \
http://localhost:8080/product/price/expensive


A method to get the products depending on the type of product, in this case “beer” and
“cider”.\
http://localhost:8080/product/type/beer \
http://localhost:8080/product/type/cider


A method to get the sort products by price and name, ascending or descending\

-By Name: Defaults to asc order\
http://localhost:8080/product/sort/name \

http://localhost:8080/product/sort/name?order=asc \
http://localhost:8080/product/sort/name?order=desc

-By Price: Defaults to asc order \
http://localhost:8080/product/sort/price \


http://localhost:8080/product/sort/price?order=asc \
http://localhost:8080/product/sort/price?order=desc
