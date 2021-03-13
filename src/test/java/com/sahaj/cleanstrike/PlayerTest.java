package com.sahaj.cleanstrike;

import com.sahaj.cleanstrike.entities.Player;
import com.sahaj.cleanstrike.enums.ActionType;
import junit.framework.TestCase;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static java.util.Arrays.asList;

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


        player1.incrementScore(1);
        player1.incrementScore(2);

        assertEquals(player1.getScore(), 3);
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
    public void testShouldResetFoulCountForThirdFoul() {
        Player player = Player.initializePlayer("testPlayer");

        player.handleFoul();
        player.handleFoul();
        player.handleFoul();

        assertEquals(player.getFoulCount(), 0);
    }

    @Test
    public void testShouldDecrementScoreByOneForThirdFoul() {
        Player player = Player.initializePlayer("testPlayer");

        player.handleFoul();
        player.handleFoul();
        player.handleFoul();

        assertEquals(player.getScore(), -1);
    }

    @Test
    public void testShouldAddFoulWhenFoulsLessThanThree() {
        Player player = Player.initializePlayer("testPlayer");

        player.handleFoul();
        player.handleFoul();

        assertEquals(player.getFoulCount(), 2);
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
        player.addCurrentActionResult(ActionType.FOUL_ACTION);


        assertEquals(player.getActionNatureList(), new ArrayList<>(asList(ActionType.NON_FOUL_ACTION, ActionType.FOUL_ACTION)));
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
    }
}