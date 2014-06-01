package fr.insalyon.smartfridge.services;

import fr.insalyon.smartfridge.modeles.Aliment;
import fr.insalyon.smartfridge.modeles.dao.AlimentDAO;
import fr.insalyon.smartfridge.modeles.dao.BaseDAO;

import java.util.List;

/** Gere la temperature du frigo */
public class ServiceThermodynamique {
    /** Met a jour la temperature
     *
     * @return La nouvelle temperature
     */
    public static double mettreAJourTemperature() {
        BaseDAO.initialiserPersistence();
        List<Aliment> aliments = AlimentDAO.tous();

        double masseTotale = 0;

        for(Aliment aliment : aliments) { // equivaut a for(int i = 0; i < aliments.size(); i ++) { Aliment aliment = aliments.get(i); // ... }
            masseTotale += aliment.getArticle().getMasse() * aliment.getQuantite();
        }

        // Calcul de temperature a partir de la masse
        double temperature = 5 - masseTotale / 30;

        System.out.println("=> Changement de temperature a " + temperature + " degres celcius.");
        BaseDAO.detruirePersistence();
        return temperature;
    }
}
