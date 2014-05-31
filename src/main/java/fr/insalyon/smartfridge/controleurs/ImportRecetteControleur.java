package fr.insalyon.smartfridge.controleurs;

import fr.insalyon.smartfridge.modeles.PropositionRecherche;
import fr.insalyon.smartfridge.services.ServiceImportInternet;
import fr.insalyon.smartfridge.services.ServiceImportMarmiton;
import fr.insalyon.smartfridge.vues.Fenetre;
import fr.insalyon.smartfridge.vues.ImportRecette;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ImportRecetteControleur implements ActionListener {
    private ImportRecette importRecette;
    private Fenetre fenetre;
    private ListModel<PropositionRecherche> liensRecettes;
    private ServiceImportInternet serviceImport = new ServiceImportMarmiton();

    public ImportRecetteControleur(Fenetre fenetre, ImportRecette importRecette) {
        this.fenetre = fenetre;
        this.importRecette = importRecette;

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(importRecette.getRechercheButton())) {
            String recherche = importRecette.getRechercheField().getText();
            liensRecettes = new ListModel<PropositionRecherche>(serviceImport.rechercherRecettes(recherche));
            importRecette.getPropositionsList().setModel(liensRecettes);
        } else if(e.getSource().equals(importRecette.getImportButton())) {
            serviceImport.importRecette((PropositionRecherche)importRecette.getPropositionsList().getSelectedValue());
        }
    }
}
