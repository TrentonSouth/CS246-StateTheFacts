package com.example.statethefacts;

/**
 * The presenter class for the Game, despite being called a view model.  This class will persist
 * between many life cycle changes of its primary view.  It directs loading and saving of the game.
 * It randomizes selection of states and possible answers.  It handles events such as populating
 * views on creation and processing events like button clicks. *
 *
 * @author Gene Higgins
 * @since 12/1/2020
 */
public class GameAnswer {
    private final String state;
    private final String correctAnswer;
    private final String submittedAnswer;
    private final QuestionsType questionsType;

    public GameAnswer(String state, String correctAnswer, String submittedAnswer, QuestionsType questionsType) {
        this.state = state;
        this.correctAnswer = correctAnswer;
        this.submittedAnswer = submittedAnswer;
        this.questionsType = questionsType;
    }

    /**
     * Check to see if the submitted answer is the same as the actual answer
     * @return true if the answers match
     */
    public boolean hasCorrectAnswer() {
        return correctAnswer.equalsIgnoreCase(submittedAnswer);
    }

    /**
     * Get the name of the question type for this answer
     * @return question type name
     */
    public String getQuestionType() {
        switch (questionsType) {
            case Bird:
                return "Bird";
            case Rock:
                return "Rock";
            case Flower:
                return "Flower";
            case Capital:
                return "Capital";
            case Governor:
                return "Governor";
        }
        return "";
    }

    public String getState() {
        return state;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public String getSubmittedAnswer() {
        return submittedAnswer;
    }
}
