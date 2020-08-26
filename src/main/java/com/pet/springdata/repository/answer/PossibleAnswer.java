package com.pet.springdata.repository.answer;

public class PossibleAnswer {

    private int indexOfAnswer;
    private String answer;

    public PossibleAnswer(int indexOfAnswer, String answer) {
        this.indexOfAnswer = indexOfAnswer;
        this.answer = answer;
    }

    public int getIndexOfAnswer() {
        return indexOfAnswer;
    }

    public void setIndexOfAnswer(int indexOfAnswer) {
        this.indexOfAnswer = indexOfAnswer;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    @Override
    public String toString() {
        return String.format("#%d - %s", indexOfAnswer, answer);
    }
}
