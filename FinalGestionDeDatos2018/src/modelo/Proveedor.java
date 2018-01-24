package modelo;

/**
 *
 * @author Fernando
 */
public class Proveedor {
    
    private int idProveedor;
    private String razonSocial;
    private String cuil;
    private String telefono;
    private Domicilio domicilio;

    public Proveedor(int idProveedor, String razonSocial, String cuil, String telefono, Domicilio domicilio) {
        this.idProveedor = idProveedor;
        this.razonSocial = razonSocial;
        this.cuil = cuil;
        this.telefono = telefono;
        this.domicilio = domicilio;
    }

    public Proveedor() {
    }

    public int getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(int idProveedor) {
        this.idProveedor = idProveedor;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getCuil() {
        return cuil;
    }

    public void setCuil(String cuil) {
        this.cuil = cuil;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Domicilio getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(Domicilio domicilio) {
        this.domicilio = domicilio;
    }
    
    
}
