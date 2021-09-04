package com.vishkash.quicksell;

import android.app.Activity;
import android.graphics.Bitmap;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.vishkash.quicksell.contract.Product;
import com.vishkash.quicksell.task.ImageDownloadTask;

import java.util.List;

public class ProductAdapter extends ArrayAdapter<Product> {

    Bitmap[] bitmaps;
    Activity activity;
    List<Product> products;
    int resource;

    public ProductAdapter(Activity activity, int resource, List<Product> products) {
        super(activity, resource, products);
        this.activity = activity;
        this.resource = resource;
        this.products = products;
        bitmaps = new Bitmap[products.size()];
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = activity.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.product_adapter, null, true);

        ImageView imageView = rowView.findViewById(R.id.productImage);
        TextView productTitle = rowView.findViewById(R.id.productTitle);
        TextView productDescription = rowView.findViewById(R.id.productDescription);
        TextView productPrice = rowView.findViewById(R.id.productPrice);

        String title = products.get(position).getName();
        if (title == null) {
            title = "No Product Title";
        }

        String description = products.get(position).getDescription();
        if (description == null) {
            description = "No product description";
        }

        Integer price = products.get(position).getPrice();

        String priceTag = "    -";
        if (price != null) {
            priceTag = "₹ " + price;
        }

        if (products.get(position).getImageLink() != null) {
            if (bitmaps[position] == null){
                new ImageDownloadTask(products.get(position).getImageLink(), activity, bitmaps, position, imageView).execute();
            }
            else{
                imageView.setImageBitmap(bitmaps[position]);
            }
        }

        productTitle.setText(title);
        productPrice.setText(priceTag);
        productDescription.setText(description);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int width = displayMetrics.widthPixels / 5;

        ViewGroup.LayoutParams layoutParams = imageView.getLayoutParams();

        layoutParams.width = width;
        layoutParams.height = width;
        imageView.setLayoutParams(layoutParams);

        return rowView;
    }

}

