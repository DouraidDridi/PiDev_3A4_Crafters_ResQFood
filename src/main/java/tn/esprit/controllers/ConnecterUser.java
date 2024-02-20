package tn.esprit.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.Duration;
import tn.esprit.services.ServiceUser;

public class ConnecterUser {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button LoginButton;

    @FXML
    private TextField email;

    @FXML
    private PasswordField password;

    @FXML
    private Button registerButton;

    @FXML
    private ProgressIndicator loadingIndicator;

    @FXML
    void handleLoginButton(ActionEvent event) {
        // Show loading indicator
        loadingIndicator.setVisible(true);

        try {
            String emailValue = email.getText();
            String passwordValue = password.getText();

            // Call the authenticate method to check credentials
            ServiceUser serviceUser = new ServiceUser();
            boolean isAuthenticated = serviceUser.authenticate(emailValue, passwordValue);

            if (isAuthenticated) {
                // Determine the user's role
                String userRole = serviceUser.getUserRole(emailValue);

                // Switch to tn.esprit.controllers.Home.fxml or any other desired scene after a delay (5 seconds in this case)
                PauseTransition pauseTransition = new PauseTransition(Duration.seconds(5));
                pauseTransition.setOnFinished(e -> {
                    try {
                        FXMLLoader loader;
                        if ("admin".equals(userRole.toLowerCase())) {
                            // Redirect to dashbord.fxml if the role is admin
                            loader = new FXMLLoader(getClass().getResource("/DashboardAdmin.fxml"));
                        } else {
                            // Otherwise, redirect to tn.esprit.controllers.Home.fxml or another scene
                            loader = new FXMLLoader(getClass().getResource("/Home.fxml"));
                        }
                        Parent homeRoot = loader.load();
                        Stage currentStage = (Stage) LoginButton.getScene().getWindow();
                        currentStage.setScene(new Scene(homeRoot));

                        // Close loading indicator after login logic is complete
                        loadingIndicator.setVisible(false);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                });
                pauseTransition.play();
            } else {
                // Authentication failed, show an error message
                Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                errorAlert.setTitle("Error");
                errorAlert.setContentText("Authentication failed!");

                // Close loading indicator in case of authentication failure
                loadingIndicator.setVisible(false);

                errorAlert.showAndWait();
            }
        } catch (Exception e) {
            // Handle any exceptions

            // Close loading indicator in case of an exception
            loadingIndicator.setVisible(false);

            e.printStackTrace();
        }
    }


    @FXML
    void handleRegisterButton(ActionEvent event) {
        try {
            // Load the FXML file for the AjouterUser scene
            Parent ajouterUserRoot = FXMLLoader.load(getClass().getResource("/AjouterUser.fxml"));
            Scene ajouterUserScene = new Scene(ajouterUserRoot);

            // Get the Stage from the current window and set the new scene
            Stage currentStage = (Stage) registerButton.getScene().getWindow();
            currentStage.setScene(ajouterUserScene);
        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Sorry, an error occurred while switching scenes.");
            alert.setTitle("Error");
            alert.show();
        }
    }

    @FXML
    void initialize() {
        assert LoginButton != null : "fx:id=\"LoginButton\" was not injected: check your FXML file 'ConnecterUser.fxml'.";
        assert email != null : "fx:id=\"email\" was not injected: check your FXML file 'ConnecterUser.fxml'.";
        assert password != null : "fx:id=\"password\" was not injected: check your FXML file 'ConnecterUser.fxml'.";
        assert registerButton != null : "fx:id=\"registerButton\" was not injected: check your FXML file 'ConnecterUser.fxml'.";

    }

}
