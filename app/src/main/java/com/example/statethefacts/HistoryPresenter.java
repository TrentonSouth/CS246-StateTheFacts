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

/**
 *  This class loads historical game data, calculates the stats and sets up the chart
 *
 * @author Gene Higgins
 * @since 12/1/2020
 */
public class HistoryPresenter {
    static final int MAX_CHART_ENTRIES = 10; // total number of entries for display on the chart
    static final int ANIMATION_DURATION = 2000;  // animation time in milliseconds

    private final HistoryActivity activity;

    List<GameResult> gameResults;
    List<Entry> chartEntries;
    private int playAttempts;
    private double averageScore;

    public HistoryPresenter(HistoryActivity activity) {
        this.activity = activity;
        chartEntries = new ArrayList<>();
    }

    /**
     * Loads all of the historical games
     */
    public void loadGameResult() {
        gameResults = new GameResult().getAllGames(activity);
        Collections.sort(gameResults, new SortByDate());
    }

    /**
     * Calculations all stats and applies them to the chart
     */
    public void getStats() {
        List<Double> scores = getGameScores();
        getAverageScore(scores);
        getPlayAttempts();
        setViewControls();

        getChartEntries(scores);
        setupChart();
    }

    /**
     * get average of scores
     * @return score average
     */
    public double getAverageScoreEmail() {
        List<Double> scores = getGameScores();
        getAverageScore(scores);
        return averageScore;
    }

    /**
     * get a count of finished games
     * @return total games completed
     */
    public int getPlayAttemptsEmail() {
        getPlayAttempts();
        return playAttempts;
    }

    /**
     * Setup chart appearance, apply a data set to the chart.
     */
    private void setupChart() {
        // Charts library information can be found at https://github.com/PhilJay/MPAndroidChart
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

    /**
     * Apply derived stats to view controls
     */
    private void setViewControls() {
        TextView textViewAverageScore = activity.findViewById(R.id.textAverageScore);
        textViewAverageScore.setText(String.format("%.2f", averageScore * 100) + "%");

        TextView textViewPlayAttempts = activity.findViewById(R.id.textPlayAttempts);
        textViewPlayAttempts.setText(String.format("%d", playAttempts));
    }

    /**
     * runs through each completed game and calculate the game score as an percentage
     * @return list of percentages.
     */
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

    /**
     * Calculates the average game score from a list of individual games scores
     * @param scores average percentage scored.
     */
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

    /**
     * Get up to the last 10 scores for the chart.
     * @param scores list of percentages to pull from, expected to be in order of play
     */
    private void getChartEntries(List<Double> scores) {
        if (scores.size() == 0) {
            return;
        }

        int index = 1;
        // find out how many scores to use
        int entryLimit = Math.min(scores.size(), MAX_CHART_ENTRIES);
        int firstEntry = scores.size() - entryLimit;
        if (firstEntry < 0)
            firstEntry = 0;

        // add entries to chart data list
        for (int i = firstEntry; i < scores.size(); i++) {
            chartEntries.add(new Entry(index++, scores.get(i).floatValue() * 100));
        }
    }

    /**
     * get number of missed answers
     * @param answers list of game answers
     * @return total missed answers
     */
    private int getMissedAnswers(List<GameAnswer> answers) {
        int totalMissed = 0;
        for (GameAnswer answer : answers) {
            if (answer.hasCorrectAnswer())
                continue;
            totalMissed++;
        }
        return totalMissed;
    }

    /**
     * find total number of finished games
     */
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
