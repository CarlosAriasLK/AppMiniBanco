package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    Connection conn;

    public Connection conexionDB() throws ClassNotFoundException, SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cooperativa", "root", "123456");
            System.out.println("Conectada la base de datos");
        } catch (Exception e) {
            System.out.println("Error al conectar la base de datos " + e);
            e.printStackTrace();
        }
        return conn;
    }
}