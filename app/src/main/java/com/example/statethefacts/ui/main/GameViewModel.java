package com.example.statethefacts.ui.main;

import android.app.Application;
import android.os.Build;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.AndroidViewModel;

import com.example.statethefacts.Facts;
import com.example.statethefacts.GameActivity;
import com.example.statethefacts.GameAnswer;
import com.example.statethefacts.GameQuestion;
import com.example.statethefacts.GameResult;
import com.example.statethefacts.GameSettings;
import com.example.statethefacts.GameType;
import com.example.statethefacts.GetFacts;
import com.example.statethefacts.QuestionsType;
import com.example.statethefacts.R;
import com.example.statethefacts.State;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

/**
 *  The presenter class for the Game, despite being called a view model.  This class will persist
 *  between many life cycle changes of its primary view.  It directs loading and saving of the game.
 *  It randomizes selection of states and possible answers.  It handles events such as populating
 *  views on creation and processing events like button clicks. *
 *
 * @author Gene Higgins
 * @since 12/1/2020
 */

public class GameViewModel extends AndroidViewModel {
    public final static int MINIMUM_STATES = 1;
    public final static int MAXIMUM_STATES = 50;
    private final List<State> allStates;
    private final GameType gameType;

    private GameQuestion question;
    private List<State> remainingStates;
    private GameResult gameResult;
    private GameAnswer lastAnswer;

    GameSettings gameSettings;

    public GameViewModel(Application application) {
        super(application);

        GetFacts gf = new GetFacts();
        Facts facts = gf.fetchFacts(getApplication().getApplicationContext());
        allStates = facts.getStates();
        gameResult = new GameResult();

        gameSettings = new GameSettings();
        gameSettings.loadGameSettings(application);
        gameType = gameSettings.getGameType();

    }

    /**
     * Loads the ongoing game information
     */
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void resumeGame() {
        gameResult = gameResult.loadCurrentGame(getApplication().getApplicationContext());
    }

    /**
     * Setup the view model and its objects for a new game.
     */
    public void startNewGame() {
        int numberOfStates = gameSettings.getNumberOfFacts();
        gameResult.startNewGame(gameType);
        getRandomStates(numberOfStates);
    }

    /**
     * Will pull the number of requested states, randomly selected and randomly ordered.
     * @param numberOfStates total number of states to use or questions to ask
     */
    private void getRandomStates(int numberOfStates) {

        // make sure number of states are in an acceptable range
        if(numberOfStates < MINIMUM_STATES)
            numberOfStates = MINIMUM_STATES;
        if(numberOfStates > MAXIMUM_STATES)
            numberOfStates = MAXIMUM_STATES;

        List<State> statesToChooseFrom = new ArrayList<>(allStates);
        remainingStates = new ArrayList<>();

        // random select a state, then remove it from the selection list to avoid duplicates
        Random random = new Random();
        for (int i = 0; i < numberOfStates; i++) {
            int index = random.nextInt(statesToChooseFrom.size());
            remainingStates.add(statesToChooseFrom.get(index));
            statesToChooseFrom.remove(index);
        }
    }

    /**
     * Creates the next question for the game
     * @return GameQuestion
     */
    public GameQuestion getNextQuestion() {
        QuestionsType questionsType = getNextQuestionType();
        question = new GameQuestion(allStates, questionsType);
        question.generate(remainingStates, gameResult.getGameType());
        return question;
    }

    /**
     * Gets a random fact type for the next question. Fact types are limited to those set by the
     * user in the game settings
     * @return QuestionType
     */
    private QuestionsType getNextQuestionType() {
        List<QuestionsType> availableTypes = new ArrayList<>();
        if (gameSettings.getBird())
            availableTypes.add(QuestionsType.Bird);
        if (gameSettings.getCapital())
            availableTypes.add(QuestionsType.Capital);
        if (gameSettings.getFlower())
            availableTypes.add(QuestionsType.Flower);
        if (gameSettings.getGovernor())
            availableTypes.add(QuestionsType.Governor);
        if (gameSettings.getRock())
            availableTypes.add(QuestionsType.Rock);

        Random random = new Random();

        int index = random.nextInt(availableTypes.size());
        return availableTypes.get(index);
    }

