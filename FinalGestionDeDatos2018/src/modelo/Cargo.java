package modelo;

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
    
    
    
}
