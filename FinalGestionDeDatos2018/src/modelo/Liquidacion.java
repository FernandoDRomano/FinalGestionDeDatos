package modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Fernando
 */
public class Liquidacion {
    
    private int idLiquidacion;
    private int año;
    private int mes;
    private double totalHaberes;
    private double totalDescuentos;
    private double sueldoNeto;
    private Empleado empleado;
    private ArrayList<LineaLiquidacion> linea;

    public Liquidacion() {
        linea = new ArrayList<>();
    }

    public int getIdLiquidacion() {
        return idLiquidacion;
    }

    public void setIdLiquidacion(int idLiquidacion) {
        this.idLiquidacion = idLiquidacion;
    }

    public int getAño() {
        return año;
    }

    public void setAño(int año) {
        this.año = año;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public double getTotalHaberes() {
        return totalHaberes;
    }

    public void setTotalHaberes(double totalHaberes) {
        this.totalHaberes = totalHaberes;
    }

    public double getTotalDescuentos() {
        return totalDescuentos;
    }

    public void setTotalDescuentos(double totalDescuentos) {
        this.totalDescuentos = totalDescuentos;
    }

    public double getSueldoNeto() {
        return sueldoNeto;
    }

    public void setSueldoNeto(double sueldoNeto) {
        this.sueldoNeto = sueldoNeto;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public ArrayList<LineaLiquidacion> getLinea() {
        return linea;
    }

    public void setLinea(ArrayList<LineaLiquidacion> linea) {
        this.linea = linea;
    }
    
    public void agregarLinea(LineaLiquidacion l){
        this.getLinea().add(l);
    }
    
    public int maxId() throws SQLException{
        int id = 0;
        ResultSet resultado = null;
        Conexion conexion = new Conexion();
            
        String query = "SELECT MAX(idliquidacion) AS id FROM liquidacion;";
        PreparedStatement st = conexion.getConnection().prepareStatement(query);
        resultado = st.executeQuery();
        while(resultado.next()){
            if (resultado.getString("id").equals(null)) {
                id = 0;
            }else{
                id = Integer.parseInt(resultado.getString("id"));
            }
        }
        
        return id;
    }
    
    public void grabarLiquidacion(){
        try {            
            Conexion conexion = new Conexion();
            String query = "insert into liquidacion (año, mes, sueldoNeto, totalHaberes, totalDescuentos, empleado_idempleado) values (?,?,?,?,?,?);";
            PreparedStatement st = conexion.getConnection().prepareStatement(query);
            st.setInt(1, this.getAño());
            st.setInt(2, this.getMes());
            st.setDouble(3, this.getSueldoNeto());
            st.setDouble(4, this.getTotalHaberes());
            st.setDouble(5, this.getTotalDescuentos());
            st.setInt(6, this.getEmpleado().getIdEmpleado());
            st.execute();
            System.out.println("SE GRABO LA LIQUIDACION EN LA BASE DE DATOS");
            st.close();            
            conexion.desconectar();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    public ResultSet filtrarLiquidacionId(){
        ResultSet resultado = null;
         try {
            Conexion conexion = new Conexion();
            String query = "select empleado.idempleado, concat(empleado.apellido , ' ' , empleado.nombre) as empleado, liquidacion.idliquidacion, concat(liquidacion.año , '/' , liquidacion.mes) as periodo, liquidacion.totalHaberes, liquidacion.totalDescuentos, liquidacion.sueldoNeto\n" +
                           "from liquidacion\n" +
                           "inner join empleado on empleado.idempleado = liquidacion.empleado_idempleado\n" +
                           "where liquidacion.idliquidacion = " + this.getIdLiquidacion() + " ;";
            System.out.println(query);
            PreparedStatement st = conexion.getConnection().prepareStatement(query);
            resultado = st.executeQuery();
            conexion.desconectar();
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
         
         return resultado;
    }
    
    public ResultSet filtrarLiquidacionEmpleadoId(){
        ResultSet resultado = null;
         try {
            Conexion conexion = new Conexion();
            String query = "select liquidacion.idliquidacion, concat(liquidacion.año , '/' , liquidacion.mes) as periodo, liquidacion.totalHaberes, liquidacion.totalDescuentos, liquidacion.sueldoNeto\n" +
                           "from liquidacion\n" +
                           "inner join empleado on empleado.idempleado = liquidacion.empleado_idempleado\n" +
                           "where empleado.idempleado = " + this.getEmpleado().getIdEmpleado() + " ORDER BY periodo DESC;";
            System.out.println(query);
            PreparedStatement st = conexion.getConnection().prepareStatement(query);
            resultado = st.executeQuery();
            conexion.desconectar();
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
         
         return resultado;
    }

    public ResultSet filtrarDetalleLiquidacion(){
        ResultSet resultado = null;
         try {
            Conexion conexion = new Conexion();
            String query = "SELECT concepto.descripcion, concepto.tipoConcepto, concepto.fijo, concepto.porcentaje, concepto.monto, linea_liquidacion.cantidad, linea_liquidacion.monto as total\n" +
                           "FROM linea_liquidacion\n" +
                           "INNER JOIN concepto on concepto.idconcepto = linea_liquidacion.concepto_idconcepto\n" +
                           "INNER JOIN liquidacion on liquidacion.idliquidacion = linea_liquidacion.liquidacion_idliquidacion\n" +
                           "WHERE liquidacion.idliquidacion = " + this.getIdLiquidacion() + " ;";
            System.out.println(query);
            PreparedStatement st = conexion.getConnection().prepareStatement(query);
            resultado = st.executeQuery();
            conexion.desconectar();
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
         
         return resultado;
    }
    
    @Override
    public String toString() {
        return "Liquidacion{" + "idLiquidacion=" + idLiquidacion + ", año=" + año + ", mes=" + mes + ", totalHaberes=" + totalHaberes + ", totalDescuentos=" + totalDescuentos + ", sueldoNeto=" + sueldoNeto + ", empleado=" + empleado + '}';
    }
    
    

    
    
    
    
}
