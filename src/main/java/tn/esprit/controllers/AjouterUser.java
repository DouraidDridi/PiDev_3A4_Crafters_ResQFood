package tn.esprit.controllers;

import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
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
    private Button Login;
    private ServiceUser serviceUser;

    public AjouterUser() {
        this.serviceUser = new ServiceUser();
    }

    @FXML
    void initialize() {
        roleChoiceBox.getItems().addAll("User", "Donateur", "RespRestau", "RespAssoc", "Livreur", "Agriculteure");
    }

    @FXML
    void handleRegisterButton(ActionEvent event) {
        try {
            String nomValue = nom.getText();
            String prenomValue = prenom.getText();
            String emailValue = email.getText();
            String passwordValue = password.getText();
            String selectedRole = roleChoiceBox.getValue();

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
                case "Livreur":
                    newUser = new Livreur();
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
            Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
            successAlert.setTitle("Success");
            successAlert.setContentText("User added successfully!");
            successAlert.show();

            // if register succes t3ada lil login
            ActionEvent actionEvent = new ActionEvent();
            navigatetoAjouterUserAction(actionEvent);
        } catch (Exception e) {
            // Show an error message for any exception
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setTitle("Error");
            errorAlert.setContentText("Exception: " + e.getMessage());
            errorAlert.showAndWait();

            // matet3adesh lil login f hala heedhy !
        }
    }

    public void navigatetoAjouterUserAction(ActionEvent actionEvent) {
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

//    @FXML
//    public void combinedAction(ActionEvent actionEvent) {
//        handleRegisterButton(actionEvent);
//        navigatetoAjouterUserAction(actionEvent);
//    }
}
