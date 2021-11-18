package com.axonactive.demo_spring.dojo.tennisGame;

public class TennisGame {

    private int player1Score;
    private int player2Score;
    private String player1Name;
    private String player2Name;

    public TennisGame(String player1Name, String player2Name) {
        this.player1Name = player1Name;
        this.player2Name = player2Name;
        player1Score = 0;
        player2Score = 0;
    }

    public String showResult() {
        String scorePlayer1 = covertScoreToString(player1Score);
        String scorePlayer2 = covertScoreToString(player2Score);
        return player1Name + " " + scorePlayer1 + ":" + scorePlayer2 + " " + player2Name;
    }

    public String covertScoreToString(int score) {
        return switch (score) {
            case 0 -> "love";
            case 1 -> "fifteen";
            case 2 -> "thirty";
            case 3 -> "forty";
            default -> "";
        };
    }

    public void player1Score() {
        player1Score++;
    }

    public void player2Score() {
        player2Score++;
    }

    public int getPlayer1Score(){
        return player1Score;
    }

    public boolean isEndGame() {
        if (player1Score >=4 || player2Score >=4)
            if (Math.abs(player1Score - player2Score) >= 2)
                return true;
        return false;
    }

    public String getWinner() {
        return player1Score>player2Score?player1Name:player2Name;
    }

    public boolean isDuece() {
        if ((player1Score >=3 || player2Score >=3)&&(player1Score==player2Score))
           return true;
        return false;
    }
}
