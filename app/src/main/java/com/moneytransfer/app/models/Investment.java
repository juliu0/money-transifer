package com.moneytransfer.app.models;

import java.util.Date;

public class Investment {
    private String id;
    private String userId;
    private double amount;
    private String investmentType;
    private double expectedReturn;
    private Date startDate;
    private Date maturityDate;
    private InvestmentStatus status;

    public enum InvestmentStatus {
        ACTIVE, MATURED, CANCELLED
    }

    public Investment(String id, String userId, double amount, String investmentType, 
                     double expectedReturn, Date maturityDate) {
        this.id = id;
        this.userId = userId;
        this.amount = amount;
        this.investmentType = investmentType;
        this.expectedReturn = expectedReturn;
        this.startDate = new Date();
        this.maturityDate = maturityDate;
        this.status = InvestmentStatus.ACTIVE;
    }

    public String getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public double getAmount() {
        return amount;
    }

    public String getInvestmentType() {
        return investmentType;
    }

    public double getExpectedReturn() {
        return expectedReturn;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getMaturityDate() {
        return maturityDate;
    }

    public InvestmentStatus getStatus() {
        return status;
    }

    public void setStatus(InvestmentStatus status) {
        this.status = status;
    }
}
