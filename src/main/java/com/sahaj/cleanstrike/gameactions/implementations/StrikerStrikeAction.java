package com.sahaj.cleanstrike.gameactions.implementations;

import com.sahaj.cleanstrike.entities.Board;
import com.sahaj.cleanstrike.entities.Player;
import com.sahaj.cleanstrike.enums.ActionType;
import com.sahaj.cleanstrike.gameactions.GameAction;

public class StrikerStrikeAction implements GameAction {

    @Override
    public Board executeOnBoard(Board board) {
        return board;
    }

    @Override
    public Player executeOnPlayer(Player player) {
        player.addCurrentActionResult(ActionType.FOUL_ACTION);
        player.handleFoul();
        player.decrementScore(1);
        return player;
    }

    @Override
    public boolean canExecute(Board board, Player player) {
        return true;
    }

    @Override
    public void handleIfLastThreeWereFaulty(Player player) {
        player.handleIfLastThreeActionsWereFoulty();
    }


}
