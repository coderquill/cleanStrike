package com.sahaj.cleanstrike;

import com.sahaj.cleanstrike.driver.Game;
import com.sahaj.cleanstrike.entities.Board;
import com.sahaj.cleanstrike.entities.Player;
import com.sahaj.cleanstrike.exceptions.InvalidBoardException;
import com.sahaj.cleanstrike.gameactions.GameAction;
import com.sahaj.cleanstrike.gameactions.implementations.*;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class GameTest {

    @Test
    public void testShouldBeWonByPlayerOne() throws InvalidBoardException {
        Board board = Board.buildDefaultBoard();
        Player player1 = Player.initializePlayer("testPlayer1");
        Player player2 = Player.initializePlayer("testPlayer2");
        List<Player> playerList = Arrays.asList(player1, player2);
        List<GameAction> actionList = Arrays.asList(
                new StrikeAction(),
                new StrikeAction(),

                new MultiStrikeAction(),
                new MultiStrikeAction(),

                new RedStrikeAction(),
                new RedStrikeAction(),

                new StrikeAction(),
                new StrikeAction(),

                new DefunctCoinAction(),
                new DefunctCoinAction(),

                new EndAction(),
                new EndAction()
        );
        Game game = new Game(playerList, actionList);
        game.playGameWithTwoPlayers(board);

        assertEquals(player1.getScore(), 6);
        assertEquals(player2.getScore(), 3);

        assertEquals(board.getBlackCoinCount(), 3);
        assertEquals(game.getWinner().getName(), "testPlayer1");
    }

    @Test
    public void testShouldReturnDrawResult() throws InvalidBoardException {
        Board board = Board.buildDefaultBoard();
        Player player1 = Player.initializePlayer("testPlayer1");
        Player player2 = Player.initializePlayer("testPlayer2");
        List<Player> playerList = Arrays.asList(player1, player2);
        List<GameAction> actionList = Arrays.asList(
                new StrikeAction(),
                new StrikeAction(),

                new StrikeAction(),
                new StrikeAction(),

                new StrikeAction(),
                new StrikeAction(),

                new StrikeAction(),
                new StrikeAction(),

                new StrikeAction(),
                new StrikeAction(),

                new StrikeAction(),
                new StrikeAction(),

                new StrikeAction(),
                new StrikeAction(),

                new EndAction(),
                new EndAction()
        );
        Game game = new Game(playerList, actionList);
        game.playGameWithTwoPlayers(board);

        assertEquals(player1.getScore(), 5);
        assertEquals(player2.getScore(), 4);

        assertEquals(board.getBlackCoinCount(), 0);
        assertNull(game.getWinner());
    }

    @Test
    public void testShouldBeWonByPlayerTwo() throws InvalidBoardException {

        Board board = Board.buildDefaultBoard();
        Player player1 = Player.initializePlayer("testPlayer1");
        Player player2 = Player.initializePlayer("testPlayer2");
        List<Player> playerList = Arrays.asList(player1, player2);
        List<GameAction> actionList = Arrays.asList(
                new StrikeAction(),
                new StrikeAction(),

                new StrikeAction(),
                new MultiStrikeAction(),

                new StrikeAction(),
                new MultiStrikeAction(),

                new StrikeAction(),
                new RedStrikeAction(),

                new EndAction(),
                new EndAction()
        );
        Game game = new Game(playerList, actionList);

        game.playGameWithTwoPlayers(board);

        assertEquals(game.getWinner(), player2);
    }
}