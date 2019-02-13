package com.example.geniusplazatest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.geniusplazatest.adapters.UserAdapter;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        String[] users = {"Pedro Picapiedra", "Bilma Picapiedra", "Pablo Marmol"};

        ListAdapter adapter = new UserAdapter(this, users);
        ListView listView = (ListView) findViewById(R.id.users_list_view);
        listView.setAdapter(adapter);

    }
}
