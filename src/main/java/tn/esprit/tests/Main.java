package tn.esprit.tests;

import tn.esprit.entities.Admin;
import tn.esprit.entities.Agriculteur;
import tn.esprit.entities.User;
import tn.esprit.services.ServiceUser;

public class Main {
    public static void main(String[] args) {
        ServiceUser su = new ServiceUser();

        // Create a new user
        User newUser = new Agriculteur();
        newUser.setNom("agric");
        newUser.setPrenom("min");
        newUser.setEmail("agric.dridi@esprit.tn");
        newUser.setPassword("secret123");
        String role = "agriculteur";



        /// Add the new user to the database
        su.ajouter(newUser,role);

        //Retrieve the updated list of users
     System.out.println("Updated list of users:");
     su.getAll();

        newUser.setId(12);
        // Modify the user's details
        newUser.setNom("updatedAgric");
        newUser.setPrenom("updatedMin");
        newUser.setEmail("updated.agric.dridi@esprit.tn");
        newUser.setPassword("newSecret123");
        
        su.modifier(newUser);

        System.out.println("Updated list of users after modification:");
        su.getAll();

        // Delete the user from the database : juste active the next line !
       // su.supprimer(newUser.getId());

        System.out.println("Updated list of users after deletion:");
        su.getAll();

    }
}
