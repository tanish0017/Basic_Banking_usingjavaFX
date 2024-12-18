package bank.view;

import bank.controller.AuthController;
import javafx.application.Application;  // Correct import for JavaFX Application
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LoginPage extends Application {  // Ensure LoginPage extends Application
    private final AuthController authController = new AuthController(); // Initialize controller

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Bank Login");

        // Create UI elements
        Label usernameLabel = new Label("Username:");
        TextField usernameField = new TextField();
        
        Label passwordLabel = new Label("Password:");
        PasswordField passwordField = new PasswordField();

        Button loginButton = new Button("Login");
        loginButton.setOnAction(e -> {
            String username = usernameField.getText();
            String password = passwordField.getText();

            if (authController.authenticate(username, password)) {
                showAlert("Login successful!", "Success");
                // Ensure BankApp exists and has a start method that accepts Stage
                BankApp bankApp = new BankApp();
                bankApp.start(primaryStage); 
            } else {
                showAlert("Invalid username or password!", "Error");
            }
        });

        VBox layout = new VBox(10, usernameLabel, usernameField, passwordLabel, passwordField, loginButton);
        layout.setPadding(new Insets(20));

        // Set the scene and show the stage
        primaryStage.setScene(new Scene(layout, 300, 200));
        primaryStage.show();
    }

    private void showAlert(String message, String title) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null); // Optional, hides header
        alert.setContentText(message);
        alert.showAndWait();
    }

    // Main method to launch the application
    public static void main(String[] args) {
        launch(args);  // No need to pass the LoginPage class, launch does this automatically
    }
}