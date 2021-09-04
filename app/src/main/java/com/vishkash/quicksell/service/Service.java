package com.vishkash.quicksell.service;

import android.content.Context;

import androidx.annotation.NonNull;

import com.google.firebase.database.FirebaseDatabase;
import com.google.gson.Gson;
import com.vishkash.quicksell.MainActivity;
import com.vishkash.quicksell.R;
import com.vishkash.quicksell.contract.Product;
import com.vishkash.quicksell.contract.ProductsResponse;

import java.util.ArrayList;
import java.util.List;


public class Service {
    Context context;
    FirebaseDatabase firebaseDatabase;

    public Service(Context context, FirebaseDatabase firebaseDatabase) {
        this.context = context;
        this.firebaseDatabase = firebaseDatabase;
    }

    public ProductsResponse getData(){

//        ProductsAPIResponse dataFromAPI = apiService.getDataFromAPI();
//        if (dataFromAPI.error != null){
//            return new ProductsResponse(null, dataFromAPI.error);
//        }
//        List<Product> products = getProducts(dataFromAPI);

        MainActivity main = (MainActivity) context;
//        main.onFirebaseDataRefresh(products);

//        FirebaseService firebaseService = new FirebaseService(context, firebaseDatabase, productIDs);
//        ProductsResponse firebaseProductResponse = firebaseService.fetchData();
//        if (firebaseProductResponse.error != null){
//            return new ProductsResponse(null, firebaseProductResponse.error);
//        }
//
//        return firebaseProductResponse;
        return null;
    }


}
