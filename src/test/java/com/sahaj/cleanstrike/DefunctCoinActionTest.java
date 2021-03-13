package com.sahaj.cleanstrike;

import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class DefunctCoinActionTest {

    @Test
    public void testShouldRemoveBlackCoinWhenBlackCoinIsPresent() throws InvalidBoardException {
        GameAction defunctCoinAction = new DefunctCoinAction();
        Player player = Player.initializePlayer("testPlayer");
        Board board = Board.buildBoard(0, 1);

        assertTrue(defunctCoinAction.canExecute(board, player));
        defunctCoinAction.execute(board, player);

        assertEquals(board.getBlackCoinCount(), 0);
        assertEquals(board.getRedCoinCount(), 0);
        assertEquals(player.getScore(), -2);
        assertEquals(player.getFoulCount(), 1);


        Player player1 = Player.initializePlayer("testPlayer");
        Board board1 = Board.buildBoard(1, 1);

        assertTrue(defunctCoinAction.canExecute(board1, player1));
        defunctCoinAction.execute(board1, player1);

        assertEquals(board1.getBlackCoinCount(), 0);
        assertEquals(board1.getRedCoinCount(), 1);
        assertEquals(player1.getScore(), -2);
        assertEquals(player1.getFoulCount(), 1);

    }

    @Test
    public void testShouldRemoveRedCoinWhenNoBlackCoinAvailable() throws InvalidBoardException {
        GameAction defunctCoin = new DefunctCoinAction();
        Board board = Board.buildBoard(1, 0);
        Player player = Player.initializePlayer("testPlayer");

        assertTrue(defunctCoin.canExecute(board, player));
        defunctCoin.execute(board, player);

        assertEquals(board.getRedCoinCount(), 0);
        assertEquals(board.getBlackCoinCount(), 0);
        assertEquals(player.getFoulCount(), 1);
    }

    @Test
    public void testShouldDecrementScoreByTwoForDefunctAction() throws InvalidBoardException {
        GameAction defunctCoin = new DefunctCoinAction();
        Board board = Board.buildDefaultBoard();
        Player player = Player.initializePlayer("testPlayer");

        assertTrue(defunctCoin.canExecute(board, player));
        defunctCoin.execute(board, player);

        assertEquals(player.getScore(), -2);
        assertEquals(player.getFoulCount(), 1);
    }

    @Test
    public void testShouldResetFoulSuccesiveThreeDefunctAction() throws InvalidBoardException {
        GameAction defunctCoin = new DefunctCoinAction();
        Board board = Board.buildDefaultBoard();
        Player player = Player.initializePlayer("testPalyer");

        assertTrue(defunctCoin.canExecute(board, player));
        defunctCoin.execute(board, player);
        defunctCoin.execute(board, player);

        defunctCoin.execute(board, player);
        defunctCoin.handleIfLastThreeWereFaulty(player);


        assertEquals(player.getFoulCount(), 0);
        assertEquals(player.getScore(), -8);
    }

}