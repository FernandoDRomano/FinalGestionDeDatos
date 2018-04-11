
package controlador;

import java.awt.Frame;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import modelo.Categoria;
import modelo.Conexion;
import modelo.Producto;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
import vista.GestionProducto;
import vista.Principal;

/**
 *
 * @author Fernando
 */
public class Controlador_Producto {
    
    private static Producto producto;
    private static Categoria categoria;
    
    public static void LogicaBotones(GestionProducto vista){
        vista.getBtn_Agregar().setEnabled(true);
        vista.getBtn_Editar().setEnabled(false);
        vista.getBtn_Eliminar().setEnabled(false);
    }
    
    public static void LogicaBotonesInvertir(GestionProducto vista){
        vista.getBtn_Agregar().setEnabled(false);
        vista.getBtn_Editar().setEnabled(true);
        vista.getBtn_Eliminar().setEnabled(true);
    }
    
    public static boolean ValidarCampos(GestionProducto vista){
        boolean bandera = true;
        //VALIDO QUE LOS CAMPOS NO SEAN VACIOS
        String descripcion = vista.getTxt_Descripcion().getText();
        descripcion = descripcion.replaceAll(" ", ""); //REEMPLAZO LOS ESPACIO VACIO
        String stock = vista.getTxt_Stock().getText();
        stock = stock.replaceAll(" ", "");
        String precioCompra = vista.getTxt_PrecioCompra().getText();
        precioCompra = precioCompra.replaceAll(" ", "");
        String precioVenta = vista.getTxt_PrecioVenta().getText();
        precioVenta = precioVenta.replaceAll(" ", "");
        String categoria = vista.getCombo_Categoria().getSelectedItem().toString();
        
        
        if (descripcion.length() == 0) {
            JOptionPane.showMessageDialog(vista, "ERROR: DEBE COMPLETAR LA DESCRIPCIÓN", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
            bandera = false;
        }
        
        if (stock.length() == 0) {
            JOptionPane.showMessageDialog(vista, "ERROR: DEBE COMPLETAR EL STOCK", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
            bandera = false;
        }
        
        if (precioCompra.length() == 0) {
            JOptionPane.showMessageDialog(vista, "ERROR: DEBE COMPLETAR EL PRECIO DE COMPRA", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
            bandera = false;
        }
        
        if (precioVenta.length() == 0) {
            JOptionPane.showMessageDialog(vista, "ERROR: DEBE COMPLETAR EL PRECIO DE VENTA", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
            bandera = false;
        }
            
        if (categoria.equals("Seleccione una Opción")) {
            JOptionPane.showMessageDialog(vista, "ERROR: DEBE SELECCIONAR UNA CATEGORÍA", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
            bandera = false;
        }
        
        return bandera;
    }
    
    public static void ActualizarProducto(GestionProducto vista) throws SQLException {
        producto = new Producto();
        vista.getModelo().setColumnCount(0);
        vista.getModelo().setNumRows(0);
        vista.getModelo().addColumn("Id");
        vista.getModelo().addColumn("Descripción");
        vista.getModelo().addColumn("Stock");
        vista.getModelo().addColumn("Precio Compra");
        vista.getModelo().addColumn("Precio de Venta");
        vista.getModelo().addColumn("Categoría");
        ResultSet r = producto.listarProducto();
        while (r.next()) {
            Object[] fila = new Object[6];
            fila[0] = r.getString("idproducto");
            fila[1] = r.getString("descripcion");
            fila[2] = r.getString("stock");
            fila[3] = r.getString("precioCompra");
            fila[4] = r.getString("precioVenta");
            fila[5] = r.getString("categoria");
            vista.getModelo().addRow(fila);
        }
        vista.getTabla_Producto().setModel(vista.getModelo());
        LogicaBotones(vista);
    }

    public static void Mostrar(GestionProducto vista) throws SQLException {
        int fila = vista.getTabla_Producto().getSelectedRow();
        if(fila > -1){
            producto = new Producto();
            LogicaBotonesInvertir(vista);
            vista.getTxt_Id().setEnabled(false);
            vista.getTxt_Stock().setEnabled(false);
            //BUSCO LA CATEGORIA POR EL ID DEL PRODUCTO Y LOS CARGO EN UN OBJETO PARA PASARLO AL COMBO
            ResultSet r = producto.buscarProductoId(vista.getTabla_Producto().getModel().getValueAt(fila, 0).toString());
            while(r.next()){
                categoria = new Categoria();
                categoria.setIdCategoria(Integer.valueOf(r.getString("idcategoria")));
                categoria.setNombre(r.getString("categoria"));
            }
            //CARGO LOS JTEXTFIELDS DE LAS COLUMNAS DE LAS TABLAS AUNQUE PODRIA TRANQUILAMENTE SACARLO DEL RESULSTSET
            vista.getTxt_Id().setText(vista.getTabla_Producto().getModel().getValueAt(fila, 0).toString());
            vista.getTxt_Descripcion().setText(vista.getTabla_Producto().getModel().getValueAt(fila, 1).toString());
            vista.getTxt_Stock().setText(vista.getTabla_Producto().getModel().getValueAt(fila, 2).toString());
            vista.getTxt_PrecioCompra().setText(vista.getTabla_Producto().getModel().getValueAt(fila, 3).toString());
            vista.getTxt_PrecioVenta().setText(vista.getTabla_Producto().getModel().getValueAt(fila, 4).toString());
            vista.getCombo_Categoria().getModel().setSelectedItem(categoria);
        }
    }
    
    public static void cargarCombo(GestionProducto vista) throws SQLException{
        categoria = new Categoria();
        ResultSet r = categoria.listarCategoria();
        vista.getModeloCombo().addElement("Seleccione una Opción");
        while(r.next()){
            categoria = new Categoria();
            categoria.setIdCategoria(Integer.parseInt(r.getString("idcategoria")));
            categoria.setNombre(r.getString("nombre"));
            vista.getModeloCombo().addElement(categoria);
        }
        vista.getCombo_Categoria().setModel(vista.getModeloCombo());
    }
    
    
    public static void AltasProducto(GestionProducto vista) throws SQLException {
        if (ValidarCampos(vista)) {
            producto = new Producto();
            categoria = new Categoria();
            //Capturo el Objeto Categoria del modelo del Combobox y lo seteo al atributo producto
            categoria = (Categoria) vista.getCombo_Categoria().getModel().getSelectedItem();
            producto.setDescripcion(vista.getTxt_Descripcion().getText());
            producto.setStock(Integer.valueOf(vista.getTxt_Stock().getText()));
            producto.setPrecioCompra(Double.parseDouble(vista.getTxt_PrecioCompra().getText()));
            producto.setPrecioVenta(Double.parseDouble(vista.getTxt_PrecioVenta().getText()));
            producto.setCategoria(categoria);
            System.out.println("AGREGANDO PRODUCTO");
            producto.grabarProducto();
            JOptionPane.showMessageDialog(vista, "PRODUCTO GRABADO CON ÉXITO", "Mensaje de Información", JOptionPane.INFORMATION_MESSAGE);
            ActualizarProducto(vista);
            LimpiarCampos(vista);     
        }   
    }

    
    public static void EditarProducto(GestionProducto vista) throws SQLException {
        int fila = vista.getTabla_Producto().getSelectedRow();
        if (fila >-1) {
            //RESOLVER EL PROBLEMA DEL COMBOBOX, SALTA ERROR CUANDO QUIERO EDITAR Y NO LO SELECCIONO
            int opt = JOptionPane.showConfirmDialog(vista, "¿ESTÁ USTED SEGURO DE MODIFICAR ÉSTE PRODUCTO?", "Mensaje de Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
            if (opt == JOptionPane.YES_OPTION) {
                if (ValidarCampos(vista)) {
                    producto = new Producto();
                    categoria = new Categoria();
                    categoria = (Categoria) vista.getCombo_Categoria().getModel().getSelectedItem();
                    System.out.println("Id: " + categoria.getIdCategoria() + " Categoria: " + categoria.getNombre());
                    producto.setIdProducto(Integer.valueOf(vista.getTxt_Id().getText()));
                    producto.setDescripcion(vista.getTxt_Descripcion().getText());
                    producto.setPrecioCompra(Double.parseDouble(vista.getTxt_PrecioCompra().getText()));
                    producto.setPrecioVenta(Double.parseDouble(vista.getTxt_PrecioVenta().getText()));
                    producto.setCategoria(categoria);
                    System.out.println("MODIFICANDO PRODUCTO");
                    producto.editarProducto();
                    JOptionPane.showMessageDialog(vista, "PRODUCTO MODIFICADO CON ÉXITO", "Mensaje de Información", JOptionPane.INFORMATION_MESSAGE);
                    ActualizarProducto(vista);
                    LimpiarCampos(vista);
                }
                
            }
        }else{
            JOptionPane.showMessageDialog(vista, "ERROR: DEBE SELECCIONAR UN PRODUCTO", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    
    public static void EliminarProducto(GestionProducto vista) throws SQLException {
        int fila = vista.getTabla_Producto().getSelectedRow();
        if (fila > -1) {
            int opt = JOptionPane.showConfirmDialog(vista, "¿ESTÁ USTED SEGURO DE ELIMINAR ÉSTE PRODUCTO?", "Mensaje de Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
            if (opt == JOptionPane.YES_OPTION) {
                producto = new Producto();
                producto.setIdProducto(Integer.parseInt(vista.getTabla_Producto().getModel().getValueAt(fila, 0).toString()));
                boolean bandera = producto.tieneCompraVenta();
                if (bandera == true) {
                    JOptionPane.showMessageDialog(vista, "ERROR: NO SE PUEDE ELIMINAR ÉSTE PRODUCTO,\n TIENE COMPRAS Y VENTAS ASOCIADAS", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
                }else{
                    producto.eliminarProducto();
                    JOptionPane.showMessageDialog(vista, "PRODUCTO ELIMINADO CON ÉXITO", "Mensaje de Información", JOptionPane.INFORMATION_MESSAGE);
                    ActualizarProducto(vista);
                    LimpiarCampos(vista);
                }
            }
        } else {
            JOptionPane.showMessageDialog(vista, "ERROR: DEBE SELECCIONAR UN PRODUCTO", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    
    public static void LimpiarCampos(GestionProducto vista){
        vista.getTxt_Id().setText("");
        vista.getTxt_Descripcion().setText("");
        vista.getTxt_PrecioCompra().setText("");
        vista.getTxt_PrecioVenta().setText("");
        vista.getTxt_Stock().setText("");
        vista.getTxt_Stock().setEnabled(true);
        vista.getCombo_Categoria().getModel().setSelectedItem("Seleccione una Opción");
        LogicaBotones(vista);
    }
    
        public static void imprimirProductos(Principal vista) throws SQLException, ClassNotFoundException{
        int opt = JOptionPane.showConfirmDialog(vista, "¿DESEA IMPRIMIR EL REPORTE DE PRODUCTOS?", "Mensaje de Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (opt == JOptionPane.YES_OPTION) {
            Conexion conexion = new Conexion();
        
        //Metodo para Exportar
            try {
                JOptionPane.showMessageDialog(null, "ESTO PUEDE TARDAR UNOS SEGUNDOS,\nESPERE POR FAVOR", "ESTAMOS GENERANDO EL REPORTE", JOptionPane.WARNING_MESSAGE);
                String rutaInforme = "/home/fernando/NetBeansProjects/FinalGestionDeDatos/FinalGestionDeDatos2018/src/reportes/Productos.jasper";
                JasperPrint informe = JasperFillManager.fillReport(rutaInforme, null, conexion.getConnection());
                JasperViewer ventanavisor = new JasperViewer(informe, false);
                JOptionPane.showMessageDialog(null, "DOCUMENTO EXPORTADO EXITOSAMENTE!", "GUARDADO EXITOSO!", JOptionPane.INFORMATION_MESSAGE);
                ventanavisor.setTitle("PRODUCTOS");
                ventanavisor.setExtendedState(Frame.MAXIMIZED_BOTH);
                ventanavisor.setVisible(true);
            } catch (Exception e) {//Catch del metodo exportar
                JOptionPane.showMessageDialog(null, "ERROR AL EXPORTAR DOCUMENTO");
            }//Fin de del metodo Exportar      
        }
    }

    
}