    /**
     * Handles they event for answer submission by the user, records the answer.
     * @param activity current view
     */
    public void submitAnswer(GameActivity activity) {
        String submittedAnswer = getSubmittedAnswer(activity);

        lastAnswer = new GameAnswer(question.getStateName(), question.getAnswer(), submittedAnswer, question.getQuestionType());
        gameResult.addAnswer(lastAnswer);
    }

    /**
     * Pulls the submitted answer from the view based on the game time.  The answers may be pulled
     * from either a text box or from radio buttons
     * @param activity current view
     * @return the literal string of the answer
     */
    private String getSubmittedAnswer(GameActivity activity) {

        switch (getGameType()) {
            case MultipleChoice:
                RadioGroup radioGroup = activity.findViewById(R.id.radioGroup_answers);
                int selectedId = radioGroup.getCheckedRadioButtonId();
                RadioButton selectedRadioButton = activity.findViewById(selectedId);
                if (selectedRadioButton == null)
                    return "";
                return selectedRadioButton.getText().toString();

            case TextEntry:
                EditText editTextAnswer = activity.findViewById(R.id.editTextAnswer);
                if (editTextAnswer == null)
                    return "";
                return editTextAnswer.getText().toString();

            default:
                throw new IllegalArgumentException("Invalid Game Type: " + getGameType().toString());
        }
    }

    /**
     * Set up the Answer card after creation
     * @param view current view
     */
    public void setupAnswerCard(View view) {
        GameAnswer lastAnswer = getLastAnswer();
        GameQuestion lastQuestion = getQuestion();

        TextView textViewQuestion = view.findViewById(R.id.textView_question);
        textViewQuestion.setText("Question was " + lastQuestion.getQuestion());

        TextView textViewCorrectAnswer = view.findViewById(R.id.textView_correct_answer);
        textViewCorrectAnswer.setText("Correct Answer: " + lastQuestion.getAnswer());

        TextView textViewSubmittedAnswer = view.findViewById(R.id.textView_submitted_answer);
        textViewSubmittedAnswer.setText("Submitted Answer: " + lastAnswer.getSubmittedAnswer());


        TextView textViewAnswerResult = view.findViewById(R.id.textView_answer_result);
        String answerResult;
        if (lastAnswer.hasCorrectAnswer())
            answerResult = "Correct Answer!";
        else
            answerResult = "Wrong Answer.";

        textViewAnswerResult.setText(answerResult);
    }

    /**
     * Set up the multiple choice Question card after creation
     * @param view current view
     */
    public void setupMultipleChoiceQuestionCard(View view) {
        GameQuestion question = getNextQuestion();


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
    }

    /**
     * Set up the text entry Question card after creation
     * @param view current view
     */
    public void setupTextEntryQuestionCard(View view) {
        GameQuestion question = getNextQuestion();

        TextView textViewQuestion = view.findViewById(R.id.textView_question);
        textViewQuestion.setText(question.getQuestion());
    }

    /**
     * Initiate saving of the game
     */
    public void saveGame() {
        gameResult.saveCurrentGame(getApplication().getApplicationContext());
    }

    /**
     * Initiate finishing of the game
     * @param activity current activity
     */
    public void endGame(GameActivity activity) {
        gameResult.finishGame(activity);
    }

    public GameQuestion getQuestion() {
        return question;
    }

    public GameAnswer getLastAnswer() {
        return lastAnswer;
    }

    public GameType getGameType() {
        return gameResult.getGameType();
    }

    public boolean hasMoreStates() {
        return remainingStates.size() >= 1;
    }

    public UUID getGameId() {
        return gameResult.getGameId();
    }

}