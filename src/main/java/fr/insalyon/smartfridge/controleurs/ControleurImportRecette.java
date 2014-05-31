package fr.insalyon.smartfridge.controleurs;

import fr.insalyon.smartfridge.modeles.PropositionRecherche;
import fr.insalyon.smartfridge.services.ServiceImportInternet;
import fr.insalyon.smartfridge.services.ServiceImportMarmiton;
import fr.insalyon.smartfridge.utilitaires.Fenetre;
import fr.insalyon.smartfridge.utilitaires.ListModel;
import fr.insalyon.smartfridge.vues.VueImportRecette;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControleurImportRecette implements ActionListener {
    private VueImportRecette vue;
    private Fenetre fenetre;
    private ServiceImportInternet serviceImport = new ServiceImportMarmiton();

    public ControleurImportRecette(Fenetre fenetre, VueImportRecette vue) {
        this.fenetre = fenetre;
        this.vue = vue;

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(vue.getRechercheButton())) {
            String recherche = vue.getRechercheField().getText();
            ListModel<PropositionRecherche> liensRecettes = new ListModel<PropositionRecherche>(serviceImport.rechercherRecettes(recherche));
            vue.getPropositionsList().setModel(liensRecettes);
        } else if(e.getSource().equals(vue.getImportButton())) {
            serviceImport.importRecette((PropositionRecherche) vue.getPropositionsList().getSelectedValue());
            fenetre.retourArriere();
        }
    }
}
