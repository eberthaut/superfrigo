package fr.insalyon.smartfridge.services;


import fr.insalyon.smartfridge.modeles.PropositionRecherche;
import fr.insalyon.smartfridge.modeles.Recette;

import java.util.List;

/** Interface generique pour l'import de recettes sur internet */
public interface ServiceImportInternet {
    /** Liste les propositions de recherche d'un service internet
     *
     * @param recherche La recherche de recette
     * @return Une Proposition
     */
    public List<PropositionRecherche> rechercherRecettes(String recherche);

    /** A partir d'une proposition, recuperer et creer la Recette
     *
     * @param proposition La Proposition
     * @return La recette cree et persistee en base de donnees
     */
    public Recette importRecette(PropositionRecherche proposition);
}

