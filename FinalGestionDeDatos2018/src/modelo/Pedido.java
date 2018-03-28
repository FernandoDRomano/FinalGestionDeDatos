package modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Fernando
 */
public class Pedido {
    
    private int idPedido;
    private String fecha;
    private double total;
    private Empleado empleado;
    private Proveedor proveedor;
    private ArrayList<LineaPedido> linea;


    public Pedido() {
        linea = new ArrayList<LineaPedido>();
    }

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
    
    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    public ArrayList<LineaPedido> getLinea() {
        return linea;
    }

    public void setLinea(ArrayList<LineaPedido> linea) {
        this.linea = linea;
    }
    
    // METODOS ABM
    
    public int maxId() throws SQLException{
        int id = 0;
        ResultSet resultado = null;
        Conexion conexion = new Conexion();
            
        String query = "SELECT MAX(idpedido) AS id FROM pedido;";
        //PreparedStatement st = conexion.getConnect().prepareStatement(query);
        PreparedStatement st = conexion.getConnection().prepareStatement(query);
        resultado = st.executeQuery();
        while(resultado.next()){
            id = Integer.parseInt(resultado.getString("id"));
        }
        
        return id;
    }
    
    public void grabarPedido(){
        try {            
            Conexion conexion = new Conexion();
            String query = "insert into pedido (fecha, total, proveedor_idproveedor, empleado_idempleado) values (?,?,?,?);";
            //PreparedStatement st = conexion.getConnect().prepareStatement(query);
            PreparedStatement st = conexion.getConnection().prepareStatement(query);
            st.setString(1, this.getFecha());
            st.setDouble(2, this.getTotal());
            st.setInt(3, this.getProveedor().getIdProveedor());
            st.setInt(4, this.getEmpleado().getIdEmpleado());
            st.execute();
            System.out.println("SE GRABO EL PEDIDO EN LA BASE DE DATOS");
            st.close();            
            conexion.desconectar();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    public ResultSet listarTodasLosPedidos(){
        ResultSet resultado = null;
         try {
            Conexion conexion = new Conexion();
            String query = "SELECT pedido.idpedido, pedido.fecha, pedido.total, proveedor.nombre AS proveedor, empleado.apellido AS empleado\n" +
                           "FROM pedido\n" +
                           "INNER JOIN proveedor ON pedido.proveedor_idproveedor = proveedor.idproveedor\n" +
                           "INNER JOIN empleado ON pedido.empleado_idempleado = empleado.idempleado;";
            PreparedStatement st = conexion.getConnection().prepareStatement(query);
            resultado = st.executeQuery();
            conexion.desconectar();
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
         
         return resultado;
    }
    
    public ResultSet filtrarPedido(String cadena){
        ResultSet resultado = null;
         try {
            Conexion conexion = new Conexion();
            String query = "SELECT pedido.idpedido, pedido.fecha, pedido.total, proveedor.nombre AS proveedor, empleado.apellido AS empleado\n" +
                           "FROM pedido\n" +
                           "INNER JOIN proveedor ON pedido.proveedor_idproveedor = proveedor.idproveedor\n" +
                           "INNER JOIN empleado ON pedido.empleado_idempleado = empleado.idempleado\n" +
                           "where pedido.idpedido like '" + cadena + "%' or proveedor.nombre like '" + cadena + "%' ;";
            System.out.println(query);
            PreparedStatement st = conexion.getConnection().prepareStatement(query);
            resultado = st.executeQuery();
            conexion.desconectar();
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
         
         return resultado;
    }
    
    public ResultSet listarPedidoxFecha(String fechaInicio, String fechaFin){
        ResultSet resultado = null;
         try {
            Conexion conexion = new Conexion();
            String query = "SELECT pedido.idpedido, pedido.fecha, pedido.total, proveedor.nombre AS proveedor, empleado.apellido AS empleado\n" +
                           "FROM pedido\n" +
                           "INNER JOIN proveedor ON pedido.proveedor_idproveedor = proveedor.idproveedor\n" +
                           "INNER JOIN empleado ON pedido.empleado_idempleado = empleado.idempleado\n" +
                           "WHERE pedido.fecha >= '"+ fechaInicio+"' and pedido.fecha <= '" +fechaFin + "' ;";
            System.out.println(query);
            PreparedStatement st = conexion.getConnection().prepareStatement(query);
            resultado = st.executeQuery();
            conexion.desconectar();
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
         
         return resultado;
    }
    
    public ResultSet listarPedidoxEmpleado(){
        ResultSet resultado = null;
         try {
            Conexion conexion = new Conexion();
            String query = "SELECT pedido.idpedido, pedido.fecha, proveedor.nombre AS proveedor, pedido.total\n" +
                           "FROM pedido\n" +
                           "INNER JOIN proveedor ON pedido.proveedor_idproveedor = proveedor.idproveedor\n" +
                           "INNER JOIN empleado ON pedido.empleado_idempleado = empleado.idempleado\n" +
                           "WHERE empleado.idempleado = '" + this.getEmpleado().getIdEmpleado() + "' ;";
            PreparedStatement st = conexion.getConnection().prepareStatement(query);
            resultado = st.executeQuery();
            conexion.desconectar();
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
         
         return resultado;
    }
    
    public ResultSet listarPedidoxEmpleadoxFecha(String fechaInicio, String fechaFin){
        ResultSet resultado = null;
         try {
            Conexion conexion = new Conexion();
            String query = "SELECT pedido.idpedido, pedido.fecha, proveedor.nombre AS proveedor, pedido.total\n" +
                           "FROM pedido\n" +
                           "INNER JOIN proveedor ON pedido.proveedor_idproveedor = proveedor.idproveedor\n" +
                           "INNER JOIN empleado ON pedido.empleado_idempleado = empleado.idempleado\n" +
                           "WHERE empleado.idempleado = '" + this.getEmpleado().getIdEmpleado() + "' and pedido.fecha >= '"+ fechaInicio+"' and pedido.fecha <= '" +fechaFin + "' ;";
            System.out.println(query);
            PreparedStatement st = conexion.getConnection().prepareStatement(query);
            resultado = st.executeQuery();
            conexion.desconectar();
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
         
         return resultado;
    }
    
}

