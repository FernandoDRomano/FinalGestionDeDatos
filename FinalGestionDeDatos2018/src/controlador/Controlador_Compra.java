
package controlador;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import modelo.Conexion;
import modelo.Empleado;
import modelo.LineaCompra;
import modelo.Compra;
import modelo.Producto;
import modelo.Proveedor;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
import vista.BuscarProductoCompra;
import vista.BuscarProveedor;
import vista.ListarCompra;
import vista.ListarCompraEmpleado;
import vista.NuevaCompra;


/**
 *
 * @author fernando
 */
public class Controlador_Compra {
  
    private static Empleado empleado;
    private static Proveedor proveedor;
    private static Producto producto;
    private static Compra compra;
    private static LineaCompra linea;
    
    //////////////// METODOS PARA CARGAR EN LOS FORM DE BUSQUEDA EL PROVEEDOR Y EL PRODUCTO /////////////////////////
    public static void CargarTodosLosProveedores(BuscarProveedor vista) throws SQLException {
        proveedor = new Proveedor();
        vista.getModelo().setColumnCount(0);
        vista.getModelo().setNumRows(0);
        vista.getModelo().addColumn("Id");
        vista.getModelo().addColumn("Nombre");
        vista.getModelo().addColumn("Razón Social");
        vista.getModelo().addColumn("CUIT");
        ResultSet r = proveedor.listarProveedor();
        while (r.next()) {
            Object[] fila = new Object[4];
            fila[0] = r.getString("idproveedor");
            fila[1] = r.getString("nombre");
            fila[2] = r.getString("razonSocial");
            fila[3] = r.getString("cuit");
            vista.getModelo().addRow(fila);
        }

        vista.getTabla_Proveedor().setModel(vista.getModelo());
        
    }
    
    public static void CargarTodosLosProveedores(BuscarProveedor vista, String cadena) throws SQLException {
        proveedor = new Proveedor();
        vista.getModelo().setColumnCount(0);
        vista.getModelo().setNumRows(0);
        vista.getModelo().addColumn("Id");
        vista.getModelo().addColumn("Nombre");
        vista.getModelo().addColumn("Razón Social");
        vista.getModelo().addColumn("CUIT");
        ResultSet r = proveedor.buscarProveedor(cadena);
        while (r.next()) {
            Object[] fila = new Object[4];
            fila[0] = r.getString("idproveedor");
            fila[1] = r.getString("nombre");
            fila[2] = r.getString("razonSocial");
            fila[3] = r.getString("cuit");
            vista.getModelo().addRow(fila);
        }

        vista.getTabla_Proveedor().setModel(vista.getModelo());
        
    }
    
    public static void CargarTodosLosProductos(BuscarProductoCompra vista) throws SQLException {
        producto = new Producto();
        vista.getModelo().setColumnCount(0);
        vista.getModelo().setNumRows(0);
        vista.getModelo().addColumn("Id");
        vista.getModelo().addColumn("Descripción");
        vista.getModelo().addColumn("Precio");
        vista.getModelo().addColumn("Stock Actual");
        ResultSet r = producto.listarProducto();
        while (r.next()) {
            Object[] fila = new Object[4];
            fila[0] = r.getString("idProducto");
            fila[1] = r.getString("descripcion");
            fila[2] = r.getString("precioCompra");
            fila[3] = r.getString("stock");
            vista.getModelo().addRow(fila);
        }
        vista.getTabla_Producto().setModel(vista.getModelo());
        
        int[] anchos = {30, 200, 100, 80};
        for(int i = 0; i < vista.getTabla_Producto().getColumnCount(); i++) {
            vista.getTabla_Producto().getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
        }
        
    }
    
    public static void CargarTodosLosProductos(BuscarProductoCompra vista, String cadena) throws SQLException {
        producto = new Producto();
        vista.getModelo().setColumnCount(0);
        vista.getModelo().setNumRows(0);
        vista.getModelo().addColumn("Id");
        vista.getModelo().addColumn("Descripción");
        vista.getModelo().addColumn("Precio");
        vista.getModelo().addColumn("Stock Actual");
        ResultSet r = producto.buscarProducto(cadena);
        while (r.next()) {
            Object[] fila = new Object[4];
            fila[0] = r.getString("idProducto");
            fila[1] = r.getString("descripcion");
            fila[2] = r.getString("precioCompra");
            fila[3] = r.getString("stock");
            vista.getModelo().addRow(fila);
        }
        vista.getTabla_Producto().setModel(vista.getModelo());
        
        int[] anchos = {30, 200, 100, 80};
        for(int i = 0; i < vista.getTabla_Producto().getColumnCount(); i++) {
            vista.getTabla_Producto().getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
        }
        
    }
    
