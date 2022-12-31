package com.example.slovenskatrtaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private RequestQueue requestQueue;
    private TextView trte;
    private String url;
    public static final String EXTRA_MESSAGE = "slovenskatrtaapp.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        requestQueue = Volley.newRequestQueue(getApplicationContext());
        trte = (TextView) findViewById(R.id.trte);
    }

    public void prikaziTrte(View view) {
        url = "https://slovenskatrta-is.azurewebsites.net/api/v1/Trte";
        if (view != null) {
            JsonArrayRequest request = new JsonArrayRequest(url, jsonArrayListener, errorListener)
            {
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError
                {
                    Map<String, String> params = new HashMap<String, String>();
                    //parametri:  kaj isce    kljuc
                    params.put("ApiKey","SecretKey");
                    return params;
                }
            };
            requestQueue.add(request);
        }
    }

    public void prikaziVinograde(View view) {
        url = "https://slovenskatrta-is.azurewebsites.net/api/v1/Vinogradi";
        if (view != null) {
            JsonArrayRequest request = new JsonArrayRequest(url, jsonArrayListenerVinograd, errorListener)
            {
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError
                {
                    Map<String, String> params = new HashMap<String, String>();
                    //parametri:  kaj isce    kljuc
                    params.put("ApiKey","SecretKey");
                    return params;
                }
            };
            requestQueue.add(request);
        }
    }

    private Response.Listener<JSONArray> jsonArrayListener = new Response.Listener<JSONArray>() {
        @Override
        public void onResponse(JSONArray response) {
            ArrayList<String> data = new ArrayList<>();

            for (int i = 0; i < response.length(); i++) {
                try {
                    JSONObject object = response.getJSONObject(i);
                    String id = object.getString("trteId");
                    String sorta = object.getString("sorta");

                    data.add(id + " " + sorta);

                } catch (JSONException e) {
                    e.printStackTrace();
                    return;

                }

                trte.setText("");

                for (String row : data) {
                    String currentText = trte.getText().toString();
                    trte.setText(currentText + "\n\n" + row);
                }

            }
        }
    };

    private Response.Listener<JSONArray> jsonArrayListenerVinograd = new Response.Listener<JSONArray>() {
        @Override
        public void onResponse(JSONArray response) {
            ArrayList<String> data = new ArrayList<>();

            for (int i = 0; i < response.length(); i++) {
                try {
                    JSONObject object = response.getJSONObject(i);
                    String id = object.getString("vinogradiId");
                    String id2 = object.getString("trteId");
                    String povrsina = object.getString("povrsina");
                    String st = object.getString("stTrt");
                    String leto = object.getString("letoMeritve");

                    data.add(id + " " + id2 + " " + povrsina + " " + st + " " + leto);

                } catch (JSONException e) {
                    e.printStackTrace();
                    return;

                }

                trte.setText("");

                for (String row : data) {
                    String currentText = trte.getText().toString();
                    trte.setText(currentText + "\n\n" + row);
                }

            }
        }
    };

    private Response.ErrorListener errorListener = new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            Log.d("REST error", error.getMessage());
        }
    };

    public void addTrtaActivity (View view) {
        Intent intent = new Intent(this,AddTrtaActivity.class);
        String message = "Dodaj trto v seznam.";
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }

    public void addVinogradiActivity (View view) {
        Intent intent = new Intent(this,AddVinogradActivity.class);
        String message = "Dodaj vinograd v seznam.";
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }
}