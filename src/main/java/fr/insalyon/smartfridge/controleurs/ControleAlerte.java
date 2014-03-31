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
        List<Aliment> prochePeremption= listeAlimentsProchePeremption(nombreLimiteJoursVoulu);
        if (!prochePeremption.isEmpty()) {
            statusAlerte=1;
        }

        List<Aliment> perimes = listeAlimentsPerimes();
        if (!perimes.isEmpty()) {
            statusAlerte=2;
        }

        return statusAlerte;
    }

    public static List<Aliment> listeAlimentsProchePeremption (int nombreLimiteJoursVoulu) {
        List<Aliment> tries = AlimentDAO.tousTriesParPeremption();
        List<Aliment> proches = new ArrayList<Aliment>();
        Date aujourdhui = new Date();
        Date dateAlerte = new Date(aujourdhui.getTime() + nombreLimiteJoursVoulu * 60 * 60 * 24);
        for(Aliment aliment : tries) {
            if(aliment.getDatePeremption().compareTo(dateAlerte) < 0 && aliment.getDatePeremption().compareTo(aujourdhui) > 0) {
                proches.add(aliment);
            } else if(aliment.getDatePeremption().compareTo(aujourdhui) > 0) {
                break;
            }
        }
        return proches;

    }

    public static List<Aliment> listeAlimentsPerimes () {
        List<Aliment> tries = AlimentDAO.tousTriesParPeremption();
        List<Aliment> perimes = new ArrayList<Aliment>();
        Date aujourdhui = new Date();
        for(Aliment aliment : tries) {
            if(aliment.getDatePeremption().compareTo(aujourdhui) < 0) {
                perimes.add(aliment);
            } else {
                break;
            }
        }
        return perimes;
    }

}
