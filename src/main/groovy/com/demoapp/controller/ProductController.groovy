package com.demoapp.controller


import com.demoapp.service.ClientInterface
import com.demoapp.service.ProductService
import io.micronaut.http.HttpResponse
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.PathVariable
import io.micronaut.http.annotation.QueryValue

import javax.annotation.Nullable
import javax.inject.Inject

@Controller('/product')
class ProductController {
    @Inject
    ProductService productService

    @Inject
    ClientInterface client

    /**
     * Loads products from the Rest Api
     * @return a list of products
     */
    @Get(uri = "/", produces = MediaType.APPLICATION_JSON)
    def products() {
        def products = [
                [ "name": "Cheese", "price" : 2.50, "style": "Refrigerated foods", image: "https://media.istockphoto.com/photos/cheese-on-white-picture-id1127471287?k=6&m=1127471287&s=612x612&w=0&h=UeS8tmYvojVM_ezFGg8NAKqi925-iJuzR4GNhxWSvFQ="],
                [ "name": "Crisps", "price" : 3, "style": "the Snack isle", image: "https://www.costco.co.uk/medias/sys_master/images/hb7/h0a/44087827365918.jpg"],
                [ "name": "Pizza", "price" : 4, "style": "Refrigerated foods", image: "https://www.simplyrecipes.com/thmb/52FAbqwMY-ZDhx2ogNm8vpFx07M=/450x0/filters:no_upscale():max_bytes(150000):strip_icc()/__opt__aboutcom__coeus__resources__content_migration__simply_recipes__uploads__2019__09__easy-pepperoni-pizza-lead-4-82c60893fcad4ade906a8a9f59b8da9d.jpg"],
                [ "name": "Chocolate", "price" : 1.50, "style": "the Snack isle", image: "https://i2-prod.cambridge-news.co.uk/incoming/article14344488.ece/ALTERNATES/s1200c/0_dairy-milk.jpg"],
                [ "name": "Self-raising flour", "price" : 1.50, "style": "Home baking", image: "https://cdn.squires-kitchen.com/products/images/1100/6ad9f281-4be3-4b6b-8ed5-c78e9374f82c.jpg"],
                [ "name": "Ground almonds", "price" : 3, "style": "Home baking", image: "https://www.theasiancookshop.co.uk/ekmps/shops/asiancookshop/images/almond-powder-ground-almonds-100g-7439-p.png" ]
        ] //as List<Product> //client.fetchProducts()
        HttpResponse response
        if (!products) {
            response = HttpResponse.noContent()
        } else {
            response = HttpResponse.ok(products)
            productService.addProducts(products)
        }
        response
    }

    @Get(uri = "/type/{type}", produces = MediaType.APPLICATION_JSON)
    def findByType(@QueryValue("type") String type) {
        HttpResponse response
        if (!type) {
            response = HttpResponse.notFound()
        } else {
            def products = productService.findByType(type)
            response = HttpResponse.ok(products)
        }
        response
    }

    @Get(uri = "/price/{price}", produces = MediaType.APPLICATION_JSON)
    def findByPrice(@QueryValue("price") String price) {
        HttpResponse response
        if (!price || (price && !["cheaper", "expensive"].contains(price))) {
            response = HttpResponse.notFound()
        } else {
            def products = productService.findByPrice(price)
            response = HttpResponse.ok(products)
        }
        response
    }

    @Get(uri = "/sort/{type}{?order}", produces = MediaType.APPLICATION_JSON)
    def sort(@PathVariable String type, @Nullable String order) {
        HttpResponse response
        if (!type || (type && !["name", "price"].contains(type))) {
            response = HttpResponse.notFound()
        } else {
            def products = productService.sortProducts(type, order)
            response = HttpResponse.ok(products)
        }
        return response
    }

}
