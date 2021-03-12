package com.sahaj.cleanstrike;

public interface GameAction {

    public Board executeOnBoard(Board board);
    public Player executeOnPlayer(Player player);

    public boolean canExecute(Board board, Player player);

    default void execute(Board board, Player player){
        executeOnBoard(board);
        executeOnPlayer(player);
    }
}
