package com.sahaj.cleanstrike;

import java.util.ArrayList;
import java.util.List;

public class Player {

    private String name;

    private int score;
    private int foulCount;
    private List<ActionType> actionTypeList;

    Player(String name, int score) {
        this.name = name;
        this.score = score;
        this.foulCount = 0;
        this.actionTypeList = new ArrayList<>();
    }

    public String getName() {
        return this.name;
    }

    public int getScore() {
        return this.score;
    }

    public void incrementScore(int scoreToBeAdded) {
        int currentScore = this.getScore();
        this.score = currentScore + scoreToBeAdded;

    }

    public void decrementScore(int scoreToBeSubtracted) {
        int currentScore = this.getScore();
        this.score = currentScore - scoreToBeSubtracted;
    }

    public int getFoulCount() {
        return this.foulCount;
    }

    public void handleFoul() {
        addFoul();
        int currentFoulCount = this.getFoulCount();

        if (currentFoulCount == 3) {
            decrementScore(1);
            resetFoulCount();
        }
    }

    public void resetFoulCount() {
        this.foulCount = 0;
    }


    public void addFoul() {
        this.foulCount = this.getFoulCount() + 1;
    }

    public static Player initializePlayer(String name) {
        int defaultScore = 0;
        return new Player(name, defaultScore);
    }

    public List<ActionType> getActionNatureList() {
        return this.actionTypeList;
    }

    public void addCurrentActionResult(ActionType actionType) {
        this.actionTypeList.add(actionType);
    }

    public void handleIfLastThreeActionsWereFoulty() {
        boolean areLastThreeFaulty = true;
        int totalActionsYet = this.actionTypeList.size();
        if (totalActionsYet >= 3) {
            for (ActionType actionType : this.actionTypeList.subList(totalActionsYet - 3, totalActionsYet)) {
                if (actionType == ActionType.NON_FOUL_ACTION) {
                    areLastThreeFaulty = false;
                    break;
                }
            }
        } else {
            areLastThreeFaulty = false;
        }
        if (areLastThreeFaulty) {
            this.decrementScore(1);
        }
    }


}