    public static void ventanaBuscarProveedor(NuevaCompra vista) throws SQLException{
        BuscarProveedor ventana = new BuscarProveedor(null, true,vista);
        ventana.setVisible(true);
    }
    
    public static void ventanaBuscarProducto(NuevaCompra vista) throws SQLException{
        BuscarProductoCompra ventana = new BuscarProductoCompra(null, true, vista);
        ventana.setVisible(true);
    }
    
    //======================= METODO PARA CARGAR EL PROVEEDOR ============================
    public static void agregarProveedorAlPedido(BuscarProveedor vista, NuevaCompra compra){
        int fila = vista.getTabla_Proveedor().getSelectedRow();
        if (fila > -1) {
            //saco los valores
            int id = Integer.parseInt(vista.getTabla_Proveedor().getModel().getValueAt(fila, 0).toString());
            String nombre = vista.getTabla_Proveedor().getModel().getValueAt(fila, 1).toString();
            //Creo el cliente
            proveedor = new Proveedor();
            proveedor.setIdProveedor(id);
            proveedor.setNombre(nombre);
            //Los cargo en la vista
            compra.getTxt_IdProveedor().setText(""+proveedor.getIdProveedor());
            compra.getTxt_NombreProveedor().setText(proveedor.getNombre());
            vista.setVisible(false);
        }else{
            JOptionPane.showMessageDialog(vista, "ERROR: DEBE SELECCIONAR UN PROVEEDOR", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        
    }
    
    //======================= MANEJO DE LAS FILAS, CALCULO DEL TOTAL Y DEL STOCK =================================
    public static void agregarProductoAlPedido(BuscarProductoCompra vista, NuevaCompra compra){
        int fila = vista.getTabla_Producto().getSelectedRow();
        if (fila > -1) {
            if (vista.getTxt_Cantidad().getText().equals("")) {
                JOptionPane.showMessageDialog(vista, "ERROR: DEBE INGRESAR UNA CANTIDAD", "ERROR", JOptionPane.ERROR_MESSAGE);
            }else{
                String id = "", descripcion = "";
                double precio = 0, subTotal = 0;
                int cantidad = 0;
                int stock = 0;
                //Capturo los datos de la tabla_Servicio
                id = vista.getTabla_Producto().getModel().getValueAt(fila, 0).toString();
                descripcion = vista.getTabla_Producto().getModel().getValueAt(fila, 1).toString();
                precio = Double.parseDouble(vista.getTabla_Producto().getModel().getValueAt(fila, 2).toString());
                cantidad = Integer.parseInt(vista.getTxt_Cantidad().getText());
                //Realizo el calculo del sub Total
                subTotal = precio * cantidad;
                //Los paso a un vector
                String filaElemento[] = {id, descripcion, String.valueOf(precio), String.valueOf(cantidad), String.valueOf(subTotal)};
                System.out.println("ID: " + filaElemento[0]+", Descripcion: " + filaElemento[1] + ", Precio: " + filaElemento[2] + ", Cantidad: " + filaElemento[3] + ", SubTotal: " + filaElemento[4]);
                //Le paso al modelo de la factura el vector
                compra.getModelo().addRow(filaElemento);
                compra.getTabla_Descripcion().setModel(compra.getModelo());
                calcularTotal(compra);
                //Seteo el valor del txt_Cantidad = 1
                vista.getTxt_Cantidad().setText("1");
                
                //Asigno valores a las columnas
                int[] anchos = {40, 260, 70, 70, 70};
                for(int i = 0; i < compra.getTabla_Descripcion().getColumnCount(); i++) {
                    compra.getTabla_Descripcion().getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
                }
            }
            
        }else{
            JOptionPane.showMessageDialog(vista, "ERROR: DEBE SELECCIONAR UN PRODUCTO", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public static void quitarProducto(NuevaCompra vista){
        int fila = vista.getTabla_Descripcion().getSelectedRow();
        System.out.println("Fila: " + fila);
        if (fila > -1) {
            int resp = JOptionPane.showConfirmDialog(vista, "¿ESTÁ USTED SEGURO DE QUITAR ÉSTE PRODUCTO?","Advertencia",JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
            if (resp == JOptionPane.YES_OPTION) {
                vista.getModelo().removeRow(fila);
                calcularTotal(vista);
                //Asigno valores a las columnas
                int[] anchos = {40, 260, 70, 70, 70};
                for(int i = 0; i < vista.getTabla_Descripcion().getColumnCount(); i++) {
                    vista.getTabla_Descripcion().getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
                }
            }
        }else{
            JOptionPane.showMessageDialog(vista, "ERROR: DEBE SELECCIONAR UN PRODUCTO", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public static void calcularTotal(NuevaCompra vista){
        double total = 0;
        for (int i = 0; i < vista.getModelo().getRowCount() ; i++) {
            total += Double.parseDouble(vista.getTabla_Descripcion().getModel().getValueAt(i, 4).toString());
        }
        
        System.out.println("Total: " + total);
        vista.getTxt_Total().setText(String.valueOf(total));
    }
    
    public static boolean validarCampos(NuevaCompra vista){
        boolean bandera = true;
        String idproveedor = vista.getTxt_IdProveedor().getText();
        idproveedor = idproveedor.replaceAll(" ", "");
        
        String total = vista.getTxt_Total().getText();
        total = total.replaceAll(" ", "");
        
        if (idproveedor.length() == 0) {
            JOptionPane.showMessageDialog(vista, "ERROR: DEBE SELECCIONAR EL PROVEEDOR", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
            bandera = false;
        }
        
        if (total.length() == 0 || total.equals("0.0")) {
            JOptionPane.showMessageDialog(vista, "ERROR: DEBE INGRESAR PRODUCTOS A LA COMPRA", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
            bandera = false;
        }
        
        return bandera;
    }
    
    
    //============================== METODOS ABM ====================================
    
    public static void AltaPedido(NuevaCompra vista) {
        if (validarCampos(vista)) {
            compra = new Compra();
            proveedor = new Proveedor();
            empleado = new Empleado();
                try {
                    //Seteo los valores del Empleado
                    empleado.setIdEmpleado(Integer.valueOf(vista.getTxt_IdEmpleado().getText()));
                    //Seteo los valores del PROVEEDOR
                    proveedor.setIdProveedor(Integer.valueOf(vista.getTxt_IdProveedor().getText()));
                    
                    //Seteo los valores de la Venta
                    compra.setTotal(Double.parseDouble(vista.getTxt_Total().getText()));
                    compra.setProveedor(proveedor);
                    compra.setEmpleado(empleado);
                    //Tratamiento de la fecha
                    String fecha = new SimpleDateFormat("yyyy/MM/dd").format(vista.getDate_Fecha().getDate());
                    compra.setFecha(fecha);
                    //Fin del tratamiento
                    
                    //Grabo la Venta en la BD
                    compra.grabarCompra();
                    //Traigo el Ultimo Id de la BD
                    compra.setIdCompra(compra.maxId());
                    
                    //Trabajo las lineas
                    for (int i = 0; i < vista.getTabla_Descripcion().getRowCount(); i++) {
                        //Creo el producto por cada linea y seteo
                        producto = new Producto();
                        producto.setIdProducto(Integer.parseInt(vista.getTabla_Descripcion().getModel().getValueAt(i, 0).toString()));
                        producto.setDescripcion(vista.getTabla_Descripcion().getModel().getValueAt(i, 1).toString());
                        //Creo la linea de servicio y seteo 
                        linea = new LineaCompra();
                        linea.setCompra(compra);
                        linea.setProducto(producto);
                        linea.setCantidad(Integer.parseInt(vista.getTabla_Descripcion().getModel().getValueAt(i, 3).toString()));
                        linea.setPrecio(Double.parseDouble(vista.getTabla_Descripcion().getModel().getValueAt(i, 2).toString()));
                        //Grabo la Linea en la BD
                        linea.grabarLineaCompra();
                        //Una ves grabada la Linea de Venta Actualizo el Stock sumando la cantidad de la linea de venta.
                        producto.sumarStock(Integer.parseInt(vista.getTabla_Descripcion().getModel().getValueAt(i, 3).toString()));
                    }
                    JOptionPane.showMessageDialog(null, "COMPRA GRABADA CON ÉXITO");
                    imprimirCompra(compra);
                    vista.dispose();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(vista, "Datos mal ingresados", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
                }
        }
        
    }
    
    //METODO PARA TRABAJAR EL FORM LISTAR_PEDIDO
    public static void cargarCompras(ListarCompra vista) throws SQLException {
        compra = new Compra();
        vista.getModeloPedidos().setColumnCount(0);
        vista.getModeloPedidos().setNumRows(0);
        vista.getModeloPedidos().addColumn("Id");
        vista.getModeloPedidos().addColumn("Fecha");
        vista.getModeloPedidos().addColumn("Proveedor");
        vista.getModeloPedidos().addColumn("Empleado");
        vista.getModeloPedidos().addColumn("Total");
        ResultSet r = compra.listarTodasLasCompras();
        while (r.next()) {
            Object[] fila = new Object[5];
            fila[0] = r.getString("idcompra");
            fila[1] = r.getString("fecha");
            fila[2] = r.getString("proveedor");
            fila[3] = r.getString("empleado");
            fila[4] = r.getString("total");
            vista.getModeloPedidos().addRow(fila);
        }
        vista.getTabla_Pedidos().setModel(vista.getModeloPedidos());
         
            //DECLARO DOS VARIABLES PARA SETEAR LOS LABEL DE LA PARTE INFERIOR DEL FORM
            int numero = 0; //ESTE ME SERVIRA PARA CONTAR LA CANTIDAD DE VENTAS
            double total = 0; //ESTE ME SERVIRA PARA SUMAR EL TOTAL DE VENTAS
            
            //RECORRO EL MODELO DE LA TABLA VENTAS.
            for (int i = 0; i < vista.getModeloPedidos().getRowCount(); i++) {
                total += Double.parseDouble(vista.getTabla_Pedidos().getModel().getValueAt(i, 4).toString());
                numero ++;
            }
            
            //SETE LOS VALORES A LOS LABELS
            vista.getLabel_NumeroVenta().setText(String.valueOf(numero));
            vista.getLabel_TotalVenta().setText(String.valueOf(total));
            
            //LOS HAGO VISIBLE
            vista.getLabel_NumeroVenta().setVisible(true);
            vista.getLabel_TotalVenta().setVisible(true);        
    }
    
     public static void detallesDeCompra(ListarCompra vista) throws SQLException{
            //INSTANCIO LOS OBJETOS A UTILIZAR
            compra = new Compra();
            linea = new LineaCompra();
            //SETEO EL ID DE LA VENTA SELECCIONADO
            compra.setIdCompra(vista.getCompra().getIdCompra());
            //LE ASIGNO LA VENTA A LA LINEA
            linea.setCompra(compra);
            //TRABAJO EL MODELO DE LA TABLA DETALLES
            vista.getModeloDetalles().setColumnCount(0);
            vista.getModeloDetalles().setNumRows(0);
            vista.getModeloDetalles().addColumn("Id Producto");
            vista.getModeloDetalles().addColumn("Descripción");
            vista.getModeloDetalles().addColumn("Precio");
            vista.getModeloDetalles().addColumn("Cantidad");
            vista.getModeloDetalles().addColumn("Sub Total");
            //TRAIGO TODAS LAS VENTAS SEGUN EL ID DE LA VENTA
            ResultSet r = linea.listarLineasXCompra();
            while (r.next()) {
                Object[] filas = new Object[5];
                filas[0] = r.getString("idproducto");
                filas[1] = r.getString("descripcion");
                filas[2] = r.getString("precio");
                filas[3] = r.getString("cantidad");
                filas[4] = r.getString("subTotal");
                vista.getModeloDetalles().addRow(filas);
            }
            //LE ASIGO A LA TABLA VENTAS EL MODELO
            vista.getTabla_Detalles().setModel(vista.getModeloDetalles());
    }

    public static void filtrarCompras(ListarCompra vista, String cadena) throws SQLException {
        compra = new Compra();
        vista.getModeloPedidos().setColumnCount(0);
        vista.getModeloPedidos().setNumRows(0);
        vista.getModeloPedidos().addColumn("Id");
        vista.getModeloPedidos().addColumn("Fecha");
        vista.getModeloPedidos().addColumn("Proveedor");
        vista.getModeloPedidos().addColumn("Empleado");
        vista.getModeloPedidos().addColumn("Total");
        ResultSet r = compra.filtrarCompra(cadena);
        while (r.next()) {
            Object[] fila = new Object[5];
            fila[0] = r.getString("idcompra");
            fila[1] = r.getString("fecha");
            fila[2] = r.getString("proveedor");
            fila[3] = r.getString("empleado");
            fila[4] = r.getString("total");
            vista.getModeloPedidos().addRow(fila);
        }
        vista.getTabla_Pedidos().setModel(vista.getModeloPedidos());
         
            //DECLARO DOS VARIABLES PARA SETEAR LOS LABEL DE LA PARTE INFERIOR DEL FORM
            int numero = 0; //ESTE ME SERVIRA PARA CONTAR LA CANTIDAD DE VENTAS
            double total = 0; //ESTE ME SERVIRA PARA SUMAR EL TOTAL DE VENTAS
            
            //RECORRO EL MODELO DE LA TABLA VENTAS.
            for (int i = 0; i < vista.getModeloPedidos().getRowCount(); i++) {
                total += Double.parseDouble(vista.getTabla_Pedidos().getModel().getValueAt(i, 4).toString());
                numero ++;
            }
            
            //SETE LOS VALORES A LOS LABELS
            vista.getLabel_NumeroVenta().setText(String.valueOf(numero));
            vista.getLabel_TotalVenta().setText(String.valueOf(total));
            
            //LOS HAGO VISIBLE
            vista.getLabel_NumeroVenta().setVisible(true);
            vista.getLabel_TotalVenta().setVisible(true);  
    }
    
    public static void filtrarFechaCompras(ListarCompra vista) throws SQLException{
        if (vista.getDate_FechaInicio().getDate() == null || vista.getDate_FechaFin().getDate() == null) {
            JOptionPane.showMessageDialog(vista, "ERROR: DEBE INGRESAR EL RANGO DE FECHA CORRECTAMENTE", "ERROR", JOptionPane.ERROR_MESSAGE);
        }else{
            //Tratamiento de la fecha
            String fechaInicio = new SimpleDateFormat("yyyy/MM/dd").format(vista.getDate_FechaInicio().getDate());
            String fechaFin = new SimpleDateFormat("yyyy/MM/dd").format(vista.getDate_FechaFin().getDate());
            
            compra = new Compra();
                        
            vista.getModeloPedidos().setColumnCount(0);
            vista.getModeloPedidos().setNumRows(0);
            vista.getModeloPedidos().addColumn("Id");
            vista.getModeloPedidos().addColumn("Fecha");
            vista.getModeloPedidos().addColumn("Proveedor");
            vista.getModeloPedidos().addColumn("Empleado");
            vista.getModeloPedidos().addColumn("Total");
            ResultSet r = compra.listarCompraxFecha(fechaInicio, fechaFin);
            while (r.next()) {
                Object[] fila = new Object[5];
                fila[0] = r.getString("idcompra");
                fila[1] = r.getString("fecha");
                fila[2] = r.getString("proveedor");
                fila[3] = r.getString("empleado");
                fila[4] = r.getString("total");
                vista.getModeloPedidos().addRow(fila);
            }
            vista.getTabla_Pedidos().setModel(vista.getModeloPedidos());

                //DECLARO DOS VARIABLES PARA SETEAR LOS LABEL DE LA PARTE INFERIOR DEL FORM
                int numero = 0; //ESTE ME SERVIRA PARA CONTAR LA CANTIDAD DE VENTAS
                double total = 0; //ESTE ME SERVIRA PARA SUMAR EL TOTAL DE VENTAS

                //RECORRO EL MODELO DE LA TABLA VENTAS.
                for (int i = 0; i < vista.getModeloPedidos().getRowCount(); i++) {
                    total += Double.parseDouble(vista.getTabla_Pedidos().getModel().getValueAt(i, 4).toString());
                    numero ++;
                }

                //SETE LOS VALORES A LOS LABELS
                vista.getLabel_NumeroVenta().setText(String.valueOf(numero));
                vista.getLabel_TotalVenta().setText(String.valueOf(total));
            
            //LOS HAGO VISIBLE
            vista.getLabel_NumeroVenta().setVisible(true);
            vista.getLabel_TotalVenta().setVisible(true);
        }
        
    }
    
    public static void LimpiarFechas(ListarCompra vista){
        vista.getDate_FechaInicio().setDate(null);
        vista.getDate_FechaFin().setDate(null);
        try {
            //UNA VES LIMPIADOS LAS FECHAS POR LAS QUE SE FILTRABA, VUELVO A CARGAR LAS VENTAS DEL ULTIMO EMPLEADO SELECCIONADO
            //POR ESO SE INVOCA AL MEDOTO CARGAR_VENTAS
            cargarCompras(vista);
        } catch (SQLException ex) {
            Logger.getLogger(Controlador_Compra.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    //METODO PARA ETRABAJAR EN EL FORM LISTAR_VENTA_EMPLEADO
    public static void cargarEmpleados(ListarCompraEmpleado vista) throws SQLException {
        empleado = new Empleado();
        vista.getModeloEmpleados().setColumnCount(0);
        vista.getModeloEmpleados().setNumRows(0);
        vista.getModeloEmpleados().addColumn("Id");
        vista.getModeloEmpleados().addColumn("Apellido");
        vista.getModeloEmpleados().addColumn("Nombre");
        vista.getModeloEmpleados().addColumn("DNI");
        vista.getModeloEmpleados().addColumn("Cargo");
        ResultSet r = empleado.listarEmpleado();
        while (r.next()) {
            Object[] fila = new Object[5];
            fila[0] = r.getString("idempleado");
            fila[1] = r.getString("apellido");
            fila[2] = r.getString("nombre");
            fila[3] = r.getString("dni");
            fila[4] = r.getString("cargo");
            vista.getModeloEmpleados().addRow(fila);
        }
        vista.getTabla_Empleados().setModel(vista.getModeloEmpleados());
    }
    
    public static void filtrarEmpleados(ListarCompraEmpleado vista, String cadena) throws SQLException{
        empleado = new Empleado();
        vista.getModeloEmpleados().setColumnCount(0);
        vista.getModeloEmpleados().setNumRows(0);
        vista.getModeloEmpleados().addColumn("Id");
        vista.getModeloEmpleados().addColumn("Apellido");
        vista.getModeloEmpleados().addColumn("Nombre");
        vista.getModeloEmpleados().addColumn("DNI");
        vista.getModeloEmpleados().addColumn("Cargo");
        ResultSet r = empleado.filtrarEmpleado(cadena);
        while (r.next()) {
            Object[] fila = new Object[5];
            fila[0] = r.getString("idempleado");
            fila[1] = r.getString("apellido");
            fila[2] = r.getString("nombre");
            fila[3] = r.getString("dni");
            fila[4] = r.getString("cargo");
            vista.getModeloEmpleados().addRow(fila);
        }
        vista.getTabla_Empleados().setModel(vista.getModeloEmpleados());
    }
    
    public static void cargarCompraxEmpleado(ListarCompraEmpleado vista) throws SQLException{
            //INSTANCIO LOS OBJETOS A UTILIZAR
            compra = new Compra();
            empleado = new Empleado();
            //SETEO EL ID DEL EMPLEADO SELECCIONADO
            empleado.setIdEmpleado(vista.getEmpleado().getIdEmpleado());
            //SETEO EL EMPLEADO A LA VENTA PARA FILTRAR LAS VENTAS POR EL ID DEL EMPLEADO
            compra.setEmpleado(empleado);
            //TRABAJO EL MODELO DE LA TABLA VENTAS
            vista.getModeloPedidos().setColumnCount(0);
            vista.getModeloPedidos().setNumRows(0);
            vista.getModeloPedidos().addColumn("Id");
            vista.getModeloPedidos().addColumn("Fecha");
            vista.getModeloPedidos().addColumn("Proveedor");
            vista.getModeloPedidos().addColumn("Total");
            //TRAIGO TODAS LAS VENTAS SEGUN EL ID DEL EMPLEADO
            ResultSet r = compra.listarCompraxEmpleado();
            while (r.next()) {
                Object[] filas = new Object[4];
                filas[0] = r.getString("idcompra");
                filas[1] = r.getString("fecha");
                filas[2] = r.getString("proveedor");
                filas[3] = r.getString("total");
                vista.getModeloPedidos().addRow(filas);
            }
            //LE ASIGO A LA TABLA PEDIDOS EL MODELO
            vista.getTabla_Pedidos().setModel(vista.getModeloPedidos());
            
            //DECLARO DOS VARIABLES PARA SETEAR LOS LABEL DE LA PARTE INFERIOR DEL FORM
            int numero = 0; //ESTE ME SERVIRA PARA CONTAR LA CANTIDAD DE VENTAS
            double total = 0; //ESTE ME SERVIRA PARA SUMAR EL TOTAL DE VENTAS
            String nombre = vista.getEmpleado().getApellido() + ", " + vista.getEmpleado().getNombre();
            
            //RECORRO EL MODELO DE LA TABLA VENTAS.
            for (int i = 0; i < vista.getModeloPedidos().getRowCount(); i++) {
                total += Double.parseDouble(vista.getTabla_Pedidos().getModel().getValueAt(i, 3).toString());
                numero ++;
            }
            
            //SETE LOS VALORES A LOS LABELS
            vista.getLabel_NumeroVenta().setText(String.valueOf(numero));
            vista.getLabel_TotalVenta().setText(String.valueOf(total));
            vista.getLabel_Empleado().setText(nombre);
            
            //LOS HAGO VISIBLE
            vista.getLabel_NumeroVenta().setVisible(true);
            vista.getLabel_TotalVenta().setVisible(true);
            vista.getLabel_Empleado().setVisible(true);
        
    }
    
            
    public static void filtrarFechaCompras(ListarCompraEmpleado vista) throws SQLException{
        if (vista.getDate_FechaInicio().getDate() == null || vista.getDate_FechaFin().getDate() == null) {
            JOptionPane.showMessageDialog(vista, "ERROR: DEBE INGRESAR EL RANGO DE FECHA CORRECTAMENTE", "ERROR", JOptionPane.ERROR_MESSAGE);
        }else{
            //Tratamiento de la fecha
            String fechaInicio = new SimpleDateFormat("yyyy/MM/dd").format(vista.getDate_FechaInicio().getDate());
            String fechaFin = new SimpleDateFormat("yyyy/MM/dd").format(vista.getDate_FechaFin().getDate());
            //Fin del tratamiento
            compra = new Compra();
            empleado = new Empleado();
            //SETEO EL ID DEL EMPLEADO SELECCIONADO
            empleado.setIdEmpleado(vista.getEmpleado().getIdEmpleado());
            //SETEO EL EMPLEADO A LA VENTA PARA FILTRAR LAS VENTAS POR EL ID DEL EMPLEADO
            compra.setEmpleado(empleado);
            //TRABAJO EL MODELO DE LA TABLA VENTAS
            vista.getModeloPedidos().setColumnCount(0);
            vista.getModeloPedidos().setNumRows(0);
            vista.getModeloPedidos().addColumn("Id");
            vista.getModeloPedidos().addColumn("Fecha");
            vista.getModeloPedidos().addColumn("Proveedor");
            vista.getModeloPedidos().addColumn("Total");
            //TRAIGO TODAS LAS VENTAS SEGUN EL ID DEL EMPLEADO
            ResultSet r = compra.listarCompraxEmpleadoxFecha(fechaInicio, fechaFin);
            while (r.next()) {
                Object[] filas = new Object[4];
                filas[0] = r.getString("idcompra");
                filas[1] = r.getString("fecha");
                filas[2] = r.getString("proveedor");
                filas[3] = r.getString("total");
                vista.getModeloPedidos().addRow(filas);
            }
            //LE ASIGO A LA TABLA VENTAS EL MODELO
            vista.getTabla_Pedidos().setModel(vista.getModeloPedidos());
            
            //DECLARO DOS VARIABLES PARA SETEAR LOS LABEL DE LA PARTE INFERIOR DEL FORM
            int numero = 0; //ESTE ME SERVIRA PARA CONTAR LA CANTIDAD DE VENTAS
            double total = 0; //ESTE ME SERVIRA PARA SUMAR EL TOTAL DE VENTAS
            String nombre = vista.getEmpleado().getApellido() + ", " + vista.getEmpleado().getNombre();
            
            //RECORRO EL MODELO DE LA TABLA VENTAS.
            for (int i = 0; i < vista.getModeloPedidos().getRowCount(); i++) {
                total += Double.parseDouble(vista.getTabla_Pedidos().getModel().getValueAt(i, 3).toString());
                numero ++;
            }
            
            //SETE LOS VALORES A LOS LABELS
            vista.getLabel_NumeroVenta().setText(String.valueOf(numero));
            vista.getLabel_TotalVenta().setText(String.valueOf(total));
            vista.getLabel_Empleado().setText(nombre);
            
            //LOS HAGO VISIBLE
            vista.getLabel_NumeroVenta().setVisible(true);
            vista.getLabel_TotalVenta().setVisible(true);
            vista.getLabel_Empleado().setVisible(true);
        }
        
    }
    
    public static void LimpiarFechas(ListarCompraEmpleado vista){
        vista.getDate_FechaInicio().setDate(null);
        vista.getDate_FechaFin().setDate(null);
        try {
            //UNA VES LIMPIADOS LAS FECHAS POR LAS QUE SE FILTRABA, VUELVO A CARGAR LAS VENTAS DEL ULTIMO EMPLEADO SELECCIONADO
            //POR ESO SE INVOCA AL MEDOTO CARGAR_VENTAS_X_EMPLEADO
            cargarCompraxEmpleado(vista);
        } catch (SQLException ex) {
            Logger.getLogger(Controlador_Venta.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
     public static void exportarPDF(ListarCompra vista) throws SQLException, ClassNotFoundException{
        int fila = vista.getTabla_Pedidos().getSelectedRow();
        if (fila > -1) {
            int id = Integer.valueOf(vista.getTabla_Pedidos().getModel().getValueAt(fila, 0).toString());
            compra = new Compra();
            compra.setIdCompra(id);
            imprimirCompra(compra);
        }else{
            JOptionPane.showMessageDialog(null, "ERROR: DEBE SELECCIONAR UNA COMPRA", "MENSAJE DE ERROR!", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public static void imprimirCompra(Compra compra) throws SQLException, ClassNotFoundException{
        int opt = JOptionPane.showConfirmDialog(null, "¿DESEA IMPRIMIR EL COMPROBANTE DE COMPRA?", "Mensaje de Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (opt == JOptionPane.YES_OPTION) {
            Conexion conexion = new Conexion();
        
        //Metodo para Exportar
            try {
                String rutaInforme = "/home/fernando/NetBeansProjects/FinalGestionDeDatos/FinalGestionDeDatos2018/src/reportes/Compra.jasper";
                Map parametros = new HashMap();
                //SETEO LOS PARAMETROS
                parametros.put("Id", compra.getIdCompra());
                JasperPrint informe = JasperFillManager.fillReport(rutaInforme, parametros, conexion.getConnection());
                JasperViewer ventanavisor = new JasperViewer(informe, false);
                JOptionPane.showMessageDialog(null, "ESTO PUEDE TARDAR UNOS SEGUNDOS,\nESPERE POR FAVOR", "ESTAMOS GENERANDO EL REPORTE", JOptionPane.WARNING_MESSAGE);
                JOptionPane.showMessageDialog(null, "DOCUMENTO EXPORTADO EXITOSAMENTE!", "GUARDADO EXITOSO!", JOptionPane.INFORMATION_MESSAGE);
                ventanavisor.setTitle("PEDIDO");
                //ventanavisor.setExtendedState(Frame.MAXIMIZED_BOTH);
                ventanavisor.setVisible(true);
            } catch (Exception e) {//Catch del metodo exportar
                JOptionPane.showMessageDialog(null, "ERROR AL EXPORTAR DOCUMENTO");
            }//Fin de del metodo Exportar      
        }
    }
    
}
