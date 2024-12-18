package bank.view;

import bank.model.BankController;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class BankApp extends Application {
    private final BankController bankController = new BankController();

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Banking System");

        // Create UI elements
        Label accountLabel = new Label("Account Number:");
        TextField accountNumberField = new TextField();

        Label holderLabel = new Label("Account Holder:");
        TextField accountHolderField = new TextField();

        Label initialBalanceLabel = new Label("Initial Balance:");
    
        TextField initialBalanceField = new TextField();

        Button createAccountButton = new Button("Create Account");
        createAccountButton.setOnAction(e -> {
            String accountNumber = accountNumberField.getText();
            String accountHolder = accountHolderField.getText();

            try {
                double initialBalance = Double.parseDouble(initialBalanceField.getText());
                bankController.createAccount(accountNumber, accountHolder, initialBalance);
                showAlert("Account created successfully!", "Success");
                
                // Clear fields after creating the account
                accountNumberField.clear();
                accountHolderField.clear();
                initialBalanceField.clear();
            } catch (NumberFormatException ex) {
                showAlert("Please enter a valid number for the initial balance.", "Input Error");
            }
        });

        Label balanceLabel = new Label("Balance:");
        Label balanceValue = new Label();

        Button checkBalanceButton = new Button("Check Balance");
        checkBalanceButton.setOnAction(e -> {
            String accountNumber = accountNumberField.getText();
            try {
                double balance = bankController.getBalance(accountNumber);
                balanceValue.setText("Balance: " + balance);
            } catch (Exception ex) {
                showAlert("Account not found. Please check the account number.", "Error");
            }
        });

        Label transactionAmountLabel = new Label("Transaction Amount:");
        TextField transactionAmountField = new TextField();

        Button depositButton = new Button("Deposit");
        depositButton.setOnAction(e -> {
            String accountNumber = accountNumberField.getText();
            try {
                double amount = Double.parseDouble(transactionAmountField.getText());
                bankController.deposit(accountNumber, amount);
                showAlert("Deposit successful!", "Success");
                transactionAmountField.clear();
            } catch (NumberFormatException ex) {
                showAlert("Please enter a valid number for the deposit amount.", "Input Error");
            }
        });

        Button withdrawButton = new Button("Withdraw");
        withdrawButton.setOnAction(e -> {
            String accountNumber = accountNumberField.getText();
            try {
                double amount = Double.parseDouble(transactionAmountField.getText());
                if (bankController.withdraw(accountNumber, amount)) {
                    showAlert("Withdrawal successful!", "Success");
                } else {
                    showAlert("Insufficient balance!", "Transaction Error");
                }
                transactionAmountField.clear();
            } catch (NumberFormatException ex) {
                showAlert("Please enter a valid number for the withdrawal amount.", "Input Error");
            }
        });

        // Arrange UI components in a layout
        VBox layout = new VBox(10, accountLabel, accountNumberField, holderLabel, accountHolderField,
                               initialBalanceLabel, initialBalanceField, createAccountButton, checkBalanceButton,
                               balanceLabel, balanceValue, transactionAmountLabel, transactionAmountField,
                               depositButton, withdrawButton);
        layout.setPadding(new Insets(20));

        // Set the scene and show the stage
        primaryStage.setScene(new Scene(layout, 400, 500));
        primaryStage.show();
    }

    private void showAlert(String message, String title) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null); // Optional, hides header
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        Application.launch(BankApp.class, args);  // Start with BankApp
    }
}