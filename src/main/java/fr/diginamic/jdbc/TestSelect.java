package fr.diginamic.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.diginamic.jdbc.entites.Fournisseur;

public class TestSelect {

	public static void main(String[] args) throws SQLException {
		DriverManager.registerDriver(new org.mariadb.jdbc.Driver());
		String url = "jdbc:mariadb://localhost:3306/campta";
		String utilisateur = "root";
		String motDePasse = "";

		Connection connexion = null;
		List<Fournisseur> fournisseurs = new ArrayList<>();

		try {
			connexion = DriverManager.getConnection(url, utilisateur,
					motDePasse);
			System.out.println(
					"Connexion à la base de données compta établie.");

			Statement statement = connexion.createStatement();
			String req = "SELECT ID, NOM FROM fournisseur";
			ResultSet resultSet = statement.executeQuery(req);

			while (resultSet.next()) {
				int id = resultSet.getInt("ID");
				String nom = resultSet.getString("NOM");
				Fournisseur fournisseur = new Fournisseur(id, nom);
				fournisseurs.add(fournisseur);
			}

			resultSet.close();
			statement.close();

		} catch (SQLException e) {
			System.err.println(
					"Erreur lors de la connexion à la base de données : "
							+ e.getMessage());
		} finally {
			if (connexion != null) {
				try {
					connexion.close();
					System.out.println(
							"Connexion à la base de données bdd_test fermée.");
				} catch (SQLException e) {
					System.err.println(
							"Erreur lors de la fermeture de la connexion : "
									+ e.getMessage());
				}
			}
		}
		
		
		System.out.println("-----------------------------");
		System.out.println("ID\t| NOM");
		System.out.println("-----------------------------");
		for (Fournisseur fournisseur : fournisseurs) {
			System.out.println(
					fournisseur.getId() + "\t| " + fournisseur.getNom());
		}
		System.out.println("-----------------------------");


	}
}
