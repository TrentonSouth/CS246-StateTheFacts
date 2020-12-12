package com.example.statethefacts.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.statethefacts.R;

/**
 *  Loads activity, view model (presenter) and sets the initial state for the view
 *  for displaying the question and its selection of possible answers
 *
 * @author Gene Higgins
 * @since 12/1/2020
 */

public class CardMultipleChoiceFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.layout_card_question, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        GameViewModel viewModel = new ViewModelProvider(requireActivity()).get(GameViewModel.class);
        viewModel.setupMultipleChoiceQuestionCard(this.getView());
    }

}