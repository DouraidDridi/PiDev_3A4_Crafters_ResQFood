package edu.esprit.entities;

import java.util.Objects;

public class Restaurant {
    private int idR ;
    private String  nomR ;
    private String adresseR ;
    private String emailR ;
    private int phoneR ;

    public Restaurant(){


    }

    public Restaurant(String nomR, String adresseR, String emailR, int phoneR) {
        this.nomR = nomR;
        this.adresseR = adresseR;
        this.emailR = emailR;
        this.phoneR = phoneR;
    }

    public Restaurant(int idR, String nomR, String adresseR, String emailR, int phoneR) {
        this.idR = idR;
        this.nomR = nomR;
        this.adresseR = adresseR;
        this.emailR = emailR;
        this.phoneR = phoneR;
    }

    public int getIdR() {
        return idR;
    }

    public void setIdR(int idR) {
        this.idR = idR;
    }

    public String getNomR() {
        return nomR;
    }

    public void setNomR(String nomR) {
        this.nomR = nomR;
    }

    public String getAdresseR() {
        return adresseR;
    }

    public void setAdresseR(String adresseR) {
        this.adresseR = adresseR;
    }

    public String getEmailR() {
        return emailR;
    }

    public void setEmailR(String emailR) {
        this.emailR = emailR;
    }

    public int getPhoneR() {
        return phoneR;
    }

    public void setPhoneR(int phoneR) {
        this.phoneR = phoneR;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "nomR='" + nomR + '\'' +
                ", adresseR='" + adresseR + '\'' +
                ", emailR='" + emailR + '\'' +
                ", phoneR=" + phoneR +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Restaurant that = (Restaurant) o;
        return idR == that.idR;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idR);
    }
}
