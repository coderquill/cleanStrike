package com.sahaj.cleanstrike.gameactions;

import com.sahaj.cleanstrike.entities.Board;
import com.sahaj.cleanstrike.entities.Player;

public interface GameAction {

    Board executeOnBoard(Board board);

    Player executeOnPlayer(Player player);

    boolean canExecute(Board board, Player player);

    void handleIfLastThreeWereFaulty(Player player);

    default void execute(Board board, Player player) {
        executeOnBoard(board);
        executeOnPlayer(player);
    }
}
