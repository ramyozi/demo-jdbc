package fr.diginamic.jdbc.dao;

import fr.diginamic.jdbc.dao.FournisseurDaoJdbc;
import fr.diginamic.jdbc.entites.Fournisseur;

import java.util.List;

public class TestDaoJdbc {
    public static void main(String[] args) {
        FournisseurDaoJdbc fournisseurDao = new FournisseurDaoJdbc();

        // Insérer un fournisseur avec un ID spécifique
        Fournisseur fournisseurAInserer = new Fournisseur(1, "France de matériaux");
        fournisseurDao.insert(fournisseurAInserer);

        // Extraire et afficher la liste des fournisseurs
        List<Fournisseur> fournisseurs = fournisseurDao.extraire();
        System.out.println("Liste des fournisseurs après insertion :");
        afficherFournisseurs(fournisseurs);

        // Modifier le fournisseur "France de matériaux" en "France matériaux"
        fournisseurDao.update("France de matériaux", "France matériaux");

        // Extraire et afficher la liste des fournisseurs après la modification
        fournisseurs = fournisseurDao.extraire();
        System.out.println("Liste des fournisseurs après modification :");
        afficherFournisseurs(fournisseurs);

        // Supprimer le fournisseur "France matériaux"
        Fournisseur fournisseurASupprimer = new Fournisseur(1, "France matériaux");
        fournisseurDao.delete(fournisseurASupprimer);

        // Extraire et afficher la liste des fournisseurs après la suppression
        fournisseurs = fournisseurDao.extraire();
        System.out.println("Liste des fournisseurs après suppression :");
        afficherFournisseurs(fournisseurs);
    }

    private static void afficherFournisseurs(List<Fournisseur> fournisseurs) {
        for (Fournisseur fournisseur : fournisseurs) {
            System.out.println("ID : " + fournisseur.getId() + ", NOM : " + fournisseur.getNom());
        }
    }
}
