package com.demoapp.service

import com.demoapp.domain.Product

import javax.inject.Singleton

@Singleton
class ProductService {
    private final Product product = new Product()

    /**
     * Add products that are fetched from the Rest Api to a local container
     * @param products
     * @return sets the products to the container
     */
    def addProducts(def products) {
        println "-----------" + products
        if (!product.products) {
            product.products = products
        }
    }

    /**
     * A method to get the products depending on the type of product
     * @param type Type of the Product "beer/cider"
     * @return list of products based on the type
     */
    def findByType(String type) {
        return product.products.findAll({
            it.name.toLowerCase() == type.toLowerCase()
        })
    }

    /**
     * A method to get the cheaper or expensive product
     * @param price a String value such as "cheaper/expensive"
     * @return sorted list based on the price
     */
    def findByPrice(String price) {
        def products
        println "-----product.products------" + product.products

        if (price.toLowerCase() == "cheaper") {
            products = product.products.sort { +it.price }
        } else if (price.toLowerCase() == "expensive") {
            products = product.products.sort { -it.price }
        }
        return products
    }

    /**
     * A method to get the sort products by price and name, ascending or descending
     * @param type a String value such as "price/name"
     * @param order a String value such as "asc/desc"
     * @return by default returns ascending order if the @param sort is not specified
     */
    def sortProducts(String sortType, String sortOrder) {
        return product.products.sort({ a, b ->
            if (sortType == 'name') {
                if (sortOrder?.toLowerCase() == "desc") {
                    b.name <=> a.name
                } else {
                    a.name <=> b.name
                }
            } else if (sortType == 'price') {
                if (sortOrder?.toLowerCase() == "desc") {
                    b.price <=> a.price
                } else {
                    a.price <=> b.price
                }
            }
        })
    }
}
