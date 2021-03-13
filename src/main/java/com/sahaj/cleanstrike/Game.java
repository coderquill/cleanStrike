package com.sahaj.cleanstrike;

import javax.swing.*;
import java.util.List;
import java.util.Scanner;

public class Game {

    static boolean winnerFound = false;
    static Player winner;
    static boolean resultDeclared = false;

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
            if (!canContinue)
                return;
        }
    }

    public static void playGameWithOnePlayer(Board board, Player player, List<GameAction> gameActionSequence) {
        for (GameAction action : gameActionSequence) {
            boolean canContinue = executeAction(board, player, action);
            if (!canContinue)
                return;
        }
    }

    public static void executeGameAction(Board board, Player player, GameAction gameAction) {
        executeAction(board, player, gameAction);
    }

    private static boolean executeAction(Board board, Player player, GameAction action) {

        if (action.getClass().equals(EndAction.class)) {
            System.out.println("Actions for player " + player.getName() + " have finished.");
//            if(!winnerFound){ //winnerFound wont ever come here
//                printDrawResult();
//            }
            getMatchResult();
            return winnerFound;
        }

        if(board.isGameFinished()){
//            printDrawResult();
            getMatchResult();
            return true;
        }

        if (!resultDeclared && action.canExecute(board, player)) {
            System.out.println("Executed " + action.getClass().getSimpleName() + " action");
            action.execute(board, player);
            action.handleIfLastThreeWereFaulty(player);
            printActionResult(board, player, action);
        } else {
            printInvalidStrikeResult(board, player, action);
            return false;
        }
        return true;
    }

    private static void printInvalidStrikeResult(Board board, Player player, GameAction action) {
        System.out.println("Invalid strike: " + action.getClass().getSimpleName() + " by Player " + player.getName() );
        System.out.println("score: " + player.getScore() + " FoulCount: " + player.getFoulCount() );
        System.out.println("Board now has:: black coins : " + board.getBlackCoinCount() + " red coins: " + board.getRedCoinCount());
        System.out.println();
    }

    private static void printDrawResult() {
        System.out.println("Game finished, stopping execution");
        System.out.println("Match is draw");
        resultDeclared = true;
    }

    private static void printActionResult(Board board, Player player, GameAction action) {
        System.out.println("Player " + player.getName() + " score: " + player.getScore() + " FoulCount: " + player.getFoulCount() );
        System.out.println("Board now has:: black coins : " + board.getBlackCoinCount() + " red coins: " + board.getRedCoinCount());
        System.out.println();
    }

    private static void getMatchResult() {
//        if (winnerFound || board.isGameFinished()) {
//            System.out.println("Game finished, stopping execution");
//            if (winnerFound) {
//                System.out.println("winner is: " + winner.getName());
//            } else {
//                System.out.println("Match is draw");
//            }
//            return true;
//        }
//        return false;


        if (winnerFound) {
            printWinner();
        } else {
            printDrawResult();
        }
    }

    private static void printWinner() {
        System.out.println("Game finished, stopping execution");
        System.out.println("winner is: " + winner.getName());
        resultDeclared = true;
    }

    public static void checkIfAnyPlayerWon(Player player1, Player player2) {
        checkScoreForWinner(player1, player2);
    }

    static void checkScoreForWinner(Player player1, Player player2) {
        if (player1.getScore() >= 5) {
            int scoreDifference = player1.getScore() - player2.getScore();
            if (scoreDifference >= 3) {
                winnerFound = true;
                winner = player1;
            }
        } else if (player2.getScore() >= 5) {
            int scoreDifference = player2.getScore() - player1.getScore();
            if (scoreDifference >= 3) {
                winnerFound = true;
                winner = player2;
            }
        }
        if (winnerFound) {
            printWinner();
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
        } else if (input == 4) {
            return new StrikerStrikeAction();
        }
        return null;
    }

    public static void playGameWithTwoPlayers(Board board, Player player1, Player player2,
                                              List<GameAction> gameActionSequenceForPlayerOne,
                                              List<GameAction> gameActionSequenceForPlayerTwo){

        int totalTurnsOfEachPlayer = Math.min(gameActionSequenceForPlayerOne.size(), gameActionSequenceForPlayerTwo.size());
        for (int i = 0; i < totalTurnsOfEachPlayer; i++) {
            Game.executeGameAction(board, player1,
                    gameActionSequenceForPlayerOne.get(i));
            Game.checkIfAnyPlayerWon(player1, player2);
            if(Game.resultDeclared || board.isGameFinished())
                break;

            Game.executeGameAction(board, player2,
                    gameActionSequenceForPlayerTwo.get(i));
            Game.checkIfAnyPlayerWon(player1, player2);
            if(Game.resultDeclared || board.isGameFinished())
                break;
        }
    }
}
