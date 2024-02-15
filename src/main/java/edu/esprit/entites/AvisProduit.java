package edu.esprit.entites;

import java.util.Objects;

public class AvisProduit {
    private int idAvis;
    private int idproduit ;
    private int iduser;
    private int nbstar;
    private String commentaire ;

    public AvisProduit(int idAvis, int idproduit, int iduser, int nbstar, String commentaire) {
        this.idAvis = idAvis;
        this.idproduit = idproduit;
       this.iduser = iduser;
        this.nbstar = nbstar;
        this.commentaire = commentaire;
    }

    public AvisProduit(int idproduit, int iduser, int nbstar, String commentaire) {
        this.idproduit = idproduit;
        this.iduser = iduser;
        this.nbstar = nbstar;
        this.commentaire = commentaire;
    }

    public int getIdAvis() {
        return idAvis;
    }

    public void setIdAvis(int idAvis) {
        this.idAvis = idAvis;
    }

    public int getIdproduit() {
      return idproduit;
    }

    public void setIdproduit(int idproduit) {
        this.idproduit = idproduit;
    }

    public int getIduser() {
        return iduser;
    }

    public void setIduser(int iduser) {
        this.iduser = iduser;
    }

    public int getNbstar() {
        return nbstar;
    }

    public void setNbstar(int nbstar) {
        this.nbstar = nbstar;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    @Override
    public String toString() {
        return "AvisProduit{" +
                "idproduit=" + idproduit +
                ", iduser=" + iduser +
                ", nbstar=" + nbstar +
                ", commentaire='" + commentaire + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AvisProduit that = (AvisProduit) o;
        return idAvis == that.idAvis;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idAvis);
    }

    public AvisProduit() {
    }
}
