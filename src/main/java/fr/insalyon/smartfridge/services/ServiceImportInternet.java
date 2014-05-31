package fr.insalyon.smartfridge.services;


import fr.insalyon.smartfridge.modeles.PropositionRecherche;
import fr.insalyon.smartfridge.modeles.Recette;

import java.util.List;

public interface ServiceImportInternet {
    public List<PropositionRecherche> rechercherRecettes(String recherche);
    public Recette importRecette(PropositionRecherche proposition);
}

