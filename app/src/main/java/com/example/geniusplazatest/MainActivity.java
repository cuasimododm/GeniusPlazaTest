package com.example.geniusplazatest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.android.volley.*;

import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.geniusplazatest.adapters.UserAdapter;
import com.example.geniusplazatest.model.ModelUser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        RequestQueue queue = Volley.newRequestQueue(this);
        //String url = "https://reqres.in/api/users?delay=3";
        String url = "https://reqres.in/api/users";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            JSONObject root = new JSONObject(response);
                            JSONArray data = root.getJSONArray("data");
                            ArrayList<ModelUser> users = new ArrayList<ModelUser>();

                            for (int i = 0; i < data.length(); ++i)
                            {
                                JSONObject jsonUser = data.getJSONObject(i);

                                ModelUser user = new ModelUser(
                                        jsonUser.getString("first_name"),
                                        jsonUser.getString("last_name"),
                                        jsonUser.getString("avatar"));
                                users.add(user);
                            }

                            ModelUser[] values = new ModelUser[users.size()];
                            values = users.toArray(values);

                            ListAdapter adapter = new UserAdapter(MainActivity.this, values);
                            ListView listView = (ListView) findViewById(R.id.users_list_view);
                            listView.setAdapter(adapter);

                        } catch (JSONException e) {
                            Log.e("GeniusPlaza: ", e.getMessage());
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError e) {
                Log.e("GeniusPlaza: ", e.getMessage());
                e.printStackTrace();
            }
        });
        queue.add(stringRequest);
    }
}
