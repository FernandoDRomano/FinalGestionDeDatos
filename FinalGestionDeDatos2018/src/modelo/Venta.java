
package modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author fernando
 */
public class Venta {
    
    private int idVenta;
    private String fecha;
    private double total;
    private Cliente cliente;
    private Empleado empleado;
    private ArrayList<LineaVenta> linea;

    public Venta(int idVenta, String fecha, double total, Cliente cliente, Empleado empleado) {
        this.idVenta = idVenta;
        this.fecha = fecha;
        this.total = total;
        this.cliente = cliente;
        this.empleado = empleado;
        linea = new ArrayList<LineaVenta>();
    }

    public Venta() {
        linea = new ArrayList<LineaVenta>();
    }

    public int getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(int idVenta) {
        this.idVenta = idVenta;
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

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public ArrayList<LineaVenta> getLinea() {
        return linea;
    }

    public void setLinea(ArrayList<LineaVenta> linea) {
        this.linea = linea;
    }

    public int maxId() throws SQLException{
        int id = 0;
        ResultSet resultado = null;
        Conexion conexion = new Conexion();
            
        String query = "SELECT MAX(idventa) AS id FROM venta;";
        //PreparedStatement st = conexion.getConnect().prepareStatement(query);
        PreparedStatement st = conexion.getConnection().prepareStatement(query);
        resultado = st.executeQuery();
        while(resultado.next()){
            id = Integer.parseInt(resultado.getString("id"));
        }
        
        return id;
    }
    
    public void grabarVenta(){
        try {            
            Conexion conexion = new Conexion();
            String query = "insert into venta (fecha, total, cliente_idcliente, empleado_idempleado) values (?,?,?,?);";
            //PreparedStatement st = conexion.getConnect().prepareStatement(query);
            PreparedStatement st = conexion.getConnection().prepareStatement(query);
            st.setString(1, this.getFecha());
            st.setDouble(2, this.getTotal());
            st.setInt(3, this.getCliente().getIdCliente());
            st.setInt(4, this.getEmpleado().getIdEmpleado());
            st.execute();
            System.out.println("SE GRABO LA VENTA EN LA BASE DE DATOS");
            st.close();            
            conexion.desconectar();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    public void eliminarVenta(){
        try {
            Conexion conexion = new Conexion();
            String query = "delete from venta where idventa = " + this.getIdVenta()+ ";";
            PreparedStatement st = conexion.getConnection().prepareStatement(query);
            st.execute();
            System.out.println("SE ELIMINO LA VENTA DE LA BASE DE DATOS");
            st.close();
            conexion.desconectar();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    public ResultSet listarTodasVentas(){
        ResultSet resultado = null;
         try {
            Conexion conexion = new Conexion();
            String query = "SELECT venta.idventa, venta.fecha, venta.total, cliente.apellido AS cliente, empleado.apellido AS empleado\n" +
                           "FROM venta\n" +
                           "INNER JOIN cliente ON venta.cliente_idcliente = cliente.idcliente\n" +
                           "INNER JOIN empleado ON venta.empleado_idempleado = empleado.idempleado;";
            PreparedStatement st = conexion.getConnection().prepareStatement(query);
            resultado = st.executeQuery();
            conexion.desconectar();
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
         
         return resultado;
    }
    
    public ResultSet listarVentaxEmpleado(){
        ResultSet resultado = null;
         try {
            Conexion conexion = new Conexion();
            String query = "SELECT venta.idventa, venta.fecha, cliente.nombre AS cliente, venta.total\n" +
                           "FROM venta\n" +
                           "INNER JOIN cliente ON venta.cliente_idcliente = cliente.idcliente\n" +
                           "INNER JOIN empleado ON venta.empleado_idempleado = empleado.idempleado\n" +
                           "WHERE empleado.idempleado = '" + this.getEmpleado().getIdEmpleado() + "' ;";
            PreparedStatement st = conexion.getConnection().prepareStatement(query);
            resultado = st.executeQuery();
            conexion.desconectar();
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
         
         return resultado;
    }
    
    public ResultSet listarVentaxEmpleadoxFecha(String fechaInicio, String fechaFin){
        ResultSet resultado = null;
         try {
            Conexion conexion = new Conexion();
            String query = "SELECT venta.idventa, venta.fecha, cliente.apellido AS cliente, venta.total\n" +
                           "FROM venta\n" +
                           "INNER JOIN cliente ON venta.cliente_idcliente = cliente.idcliente\n" +
                           "INNER JOIN empleado ON venta.empleado_idempleado = empleado.idempleado\n" +
                           "WHERE empleado.idempleado = '" + this.getEmpleado().getIdEmpleado() + "' and venta.fecha >= '"+ fechaInicio+"' and venta.fecha <= '" +fechaFin + "' ;";
            System.out.println(query);
            PreparedStatement st = conexion.getConnection().prepareStatement(query);
            resultado = st.executeQuery();
            conexion.desconectar();
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
         
         return resultado;
    }
    
    public ResultSet listarVentaxFecha(String fechaInicio, String fechaFin){
        ResultSet resultado = null;
         try {
            Conexion conexion = new Conexion();
            String query = "SELECT venta.idventa, venta.fecha, venta.total, cliente.apellido AS cliente, empleado.apellido AS empleado\n" +
                           "FROM venta\n" +
                           "INNER JOIN cliente ON venta.cliente_idcliente = cliente.idcliente\n" +
                           "INNER JOIN empleado ON venta.empleado_idempleado = empleado.idempleado\n" +
                           "WHERE venta.fecha >= '"+ fechaInicio+"' and venta.fecha <= '" +fechaFin + "' ;";
            System.out.println(query);
            PreparedStatement st = conexion.getConnection().prepareStatement(query);
            resultado = st.executeQuery();
            conexion.desconectar();
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
         
         return resultado;
    }
    
    public ResultSet filtrarVenta(String cadena){
        ResultSet resultado = null;
         try {
            Conexion conexion = new Conexion();
            String query = "SELECT venta.idventa, venta.fecha, venta.total, cliente.apellido AS cliente, empleado.apellido AS empleado\n" +
                           "FROM venta\n" +
                           "INNER JOIN cliente ON venta.cliente_idcliente = cliente.idcliente\n" +
                           "INNER JOIN empleado ON venta.empleado_idempleado = empleado.idempleado\n" +
                           "where venta.idventa like '" + cadena + "%' or cliente.apellido like '" + cadena + "%' ;";
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
