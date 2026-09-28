package com.wendyecommerce.biuwendyecommerce.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDB {
    // ⚠️ REVISIÓN: Cambia "tu_base_de_datos", "tu_usuario" y "tu_password" por tus datos reales de SQL Server
    private static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=bsEcomerce01;encrypt=true;trustServerCertificate=true;";
    private static final String USER = "sa";
    private static final String PASSWORD = "Jabroca23";

    public static Connection obtenerConexion() throws SQLException {
        try {
            // Carga explícita del driver de Microsoft SQL Server
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            throw new SQLException("No se encontró el driver de SQL Server: " + e.getMessage());
        }
    }
}
