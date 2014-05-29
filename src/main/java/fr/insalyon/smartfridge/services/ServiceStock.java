package fr.insalyon.smartfridge.services;

import fr.insalyon.smartfridge.modeles.Aliment;
import fr.insalyon.smartfridge.modeles.Article;
import fr.insalyon.smartfridge.modeles.Type;
import fr.insalyon.smartfridge.modeles.dao.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;


/**
 * S'occupe de la gestion de stock.
 */
public class ServiceStock {

    public static List<Type> listerTypes() {
        BaseDAO.initialiserPersistence();
        List<Type> types = TypeDAO.tous();
        BaseDAO.detruirePersistence();
        return types;
    }

    public static List<Aliment> listerAliments() {
        BaseDAO.initialiserPersistence();
        List<Aliment> ali = AlimentDAO.tous();
        List<Aliment> aliments = AlimentDAO.tous();

       /* for(int y=0; y<aliments.size(); y++){
            System.out.println("tour aliment j : " + aliments.get(y).toString());
        }*/
        for(int j=0; j<aliments.size(); j++){
            //System.out.println("tour aliment j : " + aliments.get(j).toString());

            for(int k=j+1 ; k<aliments.size(); k++) {
                //System.out.println(lCourses.get(j).toString());
                if (aliments.get(j).getArticle().equals(aliments.get(k).getArticle())) {
                    // System.out.println("test"+aliments.get(j).toString());
                    aliments.get(j).setQuantite(aliments.get(j).getQuantite() + aliments.get(k).getQuantite());
                    aliments.remove(k);
                    k--;
                }
            }
        }
        BaseDAO.detruirePersistence();
        return aliments;
    }

    public static List<Article> listerArticles(Type type) {
        BaseDAO.initialiserPersistence();
        List<Article> articles = TypeDAO.listerArticlesType(type);
        BaseDAO.detruirePersistence();
        return articles;
    }

    public static List<Article> listerArticles() {
        BaseDAO.initialiserPersistence();
        List<Article> articles = ArticleDAO.tous();
        BaseDAO.detruirePersistence();
        return articles;
    }

    public static boolean retraitAliment (Aliment aliment, int quantite) {

        BaseDAO.initialiserPersistence();
        aliment = AlimentDAO.trouveId(aliment.getId());
        BaseDAO.creerTransaction();

        List<Aliment> lAli = AlimentDAO.tous();
        List<Aliment> lRegroupee = new ArrayList<Aliment>();
        Aliment aliAsupprimer = aliment;
        int quantiteRestante = quantite;

        for(int i=0; i<lAli.size();i++){
            System.out.println(lAli.get(i));
        }

        for (int i = 0; i < lAli.size(); i++) {
            if (aliment.getArticle().equals(lAli.get(i).getArticle())) {
                lRegroupee.add(lAli.get(i));
            }
        }

        while (quantiteRestante != 0) {
            for (int i = 0; i < lRegroupee.size(); i++) {
                System.out.println("liste: " + lRegroupee.get(i));
                if (aliAsupprimer.getDateAjout().after(lRegroupee.get(i).getDateAjout())) {
                    aliAsupprimer = lRegroupee.get(i);
                }
            }

            if(aliAsupprimer.getQuantite() == quantiteRestante){
                aliAsupprimer.setQuantite(0);
                AlimentDAO.supprime(aliAsupprimer);
                quantiteRestante=0;
            }
            if (aliAsupprimer.getQuantite() > quantiteRestante) {
                aliAsupprimer.setQuantite(aliAsupprimer.getQuantite()-quantiteRestante);
                AlimentDAO.miseAJour(aliAsupprimer);
                quantiteRestante = 0;

            } else if (aliAsupprimer.getQuantite() < quantiteRestante) {
                AlimentDAO.supprime(aliAsupprimer);
                lRegroupee.remove(aliAsupprimer);
                quantiteRestante = quantiteRestante - aliAsupprimer.getQuantite();
                aliAsupprimer= lRegroupee.get(0);
            }
        }
        BaseDAO.faireTransaction();
        BaseDAO.detruirePersistence();
        return true;
    }

    public static boolean ajouterAliment (Article article, int quantite) {
        BaseDAO.initialiserPersistence();
        Date datePeremption = new Date(new Date().getTime() + article.getJoursPeremption() * 60 * 60 * 24 * 1000);
        Aliment aliment= new Aliment(article, datePeremption, quantite);
        BaseDAO.creerTransaction();
        AlimentDAO.persiste(aliment);
        BaseDAO.faireTransaction();
        BaseDAO.detruirePersistence();
        return true;
    }


}
