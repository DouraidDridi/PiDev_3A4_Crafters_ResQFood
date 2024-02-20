package tn.esprit.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

public class Home {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private MenuItem logoutMenuItem;

    @FXML
    void initialize() {

    }

    @FXML
    void handleLogout(ActionEvent event) {
        // Implement the logout logic here
        // For example, show a confirmation dialog
        Alert confirmationDialog = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationDialog.setTitle("Logout");
        confirmationDialog.setHeaderText("Are you sure you want to logout?");
        confirmationDialog.showAndWait().ifPresent(response -> {
            if (response == javafx.scene.control.ButtonType.OK) {

                Stage stage = (Stage) logoutMenuItem.getParentPopup().getOwnerWindow();
                stage.close();

                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/ConnecterUser.fxml"));
                    Parent root = loader.load();
                    Stage connecterUserStage = new Stage();
                    connecterUserStage.setScene(new Scene(root));
                    connecterUserStage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                    // Handle the exception, e.g., show an alert
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setContentText("Error loading ConnecterUser.fxml");
                    alert.showAndWait();
                }
            }
        });
    }
}
