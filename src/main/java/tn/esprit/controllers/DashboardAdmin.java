package tn.esprit.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.SVGPath;
import javafx.stage.Stage;
import tn.esprit.entities.User;
import tn.esprit.services.ServiceUser;

import java.io.IOException;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class DashboardAdmin {

    @FXML
    private Button btnSignout;

    @FXML
    private VBox roleTilePanesContainer;

    @FXML
    private Label totalUsersLabel;


    @FXML
    void initialize() {
        // Call the getAll method to retrieve all registered users
        ServiceUser serviceUser = new ServiceUser();
        Set<User> users = serviceUser.getAll();

        // Display user-specific content in TilePanes based on roles
        displayUsersInRoleTilePanes(users);
        updateTotalRegisteredUsers();

    }

    private String getUserRole(User user) {
        // Add logic to retrieve the role from the database based on user information
        // This might involve querying your database, as you did in the ServiceUser class
        // For simplicity, I'll assume you have a method in ServiceUser to get the role
        ServiceUser serviceUser = new ServiceUser();
        return serviceUser.getUserRole(user.getEmail());
    }

    private void displayUsersInRoleTilePanes(Set<User> users) {
        // Clear existing content in the VBox
        roleTilePanesContainer.getChildren().clear();

        // Get distinct roles from the users
        Set<String> distinctRoles = users.stream().map(this::getUserRole).collect(Collectors.toSet());

        // Create an HBox to hold all the role containers
        HBox rolesHBox = new HBox();
        rolesHBox.setSpacing(20); // Adjust the spacing between role containers

        // Create a container for each role with its own title and TilePane
        for (String role : distinctRoles) {
            // Create a title label for the role
            Label roleTitleLabel = new Label(role);
            roleTitleLabel.setStyle("-fx-font-size: 16; -fx-font-weight: bold; -fx-text-fill: #1b1eeb;");

            // Create a TilePane for the users with the current role
            TilePane roleTilePane = new TilePane();
            roleTilePane.setPrefColumns(2); // Customize the number of columns if needed
            roleTilePane.setHgap(10);
            roleTilePane.setVgap(10);

            // Filter users by role
            Set<User> usersByRole = users.stream().filter(user -> getUserRole(user).equals(role)).collect(Collectors.toSet());

            // Create a container for each user and add to the TilePane
            for (User user : usersByRole) {
                VBox userContainer = new VBox();
                userContainer.setStyle("-fx-border-color: #000000; -fx-padding: 10px;");

                Label idLabel = new Label("ID: " + user.getId());
                Label nameLabel = new Label("Nom: " + user.getNom());
                Label prenomLabel = new Label("Prénom: " + user.getPrenom());
                Label emailLabel = new Label("Email: " + user.getEmail());
                Label passwordLabel = new Label("Password: " + user.getPassword());
                Label roleLabel = new Label("Role: " + getUserRole(user));

                // Create buttons for modifying and deleting the user
                Button modifyButton = createModifyButton(user);
                Button deleteButton = createDeleteButton(user);

                // You can customize the labels and container styles, fonts, etc. as needed
                userContainer.getChildren().addAll(idLabel, nameLabel, prenomLabel, emailLabel, passwordLabel, roleLabel, modifyButton, deleteButton);
                roleTilePane.getChildren().add(userContainer);
            }

            // Create a VBox to hold the title label and TilePane for the current role
            VBox roleContainer = new VBox();
            roleContainer.getChildren().addAll(roleTitleLabel, roleTilePane);

            // Add the roleContainer to the rolesHBox
            rolesHBox.getChildren().add(roleContainer);
        }

        // Add the rolesHBox to the main VBox
        roleTilePanesContainer.getChildren().add(rolesHBox);
    }

    private Button createModifyButton(User user) {
        Button modifyButton = new Button();
        modifyButton.setGraphic(createModifyIcon());
        modifyButton.setStyle("-fx-background-color: transparent;");

        // Set the action for the modify button
        modifyButton.setOnAction(event -> handleModify(user));

        return modifyButton;
    }


    private Node createModifyIcon() {
        // Create an SVGPath for the yellow circle with a white pen icon
        SVGPath yellowCircle = new SVGPath();
        yellowCircle.setContent("M10 0a10 10 0 110 20 10 10 0 010-20");
        yellowCircle.setFill(Color.YELLOW);

        // Create an SVGPath for the white pen
        SVGPath whitePen = new SVGPath();
        whitePen.setContent("M5 14l1.414-1.414 3.172 3.172V7h2v8.757l3.172-3.172 1.414 1.414L10 18z");
        whitePen.setFill(Color.WHITE);

        // Use a StackPane to overlay the white pen on the yellow circle
        return new StackPane(yellowCircle, whitePen);
    }



    private void handleModify(User user) {
        // Display a form for modifying user details using Dialog
        Dialog<User> modifyDialog = new Dialog<>();
        modifyDialog.setTitle("Modify User");
        modifyDialog.setHeaderText("Modify User Details");

        // Set the button types (OK and Cancel)
        ButtonType modifyButtonType = new ButtonType("Modify", ButtonBar.ButtonData.OK_DONE);
        modifyDialog.getDialogPane().getButtonTypes().addAll(modifyButtonType, ButtonType.CANCEL);

        // Create a GridPane for the user details form
        GridPane modifyGrid = new GridPane();
        modifyGrid.setHgap(10);
        modifyGrid.setVgap(10);
        modifyGrid.setPadding(new Insets(20, 150, 10, 10));

        // Add form fields to the GridPane
        TextField nomField = new TextField(user.getNom());
        TextField prenomField = new TextField(user.getPrenom());
        TextField emailField = new TextField(user.getEmail());
        PasswordField passwordField = new PasswordField();
        // Add other fields as needed

        modifyGrid.add(new Label("Nom:"), 0, 0);
        modifyGrid.add(nomField, 1, 0);
        modifyGrid.add(new Label("Prénom:"), 0, 1);
        modifyGrid.add(prenomField, 1, 1);
        modifyGrid.add(new Label("Email:"), 0, 2);
        modifyGrid.add(emailField, 1, 2);
        modifyGrid.add(new Label("Password:"), 0, 3);
        modifyGrid.add(passwordField, 1, 3);

        // Customize the form based on the user type (you can add role-specific fields)

        // Set the content of the Dialog pane
        modifyDialog.getDialogPane().setContent(modifyGrid);

        // Enable/disable the Modify button based on the form's validity
        Node modifyButton = modifyDialog.getDialogPane().lookupButton(modifyButtonType);
        modifyButton.setDisable(true);

        // Add listener to the form fields for validation
        nomField.textProperty().addListener((observable, oldValue, newValue) -> {
            modifyButton.setDisable(newValue.trim().isEmpty());
        });

        // Convert the result to a user object when the Modify button is clicked
        modifyDialog.setResultConverter(dialogButton -> {
            if (dialogButton == modifyButtonType) {
                // Update the user details
                user.setNom(nomField.getText());
                user.setPrenom(prenomField.getText());
                user.setEmail(emailField.getText());
                user.setPassword(passwordField.getText());
                // Update other fields as needed
                return user;
            }
            return null;
        });

        // Show the Dialog and handle the result
        Optional<User> result = modifyDialog.showAndWait();
        result.ifPresent(updatedUser -> {
            // Call the modifier method with the updated user
            ServiceUser serviceUser = new ServiceUser();
            serviceUser.modifier(updatedUser);

            // Update the displayed users after modification
            updateDisplayedUsers();
            updateTotalRegisteredUsers();
        });
    }


    private void updateDisplayedUsers() {
        // Update the displayed users logic goes here
        // You might need to fetch the updated user list and refresh the UI
        ServiceUser serviceUser = new ServiceUser();
        Set<User> updatedUsers = serviceUser.getAll();
        displayUsersInRoleTilePanes(updatedUsers);
    }




    private Button createDeleteButton(User user) {
        Button deleteButton = new Button();
        deleteButton.setGraphic(createDeleteIcon());
        deleteButton.setStyle("-fx-background-color: transparent;"); // Make the button background transparent

        // Create an instance of ServiceUser
        ServiceUser serviceUser = new ServiceUser();

        // Set the action for the delete button
        deleteButton.setOnAction(event -> {
            // Call the supprimer method with the user's ID
            serviceUser.supprimer(user.getId());

            // Refresh the user display after deletion
            Set<User> updatedUsers = serviceUser.getAll();
            displayUsersInRoleTilePanes(updatedUsers);
            updateTotalRegisteredUsers();
        });

        return deleteButton;
    }

    private void updateTotalRegisteredUsers() {
        int totalUsers = ServiceUser.getTotalRegisteredUsers();
        totalUsersLabel.setText("Total Registered Users: " + totalUsers);
    }


    private Node createDeleteIcon() {
        // Create an SVGPath for the red "X" circle icon
        SVGPath svgPath = new SVGPath();
        svgPath.setContent("M10 0a10 10 0 110 20 10 10 0 010-20zm5.95 12.122l-1.06 1.06L10 11.06l-4.89 4.88-1.06-1.06L8.94 10 4.05 5.11l1.06-1.06L10 8.94l4.88-4.89 1.06 1.06L11.06 10z");
        svgPath.setFill(Color.RED);

        // Create an SVGPath for the white "X" shape
        SVGPath whiteX = new SVGPath();
        whiteX.setContent("M7 6L6 7L9.95 11L6 15L7 16L11 12.05L15 16L16 15L12.05 11L16 7L15 6L11 9.95L7 6Z");
        whiteX.setFill(Color.WHITE);

        // Use a StackPane to overlay the "X" shape on the red circle
        return new StackPane(svgPath, whiteX);
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

    @FXML
    void ajouter(ActionEvent event) {
        // Display a form for adding a new user using Dialog
        Dialog<User> ajouterDialog = new Dialog<>();
        ajouterDialog.setTitle("Add User");
        ajouterDialog.setHeaderText("Add a New User");

        // Set the button types (OK and Cancel)
        ButtonType ajouterButtonType = new ButtonType("Add", ButtonBar.ButtonData.OK_DONE);
        ajouterDialog.getDialogPane().getButtonTypes().addAll(ajouterButtonType, ButtonType.CANCEL);

        // Create a GridPane for the user details form
        GridPane ajouterGrid = new GridPane();
        ajouterGrid.setHgap(10);
        ajouterGrid.setVgap(10);
        ajouterGrid.setPadding(new Insets(20, 150, 10, 10));

        // Add form fields to the GridPane
        TextField nomField = new TextField();
        TextField prenomField = new TextField();
        TextField emailField = new TextField();
        PasswordField passwordField = new PasswordField();
        // Add other fields as needed

        ajouterGrid.add(new Label("Nom:"), 0, 0);
        ajouterGrid.add(nomField, 1, 0);
        ajouterGrid.add(new Label("Prénom:"), 0, 1);
        ajouterGrid.add(prenomField, 1, 1);
        ajouterGrid.add(new Label("Email:"), 0, 2);
        ajouterGrid.add(emailField, 1, 2);
        ajouterGrid.add(new Label("Password:"), 0, 3);
        ajouterGrid.add(passwordField, 1, 3);

        // Customize the form based on your needs

        // Set the content of the Dialog pane
        ajouterDialog.getDialogPane().setContent(ajouterGrid);

        // Enable/disable the Add button based on the form's validity
        Node ajouterButton = ajouterDialog.getDialogPane().lookupButton(ajouterButtonType);
        ajouterButton.setDisable(true);

        // Add listener to the form fields for validation
        nomField.textProperty().addListener((observable, oldValue, newValue) -> {
            ajouterButton.setDisable(newValue.trim().isEmpty());
        });

        // Convert the result to a user object when the Add button is clicked
        ajouterDialog.setResultConverter(dialogButton -> {
            if (dialogButton == ajouterButtonType) {
                // Create a new user with the provided details
                User newUser = new User();
                newUser.setNom(nomField.getText());
                newUser.setPrenom(prenomField.getText());
                newUser.setEmail(emailField.getText());
                newUser.setPassword(passwordField.getText());
                // Set other fields as needed

                // Get the selected role (you can modify this based on your UI)
                String selectedRole = "DefaultRole"; // Replace this with your role selection logic

                // Call the ajouter method with the new user and role
                ServiceUser serviceUser = new ServiceUser();
                serviceUser.ajouter(newUser, selectedRole);

                return newUser;
            }
            return null;
        });

        // Show the Dialog and handle the result
        Optional<User> result = ajouterDialog.showAndWait();
        result.ifPresent(newUser -> {
            // Display a choice dialog to select the role
            ChoiceDialog<String> roleDialog = new ChoiceDialog<>("DefaultRole", "agriculteur", "respResto", "respAssoc","donateur","admin");
            roleDialog.setTitle("Select Role");
            roleDialog.setHeaderText("Choose the role for the new user:");
            roleDialog.setContentText("Role:");

            Optional<String> selectedRole = roleDialog.showAndWait();
            selectedRole.ifPresent(role -> {
                // Call the ajouter method with the new user and selected role
                ServiceUser serviceUser = new ServiceUser();
                serviceUser.ajouter(newUser, role);

                // Update the displayed users after addition
                updateDisplayedUsers();
                updateTotalRegisteredUsers();
            });
        });



    }}
