package com.sahaj.cleanstrike;

import org.junit.Test;

import static org.junit.Assert.*;

public class StrikeActionTest {

    @Test
    public void testShouldIncrementScoreByOneForStrike() throws InvalidBoardException {
        GameAction strike = new StrikeAction();
        Board board = Board.buildBoard(1, 9);
        Player player = Player.initializePlayer("testPlayer");

        assertTrue(strike.canExecute(board, player));
        strike.execute(board, player);

        assertEquals(player.getScore(), 1);
    }

    @Test
    public void testShouldDecrementBlackCoinCountByOneForStrike() throws InvalidBoardException {
        GameAction strike = new StrikeAction();
        Board board = Board.buildBoard(1, 8);
        Player player = Player.initializePlayer("testPlayer");

        assertTrue(strike.canExecute(board, player));
        strike.execute(board, player);

        assertEquals(board.getBlackCoinCount(), 7);
    }


    @Test
    public void testShouldNotExecuteStrikeForNoBlackCoinsAvailable() throws InvalidBoardException {
        GameAction strike = new StrikeAction();
        Board board = Board.buildBoard(0, 0);
        Player player = Player.initializePlayer("testPlayer");

        assertFalse(strike.canExecute(board, player));
    }


    @Test(expected = InvalidBoardException.class)
    public void testShouldNotChangeCoinCountAndScoreWhenNoCoinIsAvailable() throws InvalidBoardException {
        Board.buildBoard(-1, -3);
    }

}