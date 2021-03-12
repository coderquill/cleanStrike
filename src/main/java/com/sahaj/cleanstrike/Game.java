package com.sahaj.cleanstrike;

import java.util.List;
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
            boolean canContinue = executeAction(board, player1, action);
            if(!canContinue)
                return;
        }
    }

    public static void executeGamePlay(Board board, Player player, List<GameAction> gameActionSequence) {
        for (GameAction action : gameActionSequence) {
            boolean canContinue = executeAction(board, player, action);
            if(!canContinue)
            return;
        }
    }

    private static boolean executeAction(Board board, Player player, GameAction action) {
        System.out.println("Executing " + action.getClass().getSimpleName() + " action");
        if(board.isGameFinished()){
            System.out.println("Game finished, stopping execution");
            return false;
        }
        if (action.canExecute(board, player)) {
            action.execute(board, player);
            System.out.println("black coins : " + board.getBlackCoinCount() + " red coins: " + board.getRedCoinCount());
            System.out.println("Player " + player.getName() + " score: " + player.getScore());
        } else{
            System.out.println("Invalid strike");
            System.out.println("black coins : " + board.getBlackCoinCount() + " red coins: " + board.getRedCoinCount());
        }
        return true;
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
        } else if(input == 4){
            return  new StrikerStrikeAction();
        }
        return null;
    }
}
