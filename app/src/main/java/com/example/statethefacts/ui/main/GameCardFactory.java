package com.example.statethefacts.ui.main;

import androidx.fragment.app.Fragment;

import com.example.statethefacts.CardType;
import com.example.statethefacts.GameType;

public class GameCardFactory {
    private GameViewModel viewModel;

    public GameCardFactory(GameViewModel viewModel) {

        this.viewModel = viewModel;
    }

    public Fragment getGameCard(CardType cardType) throws ClassNotFoundException {
        if(cardType == CardType.Answer)
            return new CardAnswerFragment(viewModel);
        if(viewModel.getGameType() == GameType.MultipleChoice)
            return new CardMultipleChoiceFragment(viewModel);
        if(viewModel.getGameType() == GameType.TextEntry)
            return new CardTextEntryFragment(viewModel);

        throw new ClassNotFoundException("No Card fragments found.");
    }

}
