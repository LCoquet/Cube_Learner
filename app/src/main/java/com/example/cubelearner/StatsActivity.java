package com.example.cubelearner;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;

import com.example.cubelearner.data.Time;
import com.example.cubelearner.database.TimeTable;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class StatsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);

        BarChart barChart = findViewById(R.id.barChart);

        ArrayList<BarEntry> stats = new ArrayList<>();

        ArrayList<Time> times = new ArrayList<>();
        TimeTable timeTable = new TimeTable(this);
        times = timeTable.getAllTime("3*3");

        int n = times.size();

        for (int i = 0; i<n; i++){
            long precise = times.get(i).getPrecise();
            stats.add(new BarEntry(i+1, precise));
        }

        BarDataSet barDataSet =new BarDataSet(stats, "Statistics");
        barDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        barDataSet.setValueTextColor(Color.BLACK);

        BarData barData = new BarData(barDataSet);

        barChart.setData(barData);
        barChart.getDescription().setText("Your performances");

    }
}