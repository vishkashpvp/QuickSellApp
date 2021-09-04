package com.vishkash.quicksell.contract;


import java.util.List;

public class ProductsResponse {
    public List<Product> products;
    public Exception error;

    public ProductsResponse(List<Product> products, Exception error) {
        this.products = products;
        this.error = error;
    }
}
