package tn.esprit.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import tn.esprit.entities.User;
import tn.esprit.services.ServiceUser;

import java.io.IOException;
import java.util.Set;

public class DashboardAdmin {

    @FXML
    private Button btnSignout;

    @FXML
    private StackPane userContentStackPane;

    @FXML
    void initialize() {
        // Call the getAll method to retrieve all registered users
        ServiceUser serviceUser = new ServiceUser();
        Set<User> users = serviceUser.getAll();

        // Display user-specific content in the StackPane
        displayUsersInStackPane(users);
    }

    private void displayUsersInStackPane(Set<User> users) {
        // Clear existing content in the StackPane
        userContentStackPane.getChildren().clear();

        // Create a TableView for displaying user data
        TableView<User> userTable = new TableView<>();

        // Define table columns
        TableColumn<User, Integer> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<User, String> nomColumn = new TableColumn<>("Nom");
        nomColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));

        TableColumn<User, String> prenomColumn = new TableColumn<>("Prenom");
        prenomColumn.setCellValueFactory(new PropertyValueFactory<>("prenom"));

        TableColumn<User, String> emailColumn = new TableColumn<>("Email");
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));

        // Add columns to the table
        userTable.getColumns().addAll(idColumn, nomColumn, prenomColumn, emailColumn);

        // Create observable list from the set of users
        ObservableList<User> userList = FXCollections.observableArrayList(users);

        // Set the data to the table
        userTable.setItems(userList);

        // Add the table to the StackPane
        userContentStackPane.getChildren().add(userTable);
    }



    @FXML
    void handleLogout(ActionEvent event) {
        Alert confirmationDialog = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationDialog.setTitle("Logout");
        confirmationDialog.setHeaderText("Are you sure you want to logout?");
        confirmationDialog.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                Stage currentStage = (Stage) btnSignout.getScene().getWindow();
                currentStage.close();

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
