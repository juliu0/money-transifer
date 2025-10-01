package com.moneytransfer.app.managers;

import com.moneytransfer.app.models.Bet;
import com.moneytransfer.app.models.Game;
import com.moneytransfer.app.models.User;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class BettingManager {
    private static BettingManager instance;
    private List<Bet> bets;
    private List<Game> games;

    private BettingManager() {
        bets = new ArrayList<>();
        games = new ArrayList<>();
        initializeSampleGames();
    }

    public static BettingManager getInstance() {
        if (instance == null) {
            instance = new BettingManager();
        }
        return instance;
    }

    private void initializeSampleGames() {
        games.add(new Game("1", "Team A vs Team B", "Football match", Game.GameStatus.UPCOMING));
        games.add(new Game("2", "Player X vs Player Y", "Tennis match", Game.GameStatus.LIVE));
        games.add(new Game("3", "Team C vs Team D", "Basketball game", Game.GameStatus.UPCOMING));
    }

    public boolean placeBet(User user, String gameId, double amount, String prediction, double odds) {
        if (user.deductBalance(amount)) {
            String betId = UUID.randomUUID().toString();
            double potentialWinning = amount * odds;
            Bet bet = new Bet(betId, user.getId(), gameId, amount, prediction, potentialWinning);
            bets.add(bet);
            return true;
        }
        return false;
    }

    public List<Game> getAvailableGames() {
        List<Game> availableGames = new ArrayList<>();
        for (Game game : games) {
            if (game.getStatus() != Game.GameStatus.COMPLETED) {
                availableGames.add(game);
            }
        }
        return availableGames;
    }

    public List<Bet> getUserBets(String userId) {
        List<Bet> userBets = new ArrayList<>();
        for (Bet bet : bets) {
            if (bet.getUserId().equals(userId)) {
                userBets.add(bet);
            }
        }
        return userBets;
    }

    public boolean resolveBet(User user, String betId, boolean won) {
        for (Bet bet : bets) {
            if (bet.getId().equals(betId) && 
                bet.getUserId().equals(user.getId()) &&
                bet.getStatus() == Bet.BetStatus.PENDING) {
                
                if (won) {
                    user.addBalance(bet.getPotentialWinning());
                    bet.setStatus(Bet.BetStatus.WON);
                } else {
                    bet.setStatus(Bet.BetStatus.LOST);
                }
                return true;
            }
        }
        return false;
    }

    public List<Bet> getBets() {
        return new ArrayList<>(bets);
    }

    public List<Game> getGames() {
        return new ArrayList<>(games);
    }
}
