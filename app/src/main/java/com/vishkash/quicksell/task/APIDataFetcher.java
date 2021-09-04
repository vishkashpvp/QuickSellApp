package com.vishkash.quicksell.task;

import android.app.Activity;
import android.os.AsyncTask;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.vishkash.quicksell.MainActivity;
import com.vishkash.quicksell.contract.ProductsResponse;
import com.vishkash.quicksell.service.APIService;
import com.vishkash.quicksell.service.FirebaseService;

public class APIDataFetcher extends AsyncTask<Void, Void, ProductsResponse> {

    Activity activity;
    APIService service;
    FirebaseService firebaseService;
    ListView listView;
    ProgressBar progressBar;

    public APIDataFetcher(Activity activity, APIService service, FirebaseService firebaseService, ListView listView, ProgressBar progressBar) {
        this.activity = activity;
        this.service = service;
        this.firebaseService = firebaseService;
        this.listView = listView;
        this.progressBar = progressBar;
    }

    @Override
    protected ProductsResponse doInBackground(Void... voids) {
        return service.getDataFromAPI();
    }

    @Override
    protected void onPostExecute(ProductsResponse productResponse) {
        super.onPostExecute(productResponse);
        MainActivity mainActivity = (MainActivity) activity;

        if (productResponse.error == null){
            mainActivity.onDataChange(productResponse.products);
            firebaseService.fetchDataAndUpdateUI(productResponse.products);
        }else{
            mainActivity.stopProgressbar();
        }
    }
}
