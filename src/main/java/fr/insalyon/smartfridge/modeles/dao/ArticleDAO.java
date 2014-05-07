package fr.insalyon.smartfridge.modeles.dao;

import fr.insalyon.smartfridge.modeles.Aliment;
import fr.insalyon.smartfridge.modeles.Article;
import fr.insalyon.smartfridge.modeles.Recette;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO de gestion d'articles
 */
public class ArticleDAO extends BaseDAO{
    /**
     * @param id Id de l'article
     * @return L'article
     */
    public static Article trouveId(long id) {
        return (Article) trouveId(Article.class, id); //Article.class retourne le type de la classe
    }

    /**
     * @return Toutes les entites de Article
     */
    public static List<Article> tous() {
        return (List<Article>) tous("Article");
    }

    /**
     * @return Toutes les entites de Article classees par habitudes de façon descendante
     */
    public static List<Article> tousTriesParHabitude() {
        Query q = getEntityManager().createQuery("SELECT article FROM Article article ORDER BY article.habitude DESC");
        return q.getResultList();
    }

    public static List<Article> transformationAliment() {

        List<Article> lArticle = new ArrayList<Article>();
        List<Aliment> lAliments = AlimentDAO.tous();
        List<Article> listeArticle = ArticleDAO.tous();
        Article a;
        for (int i = 0; i < lAliments.size(); i++) {
            long id = lAliments.get(i).getArticle().getId();
            for (int j = 0; j < listeArticle.size(); j++) {
                if (id == listeArticle.get(j).getId()) {
                    a = listeArticle.get(j);
                    //System.out.println(a.getNom());
                    if (lAliments.get(i).getQuantite() < listeArticle.get(j).getHabitude()) {
                        lArticle.add(0, a);
                        //System.out.println(lArticle.get(0).getNom());
                    }
                }
            }
        }

        List<Article> lArticleAbsentDuFrigo = ArticleDAO.tous();
        for(int e= 0; e<lArticleAbsentDuFrigo.size();e++) {
            Article b = lArticleAbsentDuFrigo.get(e);
            for (int g = 0; g < lArticle.size(); g++) {
                Article c = lArticle.get(g);
                if (b.equals(c)) {
                    System.out.println(b.getNom());
                    lArticleAbsentDuFrigo.remove(e);
                }
            }
        }

        for (int h = 0; h <lArticleAbsentDuFrigo.size(); h++) {
            Article d = lArticleAbsentDuFrigo.get(h);
            if(d.getHabitude() != 0){
                //System.out.println(a.getNom());
                lArticle.add(0, d);
                //System.out.println(lArticle.get(0).getNom());
            }
        }

        List<Recette> listeRecettes = RecetteDAO.tous();

        // TODO: Mettre a jour pour les ingredients
        /*for(int p=0; p< listeRecettes.size(); p++){
            if(listeRecettes.get(p).isActif()==true) {
                List<Article> lArticleRecette = listeRecettes.get(p).getArticles();
                for (int q = 0; q < lArticleRecette.size(); q++) {
                    Article f = lArticleRecette.get(q);
                    lArticle.add(f);
                }
            }
        }*/

        return lArticle;
    }
}

