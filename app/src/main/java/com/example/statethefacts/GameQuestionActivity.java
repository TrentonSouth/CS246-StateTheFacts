package com.example.statethefacts;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class GameQuestionActivity extends AppCompatActivity {

    private GameQuestionPresenter presenter;
    private GameType gameType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_question);

        Bundle extras = getIntent().getExtras();

        if(extras != null)
            gameType = GameType.values()[extras.getInt("gameType")];
        else
            gameType = GameType.MultipleChoice;

//        presenter = new GameQuestionPresenter(this, gameType);
//        GameQuestion question = presenter.getNextQuestion(QuestionsType.Rock);
//
//        TextView textViewQuestion = findViewById(R.id.textView_question);
//        textViewQuestion.setText(question.getQuestion());
//
//        RadioButton radioOption1 = findViewById(R.id.radioButton_option1);
//        RadioButton radioOption2 = findViewById(R.id.radioButton_option2);
//        RadioButton radioOption3 = findViewById(R.id.radioButton_option3);
//        RadioButton radioOption4 = findViewById(R.id.radioButton_option4);
//
//        radioOption1.setText(question.getOption1());
//        radioOption2.setText(question.getOption2());
//        radioOption3.setText(question.getOption3());
//        radioOption4.setText(question.getOption4());

    }

    public void submitAnswer(View view) {

        RadioGroup radioGroup = findViewById(R.id.radioGroup_answers);
        int selectedId = radioGroup.getCheckedRadioButtonId();
        RadioButton selectedRadioButton = findViewById(selectedId);
        String answer = selectedRadioButton.getText().toString();
//        String result = presenter.submitAnswer(answer);
//        TextView textViewAnswer = findViewById(R.id.textView_answer);
//        textViewAnswer.setText(result);
    }
}