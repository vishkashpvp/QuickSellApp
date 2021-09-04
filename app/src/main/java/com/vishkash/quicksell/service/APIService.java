package com.vishkash.quicksell.service;

import android.content.Context;

import androidx.annotation.NonNull;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.vishkash.quicksell.contract.Product;
import com.vishkash.quicksell.contract.ProductsResponse;

import java.util.ArrayList;
import java.util.List;

public class APIService {
    volatile boolean isDataFetched = false;
    Context context;
    private List<Product> apiResponse;
    private Exception apiException = null;
    String url;

    public APIService(Context context, String url) {
        this.context = context;
        this.url = url;
    }

    public ProductsResponse getDataFromAPI() {
        RequestQueue queue = Volley.newRequestQueue(context);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        apiResponse = getProducts(response);
                        isDataFetched = true;
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                isDataFetched = true;
                apiException = new Exception("api error");
            }
        });

        queue.add(stringRequest);

        while (!isDataFetched) ;
        return new ProductsResponse(apiResponse, apiException);
    }

    private List<Product> getProducts(String dataFromAPI) {
        Gson g = new Gson();
        String[] productIDs = g.fromJson(dataFromAPI, String[].class);

        List<Product> products = new ArrayList<>();
        for (String productId : productIDs) {
            products.add(new Product(productId));
        }

        return products;
    }
}
