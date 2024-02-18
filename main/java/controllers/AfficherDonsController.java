package controllers;

import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import entities.Dons;
import service.DonsService;

import java.text.SimpleDateFormat;
import java.util.List;

public class AfficherDonsController {

    @FXML
    private TableView<Dons> donsTable;

    @FXML
    private TableColumn<Dons, String> idColumn;

    @FXML
    private TableColumn<Dons, String> userIdColumn;

    @FXML
    private TableColumn<Dons, String> nbPointsColumn;

    @FXML
    private TableColumn<Dons, String> dateAjoutColumn;

    private DonsService donsService;

    public AfficherDonsController() {
        donsService = new DonsService();
    }

    @FXML
    void initialize() {
        // Configuration des colonnes de la table
        idColumn.setCellValueFactory(data -> new SimpleStringProperty(String.valueOf(data.getValue().getId())));
        userIdColumn.setCellValueFactory(data -> new SimpleStringProperty(String.valueOf(data.getValue().getUserId())));
        nbPointsColumn.setCellValueFactory(data -> new SimpleStringProperty(String.valueOf(data.getValue().getNbPoints())));
        dateAjoutColumn.setCellValueFactory(data -> {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return new SimpleStringProperty(dateFormat.format(data.getValue().getDateAjout()));
        });

        // Charger les dons depuis la base de données et les afficher dans la table
        loadDons();
    }

    private void loadDons() {
        List<Dons> donsList = donsService.getAllDons();
        donsTable.getItems().addAll(donsList);
    }

}
