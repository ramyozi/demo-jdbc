package fr.diginamic.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class TestUpdate {
	public static void main(String[] args) throws SQLException {
		DriverManager.registerDriver(new org.mariadb.jdbc.Driver());
		String url = "jdbc:mariadb://localhost:3306/campta";
		String utilisateur = "root";
		String motDePasse = "";

		Connection connexion = null;

		try {
			connexion = DriverManager.getConnection(url, utilisateur,
					motDePasse);

			System.out.println(
					"Connexion à la base de données compta établie.");
			
			Statement statement = connexion.createStatement();
            String req = "UPDATE fournisseur SET NOM = 'La Maison des Peintures' WHERE ID = 0";
            int rowCount = statement.executeUpdate(req);

            if (rowCount > 0) {
                System.out.println("Mise à jour effectuée avec succès.");
            } else {
                System.out.println("Aucune ligne mise à jour.");
            }
            
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
	}
}
