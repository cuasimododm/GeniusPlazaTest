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
import com.example.geniusplazatest.model.ModelUser;

public class UserAdapter extends ArrayAdapter<ModelUser> {

    public UserAdapter(@NonNull Context context, @NonNull ModelUser[] values) {
        super(context, R.layout.row_user_layout, values);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(getContext());

        View view = inflater.inflate(R.layout.row_user_layout, parent, false);

        ModelUser user = getItem(position);

        TextView firstName = (TextView) view.findViewById(R.id.textViewFirstName);
        firstName.setText(user.getFirstName());

        TextView lastName = (TextView) view.findViewById(R.id.textViewLastName);
        lastName.setText(user.getLastName());

        return view;
    }
}
