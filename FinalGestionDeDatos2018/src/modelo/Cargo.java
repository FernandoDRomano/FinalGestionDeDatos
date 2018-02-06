package modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Fernando
 */
public class Cargo {
    
    private int idCargo;
    private String cargo;
    private double basico;

    public Cargo(int idCargo, String cargo, double basico) {
        this.idCargo = idCargo;
        this.cargo = cargo;
        this.basico = basico;
    }

    public Cargo() {
    }

    public int getIdCargo() {
        return idCargo;
    }

    public void setIdCargo(int idCargo) {
        this.idCargo = idCargo;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public double getBasico() {
        return basico;
    }

    public void setBasico(double basico) {
        this.basico = basico;
    }

    @Override
    public String toString() {
        return this.getCargo();
    }
    
    
    
    //METODOS PARA LAS TRANSACCIONES A LA BASE DE DATOS
    
    public void grabarCargo(){
        try {
            Conexion conexion = new Conexion();
            String query = "insert into cargo (cargo, basico) values (?,?);";
            PreparedStatement st = conexion.getConnection().prepareStatement(query);
            st.setString(1, this.getCargo());
            st.setFloat(2, (float) this.getBasico());
            st.execute();
            System.out.println("SE GRABO EL CARGO EN LA BASE DE DATOS");
            st.close();
            conexion.desconectar();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    public void eliminarCargo(){
        try {
            Conexion conexion = new Conexion();
            String query = "delete from cargo where idcargo = " + this.getIdCargo()+ ";";
            System.out.println(query);
            PreparedStatement st = conexion.getConnection().prepareStatement(query);
            st.execute();
            System.out.println("SE ELIMINO EL CARGO DE LA BASE DE DATOS");
            st.close();
            conexion.desconectar();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    public void editarCargo(){
        try {
            Conexion conexion = new Conexion();
            String query = "update cargo set cargo ='"+ this.getCargo()+ "', basico =' " + this.getBasico() +  "' where idcargo =" + this.getIdCargo()+ ";";
            System.out.println(query);
            PreparedStatement st = conexion.getConnection().prepareStatement(query);
            st.executeUpdate();
            System.out.println("SE EDITO EL CARGO EN LA BASE DE DATOS");
            st.close();
            conexion.desconectar();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    public ResultSet listarCargo(){
        ResultSet resultado = null;
         try {
            Conexion conexion = new Conexion();
            String query = "select * from cargo;";
            PreparedStatement st = conexion.getConnection().prepareStatement(query);
            resultado = st.executeQuery();
            conexion.desconectar();
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
         
         return resultado;
    }
    
    public ResultSet buscarCargo(String cadena){
        ResultSet resultado = null;
         try {
            Conexion conexion = new Conexion();
            String query = "select * from cargo where cargo like '" + cadena + "%' or idcargo like'" + cadena + "%' ;";
            PreparedStatement st = conexion.getConnection().prepareStatement(query);
            resultado = st.executeQuery();
            conexion.desconectar();           
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
         
         return resultado;
    }

    public boolean tieneEmpleados() {
        return false;
    }

    
}
