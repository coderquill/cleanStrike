package com.sahaj.cleanstrike.gameactiontests;

import com.sahaj.cleanstrike.entities.Board;
import com.sahaj.cleanstrike.entities.Player;
import com.sahaj.cleanstrike.exceptions.InvalidBoardException;
import com.sahaj.cleanstrike.gameactions.GameAction;
import com.sahaj.cleanstrike.gameactions.implementations.StrikeAction;
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