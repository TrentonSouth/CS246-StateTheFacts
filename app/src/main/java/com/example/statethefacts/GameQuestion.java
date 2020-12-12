package com.example.statethefacts;

import java.util.ArrayList;
import java.util.HashMap;

import java.util.List;
import java.util.Random;


/**
 *  This class holds the question information include the state, question type
 *  and selection of answers.  It also randomly generates question including the
 *  selection of state, fact type and possible answers
 *
 * @author Gene Higgins
 * @since 12/1/2020
 */

public class GameQuestion {
    private final int MINIMUM_STATE_SIZE = 4;
    private final HashMap<String, State> states = new HashMap<>();
    private final List<String> stateNames = new ArrayList<>();
    private final QuestionsType questionType;

    private String stateName = "";
    private String question = "";
    private State answer = null;
    private State option1 = null;
    private State option2 = null;
    private State option3 = null;
    private State option4 = null;

    /**
     * initializes the question with the question type and a list of states
     * @param states list of State object to generate questions from
     * @param questionType the type of question/fact use
     */
    public GameQuestion(List<State> states, QuestionsType questionType) {
        if (states.size() < MINIMUM_STATE_SIZE) // required number of state to populate radio buttons
            throw new IllegalArgumentException("Information for 4 or more states is required.");

        for (State state : states) {
            this.states.put(state.state, state); //add the state to internal list
            this.stateNames.add(state.state); //list of state names use for lookup table
        }
        this.questionType = questionType;
    }

    /**
     * Generates a new question
     * @param states list of states that still need to be asked about
     * @param gameType type of game used to generate answers, when needed
     */
    public void generate(List<State> states, GameType gameType) {

        // get the next state to ask about.
        State nextState = states.get(states.size() - 1);
        states.remove(nextState);
        String stateName = nextState.state;

        // make sure the state is actionable
        if (!this.states.containsKey(stateName))
            throw new IllegalArgumentException("Invalid state name: " + this.stateName);

        setupQuestion(stateName);

        //set values for the question
        this.stateName = stateName;
        this.answer = nextState;

        if (gameType == GameType.TextEntry)
            return;

        //if multiple choice question then get answer set
        setupOptions();

    }

    /**
     * Create the question text to be displayed
     * @param state name of the state in question
     */
    private void setupQuestion(String state) {
        switch (questionType) {
            case Capital:
                question = "What is the Capital of " + state.trim() + "?";
                break;
            case Bird:
                question = "What is the state Bird for " + state.trim() + "?";
                break;
            case Rock:
                question = "What is the state Rock for " + state.trim() + "?";
                break;
            case Flower:
                question = "What is the state Flower for " + state.trim() + "?";
                break;
            case Governor:
                question = "What is the state Governor for " + state.trim() + "?";
                break;
            default:
                question = "Unknown game type!";
                break;
        }
    }

    /** create multiple choice answers, each answer is put into a slot, which
     *  corresponds to a radio button.
     */
    private void setupOptions() {
        List<Integer> slots = getSlotList();

        int answerSlot = getUnsetOptionSlot(slots); // get random slot
        setOption(answerSlot, answer); // store correct answer

        do {
            State randomAnswer = getRandomFact();  // get random fact from another state
            answerSlot = getUnsetOptionSlot(slots); // get a random unused slot
            setOption(answerSlot, randomAnswer); // store answer in slot

        } while (slots.size() > 0); // until all slots are used
    }

    /**
     * Gets a random states and it fact until it finds an unused one
     * @return - the random, unused state with its facts
     */
    private State getRandomFact() {
        do {
            State selectedState = getRandomState();  // Get random state
            if (isAnswerUnused(selectedState))  //See if answer is used to avoid duplicate answers
                return selectedState; // unused then return
        } while (true); // we know this loop will terminate because
                        // the minimum number needed was verified when class was constructed.
    }

    /**
     * Gets a random state from the full list of possibilities
     * @return random state with its facts
     */
    private State getRandomState() {
        Random random = new Random();
        int stateIndex = random.nextInt(states.size());
        String stateName = stateNames.get(stateIndex);
        return states.get(stateName);
    }

    /**
     * Checks to see if a states fact has already been used in the list of possible answers
     * @param answer State with its facts
     * @return true or false if the answer is unused or not
     */
    private boolean isAnswerUnused(State answer) {
        if (option1 != null && option1.equals(answer))
            return false;
        if (option2 != null && option2.equals(answer))
            return false;
        if (option3 != null && option3.equals(answer))
            return false;
        if (option4 != null && option4.equals(answer))
            return false;
        return true;
    }


    /**
     * Randomly retrieve an used slot
     * @param slots list of used slot
     * @return index of the next slot to use
     */
    private int getUnsetOptionSlot(List<Integer> slots) {
        Random random = new Random();
        int slotPosition = random.nextInt(slots.size());
        return slots.remove(slotPosition);
    }

    /**
     * Sets up the initial slot list
     * @return fresh list of unused slots
     */
    private List<Integer> getSlotList() {
        List<Integer> list = new ArrayList<>();
        list.add(0);
        list.add(1);
        list.add(2);
        list.add(3);
        return list;
    }

    /**
     * Set the state with its facts to a given slot
     * @param optionSlot index of slot for the fact
     * @param option state with its facts
     */
    private void setOption(int optionSlot, State option) {
        switch (optionSlot) {
            case 0:
                option1 = option;
                return;
            case 1:
                option2 = option;
                return;
            case 2:
                option3 = option;
                return;
            case 3:
                option4 = option;
                return;
            default:
                throw new IllegalArgumentException("Invalid option slot: " + optionSlot);
        }
    }

    /**
     * Get the literal fact from a given state based on the Fact type
     * @param selectedState the state to pull the fact from
     * @return fact for the slot
     */
    private String getStateFact(State selectedState) {
        switch (questionType) {
            case Capital:
                return selectedState.capital;
            case Bird:
                return selectedState.bird;
            case Rock:
                return selectedState.rock;
            case Flower:
                return selectedState.flower;
            case Governor:
                return selectedState.governor;
            default:
                return "Unknown Question Type";
        }
    }

    public String getStateName() {
        return stateName;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return getStateFact(answer);
    }

    public QuestionsType getQuestionType() {
        return questionType;
    }

    public String getOption1() {
        return getStateFact(option1);
    }

    public String getOption2() {
        return getStateFact(option2);
    }

    public String getOption3() {
        return getStateFact(option3);
    }

    public String getOption4() {
        return getStateFact(option4);
    }

}
