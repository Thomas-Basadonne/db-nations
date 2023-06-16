package org.lessons.java.nations;

import java.sql.*;

public class Main {
    public static void main(String[] args) {
        //    parametri connessione
        String url = "jdbc:mysql://localhost:8889/db_nations";
        String user = "root";
        String password = "root";

        
        String query = """
                SELECT c.name AS country_name, c.country_id, r.name AS region_name, cn.name AS continent_name
                FROM countries c
                JOIN regions r ON c.region_id = r.region_id
                JOIN continents cn ON r.continent_id = cn.continent_id
                ORDER BY c.name;
                                      
                """;
        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            try (PreparedStatement ps = conn.prepareStatement(query)) {
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        String countryName = rs.getString("country_name");
                        String countryId = rs.getString("country_id");
                        String regionName = rs.getString("region_name");
                        String continentName = rs.getString("continent_name");
                        System.out.println("Nome Nazione: " + countryName);
                        System.out.println("ID Nazione: " + countryId);
                        System.out.println("Nome Regione: " + regionName);
                        System.out.println("Nome Continente: " + continentName);
                        System.out.println("-----------------------");
                    }
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
