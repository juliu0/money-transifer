package com.moneytransfer.app;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.moneytransfer.app.managers.TransactionManager;
import com.moneytransfer.app.managers.UserManager;
import com.moneytransfer.app.models.User;

public class SendMoneyActivity extends AppCompatActivity {
    private TextView currentBalanceTextView;
    private EditText recipientIdEditText;
    private EditText amountEditText;
    private Button sendButton;
    private UserManager userManager;
    private TransactionManager transactionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_money);

        userManager = UserManager.getInstance();
        transactionManager = TransactionManager.getInstance();

        currentBalanceTextView = findViewById(R.id.currentBalanceTextView);
        recipientIdEditText = findViewById(R.id.recipientIdEditText);
        amountEditText = findViewById(R.id.amountEditText);
        sendButton = findViewById(R.id.sendButton);

        updateBalance();

        sendButton.setOnClickListener(v -> sendMoney());
    }

    private void sendMoney() {
        String recipientId = recipientIdEditText.getText().toString().trim();
        String amountStr = amountEditText.getText().toString().trim();

        if (recipientId.isEmpty() || amountStr.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            double amount = Double.parseDouble(amountStr);
            User currentUser = userManager.getCurrentUser();

            if (transactionManager.sendMoney(currentUser, recipientId, amount)) {
                Toast.makeText(this, getString(R.string.transaction_successful), Toast.LENGTH_SHORT).show();
                updateBalance();
                recipientIdEditText.setText("");
                amountEditText.setText("");
                finish();
            } else {
                Toast.makeText(this, getString(R.string.insufficient_balance), Toast.LENGTH_SHORT).show();
            }
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Invalid amount", Toast.LENGTH_SHORT).show();
        }
    }

    private void updateBalance() {
        User currentUser = userManager.getCurrentUser();
        currentBalanceTextView.setText(String.format(getString(R.string.balance), currentUser.getBalance()));
    }
}
