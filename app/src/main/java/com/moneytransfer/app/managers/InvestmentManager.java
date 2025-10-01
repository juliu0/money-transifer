package com.moneytransfer.app.managers;

import com.moneytransfer.app.models.Investment;
import com.moneytransfer.app.models.User;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class InvestmentManager {
    private static InvestmentManager instance;
    private List<Investment> investments;

    private InvestmentManager() {
        investments = new ArrayList<>();
    }

    public static InvestmentManager getInstance() {
        if (instance == null) {
            instance = new InvestmentManager();
        }
        return instance;
    }

    public boolean createInvestment(User user, double amount, String investmentType, 
                                   double expectedReturn, int durationMonths) {
        if (user.deductBalance(amount)) {
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.MONTH, durationMonths);
            Date maturityDate = calendar.getTime();

            String investmentId = UUID.randomUUID().toString();
            Investment investment = new Investment(
                investmentId,
                user.getId(),
                amount,
                investmentType,
                expectedReturn,
                maturityDate
            );
            investments.add(investment);
            return true;
        }
        return false;
    }

    public List<Investment> getUserInvestments(String userId) {
        List<Investment> userInvestments = new ArrayList<>();
        for (Investment investment : investments) {
            if (investment.getUserId().equals(userId)) {
                userInvestments.add(investment);
            }
        }
        return userInvestments;
    }

    public boolean withdrawInvestment(User user, String investmentId) {
        for (Investment investment : investments) {
            if (investment.getId().equals(investmentId) && 
                investment.getUserId().equals(user.getId()) &&
                investment.getStatus() == Investment.InvestmentStatus.ACTIVE) {
                
                double totalAmount = investment.getAmount() + investment.getExpectedReturn();
                user.addBalance(totalAmount);
                investment.setStatus(Investment.InvestmentStatus.MATURED);
                return true;
            }
        }
        return false;
    }

    public List<Investment> getInvestments() {
        return new ArrayList<>(investments);
    }
}
