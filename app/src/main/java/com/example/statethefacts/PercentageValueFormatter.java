package com.example.statethefacts;

import com.github.mikephil.charting.formatter.ValueFormatter;

public class PercentageValueFormatter extends ValueFormatter {

    @Override
    public String getFormattedValue(float value) {
        return String.format("%.0f", value) + "%";

    }
}