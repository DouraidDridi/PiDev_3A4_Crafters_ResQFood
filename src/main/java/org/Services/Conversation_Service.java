package org.Services;
import org.Entities.Conversation;
import org.Entities.Reclamation;
import org.Utils.DbConnector;
import org.Utils.Etat_Message;
import org.Utils.Etat_Reclamation;
import org.Utils.Type_Reclamation;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Conversation_Service {
    private Connection cnx;
    private PreparedStatement ste;

    public Conversation_Service() {
        cnx = DbConnector.getInstance().getConnection();
    }

    public void createConversation(Conversation c) {
        try {
            String req = "INSERT INTO Conversation (`id_user`,`id_reclamation`,`message`,`dateMsg`,`etat`) VALUES (?,?,?,?,?)";
            PreparedStatement st = cnx.prepareStatement(req);

            st.setInt(1, c.getId_user());
            st.setInt(2, c.getId_reclamation());
            st.setString(3, c.getMessage());
            st.setDate(4, c.getDateMsg());
            st.setString(5, c.getEtat().toString());


            st.executeUpdate();
            System.out.println("conversation ajoutée avec succes.");

        } catch (SQLException ex) {

            ex.printStackTrace();
        }
    }

    public void modifyConversation(Conversation c) {
        String sql = "UPDATE Conversation SET `message`=?,`etat`=? where id=?";
        try {
            PreparedStatement st = cnx.prepareStatement(sql);

            st.setString(1, c.getMessage());
            st.setString(2, c.getEtat().toString());
            st.setInt(3,c.getId());

            st.executeUpdate();
            System.out.println("modification avec succees !");
           // System.out.println(c);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void deleteConversation(Conversation c) {
        try {

            String sql = "delete from Conversation WHERE id=?";
            PreparedStatement st = cnx.prepareStatement(sql);
            st.setInt(1, c.getId());
            st.executeUpdate();
            System.out.println("conversation deleted !");


        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public List<Conversation> getConversation() {
        ArrayList<Conversation> conversations = new ArrayList();
        String req = "SELECT * FROM Conversation";

        try {
            Statement st;

            st = DbConnector.getInstance().getConnection().prepareStatement(req);
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                Conversation c = new Conversation(rs.getInt(1), rs.getInt(2),rs.getInt(3), rs.getString(4)
                        , rs.getDate(5), Etat_Message.valueOf(rs.getString(6)));

                conversations.add(c
                );

            }


        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return conversations;
    }

}

