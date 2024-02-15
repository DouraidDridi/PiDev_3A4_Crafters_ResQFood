package edu.esprit.entites;

import java.util.Objects;

public class User {
    private int Id;
    private String Nom;
    private String Prenom;

    public User(int id, String nom, String prenom) {
        Id = id;
        Nom = nom;
        Prenom = prenom;
    }

    public User(String nom, String prenom) {
        Nom = nom;
        Prenom = prenom;
    }



    public String getNom() {
        return Nom;
    }

    public void setNom(String nom) {
        Nom = nom;
    }

    public String getPrenom() {
        return Prenom;
    }

    public void setPrenom(String prenom) {
        Prenom = prenom;
    }


    @Override
    public String toString() {
        return "User{" +
                "Nom='" + Nom + '\'' +
                ", Prenom='" + Prenom + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Id == user.Id && Objects.equals(Nom, user.Nom) && Objects.equals(Prenom, user.Prenom);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Id, Nom, Prenom);
    }

    public User() {

    }
}
