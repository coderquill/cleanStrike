package com.sahaj.cleanstrike;

import junit.framework.TestCase;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class StrikerStrikeActionTest {

    @Test
    public void testShouldExecuteGameActionStrikerStrike() throws InvalidBoardException {
        GameAction strikerStrikeAction = new StrikerStrikeAction();
        Player player = Player.initializePlayer("testPlayer");
        Board board = Board.buildBoard(1, 6);

        assertTrue(strikerStrikeAction.canExecute(board, player));
        strikerStrikeAction.execute(board, player);

        assertEquals(board.getBlackCoinCount(), 6);
        assertEquals(board.getRedCoinCount(), 1);
        assertEquals(player.getScore(), -1);
        assertEquals(player.getFoulCount(), 1);
    }

    @Test
    public void testShouldAddFoulForStrikerStrikeAction() throws InvalidBoardException {
        GameAction strikerStrikeAction = new StrikerStrikeAction();
        Board board = Board.buildDefaultBoard();
        Player player = Player.initializePlayer("testPalyer");

        assertTrue(strikerStrikeAction.canExecute(board, player));
        strikerStrikeAction.execute(board, player);

        assertEquals(player.getFoulCount(), 1);
    }

}
