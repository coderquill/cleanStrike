package com.sahaj.cleanstrike;

import junit.framework.TestCase;
import org.junit.Test;

import static org.junit.Assert.*;

public class RedStrikeActionTest {

    @Test
    public void testShouldExecuteGameActionRedStrike() throws InvalidBoardException {

        GameAction redStrike = new RedStrikeAction();
        Player player = Player.initializePlayer("testPlayer");
        Board board = Board.buildBoard(1, 9);

        assertTrue(redStrike.canExecute(board, player));
        redStrike.execute(board, player);

        assertEquals(board.getBlackCoinCount(), 9);
        assertEquals(board.getRedCoinCount(), 0);
        assertEquals(player.getScore(), 3);
    }

    @Test
    public void testShouldNotExecuteRedStrikeWhenNoRedCoinAvailable() throws InvalidBoardException {
        GameAction redStrike = new RedStrikeAction();
        Board board = Board.buildBoard(0, 1);
        Player player = Player.initializePlayer("testPlayer");

        assertFalse(redStrike.canExecute(board, player));
    }

}
