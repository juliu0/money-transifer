package com.moneytransfer.app.models;

import java.util.Date;

public class Bet {
    private String id;
    private String userId;
    private String gameId;
    private double amount;
    private String prediction;
    private Date timestamp;
    private BetStatus status;
    private double potentialWinning;

    public enum BetStatus {
        PENDING, WON, LOST, CANCELLED
    }

    public Bet(String id, String userId, String gameId, double amount, String prediction, double potentialWinning) {
        this.id = id;
        this.userId = userId;
        this.gameId = gameId;
        this.amount = amount;
        this.prediction = prediction;
        this.potentialWinning = potentialWinning;
        this.timestamp = new Date();
        this.status = BetStatus.PENDING;
    }

    public String getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public String getGameId() {
        return gameId;
    }

    public double getAmount() {
        return amount;
    }

    public String getPrediction() {
        return prediction;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public BetStatus getStatus() {
        return status;
    }

    public void setStatus(BetStatus status) {
        this.status = status;
    }

    public double getPotentialWinning() {
        return potentialWinning;
    }
}
