package entities;

import javafx.beans.property.*;

import java.util.Date;

public class Dons {
    private final IntegerProperty id;
    private final IntegerProperty userId;
    private final IntegerProperty nbPoints;
    private final ObjectProperty<Date> dateAjout;

    public Dons() {
        this.id = new SimpleIntegerProperty();
        this.userId = new SimpleIntegerProperty();
        this.nbPoints = new SimpleIntegerProperty();
        this.dateAjout = new SimpleObjectProperty<>();
    }

    public Dons(int id, int userId, int nbPoints, Date dateAjout) {
        this.id = new SimpleIntegerProperty(id);
        this.userId = new SimpleIntegerProperty(userId);
        this.nbPoints = new SimpleIntegerProperty(nbPoints);
        this.dateAjout = new SimpleObjectProperty<>(dateAjout);
    }

    // Méthodes d'accès aux propriétés
    public IntegerProperty idProperty() {
        return id;
    }

    public int getId() {
        return id.get();
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public IntegerProperty userIdProperty() {
        return userId;
    }

    public int getUserId() {
        return userId.get();
    }

    public void setUserId(int userId) {
        this.userId.set(userId);
    }

    public IntegerProperty nbPointsProperty() {
        return nbPoints;
    }

    public int getNbPoints() {
        return nbPoints.get();
    }

    public void setNbPoints(int nbPoints) {
        this.nbPoints.set(nbPoints);
    }

    public ObjectProperty<Date> dateAjoutProperty() {
        return dateAjout;
    }

    public Date getDateAjout() {
        return dateAjout.get();
    }

    public void setDateAjout(Date dateAjout) {
        this.dateAjout.set(dateAjout);
    }

    public void setIdUser(int idUser) {
    }

    public void setIdDons(int idDons) {
    }
}
