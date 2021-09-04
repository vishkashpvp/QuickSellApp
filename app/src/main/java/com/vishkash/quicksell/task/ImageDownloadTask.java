package com.vishkash.quicksell.task;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class ImageDownloadTask extends AsyncTask<Void, Void, Bitmap> {
    private String url;
    Context activity;
    Bitmap[] bitmaps;
    int position;
    ImageView imageView;


    public ImageDownloadTask(String url, Context activity, Bitmap[] bitmaps, int position, ImageView imageView) {
        this.url = url;
        this.activity = activity;
        this.bitmaps = bitmaps;
        this.position = position;
        this.imageView = imageView;
    }

    @Override
    protected Bitmap doInBackground(Void... params) {
        try {
            URL urlConnection = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) urlConnection.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            return myBitmap;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Bitmap result) {
        super.onPostExecute(result);
        bitmaps[position] = result;
        if (result != null)
            imageView.setImageBitmap(result);
    }

}
