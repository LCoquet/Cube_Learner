package com.example.cubelearner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.example.cubelearner.data.Algorithm;
import com.example.cubelearner.data.AlgorithmAdapter;
import com.example.cubelearner.databases.TimeTable;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class AlgorithmActivity extends AppCompatActivity {

    private String type;
    private ListView algorithmLV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_algorithm);
        type = getIntent().getStringExtra("type");
        algorithmLV = (ListView) findViewById(R.id.algorithmList);
        AlgorithmAdapter adapter = new AlgorithmAdapter(this, algorithmGenerator(type));
        algorithmLV.setAdapter(adapter);
    }

    private List<Algorithm> algorithmGenerator(String type){
        InputStream is = null;
        ArrayList<Algorithm> algorithms = new ArrayList<Algorithm>();
        switch(type){
            case "pll" : is = getResources().openRawResource(R.raw.pll);    break;
            case "oll" : is = getResources().openRawResource(R.raw.oll);    break;
        }
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            String line;
            String[] values;
            while((line = br.readLine()) != null){
                values = line.split(";");
                algorithms.add(new Algorithm(values[0], values[1]));
            }
        } catch(Exception e){
            e.printStackTrace();
        }
        return algorithms;
    }

    public void refreshAlgorithmColor(View v){
        v.findViewById(R.id.algorithmBackground).setBackgroundColor(ContextCompat.getColor(this, R.color.stopwatchRunning));
    }

}