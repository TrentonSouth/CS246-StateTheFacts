package com.example.statethefacts.ui.main;

import androidx.fragment.app.Fragment;

import com.example.statethefacts.CardType;
import com.example.statethefacts.GameType;

/**
 *  Returns the correct activity fragment using a factory pattern select and create the fragment
 *
 * @author Gene Higgins
 * @since 12/1/2020
 */
public class GameCardFactory {
    private final GameViewModel viewModel;

    public GameCardFactory(GameViewModel viewModel) {
        this.viewModel = viewModel;
    }

    /**
     * Creates and return the correct fragment based on the given card type and the current game type
     * @param cardType is the type of card to return, question or answer card
     * @return Fragment class
     * @throws ClassNotFoundException - thrown when a unexpected card type is used
     */
    public Fragment getGameCard(CardType cardType) throws ClassNotFoundException {
        if (cardType == CardType.Answer)
            return new CardAnswerFragment();
        if (viewModel.getGameType() == GameType.MultipleChoice)
            return new CardMultipleChoiceFragment();
        if (viewModel.getGameType() == GameType.TextEntry)
            return new CardTextEntryFragment();

        throw new ClassNotFoundException("No Card fragments found.");
    }

}
