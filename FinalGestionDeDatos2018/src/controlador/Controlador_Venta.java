package controlador;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import modelo.Cliente;
import modelo.Conexion;
import modelo.Empleado;
import modelo.LineaVenta;
import modelo.Producto;
import modelo.Venta;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
import vista.BuscarCliente;
import vista.BuscarProducto;
import vista.ListarVenta;
import vista.ListarVentaEmpleado;
import vista.NuevaVenta;

/**
 *
 * @author fernando
 */
public class Controlador_Venta {

    private static Venta venta;
    private static LineaVenta linea;
    private static Cliente cliente;
    private static Empleado empleado;
    private static Producto producto;
    
    //////////////// METODOS PARA CARGAR EN LOS FORM DE BUSQUEDA EL CLIENTE Y EL PRODUCTO /////////////////////////
    public static void CargarTodosClientes(BuscarCliente vista) throws SQLException {
        Cliente cliente = new Cliente();
        vista.getModelo().setColumnCount(0);
        vista.getModelo().setNumRows(0);
        vista.getModelo().addColumn("Id");
        vista.getModelo().addColumn("Apellido");
        vista.getModelo().addColumn("Nombre");
        vista.getModelo().addColumn("Teléfono");
        ResultSet r = cliente.listarCliente();
        while (r.next()) {
            Object[] fila = new Object[4];
            fila[0] = r.getString("idCliente");
            fila[1] = r.getString("apellido");
            fila[2] = r.getString("nombre");
            fila[3] = r.getString("telefono");
            vista.getModelo().addRow(fila);
        }

        vista.getTabla_Cliente().setModel(vista.getModelo());
        
        //declaramos un arreglo de enteros con los anchos que deseamos
        //para nuestra tabla
        int[] anchos = {30, 200, 200, 80};
        //hacemos un bucle FOR desde cero hasta la cantidad de columnas
        //de nuestra tabla
        for(int i = 0; i < vista.getTabla_Cliente().getColumnCount(); i++) {
        //Sacamos el modelo de columnas de nuestra tabla
        //luego obtenemos la columna en la posicion "i"
        //invocamos el metodo setPreferrefWidth para ajustar el ancho
        //y le damos el valor del entero que esta en el arreglo en la posicion "i"
        vista.getTabla_Cliente().getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
        }
        
    }
    
    public static void CargarTodosClientes(BuscarCliente vista, String cadena) throws SQLException {
        cliente = new Cliente();
        vista.getModelo().setColumnCount(0);
        vista.getModelo().setNumRows(0);
        vista.getModelo().addColumn("Id");
        vista.getModelo().addColumn("Apellido");
        vista.getModelo().addColumn("Nombre");
        vista.getModelo().addColumn("Teléfono");
        ResultSet r = cliente.buscarCliente(cadena);
        while (r.next()) {
            Object[] fila = new Object[4];
            fila[0] = r.getString("idCliente");
            fila[1] = r.getString("apellido");
            fila[2] = r.getString("nombre");
            fila[3] = r.getString("telefono");
            vista.getModelo().addRow(fila);
        }

        vista.getTabla_Cliente().setModel(vista.getModelo());
        
        //declaramos un arreglo de enteros con los anchos que deseamos
        //para nuestra tabla
        int[] anchos = {30, 200, 200, 80};
        //hacemos un bucle FOR desde cero hasta la cantidad de columnas
        //de nuestra tabla
        for(int i = 0; i < vista.getTabla_Cliente().getColumnCount(); i++) {
        //Sacamos el modelo de columnas de nuestra tabla
        //luego obtenemos la columna en la posicion "i"
        //invocamos el metodo setPreferrefWidth para ajustar el ancho
        //y le damos el valor del entero que esta en el arreglo en la posicion "i"
        vista.getTabla_Cliente().getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
        }
        
    }

    
    public static void CargarTodosLosProductos(BuscarProducto vista) throws SQLException {
        producto = new Producto();
        vista.getModelo().setColumnCount(0);
        vista.getModelo().setNumRows(0);
        vista.getModelo().addColumn("Id");
        vista.getModelo().addColumn("Descripción");
        vista.getModelo().addColumn("Precio");
        vista.getModelo().addColumn("Stock");
        ResultSet r = producto.listarProducto();
        while (r.next()) {
            Object[] fila = new Object[4];
            fila[0] = r.getString("idProducto");
            fila[1] = r.getString("descripcion");
            fila[2] = r.getString("precioVenta");
            fila[3] = r.getString("stock");
            vista.getModelo().addRow(fila);
        }
        vista.getTabla_Producto().setModel(vista.getModelo());
        
        int[] anchos = {30, 200, 100, 80};
        for(int i = 0; i < vista.getTabla_Producto().getColumnCount(); i++) {
            vista.getTabla_Producto().getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
        }
        
    }
    
