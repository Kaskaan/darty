package com.kaskaan.darts;

public class Darts {
    private int playerPoints;
    private int playerRound = 1;
    private boolean gameOverForThisPlayer = false;
    private Player player;

    Darts(String playerName) {
        player = new Player(playerName);
        player.setPoints(301);
        playerPoints = player.getPoints();
    }

    public String getPlayerName() {
        return player.getName();
    }

    public int getPlayerRound() {
        return playerRound;
    }

    public void setPlayerRound(int playerRound) {
        this.playerRound = playerRound;
    }

    public void calculateByWriteEachShoot(int firstShoot, int secondShoot, int thirdShoot) {
        int sumOfShoots = firstShoot + secondShoot + thirdShoot;
        player.setPoints(playerPoints -= sumOfShoots);
        checkPoints();
        checkWin();
    }

    public void calculateBySumOfShoots(int sumOfShoots) {
        playerPoints -= sumOfShoots;
        checkPoints();
        checkWin();
    }

    private void checkWin() {
        if (playerPoints <= 0) {
            System.out.println(player.getName() + " WYGRAŁ" + checkGender() + "!!!\n");
        } else {
            System.out.println('\n');
        }
    }

    private void checkPoints() {
        if (playerPoints > 0) {
            System.out.println("Graczowi " + player.getName() + " pozostało tyle punktów do zwycięstwa: " + playerPoints);
        } else
            this.gameOverForThisPlayer = true;
    }

    private String checkGender() {
        if (player.getName().endsWith("a")){
            return "A";
        } return "";
    }

    public boolean isGameOverForThisPlayer() {
        return gameOverForThisPlayer;
    }
}
