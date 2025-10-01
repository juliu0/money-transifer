package com.moneytransfer.app.models;

public class Game {
    private String id;
    private String name;
    private String description;
    private GameStatus status;

    public enum GameStatus {
        UPCOMING, LIVE, COMPLETED
    }

    public Game(String id, String name, String description, GameStatus status) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public GameStatus getStatus() {
        return status;
    }

    public void setStatus(GameStatus status) {
        this.status = status;
    }
}
