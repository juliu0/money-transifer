package com.moneytransfer.app;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.moneytransfer.app.managers.UserManager;
import com.moneytransfer.app.models.User;

public class MainActivity extends AppCompatActivity {
    private TextView balanceTextView;
    private Button sendMoneyButton;
    private Button receiveMoneyButton;
    private Button investButton;
    private Button bettingButton;
    private UserManager userManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userManager = UserManager.getInstance();
        
        balanceTextView = findViewById(R.id.balanceTextView);
        sendMoneyButton = findViewById(R.id.sendMoneyButton);
        receiveMoneyButton = findViewById(R.id.receiveMoneyButton);
        investButton = findViewById(R.id.investButton);
        bettingButton = findViewById(R.id.bettingButton);

        updateBalance();

        sendMoneyButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, SendMoneyActivity.class);
            startActivity(intent);
        });

        receiveMoneyButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ReceiveMoneyActivity.class);
            startActivity(intent);
        });

        investButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, InvestmentActivity.class);
            startActivity(intent);
        });

        bettingButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, BettingActivity.class);
            startActivity(intent);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateBalance();
    }

    private void updateBalance() {
        User currentUser = userManager.getCurrentUser();
        balanceTextView.setText(String.format(getString(R.string.balance), currentUser.getBalance()));
    }
}
