package com.example.geniusplazatest;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.geniusplazatest.adapters.UserAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ProgressBar spinner;

    // Navigation bar listener
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_new:
                        item.setEnabled(false);
                        Intent intent = new Intent(getApplicationContext(), NewUserActivity.class);
                        startActivity(intent);
                        return true;
                }
                return false;
            }
    };
    //..

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Navigation setup
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navigation.getMenu().getItem(0).setEnabled(false);
        // ..

        // Spinner setup
        spinner = (ProgressBar)findViewById(R.id.progressBar);
        spinner.setVisibility(View.GONE);
        //..


        // Ask for users list
        RequestUsersList();


    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.getMenu().getItem(1).setEnabled(true);
    }

    private void RequestUsersList()
    {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = getResources().getString(R.string.api_url_test);;

        JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        ParseResponse(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError e) {
                Log.e("GeniusPlaza: ", "MainActivity " + e.getMessage());
                e.printStackTrace();
            }
        });
        queue.add(jsonRequest);

        // Starts the spinner animation
        spinner.setVisibility(View.VISIBLE);
    }

    private void ParseResponse(JSONObject response)
    {
        try{
            JSONArray data = response.getJSONArray("data");
            ArrayList<JSONObject> users = new ArrayList<JSONObject>();

            for (int i = 0; i < data.length(); ++i)
            {
                JSONObject jsonUser = data.getJSONObject(i);
                users.add(jsonUser);
            }

            CreateListView(users);


        } catch (JSONException e) {
            Log.e("GeniusPlaza: ", "MainActivity " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void CreateListView(ArrayList<JSONObject> users)
    {
        JSONObject[] values = new JSONObject[users.size()];
        values = users.toArray(values);

        // Populates the ListView
        ListAdapter adapter = new UserAdapter(MainActivity.this, values);
        ListView listView = (ListView) findViewById(R.id.users_list_view);
        listView.setAdapter(adapter);
        //..

        // Stops the spinner animation
        spinner.setVisibility(View.GONE);
    }
}
