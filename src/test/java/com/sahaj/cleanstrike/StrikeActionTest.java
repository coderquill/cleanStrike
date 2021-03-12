package com.sahaj.cleanstrike;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class StrikeActionTest {

    @Test
    public void testShouldIncrementScoreByOneAndDecrementBlackCoinCountByOneForStrike() throws InvalidBoardException {
        GameAction strike = new StrikeAction();
        Board board = Board.buildBoard(1, 9);
        Player player = Player.initializePlayer("testPlayer");

        strike.execute(board, player);

        assertEquals(board.getBlackCoinCount(), 8);
        assertEquals(board.getRedCoinCount(), 1);
        assertEquals(player.getScore(), 1);
    }

    @Test
    public void testShouldNotChangeBlackCoinCountWhenNoBlackCoinsAreAvailable() throws InvalidBoardException {
        GameAction strike = new StrikeAction();
        Board board = Board.buildBoard(0, 0);
        Player player = Player.initializePlayer("testPlayer");

        assertFalse(strike.canExecute(board, player));

        assertEquals(board.getBlackCoinCount(), 0);
        assertEquals(board.getRedCoinCount(), 0);
        assertEquals(player.getScore(), 0);
    }

    @Test
    public void testShouldNotChangeCoinCountAndScoreWhenOnlyARedCoinIsAvailable() throws InvalidBoardException {
        GameAction strike = new StrikeAction();
        Board board = Board.buildBoard(1, 0);
        Player player = Player.initializePlayer("testPlayer");

        assertFalse(strike.canExecute(board, player));

        assertEquals(board.getBlackCoinCount(), 0);
        assertEquals(board.getRedCoinCount(), 1);
        assertEquals(player.getScore(), 0);
    }

    @Test(expected = InvalidBoardException.class)
    public void testShouldNotChangeCoinCountAndScoreWhenNoCoinIsAvailable() throws InvalidBoardException {
        Board.buildBoard(-1, -3);
    }

}