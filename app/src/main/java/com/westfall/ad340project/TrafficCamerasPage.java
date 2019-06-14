package com.westfall.ad340project;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class TrafficCamerasPage extends AppCompatActivity {

    Context context;
    RecyclerView recyclerView;
    RecyclerView.Adapter recyclerViewAdapter;
    RecyclerView.LayoutManager recyclerViewLayoutManager;
    private static boolean WIFIconnected = false;
    private static boolean mobileConnected = false;
    List<TrafficCameraDetails> camera = new ArrayList<TrafficCameraDetails>();

    private static final String TAG = "Camera List";

    public void onCreate(Bundle savedInstances) {
        super.onCreate(savedInstances);
        setContentView(R.layout.activity_traffic_cameras_page);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        ((ActionBar) ab).setDisplayHomeAsUpEnabled(true);

        context = getApplicationContext();

        recyclerView = (RecyclerView) findViewById(R.id.camera_recyclerView);
        recyclerViewLayoutManager = new LinearLayoutManager(context);
        recyclerViewLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(recyclerViewLayoutManager);
        recyclerViewAdapter = new TrafficCamerasPage.CustomAdapter();
        recyclerView.setAdapter(recyclerViewAdapter);

        String cameraUrl = "http://brisksoft.us/ad340/traffic_cameras_merged.json";

        boolean connected = checkNetworkConnections();

        if (connected) {
            RequestQueue queue = Volley.newRequestQueue(this);
            JsonArrayRequest arrayRequest = new JsonArrayRequest(Request.Method.GET, cameraUrl, null, new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {
                    Log.d("Camera 1", response.toString());
                    try {
                        for (int i = 0; i < response.length(); i++) {
                            JSONObject individualCamera = response.getJSONObject(i);
                            double[] coords = {individualCamera.getDouble("ypos"), individualCamera.getDouble("xpos")};
                            TrafficCameraDetails cameras = new TrafficCameraDetails(
                                    individualCamera.getString("cameralabel"),
                                    individualCamera.getJSONObject("imageurl").getString("url"),
                                    individualCamera.getString("ownershipcd"),
                                    coords
                            );
                            camera.add(cameras);
                        }
                        recyclerViewAdapter.notifyDataSetChanged();
                    } catch (JSONException e) {
                        Log.d("Camera error", e.getMessage());
                    }
                }
            },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.d("JSON", "Error:" + error.getMessage());
                        }

                    });
            queue.add(arrayRequest);

        } else {
            Intent intent = new Intent(this, NoInternetConnection.class);
            startActivity(intent);
        }
    }

    public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {

        public class ViewHolder extends RecyclerView.ViewHolder {

            public TextView cameraLabel;
            public ImageView cameraImage;

            public ViewHolder(View v) {
                super(v);
                cameraLabel = v.findViewById(R.id.description1);
                cameraImage = v.findViewById(R.id.image1);
            }
        }

        @Override
        public CustomAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View item = getLayoutInflater().inflate(R.layout.activity_traffic_camera_details, parent, false);
            ViewHolder holder = new ViewHolder(item);
            return holder;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {

            holder.cameraLabel.setText(camera.get(position).getCameraLabel());
            String imageUrl = camera.get(position).getImageUrl();
            if (!imageUrl.isEmpty()) {
                Picasso.get().load(imageUrl).into(holder.cameraImage);
            }
        }

        @Override
        public int getItemCount() {

            return camera.size();
        }
    }

    public boolean checkNetworkConnections() {

        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {
            WIFIconnected = networkInfo.getType() == connectivityManager.TYPE_WIFI;
            mobileConnected = networkInfo.getType() == connectivityManager.TYPE_MOBILE;
            if (WIFIconnected) {
                Log.i("WIFI Connected", "Success");
                return true;
            } else if (mobileConnected) {
                Log.i("Mobile Connected", "Success");
                return true;
            }
        } else {
            Log.i("Connection:", "Not Connected");
            return false;
        }
        return false;
    }
}
