package tn.esprit.services;

import tn.esprit.entities.*;
import tn.esprit.utils.DataSource;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class ServiceUser implements IService<User> {

    Connection cnx = DataSource.getInstance().getCnx();

    @Override
    public void ajouter(User user, String role) {
       /* String req = "INSERT INTO `user`(`nom`, `prenom`, `email`, `password`) VALUES (?,?,?,?)";
        try {
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("user added !");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }*/
        /* String req = "INSERT INTO `user`(`nom`, `prenom`, `email`, `password`) VALUES (?,?,?,?)";
        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, user.getNom());
            ps.setString(2, user.getPrenom());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getPassword());
            ps.executeUpdate();
            System.out.println("User added !");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }*/



            // Determine the role based on the instance of the user
            if (user instanceof Agriculteur) {
                role = "agriculteur";
            } else if (user instanceof RespResto) {
                role = "respResto";
            } else if (user instanceof RespAssoc) {
                role = "respAssoc";
            } else if (user instanceof Donateur) {
                role = "donateur";
            } else if (user instanceof Admin) {
                role = "admin";
            } else {
                role = "USER"; // Default role or an error handling case
            }

            // SQL query to insert the user with the determined role
            String req = "INSERT INTO `user`(`nom`, `prenom`, `email`, `password`, `roles`) VALUES (?,?,?,?,?)";
            try {
                PreparedStatement ps = cnx.prepareStatement(req);
                ps.setString(1, user.getNom());
                ps.setString(2, user.getPrenom());
                ps.setString(3, user.getEmail());
                ps.setString(4, user.getPassword());
                ps.setString(5, role); // Set the role in the database
                ps.executeUpdate();
                System.out.println("User added with role: " + role);
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }


    }

//    @Override
//    public void modifier(User user) {
//        String req = "UPDATE `user` SET `nom`=?, `prenom`=?, `email`=?, `password`=? WHERE `id`=?";
//        try {
//            PreparedStatement ps = cnx.prepareStatement(req);
//            ps.setString(1, user.getNom());
//            ps.setString(2, user.getPrenom());
//            ps.setString(3, user.getEmail());
//            ps.setString(4, user.getPassword());
//            ps.setInt(5, user.getId());
//            ps.executeUpdate();
//            System.out.println("User updated !");
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//        }
//    }

    public void modifier(User user) {
        // Determine the role based on the instance of the user
        String role;
        if (user instanceof Agriculteur) {
            role = "agriculteur";
        } else if (user instanceof RespResto) {
            role = "respResto";
        } else if (user instanceof RespAssoc) {
            role = "respAssoc";
        } else if (user instanceof Donateur) {
            role = "donateur";
        } else if (user instanceof Admin) {
            role = "admin";
        } else {
            role = "USER"; // Default role or an error handling case
        }

        // SQL query to update the user details and role based on ID
        String req = "UPDATE `user` SET `nom`=?, `prenom`=?, `email`=?, `password`=?, `roles`=? WHERE `id`=?";
        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, user.getNom());
            ps.setString(2, user.getPrenom());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getPassword());
            ps.setString(5, role); // Update the role in the database
            ps.setInt(6, user.getId()); // Use user ID as the key for update
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("User updated successfully with ID: " + user.getId());
            } else {
                System.out.println("No user found with ID: " + user.getId());
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    @Override
    public void supprimer(int id) {
        String req = "DELETE FROM `user` WHERE `id`=?";
        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("User deleted !");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

//    @Override
//    public User getOneById(int id) {
//        String req = "SELECT * FROM `user` WHERE `id`=?";
//        try {
//            PreparedStatement ps = cnx.prepareStatement(req);
//            ps.setInt(1, id);
//            ResultSet res = ps.executeQuery();
//            if (res.next()) {
//                User user = new User(res.getInt("id"), res.getString("nom"), res.getString("prenom"), res.getString("email"), res.getString("password"));
//                return user;
//            }
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//        }
//        return null;
//    }


//    @Override
//    public Set<User> getAll() {
//        Set<User> users = new HashSet<>();
//        String req = "SELECT * FROM `user`";
//        try {
//            Statement st = cnx.createStatement();
//            ResultSet res = st.executeQuery(req);
//            while (res.next()) {
//                User user = new User(res.getInt("id"), res.getString("nom"), res.getString("prenom"), res.getString("email"), res.getString("password"));
//                users.add(user);
//            }
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//        }
//        return users;
//    }

    @Override
    public User getOneById(int id) {
        String req = "SELECT * FROM `user` WHERE `id`=?";
        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, id);
            ResultSet res = ps.executeQuery();
            if (res.next()) {
                String role = res.getString("roles");
                User user;
                switch (role) {
                    case "agriculteur":
                        user = new Agriculteur(res.getInt("id"), res.getString("nom"), res.getString("prenom"), res.getString("email"), res.getString("password"));
                        break;
                    case "respResto":
                        user = new RespResto(res.getInt("id"), res.getString("nom"), res.getString("prenom"), res.getString("email"), res.getString("password"));
                        break;
                    case "respAssoc":
                        user = new RespAssoc(res.getInt("id"), res.getString("nom"), res.getString("prenom"), res.getString("email"), res.getString("password"));
                        break;
                    case "donateur":
                        user = new Donateur(res.getInt("id"), res.getString("nom"), res.getString("prenom"), res.getString("email"), res.getString("password"));
                        break;
                    case "admin":
                        user = new Admin(res.getInt("id"), res.getString("nom"), res.getString("prenom"), res.getString("email"), res.getString("password"));
                        break;
                    default:
                        user = new User(res.getInt("id"), res.getString("nom"), res.getString("prenom"), res.getString("email"), res.getString("password"));
                        break;
                }
                return user;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public Set<User> getAll() {
        Set<User> users = new HashSet<>();
        String req = "SELECT * FROM `user`";
        try {
            Statement st = cnx.createStatement();
            ResultSet res = st.executeQuery(req);
            while (res.next()) {
                int id = res.getInt("id");
                String nom = res.getString("nom");
                String prenom = res.getString("prenom");
                String email = res.getString("email");
                String password = res.getString("password");
                String role = res.getString("roles");

                // Based on the role, instantiate the correct subclass of User
                User user;
                switch (role) {
                    case "agriculteur":
                        user = new Agriculteur(id, nom, prenom, email, password);
                        break;
                    case "respResto":
                        user = new RespResto(id, nom, prenom, email, password);
                        break;
                    case "respAssoc":
                        user = new RespAssoc(id, nom, prenom, email, password);
                        break;
                    case "donateur":
                        user = new Donateur(id, nom, prenom, email, password);
                        break;
                    case "admin":
                        user = new Admin(id, nom, prenom, email, password);
                        break;
                    default:
                        user = new User(id, nom, prenom, email, password);
                        break;
                }

                users.add(user);
                // Optionally, print the user's details and role if you need to display them
                System.out.println("{"+"User ID: " + id + ", Name: " + nom + " " + prenom + ", Email: " + email + ", Role: " + role+"}");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return users;
    }



}
