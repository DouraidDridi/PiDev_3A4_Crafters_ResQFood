package edu.esprit.service;


import edu.esprit.entites.AvisProduit;
import edu.esprit.utils.DataSource;

import java.sql.Connection;
import java.util.Set;
import java.sql.PreparedStatement;
import java.sql.SQLException;
public class ServiceAvisProduit implements IServiceavisProduit <AvisProduit> {
    Connection cnx = DataSource.getInstance().getCnx();


   @Override
    public void ajouter(AvisProduit avisProduit) {

        String req = "INSERT INTO `avisproduit`(`id_Produit`, `id_user`, `nbstar`, `commentaire`) VALUES (?,?,?,?)";
        try {
            PreparedStatement st = cnx.prepareStatement(req);

            // Assurez-vous que `avis.getid_Produit()` et `avis.getid_user()` renvoient des entiers valides
            st.setInt(1, avisProduit.getIdproduit());
            st.setInt(2, avisProduit.getIduser());
            st.setInt(3, avisProduit.getNbstar());
            st.setString(4, avisProduit.getCommentaire());

            st.executeUpdate();
            System.out.println("Avis inséré avec succès !");
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de l'insertion de l'avis : " + e.getMessage());
        }


    }

    private int getIdProduit() {
        return 0;
    }

    private String getcommentaire() {
        return null;
    }

    private int getnbstar() {
        return 0;
    }

    private int getiduser() {
        return 0;
    }

    private int getIdAvis() {
        return 0;
    }

    @Override
    public void modifier(AvisProduit avisProduit) {
        String req = "UPDATE `avisproduit` SET `id_Produit` = ?, `id_user` = ?, `nbstar` = ?, `commentaire` = ? WHERE `idAvis` = ?";
        try {
            PreparedStatement st = cnx.prepareStatement(req);

            st.setInt(1, avisProduit.getIdproduit());
            st.setInt(2, avisProduit.getIduser());
            st.setInt(3, avisProduit.getNbstar());
            st.setString(4, avisProduit.getCommentaire());
            st.setInt(5, avisProduit.getIdAvis());

            st.executeUpdate();
            System.out.println("Avis modifié avec succès !");
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la modification de l'avis : " + e.getMessage());
        }
    }

    @Override
    public void supprimer(int idAvis) {
        String req = "DELETE FROM `avisproduit` WHERE `idAvis` = ?";
        try {
            PreparedStatement st = cnx.prepareStatement(req);
            st.setInt(1, idAvis);

            st.executeUpdate();
            System.out.println("Avis supprimé avec succès !");
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la suppression de l'avis : " + e.getMessage());
        }
    }

    @Override
    public AvisProduit getOneById(int avisProduit) {
        return null;
    }

    @Override
    public Set<AvisProduit> getAll() {
        return null;
    }


}
