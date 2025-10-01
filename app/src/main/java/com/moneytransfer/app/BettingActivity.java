package com.moneytransfer.app;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.moneytransfer.app.managers.BettingManager;
import com.moneytransfer.app.managers.UserManager;
import com.moneytransfer.app.models.Game;
import com.moneytransfer.app.models.User;
import java.util.ArrayList;
import java.util.List;

public class BettingActivity extends AppCompatActivity {
    private TextView currentBalanceTextView;
    private Spinner gameSpinner;
    private EditText amountEditText;
    private EditText predictionEditText;
    private EditText oddsEditText;
    private Button placeBetButton;
    private UserManager userManager;
    private BettingManager bettingManager;
    private List<Game> availableGames;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_betting);

        userManager = UserManager.getInstance();
        bettingManager = BettingManager.getInstance();

        currentBalanceTextView = findViewById(R.id.currentBalanceTextView);
        gameSpinner = findViewById(R.id.gameSpinner);
        amountEditText = findViewById(R.id.amountEditText);
        predictionEditText = findViewById(R.id.predictionEditText);
        oddsEditText = findViewById(R.id.oddsEditText);
        placeBetButton = findViewById(R.id.placeBetButton);

        setupGameSpinner();
        updateBalance();

        placeBetButton.setOnClickListener(v -> placeBet());
    }

    private void setupGameSpinner() {
        availableGames = bettingManager.getAvailableGames();
        List<String> gameNames = new ArrayList<>();
        for (Game game : availableGames) {
            gameNames.add(game.getName());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
            this,
            android.R.layout.simple_spinner_item,
            gameNames
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        gameSpinner.setAdapter(adapter);
    }

    private void placeBet() {
        String amountStr = amountEditText.getText().toString().trim();
        String prediction = predictionEditText.getText().toString().trim();
        String oddsStr = oddsEditText.getText().toString().trim();

        if (amountStr.isEmpty() || prediction.isEmpty() || oddsStr.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            double amount = Double.parseDouble(amountStr);
            double odds = Double.parseDouble(oddsStr);
            int selectedGameIndex = gameSpinner.getSelectedItemPosition();
            
            if (selectedGameIndex < 0 || selectedGameIndex >= availableGames.size()) {
                Toast.makeText(this, "Please select a game", Toast.LENGTH_SHORT).show();
                return;
            }

            Game selectedGame = availableGames.get(selectedGameIndex);
            User currentUser = userManager.getCurrentUser();

            if (bettingManager.placeBet(currentUser, selectedGame.getId(), amount, prediction, odds)) {
                Toast.makeText(this, getString(R.string.bet_placed), Toast.LENGTH_SHORT).show();
                updateBalance();
                amountEditText.setText("");
                predictionEditText.setText("");
                oddsEditText.setText("");
                finish();
            } else {
                Toast.makeText(this, getString(R.string.insufficient_balance), Toast.LENGTH_SHORT).show();
            }
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Invalid input", Toast.LENGTH_SHORT).show();
        }
    }

    private void updateBalance() {
        User currentUser = userManager.getCurrentUser();
        currentBalanceTextView.setText(String.format(getString(R.string.balance), currentUser.getBalance()));
    }
}
