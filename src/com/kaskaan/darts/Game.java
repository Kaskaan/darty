package com.kaskaan.darts;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Game {
    private int numberOfPlayers;
    private int actualPlayerId;
    private int round = 1;
    private List<Darts> listOfGames;
    private List<Darts> listOfWinners = new ArrayList<>();
    private boolean isGameOver = false;
    private int timesToChangeRound;

    private Scanner scanner = new Scanner(System.in);

    public static class Builder {
        private List<Darts> listOfGames = new ArrayList<>();
        private int numberOfPlayers;

        Builder(String playerName) {
            listOfGames.add(new Darts(playerName));
        }

        public Builder newPlayer(String playerName) {
            listOfGames.add(new Darts(playerName));
            numberOfPlayers++;
            return this;
        }

        public Game build() {
            return new Game(this);
        }

    }

    private Game(Builder builder) {
        listOfGames = builder.listOfGames;
        numberOfPlayers = builder.numberOfPlayers;
        timesToChangeRound = numberOfPlayers;
    }

    public void play() {
        showWelcomeMessage();
        while (!isGameOver) {
            showNumberOfPlayers();
            System.out.println("ID: " + actualPlayerId + " | Runda: " + round + " | Aktualny gracz: " + getActualPlayerName());
            calculateBySumOfShotsFromConsoleInput(scanner);
            changeRoundInTheRightMoment();
            movePlayerToListOfWinnersIfHisGameIsOver();
            checkPlayerIdStatus();
        }
    }

    private void showWelcomeMessage() {
        System.out.println("----------------------------------------\n");
        System.out.println(" Darts v 0.0.1 (alpha) (Konrad Lesiak)\n");
        System.out.println("----------------------------------------\n");
    }



    public void setNumberOfPlayers() {
        numberOfPlayers = listOfGames.size();
        System.out.println("Liczba graczy: " + numberOfPlayers);
    }

    public void showNumberOfPlayers() {
        System.out.println("Liczba graczy: " + (numberOfPlayers + 1));
    }

    public void checkPlayerIdStatus() {
        if (actualPlayerId >= numberOfPlayers) {
            actualPlayerId = 0;
        } else
            actualPlayerId += 1;
    }

    public void showListOfPlayers() {
        System.out.println("Lista graczy: ");
        listOfGames.forEach(Game::getPlayerName);
    }

    private static void getPlayerName(Darts e) {
        System.out.println(e.getPlayerName());
    }

    private void showPlayersPoints() {
        System.out.println(listOfGames.get(actualPlayerId));
    }

    private void calculateBySumOfShotsFromConsoleInput(Scanner scanner) {
        try {
            System.out.println("Wpisz sumę trzech rzutów gracza " + getActualPlayerName() + '.');
            listOfGames.get(actualPlayerId).calculateBySumOfShoots(scanner.nextInt());
        } catch (InputMismatchException e) {
            System.out.println("Wpisano niepoprawne dane! Koniec imprezy!");
            isGameOver = true;
        }
    }

    private String getActualPlayerName() throws IndexOutOfBoundsException {
        return listOfGames.get(actualPlayerId).getPlayerName();
    }

    public void movePlayerToListOfWinnersIfHisGameIsOver() {
        if (listOfGames.get(actualPlayerId).isGameOverForThisPlayer()) {
            listOfGames.get(actualPlayerId).setPlayerRound(this.round);
            String playerName = listOfGames.get(actualPlayerId).getPlayerName();
            listOfWinners.add(new Darts(playerName));
        }
    }

    private void changeRoundInTheRightMoment() {
        if (timesToChangeRound != 0) {
            timesToChangeRound--;
        } else {
            timesToChangeRound = numberOfPlayers;
            round++;
        }
    }

    public void showListOfWinners() {
        System.out.println("List of winners: ");
        listOfWinners.forEach(s -> System.out.println(s.getPlayerName()));
    }

    public void showInfoWhenGameIsOver() {
        System.out.println("Game Over!");
        showListOfWinners();
        isGameOver = true;
    }

}