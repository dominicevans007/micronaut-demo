# Getting Started with App

## Available Urls
### `./gradlew run`

Runs the app in the development mode.\
Open [http://localhost:8080](http://localhost:8080)


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

## Name 
-
http://localhost:8080/product/sort/name \
Defaults to asc order

http://localhost:8080/product/sort/name?order=asc \
http://localhost:8080/product/sort/name?order=desc

## Price
-
http://localhost:8080/product/sort/price \
Defaults to asc order

http://localhost:8080/product/sort/price?order=asc \
http://localhost:8080/product/sort/price?order=desc
