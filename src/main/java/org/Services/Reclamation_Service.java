package org.Services;

import org.Entities.Reclamation;
import org.Utils.DbConnector;
import org.Utils.Etat_Reclamation;
import org.Utils.Type_Reclamation;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Reclamation_Service {
    private Connection cnx;
    private PreparedStatement ste;

    public Reclamation_Service() {
        cnx = DbConnector.getInstance().getConnection();
    }
    public void createReclamation(Reclamation E) {
        try {
            String req = "INSERT INTO reclamation (`id_user`,`description`,`type`,`date`,`etat`) VALUES (?,?,?,?,?)";
            PreparedStatement st = cnx.prepareStatement(req);

            st.setInt(1, E.getId_user());
            st.setString(2, E.getDescription());
            st.setString(3, E.getType_reclamation().toString());
            st.setDate(4, E.getDate());
            st.setString(5, E.getEtat_reclamation().toString());


            st.executeUpdate();
            System.out.println("reclamation ajoutée avec succes.");

        } catch (SQLException ex) {

            ex.printStackTrace();
        }

    }


    public void modifyReclamation(Reclamation E) {
        String sql = "UPDATE reclamation SET `description`=?,`type`=?,`Etat`=? where id=?";
        try {
            PreparedStatement st = cnx.prepareStatement(sql);

            st.setString(1, E.getDescription());
            st.setString(2, E.getType_reclamation().toString());
            st.setString(3,E.getEtat_reclamation().toString());
            st.setInt(4, E.getId());

            st.executeUpdate();
            System.out.println("modification avec succees !");
            //System.out.println(E);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void deleteReclamation(Reclamation e) {
        try {

                String sql = "delete from reclamation WHERE id=?";
                PreparedStatement st = cnx.prepareStatement(sql);
                st.setInt(1, e.getId());
                st.executeUpdate();
                System.out.println("Reclamation deleted !");


        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }



    public List<Reclamation> getReclamation() {
        ArrayList<Reclamation> reclamations = new ArrayList();
        String req = "SELECT * FROM reclamation";

        try {
            Statement st;

            st= DbConnector.getInstance().getConnection().prepareStatement(req);
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                Reclamation e= new Reclamation(rs.getInt(1), rs.getInt(2),rs.getString(3)
                        , Etat_Reclamation.valueOf(rs.getString(6)), Type_Reclamation.valueOf(rs.getString(4)),rs.getDate(5));

                reclamations.add(e
                );

            }


        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return reclamations;
    }




}
