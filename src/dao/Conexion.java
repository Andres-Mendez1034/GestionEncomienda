package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    private static final String URL = System.getenv("DB_URL") != null
            ? System.getenv("DB_URL")  // Obtiene la URL de la base de datos desde variables de entorno
            : "jdbc:mysql://localhost:3306/encomiendas?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC"; // Valor por defecto

    private static final String USER = System.getenv("DB_USER") != null ? System.getenv("DB_USER") : "root"; // Usuario desde variable de entorno o valor por defecto
    private static final String PASS = System.getenv("DB_PASS") != null ? System.getenv("DB_PASS") : ""; // Contraseña desde variable de entorno o vacío por defecto

    private Conexion() { } // Constructor privado para evitar instanciación

    // Método estático para obtener una conexión a la base de datos
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);  // Establece la conexión usando los parámetros definidos
    }
}
