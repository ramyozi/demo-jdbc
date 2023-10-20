package fr.diginamic.jdbc.dao;

import java.util.List;

import fr.diginamic.jdbc.entites.Fournisseur;

public class TestDaoJdbc {
    public static void main(String[] args) {
        FournisseurDaoJdbc fournisseurDao = new FournisseurDaoJdbc();

        // Insérer 
        Fournisseur fournisseurAInserer = new Fournisseur(0, "France de matériaux");
        fournisseurDao.insert(fournisseurAInserer);

        // Extraire et afficher 
        List<Fournisseur> fournisseurs = fournisseurDao.extraire();
        System.out.println("Liste des fournisseurs après insertion :");
        afficherFournisseurs(fournisseurs);

        // Modifier 
        fournisseurDao.update("France de matériaux", "France matériaux");

        // Extraire et afficher 
        fournisseurs = fournisseurDao.extraire();
        System.out.println("Liste des fournisseurs après modification :");
        afficherFournisseurs(fournisseurs);

        // Supprimer 
        Fournisseur fournisseurASupprimer = new Fournisseur(0, "France matériaux");
        fournisseurDao.delete(fournisseurASupprimer);

        // Extraire et afficher
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
