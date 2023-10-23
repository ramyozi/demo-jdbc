package tp6;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class ImportAndExportData {

    public static void main(String[] args) {
        String jdbcURL = "jdbc:mariadb://localhost:3306/tp6autre";
        String username = "root";
        String password = "";
        String csvFilePath = "C:\\Users\\ramzi\\Desktop\\Spring projects\\demo-jdbc\\recensement2016.csv";

        Map<String, Integer> regionIdMap = new HashMap<>();
        Map<String, Integer> deptIdMap = new HashMap<>();

        try {
            Connection connection = DriverManager.getConnection(jdbcURL, username, password);
            connection.setAutoCommit(false);  // Disable auto-commit to manage transactions
            BufferedReader reader = new BufferedReader(new FileReader(csvFilePath));

            String line;
            reader.readLine();

            while ((line = reader.readLine()) != null) {
                String[] data = line.split(";");
                String idRegion = data[0];
                String nomRegion = data[1];
                String idDept = data[2];

                // Check for duplicates for regions and departments
                if (!regionIdMap.containsKey(idRegion)) {
                    String regionSql = "INSERT INTO REGIONS (nom) VALUES (?)";
                    PreparedStatement regionStatement = connection.prepareStatement(regionSql, PreparedStatement.RETURN_GENERATED_KEYS);
                    regionStatement.setString(1, nomRegion);
                    regionStatement.executeUpdate();

                    ResultSet generatedKeys = regionStatement.getGeneratedKeys();
                    if (generatedKeys.next()) {
                        int regionId = generatedKeys.getInt(1);
                        regionIdMap.put(idRegion, regionId);
                        System.out.println("Added Region: " + nomRegion + " with id: " + regionId);
                    }
                }

                if (!deptIdMap.containsKey(idDept)) {
                    String deptSql = "INSERT INTO DEPTS (code) VALUES (?)";
                    PreparedStatement deptStatement = connection.prepareStatement(deptSql, PreparedStatement.RETURN_GENERATED_KEYS);
                    deptStatement.setString(1, data[3]);
                    deptStatement.executeUpdate();

                    ResultSet generatedKeys = deptStatement.getGeneratedKeys();
                    if (generatedKeys.next()) {
                        int deptId = generatedKeys.getInt(1);
                        deptIdMap.put(idDept, deptId);
                        System.out.println("Added Department with id: " + deptId);
                    }
                }
            }

            reader = new BufferedReader(new FileReader(csvFilePath));
            reader.readLine();

            while ((line = reader.readLine()) != null) {
                String[] data = line.split(";");
                String idRegion = data[0];
                String idDept = data[2];
                String nomVille = data[6];
                int population = Integer.parseInt(data[7].trim().replace(" ", ""));

                int regionId = regionIdMap.get(idRegion);
                int deptId = deptIdMap.get(idDept);

                // Insert VILLE, referencing DEPTS and REGIONS (IDs)
                String villeSql = "INSERT INTO VILLES (nom, population, idDept, idRegion) VALUES (?, ?, ?, ?)";
                PreparedStatement villeStatement = connection.prepareStatement(villeSql, PreparedStatement.RETURN_GENERATED_KEYS);
                villeStatement.setString(1, nomVille);
                villeStatement.setInt(2, population);
                villeStatement.setInt(3, deptId);
                villeStatement.setInt(4, regionId);
                villeStatement.executeUpdate();

                // Retrieve the auto-generated 'id' for the VILLE
                ResultSet generatedKeys = villeStatement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int villeId = generatedKeys.getInt(1);
                    System.out.println("Added Ville: " + nomVille + " with population: " + population + " and id: " + villeId);
                }
            }

            connection.commit();  // Commit the transaction
            System.out.println("Data imported successfully for VILLES.");

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
