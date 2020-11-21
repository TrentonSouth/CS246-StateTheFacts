package com.example.statethefacts.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.statethefacts.GameAnswer;
import com.example.statethefacts.GameQuestion;
import com.example.statethefacts.GameQuestionPresenter;
import com.example.statethefacts.R;

public  class CardAnswerFragment extends Fragment {
    private GameQuestionPresenter presenter;

    public CardAnswerFragment(GameQuestionPresenter presenter) {

        this.presenter = presenter;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_card_answer, container, false);

        GameAnswer lastAnswer = presenter.getLastAnswer();
        GameQuestion lastQuestion = presenter.getQuestion();

        TextView textViewQuestion = view.findViewById(R.id.textView_question);
        textViewQuestion.setText("Question was " + lastQuestion.getQuestion());

        TextView textViewCorrectAnswer = view.findViewById(R.id.textView_correct_answer);
        textViewCorrectAnswer.setText("Correct Answer: " + lastQuestion.getAnswer());

        TextView textViewSubmittedAnswer = view.findViewById(R.id.textView_submitted_answer);
        textViewSubmittedAnswer.setText("Submitted Answer: " + lastAnswer.getSubmittedAnswer());


        TextView textViewAnswerResult = view.findViewById(R.id.textView_answer_result);
        String answerResult;
        if(lastAnswer.HasCorrectAnswer())
            answerResult = "Correct Answer!";
        else
            answerResult = "Wrong Answer.";

        textViewAnswerResult.setText(answerResult);

        return view;
    }
}