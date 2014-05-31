package fr.insalyon.smartfridge.services;

import fr.insalyon.smartfridge.modeles.Aliment;
import fr.insalyon.smartfridge.modeles.dao.AlimentDAO;
import fr.insalyon.smartfridge.modeles.dao.BaseDAO;

import java.util.*;

/** Determine les aliments perimes ou proches de la peremption */
public class ServiceAlerte {

    private static Date computeLimite(int nbJours) {
        Date aujourdhui = new Date();
        return new Date(aujourdhui.getTime() + (long)nbJours * 60 * 60 * 24 * 1000);
    }

    /** Determine le type d'alerte
     *
     * @param nombreLimiteJoursVoulu A combien de jour commence t'on a prevenir de la peremption proche ?
     * @return 0 si tout va bien, 1 si des aliments vont bientot perimer et 2 si il y a des aliments perimes
     */
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

    /** Donne les aliments qui vont bientot perimer
     *
     * @param nombreLimiteJoursVoulu A combien de jours ?
     * @return Les aliments qui periment bientot
     */
    public static List<Aliment> listeAlimentsProchePeremption (int nombreLimiteJoursVoulu) {
        BaseDAO.initialiserPersistence();
        List<Aliment> proches = AlimentDAO.tousAvant(computeLimite(nombreLimiteJoursVoulu));
        BaseDAO.detruirePersistence();
        return proches;

    }

    /** Donne les aliments perimes
     *
     * @return Les aliments qui ont perime
     */
    public static List<Aliment> listeAlimentsPerimes () {
        BaseDAO.initialiserPersistence();
        List<Aliment> perimes = AlimentDAO.tousAvant(computeLimite(0));
        BaseDAO.detruirePersistence();
        return perimes;
    }

}
