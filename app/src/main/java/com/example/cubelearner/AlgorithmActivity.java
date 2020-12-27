package com.example.cubelearner;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class AlgorithmActivity extends AppCompatActivity {

    private String type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_algorithm);
        type = (String) getIntent().getSerializableExtra("type");
        TextView tv = findViewById(R.id.textView);
        tv.setText(type);
    }
}