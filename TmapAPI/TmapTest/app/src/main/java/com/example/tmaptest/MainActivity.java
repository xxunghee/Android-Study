package com.example.tmaptest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.skt.Tmap.TMapView;

public class MainActivity extends AppCompatActivity {
    private String API_KEY = "enter your API key";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TMapView tmapview = new TMapView(this);
        tmapview.setSKTMapApiKey(API_KEY);
    }
}