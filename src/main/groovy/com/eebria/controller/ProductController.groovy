package com.eebria.controller

import com.eebria.domain.Product
import com.eebria.service.ClientInterface
import com.eebria.service.ProductService
import groovy.transform.CompileStatic
import io.micronaut.http.HttpResponse
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.PathVariable
import io.micronaut.http.annotation.QueryValue

import javax.annotation.Nullable
import javax.inject.Inject

@CompileStatic
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
        List<Product> products = client.fetchProducts()
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