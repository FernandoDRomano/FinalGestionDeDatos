package modelo;

import java.util.Date;

/**
 *
 * @author Fernando
 */
public class Familia {
    
    private int idFamilia;
    private String apellido;
    private String nombre;
    private String vinculo;
    private Date fechaNacimiento;
    private String discapacidad;
    private Empleado empleado;

    public Familia(int idFamilia, String apellido, String nombre, String vinculo, Date fechaNacimiento, String discapacidad, Empleado empleado) {
        this.idFamilia = idFamilia;
        this.apellido = apellido;
        this.nombre = nombre;
        this.vinculo = vinculo;
        this.fechaNacimiento = fechaNacimiento;
        this.discapacidad = discapacidad;
        this.empleado = empleado;
    }

    public Familia() {
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

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
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
    
    
    
}
