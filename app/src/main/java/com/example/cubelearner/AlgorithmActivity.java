package com.example.cubelearner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.example.cubelearner.data.Algorithm;
import com.example.cubelearner.data.AlgorithmAdapter;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/*
 * Shows all the OLL or PLL algorithms.
 */

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

    //Finds all the algorithm from the ressources.
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

}