package com.sahaj.cleanstrike;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Game {

    private boolean winnerFound;

    public Player getWinner() {
        return winner;
    }

    private Player winner;
    private boolean resultDeclared;
    private List<Player> playerList;
    private List<GameAction> actionRecord = new ArrayList<>();

    public Game(List<Player> playerList) {
        this.playerList = playerList;
    }

    public Game(List<Player> playerList, List<GameAction> gameAction) {
        this.playerList = playerList;
        this.actionRecord = gameAction;

    }


    public static void main(String[] args) throws InvalidBoardException {
        Scanner scan = new Scanner(System.in);

        System.out.println("How many players are playing?");
        int totalPlayers = scan.nextInt();

        List<Player> playerList = new ArrayList<>();
        for (int i = 1; i < totalPlayers + 1; i++) {
            System.out.println("Please provide name of player" + i + "-");
            String playerName = scan.next();
            playerList.add(Player.initializePlayer(playerName));
        }


        Board board = Board.buildDefaultBoard();
        Game game = new Game(playerList);

        int count = 0;
        while (!(game.resultDeclared)) {
            printInputMessage();
            int choice = scan.nextInt();
            GameAction action = getGameAction(choice);
            game.executeAction(board, game.playerList.get(count % totalPlayers), action);
            game.checkIfAnyPlayerWon();
            count++;
        }
    }

    private void executeGameAction(Board board, Player player, GameAction gameAction) {
        executeAction(board, player, gameAction);
    }

    private void executeAction(Board board, Player player, GameAction action) {

        if (this.resultDeclared || board.coinsExhausted()) {
            this.getMatchResult();
        } else if (action.getClass().equals(EndAction.class)) {
            System.out.println("Actions for player " + player.getName() + " have finished.");
            this.getMatchResult();
        } else if (!action.canExecute(board, player)) {
            printInvalidStrikeResult(board, player, action);
        } else {
            System.out.println("Executed " + action.getClass().getSimpleName() + " action");
            action.execute(board, player);
            action.handleIfLastThreeWereFaulty(player);
            printActionResult(board, player);
        }
    }

    private void printInvalidStrikeResult(Board board, Player player, GameAction action) {
        System.out.println("Invalid strike: " + action.getClass().getSimpleName() + " by Player " + player.getName());
        System.out.println("score: " + player.getScore() + " FoulCount: " + player.getFoulCount());
        System.out.println("Board now has:: black coins : " + board.getBlackCoinCount() + " red coins: " + board.getRedCoinCount());
        System.out.println();
    }

    private void printDrawResult() {
        System.out.println("Game finished, stopping execution");
        System.out.println("Match is draw");
    }

    private void printActionResult(Board board, Player player) {
        System.out.println();
        System.out.println("Player Name \t" + "score \t" + "Fouls ");
        System.out.println(player.getName() + "   \t"
                + player.getScore() + "   \t"
                + player.getFoulCount() + "    \t");
        System.out.println();
        System.out.println("Coins \t\t\t" + "Black \t" + "Red ");
        System.out.println("\t\t\t\t"
                + board.getBlackCoinCount() + "\t\t"
                + board.getRedCoinCount());
        System.out.println("---------------------------------------");
    }

    private void getMatchResult() {
        if (this.winnerFound) {
            this.printWinner();
        } else {
            this.printDrawResult();
        }
        this.resultDeclared = true;
        printScoreOfAllPlayers();
    }

    private void printScoreOfAllPlayers() {
        System.out.println();
        System.out.println("Player Name \t" +
                "score \t" + "Fouls ");

        for (Player player : this.playerList) {
            System.out.println(player.getName() + "   \t"
                    + player.getScore() + "   \t"
                    + player.getFoulCount() + "    \t");
        }
    }

    private void printWinner() {
        System.out.println("Game finished, stopping execution");
        System.out.println("winner is: " + winner.getName());

    }

    public void checkIfAnyPlayerWon() {
        if (!(this.playerList.size() < 2)) {
            checkScoreForWinner(this.playerList.get(0), this.playerList.get(1));
        }
    }

    void checkScoreForWinner(Player player1, Player player2) {
        if (player1.getScore() >= 5) {
            int scoreDifference = player1.getScore() - player2.getScore();
            if (scoreDifference >= 3) {
                this.winnerFound = true;
                this.winner = player1;
            }
        } else if (player2.getScore() >= 5) {
            int scoreDifference = player2.getScore() - player1.getScore();
            if (scoreDifference >= 3) {
                this.winnerFound = true;
                this.winner = player2;
            }
        }
        if (this.winnerFound) {
            getMatchResult();
            this.resultDeclared = true;
        }
    }

    public static void printInputMessage() {

        System.out.println("Choose one action");
        System.out.println("1. Strike");
        System.out.println("2. MulitiStrike");
        System.out.println("3. Red Strike");
        System.out.println("4. Striker Strike");
        System.out.println("5. Defunct strike");
        System.out.println("6. None");
    }


    public static GameAction getGameAction(int input) {
        switch (input) {
            case 1:
                return new StrikeAction();
            case 2:
                return new MultiStrikeAction();
            case 3:
                return new RedStrikeAction();
            case 4:
                return new StrikerStrikeAction();
            case 5:
                return new DefunctCoinAction();
            case 6:
                return new EndAction();
        }
        return null;
    }

    public void playGameWithTwoPlayers(Board board) {

        for (int i = 0; !(this.resultDeclared) && i < this.actionRecord.size(); i++) {

            if (i % 2 == 0) {
                executeGameAction(board, this.playerList.get(0), this.actionRecord.get(i));
            } else {
                executeGameAction(board, this.playerList.get(1), this.actionRecord.get(i));
            }
            checkIfAnyPlayerWon();
        }
    }
}
