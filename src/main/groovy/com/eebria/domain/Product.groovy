package com.eebria.domain

import groovy.transform.CompileStatic

@CompileStatic
class Product {
    String name
    String image
    String style
    Double price
    List<Product> products = []
}