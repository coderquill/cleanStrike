package com.sahaj.cleanstrike.gameactiontests;

import com.sahaj.cleanstrike.entities.Board;
import com.sahaj.cleanstrike.entities.Player;
import com.sahaj.cleanstrike.exceptions.InvalidBoardException;
import com.sahaj.cleanstrike.gameactions.GameAction;
import com.sahaj.cleanstrike.gameactions.implementations.StrikerStrikeAction;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class StrikerStrikeActionTest {

    @Test
    public void testShouldDecrementScoreByOneForStrikerStrike() throws InvalidBoardException {
        GameAction strikerStrikeAction = new StrikerStrikeAction();
        Player player = Player.initializePlayer("testPlayer");
        Board board = Board.buildBoard(1, 6);

        strikerStrikeAction.execute(board, player);

        assertEquals(player.getScore(), -1);
    }

    @Test
    public void testShouldAddFoulForStrikerStrike() throws InvalidBoardException {
        GameAction strikerStrikeAction = new StrikerStrikeAction();
        Board board = Board.buildDefaultBoard();
        Player player = Player.initializePlayer("testPalyer");

        assertTrue(strikerStrikeAction.canExecute(board, player));
        strikerStrikeAction.execute(board, player);

        assertEquals(player.getFoulCount(), 1);
    }

    @Test
    public void testShouldNotChangeBlackCoinCountForStrikerStrike() throws InvalidBoardException {
        GameAction strikerStrikeAction = new StrikerStrikeAction();
        Board board = Board.buildBoard(1, 6);
        Player player = Player.initializePlayer("testPalyer");

        assertTrue(strikerStrikeAction.canExecute(board, player));
        strikerStrikeAction.execute(board, player);

        assertEquals(board.getBlackCoinCount(), 6);
    }

    @Test
    public void testShouldNotChangeRedCoinCountForStrikerStrike() throws InvalidBoardException {
        GameAction strikerStrikeAction = new StrikerStrikeAction();
        Board board = Board.buildBoard(2, 6);
        Player player = Player.initializePlayer("testPalyer");

        assertTrue(strikerStrikeAction.canExecute(board, player));
        strikerStrikeAction.execute(board, player);

        assertEquals(board.getRedCoinCount(), 2);
    }

}
