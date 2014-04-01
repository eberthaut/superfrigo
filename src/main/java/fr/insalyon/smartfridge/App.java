package fr.insalyon.smartfridge;


import fr.insalyon.smartfridge.controleurs.ControleStock;
import fr.insalyon.smartfridge.modeles.Aliment;
import fr.insalyon.smartfridge.modeles.Article;
import fr.insalyon.smartfridge.modeles.Type;
import fr.insalyon.smartfridge.modeles.dao.AlimentDAO;
import fr.insalyon.smartfridge.modeles.dao.ArticleDAO;
import fr.insalyon.smartfridge.modeles.dao.BaseDAO;
import fr.insalyon.smartfridge.modeles.dao.TypeDAO;

import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        BaseDAO.initialiserPersistence();

        Type unType = new Type("Fruit");
        BaseDAO.creerTransaction();
        TypeDAO.persiste(unType);
        BaseDAO.faireTransaction();

        Type leMemeType = TypeDAO.trouveId(unType.getId());

        System.out.println(leMemeType.getNom());

        Article article = new Article("Pomme", "", 0.1, 7, 0.2);

        BaseDAO.creerTransaction();
        ArticleDAO.persiste(article);
        BaseDAO.faireTransaction();

        ControleStock.ajouterAliment(article, 3);

        AlimentDAO.tous();

        BaseDAO.detruirePersistence(); // Destruction de la connexion à la Base de données


    }
}
