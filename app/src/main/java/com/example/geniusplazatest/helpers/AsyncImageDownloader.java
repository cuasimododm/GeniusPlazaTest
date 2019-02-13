package com.example.geniusplazatest.helpers;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.io.InputStream;

public class AsyncImageDownloader extends AsyncTask<String, Void, Bitmap> {

    private ImageView imageView;

    public AsyncImageDownloader(ImageView imageView)
    {
        this.imageView = imageView;
    }


    @Override
    protected Bitmap doInBackground(String... urls) {
        String imgUrl = urls[0];
        Bitmap image = null;

        try {
            InputStream in = new java.net.URL(imgUrl).openStream();
            image = BitmapFactory.decodeStream(in);

        } catch (Exception e) {
            Log.e("Error Message", e.getMessage());
            e.printStackTrace();
        }

        return image;
    }

    protected void onPostExecute(Bitmap result)
    {
        imageView.setImageBitmap(result);
    }
}
