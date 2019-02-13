package com.example.geniusplazatest.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.geniusplazatest.R;

public class UserAdapter extends ArrayAdapter<String> {

    public UserAdapter(@NonNull Context context, @NonNull String[] values) {
        super(context, R.layout.row_user_layout, values);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(getContext());

        View view = inflater.inflate(R.layout.row_user_layout, parent, false);

        String user = getItem(position);

        TextView firstName = (TextView) view.findViewById(R.id.textViewFirstName);
        firstName.setText(user.substring(0, user.indexOf(" ")));

        TextView lastName = (TextView) view.findViewById(R.id.textViewLastName);
        lastName.setText(user.substring(user.indexOf(" ") + 1));

        return view;
    }
}
