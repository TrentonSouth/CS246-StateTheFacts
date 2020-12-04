package com.example.statethefacts.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.statethefacts.GameQuestion;
import com.example.statethefacts.QuestionsType;
import com.example.statethefacts.R;

public class CardTextEntryFragment extends Fragment {
    private GameViewModel viewModel;

    public CardTextEntryFragment(GameViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        GameQuestion question = viewModel.getNextQuestion();

        View view = inflater.inflate(R.layout.layout_card_question_text_entry, container, false);
        TextView textViewQuestion = view.findViewById(R.id.textView_question);
        textViewQuestion.setText(question.getQuestion());

        return view;
    }
}