    public static void CargarTodosLosProductos(BuscarProducto vista, String cadena) throws SQLException {
        producto = new Producto();
        vista.getModelo().setColumnCount(0);
        vista.getModelo().setNumRows(0);
        vista.getModelo().addColumn("Id");
        vista.getModelo().addColumn("Descripción");
        vista.getModelo().addColumn("Precio");
        vista.getModelo().addColumn("Stock");
        ResultSet r = producto.buscarProducto(cadena);
        while (r.next()) {
            Object[] fila = new Object[4];
            fila[0] = r.getString("idProducto");
            fila[1] = r.getString("descripcion");
            fila[2] = r.getString("precioVenta");
            fila[3] = r.getString("stock");
            vista.getModelo().addRow(fila);
        }
        vista.getTabla_Producto().setModel(vista.getModelo());
        
        int[] anchos = {30, 200, 100, 80};
        for(int i = 0; i < vista.getTabla_Producto().getColumnCount(); i++) {
            vista.getTabla_Producto().getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
        }
        
    }
    
    public static void ventanaBuscarCliente(NuevaVenta vista) throws SQLException{
        BuscarCliente ventana = new BuscarCliente(null, true,vista);
        ventana.setVisible(true);
    }
    
    public static void ventanaBuscarProducto(NuevaVenta vista) throws SQLException{
        BuscarProducto ventana = new BuscarProducto(null, true, vista);
        ventana.setVisible(true);
    }
    
