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

    private HistoryActivity activity;
    List<GameResult> gameResults;
    List<Entry> chartEntries;
    private int playAttempts;
    private double averageScore;

    public HistoryPresenter(HistoryActivity activity){
        this.activity = activity;
        chartEntries = new ArrayList<>();
    }

    public void loadGameResult() {
        gameResults = new GameResult().getAllGames(activity);
        Collections.sort(gameResults, new SortByDate());
    }


    public void getStats() {
        getAverageScore();
        getPlayAttempts();

        setViewControls();

        getChartEntries();
        setupChart();
    }

    public double getAverageScoreEmail() {
        getAverageScore();
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
        //dataSet.setValueFormatter(new WholeNumberValueFormatter());
        dataSet.setValueFormatter(new PercentageValueFormatter());

        LineData lineData = new LineData(dataSet);
        chart.setData(lineData);

        Description description = new Description();
        description.setText("Last " + String.format("%d",chartEntries.size()) + " Games");
        description.setTextSize(16f);
        chart.setDescription(description);

        chart.setDrawGridBackground(true);

        chart.animateXY(2000,2000);

        chart.invalidate();
    }



    private void setViewControls() {
        TextView textViewAverageScore = activity.findViewById(R.id.textAverageScore);
        textViewAverageScore.setText(String.format("%.2f",averageScore*100)+"%");

        TextView textViewPlayAttempts = activity.findViewById(R.id.textPlayAttempts);
        textViewPlayAttempts.setText(String.format("%d",playAttempts));
    }

    private void getAverageScore() {
        List<Double> scores = new ArrayList<>();
        for(int i=0; i<gameResults.size(); i++){

            GameResult result = gameResults.get(i);
            if(result.getFinishedOn() == null)
                continue;

            int totalAnswers = result.getAnswers().size();
            if(totalAnswers == 0)
                continue;

            int missedAnswers = getMissedAnswers(result.getAnswers());
            double score = (totalAnswers-missedAnswers)/(double)totalAnswers;
            scores.add(score);

//
//            if(chartEntries.size()<MAX_CHART_ENTRIES){
//                chartEntries.add(new Entry(i, ((float) score)));
//            }
        }
        if(scores.size() == 0){
            averageScore = 0;
            return;
        }
        double sumOfScores = 0;
        for(double score : scores){
            sumOfScores += score;
        }
        averageScore = sumOfScores/scores.size();
    }

    private void getChartEntries() {
        List<Double> scores = new ArrayList<>();
        for(int i=gameResults.size()-1; i >= 0; i--){

            GameResult result = gameResults.get(i);
            if(result.getFinishedOn() == null)
                continue;

            int totalAnswers = result.getAnswers().size();
            if(totalAnswers == 0)
                continue;

            int missedAnswers = getMissedAnswers(result.getAnswers());
            double score = (totalAnswers-missedAnswers)/(double)totalAnswers;
            scores.add(score);

        }

        int entryLimit = 0;
        if(scores.size() > MAX_CHART_ENTRIES)
            entryLimit = MAX_CHART_ENTRIES;
        else
            entryLimit = scores.size();


        int index = 0;
        for(int i=entryLimit-1; i>=0; i--){
            chartEntries.add(new Entry(index++,scores.get(i).floatValue()*100));
        }
    }



    private int getMissedAnswers(List<GameAnswer> answers) {
        int totalMissed = 0;
        for(GameAnswer answer: answers){
            if(answer.HasCorrectAnswer())
               continue;
            totalMissed++;
        }
        return totalMissed;
    }

    private void getPlayAttempts() {
        playAttempts = 0;
        for(GameResult result: gameResults){
            if(result.getFinishedOn() == null)
                continue;
            if(result.getAnswers().size() == 0)
                continue;
            playAttempts ++;
        }
    }
}
