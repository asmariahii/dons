package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import entities.User;
import service.DonsService;
import service.UserService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

// Assurez-vous que ces importations sont ajoutées
import java.util.List;


public class AjouterDonsController {

    @FXML
    private ComboBox<User> userComboBox;

    @FXML
    private TextField donPointsField;

    private DonsService donsService;
    private UserService userService;

    public AjouterDonsController() {
        donsService = new DonsService();
        userService = new UserService();
    }

    @FXML
    void initialize() {
        // Charger les utilisateurs existants à partir de la base de données
        List<User> userList = userService.getAllUsers();
        ObservableList<User> observableUserList = FXCollections.observableArrayList(userList);
        userComboBox.setItems(observableUserList);
    }
    @FXML
    private void handleAjouterDon() {
        // Récupérer l'utilisateur sélectionné dans la ComboBox
        User selectedUser = userComboBox.getValue();
        if (selectedUser == null) {
            showAlert(Alert.AlertType.ERROR, "Erreur", "Veuillez sélectionner un utilisateur.");
            return;
        }

        // Récupérer le nombre de points de don à partir du champ de texte
        int donPoints;
        try {
            donPoints = Integer.parseInt(donPointsField.getText());
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Erreur", "Veuillez saisir un nombre entier pour les points de don.");
            return;
        }

        // Ajouter le don pour l'utilisateur sélectionné
        donsService.addDons(selectedUser, donPoints);

        // Afficher une confirmation
        showAlert(Alert.AlertType.INFORMATION, "Succès", "Don ajouté avec succès pour l'utilisateur " + selectedUser.getId());

        // Effacer les champs après l'ajout du don
        donPointsField.clear();
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
