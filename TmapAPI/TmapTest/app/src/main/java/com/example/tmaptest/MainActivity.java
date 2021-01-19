package com.example.tmaptest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.example.tmaptest.databinding.ActivityMainBinding;
import com.skt.Tmap.TMapView;

public class MainActivity extends AppCompatActivity {
    private String APP_KEY = "enter your App Key";
    private String SECRET_KEY = "enter your Secret Key";
    ActivityMainBinding binding;
    TMapView tmapview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        tmapview = new TMapView(this);
        tmapview.setSKTMapApiKey(APP_KEY);

        binding.Tmapview.addView(tmapview);
    }
}