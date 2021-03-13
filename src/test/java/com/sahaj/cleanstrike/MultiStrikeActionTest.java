package com.sahaj.cleanstrike;

import org.junit.Test;

import static org.junit.Assert.*;

public class MultiStrikeActionTest{

    @Test
    public void testShouldIncrementScoreByTwoForMultiStrike() throws InvalidBoardException {
        GameAction multiStrike = new MultiStrikeAction();
        Player player = Player.initializePlayer("testPlayer");
        Board board = Board.buildBoard(1, 9);

        assertTrue(multiStrike.canExecute(board, player));
        multiStrike.execute(board, player);
        assertEquals(player.getScore(), 2);
    }

    @Test
    public void testShouldDecrementBlackCoinCountByTwo() throws InvalidBoardException {
        GameAction multiStrike = new MultiStrikeAction();
        Player player = Player.initializePlayer("testPlayer");
        Board board = Board.buildBoard(1, 6);

        assertTrue(multiStrike.canExecute(board, player));
        multiStrike.execute(board, player);
        assertEquals(board.getBlackCoinCount(), 4);
    }

    @Test
    public void testShouldNotExecuteMultiStrikeForLessThanOneBlackCoin() throws InvalidBoardException {
        Board board = Board.buildBoard(1, 1);
        Player player = Player.initializePlayer("testPlayer");

        GameAction multiStrike = new MultiStrikeAction();

        assertFalse(multiStrike.canExecute(board, player));
    }

    @Test
    public void testShouldExecuteMultiStrikeForMoreThanOneBlackCoin() throws InvalidBoardException {
        Board board = Board.buildBoard(1, 3);
        Player player = Player.initializePlayer("testPlayer");

        GameAction multiStrike = new MultiStrikeAction();

        assertTrue(multiStrike.canExecute(board, player));
    }
}
