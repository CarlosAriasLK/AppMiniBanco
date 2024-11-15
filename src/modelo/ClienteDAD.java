package modelo;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ClienteDAD  {

    // Variables de clases
    private PreparedStatement ps;
    private ResultSet rs;
    private boolean respuesta = false;

    // Conexion de la base de datos
    private final Conexion coneccion = new Conexion();
    private Connection cnn;

    // Constructor que me permite manejar las excepciones de conexion en un solo lugar
    public ClienteDAD() throws SQLException, ClassNotFoundException {
        this.cnn = coneccion.conexionDB();
    }


    // Insertar datos a la tabla cliente
    public boolean insertar( Cliente cliente ) throws SQLException {

        ps = cnn.prepareStatement("SELECT documento FROM cliente WHERE documento = ?");
        ps.setString(1, cliente.getDocumento());

        rs = ps.executeQuery();
        if( rs.next() ) {
            return false;
        } else {
            ps = cnn.prepareStatement("INSERT INTO cliente values ( ?,?,?,? )");
            ps.setString(1, cliente.getDocumento());
            ps.setString(2, cliente.getNombre());
            ps.setString(3, cliente.getApellido());
            ps.setString(4, cliente.getCorreo());

            respuesta = ps.executeUpdate() > 0;
            return respuesta;
        }
    }

    // Listar datos
    public ArrayList<Cliente> consultar() throws SQLException {

        ArrayList<Cliente> datos = new ArrayList<>();

        ps = cnn.prepareStatement("SELECT * FROM cliente");
        rs = ps.executeQuery();

        while( rs.next() ) {
            Cliente cliente = new Cliente( rs.getString("documento"), rs.getString("nombre"), rs.getString("apellido"), rs.getString("correo") );
            datos.add(cliente);
        }
        return datos;
    }


    // Consultar datos de la tabla cliente
    public ArrayList<Cliente> consultar( String documento ) throws SQLException {

        ArrayList<Cliente> datos = new ArrayList<>();

        ps = cnn.prepareStatement("SELECT * FROM cliente WHERE documento = ?");
        ps.setString(1, documento);
        rs = ps.executeQuery();

        while( rs.next() ) {
            Cliente cliente = new Cliente( rs.getString("documento"), rs.getString("nombre"), rs.getString("apellido"), rs.getString("correo") );
            datos.add(cliente);
        }
        return datos;
    }


    // Actualizar datos de la DB
    public boolean actualizar(Cliente cliente) throws SQLException {
        ps = cnn.prepareStatement("UPDATE cliente SET nombre = ?, apellido = ?, correo = ? WHERE documento = ?");
        ps.setString(1, cliente.getNombre());
        ps.setString(2, cliente.getApellido());
        ps.setString(3, cliente.getCorreo());
        ps.setString(4, cliente.getDocumento());
        respuesta = ps.executeUpdate() > 0;

        return respuesta;
    }

    // Eliminar datos en la DB
    public boolean eliminar( Cliente cliente ) throws  SQLException {

        ps = cnn.prepareStatement("DELETE FROM cliente WHERE documento = ?");
        ps.setString(1, cliente.getDocumento());
        respuesta = ps.executeUpdate() > 0;

        return respuesta;
    }

}