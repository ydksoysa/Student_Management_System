package edu.ijse.sms.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import edu.ijse.sms.db.DBConnection;


import java.io.IOException;
import java.sql.*;

public class LoginController {
    public AnchorPane RadioButton;
    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private RadioButton adminRadio;

    @FXML
    private RadioButton lecturerRadio;

    @FXML
    private Button loginButton;

    private ToggleGroup roleGroup;

    public void initialize() {
        if (adminRadio == null || lecturerRadio == null) {
            System.err.println("FXML injection failed for RadioButtons");
            return;
        }
        roleGroup = new ToggleGroup();
        adminRadio.setToggleGroup(roleGroup);
        lecturerRadio.setToggleGroup(roleGroup);
    }

    @FXML
    private void handleLogin() {
        String username = usernameField.getText();
        String password = passwordField.getText();
        String role = adminRadio.isSelected() ? "Admin" : lecturerRadio.isSelected() ? "Lecturer" : null;

        System.out.println("Login attempt - Username: " + username + ", Role: " + role);

        if (username.isEmpty() || password.isEmpty() || role == null) {
            showAlert("Error", "Please fill all fields and select a role.");
            return;
        }

        if (authenticate(username, password, role)) {
            System.out.println("Authentication successful, navigating to " + role + " dashboard");
            navigateToDashboard(role);
        } else {
            System.out.println("Authentication failed for " + username);
            showAlert("Error", "Invalid username, password, or role.");
        }
    }

    private boolean authenticate(String username, String password, String role) {


        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // Add this line
            try (Connection conn = DBConnection.getConnection();
                 PreparedStatement stmt = conn.prepareStatement("SELECT * FROM users WHERE username = ? AND password = ? AND role = ?")) {
                stmt.setString(1, username);
                stmt.setString(2, password); // Note: Use hashed passwords in production
                stmt.setString(3, role);
                ResultSet rs = stmt.executeQuery();
                boolean isValid = rs.next();
                System.out.println("Authentication result: " + isValid);
                return isValid;
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Database error: " + e.getMessage());
            showAlert("Database Error", "Unable to connect to the database: " + e.getMessage());
            return false;
        }
    }

    private void navigateToDashboard(String role) {
        try {
            Stage stage = (Stage) loginButton.getScene().getWindow();
            Pane newContent = null;
            String fxmlPath = "/view/" + (role.equals("Admin") ? "AdminDashboard.fxml" : "LecturerDashboard.fxml");
            System.out.println("Attempting to load FXML: " + fxmlPath);

            newContent = FXMLLoader.load(getClass().getResource(fxmlPath));
            if (newContent != null) {
                stage.getScene().setRoot(newContent);
                stage.setTitle(role + " Dashboard");
                System.out.println("Successfully navigated to " + role + " dashboard");
            } else {
                System.out.println("Failed to load FXML content for " + fxmlPath);
                showAlert("Error", "Failed to load " + role + " dashboard.");
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Navigation error for " + role + ": " + e.getMessage());
            showAlert("Error", "Failed to load " + role + " dashboard: " + e.getMessage());
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
