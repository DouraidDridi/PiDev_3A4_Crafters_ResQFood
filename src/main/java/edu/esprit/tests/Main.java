package edu.esprit.tests;


import edu.esprit.entities.Restaurant;
import edu.esprit.service.ServiceRestau;
import edu.esprit.utils.DataSource;
import edu.esprit.service.ServiceRestau;

import java.util.Set;

import static edu.esprit.entities.Restaurant.*;

public class Main {
    public static void main(String[] args) {

        DataSource.getInstance() ;
        ServiceRestau r=new ServiceRestau() ;
        Restaurant  r1= new Restaurant("sarra","3 rue liberte","res@gmail.com",97556241) ;
        Restaurant  r2= new Restaurant("mmm","55 rue libe","mmms@gmail.com",94788511) ;
         r.ajouter(r2);
        //r1.setIdR(2);
       // r.modifier(r1);
       // r.supprimer(2) ;

        Set<Restaurant> allRestau = r.getAll();

        / /  Display the details of each Personne
        for (Restaurant res : allRestau) {
            System.out.println("ID: " + res.getIdR() +
                    ", Nom: " + res.getNomR() +
                    ", adresse: " + res.getAdresseR()+
                   ",email: "+res.getEmailR() +
                    ", phone: " +res.getPhoneR());
        }




    }

}