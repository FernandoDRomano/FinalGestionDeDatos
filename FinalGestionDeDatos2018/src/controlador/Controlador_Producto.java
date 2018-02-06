
package controlador;

import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import modelo.Categoria;
import modelo.Producto;
import vista.GestionProducto;

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
    
    public static void ActualizarProducto(GestionProducto vista) throws SQLException {
        producto = new Producto();
        vista.getModelo().setColumnCount(0);
        vista.getModelo().setNumRows(0);
        vista.getModelo().addColumn("Id");
        vista.getModelo().addColumn("Descripción");
        vista.getModelo().addColumn("Stock");
        vista.getModelo().addColumn("Precio Compra");
        vista.getModelo().addColumn("Precio de Venta");
        vista.getModelo().addColumn("Categoria");
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
        vista.getModeloCombo().addElement("Seleccione una Opcion");
        while(r.next()){
            categoria = new Categoria();
            categoria.setIdCategoria(Integer.parseInt(r.getString("idcategoria")));
            categoria.setNombre(r.getString("nombre"));
            vista.getModeloCombo().addElement(categoria);
        }
        vista.getCombo_Categoria().setModel(vista.getModeloCombo());
    }
    
    
    public static void AltasProducto(GestionProducto vista) {
        producto = new Producto();
        categoria = new Categoria();
        try {
            //Capturo el Objeto Categoria del modelo del Combobox y lo seteo al atributo producto
            categoria = (Categoria) vista.getCombo_Categoria().getModel().getSelectedItem();
            producto.setDescripcion(vista.getTxt_Descripcion().getText());
            producto.setStock(Integer.valueOf(vista.getTxt_Stock().getText()));
            producto.setPrecioCompra(Double.parseDouble(vista.getTxt_PrecioCompra().getText()));
            producto.setPrecioVenta(Double.parseDouble(vista.getTxt_PrecioVenta().getText()));
            producto.setCategoria(categoria);
            System.out.println("AGREGANDO PRODUCTO");
            producto.grabarProducto();
            ActualizarProducto(vista);
            LimpiarCampos(vista);      
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(vista, "Datos mal ingresados", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
            }
    }

    
    public static void EditarProducto(GestionProducto vista) throws SQLException {
        int fila = vista.getTabla_Producto().getSelectedRow();
        if (fila >-1) {
            //RESOLVER EL PROBLEMA DEL COMBOBOX, SALTA ERROR CUANDO QUIERO EDITAR Y NO LO SELECCIONO
            int opt = JOptionPane.showConfirmDialog(vista, "¿Esta seguro de modificarlo?", "Mensaje de Confirmación", JOptionPane.YES_NO_OPTION);
            if (opt == JOptionPane.YES_OPTION) {
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
                ActualizarProducto(vista);
                LimpiarCampos(vista);
            }
        }else{
            JOptionPane.showMessageDialog(vista, "Debe seleccionar un Producto", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    
    public static void EliminarProducto(GestionProducto vista) throws SQLException {
        int fila = vista.getTabla_Producto().getSelectedRow();
        if (fila > -1) {
            int opt = JOptionPane.showConfirmDialog(vista, "Esta seguro de Borrar ?", "Mensaje de Confirmación", JOptionPane.YES_NO_OPTION);
            if (opt == JOptionPane.YES_OPTION) {
                producto = new Producto();
                producto.setIdProducto(Integer.parseInt(vista.getTabla_Producto().getModel().getValueAt(fila, 0).toString()));
                boolean bandera = producto.tieneCompraVenta();
                if (bandera == true) {
                    JOptionPane.showMessageDialog(vista, "NO SE PUEDE ELIMINAR EL PRODUCTO TIENE COMPRAS Y VENTAS ASOCIADAS", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
                }else{
                    producto.eliminarProducto();
                    ActualizarProducto(vista);
                    LimpiarCampos(vista);
                }
            }
        } else {
            JOptionPane.showMessageDialog(vista, "Debe seleccionar un Producto", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public static void LimpiarVentana(GestionProducto vista){
        LimpiarCampos(vista);
        LogicaBotones(vista);
    }
    
    public static void LimpiarCampos(GestionProducto vista){
        vista.getTxt_Id().setText("");
        vista.getTxt_Descripcion().setText("");
        vista.getTxt_PrecioCompra().setText("");
        vista.getTxt_PrecioVenta().setText("");
        vista.getTxt_Stock().setText("");
        vista.getTxt_Stock().setEnabled(true);
        vista.getCombo_Categoria().getModel().setSelectedItem("Seleccione una Opcion");
    }
    
}
