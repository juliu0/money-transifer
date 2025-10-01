package com.moneytransfer.app.models;

import java.util.Date;

public class Transaction {
    private String id;
    private String fromUserId;
    private String toUserId;
    private double amount;
    private Date timestamp;
    private TransactionType type;

    public enum TransactionType {
        SEND, RECEIVE, INVESTMENT, BETTING
    }

    public Transaction(String id, String fromUserId, String toUserId, double amount, TransactionType type) {
        this.id = id;
        this.fromUserId = fromUserId;
        this.toUserId = toUserId;
        this.amount = amount;
        this.type = type;
        this.timestamp = new Date();
    }

    public String getId() {
        return id;
    }

    public String getFromUserId() {
        return fromUserId;
    }

    public String getToUserId() {
        return toUserId;
    }

    public double getAmount() {
        return amount;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public TransactionType getType() {
        return type;
    }
}
