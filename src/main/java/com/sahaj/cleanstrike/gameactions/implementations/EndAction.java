package com.sahaj.cleanstrike.gameactions.implementations;

import com.sahaj.cleanstrike.entities.Board;
import com.sahaj.cleanstrike.entities.Player;
import com.sahaj.cleanstrike.gameactions.GameAction;

public class EndAction implements GameAction {

    @Override
    public Board executeOnBoard(Board board) {
        return board;
    }

    @Override
    public Player executeOnPlayer(Player player) {
        return player;
    }

    @Override
    public boolean canExecute(Board board, Player player) {
        return false;
    }

    @Override
    public void handleIfLastThreeWereFaulty(Player player) {

    }
}
