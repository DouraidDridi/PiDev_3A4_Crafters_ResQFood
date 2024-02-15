package org.Entities;

import org.Utils.Etat_Reclamation;
import org.Utils.Type_Reclamation;

import java.sql.Date;

public class Reclamation {
    public int id,id_user;
    public String description;
    public Etat_Reclamation etat_reclamation;
    public Type_Reclamation type_reclamation;
    public Date date;

    public Reclamation() {
    }

    public Reclamation(int id, int id_user, String description, Etat_Reclamation etat_reclamation, Type_Reclamation type_reclamation, Date date) {
        this.id = id;
        this.id_user = id_user;
        this.description = description;
        this.etat_reclamation = etat_reclamation;
        this.type_reclamation = type_reclamation;
        this.date = date;
    }

    public Reclamation(int id_user, String description, Etat_Reclamation etat_reclamation, Type_Reclamation type_reclamation, Date date) {
        this.id_user = id_user;
        this.description = description;
        this.etat_reclamation = etat_reclamation;
        this.type_reclamation = type_reclamation;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Etat_Reclamation getEtat_reclamation() {
        return etat_reclamation;
    }

    public void setEtat_reclamation(Etat_Reclamation etat_reclamation) {
        this.etat_reclamation = etat_reclamation;
    }

    public Type_Reclamation getType_reclamation() {
        return type_reclamation;
    }

    public void setType_reclamation(Type_Reclamation type_reclamation) {
        this.type_reclamation = type_reclamation;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Reclamation{" +
                "id=" + id +
                ", id_user=" + id_user +
                ", description='" + description + '\'' +
                ", etat_reclamation=" + etat_reclamation +
                ", type_reclamation=" + type_reclamation +
                ", date=" + date +
                '}';
    }
}
