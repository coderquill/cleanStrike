package com.sahaj.cleanstrike;

import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GameTest  {

    @Test
    public void testGameExecutionWhenMultipleSingeStrikeActionsOccur() throws InvalidBoardException {
        List<GameAction> gameActionSequence = Arrays.asList(new StrikeAction(),
                new StrikeAction(),
                new StrikeAction(),
                new StrikeAction(),
                new StrikeAction(),
                new StrikeAction()
               );

        Player player = Player.initializePlayer("Player1");
        Board board = Board.buildDefaultBoard();
        Game.executeGamePlay(board, player,
                gameActionSequence);
        Assert.assertEquals(player.getScore(), 6);
        Assert.assertEquals(board.getBlackCoinCount(), 3);
    }
    @Test
    public void testGameExecutionForGivenGameActionSequence() throws InvalidBoardException {
        List<GameAction> gameActionSequence = Arrays.asList(new StrikerStrikeAction(),
                new StrikeAction(),
                new MultiStrikeAction(),
                new RedStrikeAction(),
                new DefunctCoinAction(),
                new MultiStrikeAction(),
                new StrikeAction(),
                new DefunctCoinAction());

        Player player = Player.initializePlayer("Player1");
        Game.executeGamePlay(Board.buildDefaultBoard(), player,
                gameActionSequence);
        Assert.assertEquals(player.getScore(), 4);
    }
}