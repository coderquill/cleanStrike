package com.sahaj.cleanstrike;

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
