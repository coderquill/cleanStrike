package com.sahaj.cleanstrike;

public class DefunctCoinAction implements GameAction {
    @Override
    public Board executeOnBoard(Board board) {
        if (board.getBlackCoinCount() >= 1)
            board.removeBlackCoins(1);
        else {
            board.removeRedCoin();
        }
        return board;
    }

    @Override
    public Player executeOnPlayer(Player player) {
        player.addCurrentActionResult(ActionType.FOUL);
        player.handleFoul();
        player.decrementScore(2);
        return player;
    }

    @Override
    public boolean canExecute(Board board, Player player) {
        return board.getTotalCoinCount() >= 1;
    }

    @Override
    public void handleIfLastThreeWereFaulty(Player player) {
        player.handleIfLastThreeActionsWereFoulty();
    }

}
