package com.demoapp.service

import com.demoapp.domain.Product
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.client.annotation.Client;

@Client("http://api.demo.com/")
public interface ClientInterface {

    /**
     * Fetches data from the Rest API
     * @return a list of items
     */
    @Get(consumes = MediaType.APPLICATION_JSON)
    List<Product> fetchProducts()
}
