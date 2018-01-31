package controlador;

import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import modelo.Cliente;
import vista.GestionCliente;

/**
 *
 * @author Fernando
 */
public class Controlador_Cliente {
    
    private static Cliente cliente;
    
    public static void LogicaBotones(GestionCliente vista){
        vista.getBtn_Agregar().setEnabled(true);
        vista.getBtn_Editar().setEnabled(false);
        vista.getBtn_Eliminar().setEnabled(false);
    }
    
    public static void LogicaBotonesInvertir(GestionCliente vista){
        vista.getBtn_Agregar().setEnabled(false);
        vista.getBtn_Editar().setEnabled(true);
        vista.getBtn_Eliminar().setEnabled(true);
    }
    
    public static void ActualizarCliente(GestionCliente vista) throws SQLException {
        cliente = new Cliente();
        vista.getModelo().setColumnCount(0);
        vista.getModelo().setNumRows(0);
        vista.getModelo().addColumn("Id");
        vista.getModelo().addColumn("Apellido");
        vista.getModelo().addColumn("Nombre");
        vista.getModelo().addColumn("Telefono");
        ResultSet r = cliente.listarCliente();
        while (r.next()) {
            Object[] fila = new Object[5];
            fila[0] = r.getString("idCliente");
            fila[1] = r.getString("apellido");
            fila[2] = r.getString("nombre");
            fila[3] = r.getString("telefono");
            vista.getModelo().addRow(fila);
        }
        vista.getTabla_Cliente().setModel(vista.getModelo());
        LogicaBotones(vista);
    }

    public static void Mostrar(GestionCliente vista) {
        int fila = vista.getTabla_Cliente().getSelectedRow();
        if(fila > -1){
            LogicaBotonesInvertir(vista);
            vista.getTxt_Id().setEnabled(false);
            vista.getTxt_Id().setText(vista.getTabla_Cliente().getModel().getValueAt(fila, 0).toString());
            vista.getTxt_Nombre().setText(vista.getTabla_Cliente().getModel().getValueAt(fila, 2).toString());
            vista.getTxt_Apellido().setText(vista.getTabla_Cliente().getModel().getValueAt(fila, 1).toString());
            vista.getTxt_Telefono().setText(vista.getTabla_Cliente().getModel().getValueAt(fila, 3).toString());
        }
    }

    public static void AltasCliente(GestionCliente vista) {
        cliente = new Cliente();
        try {
            cliente.setNombre(vista.getTxt_Nombre().getText());
            cliente.setApellido(vista.getTxt_Apellido().getText());
            cliente.setTelefono(vista.getTxt_Telefono().getText());
            System.out.println("AGREGANDO CLIENTE");
            cliente.grabarCliente();
            ActualizarCliente(vista);
            LimpiarCampos(vista);      
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(vista, "Datos mal ingresados", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
            }
    }

    public static void EditarCliente(GestionCliente vista) throws SQLException {
        int fila = vista.getTabla_Cliente().getSelectedRow();
        if (fila >-1) {
            int opt = JOptionPane.showConfirmDialog(vista, "¿Esta seguro de modificarlo?", "Mensaje de Confirmación", JOptionPane.YES_NO_OPTION);
            if (opt == JOptionPane.YES_OPTION) {
                cliente = new Cliente();
                cliente.setIdCliente(Integer.valueOf(vista.getTxt_Id().getText()));
                cliente.setNombre(vista.getTxt_Nombre().getText());
                cliente.setApellido(vista.getTxt_Apellido().getText());
                cliente.setTelefono(vista.getTxt_Telefono().getText());
                System.out.println("MODIFICANDO CLIENTE");
                cliente.editarCliente();
                ActualizarCliente(vista);
                LimpiarCampos(vista);
            }
        }else{
            JOptionPane.showMessageDialog(vista, "Debe seleccionar un Cliente", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void EliminarCliente(GestionCliente vista) throws SQLException {
        int fila = vista.getTabla_Cliente().getSelectedRow();
        if (fila > -1) {
            int opt = JOptionPane.showConfirmDialog(vista, "Esta seguro de Borrar ?", "Mensaje de Confirmación", JOptionPane.YES_NO_OPTION);
            if (opt == JOptionPane.YES_OPTION) {
                cliente = new Cliente();
                cliente.setIdCliente(Integer.parseInt(vista.getTabla_Cliente().getModel().getValueAt(fila, 0).toString()));
                boolean bandera = cliente.tieneVenta();
                if (bandera == true) {
                    JOptionPane.showMessageDialog(vista, "NO SE PUEDE ELIMINAR TIENE FACTURAS ASOCIADAS", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
                }else {
                    cliente.eliminarCliente();
                    ActualizarCliente(vista);
                    LimpiarCampos(vista);
                }
            }
        } else {
            JOptionPane.showMessageDialog(vista, "Debe seleccionar un Cliente", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public static void LimpiarVentana(GestionCliente vista){
        LimpiarCampos(vista);
        LogicaBotones(vista);
    }
    
    public static void LimpiarCampos(GestionCliente vista){
        vista.getTxt_Apellido().setText("");
        vista.getTxt_Id().setText("");
        vista.getTxt_Nombre().setText("");
        vista.getTxt_Telefono().setText("");
    }
    
}
