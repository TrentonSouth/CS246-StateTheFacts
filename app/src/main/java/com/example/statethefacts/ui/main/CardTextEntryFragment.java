package com.example.statethefacts.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.statethefacts.GameQuestion;
import com.example.statethefacts.QuestionsType;
import com.example.statethefacts.R;

public class CardTextEntryFragment extends Fragment {
    private GameViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.layout_card_question_text_entry, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(GameViewModel.class);
        // TODO: Use the ViewModel

        GameQuestion question = viewModel.getNextQuestion();

        TextView textViewQuestion = this.getView().findViewById(R.id.textView_question);
        textViewQuestion.setText(question.getQuestion());
    }
}