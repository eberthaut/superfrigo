package fr.insalyon.smartfridge.services;


import fr.insalyon.smartfridge.modeles.PropositionRecherche;
import fr.insalyon.smartfridge.modeles.Recette;

import java.util.List;

public class ServiceImportMarmiton implements ServiceImportInternet {
    @Override
    public List<PropositionRecherche> rechercherRecettes(String recherche) {
        return null;
    }

    @Override
    public Recette importRecette(PropositionRecherche proposition) {
        return null;
    }
}
