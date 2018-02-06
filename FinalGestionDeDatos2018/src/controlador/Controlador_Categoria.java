
package controlador;

import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import modelo.Categoria;
import vista.GestionCategoria;

/**
 *
 * @author Fernando
 */
public class Controlador_Categoria {
    
    private static Categoria categoria;
    
    public static void LogicaBotones(GestionCategoria vista){
        vista.getBtn_Agregar().setEnabled(true);
        vista.getBtn_Editar().setEnabled(false);
        vista.getBtn_Eliminar().setEnabled(false);
    }
    
    public static void LogicaBotonesInvertir(GestionCategoria vista){
        vista.getBtn_Agregar().setEnabled(false);
        vista.getBtn_Editar().setEnabled(true);
        vista.getBtn_Eliminar().setEnabled(true);
    }
    
    public static void ActualizarCategoria(GestionCategoria vista) throws SQLException {
        categoria = new Categoria();
        vista.getModelo().setColumnCount(0);
        vista.getModelo().setNumRows(0);
        vista.getModelo().addColumn("Id");
        vista.getModelo().addColumn("Nombre");
        ResultSet r = categoria.listarCategoria();
        while (r.next()) {
            Object[] fila = new Object[2];
            fila[0] = r.getString("idcategoria");
            fila[1] = r.getString("nombre");
            vista.getModelo().addRow(fila);
        }
        vista.getTabla_Categoria().setModel(vista.getModelo());
        LogicaBotones(vista);
    }

    public static void Mostrar(GestionCategoria vista) {
        int fila = vista.getTabla_Categoria().getSelectedRow();
        if(fila > -1){
            LogicaBotonesInvertir(vista);
            vista.getTxt_Id().setEnabled(false);
            vista.getTxt_Id().setText(vista.getTabla_Categoria().getModel().getValueAt(fila, 0).toString());
            vista.getTxt_Nombre().setText(vista.getTabla_Categoria().getModel().getValueAt(fila, 1).toString());
        }
    }

    public static void AltasCategoria(GestionCategoria vista) {
        categoria = new Categoria();
        try {
            categoria.setNombre(vista.getTxt_Nombre().getText());
            System.out.println("AGREGANDO CATEGORIA");
            categoria.grabarCategoria();
            ActualizarCategoria(vista);
            LimpiarCampos(vista);      
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(vista, "Datos mal ingresados", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
            }
    }

    public static void EditarCategoria(GestionCategoria vista) throws SQLException {
        int fila = vista.getTabla_Categoria().getSelectedRow();
        if (fila >-1) {
            int opt = JOptionPane.showConfirmDialog(vista, "¿Esta seguro de modificarlo?", "Mensaje de Confirmación", JOptionPane.YES_NO_OPTION);
            if (opt == JOptionPane.YES_OPTION) {
                categoria = new Categoria();
                categoria.setIdCategoria(Integer.valueOf(vista.getTxt_Id().getText()));
                categoria.setNombre(vista.getTxt_Nombre().getText());
                System.out.println("MODIFICANDO CATEGORIA");
                categoria.editarCategoria();
                ActualizarCategoria(vista);
                LimpiarCampos(vista);
            }
        }else{
            JOptionPane.showMessageDialog(vista, "Debe seleccionar una Categoria", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void EliminarCategoria(GestionCategoria vista) throws SQLException {
        int fila = vista.getTabla_Categoria().getSelectedRow();
        if (fila > -1) {
            int opt = JOptionPane.showConfirmDialog(vista, "Esta seguro de Borrar ?", "Mensaje de Confirmación", JOptionPane.YES_NO_OPTION);
            if (opt == JOptionPane.YES_OPTION) {
                categoria = new Categoria();
                categoria.setIdCategoria(Integer.parseInt(vista.getTabla_Categoria().getModel().getValueAt(fila, 0).toString()));
                boolean bandera = categoria.tieneProductos();
                if (bandera == true) {
                    JOptionPane.showMessageDialog(vista, "NO SE PUEDE ELIMINAR TIENE PRODUCTOS ASOCIADOS", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
                }else{
                    categoria.eliminarCategoria();
                    ActualizarCategoria(vista);
                    LimpiarCampos(vista);
                }
            }
        } else {
            JOptionPane.showMessageDialog(vista, "Debe seleccionar una Categoria", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public static void LimpiarVentana(GestionCategoria vista){
        LimpiarCampos(vista);
        LogicaBotones(vista);
    }
    
    public static void LimpiarCampos(GestionCategoria vista){
        vista.getTxt_Id().setText("");
        vista.getTxt_Nombre().setText("");
    }
}
