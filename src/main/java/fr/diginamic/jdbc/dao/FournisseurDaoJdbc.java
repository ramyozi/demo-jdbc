package fr.diginamic.jdbc.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.diginamic.jdbc.entites.Fournisseur;

public class FournisseurDaoJdbc implements FournisseurDao {
    private String url = "jdbc:mysql://localhost:3306/compta";
    private String utilisateur = "votreUtilisateur";
    private String motDePasse = "votreMotDePasse";

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
             PreparedStatement statement = connexion.prepareStatement("INSERT INTO fournisseur (NOM) VALUES (?)")) {
            statement.setString(1, fournisseur.getNom());
            statement.executeUpdate();
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
             PreparedStatement statement = connexion.prepareStatement("DELETE FROM fournisseur WHERE ID = ?")) {
            statement.setInt(1, fournisseur.getId());
            int rowCount = statement.executeUpdate();
            deleted = rowCount > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return deleted;
    }
}
