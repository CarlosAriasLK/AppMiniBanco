package controlador;

import modelo.Cliente;
import modelo.ClienteDAD;

import javax.swing.*;
import java.sql.SQLException;
import java.util.ArrayList;

public class Controlador {

    private boolean resultado = false;
    ClienteDAD clienteDad;
    Cliente cliente;

    public Controlador() throws SQLException, ClassNotFoundException {
        clienteDad = new ClienteDAD();
    }


    // Insertar datos a la DB
    public boolean insertarDatosCliente( String documento, String nombre, String apellido, String correo ) throws SQLException {
        cliente = new Cliente(documento, nombre, apellido, correo);
        resultado = clienteDad.insertar(cliente);
        return resultado;
    }

    // Listar datos de la DB
    public ArrayList<Cliente> listarDatosCliente() throws SQLException {
        return clienteDad.consultar();
    }

    // Consultar datos de la DB
    public ArrayList<Cliente> consultarDatosCliente( String documento ) throws SQLException {
        return clienteDad.consultar( documento );
    }

    // Actualizar datos de la DB
    public boolean actualizarDatosCliente(String documento, String nombre, String apellido, String correo ) throws SQLException {
        cliente = new Cliente(documento, nombre, apellido, correo);
        resultado = clienteDad.actualizar(cliente);
        return resultado;
    }

    // Eliminar datos de la DB
    public boolean eliminarCliente( String documento ) throws SQLException {
        cliente = new Cliente(documento);
        resultado = clienteDad.eliminar(cliente);
        return resultado;
    }

}