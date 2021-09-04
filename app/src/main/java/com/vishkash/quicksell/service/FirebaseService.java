package com.vishkash.quicksell.service;

import android.content.Context;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.vishkash.quicksell.MainActivity;
import com.vishkash.quicksell.contract.Product;

import java.util.List;

public class FirebaseService {
    Context context;
    FirebaseDatabase firebaseDatabase;
    final String titleNode = "product-name", descriptionNode = "product-desc", priceNode = "product-price", imageNode = "product-image";


    public FirebaseService(Context context, FirebaseDatabase firebaseDatabase) {
        this.context = context;
        this.firebaseDatabase = firebaseDatabase;
    }

    public void fetchDataAndUpdateUI(final List<Product> products){

        firebaseDatabase.getReference().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    populateData(products, snapshot.child(titleNode), snapshot.child(priceNode), snapshot.child(descriptionNode), snapshot.child(imageNode));
                    MainActivity mainActivity = (MainActivity) context;
                    mainActivity.stopProgressbar();
                    mainActivity.onDataChange(products);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }

    private void populateData(List<Product> products, DataSnapshot productNameNode, DataSnapshot productPriceNode, DataSnapshot productDescriptionNode, DataSnapshot productImageNode) {

        String name, image, description, productId;
        Integer price;

        for (Product product : products) {
            productId = product.getId();

            DataSnapshot dataSnapshot = productNameNode.child(productId);
            name = dataSnapshot.getValue(String.class);
            product.setName(name);

            dataSnapshot = productPriceNode.child(productId);
            price = dataSnapshot.getValue(Integer.class);
            product.setPrice(price);

            dataSnapshot = productImageNode.child(productId);
            image = dataSnapshot.getValue(String.class);
            product.setImageLink(image);

            dataSnapshot = productDescriptionNode.child(productId);
            description = dataSnapshot.getValue(String.class);
            product.setDescription(description);

        }
    }
}
