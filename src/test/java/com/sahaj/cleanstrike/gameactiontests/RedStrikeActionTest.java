package com.sahaj.cleanstrike.gameactiontests;

import com.sahaj.cleanstrike.entities.Board;
import com.sahaj.cleanstrike.entities.Player;
import com.sahaj.cleanstrike.exceptions.InvalidBoardException;
import com.sahaj.cleanstrike.gameactions.GameAction;
import com.sahaj.cleanstrike.gameactions.implementations.RedStrikeAction;
import org.junit.Test;

import static org.junit.Assert.*;

public class RedStrikeActionTest {

    @Test
    public void testShouldNotExecuteRedStrikeWhenNoRedCoinAvailable() throws InvalidBoardException {
        GameAction redStrike = new RedStrikeAction();
        Board board = Board.buildBoard(0, 1);
        Player player = Player.initializePlayer("testPlayer");

        assertFalse(redStrike.canExecute(board, player));
    }

    @Test
    public void testShouldExecuteRedStrikeWhenRedCoinAvailable() throws InvalidBoardException {
        GameAction redStrike = new RedStrikeAction();
        Board board = Board.buildBoard(1, 1);
        Player player = Player.initializePlayer("testPlayer");

        assertTrue(redStrike.canExecute(board, player));
    }

    @Test
    public void testShouldRemoveRedCoinForRedStrike() throws InvalidBoardException {

        GameAction redStrike = new RedStrikeAction();
        Player player = Player.initializePlayer("testPlayer");
        Board board = Board.buildBoard(1, 9);

        assertTrue(redStrike.canExecute(board, player));
        redStrike.execute(board, player);

        assertEquals(board.getRedCoinCount(), 0);
    }

    @Test
    public void testShouldIncreaseScoreByThreeForRedStrike() throws InvalidBoardException {

        GameAction redStrike = new RedStrikeAction();
        Player player = Player.initializePlayer("testPlayer");
        Board board = Board.buildBoard(1, 9);

        assertTrue(redStrike.canExecute(board, player));
        redStrike.execute(board, player);

        assertEquals(player.getScore(), 3);
    }

}
