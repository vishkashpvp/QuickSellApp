package com.vishkash.quicksell;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.FirebaseDatabase;
import com.vishkash.quicksell.contract.Product;
import com.vishkash.quicksell.service.APIService;
import com.vishkash.quicksell.service.FirebaseService;
import com.vishkash.quicksell.task.APIDataFetcher;

import java.util.List;


public class MainActivity extends AppCompatActivity {

    ListView listView;
    FirebaseDatabase firebaseDatabase;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();
        listView = findViewById(R.id.productsList);
        progressBar = findViewById(R.id.pb);
        firebaseDatabase = FirebaseDatabase.getInstance();

        String API_URL = getResources().getString(R.string.api_url);
        APIService apiService = new APIService(this, API_URL);
        FirebaseService firebaseService = new FirebaseService(this, firebaseDatabase);

        APIDataFetcher d = new APIDataFetcher(this, apiService, firebaseService, listView, progressBar);
        d.execute();
    }

    public void onDataChange(List<Product> products){
        listView.setAdapter(new ProductAdapter(this, R.layout.product_adapter, products));
    }

    public void startProgressbar(){
        progressBar.setVisibility(View.VISIBLE);
    }

    public void stopProgressbar(){
        progressBar.setVisibility(View.INVISIBLE);
    }

}


