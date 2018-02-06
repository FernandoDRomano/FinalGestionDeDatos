package controlador;

import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import modelo.Cargo;
import vista.GestionCargo;

/**
 *
 * @author Fernando
 */
public class Controlador_Cargo {
    
    private static Cargo cargo;
    
    public static void LogicaBotones(GestionCargo vista){
        vista.getBtn_Agregar().setEnabled(true);
        vista.getBtn_Editar().setEnabled(false);
        vista.getBtn_Eliminar().setEnabled(false);
    }
    
    public static void LogicaBotonesInvertir(GestionCargo vista){
        vista.getBtn_Agregar().setEnabled(false);
        vista.getBtn_Editar().setEnabled(true);
        vista.getBtn_Eliminar().setEnabled(true);
    }
    
    public static void ActualizarCargo(GestionCargo vista) throws SQLException {
        cargo = new Cargo();
        vista.getModelo().setColumnCount(0);
        vista.getModelo().setNumRows(0);
        vista.getModelo().addColumn("Id");
        vista.getModelo().addColumn("Nombre");
        vista.getModelo().addColumn("Basico");
        ResultSet r = cargo.listarCargo();
        while (r.next()) {
            Object[] fila = new Object[3];
            fila[0] = r.getString("idcargo");
            fila[1] = r.getString("cargo");
            fila[2] = r.getString("basico");
            vista.getModelo().addRow(fila);
        }
        vista.getTabla_Cargo().setModel(vista.getModelo());
        LogicaBotones(vista);
    }

    public static void Mostrar(GestionCargo vista) {
        int fila = vista.getTabla_Cargo().getSelectedRow();
        if(fila > -1){
            LogicaBotonesInvertir(vista);
            vista.getTxt_Id().setEnabled(false);
            vista.getTxt_Id().setText(vista.getTabla_Cargo().getModel().getValueAt(fila, 0).toString());
            vista.getTxt_Nombre().setText(vista.getTabla_Cargo().getModel().getValueAt(fila, 1).toString());
            vista.getTxt_Basico().setText(vista.getTabla_Cargo().getModel().getValueAt(fila, 2).toString());
        }
    }

    public static void AltasCargo(GestionCargo vista) {
        cargo = new Cargo();
        try {
            cargo.setCargo(vista.getTxt_Nombre().getText());
            cargo.setBasico(Double.parseDouble(vista.getTxt_Basico().getText()));
            System.out.println("AGREGANDO CARGO");
            cargo.grabarCargo();
            ActualizarCargo(vista);
            LimpiarCampos(vista);      
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(vista, "Datos mal ingresados", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
            }
    }

    public static void EditarCargo(GestionCargo vista) throws SQLException {
        int fila = vista.getTabla_Cargo().getSelectedRow();
        if (fila >-1) {
            int opt = JOptionPane.showConfirmDialog(vista, "¿Esta seguro de modificarlo?", "Mensaje de Confirmación", JOptionPane.YES_NO_OPTION);
            if (opt == JOptionPane.YES_OPTION) {
                cargo = new Cargo();
                cargo.setIdCargo(Integer.valueOf(vista.getTxt_Id().getText()));
                cargo.setCargo(vista.getTxt_Nombre().getText());
                cargo.setBasico(Double.parseDouble(vista.getTxt_Basico().getText()));
                System.out.println("MODIFICANDO CARGO");
                cargo.editarCargo();
                ActualizarCargo(vista);
                LimpiarCampos(vista);
            }
        }else{
            JOptionPane.showMessageDialog(vista, "Debe seleccionar un Cargo", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void EliminarCargo(GestionCargo vista) throws SQLException {
        int fila = vista.getTabla_Cargo().getSelectedRow();
        if (fila > -1) {
            int opt = JOptionPane.showConfirmDialog(vista, "Esta seguro de Borrar ?", "Mensaje de Confirmación", JOptionPane.YES_NO_OPTION);
            if (opt == JOptionPane.YES_OPTION) {
                cargo = new Cargo();
                cargo.setIdCargo(Integer.parseInt(vista.getTabla_Cargo().getModel().getValueAt(fila, 0).toString()));
                boolean bandera = cargo.tieneEmpleados();
                if (bandera == true) {
                    JOptionPane.showMessageDialog(vista, "NO SE PUEDE ELIMINAR TIENE EMPLEADOS ASOCIADOS", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
                }else{
                    cargo.eliminarCargo();
                    ActualizarCargo(vista);
                    LimpiarCampos(vista);
                }
            }
        } else {
            JOptionPane.showMessageDialog(vista, "Debe seleccionar un Cargo", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public static void LimpiarVentana(GestionCargo vista){
        LimpiarCampos(vista);
        LogicaBotones(vista);
    }
    
    public static void LimpiarCampos(GestionCargo vista){
        vista.getTxt_Id().setText("");
        vista.getTxt_Nombre().setText("");
        vista.getTxt_Basico().setText("");
    }
}
