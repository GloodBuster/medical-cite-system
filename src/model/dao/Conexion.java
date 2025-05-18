package model.dao;

import database.ConfigLoader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Conexion {
    private static final String URL = "jdbc:postgresql://localhost:5432/cite_system_db";
    private static String USER;
    private static String PASSWORD;
    private static Connection conectar = null;

    public static Connection conectar() {
        ConfigLoader config = new ConfigLoader();
        USER = config.getUser();
        PASSWORD = config.getPassword();
        try {
            Class.forName("org.postgresql.Driver");
            conectar = DriverManager.getConnection(URL, USER, PASSWORD);

        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return conectar;
    }

    public static void cerrarConexion() {
        try {
            if (conectar != null && !conectar.isClosed()) {
                conectar.close();
                System.out.println("Conexión cerrada exitosamente");
            }
        } catch (SQLException e) {
            System.out.println("Error al cerrar la conexión: " + e.getMessage());
        }
    }
}
