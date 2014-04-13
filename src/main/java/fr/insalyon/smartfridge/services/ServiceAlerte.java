package fr.insalyon.smartfridge.services;

import fr.insalyon.smartfridge.modeles.Aliment;
import fr.insalyon.smartfridge.modeles.dao.AlimentDAO;
import fr.insalyon.smartfridge.modeles.dao.BaseDAO;

import java.util.*;

/**
 * S'occupe des aliments périmés.
 */
public class ServiceAlerte {

    public static int statusAlerte (int nombreLimiteJoursVoulu) {
        int statusAlerte= 0;
        List<Aliment> prochePeremption = listeAlimentsProchePeremption(nombreLimiteJoursVoulu);
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
        BaseDAO.initialiserPersistence();
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
        BaseDAO.detruirePersistence();
        return proches;

    }

    public static List<Aliment> listeAlimentsPerimes () {
        BaseDAO.initialiserPersistence();
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
        BaseDAO.detruirePersistence();
        return perimes;
    }

}
