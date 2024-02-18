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

import java.util.List;

public class SupprimerDonsController {

    @FXML
    private ComboBox<User> userComboBox;

    @FXML
    private TextField idDonField;

    @FXML
    private TextField donPointsField;

    private DonsService donsService;
    private UserService userService;

    // Méthode pour injecter le service de dons
    public void setDonsService(DonsService donsService) {
        this.donsService = donsService;
    }



    public SupprimerDonsController() {
        this.donsService = new DonsService();
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
    private void handleSupprimerDon() {
        // Récupérer l'ID du don à supprimer du champ de texte
        int donsId;
        int nbPoints;
        try {
            donsId = Integer.parseInt(idDonField.getText());
            nbPoints = Integer.parseInt(donPointsField.getText());
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Erreur", "Veuillez saisir des valeurs valides pour l'ID du don et le nombre de points.");
            return;
        }

        // Appeler la méthode supprimerDon de votre service DonsService
        boolean success = donsService.supprimerDon(donsId, nbPoints);

        if (success) {
            // Afficher une confirmation si la suppression a réussi
            showAlert(Alert.AlertType.INFORMATION, "Succès", "Points supprimés avec succès du don.");

            // Effacer les champs après la suppression des dons
            idDonField.clear();
            donPointsField.clear();
        } else {
            // Afficher un message d'erreur si la suppression a échoué
            showAlert(Alert.AlertType.ERROR, "Erreur", "La suppression des points du don a échoué.");
        }
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}

