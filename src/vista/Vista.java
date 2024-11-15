package vista;

import controlador.Controlador;
import modelo.Cliente;

import javax.swing.*;
import java.sql.SQLException;
import java.util.ArrayList;

public class Vista {

    private String documento;
    private String nombre;
    private String apellido;
    private String correo;
    private boolean resultado;
    private String mensaje;

    Controlador controlador;

    public Vista() throws SQLException, ClassNotFoundException {
        controlador = new Controlador();
    }

    public void vista() throws SQLException {

        boolean salir = false;

        while (!salir) {

            int opcion = Integer.parseInt(JOptionPane.showInputDialog(
                    "Ingrese una opción: \n" +
                            "1. Insertar datos: \n" +
                            "2. Listar Datos: \n" +
                            "3. Buscar datos por el documento: \n" +
                            "4. Actualizar datos: \n" +
                            "5. Eliminar datos: \n" +
                            "6. Salir: \n"
            ));

            switch (opcion) {

                case 1: {
                    // Insertar datos
                    documento = JOptionPane.showInputDialog("Digite el documento: ");
                    nombre = JOptionPane.showInputDialog("Digite el nombre: ");
                    apellido = JOptionPane.showInputDialog("Digite el apellido: ");
                    correo = JOptionPane.showInputDialog("Digite el correo: ");

                    resultado = controlador.insertarDatosCliente(documento, nombre, apellido, correo);
                    if (resultado) {
                        JOptionPane.showMessageDialog(null, "Registro exitoso");
                    } else {
                        JOptionPane.showMessageDialog(null, "Error al registrar");
                    }
                    break;
                }

                case 2: {
                    // Listar datos de la DB
                    ArrayList<Cliente> datos = new ArrayList<>();
                    datos = controlador.listarDatosCliente();
                    for (Cliente c : datos) {
                        //System.out.println("Datos registrados: \n" +  c.getDocumento() + ": " + c.getNombre() + " " + c.getApellido() + " " + c.getCorreo());
                        System.out.println(c.toString());
                    }
                    break;
                }

                case 3: {
                    // Consultando/listando datos de la DB
                    ArrayList<Cliente> datos = new ArrayList<>();
                    documento = JOptionPane.showInputDialog("Digite el documento a buscar: ");
                    datos = controlador.consultarDatosCliente( documento );
                    for (Cliente c : datos) {
                        //System.out.println("Datos registrados: \n" +  c.getDocumento() + ": " + c.getNombre() + " " + c.getApellido() + " " + c.getCorreo());
                        System.out.println(c.toString());
                    }
                    break;
                }

                case 4: {
                    // Actualizando datos de la DB
                    documento = JOptionPane.showInputDialog("Digite el documento del cliente a actualizar: ");
                    nombre = JOptionPane.showInputDialog("Digite el nuevo nombre: ");
                    apellido = JOptionPane.showInputDialog("Digite el nuevo apellido: ");
                    correo = JOptionPane.showInputDialog("Digite el nuevo correo: ");

                    resultado = controlador.actualizarDatosCliente(documento, nombre, apellido, correo);
                    if (resultado) {
                        JOptionPane.showMessageDialog(null, "Actualización exitoso");
                    } else {
                        JOptionPane.showMessageDialog(null, "Error al actualizar");
                    }
                    break;
                }

                case 5: {
                    // Eliminar datos de la DB
                    documento = JOptionPane.showInputDialog("Digite el documento del cliente a eliminar: ");

                    resultado = controlador.eliminarCliente(documento);
                    if (resultado) {
                        JOptionPane.showMessageDialog(null, "Eliminado exitosamente");
                    } else {
                        JOptionPane.showMessageDialog(null, "Error al eliminar");
                    }
                    break;
                }

                case 6:
                    salir = true;
                    System.out.println("Saliendo del programa...");
                    break;

                default:
                    JOptionPane.showMessageDialog(null, "Opción no válida");
                    break;
            }

        }

    }

}