package com.example.geniusplazatest;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class NewUserActivity extends AppCompatActivity {


    private EditText firstName;
    private EditText lastName;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_users:
                    item.setEnabled(false);
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user);

        // Navigation setup
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navigation.getMenu().getItem(1).setEnabled(false);
        //..

        firstName = (EditText)findViewById(R.id.edit_text_first_name);
        lastName = (EditText)findViewById(R.id.edit_text_last_name);
    }

    public void onNewUserClicked(View view) {

        // If name fields don't have data return
        if (firstName.getText().toString().length() == 0 && lastName.getText().toString().length() == 0)
            return;


        JSONObject newUser = new JSONObject();
        try {
            newUser.put(getResources().getString(R.string.key_json_first_name), firstName.getText());
            newUser.put(getResources().getString(R.string.key_json_last_name), lastName.getText());

            AddNewUser(newUser);

        } catch (JSONException e) {
            Log.e("GeniusPlaza", "NewUserActivity " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void AddNewUser(JSONObject newUser)
    {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = getResources().getString(R.string.api_url_test);
        JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.POST, url, newUser,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("GeniusPlaza: ", "NewUserActivity " + response.toString());
                        firstName.setText("");
                        lastName.setText("");
                        Toast.makeText(NewUserActivity.this, "New User Created", Toast.LENGTH_SHORT).show();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError e) {
                Toast.makeText(NewUserActivity.this, "Error", Toast.LENGTH_SHORT).show();
                Log.d("GeniusPlaza: ", "NewUserActivity " + e.getLocalizedMessage());
                e.printStackTrace();;
            }
        });
        queue.add(jsonRequest);
    }
}
