package com.sahaj.cleanstrike;

import com.sahaj.cleanstrike.entities.Board;
import com.sahaj.cleanstrike.exceptions.InvalidBoardException;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

public class BoardTest extends TestCase {

    @Test
    public void testShouldCreateDefaultBoardWithCorrectCoinCount() throws InvalidBoardException {
        Board newBoard = Board.buildBoard(1, 9);

        int redCoinCount = newBoard.getRedCoinCount();
        int blackCoinCount = newBoard.getBlackCoinCount();

        Assert.assertEquals(redCoinCount, 1);
        Assert.assertEquals(blackCoinCount, 9);

    }

    @Test
    public void testShouldGetRedCoinCount() throws InvalidBoardException {
        Board newBoard = Board.buildBoard(1, 9);

        int redCoinCount = newBoard.getRedCoinCount();

        assertEquals(redCoinCount, 1);
    }

    @Test
    public void testShouldGetBlackCoinCount() throws InvalidBoardException {
        Board newBoard = Board.buildBoard(1, 9);

        int blackCoinCount = newBoard.getBlackCoinCount();

        assertEquals(blackCoinCount, 9);
    }

    @Test
    public void testShouldBuildBoardWithValidCoinCounts() throws InvalidBoardException {
        Board newBoard = Board.buildBoard(2, 15);

        int redCoinCount = newBoard.getRedCoinCount();
        int blackCoinCount = newBoard.getBlackCoinCount();

        Assert.assertEquals(redCoinCount, 2);
        Assert.assertEquals(blackCoinCount, 15);
    }

    @Test
    public void testShouldRemoveGivenNumberOfBlackCoinFromBoard() throws InvalidBoardException {
        Board board = Board.buildDefaultBoard();
        int numberOfBlackCoinsToBeRemovedFromBoard = 1;
        int initialBlackCoinCount = board.getBlackCoinCount();

        board.removeBlackCoins(numberOfBlackCoinsToBeRemovedFromBoard);

        int newBlackCoinCount = board.getBlackCoinCount();

        assertEquals(newBlackCoinCount, initialBlackCoinCount - numberOfBlackCoinsToBeRemovedFromBoard);
    }

    @Test
    public void testShouldFinishWhenCoinsAreExhausted() throws InvalidBoardException {
        Board board = Board.buildBoard(0, 0);

        assertTrue(board.coinsExhausted());
    }

    @Test
    public void testShouldNotFinishWhenCoinsArePresent() throws InvalidBoardException {
        Board board = Board.buildBoard(1, 0);
        assertFalse(board.coinsExhausted());

        Board board1 = Board.buildBoard(0, 1);
        assertFalse(board1.coinsExhausted());

        Board board2 = Board.buildBoard(1, 2);
        assertFalse(board2.coinsExhausted());
    }

}