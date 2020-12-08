package com.example.statethefacts;

import android.graphics.Color;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HistoryPresenter {
    static final int MAX_CHART_ENTRIES = 10;
    static final int ANIMATION_DURATION = 2000;

    private final HistoryActivity activity;
    List<GameResult> gameResults;
    List<Entry> chartEntries;
    private int playAttempts;
    private double averageScore;

    public HistoryPresenter(HistoryActivity activity) {
        this.activity = activity;
        chartEntries = new ArrayList<>();
    }

    public void loadGameResult() {
        gameResults = new GameResult().getAllGames(activity);
        Collections.sort(gameResults, new SortByDate());
    }


    public void getStats() {
        List<Double> scores = getGameScores();
        getAverageScore(scores);
        getPlayAttempts();
        setViewControls();

        getChartEntries(scores);
        setupChart();
    }

    // Charts library information can be found at https://github.com/PhilJay/MPAndroidChart
    public double getAverageScoreEmail() {
        List<Double> scores = getGameScores();
        getAverageScore(scores);
        return averageScore;
    }

    public int getPlayAttemptsEmail() {
        getPlayAttempts();
        return playAttempts;
    }

    private void setupChart() {
        LineChart chart = activity.findViewById(R.id.chart);

        LineDataSet dataSet = new LineDataSet(chartEntries, "Correct Answers");
        dataSet.setColor(Color.BLACK);
        dataSet.setValueTextColor(Color.BLUE);
        dataSet.setValueTextSize(10f);
        dataSet.setValueFormatter(new PercentageValueFormatter());

        LineData lineData = new LineData(dataSet);
        chart.setData(lineData);

        Description description = new Description();
        description.setText("Last " + String.format("%d", chartEntries.size()) + " Games");
        description.setTextSize(16f);
        chart.setDescription(description);

        chart.setDrawGridBackground(true);

        chart.animateXY(ANIMATION_DURATION, ANIMATION_DURATION);

        chart.invalidate();
    }


    private void setViewControls() {
        TextView textViewAverageScore = activity.findViewById(R.id.textAverageScore);
        textViewAverageScore.setText(String.format("%.2f", averageScore * 100) + "%");

        TextView textViewPlayAttempts = activity.findViewById(R.id.textPlayAttempts);
        textViewPlayAttempts.setText(String.format("%d", playAttempts));
    }

    private List<Double> getGameScores() {
        List<Double> scores = new ArrayList<>();
        for (int i = 0; i < gameResults.size(); i++) {

            GameResult result = gameResults.get(i);
            if (result.getFinishedOn() == null)
                continue;

            int totalAnswers = result.getAnswers().size();
            if (totalAnswers == 0)
                continue;

            int missedAnswers = getMissedAnswers(result.getAnswers());
            double score = (totalAnswers - missedAnswers) / (double) totalAnswers;
            scores.add(score);
        }
        return scores;
    }

    private void getAverageScore(List<Double> scores) {
        if (scores.size() == 0) {
            averageScore = 0;
            return;
        }

        double sumOfScores = 0;
        for (double score : scores)
            sumOfScores += score;
        averageScore = sumOfScores / scores.size();
    }



    private void getChartEntries(List<Double> scores) {
        if (scores.size() == 0) {
            return;
        }

        int index = 0;
        int entryLimit = Math.min(scores.size(), MAX_CHART_ENTRIES);
        for (int i = entryLimit - 1; i >= 0; i--) {
            chartEntries.add(new Entry(index++, scores.get(i).floatValue() * 100));
        }
    }


    private int getMissedAnswers(List<GameAnswer> answers) {
        int totalMissed = 0;
        for (GameAnswer answer : answers) {
            if (answer.HasCorrectAnswer())
                continue;
            totalMissed++;
        }
        return totalMissed;
    }

    private void getPlayAttempts() {
        playAttempts = 0;
        for (GameResult result : gameResults) {
            if (result.getFinishedOn() == null)
                continue;
            if (result.getAnswers().size() == 0)
                continue;
            playAttempts++;
        }
    }
}
