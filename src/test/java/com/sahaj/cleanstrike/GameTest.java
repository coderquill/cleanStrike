package com.sahaj.cleanstrike;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class GameTest {

    @Test
    public void testGameExecutionWhenMultipleSingeStrikeActionsOccur() throws InvalidBoardException {
        List<GameAction> gameActionSequence = Arrays.asList(
                new StrikeAction(),
                new StrikeAction(),
                new StrikeAction(),
                new StrikeAction(),
                new StrikeAction(),
                new StrikeAction()
        );

        Player player = Player.initializePlayer("Player1");
        Board board = Board.buildDefaultBoard();
        Game.playGameWithOnePlayer(board, player,
                gameActionSequence);
        assertEquals(player.getScore(), 6);
        assertEquals(board.getBlackCoinCount(), 3);
    }

    @Test
    public void testGameExecutionForGivenGameActionSequence() throws InvalidBoardException {
        List<GameAction> gameActionSequence = Arrays.asList(
                new StrikerStrikeAction(),
                new StrikeAction(),
                new MultiStrikeAction(),
                new RedStrikeAction(),
                new DefunctCoinAction(),
                new MultiStrikeAction(),
                new StrikeAction(),
                new DefunctCoinAction());

        Player player = Player.initializePlayer("Player1");
        Game.playGameWithOnePlayer(Board.buildDefaultBoard(), player,
                gameActionSequence);
        assertEquals(player.getScore(), 3);
    }

    @Test
    public void testGameExecutionForActionSequenceWithFoulActions() throws InvalidBoardException {
        List<GameAction> gameActionSequence = Arrays.asList(
                new StrikerStrikeAction(),
                new StrikeAction(),
                new MultiStrikeAction(),
                new RedStrikeAction(),
                new DefunctCoinAction(),
                new DefunctCoinAction(),
                new StrikeAction(),
                new MultiStrikeAction(),
                new StrikeAction(),
                new StrikeAction(),
                new DefunctCoinAction());

        Player player = Player.initializePlayer("Player1");
        Game.playGameWithOnePlayer(Board.buildDefaultBoard(), player,
                gameActionSequence);
        assertEquals(player.getScore(), 4);
    }

    @Test
    public void testGameExecutionForOnlyFoulActions() throws InvalidBoardException {
        List<GameAction> gameActionSequence = Arrays.asList(
                new StrikerStrikeAction(),
                new DefunctCoinAction(),
                new DefunctCoinAction(),
                new StrikerStrikeAction(),
                new DefunctCoinAction());

        Player player = Player.initializePlayer("Player1");
        Game.playGameWithOnePlayer(Board.buildDefaultBoard(), player,
                gameActionSequence);
        assertEquals(player.getScore(), -12);
    }

    @Test
    public void testGameExecutionForSuccessiveThreeFaultyActions() throws InvalidBoardException {
        List<GameAction> gameActionSequence = Arrays.asList(
                new StrikeAction(),
                new MultiStrikeAction(),
                new DefunctCoinAction(),
                new StrikerStrikeAction(),
                new DefunctCoinAction(),
                new RedStrikeAction()
        );

        Player player = Player.initializePlayer("Player1");
        Game.playGameWithOnePlayer(Board.buildDefaultBoard(), player,
                gameActionSequence);
        assertEquals(player.getScore(), -1);
    }

    @Test
    public void testGameExecutionForOnlyDefunctCoinAction() throws InvalidBoardException {
        List<GameAction> gameActionSequence = Arrays.asList(
                new DefunctCoinAction(),
                new DefunctCoinAction(),
                new DefunctCoinAction(),
                new DefunctCoinAction(),
                new DefunctCoinAction(),
                new DefunctCoinAction()
        );

        Player player = Player.initializePlayer("Player1");
        Game.playGameWithOnePlayer(Board.buildDefaultBoard(), player,
                gameActionSequence);
        assertEquals(player.getScore(), -18);
    }

    @Test
    public void testShouldBeWonByPlayerOne() throws InvalidBoardException {
        List<GameAction> gameActionSequenceForPlayerOne = Arrays.asList(
                new StrikeAction(),
                new MultiStrikeAction(),
                new RedStrikeAction(),
                new StrikerStrikeAction(),
                new DefunctCoinAction(),
                new EndAction()
        );

        List<GameAction> gameActionSequenceForPlayerTwo = Arrays.asList(
                new StrikeAction(),
                new MultiStrikeAction(),
                new RedStrikeAction(),
                new StrikerStrikeAction(),
                new DefunctCoinAction(),
                new EndAction()
        );

        Board board = Board.buildDefaultBoard();
        Player player1 = Player.initializePlayer("testPlayer1");
        Player player2 = Player.initializePlayer("testPlayer2");

        Game.playGameWithTwoPlayers(board, player1, player2, gameActionSequenceForPlayerOne, gameActionSequenceForPlayerTwo);

        assertEquals(player1.getScore(), 6);
        assertEquals(player2.getScore(), 3);

        assertEquals(board.getBlackCoinCount(), 3);
        assertEquals(Game.winner.getName(), "testPlayer1");
    }

    @Test
    public void testShouldReturnDrawResult() throws InvalidBoardException {
        List<GameAction> gameActionSequenceForPlayerOne = Arrays.asList(
                new StrikeAction(),
                new StrikeAction(),
                new StrikeAction(),
                new StrikeAction(),
                new StrikeAction(),
                new StrikeAction(),
                new StrikeAction(),
                new EndAction()
        );

        List<GameAction> gameActionSequenceForPlayerTwo = Arrays.asList(
                new StrikeAction(),
                new StrikeAction(),
                new StrikeAction(),
                new StrikeAction(),
                new StrikeAction(),
                new StrikeAction(),
                new StrikeAction(),
                new EndAction()
        );

        Board board = Board.buildDefaultBoard();
        Player player1 = Player.initializePlayer("testPlayer1");
        Player player2 = Player.initializePlayer("testPlayer2");

        Game.playGameWithTwoPlayers(board, player1, player2, gameActionSequenceForPlayerOne, gameActionSequenceForPlayerTwo);

        assertEquals(player1.getScore(), 5);
        assertEquals(player2.getScore(), 4);

        assertEquals(board.getBlackCoinCount(), 0);
        assertNull(Game.winner);
    }

    @Test
    public void testShouldBeWonByPlayerTwo() throws InvalidBoardException {

        List<GameAction> gameActionSequenceForPlayerOne = Arrays.asList(
                new StrikeAction(),
                new StrikeAction(),
                new StrikeAction(),
                new StrikeAction(),
                new EndAction()
        );

        List<GameAction> gameActionSequenceForPlayerTwo = Arrays.asList(
                new StrikeAction(),
                new MultiStrikeAction(),
                new MultiStrikeAction(),
                new RedStrikeAction(),
                new EndAction()
        );

        Board board = Board.buildDefaultBoard();
        Player player1 = Player.initializePlayer("testPlayer1");
        Player player2 = Player.initializePlayer("testPlayer2");

        Game.playGameWithTwoPlayers(board, player1, player2, gameActionSequenceForPlayerOne, gameActionSequenceForPlayerTwo);

        assertEquals(Game.winner, player2);
    }
}