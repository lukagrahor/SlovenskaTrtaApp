package com.example.slovenskatrtaapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class AddVinogradActivity extends AppCompatActivity {
    private TextView status;
    private EditText id;
    private EditText povrsina;
    private EditText st;
    private EditText leto;
    String string_id;
    int koncni_id;
    public static final String EXTRA_MESSAGE = "slovenskatrtaapp.MESSAGE";

    private RequestQueue requestQueue;
    private String url = "https://slovenskatrta-is.azurewebsites.net/api/v1/Vinogradi";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_vinograd);
        id = (EditText) findViewById(R.id.teTrteId);
        povrsina = (EditText) findViewById(R.id.tePovrsina);
        st = (EditText) findViewById(R.id.teStTrt);
        leto = (EditText) findViewById(R.id.teLeto);
        status = (TextView) findViewById(R.id.status);
        requestQueue = Volley.newRequestQueue(getApplicationContext());
    }

    public void addTrta(View view){
        this.status.setText("Posting to " + url);
        try {
            JSONObject jsonBody = new JSONObject();
            jsonBody.put("trteId", id.getText());
            jsonBody.put("povrsina", povrsina.getText());
            jsonBody.put("stTrt", st.getText());
            jsonBody.put("letoMeritve", leto.getText());

            final String mRequestBody = jsonBody.toString();

            status.setText(mRequestBody);

            StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Log.i("LOG_VOLLEY", response);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e("LOG_VOLLEY", error.toString());
                }
            }
            ) {
                @Override
                public String getBodyContentType() {
                    return "application/json; charset=utf-8";
                }
                @Override
                public byte[] getBody() throws AuthFailureError {
                    try {
                        return mRequestBody == null ? null : mRequestBody.getBytes("utf-8");
                    } catch (UnsupportedEncodingException uee) {
                        VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", mRequestBody, "utf-8");
                        return null;
                    }
                }
                @Override
                protected Response<String> parseNetworkResponse(NetworkResponse response) {
                    String responseString = "";
                    if (response != null) {
                        responseString = String.valueOf(response.statusCode);
                        status.setText(responseString);
                    }
                    return Response.success(responseString, HttpHeaderParser.parseCacheHeaders(response));
                }

                @Override
                public Map<String, String> getHeaders() throws AuthFailureError
                {
                    Map<String, String> params = new HashMap<String, String>();
                    //parametri:  kaj isce    kljuc
                    params.put("ApiKey","SecretKey");
                    params.put("Content-Type","application/json");
                    return params;
                }

            };

            requestQueue.add(stringRequest);

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public void returnBack (View view) {
        Intent intent = new Intent(this,MainActivity.class);
        String message = "Vrni se nazaj.";
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }
}