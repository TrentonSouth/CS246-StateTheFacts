package com.example.statethefacts;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.statethefacts.ui.main.CardFlipperFragment;

public class CardFlipperActivity extends AppCompatActivity {

    private GameQuestionPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.card_flipper_activity);

        presenter = new GameQuestionPresenter(this, GameType.MultipleChoice);


        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.container, new CardFrontFragment(presenter))
                    .commit();
        }
    }

    public void onFlipCard(View view){
        flipCard();
    }

    private boolean showingBack;
    public void flipCard() {
//        if (showingBack) {
//            getSupportFragmentManager().popBackStack();
//            showingBack = false;
//            return;
//        }

        // Flip to the back.

        //showingBack = true;


        Fragment nextCard;
        if(showingBack)
            nextCard = new CardFrontFragment(presenter);
        else
            nextCard = new CardBackFragment(presenter);


        showingBack = !showingBack;

        // Create and commit a new fragment transaction that adds the fragment for
        // the back of the card, uses custom animations, and is part of the fragment
        // manager's back stack.

        getSupportFragmentManager()
                .beginTransaction()

                // Replace the default fragment animations with animator resources
                // representing rotations when switching to the back of the card, as
                // well as animator resources representing rotations when flipping
                // back to the front (e.g. when the system Back button is pressed).
                .setCustomAnimations(
                        R.animator.card_flip_right_in,
                        R.animator.card_flip_right_out,
                        R.animator.card_flip_right_in,
                        R.animator.card_flip_right_out)

                // Replace any fragments currently in the container view with a
                // fragment representing the next page (indicated by the
                // just-incremented currentPage variable).
                .replace(R.id.container, nextCard)

                // Add this transaction to the back stack, allowing users to press
                // Back to get to the front of the card.
                //.addToBackStack(null)

                // Commit the transaction.
                .commit();
    }

    public void submitAnswer(View view){
        RadioGroup radioGroup = findViewById(R.id.radioGroup_answers);
        int selectedId = radioGroup.getCheckedRadioButtonId();
        RadioButton selectedRadioButton = findViewById(selectedId);
        String answer = selectedRadioButton.getText().toString();
        presenter.submitAnswer(answer);
    }

    public void nextQuestion(View view){
        flipCard();
    }


    /**
     * A fragment representing the front of the card.
     */
    public static class CardFrontFragment extends Fragment {
        private GameQuestionPresenter presenter;

        public CardFrontFragment(GameQuestionPresenter presenter) {
            this.presenter = presenter;

        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {

            GameQuestion question = presenter.getNextQuestion(QuestionsType.Flower);

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

    /**
     * A fragment representing the back of the card.
     */
    public static class CardBackFragment extends Fragment {
        private GameQuestionPresenter presenter;

        public CardBackFragment(GameQuestionPresenter presenter) {

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
}