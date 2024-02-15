package edu.esprit.entites;

import java.sql.Date;
import java.util.Objects;

public class Produit {
    private int idProduit;
    private String nomProduit;
    private double prix;
    private String image_produit;
    private String description ;

    public Produit(String nomProduit, double prix, String image_produit, String description) {
        this.nomProduit = nomProduit;
        this.prix = prix;
        this.image_produit = image_produit;
        this.description = description;
    }

    public Produit(int idProduit, String nomProduit, double prix, String image_produit, String description) {
        this.idProduit = idProduit;
        this.nomProduit = nomProduit;
        this.prix = prix;
        this.image_produit = image_produit;
        this.description = description;
    }


    public String getNomProduit() {
        return nomProduit;
    }

    public void setNomProduit(String nomProduit) {
        this.nomProduit = nomProduit;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public String getImage_produit() {
        return image_produit;
    }

    public void setImage_produit(String image_produit) {
        this.image_produit = image_produit;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getIdProduit() {
        return idProduit;
    }

    public void setIdProduit(int idProduit) {
        this.idProduit = idProduit;
    }

    @Override
    public String toString() {
        return "Produit{" +
                "nomProduit='" + nomProduit + '\'' +
                ", prix=" + prix +
                ", image_produit='" + image_produit + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Produit produit = (Produit) o;
        return idProduit == produit.idProduit;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idProduit);
    }

    public Produit (){

    }

}
