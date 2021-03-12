package com.sahaj.cleanstrike;

import junit.framework.Assert;
import junit.framework.TestCase;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class PlayerTest extends TestCase {
  @Test
    public void testShouldCreatePlayerWithProvidedNameAndDefaultScore(){
      Player player1 = Player.initializePlayer("player1");
      int playerScore = player1.getPlayerScore();
      String playerName = player1.getPlayerName();

      assertEquals(playerName, "player1");
      assertEquals(playerScore, 0);
  }
}