package com.example.statethefacts;

import com.github.mikephil.charting.formatter.ValueFormatter;


public class WholeNumberValueFormatter extends ValueFormatter {

    @Override
    public String getFormattedValue(float value) {
        return String.format("%.0f", value);
    }
}
