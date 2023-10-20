package connexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestConnexionJdbc {

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		DriverManager.registerDriver(new org.mariadb.jdbc.Driver());
		String url = "jdbc:mariadb://localhost:3306/bdd_test";
		String utilisateur = "root";
		String motDePasse = "";

		Connection connexion = null;

		 try {
	            connexion = DriverManager.getConnection(url, utilisateur, motDePasse);

	            System.out.println("Connexion à la base de données compta établie.");
	            System.out.println("Connexion : " + connexion);

	        } catch (SQLException e) {
	            System.err.println("Erreur lors de la connexion à la base de données : " + e.getMessage());
	        } finally {
	            if (connexion != null) {
	                try {
	                    connexion.close();
	                    System.out.println("Connexion à la base de données bdd_test fermée.");
	                } catch (SQLException e) {
	                    System.err.println("Erreur lors de la fermeture de la connexion : " + e.getMessage());
	                }
	            }
	        }
	    }
	}