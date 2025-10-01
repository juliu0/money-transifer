package com.moneytransfer.app.managers;

import com.moneytransfer.app.models.Transaction;
import com.moneytransfer.app.models.User;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TransactionManager {
    private static TransactionManager instance;
    private List<Transaction> transactions;

    private TransactionManager() {
        transactions = new ArrayList<>();
    }

    public static TransactionManager getInstance() {
        if (instance == null) {
            instance = new TransactionManager();
        }
        return instance;
    }

    public boolean sendMoney(User sender, String recipientId, double amount) {
        if (sender.deductBalance(amount)) {
            String transactionId = UUID.randomUUID().toString();
            Transaction transaction = new Transaction(
                transactionId, 
                sender.getId(), 
                recipientId, 
                amount, 
                Transaction.TransactionType.SEND
            );
            transactions.add(transaction);
            return true;
        }
        return false;
    }

    public void receiveMoney(User receiver, String senderId, double amount) {
        receiver.addBalance(amount);
        String transactionId = UUID.randomUUID().toString();
        Transaction transaction = new Transaction(
            transactionId, 
            senderId, 
            receiver.getId(), 
            amount, 
            Transaction.TransactionType.RECEIVE
        );
        transactions.add(transaction);
    }

    public List<Transaction> getTransactions() {
        return new ArrayList<>(transactions);
    }

    public List<Transaction> getUserTransactions(String userId) {
        List<Transaction> userTransactions = new ArrayList<>();
        for (Transaction transaction : transactions) {
            if (transaction.getFromUserId().equals(userId) || 
                transaction.getToUserId().equals(userId)) {
                userTransactions.add(transaction);
            }
        }
        return userTransactions;
    }
}
