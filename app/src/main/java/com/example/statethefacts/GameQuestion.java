package com.example.statethefacts;

import java.util.ArrayList;
import java.util.HashMap;

import java.util.List;
import java.util.Random;

public class GameQuestion {
    private String state = "";
    private QuestionsType questionType;
    private String question = "";
    private State answer = null;
    private State option1 = null;
    private State option2 = null;
    private State option3 = null;
    private State option4 = null;

    private HashMap<String,State> states = new HashMap<>();
    private List<String> stateNames = new ArrayList<>();

    public GameQuestion(List<State> states, QuestionsType questionType){
        if(states.size()<4)
            throw new IllegalArgumentException("Information for 4 or more states is required.");

        for(State state : states)
        {
            this.states.put(state.state, state);
            this.stateNames.add(state.state);
        }
        this.questionType = questionType;
    }

    public void generate(String state, GameType gameType) {
        if(!states.containsKey(state))
            throw new IllegalArgumentException("Invalid state name: "+ state);

        setupQuestion(state);

        State selectedState = states.get(state);

        this.state = state;
        this.answer = selectedState;

        if(gameType == GameType.TextEntry)
            return;

        setupOptions();

    }

    private void setupQuestion(String state) {
        switch(questionType){
            case Capital:
                question = "What is the Capital of " + state.trim() +"?";
                break;
            case Bird:
                question = "What is the state Bird for " + state.trim() +"?";
                break;
            case Rock:
                question = "What is the state Rock for " + state.trim() +"?";
                break;
            case Flower:
                question = "What is the state Flower for " + state.trim() +"?";
                break;
            case Governor:
                question = "What is the state Governor for " + state.trim() +"?";
                break;
            default:
                question = "Unknown game type!";
                break;
        }
    }

    private void setupOptions() {
        List<Integer> slots = getSlotList();

        int answerSlot = getUnsetOptionSlot(slots);
        setOption(answerSlot, answer);

        do {
            State randomAnswer = getRandomFact();
            answerSlot = getUnsetOptionSlot(slots);
            setOption(answerSlot, randomAnswer);

        } while(slots.size() > 0);
    }

    private State getRandomFact() {
        do {
            State selectedState = getRandomState();
            if (isAnswerUnused(selectedState))
                return selectedState;
        } while(true);
    }

    private State getRandomState() {
        Random random = new Random();
        int stateIndex = random.nextInt(states.size());
        String stateName = stateNames.get(stateIndex);
        return states.get(stateName);
    }

    private boolean isAnswerUnused(State answer) {
        if(option1 != null && option1.equals(answer))
            return false;
        if(option2 != null && option2.equals(answer))
            return false;
        if(option3 != null && option3.equals(answer))
            return false;
        if(option4 != null && option4.equals(answer))
            return false;
        return true;
    }

    private int getUnsetOptionSlot(List<Integer> slots) {
        Random random = new Random();
        int slotPosition = random.nextInt(slots.size());
        return slots.remove(slotPosition);
    }

    private List<Integer> getSlotList() {
        List<Integer> list = new ArrayList<>();
        list.add(0);
        list.add(1);
        list.add(2);
        list.add(3);
        return list;
    }

    private void setOption(int optionSlot, State option) {
        switch (optionSlot){
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

    private String getStateFact(State selectedState) {
        switch (questionType){
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

    public String getState() {
        return state;
    }

    public String getQuestion(){
        return question;
    }

    public String getAnswer() { return getStateFact(answer); }

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
