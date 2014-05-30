package fr.insalyon.smartfridge.services;

import fr.insalyon.smartfridge.modeles.Aliment;
import fr.insalyon.smartfridge.modeles.dao.AlimentDAO;
import fr.insalyon.smartfridge.modeles.dao.BaseDAO;

import java.util.*;

/**
 * S'occupe des aliments périmés.
 */
public class ServiceAlerte {

    private static Date computeLimite(int nbJours) {
        Date aujourdhui = new Date();
        return new Date(aujourdhui.getTime() + (long)nbJours * 60 * 60 * 24 * 1000);
    }

    public static int statusAlerte (int nombreLimiteJoursVoulu) {
        BaseDAO.initialiserPersistence();
        int statusAlerte= 0;
        long prochePeremption = AlimentDAO.compteTousAvant(computeLimite(nombreLimiteJoursVoulu));
        if (prochePeremption > 0) {
            statusAlerte=1;
        }

        long perimes = AlimentDAO.compteTousAvant(computeLimite(0));
        if (perimes > 0) {
            statusAlerte=2;
        }
        BaseDAO.detruirePersistence();
        return statusAlerte;
    }

    public static List<Aliment> listeAlimentsProchePeremption (int nombreLimiteJoursVoulu) {
        BaseDAO.initialiserPersistence();
        List<Aliment> proches = AlimentDAO.tousAvant(computeLimite(nombreLimiteJoursVoulu));
        BaseDAO.detruirePersistence();
        return proches;

    }

    public static List<Aliment> listeAlimentsPerimes () {
        BaseDAO.initialiserPersistence();
        List<Aliment> perimes = AlimentDAO.tousAvant(computeLimite(0));
        BaseDAO.detruirePersistence();
        return perimes;
    }

}
