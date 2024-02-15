


package edu.esprit.service;

import edu.esprit.entities.Restaurant;
import edu.esprit.utils.DataSource;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;
public class ServiceRestau implements IService<Restaurant> {

    Connection cnx = DataSource.getInstance().getCnx();

    @Override
    public void ajouter(Restaurant restaurant) {
        String req = "INSERT INTO restaurant(nomR, adresseR,emailR,phoneR) VALUES (?,?,?,?)";
        try (PreparedStatement ps = cnx.prepareStatement(req)) {
            ps.setString(1, restaurant.getNomR());
            ps.setString(2, restaurant.getAdresseR());
            ps.setString(3, restaurant.getEmailR());
            ps.setInt(4, restaurant.getPhoneR());

            ps.executeUpdate();
            System.out.println("Restaurant added !");
        } catch (SQLException e) {
            // Handle or log the exception
            e.printStackTrace();
        }
    }

    @Override
    public void modifier(Restaurant restaurant) {

        String req = "UPDATE restaurant SET nomR = ?, adresseR = ?, emailR = ?, phoneR = ? WHERE idR = ?";
        try (PreparedStatement ps = cnx.prepareStatement(req)) {
            ps.setString(1, restaurant.getNomR());
            ps.setString(2, restaurant.getAdresseR());
            ps.setString(3, restaurant.getEmailR());
            ps.setInt(4, restaurant.getPhoneR());
            ps.setInt(5, restaurant.getIdR());
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("restaurant with ID " + restaurant.getIdR() + " updated successfully!");
            } else {
                System.out.println("No record found with ID " + restaurant.getIdR() + ". Update failed.");
            }
        } catch (SQLException e) {
            // Handle or log the exception
            e.printStackTrace();
        }

    }

    @Override
    public void supprimer(int id) {
        String req = "DELETE FROM restaurant WHERE idR = ?";
        try (PreparedStatement ps = cnx.prepareStatement(req)) {
            ps.setInt(1, id);
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("restaurant with ID " + id + " deleted successfully!");
            } else {
                System.out.println("No record found with ID " + id + ". Deletion failed.");
            }
        } catch (SQLException e) {
            // Handle or log the exception
            e.printStackTrace();
        }

    }

    @Override
    public Restaurant getOneById(int id) {
        return null;
    }

    @Override
    public  Set<Restaurant> getAll() {
        Set<Restaurant> restaurant = new HashSet<>();
        String req = "SELECT * FROM restaurant";
        try (Statement st = cnx.createStatement();
             ResultSet res = st.executeQuery(req)) {
            while (res.next()) {
                int id = res.getInt("idR");
                String nom = res.getString("nomR");
                String adresse= res.getString("adresseR");
                String email = res.getString("emailR");
                int  phone = res.getInt("phoneR");
                Restaurant r = new Restaurant(id, nom, adresse,email,phone);
                restaurant.add(r);
            }
        } catch (SQLException e) {
            // Handle or log the exception
            e.printStackTrace();
        }
        return restaurant;
    }




}
