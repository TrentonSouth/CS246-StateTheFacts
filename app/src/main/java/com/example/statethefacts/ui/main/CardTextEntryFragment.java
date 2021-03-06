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

/**
 *  Loads activity, view model (presenter) and sets the initial state for the view
 *  for displaying the question
 *
 * @author Gene Higgins
 * @since 12/1/2020
 */

public class CardTextEntryFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.layout_card_question_text_entry, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        GameViewModel viewModel = new ViewModelProvider(requireActivity()).get(GameViewModel.class);
        viewModel.setupTextEntryQuestionCard(this.getView());
    }
}