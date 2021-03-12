package com.sahaj.cleanstrike;

import junit.framework.TestCase;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class PlayerTest extends TestCase {
  @Test
    public void testShouldCreatePlayerWithProvidedNameAndDefaultScore(){
      Player player1 = Player.initializePlayer("player1");
      int playerScore = player1.getScore();
      String playerName = player1.getName();

      assertEquals(playerName, "player1");
      assertEquals(playerScore, 0);
  }

  public void testShouldGetPlayerName() {
    Player player1 = Player.initializePlayer("player1");
    String playerName = player1.getName();

    assertEquals(playerName, "player1");
  }

  public void testShouldGetPlayerScore() {
    Player player1 = Player.initializePlayer("player1");
    int playerScore = player1.getScore();

    assertEquals(playerScore, 0);
  }

  public void testShouldIncrementPlayerScoreByProvidedAmount() {
    Player player1 = Player.initializePlayer("player1");
    int initialPlayerScore = player1.getScore();
    int incrementScoreByONe = 1;
    int incrementScoreByTwo = 2;

    player1.incrementScore(incrementScoreByONe);
    player1.incrementScore(incrementScoreByTwo);

    assertEquals(player1.getScore(), initialPlayerScore+incrementScoreByONe+incrementScoreByTwo);
  }

}