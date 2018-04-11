package modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Fernando
 */
public class LineaCompra {
    
    private int idLinea;
    private int cantidad;
    private double precio;
    private Producto producto;
    private Compra compra;

    public LineaCompra() {
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

    public Compra getCompra() {
        return compra;
    }

    public void setCompra(Compra compra) {
        this.compra = compra;
    }
    
    //METODOS ABM
    
    public void grabarLineaCompra(){
        try {            
            Conexion conexion = new Conexion();
            
            String query = "INSERT INTO linea_compra (cantidad, precio, compra_idcompra, producto_idproducto) values (?,?,?,?);";
            PreparedStatement st = conexion.getConnection().prepareStatement(query);
            st.setInt(1, this.getCantidad());
            st.setDouble(2, this.getPrecio());
            st.setInt(3, this.getCompra().getIdCompra());
            st.setInt(4, this.getProducto().getIdProducto());
            st.execute();
            System.out.println("SE GRABO LA LINEA DE COMPRA EN LA BASE DE DATOS");
            st.close();
            conexion.desconectar();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    public ResultSet listarLineasXCompra(){
        ResultSet resultado = null;
         try {
            Conexion conexion = new Conexion();
            String query = "SELECT producto.idproducto AS idproducto, producto.descripcion, cantidad, precio, (precio*cantidad) AS subTotal\n" +
                           "FROM linea_compra\n" +
                           "INNER JOIN compra ON compra.idcompra = linea_compra.compra_idcompra\n" +
                           "INNER JOIN producto ON producto.idproducto = linea_compra.producto_idproducto\n" +
                           "WHERE linea_compra.compra_idcompra = '" + this.getCompra().getIdCompra() + "' ;";
            PreparedStatement st = conexion.getConnection().prepareStatement(query);
            resultado = st.executeQuery();
            conexion.desconectar();
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
         
         return resultado;
    }
    
}
