package modelo;

import java.sql.Date;

/**
 *
 * @author Fernando
 */
public class Empleado {
 
    private int idEmpleado;
    private String nombre;
    private int dni;
    private String telefono;
    private Date fechaIngreso;
    private char sexo;
    private String estado;
    private Domicilio domicilio;
    private Cargo cargo;
    private Usuario usuario;

    public Empleado(int idEmpleado, String nombre, int dni, String telefono, Date fechaIngreso, char sexo, String estado, Domicilio domicilio, Cargo cargo, Usuario usuario) {
        this.idEmpleado = idEmpleado;
        this.nombre = nombre;
        this.dni = dni;
        this.telefono = telefono;
        this.fechaIngreso = fechaIngreso;
        this.sexo = sexo;
        this.estado = estado;
        this.domicilio = domicilio;
        this.cargo = cargo;
        this.usuario = usuario;
    }

    public Empleado() {
    }

    public int getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public char getSexo() {
        return sexo;
    }

    public void setSexo(char sexo) {
        this.sexo = sexo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Domicilio getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(Domicilio domicilio) {
        this.domicilio = domicilio;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    
        
    
}
