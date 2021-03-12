package com.sahaj.cleanstrike;

import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

public class BoardTest extends TestCase {
  @Test
  public void testShouldCreateDefaultBoardWithCorrectCoinCount(){
      Board newBoard = Board.buildDefaultBoard();

      int redCoinCount = newBoard.getRedCoinCount();
      int blackCoinCount = newBoard.getBlackCoinCount();

      Assert.assertEquals(redCoinCount, 1);
      Assert.assertEquals(blackCoinCount, 9);

  }
}