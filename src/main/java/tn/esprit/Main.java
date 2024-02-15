package tn.esprit;

import edu.esprit.entites.AvisProduit;
import edu.esprit.entites.Produit;
import edu.esprit.service.ServiceAvisProduit;
import edu.esprit.service.ServiceProduit;
import edu.esprit.utils.DataSource;

import java.util.Set;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        DataSource.getInstance().getCnx();


        ServiceProduit sp = new ServiceProduit();
        Produit produit = new Produit();
        AvisProduit a = new AvisProduit();
        ServiceAvisProduit s = new ServiceAvisProduit();

      /* a.setIdproduit(1);                        //1°AJOUT AVIS
       a.setNbstar(2);
       a.setCommentaire("TRES BON");
       a.setIduser(2);
       s.ajouter(a);
       //System.out.println(sp.getAll());*/



       /* a.setIdAvis(2);                            //2°MODIFIER AVIS
        a.setIdproduit(2);
        a.setNbstar(4);
        a.setCommentaire("Très bon produit !");
        s.modifier(a); */


        /*s.supprimer(1);                           //3°SUPPRIMER AVIS




        /*sp.ajouter(new Produit("amal", 8000, "image_produit", "vgjb")); /*    // 1° AJOUTER PRODUIT


        //System.out.println(sp.getAll());


      produit.setIdProduit(1);                                                 //2° MODIFIER PRODUIT
        produit.setNomProduit("nom modifée");
        produit.setDescription("description modifié");
        produit.setPrix(400);
        produit.setImage_produit("image modifée");

        sp.modifier(produit);





               sp.supprimer(1);                                                     //3° SUPPRIMER PRODUIT
         */

//        Set<Produit> produits = sp.getAll();
//        System.out.println("Nombre de produit : " + produits.size());


         /* produit = sp.getOneById(2); // Remplacez 1 par l'ID du produit que vous souhaitez récupérer

              if (produit != null) {
          System.out.println("Produit récupéré avec succès !");
          System.out.println("ID: " + produit.getIdProduit());
          System.out.println("Nom du produit: " + produit.getNomProduit());
          System.out.println("Prix: " + produit.getPrix());                             // GET ALL PRODUIT
          System.out.println("Image: " + produit.getImage_produit());
          System.out.println("Description: " + produit.getDescription());
          } else {
          System.out.println("Aucun produit trouvé avec cet ID.");
         }
         */

        System.out.println(sp.getAll());


    }
}
