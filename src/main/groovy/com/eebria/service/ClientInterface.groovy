package com.eebria.service

import com.eebria.domain.Product
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.client.annotation.Client;

@Client("http://api.eebria.com/") 
public interface ClientInterface {

    /**
     * Fetches data from the Eebria Rest API
     * @return a list of items
     */
    @Get(consumes = MediaType.APPLICATION_JSON)
    List<Product> fetchProducts()
}