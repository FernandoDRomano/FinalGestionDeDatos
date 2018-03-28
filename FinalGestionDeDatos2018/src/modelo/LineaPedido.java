package modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Fernando
 */
public class LineaPedido {
    
    private int idLinea;
    private int cantidad;
    private double precio;
    private Producto producto;
    private Pedido pedido;

    public LineaPedido(int idLinea, int cantidad, double precio, Producto producto, Pedido pedido) {
        this.idLinea = idLinea;
        this.cantidad = cantidad;
        this.precio = precio;
        this.producto = producto;
        this.pedido = pedido;
    }

    public LineaPedido() {
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

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }
    
    //METODOS ABM
    
    public void grabarLineaPedido(){
        try {            
            Conexion conexion = new Conexion();
            
            String query = "INSERT INTO linea_pedido (cantidad, precio, pedido_idpedido, producto_idproducto) values (?,?,?,?);";
            PreparedStatement st = conexion.getConnection().prepareStatement(query);
            st.setInt(1, this.getCantidad());
            st.setDouble(2, this.getPrecio());
            st.setInt(3, this.getPedido().getIdPedido());
            st.setInt(4, this.getProducto().getIdProducto());
            st.execute();
            System.out.println("SE GRABO LA LINEA DE PEDIDO EN LA BASE DE DATOS");
            st.close();
            conexion.desconectar();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    public ResultSet listarLineasXpedido(){
        ResultSet resultado = null;
         try {
            Conexion conexion = new Conexion();
            String query = "SELECT producto.idproducto AS idproducto, producto.descripcion, cantidad, precio, (precio*cantidad) AS subTotal\n" +
                           "FROM linea_pedido\n" +
                           "INNER JOIN pedido ON pedido.idpedido = linea_pedido.pedido_idpedido\n" +
                           "INNER JOIN producto ON producto.idproducto = linea_pedido.producto_idproducto\n" +
                           "WHERE linea_pedido.pedido_idpedido = '" + this.getPedido().getIdPedido() + "' ;";
            PreparedStatement st = conexion.getConnection().prepareStatement(query);
            resultado = st.executeQuery();
            conexion.desconectar();
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
         
         return resultado;
    }
    
}
