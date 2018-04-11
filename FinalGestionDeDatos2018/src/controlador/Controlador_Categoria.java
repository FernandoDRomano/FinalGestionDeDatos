
package controlador;

import java.awt.Frame;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import modelo.Categoria;
import modelo.Conexion;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
import vista.GestionCategoria;
import vista.Principal;

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
    
    public static boolean ValidarCampos(GestionCategoria vista){
        boolean bandera = true;
        //VALIDO QUE LOS CAMPOS NO SEAN VACIOS
        String nombre = vista.getTxt_Nombre().getText();
        nombre = nombre.replaceAll(" ", ""); //REEMPLAZO LOS ESPACIO VACIO
        
        if (nombre.length() == 0) {
            JOptionPane.showMessageDialog(vista, "ERROR: DEBE COMPLETAR EL NOMBRE DE LA CATEGORÍA", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
            bandera = false;
        }
            
        
        return bandera;
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

    public static void AltasCategoria(GestionCategoria vista) throws SQLException {
        if (ValidarCampos(vista)) {
            categoria = new Categoria();
            categoria.setNombre(vista.getTxt_Nombre().getText());
            System.out.println("AGREGANDO CATEGORIA");
            categoria.grabarCategoria();
            JOptionPane.showMessageDialog(vista, "CATEGORÍA GRABADA CON ÉXITO", "Mensaje de Información", JOptionPane.INFORMATION_MESSAGE);
            ActualizarCategoria(vista);
            LimpiarCampos(vista);  
        }
            
    }

    public static void EditarCategoria(GestionCategoria vista) throws SQLException {
        int fila = vista.getTabla_Categoria().getSelectedRow();
        if (fila >-1) {
            int opt = JOptionPane.showConfirmDialog(vista, "¿ESTÁ USTED SEGURO DE MODIFICAR ÉSTA CATEGORÍA?", "Mensaje de Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
            if (opt == JOptionPane.YES_OPTION) {
                if (ValidarCampos(vista)) {
                    categoria = new Categoria();
                    categoria.setIdCategoria(Integer.valueOf(vista.getTxt_Id().getText()));
                    categoria.setNombre(vista.getTxt_Nombre().getText());
                    System.out.println("MODIFICANDO CATEGORIA");
                    categoria.editarCategoria();
                    JOptionPane.showMessageDialog(vista, "CATEGORÍA MODIFICADA CON ÉXITO", "Mensaje de Información", JOptionPane.INFORMATION_MESSAGE);
                    ActualizarCategoria(vista);
                    LimpiarCampos(vista);
                }
                
            }
        }else{
            JOptionPane.showMessageDialog(vista, "ERROR: DEBE SELECCIONAR UNA CATEGORÍA", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void EliminarCategoria(GestionCategoria vista) throws SQLException {
        int fila = vista.getTabla_Categoria().getSelectedRow();
        if (fila > -1) {
            int opt = JOptionPane.showConfirmDialog(vista, "¿ESTÁ USTED SEGURO DE ELIMINAR ÉSTA CATEGORÍA?", "Mensaje de Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
            if (opt == JOptionPane.YES_OPTION) {
                categoria = new Categoria();
                categoria.setIdCategoria(Integer.parseInt(vista.getTabla_Categoria().getModel().getValueAt(fila, 0).toString()));
                boolean bandera = categoria.tieneProductos();
                if (bandera == true) {
                    JOptionPane.showMessageDialog(vista, "ERROR: NO SE PUEDE ELIMINAR ÉSTA CATEGORÍA, \nTIENE PRODUCTOS ASOCIADOS", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
                }else{
                    categoria.eliminarCategoria();
                    JOptionPane.showMessageDialog(vista, "CATEGORÍA ELIMINADA CON ÉXITO", "Mensaje de Información", JOptionPane.INFORMATION_MESSAGE);
                    ActualizarCategoria(vista);
                    LimpiarCampos(vista);
                }
            }
        } else {
            JOptionPane.showMessageDialog(vista, "ERROR: DEBE SELECCIONAR UNA CATEGORÍA", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public static void LimpiarCampos(GestionCategoria vista){
        vista.getTxt_Id().setText("");
        vista.getTxt_Nombre().setText("");
        LogicaBotones(vista);
    }
    
        public static void imprimirCategorias(Principal vista) throws SQLException, ClassNotFoundException{
        int opt = JOptionPane.showConfirmDialog(vista, "¿DESEA IMPRIMIR EL REPORTE DE CATEGORÍAS?", "Mensaje de Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (opt == JOptionPane.YES_OPTION) {
            Conexion conexion = new Conexion();
        
        //Metodo para Exportar
            try {
                JOptionPane.showMessageDialog(null, "ESTO PUEDE TARDAR UNOS SEGUNDOS,\nESPERE POR FAVOR", "ESTAMOS GENERANDO EL REPORTE", JOptionPane.WARNING_MESSAGE);
                String rutaInforme = "/home/fernando/NetBeansProjects/FinalGestionDeDatos/FinalGestionDeDatos2018/src/reportes/Categorias.jasper";
                JasperPrint informe = JasperFillManager.fillReport(rutaInforme, null, conexion.getConnection());
                JasperViewer ventanavisor = new JasperViewer(informe, false);
                JOptionPane.showMessageDialog(null, "DOCUMENTO EXPORTADO EXITOSAMENTE!", "GUARDADO EXITOSO!", JOptionPane.INFORMATION_MESSAGE);
                ventanavisor.setTitle("CATEGORIAS");
                ventanavisor.setExtendedState(Frame.MAXIMIZED_BOTH);
                ventanavisor.setVisible(true);
            } catch (Exception e) {//Catch del metodo exportar
                JOptionPane.showMessageDialog(null, "ERROR AL EXPORTAR DOCUMENTO");
            }//Fin de del metodo Exportar      
        }
    }

}
