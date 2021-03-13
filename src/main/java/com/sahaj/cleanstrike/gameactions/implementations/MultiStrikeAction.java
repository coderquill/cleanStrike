package com.sahaj.cleanstrike.gameactions.implementations;

import com.sahaj.cleanstrike.entities.Board;
import com.sahaj.cleanstrike.entities.Player;
import com.sahaj.cleanstrike.enums.ActionType;
import com.sahaj.cleanstrike.gameactions.GameAction;

public class MultiStrikeAction implements GameAction {
    @Override
    public Board executeOnBoard(Board board) {
        board.removeBlackCoins(2);
        return board;
    }

    @Override
    public Player executeOnPlayer(Player player) {
        player.addCurrentActionResult(ActionType.NON_FOUL_ACTION);
        player.incrementScore(2);
        return player;
    }

    @Override
    public boolean canExecute(Board board, Player player) {
        return board.getBlackCoinCount() > 1;
    }

    @Override
    public void handleIfLastThreeWereFaulty(Player player) {
        player.handleIfLastThreeActionsWereFoulty();
    }
}
