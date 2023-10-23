package fr.diginamic.jdbc.dao;

import fr.diginamic.jdbc.entites.Fournisseur;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FournisseurDaoJdbc implements FournisseurDao {
    private String url = "jdbc:mysql://localhost:3306/campta";
    private String utilisateur = "root";
    private String motDePasse = "";

    @Override
    public List<Fournisseur> extraire() {
        List<Fournisseur> fournisseurs = new ArrayList<>();

        try (Connection connexion = DriverManager.getConnection(url, utilisateur, motDePasse);
             Statement statement = connexion.createStatement()) {
            String req = "SELECT ID, NOM FROM fournisseur";
            ResultSet resultSet = statement.executeQuery(req);

            while (resultSet.next()) {
                int id = resultSet.getInt("ID");
                String nom = resultSet.getString("NOM");
                Fournisseur fournisseur = new Fournisseur(id, nom);
                fournisseurs.add(fournisseur);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return fournisseurs;
    }

    @Override
    public void insert(Fournisseur fournisseur) {
        try (Connection connexion = DriverManager.getConnection(url, utilisateur, motDePasse);
             Statement lastIdStatement = connexion.createStatement();
             PreparedStatement insertStatement = connexion.prepareStatement("INSERT INTO fournisseur (ID, NOM) VALUES (?, ?)")) {

            // Trouver le dernier ID dans la table
            ResultSet resultSet = lastIdStatement.executeQuery("SELECT MAX(ID) FROM fournisseur");
            int lastId = 0;
            if (resultSet.next()) {
                lastId = resultSet.getInt(1);
            }

            // Utiliser ID + 1 pour l'insertion
            insertStatement.setInt(1, lastId + 1);
            insertStatement.setString(2, fournisseur.getNom());
            insertStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int update(String ancienNom, String nouveauNom) {
        int rowCount = 0;
        try (Connection connexion = DriverManager.getConnection(url, utilisateur, motDePasse);
             PreparedStatement statement = connexion.prepareStatement("UPDATE fournisseur SET NOM = ? WHERE NOM = ?")) {
            statement.setString(1, nouveauNom);
            statement.setString(2, ancienNom);
            rowCount = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowCount;
    }

    @Override
    public boolean delete(Fournisseur fournisseur) {
        boolean deleted = false;
        try (Connection connexion = DriverManager.getConnection(url, utilisateur, motDePasse);
             PreparedStatement statement = connexion.prepareStatement("DELETE FROM fournisseur WHERE ID = ? AND NOM = ?")) {
            statement.setInt(1, fournisseur.getId());
            statement.setString(2, fournisseur.getNom());
            int rowCount = statement.executeUpdate();
            deleted = rowCount > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return deleted;
    }
}
