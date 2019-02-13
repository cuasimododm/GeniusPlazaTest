package com.example.geniusplazatest.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.geniusplazatest.R;
import com.example.geniusplazatest.helpers.AsyncImageDownloader;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;

public class UserAdapter extends ArrayAdapter<JSONObject> {

    public UserAdapter(@NonNull Context context, @NonNull JSONObject[] values) {
        super(context, R.layout.row_user_layout, values);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(getContext());

        View view = inflater.inflate(R.layout.row_user_layout, parent, false);

        JSONObject user = getItem(position);

        try {
            TextView firstName = (TextView) view.findViewById(R.id.textViewFirstName);
            firstName.setText(user.getString("first_name"));

            TextView lastName = (TextView) view.findViewById(R.id.textViewLastName);
            lastName.setText(user.getString("last_name"));

            ImageView avatarImage = (ImageView) view.findViewById(R.id.imageViewAvatar);
            new AsyncImageDownloader(avatarImage).execute(user.getString("avatar"));


        } catch (JSONException e) {
            Log.e("GeniusPlaza: ", "UserAdapter " + e.getMessage());
            e.printStackTrace();
        }


        return view;
    }
}
