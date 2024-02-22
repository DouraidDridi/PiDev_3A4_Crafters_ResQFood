package tn.esprit.controllers;

import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.util.Duration;
import tn.esprit.entities.*;
import tn.esprit.services.ServiceUser;

import java.io.IOException;
import java.util.Objects;

public class AjouterUser {

    @FXML
    private ChoiceBox<String> roleChoiceBox;

    @FXML
    private TextField nom;

    @FXML
    private TextField prenom;

    @FXML
    private TextField email;

    @FXML
    private PasswordField password;

    @FXML
    private Button registerButton;

    @FXML
    private Label successMessage;


    @FXML
    private Button Login;

    @FXML
    private Text nomError;

    @FXML
    private Text prenomError;

    @FXML
    private Text emailError;

    @FXML
    private Text passwordError;

    @FXML
    private ServiceUser serviceUser;

    @FXML
    private ProgressIndicator loadingIndicator;

    public AjouterUser() {
        this.serviceUser = new ServiceUser();
    }

    @FXML
    void initialize() {
        roleChoiceBox.getItems().addAll("User", "Donateur", "RespRestau", "RespAssoc", "Livreur", "Agriculteure");
    }

    private boolean isValidEmail(String email) {
        // Add your email validation logic here
        // For simplicity, I'll use a basic email pattern matching
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        return email.matches(emailRegex);
    }

    private void handleError(TextField textField, Text errorText, String errorMessage) {
        // Display the error message in the text field
        textField.setStyle("-fx-border-color: red;");
        errorText.setText(errorMessage);

    }

    @FXML
    void handleRegisterButton(ActionEvent event) {
        clearErrorMessages(); // Clear previous error messages
        loadingIndicator.setVisible(true);

        String nomValue = nom.getText();
        String prenomValue = prenom.getText();
        String emailValue = email.getText();
        String passwordValue = password.getText();
        String selectedRole = roleChoiceBox.getValue();

        if (nomValue.isEmpty()) {
            handleError(nom, nomError, "Nom is required");
            return;
        }

        if (prenomValue.isEmpty()) {
            handleError(prenom, prenomError, "Prenom is required");
            return;
        }

        if (emailValue.isEmpty()) {
            handleError(email, emailError, "Email is required");
            return;
        } else if (!isValidEmail(emailValue)) {
            handleError(email, emailError, "Invalid email address");
            return;
        }

        if (passwordValue.isEmpty()) {
            handleError(password, passwordError, "Password is required");
            return;
        }

        // Check if the email already exists in the database
        if (serviceUser.emailExists(emailValue)) {
            handleError(email, emailError, "Email already exists");
            return;
        }

        try {
            // Rest of your code for user creation and database interaction

            if (validateInputs()) {
                // Rest of your code for user creation and database interaction
                User newUser;

                switch (selectedRole) {
                    case "Donateur":
                        newUser = new Donateur();
                        break;
                    case "RespRestau":
                        newUser = new RespResto();
                        break;
                    case "RespAssoc":
                        newUser = new RespAssoc();
                        break;
                    case "Admin":
                        newUser = new Admin();
                        break;
                    case "Agriculteure":
                        newUser = new Agriculteur();
                        break;
                    default:
                        newUser = new User();
                        break;
                }

                // Set common attributes
                newUser.setNom(nomValue);
                newUser.setPrenom(prenomValue);
                newUser.setEmail(emailValue);
                newUser.setPassword(passwordValue);

                // Add the new user to the database with the selected role
                serviceUser.ajouter(newUser, selectedRole);
                // Show a success message
                successMessage.setText("User added successfully!");

                // if register success, navigate to login
                ActionEvent actionEvent = new ActionEvent();
                navigatetoAjouterUserAction(actionEvent);

            } else {
                loadingIndicator.setVisible(false);          }
        } catch (Exception e) {
            // Show an error message for any exception
            handleError(email, emailError, "Exception: " + e.getMessage());
            loadingIndicator.setVisible(false);
        }
    }

    private boolean validateInputs() {
        // Validation logic, if needed
        return true;
    }

    private void clearErrorMessages() {
        nomError.setText("");
        prenomError.setText("");
        emailError.setText("");
        passwordError.setText("");

        nom.setStyle(null);
        prenom.setStyle(null);
        email.setStyle(null);
        password.setStyle(null);
    }

    private void navigatetoAjouterUserAction(ActionEvent actionEvent) {
        try {
            // Create a PauseTransition with a delay of 5 seconds
            PauseTransition pauseTransition = new PauseTransition(Duration.seconds(5));

            // Set the action to be performed after the delay
            pauseTransition.setOnFinished(e -> {
                try {
                    // Load the FXML file for the ConnecterUser scene
                    Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/ConnecterUser.fxml")));

                    // Switch to the ConnecterUser scene
                    registerButton.getScene().setRoot(root);
                } catch (IOException ex) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setContentText("Sorry");
                    alert.setTitle("Error");
                    alert.show();
                }
            });

            // Start the PauseTransition
            pauseTransition.play();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void navigateToConnecterUser() {
        try {
            // Load the FXML file for the ConnecterUser scene
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/ConnecterUser.fxml")));

            // Switch to the ConnecterUser scene
            Login.getScene().setRoot(root);
        } catch (IOException ex) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Sorry");
            alert.setTitle("Error");
            alert.show();
        }
    }
}
