package com.example.jsonwithvolly;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Method;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private ProgressBar progressBar;
    private EditText tvname,tvphone,tvemail,tvaddress;
    private Button loadbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = findViewById(R.id.progressBar);
        tvname = findViewById(R.id.name);
        tvphone = findViewById(R.id.phone);
        tvemail = findViewById(R.id.email);
        tvaddress = findViewById(R.id.address);
        loadbtn = findViewById(R.id.loadbtn);

        loadbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                progressBar.setVisibility(View.VISIBLE);

                RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
                String url = "https://rakibhossen.000webhostapp.com/apps/info.json";

                // Request a string response from the provided URL.
                StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                // Display the first 500 characters of the response string.
                                progressBar.setVisibility(View.GONE);
                                Log.d("onResponse: ",response);

                                try {
                                    JSONObject jsonObject = new JSONObject(response);
                                    String name = jsonObject.getString("name");
                                    String phone = jsonObject.getString("phone");
                                    String email = jsonObject.getString("email");
                                    String address = jsonObject.getString("address");

                                    tvname.setText(name);
                                    tvphone.setText(phone);
                                    tvemail.setText(email);
                                    tvaddress.setText(address);

                                }catch (Exception e){
                                 e.printStackTrace();
                                }
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, "file error", Toast.LENGTH_SHORT).show();
                    }
                });

                // Add the request to the RequestQueue.
                queue.add(stringRequest);

            }
        });

    }
}