package com.sahaj.cleanstrike;

import junit.framework.TestCase;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static java.util.Arrays.asList;
import static junit.framework.Assert.assertEquals;

public class PlayerTest extends TestCase {
    @Test
    public void testShouldCreatePlayerWithProvidedNameAndDefaultScore() {
        Player player1 = Player.initializePlayer("player1");
        int playerScore = player1.getScore();
        String playerName = player1.getName();

        assertEquals(playerName, "player1");
        assertEquals(playerScore, 0);
    }

    @Test
    public void testShouldGetPlayerName() {
        Player player1 = Player.initializePlayer("player1");
        String playerName = player1.getName();

        assertEquals(playerName, "player1");
    }

    @Test
    public void testShouldGetPlayerScore() {
        Player player1 = Player.initializePlayer("player1");
        int playerScore = player1.getScore();

        assertEquals(playerScore, 0);
    }

    @Test
    public void testShouldIncrementPlayerScoreByProvidedAmount() {
        Player player1 = Player.initializePlayer("player1");
        int initialPlayerScore = player1.getScore();
        int incrementScoreByONe = 1;
        int incrementScoreByTwo = 2;

        player1.incrementScore(incrementScoreByONe);
        player1.incrementScore(incrementScoreByTwo);

        assertEquals(player1.getScore(), initialPlayerScore + incrementScoreByONe + incrementScoreByTwo);
    }

    @Test
    public void testShouldGetFoulCount() {
        Player player = Player.initializePlayer("testPlayer");

        assertEquals(player.getFoulCount(), 0);
    }

    @Test
    public void testShouldAddFoul() {
        Player player = Player.initializePlayer("testPlayer");
        player.addFoul();
        assertEquals(player.getFoulCount(), 1);

        player.addFoul();
        assertEquals(player.getFoulCount(), 2);

    }

    @Test
    public void testShouldResetFoulCountAndDecrementScore() {
        Player player = Player.initializePlayer("testPlayer");

        player.handleFoul();
        assertEquals(player.getFoulCount(), 1);
        assertEquals(player.getScore(), 0);

        player.handleFoul();
        assertEquals(player.getFoulCount(), 2);
        assertEquals(player.getScore(), 0);

        player.handleFoul();
        assertEquals(player.getFoulCount(), 0);
        assertEquals(player.getScore(), -1);

        player.handleFoul();
        assertEquals(player.getFoulCount(), 1);
        assertEquals(player.getScore(), -1);

        player.handleFoul();
        assertEquals(player.getFoulCount(), 2);
        assertEquals(player.getScore(), -1);

        player.handleFoul();
        assertEquals(player.getFoulCount(), 0);
        assertEquals(player.getScore(), -2);


    }

    @Test
    public void testShouldAddFoulWhenFoulsLessThanThree() {
        Player player = Player.initializePlayer("testPlayer");

        player.handleFoul();

        assertEquals(player.getFoulCount(), 1);
        assertEquals(player.getScore(), 0);
    }

    @Test
    public void testShouldGetActionResultList() {
        Player player = Player.initializePlayer("testPlayer");

        assertEquals(player.getActionNatureList(), new ArrayList<Boolean>());
    }

    @Test
    public void testShouldAddCurrentActionResult() {
        Player player = Player.initializePlayer("testPlayer");

        player.addCurrentActionResult(ActionType.NON_FOUL_ACTION);

        assertEquals(player.getActionNatureList(), new ArrayList<>(asList(ActionType.NON_FOUL_ACTION)));

        Player player2 = Player.initializePlayer("testPlayer");

        player2.addCurrentActionResult(ActionType.FOUL_ACTION);

        player2.addCurrentActionResult(ActionType.FOUL_ACTION);

        assertEquals(player2.getActionNatureList(), new ArrayList<>(asList(ActionType.FOUL_ACTION, ActionType.FOUL_ACTION)));
    }

    @Test
    public void testShouldDecrementScoreIfLastThreeActionsWereFaulty() {
        Player player = Player.initializePlayer("testPlayer");

        player.getActionNatureList().addAll(Arrays.asList(ActionType.FOUL_ACTION,
                ActionType.NON_FOUL_ACTION, ActionType.FOUL_ACTION, ActionType.FOUL_ACTION, ActionType.FOUL_ACTION));


        player.handleIfLastThreeActionsWereFoulty();
        assertEquals(player.getScore(), -1);
    }

    @Test
    public void testShouldNotChangeScoreIfLastThreeActionsWereNotFaulty() {
        Player player = Player.initializePlayer("testPlayer");

        player.getActionNatureList().addAll(Arrays.asList(ActionType.FOUL_ACTION,
                ActionType.NON_FOUL_ACTION, ActionType.NON_FOUL_ACTION, ActionType.FOUL_ACTION, ActionType.FOUL_ACTION));

        player.handleIfLastThreeActionsWereFoulty();
        assertEquals(player.getScore(), 0);

        Player player1 = Player.initializePlayer("testPlayer1");

        player1.getActionNatureList().addAll(Arrays.asList(ActionType.FOUL_ACTION,
                ActionType.NON_FOUL_ACTION, ActionType.FOUL_ACTION, ActionType.FOUL_ACTION, ActionType.NON_FOUL_ACTION));

        player1.handleIfLastThreeActionsWereFoulty();
        assertEquals(player1.getScore(), 0);
    }


}