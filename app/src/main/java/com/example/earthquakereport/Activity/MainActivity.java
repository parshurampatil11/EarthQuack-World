package com.example.earthquakereport.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.earthquakereport.JsonFetch.ExtractFeature;
import com.example.earthquakereport.ConnectAll.Model;
import com.example.earthquakereport.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<Model> arrayList;
    private final String USGS_DATA ="https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&orderby=time&limit=20";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recycleV);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        arrayList = new ArrayList<>();
        ExtractFeature extractFeatureClass = new ExtractFeature(this,USGS_DATA,arrayList,recyclerView);
        extractFeatureClass.extractFeature();

    }

}