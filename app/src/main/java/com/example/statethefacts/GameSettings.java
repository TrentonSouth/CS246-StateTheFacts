package com.example.statethefacts;

import java.util.List;

public class GameSettings {
    Boolean Name;  //this is the State
    Boolean Capital;
    Boolean Flower;
    Boolean Rock;
    Boolean Bird;
    Boolean Governor;
    Boolean multipleChoice;
    List<State> states;

    public GameSettings(Boolean name, Boolean capital, Boolean flower, Boolean rock, Boolean bird, Boolean governor, Boolean multipleChoice, List<State> states) {
        Name = name;
        Capital = capital;
        Flower = flower;
        Rock = rock;
        Bird = bird;
        Governor = governor;
        this.multipleChoice = multipleChoice;
        this.states = states;
    }

    public Boolean getName() {
        return Name;
    }

    public void setName(Boolean name) {
        Name = name;
    }

    public Boolean getCapital() {
        return Capital;
    }

    public void setCapital(Boolean capital) {
        Capital = capital;
    }

    public Boolean getFlower() {
        return Flower;
    }

    public void setFlower(Boolean flower) {
        Flower = flower;
    }

    public Boolean getRock() {
        return Rock;
    }

    public void setRock(Boolean rock) {
        Rock = rock;
    }

    public Boolean getBird() {
        return Bird;
    }

    public void setBird(Boolean bird) {
        Bird = bird;
    }

    public Boolean getGovernor() {
        return Governor;
    }

    public void setGovernor(Boolean governor) {
        Governor = governor;
    }

    public Boolean getMultipleChoice() {
        return multipleChoice;
    }

    public void setMultipleChoice(Boolean multipleChoice) {
        this.multipleChoice = multipleChoice;
    }

    public List<State> getStates() {
        return states;
    }

    public void setStates(List<State> states) {
        this.states = states;
    }


}
