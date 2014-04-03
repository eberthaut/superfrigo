package fr.insalyon.smartfridge.services;

import fr.insalyon.smartfridge.modeles.Aliment;
import fr.insalyon.smartfridge.modeles.dao.AlimentDAO;

import java.util.List;

public class ServiceThermodynamique {
    public static void mettreAJourTemperature() {
        List<Aliment> aliments = AlimentDAO.tous();

        double masseTotale = 0;

        for(Aliment aliment : aliments) { // equivaut à for(int i = 0; i < aliments.size(); i ++) { Aliment aliment = aliments.get(i); // ... }
            masseTotale += aliment.getArticle().getMasse() * aliment.getQuantite();
        }

        // Calcul de temperature a partir de la masse
        double temperature = 5 - masseTotale / 30;

        System.out.println("=> Changement de temperature a " + temperature + " degres celcius.");
    }
}
