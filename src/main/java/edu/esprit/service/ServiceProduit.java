package edu.esprit.service;

import edu.esprit.entites.Produit;
import edu.esprit.utils.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;
public class ServiceProduit implements IServiceProduit <Produit> {
    Connection cnx = DataSource.getInstance().getCnx();
    @Override
    public void ajouter(Produit produit) {
        String req = "INSERT INTO `produit` (`nomProduit`, `prix`, `image_produit`, `description`) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement st = cnx.prepareStatement(req);

            // Assurez-vous que `produit.getNomProduit()` renvoie un entier valide
            st.setString(1, produit.getNomProduit());
            st.setDouble(2, produit.getPrix());
            st.setString(3,produit.getImage_produit());
            st.setString(4, produit.getDescription());

            st.executeUpdate();
            System.out.println("Produit inséré avec succès !");
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de l'insertion du produit : " + e.getMessage());
        }
    }
    @Override
    public void modifier(Produit produit) {

        String req = "UPDATE `produit` SET `nomProduit` = ?, `prix` = ?, `image_produit` = ?, `description` = ? WHERE `idProduit` = ?";
        try {
            PreparedStatement st = cnx.prepareStatement(req);
            st.setString(1, produit.getNomProduit());
            st.setDouble(2, produit.getPrix());
            st.setString(3, produit.getImage_produit());
            st.setString(4, produit.getDescription());
            st.setInt(5, produit.getIdProduit());

            st.executeUpdate();
            System.out.println("Produit modifié avec succès !");
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la modification du produit : " + e.getMessage());
        }


    }

    @Override
    public void supprimer(int idProduit) {

        String req = "DELETE FROM `produit` WHERE `idProduit` = ?";
        try {
            PreparedStatement st = cnx.prepareStatement(req);
            st.setInt(1, idProduit);

            st.executeUpdate();
            System.out.println("Produit supprimé avec succès !");
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la suppression du produit : " + e.getMessage());
        }
    }

    @Override
    public Produit getOneById(int idProduit) {


        String req = "SELECT * FROM `produit` WHERE `idProduit` = ?";
        try {
            PreparedStatement st = cnx.prepareStatement(req);
            st.setInt(1, idProduit);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                return new Produit(rs.getInt("idProduit"), rs.getString("nomProduit"), rs.getDouble("prix"), rs.getString("image_produit"), rs.getString("description"));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la récupération du produit : " + e.getMessage());
        }
        return null;



    }

    @Override
    public Set<Produit> getAll() {
        Set<Produit> produits = new HashSet<>();
        String req = "SELECT * FROM `produit`";
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);

            while (rs.next()) {
                produits.add(new Produit(rs.getInt("idProduit"), rs.getString("nomProduit"), rs.getDouble("prix"), rs.getString("image_produit"), rs.getString("description")));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la récupération des produits : " + e.getMessage());
        }
        return produits;

    }
}
