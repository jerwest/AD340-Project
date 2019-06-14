package com.westfall.ad340project;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.HashMap;
import java.util.Map;

import static com.android.volley.VolleyLog.TAG;

public class TrafficCameraDetails {

    private String cameraLabel;
    private String imageUrl;
    private String type;
    private double[] coords;

    private Map<String, String> baseUrl = new HashMap<String, String>();


    public TrafficCameraDetails(String cameraLabel, String imageUrl, String type, double[] coords) {
        this.cameraLabel = cameraLabel;
        this.imageUrl = imageUrl;
        this.type = type;
        this.coords = coords;
    }

    public String getImageUrl() {
        return this.imageUrl;
    }

    public String getCameraLabel() {
        return this.cameraLabel;
    }

    public double[] getCoords() {
        return this.coords;
    }
}