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

    public void prikaziPridelek(View view) {
        url = "https://slovenskatrta-is.azurewebsites.net/api/v1/Pridelek";
        if (view != null) {
            JsonArrayRequest request = new JsonArrayRequest(url, jsonArrayListenerPridelek, errorListener)
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

    public void prikaziOdkup(View view) {
        url = "https://slovenskatrta-is.azurewebsites.net/api/v1/Odkup";
        if (view != null) {
            JsonArrayRequest request = new JsonArrayRequest(url, jsonArrayListenerOdkup, errorListener)
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
                trte.setMovementMethod(new ScrollingMovementMethod());
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
                trte.setMovementMethod(new ScrollingMovementMethod());
            }
        }
    };

    private Response.Listener<JSONArray> jsonArrayListenerPridelek = new Response.Listener<JSONArray>() {
        @Override
        public void onResponse(JSONArray response) {
            ArrayList<String> data = new ArrayList<>();

            for (int i = 0; i < response.length(); i++) {
                try {
                    JSONObject object = response.getJSONObject(i);
                    String id = object.getString("trteId");
                    String id2 = object.getString("vinogradId");
                    String kolicina = object.getString("kolicina");
                    String hektar = object.getString("kolNaHa");
                    String teza = object.getString("kgNaTrto");
                    String leto = object.getString("letoMeritve");

                    data.add(id + " " + id2 + " " + kolicina + " " + hektar + " " + teza + " " +leto);

                } catch (JSONException e) {
                    e.printStackTrace();
                    return;

                }

                trte.setText("");

                for (String row : data) {
                    String currentText = trte.getText().toString();
                    trte.setText(currentText + "\n\n" + row);
                }
                trte.setMovementMethod(new ScrollingMovementMethod());
            }
        }
    };

    private Response.Listener<JSONArray> jsonArrayListenerOdkup = new Response.Listener<JSONArray>() {
        @Override
        public void onResponse(JSONArray response) {
            ArrayList<String> data = new ArrayList<>();

            for (int i = 0; i < response.length(); i++) {
                try {
                    JSONObject object = response.getJSONObject(i);
                    String id = object.getString("pridelekId");
                    String kolicina = object.getString("kolicina");
                    String cenaNaKg = object.getString("cenaNaKg");
                    String leto = object.getString("letoMeritve");

                    data.add(id + " " + kolicina + " " + cenaNaKg + " " +leto);

                } catch (JSONException e) {
                    e.printStackTrace();
                    return;

                }

                trte.setText("");

                for (String row : data) {
                    String currentText = trte.getText().toString();
                    trte.setText(currentText + "\n\n" + row);
                }
                trte.setMovementMethod(new ScrollingMovementMethod());
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
        finish();
    }

    public void addVinogradiActivity (View view) {
        Intent intent = new Intent(this,AddVinogradiActivity.class);
        String message = "Dodaj vinograd v seznam.";
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
        finish();
    }

    public void addPridelekActivity (View view) {
        Intent intent = new Intent(this,AddPridelekActivity.class);
        String message = "Dodaj pridelek v seznam.";
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
        finish();
    }

    public void addOdkupActivity (View view) {
        Intent intent = new Intent(this,AddOdkupActivity.class);
        String message = "Dodaj odkup v seznam.";
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
        finish();
    }
}