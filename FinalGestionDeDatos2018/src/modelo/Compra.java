package modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Fernando
 */
public class Compra {
    
    private int idCompra;
    private String fecha;
    private double total;
    private Empleado empleado;
    private Proveedor proveedor;
    private ArrayList<LineaCompra> linea;


    public Compra() {
        linea = new ArrayList<LineaCompra>();
    }

    public int getIdCompra() {
        return idCompra;
    }

    public void setIdCompra(int idCompra) {
        this.idCompra = idCompra;
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

    public ArrayList<LineaCompra> getLinea() {
        return linea;
    }

    public void setLinea(ArrayList<LineaCompra> linea) {
        this.linea = linea;
    }
    
    // METODOS ABM
    
    public int maxId() throws SQLException{
        int id = 0;
        ResultSet resultado = null;
        Conexion conexion = new Conexion();
            
        String query = "SELECT MAX(idcompra) AS id FROM compra;";
        //PreparedStatement st = conexion.getConnect().prepareStatement(query);
        PreparedStatement st = conexion.getConnection().prepareStatement(query);
        resultado = st.executeQuery();
        while(resultado.next()){
            id = Integer.parseInt(resultado.getString("id"));
        }
        
        return id;
    }
    
    public void grabarCompra(){
        try {            
            Conexion conexion = new Conexion();
            String query = "insert into compra (fecha, total, proveedor_idproveedor, empleado_idempleado) values (?,?,?,?);";
            //PreparedStatement st = conexion.getConnect().prepareStatement(query);
            PreparedStatement st = conexion.getConnection().prepareStatement(query);
            st.setString(1, this.getFecha());
            st.setDouble(2, this.getTotal());
            st.setInt(3, this.getProveedor().getIdProveedor());
            st.setInt(4, this.getEmpleado().getIdEmpleado());
            st.execute();
            System.out.println("SE GRABO LA COMPRA EN LA BASE DE DATOS");
            st.close();            
            conexion.desconectar();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    public ResultSet listarTodasLasCompras(){
        ResultSet resultado = null;
         try {
            Conexion conexion = new Conexion();
            String query = "SELECT compra.idcompra, compra.fecha, compra.total, proveedor.nombre AS proveedor, empleado.apellido AS empleado\n" +
                           "FROM compra\n" +
                           "INNER JOIN proveedor ON compra.proveedor_idproveedor = proveedor.idproveedor\n" +
                           "INNER JOIN empleado ON compra.empleado_idempleado = empleado.idempleado;";
            PreparedStatement st = conexion.getConnection().prepareStatement(query);
            resultado = st.executeQuery();
            conexion.desconectar();
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
         
         return resultado;
    }
    
    public ResultSet filtrarCompra(String cadena){
        ResultSet resultado = null;
         try {
            Conexion conexion = new Conexion();
            String query = "SELECT compra.idcompra, compra.fecha, compra.total, proveedor.nombre AS proveedor, empleado.apellido AS empleado\n" +
                           "FROM compra\n" +
                           "INNER JOIN proveedor ON compra.proveedor_idproveedor = proveedor.idproveedor\n" +
                           "INNER JOIN empleado ON compra.empleado_idempleado = empleado.idempleado\n" +
                           "where compra.idcompra like '" + cadena + "%' or proveedor.nombre like '" + cadena + "%' ;";
            System.out.println(query);
            PreparedStatement st = conexion.getConnection().prepareStatement(query);
            resultado = st.executeQuery();
            conexion.desconectar();
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
         
         return resultado;
    }
    
    public ResultSet listarCompraxFecha(String fechaInicio, String fechaFin){
        ResultSet resultado = null;
         try {
            Conexion conexion = new Conexion();
            String query = "SELECT compra.idcompra, compra.fecha, compra.total, proveedor.nombre AS proveedor, empleado.apellido AS empleado\n" +
                           "FROM compra\n" +
                           "INNER JOIN proveedor ON compra.proveedor_idproveedor = proveedor.idproveedor\n" +
                           "INNER JOIN empleado ON compra.empleado_idempleado = empleado.idempleado\n" +
                           "WHERE compra.fecha >= '"+ fechaInicio+"' and compra.fecha <= '" +fechaFin + "' ;";
            System.out.println(query);
            PreparedStatement st = conexion.getConnection().prepareStatement(query);
            resultado = st.executeQuery();
            conexion.desconectar();
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
         
         return resultado;
    }
    
    public ResultSet listarCompraxEmpleado(){
        ResultSet resultado = null;
         try {
            Conexion conexion = new Conexion();
            String query = "SELECT compra.idcompra, compra.fecha, proveedor.nombre AS proveedor, compra.total\n" +
                           "FROM compra\n" +
                           "INNER JOIN proveedor ON compra.proveedor_idproveedor = proveedor.idproveedor\n" +
                           "INNER JOIN empleado ON compra.empleado_idempleado = empleado.idempleado\n" +
                           "WHERE empleado.idempleado = '" + this.getEmpleado().getIdEmpleado() + "' ;";
            PreparedStatement st = conexion.getConnection().prepareStatement(query);
            resultado = st.executeQuery();
            conexion.desconectar();
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
         
         return resultado;
    }
    
    public ResultSet listarCompraxEmpleadoxFecha(String fechaInicio, String fechaFin){
        ResultSet resultado = null;
         try {
            Conexion conexion = new Conexion();
            String query = "SELECT compra.idcompra, compra.fecha, proveedor.nombre AS proveedor, compra.total\n" +
                           "FROM compra\n" +
                           "INNER JOIN proveedor ON compra.proveedor_idproveedor = proveedor.idproveedor\n" +
                           "INNER JOIN empleado ON compra.empleado_idempleado = empleado.idempleado\n" +
                           "WHERE empleado.idempleado = '" + this.getEmpleado().getIdEmpleado() + "' and compra.fecha >= '"+ fechaInicio+"' and compra.fecha <= '" +fechaFin + "' ;";
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

