package com.example.statethefacts;

import com.github.mikephil.charting.formatter.ValueFormatter;


/**
 *  This class will format a number as a whole number in a string
 *
 * @author Gene Higgins
 * @since 12/1/2020
 */
public class WholeNumberValueFormatter extends ValueFormatter {

    @Override
    public String getFormattedValue(float value) {
        return String.format("%.0f", value);
    }
}
