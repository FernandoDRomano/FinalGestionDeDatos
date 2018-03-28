
package controlador;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import modelo.Empleado;
import modelo.LineaPedido;
import modelo.Pedido;
import modelo.Producto;
import modelo.Proveedor;
import vista.BuscarProducto;
import vista.BuscarProductoPedido;
import vista.BuscarProveedor;
import vista.ListarPedido;
import vista.ListarPedidoEmpleado;
import vista.NuevoPedido;


/**
 *
 * @author fernando
 */
public class Controlador_Pedido {
  
    private static Empleado empleado;
    private static Proveedor proveedor;
    private static Producto producto;
    private static Pedido compra;
    private static LineaPedido linea;
    
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
    
    public static void CargarTodosLosProductos(BuscarProductoPedido vista) throws SQLException {
        producto = new Producto();
        vista.getModelo().setColumnCount(0);
        vista.getModelo().setNumRows(0);
        vista.getModelo().addColumn("Id");
        vista.getModelo().addColumn("Descripcion");
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
    
    public static void ventanaBuscarProveedor(NuevoPedido vista) throws SQLException{
        BuscarProveedor ventana = new BuscarProveedor(null, true,vista);
        ventana.setVisible(true);
    }
    
    public static void ventanaBuscarProducto(NuevoPedido vista) throws SQLException{
        BuscarProductoPedido ventana = new BuscarProductoPedido(null, true, vista);
        ventana.setVisible(true);
    }
    
    //======================= METODO PARA CARGAR EL PROVEEDOR ============================
    public static void agregarProveedorAlPedido(BuscarProveedor vista, NuevoPedido compra){
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
            JOptionPane.showMessageDialog(vista, "DEBE SELECCIONAR UN PROVEEDOR", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        
    }
    
    //======================= MANEJO DE LAS FILAS, CALCULO DEL TOTAL Y DEL STOCK =================================
    public static void agregarProductoAlPedido(BuscarProductoPedido vista, NuevoPedido compra){
        int fila = vista.getTabla_Producto().getSelectedRow();
        if (fila > -1) {
            if (vista.getTxt_Cantidad().getText().equals("")) {
                JOptionPane.showMessageDialog(vista, "DEBE INGRESAR UNA CANTIDAD", "ERROR", JOptionPane.ERROR_MESSAGE);
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
            JOptionPane.showMessageDialog(vista, "DEBE SELECCIONAR UN PRODUCTO", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public static void quitarProducto(NuevoPedido vista){
        int fila = vista.getTabla_Descripcion().getSelectedRow();
        System.out.println("Fila: " + fila);
        if (fila > -1) {
            int resp = JOptionPane.showConfirmDialog(vista, "Esta Seguro de Quitar?","Advertencia",JOptionPane.YES_NO_OPTION);
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
            JOptionPane.showMessageDialog(vista, "DEBE SELECCIONAR UN PRODUCTO", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public static void calcularTotal(NuevoPedido vista){
        double total = 0;
        for (int i = 0; i < vista.getModelo().getRowCount() ; i++) {
            total += Double.parseDouble(vista.getTabla_Descripcion().getModel().getValueAt(i, 4).toString());
        }
        
        System.out.println("Total: " + total);
        vista.getTxt_Total().setText(String.valueOf(total));
    }
    
    //============================== METODOS ABM ====================================
    
    public static void AltaVenta(NuevoPedido vista) {
        compra = new Pedido();
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
                    compra.grabarPedido();
                    //Traigo el Ultimo Id de la BD
                    compra.setIdPedido(compra.maxId());
                    
                    //Trabajo las lineas
                    for (int i = 0; i < vista.getTabla_Descripcion().getRowCount(); i++) {
                        //Creo el producto por cada linea y seteo
                        producto = new Producto();
                        producto.setIdProducto(Integer.parseInt(vista.getTabla_Descripcion().getModel().getValueAt(i, 0).toString()));
                        producto.setDescripcion(vista.getTabla_Descripcion().getModel().getValueAt(i, 1).toString());
                        //Creo la linea de servicio y seteo 
                        linea = new LineaPedido();
                        linea.setPedido(compra);
                        linea.setProducto(producto);
                        linea.setCantidad(Integer.parseInt(vista.getTabla_Descripcion().getModel().getValueAt(i, 3).toString()));
                        linea.setPrecio(Double.parseDouble(vista.getTabla_Descripcion().getModel().getValueAt(i, 2).toString()));
                        //Grabo la Linea en la BD
                        linea.grabarLineaPedido();
                        //Una ves grabada la Linea de Venta Actualizo el Stock sumando la cantidad de la linea de venta.
                        producto.sumarStock(Integer.parseInt(vista.getTabla_Descripcion().getModel().getValueAt(i, 3).toString()));
                    }
                    JOptionPane.showMessageDialog(null, "Pedido Grabado con Exito!");
                    vista.dispose();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(vista, "Datos mal ingresados", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
                }
    }
    
    //METODO PARA TRABAJAR EL FORM LISTAR_PEDIDO
    public static void cargarPedidos(ListarPedido vista) throws SQLException {
        compra = new Pedido();
        vista.getModeloPedidos().setColumnCount(0);
        vista.getModeloPedidos().setNumRows(0);
        vista.getModeloPedidos().addColumn("Id");
        vista.getModeloPedidos().addColumn("Fecha");
        vista.getModeloPedidos().addColumn("Proveedor");
        vista.getModeloPedidos().addColumn("Empleado");
        vista.getModeloPedidos().addColumn("Total");
        ResultSet r = compra.listarTodasLosPedidos();
        while (r.next()) {
            Object[] fila = new Object[5];
            fila[0] = r.getString("idpedido");
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
    
     public static void detallesDePedido(ListarPedido vista) throws SQLException{
            //INSTANCIO LOS OBJETOS A UTILIZAR
            compra = new Pedido();
            linea = new LineaPedido();
            //SETEO EL ID DE LA VENTA SELECCIONADO
            compra.setIdPedido(vista.getCompra().getIdPedido());
            //LE ASIGNO LA VENTA A LA LINEA
            linea.setPedido(compra);
            //TRABAJO EL MODELO DE LA TABLA DETALLES
            vista.getModeloDetalles().setColumnCount(0);
            vista.getModeloDetalles().setNumRows(0);
            vista.getModeloDetalles().addColumn("Id Producto");
            vista.getModeloDetalles().addColumn("Descripcion");
            vista.getModeloDetalles().addColumn("Precio");
            vista.getModeloDetalles().addColumn("Cantidad");
            vista.getModeloDetalles().addColumn("Sub Total");
            //TRAIGO TODAS LAS VENTAS SEGUN EL ID DE LA VENTA
            ResultSet r = linea.listarLineasXpedido();
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

    public static void filtrarPedidos(ListarPedido vista, String cadena) throws SQLException {
        compra = new Pedido();
        vista.getModeloPedidos().setColumnCount(0);
        vista.getModeloPedidos().setNumRows(0);
        vista.getModeloPedidos().addColumn("Id");
        vista.getModeloPedidos().addColumn("Fecha");
        vista.getModeloPedidos().addColumn("Proveedor");
        vista.getModeloPedidos().addColumn("Empleado");
        vista.getModeloPedidos().addColumn("Total");
        ResultSet r = compra.filtrarPedido(cadena);
        while (r.next()) {
            Object[] fila = new Object[5];
            fila[0] = r.getString("idpedido");
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
    
    public static void filtrarFechaPedidos(ListarPedido vista) throws SQLException{
        if (vista.getDate_FechaInicio().getDate() == null || vista.getDate_FechaFin().getDate() == null) {
            JOptionPane.showMessageDialog(vista, "DEBE INGRESAR EL RANGO DE FECHA CORRECTAMENTE", "ERROR", JOptionPane.ERROR_MESSAGE);
        }else{
            //Tratamiento de la fecha
            String fechaInicio = new SimpleDateFormat("yyyy/MM/dd").format(vista.getDate_FechaInicio().getDate());
            String fechaFin = new SimpleDateFormat("yyyy/MM/dd").format(vista.getDate_FechaFin().getDate());
            
            compra = new Pedido();
                        
            vista.getModeloPedidos().setColumnCount(0);
            vista.getModeloPedidos().setNumRows(0);
            vista.getModeloPedidos().addColumn("Id");
            vista.getModeloPedidos().addColumn("Fecha");
            vista.getModeloPedidos().addColumn("Proveedor");
            vista.getModeloPedidos().addColumn("Empleado");
            vista.getModeloPedidos().addColumn("Total");
            ResultSet r = compra.listarPedidoxFecha(fechaInicio, fechaFin);
            while (r.next()) {
                Object[] fila = new Object[5];
                fila[0] = r.getString("idpedido");
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
    
    public static void LimpiarFechas(ListarPedido vista){
        vista.getDate_FechaInicio().setDate(null);
        vista.getDate_FechaFin().setDate(null);
        try {
            //UNA VES LIMPIADOS LAS FECHAS POR LAS QUE SE FILTRABA, VUELVO A CARGAR LAS VENTAS DEL ULTIMO EMPLEADO SELECCIONADO
            //POR ESO SE INVOCA AL MEDOTO CARGAR_VENTAS
            cargarPedidos(vista);
        } catch (SQLException ex) {
            Logger.getLogger(Controlador_Pedido.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    //METODO PARA ETRABAJAR EN EL FORM LISTAR_VENTA_EMPLEADO
    public static void cargarEmpleados(ListarPedidoEmpleado vista) throws SQLException {
        empleado = new Empleado();
        vista.getModeloEmpleados().setColumnCount(0);
        vista.getModeloEmpleados().setNumRows(0);
        vista.getModeloEmpleados().addColumn("Id");
        vista.getModeloEmpleados().addColumn("Apellido");
        vista.getModeloEmpleados().addColumn("Nombre");
        vista.getModeloEmpleados().addColumn("Dni");
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
    
    public static void filtrarEmpleados(ListarPedidoEmpleado vista, String cadena) throws SQLException{
        empleado = new Empleado();
        vista.getModeloEmpleados().setColumnCount(0);
        vista.getModeloEmpleados().setNumRows(0);
        vista.getModeloEmpleados().addColumn("Id");
        vista.getModeloEmpleados().addColumn("Apellido");
        vista.getModeloEmpleados().addColumn("Nombre");
        vista.getModeloEmpleados().addColumn("Dni");
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
    
    public static void cargarPedidoxEmpleado(ListarPedidoEmpleado vista) throws SQLException{
            //INSTANCIO LOS OBJETOS A UTILIZAR
            compra = new Pedido();
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
            ResultSet r = compra.listarPedidoxEmpleado();
            while (r.next()) {
                Object[] filas = new Object[4];
                filas[0] = r.getString("idpedido");
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
    
            
    public static void filtrarFechaPedidos(ListarPedidoEmpleado vista) throws SQLException{
        if (vista.getDate_FechaInicio().getDate() == null || vista.getDate_FechaFin().getDate() == null) {
            JOptionPane.showMessageDialog(vista, "DEBE INGRESAR EL RANGO DE FECHA CORRECTAMENTE", "ERROR", JOptionPane.ERROR_MESSAGE);
        }else{
            //Tratamiento de la fecha
            String fechaInicio = new SimpleDateFormat("yyyy/MM/dd").format(vista.getDate_FechaInicio().getDate());
            String fechaFin = new SimpleDateFormat("yyyy/MM/dd").format(vista.getDate_FechaFin().getDate());
            //Fin del tratamiento
            compra = new Pedido();
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
            ResultSet r = compra.listarPedidoxEmpleadoxFecha(fechaInicio, fechaFin);
            while (r.next()) {
                Object[] filas = new Object[4];
                filas[0] = r.getString("idpedido");
                filas[1] = r.getString("fecha");
                filas[2] = r.getString("cliente");
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
    
    public static void LimpiarFechas(ListarPedidoEmpleado vista){
        vista.getDate_FechaInicio().setDate(null);
        vista.getDate_FechaFin().setDate(null);
        try {
            //UNA VES LIMPIADOS LAS FECHAS POR LAS QUE SE FILTRABA, VUELVO A CARGAR LAS VENTAS DEL ULTIMO EMPLEADO SELECCIONADO
            //POR ESO SE INVOCA AL MEDOTO CARGAR_VENTAS_X_EMPLEADO
            cargarPedidoxEmpleado(vista);
        } catch (SQLException ex) {
            Logger.getLogger(Controlador_Venta.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
