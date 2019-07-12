package com.example.abercrombie.activity;

import android.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.abercrombie.R;
import com.example.abercrombie.adapter.PromoAdapter;

import com.example.abercrombie.model.Promotion;
import com.example.abercrombie.model.PromotionContent;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ArrayList<Promotion> promos = new ArrayList<>();
    private RequestQueue mQueue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final ActionBar actionBar = getActionBar();

        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }


        // Using Volley library to parse Json data
        mQueue = Volley.newRequestQueue(this);

        downloadPromos();
        /*Creating the instance of the School adapter*/
        PromoAdapter promoAdapter = new PromoAdapter(this, promos);

        // Defining the recycler view
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Attaching the promo adapter to recycler view in order to populate the JSON Parsed data
        recyclerView.setAdapter(promoAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

    }




    /**
     * Downloading promos, and notifying the adapter when the list is downloaded.
     */
    private void downloadPromos() {

        // json object response url
        String url = "https://www.abercrombie.com/anf/nativeapp/qa/codetest/codeTest_exploreData.json";
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        // Using Try catch to catch any exception while parsing
                        try {
                            // Iterating for loop to the end of the parsed arrays and adding them to promos arraylist in every iteration
                            //JSONArray responseJSONArray = response.getJSONArray(0);
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject promo = response.getJSONObject(i);

                                //if (response.length()>0){

                                    String title = promo.optString("title");
                                    String backGroundImage = promo.optString("backgroundImage");
                                    String promoMsg = promo.optString("promoMessage");
                                    String topDesc = promo.optString("topDescription");
                                    String bottomDesc = promo.optString("bottomDescription");

                                    ArrayList<PromotionContent> contectList = null;


                                    if (promo.optJSONArray("content") != null) {

                                        contectList = new Gson().fromJson(promo.optJSONArray("content").toString(), new TypeToken<List<PromotionContent>>() {
                                        }.getType());
                                    }
                                    // add the parsed array to arraylist

                                    promos.add(new Promotion(title,backGroundImage,contectList,promoMsg,topDesc,bottomDesc));

                                //}
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        // Using progress bar which shows up if loading id slow
                        findViewById(R.id.progressBar).setVisibility(View.GONE);
                    }

                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();

                // Checking for possible errors while parsing and displaying the error message
                if (error instanceof NetworkError) {
                    errorMessage(getResources().getString(R.string.network_error_msg));

                } else if (error instanceof ServerError) {
                    errorMessage(getResources().getString(R.string.server_error_msg));

                } else if (error instanceof AuthFailureError) {
                    errorMessage(getResources().getString(R.string.network_error_msg));

                } else if (error instanceof ParseError) {
                    errorMessage(getResources().getString(R.string.parsing_error_msg));

                } else if (error instanceof NoConnectionError) {
                    errorMessage(getResources().getString(R.string.network_error_msg));

                } else if (error instanceof TimeoutError) {
                    errorMessage(getResources().getString(R.string.network_error_msg));

                }
            }
        });

        mQueue.add(request);




    }
    private void errorMessage(String errMsg){
        Toast.makeText(getApplicationContext(), errMsg,
                Toast.LENGTH_LONG).show();
        findViewById(R.id.progressBar).setVisibility(View.GONE); }
}
