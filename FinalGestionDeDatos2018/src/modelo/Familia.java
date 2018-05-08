package modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Fernando
 */
public class Familia {
    
    private int idFamilia;
    private int dni;
    private String apellido;
    private String nombre;
    private String vinculo;
    private String fechaNacimiento;
    private String discapacidad;
    private String escolaridad;
    private Empleado empleado;

    public Familia() {
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public String getEscolaridad() {
        return escolaridad;
    }

    public void setEscolaridad(String escolaridad) {
        this.escolaridad = escolaridad;
    }
    
    public int getIdFamilia() {
        return idFamilia;
    }

    public void setIdFamilia(int idFamilia) {
        this.idFamilia = idFamilia;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getVinculo() {
        return vinculo;
    }

    public void setVinculo(String vinculo) {
        this.vinculo = vinculo;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getDiscapacidad() {
        return discapacidad;
    }

    public void setDiscapacidad(String discapacidad) {
        this.discapacidad = discapacidad;
    }
    
    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }
    
    //METODOS PARA LAS TRANSACCIONES A LA BASE DE DATOS
    
    public void grabarFamiliar(){
        try {
            Conexion conexion = new Conexion();
            String query = "insert into familia (apellido, nombre, vinculo, fechaNacimiento, discapacidad, dni, escolaridad, empleado_idempleado) values (?,?,?,?,?,?,?,?);";
            PreparedStatement st = conexion.getConnection().prepareStatement(query);
            st.setString(1, this.getApellido());
            st.setString(2, this.getNombre());
            st.setString(3, this.getVinculo());
            st.setString(4, this.getFechaNacimiento());
            st.setString(5, this.getDiscapacidad());
            st.setInt(6, this.getDni());
            st.setString(7, this.getEscolaridad());
            st.setInt(8, this.getEmpleado().getIdEmpleado());
            st.execute();
            System.out.println("SE GRABO EL FAMILIAR EN LA BASE DE DATOS");
            st.close();
            conexion.desconectar();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    public void eliminarFamiliar(){
        try {
            Conexion conexion = new Conexion();
            String query = "delete from familia where idfamilia = " + this.getIdFamilia()+ ";";
            System.out.println(query);
            PreparedStatement st = conexion.getConnection().prepareStatement(query);
            st.execute();
            System.out.println("SE ELIMINO EL FAMILIAR DE LA BASE DE DATOS");
            st.close();
            conexion.desconectar();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    public void editarFamiliar(){
        try {
            Conexion conexion = new Conexion();
            String query = "update familia set apellido = '"+ this.getApellido()+ "', nombre = '" + this.getNombre()+ "', vinculo = '" + this.getVinculo()+ "', fechaNacimiento = '" + this.getFechaNacimiento() + "', discapacidad = '" + this.getDiscapacidad() + "', dni = '" + this.getDni() + "', escolaridad = '" + this.getEscolaridad() + "' where idfamilia =" + this.getIdFamilia()+ ";";
            System.out.println(query);
            PreparedStatement st = conexion.getConnection().prepareStatement(query);
            st.executeUpdate();
            System.out.println("SE EDITO EL FAMILIAR EN LA BASE DE DATOS");
            st.close();
            conexion.desconectar();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    public ResultSet listarFamiliar(){
        ResultSet resultado = null;
         try {
            Conexion conexion = new Conexion();
            String query = "SELECT familia.idfamilia, familia.dni, familia.nombre, familia.apellido, familia.vinculo, date_format(familia.fechaNacimiento, '%d/%m/%Y') as fechaNacimiento, familia.discapacidad, familia.escolaridad \n" +
                           "FROM familia\n" +
                           "INNER JOIN empleado ON empleado.idempleado = familia.empleado_idempleado\n" +
                           "WHERE empleado.idempleado = " + this.getEmpleado().getIdEmpleado() + ";";
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
        return "Familia{" + "idFamilia=" + idFamilia + ", apellido=" + apellido + ", nombre=" + nombre + ", vinculo=" + vinculo + ", fechaNacimiento=" + fechaNacimiento + ", discapacidad=" + discapacidad + ", empleado=" + empleado + '}';
    }

    
}
