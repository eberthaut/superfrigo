package fr.insalyon.smartfridge.services;


import fr.insalyon.smartfridge.modeles.*;
import fr.insalyon.smartfridge.modeles.dao.ArticleDAO;
import fr.insalyon.smartfridge.modeles.dao.BaseDAO;
import fr.insalyon.smartfridge.modeles.dao.TypeDAO;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.persistence.NoResultException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/** Importe des recettes depuis marmiton.org */
public class ServiceImportMarmiton implements ServiceImportInternet {
    /** L'URL de base du service */
    private final String BASE_MARMITON = "http://www.marmiton.org";
    /** Les inflexions au singulier non pluriel de certains mots */
    private final String[] INFLEXIONS = {
            "Maïs",
            "Ananas"
    };

    @Override
    public List<PropositionRecherche> rechercherRecettes(String recherche) {
        ArrayList<PropositionRecherche> propositions = new ArrayList<PropositionRecherche>();
        try {
            Document pageRecherche = Jsoup.parse(new URL(BASE_MARMITON + "/recettes/recherche.aspx?aqt=" + recherche), 2000);
            Elements liens = pageRecherche.select("div.m_search_titre_recette a");
            for(Element lien : liens) {
                propositions.add(new PropositionRecherche(lien.text(), lien.attr("href")));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return propositions;
    }

    @Override
    public Recette importRecette(PropositionRecherche proposition) {
        try {
            Document pageRecherche = Jsoup.parse(new URL(BASE_MARMITON + proposition.getId()), 2000);
            Element titreIngredients = pageRecherche.select("p.m_content_recette_ingredients span").first();
            Elements listeIngredients = pageRecherche.select("p.m_content_recette_ingredients a.mrm_al");
            int nbPersonnes = Integer.parseInt(titreIngredients.text().replaceAll("\\D+", ""));
            List<Ingredient> ingredients = new ArrayList<Ingredient>();
            BaseDAO.initialiserPersistence();
            for(Element elIngredient : listeIngredients) {
                int quantite = 1; // TODO: Faire une operation de l'espace sur le DOM pour recuperer cette valeur #bonnechance
                String nomIngredient = singulier(elIngredient.text());
                Article article;
                try {
                    article = ArticleDAO.trouve(nomIngredient);
                } catch(NoResultException e) {
                    System.out.println("ATTENTION! L'article " + nomIngredient + " n'a pas ete trouve pour l'import ! Il va etre ajoute avec les options par defaut.");
                    Type typeImport;
                    try {
                        typeImport = TypeDAO.trouve("Import");
                    } catch (NoResultException e2) {
                        typeImport = new Type("Import");
                        TypeDAO.creerTransaction();
                        TypeDAO.persiste(typeImport);
                        TypeDAO.faireTransactionSecurisee();
                    }
                    article = new Article(nomIngredient, 1, 10, 1, typeImport);
                    ArticleDAO.creerTransaction();
                    ArticleDAO.persiste(article);
                    ArticleDAO.faireTransactionSecurisee();
                }
                ingredients.add(new Ingredient(article, quantite));
            }
            BaseDAO.detruirePersistence();
            ServiceCourses.ajoutRecette(proposition.getNom(), nbPersonnes, ingredients);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /** Utilitaire de passage au singulier
     *
     * TODO: Mettre cette methode plus accessible
     *
     * @param mot Le mot a passer au singulier
     * @return Le mot au singulier
     */
    private String singulier(String mot) {
        mot = Character.toUpperCase(mot.charAt(0)) + mot.substring(1);
        char dernierChar = mot.charAt(mot.length() - 1);
        boolean inflexion = false;
        for (String INFLEXION : INFLEXIONS) {
            if (mot.equals(INFLEXION)) {
                inflexion = true;
            }
        }
        if(dernierChar == 's' || dernierChar == 'x') {
            if(!inflexion)
                mot = mot.substring(0, mot.length() - 1);
        }
        return mot;
    }
}
