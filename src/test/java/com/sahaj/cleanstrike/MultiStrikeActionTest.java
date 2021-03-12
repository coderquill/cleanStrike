package com.sahaj.cleanstrike;

import org.junit.Test;

import static org.junit.Assert.*;

public class MultiStrikeActionTest{

    @Test
    public void testShouldIncrementScoreByTwoAndDecrementBlackCoinCountByTwoForMultiStrike() throws InvalidBoardException {
        GameAction multiStrike = new MultiStrikeAction();
        Player player = Player.initializePlayer("testPlayer");
        Board board = Board.buildBoard(1, 9);

        assertTrue(multiStrike.canExecute(board, player));
        multiStrike.execute(board, player);

        assertEquals(board.getBlackCoinCount(), 7);
        assertEquals(board.getRedCoinCount(), 1);
        assertEquals(player.getScore(), 2);
    }

    @Test
    public void testShouldNotExecuteMultiStrikeForLessThanOneBlackCoin() throws InvalidBoardException {
        Board board = Board.buildBoard(1, 1);
        Player player = Player.initializePlayer("testPalyer");

        GameAction multiStrike = new MultiStrikeAction();

        assertFalse(multiStrike.canExecute(board, player));
    }
}
