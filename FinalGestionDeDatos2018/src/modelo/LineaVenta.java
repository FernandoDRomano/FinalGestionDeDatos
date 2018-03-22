package modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Fernando
 */
public class LineaVenta {
    
    private int idLinea;
    private int cantidad;
    private double precio;
    private Producto producto;
    private Venta venta;

    public LineaVenta(int idLinea, int cantidad, double precio, Producto producto, Venta venta) {
        this.idLinea = idLinea;
        this.cantidad = cantidad;
        this.precio = precio;
        this.producto = producto;
        this.venta = venta;
    }

    public LineaVenta() {
    }

    public int getIdLinea() {
        return idLinea;
    }

    public void setIdLinea(int idLinea) {
        this.idLinea = idLinea;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Venta getVenta() {
        return venta;
    }

    public void setVenta(Venta venta) {
        this.venta = venta;
    }
    
    public void grabarLineaVenta(){
        try {            
            Conexion conexion = new Conexion();
            
            String query = "INSERT INTO linea_venta (cantidad, precio, venta_idventa, producto_idproducto) values (?,?,?,?);";
            PreparedStatement st = conexion.getConnection().prepareStatement(query);
            st.setInt(1, this.getCantidad());
            st.setDouble(2, this.getPrecio());
            st.setInt(3, this.getVenta().getIdVenta());
            st.setInt(4, this.getProducto().getIdProducto());
            st.execute();
            System.out.println("SE GRABO LA LINEA DE VENTA EN LA BASE DE DATOS");
            st.close();
            conexion.desconectar();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    public ResultSet listarLineasXventa(){
        ResultSet resultado = null;
         try {
            Conexion conexion = new Conexion();
            String query = "SELECT producto.idproducto AS idproducto, producto.descripcion, cantidad, precio, (precio*cantidad) AS subTotal\n" +
                           "FROM linea_venta\n" +
                           "INNER JOIN venta ON venta.idventa = linea_venta.venta_idventa\n" +
                           "INNER JOIN producto ON producto.idproducto = linea_venta.producto_idproducto\n" +
                           "WHERE linea_venta.venta_idventa = '" + this.getVenta().getIdVenta() + "' ;";
            PreparedStatement st = conexion.getConnection().prepareStatement(query);
            resultado = st.executeQuery();
            conexion.desconectar();
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
         
         return resultado;
    }
    
    public void eliminarLineaServicio(){
        try {
            Conexion conexion = new Conexion();
            
            String query = "DELETE FROM linea_venta WHERE linea_venta.venta_idventa = " + this.getVenta().getIdVenta()+ ";";
            PreparedStatement st = conexion.getConnection().prepareStatement(query);
            st.execute();
            System.out.println("SE ELIMINO LA LINEA DE VENTA DE LA BASE DE DATOS");
            st.close();
            conexion.desconectar();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
}
