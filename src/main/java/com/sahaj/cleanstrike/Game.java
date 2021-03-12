package com.sahaj.cleanstrike;

import java.util.Scanner;

public class Game {
    public static void main(String[] args) throws InvalidBoardException {
        Scanner scan = new Scanner(System.in);
        Player player1 = Player.initializePlayer("player1");
        Player player2 = Player.initializePlayer("player2");

        Board board = Board.buildDefaultBoard();
        printInputMessage();

        while (!board.isGameFinished()) {
            int choice = scan.nextInt();
            GameAction action = getGameAction(choice);


            if (action.canExecute(board, player1)) {
                action.execute(board, player1);
                System.out.println("black coins : " + board.getBlackCoinCount() + " red coins: " + board.getRedCoinCount());
                System.out.println("Player " + player1.getName() + " score: " + player1.getScore());
            } else{
                System.out.println("Invalid strike");
                System.out.println("black coins : " + board.getBlackCoinCount() + " red coins: " + board.getRedCoinCount());
            }

            if (board.getTotalCoinCount() == 0) {
                System.out.println("game finished");
                return;
            }
        }
    }

    private static void printInputMessage() {
        System.out.println("give choice...");
    }

    public static GameAction getGameAction(int input) {
        if (input == 1) {
            return new StrikeAction();
        } else if (input == 2) {
            return new MultiStrikeAction();
        } else if (input == 3) {
            return new RedStrikeAction();
        }
        return null;
    }
}
