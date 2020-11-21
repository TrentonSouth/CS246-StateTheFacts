package com.example.statethefacts.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.statethefacts.GameAnswer;
import com.example.statethefacts.GameQuestion;
import com.example.statethefacts.R;

public  class CardAnswerFragment extends Fragment {
    private GameViewModel viewModel;

    public CardAnswerFragment(GameViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_card_answer, container, false);

        GameAnswer lastAnswer = viewModel.getLastAnswer();
        GameQuestion lastQuestion = viewModel.getQuestion();

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

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(GameViewModel.class);
        // TODO: Use the ViewModel
    }
}