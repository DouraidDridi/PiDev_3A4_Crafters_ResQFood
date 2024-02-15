package org.example;

import org.Entities.Conversation;
import org.Entities.Reclamation;
import org.Services.Conversation_Service;
import org.Services.Reclamation_Service;
import org.Utils.Etat_Message;
import org.Utils.Etat_Reclamation;
import org.Utils.Type_Reclamation;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        Reclamation rec = new Reclamation(1,"test", Etat_Reclamation.Analyser, Type_Reclamation.Technique,
                Date.valueOf(LocalDate.now()));

       Reclamation_Service rs = new Reclamation_Service();
       // rs.createReclamation(rec);

        //MODIFICATION
       // rec.setDescription("asma");
        //rec.setId(24);
        //rs.modifyReclamation(rec);




         //DELETE
        //rec.setId(23);
      // rs.deleteReclamation(rec);



        List<Reclamation> lst = rs.getReclamation();
        for(int i=0 ; i < lst.size();i++){
            System.out.println(lst.get(i).toString());
        }

//////


///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        Conversation cnv = new Conversation(1,25,"testjkhkjhkjhk",
                Date.valueOf(LocalDate.now()) ,Etat_Message.Not_Sent);
        Conversation_Service cs = new Conversation_Service();
        cs.createConversation(cnv);

        cnv.setMessage("asoumette");
       cnv.setId(15);
      cs.modifyConversation(cnv);

        //cs.deleteConversation(cnv);

        List<Conversation> lstc = cs.getConversation();
        for(int i=0 ; i < lstc.size();i++){
            System.out.println(lstc.get(i).toString());
        }

    }
}