    //======================= METODO PARA CARGAR EL CLIENTE ============================
    public static void agregarClienteAlaVenta(BuscarCliente vista, NuevaVenta venta){
        int fila = vista.getTabla_Cliente().getSelectedRow();
        if (fila > -1) {
            //saco los valores
            int id = Integer.parseInt(vista.getTabla_Cliente().getModel().getValueAt(fila, 0).toString());
            String apellido = vista.getTabla_Cliente().getModel().getValueAt(fila, 1).toString();
            String nombre = vista.getTabla_Cliente().getModel().getValueAt(fila, 2).toString();
            int telefono = Integer.valueOf(vista.getTabla_Cliente().getModel().getValueAt(fila, 3).toString());
            //Creo el cliente
            cliente = new Cliente(id,apellido,nombre,telefono);
            //Los cargo en la vista
            venta.getTxt_IdCliente().setText(""+cliente.getIdCliente());
            venta.getTxt_NombreCliente().setText(cliente.getApellido() + ", " + cliente.getNombre());
            vista.setVisible(false);
        }else{
            JOptionPane.showMessageDialog(vista, "ERROR: DEBE SELECCIONAR UN CLIENTE", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        
    }
    
     //======================= MANEJO DE LAS FILAS, CALCULO DEL TOTAL Y DEL STOCK =================================
    public static void agregarProductoAlaVenta(BuscarProducto vista, NuevaVenta venta){
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
                //Llamo al metodo para calcular el Stock
                restarStock(vista, fila, cantidad);
                //Realizo el calculo del sub Total
                subTotal = precio * cantidad;
                //Los paso a un vector
                String filaElemento[] = {id, descripcion, String.valueOf(precio), String.valueOf(cantidad), String.valueOf(subTotal)};
                System.out.println("ID: " + filaElemento[0]+", Descripcion: " + filaElemento[1] + ", Precio: " + filaElemento[2] + ", Cantidad: " + filaElemento[3] + ", SubTotal: " + filaElemento[4]);
                //Le paso al modelo de la factura el vector
                venta.getModelo().addRow(filaElemento);
                venta.getTabla_Descripcion().setModel(venta.getModelo());
                calcularTotal(venta);
                //Seteo el valor del txt_Cantidad = 1
                vista.getTxt_Cantidad().setText("1");
                
                //Asigno valores a las columnas
                int[] anchos = {40, 260, 70, 70, 70};
                for(int i = 0; i < venta.getTabla_Descripcion().getColumnCount(); i++) {
                    venta.getTabla_Descripcion().getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
                }
            }
            
        }else{
            JOptionPane.showMessageDialog(vista, "ERROR: DEBE SELECCIONAR UN PRODUCTO", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public static void quitarProducto(NuevaVenta vista){
        int fila = vista.getTabla_Descripcion().getSelectedRow();
        System.out.println("Fila: " + fila);
        if (fila > -1) {
            int resp = JOptionPane.showConfirmDialog(vista, "¿ESTÁ USTED SEGURO DE QUITAR ESTÉ PRODUCTO?","Advertencia",JOptionPane.YES_NO_OPTION);
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
    
    public static void calcularTotal(NuevaVenta vista){
        double total = 0;
        for (int i = 0; i < vista.getModelo().getRowCount() ; i++) {
            total += Double.parseDouble(vista.getTabla_Descripcion().getModel().getValueAt(i, 4).toString());
        }
        
        System.out.println("Total: " + total);
        vista.getTxt_Total().setText(String.valueOf(total));
    }
    
    public static void restarStock(BuscarProducto vista, int fila, int cantidad){
        //ESTE METODO ME PERMITE RESTAR EL STOCK DIRECTAMENTE EN EL FORM BUSCARPRODUCTO DEPENDIENDO
        //DE LA CANTIDAD INGRESADA
        int stock = Integer.parseInt(vista.getTabla_Producto().getModel().getValueAt(fila, 3).toString()) - cantidad;
        vista.getTabla_Producto().getModel().setValueAt(stock, fila, 3);
    }
    
    //EL METODO VALIDAR STOCK ES EL QUE ME VALIDARA EL STOCK DEL FORM BUSCARPRODUCTO EN BASE A LA CANTIDAD DE LOS PRODUCTOS
    //YA SELECCIONADOS EN LAS LINEAS DE DETALLE DE LA NUEVAVENTA
    public static void validarStock(NuevaVenta vista, BuscarProducto vistaP){
        //INICIALIZO LAS VARIABLES
        int id = 0;
        int cantidad = 0;
        //RECORRO EL MODELO DE LA TABLA DEL FORM NUEVAVENTA
        for (int i = 0; i < vista.getModelo().getRowCount() ; i++) {
            //CAPTURO EL ID Y LA CANTIDAD DE LOS PRODUCTOS DE LAS LINEAS DE DETALLE
            id = Integer.parseInt(vista.getTabla_Descripcion().getModel().getValueAt(i, 0).toString());
            cantidad = Integer.parseInt(vista.getTabla_Descripcion().getModel().getValueAt(i, 3).toString());
            //LLAMO AL METODO CONTROLARSTOCK PASANDOLE COMO PARAMETROS EL ID, LA CANTIDAD Y LA VISTA NUEVAVENTA
            controlarStock(vistaP, id, cantidad);
        }
    }
    
    //ESTE METODO ES EL ENCARGADO DE RECORRER EL MODELO DEL FORM BUSCARPRODUCTO Y RESTAR LA CANTIDAD DE LOS PRODUCTOS
    //YA SELECCIONADOS EN LA LINEA DE DETALLE
    public static void controlarStock(BuscarProducto vista, int id, int cantidad){
        //RECORRO EL MODELO DE LA VISTA BUSCARPRODUCTO
        for (int i = 0; i < vista.getTabla_Producto().getModel().getRowCount(); i++) {
            //VALIDO SI EL ID DEL PRODUCTO DE LA LINEA DE DETALLE ES IGUAL AL ID DEL PRODUCTO DEL MODELO QUE ESTOY RECORRIENDO
            if (id == Integer.parseInt(vista.getTabla_Producto().getModel().getValueAt(i, 0).toString())) {
                //EN CASO DE SER IGUAL LOS ID, CAPTURO EL STOCK ORIGINAL TRAIDO DE LA BASE DE DATOS Y LE RESTO LA CANTIDAD QUE FIGURA EN LA LINEA DE DETALLES
                int stock = Integer.parseInt(vista.getTabla_Producto().getModel().getValueAt(i, 3).toString());
                //ACTUALIZO EL VALOR DEL STOCK REALIZANDO LA RESTA
                vista.getTabla_Producto().getModel().setValueAt(stock-cantidad, i ,3);
            }
        }
    }
    
    public static boolean validarCampos(NuevaVenta vista){
        boolean bandera = true;
        String idCliente = vista.getTxt_IdCliente().getText();
        idCliente = idCliente.replaceAll(" ", "");
        
        String total = vista.getTxt_Total().getText();
        total = total.replaceAll(" ", "");
        
        if (idCliente.length() == 0) {
            JOptionPane.showMessageDialog(vista, "ERROR: DEBE SELECCIONAR EL CLIENTE", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
            bandera = false;
        }
        
        if (total.length() == 0 || total.equals("0.0")) {
            JOptionPane.showMessageDialog(vista, "ERROR: DEBE INGRESAR PRODUCTOS A LA VENTA", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
            bandera = false;
        }
        
        return bandera;
    }
    
    //============================== METODOS ABM ====================================
    
    public static void AltaVenta(NuevaVenta vista) throws SQLException, ClassNotFoundException {
        if (validarCampos(vista)) {
            venta = new Venta();
            cliente = new Cliente();
            empleado = new Empleado();
                //Seteo los valores del Empleado
                empleado.setIdEmpleado(Integer.valueOf(vista.getTxt_IdEmpleado().getText()));
                //Seteo los valores del Cliente
                cliente.setIdCliente(Integer.valueOf(vista.getTxt_IdCliente().getText()));

                //Seteo los valores de la Venta
                venta.setTotal(Double.parseDouble(vista.getTxt_Total().getText()));
                venta.setCliente(cliente);
                venta.setEmpleado(empleado);
                //Tratamiento de la fecha
                String fecha = new SimpleDateFormat("yyyy/MM/dd").format(vista.getDate_Fecha().getDate());
                venta.setFecha(fecha);
                //Fin del tratamiento

                //Grabo la Venta en la BD
                venta.grabarVenta();
                //Traigo el Ultimo Id de la BD
                venta.setIdVenta(venta.maxId());

                //Trabajo las lineas
                for (int i = 0; i < vista.getTabla_Descripcion().getRowCount(); i++) {
                    //Creo el producto por cada linea y seteo
                    producto = new Producto();
                    producto.setIdProducto(Integer.parseInt(vista.getTabla_Descripcion().getModel().getValueAt(i, 0).toString()));
                    producto.setDescripcion(vista.getTabla_Descripcion().getModel().getValueAt(i, 1).toString());
                    //Creo la linea de servicio y seteo 
                    linea = new LineaVenta();
                    linea.setVenta(venta);
                    linea.setProducto(producto);
                    linea.setCantidad(Integer.parseInt(vista.getTabla_Descripcion().getModel().getValueAt(i, 3).toString()));
                    linea.setPrecio(Double.parseDouble(vista.getTabla_Descripcion().getModel().getValueAt(i, 2).toString()));
                    //Grabo la Linea en la BD
                    linea.grabarLineaVenta();
                    //Una ves grabada la Linea de Venta Actualizo el Stock restando la cantidad de la linea de venta.
                    producto.restarStock(Integer.parseInt(vista.getTabla_Descripcion().getModel().getValueAt(i, 3).toString()));
                    }
                JOptionPane.showMessageDialog(vista, "VENTA GRABADA CON ÉXITO", "Mensaje de Información", JOptionPane.INFORMATION_MESSAGE);                    imprimirVenta(venta);
                vista.dispose();
        }
        
    }
    
    
    //METODO PARA ETRABAJAR EN EL FORM LISTAR_VENTA_EMPLEADO
    public static void cargarEmpleados(ListarVentaEmpleado vista) throws SQLException {
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
    
    public static void filtrarEmpleados(ListarVentaEmpleado vista, String cadena) throws SQLException{
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
    
    public static void cargarVentaxEmpleado(ListarVentaEmpleado vista) throws SQLException{
            //INSTANCIO LOS OBJETOS A UTILIZAR
            venta = new Venta();
            empleado = new Empleado();
            //SETEO EL ID DEL EMPLEADO SELECCIONADO
            empleado.setIdEmpleado(vista.getEmpleado().getIdEmpleado());
            //SETEO EL EMPLEADO A LA VENTA PARA FILTRAR LAS VENTAS POR EL ID DEL EMPLEADO
            venta.setEmpleado(empleado);
            //TRABAJO EL MODELO DE LA TABLA VENTAS
            vista.getModeloVentas().setColumnCount(0);
            vista.getModeloVentas().setNumRows(0);
            vista.getModeloVentas().addColumn("Id");
            vista.getModeloVentas().addColumn("Fecha");
            vista.getModeloVentas().addColumn("Cliente");
            vista.getModeloVentas().addColumn("Total");
            //TRAIGO TODAS LAS VENTAS SEGUN EL ID DEL EMPLEADO
            ResultSet r = venta.listarVentaxEmpleado();
            while (r.next()) {
                Object[] filas = new Object[4];
                filas[0] = r.getString("idventa");
                filas[1] = r.getString("fecha");
                filas[2] = r.getString("cliente");
                filas[3] = r.getString("total");
                vista.getModeloVentas().addRow(filas);
            }
            //LE ASIGO A LA TABLA VENTAS EL MODELO
            vista.getTabla_Ventas().setModel(vista.getModeloVentas());
            
            //DECLARO DOS VARIABLES PARA SETEAR LOS LABEL DE LA PARTE INFERIOR DEL FORM
            int numero = 0; //ESTE ME SERVIRA PARA CONTAR LA CANTIDAD DE VENTAS
            double total = 0; //ESTE ME SERVIRA PARA SUMAR EL TOTAL DE VENTAS
            String nombre = vista.getEmpleado().getApellido() + ", " + vista.getEmpleado().getNombre();
            
            //RECORRO EL MODELO DE LA TABLA VENTAS.
            for (int i = 0; i < vista.getModeloVentas().getRowCount(); i++) {
                total += Double.parseDouble(vista.getTabla_Ventas().getModel().getValueAt(i, 3).toString());
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
    
            
    public static void filtrarFechaVentas(ListarVentaEmpleado vista) throws SQLException{
        if (vista.getDate_FechaInicio().getDate() == null || vista.getDate_FechaFin().getDate() == null) {
            JOptionPane.showMessageDialog(vista, "ERROR: DEBE INGRESAR EL RANGO DE FECHA CORRECTAMENTE", "ERROR", JOptionPane.ERROR_MESSAGE);
        }else{
            //Tratamiento de la fecha
            String fechaInicio = new SimpleDateFormat("yyyy/MM/dd").format(vista.getDate_FechaInicio().getDate());
            String fechaFin = new SimpleDateFormat("yyyy/MM/dd").format(vista.getDate_FechaFin().getDate());
            //Fin del tratamiento
            venta = new Venta();
            empleado = new Empleado();
            //SETEO EL ID DEL EMPLEADO SELECCIONADO
            empleado.setIdEmpleado(vista.getEmpleado().getIdEmpleado());
            //SETEO EL EMPLEADO A LA VENTA PARA FILTRAR LAS VENTAS POR EL ID DEL EMPLEADO
            venta.setEmpleado(empleado);
            //TRABAJO EL MODELO DE LA TABLA VENTAS
            vista.getModeloVentas().setColumnCount(0);
            vista.getModeloVentas().setNumRows(0);
            vista.getModeloVentas().addColumn("Id");
            vista.getModeloVentas().addColumn("Fecha");            
            vista.getModeloVentas().addColumn("Cliente");
            vista.getModeloVentas().addColumn("Total");
            //TRAIGO TODAS LAS VENTAS SEGUN EL ID DEL EMPLEADO
            ResultSet r = venta.listarVentaxEmpleadoxFecha(fechaInicio, fechaFin);
            while (r.next()) {
                Object[] filas = new Object[4];
                filas[0] = r.getString("idventa");
                filas[1] = r.getString("fecha");
                filas[2] = r.getString("cliente");
                filas[3] = r.getString("total");
                vista.getModeloVentas().addRow(filas);
            }
            //LE ASIGO A LA TABLA VENTAS EL MODELO
            vista.getTabla_Ventas().setModel(vista.getModeloVentas());
            
            //DECLARO DOS VARIABLES PARA SETEAR LOS LABEL DE LA PARTE INFERIOR DEL FORM
            int numero = 0; //ESTE ME SERVIRA PARA CONTAR LA CANTIDAD DE VENTAS
            double total = 0; //ESTE ME SERVIRA PARA SUMAR EL TOTAL DE VENTAS
            String nombre = vista.getEmpleado().getApellido() + ", " + vista.getEmpleado().getNombre();
            
            //RECORRO EL MODELO DE LA TABLA VENTAS.
            for (int i = 0; i < vista.getModeloVentas().getRowCount(); i++) {
                total += Double.parseDouble(vista.getTabla_Ventas().getModel().getValueAt(i, 3).toString());
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
    
    public static void LimpiarFechas(ListarVentaEmpleado vista){
        vista.getDate_FechaInicio().setDate(null);
        vista.getDate_FechaFin().setDate(null);
        try {
            //UNA VES LIMPIADOS LAS FECHAS POR LAS QUE SE FILTRABA, VUELVO A CARGAR LAS VENTAS DEL ULTIMO EMPLEADO SELECCIONADO
            //POR ESO SE INVOCA AL MEDOTO CARGAR_VENTAS_X_EMPLEADO
            cargarVentaxEmpleado(vista);
        } catch (SQLException ex) {
            Logger.getLogger(Controlador_Venta.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    //METODO PARA TRABAJAR EL FORM LISTAR_VENTA
    
    public static void cargarVentas(ListarVenta vista) throws SQLException {
        venta = new Venta();
        vista.getModeloVentas().setColumnCount(0);
        vista.getModeloVentas().setNumRows(0);
        vista.getModeloVentas().addColumn("Id");
        vista.getModeloVentas().addColumn("Fecha");
        vista.getModeloVentas().addColumn("Cliente");
        vista.getModeloVentas().addColumn("Empleado");
        vista.getModeloVentas().addColumn("Total");
        ResultSet r = venta.listarTodasVentas();
        while (r.next()) {
            Object[] fila = new Object[5];
            fila[0] = r.getString("idventa");
            fila[1] = r.getString("fecha");
            fila[2] = r.getString("cliente");
            fila[3] = r.getString("empleado");
            fila[4] = r.getString("total");
            vista.getModeloVentas().addRow(fila);
        }
        vista.getTabla_Ventas().setModel(vista.getModeloVentas());
         
            //DECLARO DOS VARIABLES PARA SETEAR LOS LABEL DE LA PARTE INFERIOR DEL FORM
            int numero = 0; //ESTE ME SERVIRA PARA CONTAR LA CANTIDAD DE VENTAS
            double total = 0; //ESTE ME SERVIRA PARA SUMAR EL TOTAL DE VENTAS
            
            //RECORRO EL MODELO DE LA TABLA VENTAS.
            for (int i = 0; i < vista.getModeloVentas().getRowCount(); i++) {
                total += Double.parseDouble(vista.getTabla_Ventas().getModel().getValueAt(i, 4).toString());
                numero ++;
            }
            
            //SETE LOS VALORES A LOS LABELS
            vista.getLabel_NumeroVenta().setText(String.valueOf(numero));
            vista.getLabel_TotalVenta().setText(String.valueOf(total));
            
            //LOS HAGO VISIBLE
            vista.getLabel_NumeroVenta().setVisible(true);
            vista.getLabel_TotalVenta().setVisible(true);        
    }
    
     public static void detallesDeVenta(ListarVenta vista) throws SQLException{
            //INSTANCIO LOS OBJETOS A UTILIZAR
            venta = new Venta();
            linea = new LineaVenta();
            //SETEO EL ID DE LA VENTA SELECCIONADO
            venta.setIdVenta(vista.getVenta().getIdVenta());
            //LE ASIGNO LA VENTA A LA LINEA
            linea.setVenta(venta);
            //TRABAJO EL MODELO DE LA TABLA DETALLES
            vista.getModeloDetalles().setColumnCount(0);
            vista.getModeloDetalles().setNumRows(0);
            vista.getModeloDetalles().addColumn("Id Producto");
            vista.getModeloDetalles().addColumn("Descripción");
            vista.getModeloDetalles().addColumn("Precio");
            vista.getModeloDetalles().addColumn("Cantidad");
            vista.getModeloDetalles().addColumn("Sub Total");
            //TRAIGO TODAS LAS VENTAS SEGUN EL ID DE LA VENTA
            ResultSet r = linea.listarLineasXventa();
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

    public static void filtrarVentas(ListarVenta vista, String cadena) throws SQLException {
        venta = new Venta();
        vista.getModeloVentas().setColumnCount(0);
        vista.getModeloVentas().setNumRows(0);
        vista.getModeloVentas().addColumn("Id");
        vista.getModeloVentas().addColumn("Fecha");
        vista.getModeloVentas().addColumn("Cliente");
        vista.getModeloVentas().addColumn("Empleado");
        vista.getModeloVentas().addColumn("Total");
        ResultSet r = venta.filtrarVenta(cadena);
        while (r.next()) {
            Object[] fila = new Object[5];
            fila[0] = r.getString("idventa");
            fila[1] = r.getString("fecha");
            fila[2] = r.getString("cliente");
            fila[3] = r.getString("empleado");
            fila[4] = r.getString("total");
            vista.getModeloVentas().addRow(fila);
        }
        vista.getTabla_Ventas().setModel(vista.getModeloVentas());
         
            //DECLARO DOS VARIABLES PARA SETEAR LOS LABEL DE LA PARTE INFERIOR DEL FORM
            int numero = 0; //ESTE ME SERVIRA PARA CONTAR LA CANTIDAD DE VENTAS
            double total = 0; //ESTE ME SERVIRA PARA SUMAR EL TOTAL DE VENTAS
            
            //RECORRO EL MODELO DE LA TABLA VENTAS.
            for (int i = 0; i < vista.getModeloVentas().getRowCount(); i++) {
                total += Double.parseDouble(vista.getTabla_Ventas().getModel().getValueAt(i, 4).toString());
                numero ++;
            }
            
            //SETE LOS VALORES A LOS LABELS
            vista.getLabel_NumeroVenta().setText(String.valueOf(numero));
            vista.getLabel_TotalVenta().setText(String.valueOf(total));
            
            //LOS HAGO VISIBLE
            vista.getLabel_NumeroVenta().setVisible(true);
            vista.getLabel_TotalVenta().setVisible(true);  
    }
    
    public static void filtrarFechaVentas(ListarVenta vista) throws SQLException{
        if (vista.getDate_FechaInicio().getDate() == null || vista.getDate_FechaFin().getDate() == null) {
            JOptionPane.showMessageDialog(vista, "ERROR: DEBE INGRESAR EL RANGO DE FECHA CORRECTAMENTE", "ERROR", JOptionPane.ERROR_MESSAGE);
        }else{
            //Tratamiento de la fecha
            String fechaInicio = new SimpleDateFormat("yyyy/MM/dd").format(vista.getDate_FechaInicio().getDate());
            String fechaFin = new SimpleDateFormat("yyyy/MM/dd").format(vista.getDate_FechaFin().getDate());
            
            venta = new Venta();
                        
            vista.getModeloVentas().setColumnCount(0);
            vista.getModeloVentas().setNumRows(0);
            vista.getModeloVentas().addColumn("Id");
            vista.getModeloVentas().addColumn("Fecha");
            vista.getModeloVentas().addColumn("Cliente");
            vista.getModeloVentas().addColumn("Empleado");
            vista.getModeloVentas().addColumn("Total");
            ResultSet r = venta.listarVentaxFecha(fechaInicio, fechaFin);
            while (r.next()) {
                Object[] fila = new Object[5];
                fila[0] = r.getString("idventa");
                fila[1] = r.getString("fecha");
                fila[2] = r.getString("cliente");
                fila[3] = r.getString("empleado");
                fila[4] = r.getString("total");
                vista.getModeloVentas().addRow(fila);
            }
            vista.getTabla_Ventas().setModel(vista.getModeloVentas());

                //DECLARO DOS VARIABLES PARA SETEAR LOS LABEL DE LA PARTE INFERIOR DEL FORM
                int numero = 0; //ESTE ME SERVIRA PARA CONTAR LA CANTIDAD DE VENTAS
                double total = 0; //ESTE ME SERVIRA PARA SUMAR EL TOTAL DE VENTAS

                //RECORRO EL MODELO DE LA TABLA VENTAS.
                for (int i = 0; i < vista.getModeloVentas().getRowCount(); i++) {
                    total += Double.parseDouble(vista.getTabla_Ventas().getModel().getValueAt(i, 4).toString());
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
    
    public static void LimpiarFechas(ListarVenta vista){
        vista.getDate_FechaInicio().setDate(null);
        vista.getDate_FechaFin().setDate(null);
        try {
            //UNA VES LIMPIADOS LAS FECHAS POR LAS QUE SE FILTRABA, VUELVO A CARGAR LAS VENTAS DEL ULTIMO EMPLEADO SELECCIONADO
            //POR ESO SE INVOCA AL MEDOTO CARGAR_VENTAS
            cargarVentas(vista);
        } catch (SQLException ex) {
            Logger.getLogger(Controlador_Venta.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public static void exportarPDF(ListarVenta vista) throws SQLException, ClassNotFoundException{
        int fila = vista.getTabla_Ventas().getSelectedRow();
        if (fila > -1) {
            int id = Integer.valueOf(vista.getTabla_Ventas().getModel().getValueAt(fila, 0).toString());
            venta = new Venta();
            venta.setIdVenta(id);
            imprimirVenta(venta);
        }else{
            JOptionPane.showMessageDialog(null, "ERROR: DEBE SELECCIONAR UNA VENTA", "MENSAJE DE ERROR!", JOptionPane.ERROR_MESSAGE);
        }
    }
  
    
    public static void imprimirVenta(Venta venta) throws SQLException, ClassNotFoundException{
        int opt = JOptionPane.showConfirmDialog(null, "¿DESEA IMPRIMIR EL COMPROBANTE DE VENTA?", "Mensaje de Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (opt == JOptionPane.YES_OPTION) {
            Conexion conexion = new Conexion();
        
        //Metodo para Exportar
            try {
                String rutaInforme = "/home/fernando/NetBeansProjects/FinalGestionDeDatos/FinalGestionDeDatos2018/src/reportes/Venta.jasper";
                Map parametros = new HashMap();
                //SETEO LOS PARAMETROS
                parametros.put("id", venta.getIdVenta());
                JasperPrint informe = JasperFillManager.fillReport(rutaInforme, parametros, conexion.getConnection());
                JasperViewer ventanavisor = new JasperViewer(informe, false);
                JOptionPane.showMessageDialog(null, "ESTO PUEDE TARDAR UNOS SEGUNDOS,\nESPERE POR FAVOR", "ESTAMOS GENERANDO EL REPORTE", JOptionPane.WARNING_MESSAGE);
                JOptionPane.showMessageDialog(null, "DOCUMENTO EXPORTADO EXITOSAMENTE!", "GUARDADO EXITOSO!", JOptionPane.INFORMATION_MESSAGE);
                ventanavisor.setTitle("VENTA");
                //ventanavisor.setExtendedState(Frame.MAXIMIZED_BOTH);
                ventanavisor.setVisible(true);
            } catch (Exception e) {//Catch del metodo exportar
                JOptionPane.showMessageDialog(null, "ERROR AL EXPORTAR DOCUMENTO");
            }//Fin de del metodo Exportar      
        }
    }
    
    
    
}
