package com.example.statethefacts.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.statethefacts.GameQuestion;
import com.example.statethefacts.QuestionsType;
import com.example.statethefacts.R;

public class CardMultipleChoiceFragment extends Fragment {
    private GameViewModel viewModel;

    public CardMultipleChoiceFragment(GameViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        GameQuestion question = viewModel.getNextQuestion(QuestionsType.Flower);

        View view = inflater.inflate(R.layout.layout_card_question, container, false);
        TextView textViewQuestion = view.findViewById(R.id.textView_question);
        textViewQuestion.setText(question.getQuestion());

        RadioButton radioOption1 = view.findViewById(R.id.radioButton_option1);
        RadioButton radioOption2 = view.findViewById(R.id.radioButton_option2);
        RadioButton radioOption3 = view.findViewById(R.id.radioButton_option3);
        RadioButton radioOption4 = view.findViewById(R.id.radioButton_option4);

        radioOption1.setText(question.getOption1());
        radioOption2.setText(question.getOption2());
        radioOption3.setText(question.getOption3());
        radioOption4.setText(question.getOption4());

        return view;
    }

}