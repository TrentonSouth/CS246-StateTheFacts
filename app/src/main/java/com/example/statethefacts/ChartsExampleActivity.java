package com.example.statethefacts;

import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;
import java.util.List;

public class ChartsExampleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_charts_example);

        LineChart chart = findViewById(R.id.chart);


        LineDataSet dataSet = new LineDataSet(getDataSet(), "Correct Answers");
        dataSet.setColor(Color.BLACK);
        dataSet.setValueTextColor(Color.BLUE);
        dataSet.setValueTextSize(9f);
        dataSet.setValueFormatter(new WholeNumberValueFormatter());

        LineData lineData = new LineData(dataSet);
        chart.setData(lineData);

        Description description = new Description();
        description.setText("Example Line Chart");
        description.setTextSize(16f);
        chart.setDescription(description);

        chart.setDrawGridBackground(true);

        chart.animateXY(2000,2000);

        chart.invalidate();
    }

    private List<Entry> getDataSet(){
        List<Entry> entries = new ArrayList<>();

        entries.add(new Entry(0,0));
        entries.add(new Entry(1,20));
        entries.add(new Entry(2,18));
        entries.add(new Entry(3,23));
        entries.add(new Entry(4,24));
        entries.add(new Entry(5,17));
        entries.add(new Entry(6,30));
        entries.add(new Entry(7,33));
        entries.add(new Entry(8,15));
        entries.add(new Entry(9,38));
        entries.add(new Entry(10,40));

        return entries;
    }

}