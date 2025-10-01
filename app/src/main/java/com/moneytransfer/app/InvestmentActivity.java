package com.moneytransfer.app;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.moneytransfer.app.managers.InvestmentManager;
import com.moneytransfer.app.managers.UserManager;
import com.moneytransfer.app.models.User;

public class InvestmentActivity extends AppCompatActivity {
    private TextView currentBalanceTextView;
    private Spinner investmentTypeSpinner;
    private EditText amountEditText;
    private EditText expectedReturnEditText;
    private EditText durationEditText;
    private Button investButton;
    private UserManager userManager;
    private InvestmentManager investmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_investment);

        userManager = UserManager.getInstance();
        investmentManager = InvestmentManager.getInstance();

        currentBalanceTextView = findViewById(R.id.currentBalanceTextView);
        investmentTypeSpinner = findViewById(R.id.investmentTypeSpinner);
        amountEditText = findViewById(R.id.amountEditText);
        expectedReturnEditText = findViewById(R.id.expectedReturnEditText);
        durationEditText = findViewById(R.id.durationEditText);
        investButton = findViewById(R.id.investButton);

        setupSpinner();
        updateBalance();

        investButton.setOnClickListener(v -> createInvestment());
    }

    private void setupSpinner() {
        String[] investmentTypes = {
            getString(R.string.stocks),
            getString(R.string.bonds),
            getString(R.string.mutual_funds)
        };
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
            this, 
            android.R.layout.simple_spinner_item, 
            investmentTypes
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        investmentTypeSpinner.setAdapter(adapter);
    }

    private void createInvestment() {
        String amountStr = amountEditText.getText().toString().trim();
        String expectedReturnStr = expectedReturnEditText.getText().toString().trim();
        String durationStr = durationEditText.getText().toString().trim();
        String investmentType = investmentTypeSpinner.getSelectedItem().toString();

        if (amountStr.isEmpty() || expectedReturnStr.isEmpty() || durationStr.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            double amount = Double.parseDouble(amountStr);
            double expectedReturn = Double.parseDouble(expectedReturnStr);
            int duration = Integer.parseInt(durationStr);
            User currentUser = userManager.getCurrentUser();

            if (investmentManager.createInvestment(currentUser, amount, investmentType, expectedReturn, duration)) {
                Toast.makeText(this, getString(R.string.investment_created), Toast.LENGTH_SHORT).show();
                updateBalance();
                amountEditText.setText("");
                expectedReturnEditText.setText("");
                durationEditText.setText("");
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
