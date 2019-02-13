package com.example.geniusplazatest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListAdapter;
import android.widget.ListView;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://reqres.in/api/users";
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

        ListAdapter adapter = new UserAdapter(MainActivity.this, values);
        ListView listView = (ListView) findViewById(R.id.users_list_view);
        listView.setAdapter(adapter);
    }
}
