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

public class ReceiveMoneyActivity extends AppCompatActivity {
    private TextView currentBalanceTextView;
    private EditText senderIdEditText;
    private EditText amountEditText;
    private Button receiveButton;
    private UserManager userManager;
    private TransactionManager transactionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receive_money);

        userManager = UserManager.getInstance();
        transactionManager = TransactionManager.getInstance();

        currentBalanceTextView = findViewById(R.id.currentBalanceTextView);
        senderIdEditText = findViewById(R.id.senderIdEditText);
        amountEditText = findViewById(R.id.amountEditText);
        receiveButton = findViewById(R.id.receiveButton);

        updateBalance();

        receiveButton.setOnClickListener(v -> receiveMoney());
    }

    private void receiveMoney() {
        String senderId = senderIdEditText.getText().toString().trim();
        String amountStr = amountEditText.getText().toString().trim();

        if (senderId.isEmpty() || amountStr.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            double amount = Double.parseDouble(amountStr);
            User currentUser = userManager.getCurrentUser();

            transactionManager.receiveMoney(currentUser, senderId, amount);
            Toast.makeText(this, getString(R.string.transaction_successful), Toast.LENGTH_SHORT).show();
            updateBalance();
            senderIdEditText.setText("");
            amountEditText.setText("");
            finish();
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Invalid amount", Toast.LENGTH_SHORT).show();
        }
    }

    private void updateBalance() {
        User currentUser = userManager.getCurrentUser();
        currentBalanceTextView.setText(String.format(getString(R.string.balance), currentUser.getBalance()));
    }
}
