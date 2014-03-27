package fr.insalyon.smartfridge.controleurs;

import fr.insalyon.smartfridge.modeles.Aliment;
import fr.insalyon.smartfridge.modeles.dao.AlimentDAO;
import org.eclipse.persistence.jpa.jpql.parser.DateTime;

import javax.persistence.Query;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * S'occupe des aliments périmés.
 */
public class ControleAlerte {

    public static int statusAlerte (int nombreLimiteJoursVoulu) {
        int statusAlerte= 0;
        ArrayList<Aliment> prochePeremption= new ArrayList<Aliment>();
        prochePeremption= listeAlimentsProchePeremption(nombreLimiteJoursVoulu);
        if (!prochePeremption.isEmpty()) {
            statusAlerte=1;
        }
        ArrayList<Aliment> perimes= new ArrayList<Aliment>();
        //perimes= listeAlimentsPerimes();
        if (!perimes.isEmpty()) {
            statusAlerte=2;
        }

        return statusAlerte;
    }

    public static ArrayList<Aliment> listeAlimentsProchePeremption (int nombreLimiteJoursVoulu) {
        ArrayList<Aliment> prochePeremption = AlimentDAO.tousTriesParPeremption();
        //TODO: trouver un truc pour parcourir les aliments et récupérer ceux qui ont une date de péremption entre aujourd'hui et la date limite

        String aujourdhui= calculDateToday();
        String calculDateLimite= calculDateLimite(nombreLimiteJoursVoulu);


        return prochePeremption;

    }

    /*TODO
    public static ArrayList<Aliment> listeAlimentsPerimes () {

    }
    */

    public static String calculDateToday(){
        Calendar aujourdhui = Calendar.getInstance(); // Donne la date d'aujourd'hui en format tout moche
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy"); // "Op�rateur" qui permet de simplifier le format pour avoir type 14/03/2014
        String aujourdhuiEnString = sdf.format(aujourdhui.getTime()); // On applique l'op�rateur � la date d'aujoud'hui pour avoir la date d'ajout
        return aujourdhuiEnString;

    }

    public static String calculDateLimite(int n){ // n : nombre de jours avant aujourd'hui qu'on veut vérifier
        Calendar aujourdhui = Calendar.getInstance();
        // TODO: voir si ça marche
        aujourdhui.add(Calendar.DATE,-n); // On enleve n jours à la date d aujourd hui
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String dateLimite = sdf.format(aujourdhui.getTime());
        return dateLimite;
    }

}
