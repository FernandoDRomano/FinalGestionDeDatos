package modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Fernando
 */
public class Empleado {
 
    private int idEmpleado;
    private String apellido;
    private String nombre;
    private int dni;
    private int telefono;
    private String fechaIngreso;
    private String sexo;
    private String estado;
    private Domicilio domicilio;
    private Cargo cargo;
    private Usuario usuario;

    public Empleado(int idEmpleado, String nombre, int dni, int telefono, String fechaIngreso, String sexo, String estado, Domicilio domicilio, Cargo cargo, Usuario usuario) {
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

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
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

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public String getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(String fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
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

    @Override
    public String toString() {
        return "Empleado{" + "idEmpleado=" + idEmpleado + ", apellido=" + apellido + ", nombre=" + nombre + ", dni=" + dni + ", telefono=" + telefono + ", fechaIngreso=" + fechaIngreso + ", sexo=" + sexo + ", estado=" + estado + ", domicilio=" + domicilio + ", cargo=" + cargo + ", usuario=" + usuario + '}';
    }

    
    
    
    //METODOS PARA LAS TRANSACCIONES A LA BASE DE DATOS
    
    public void grabarEmpleado(){
        try {
            Conexion conexion = new Conexion();
            String query = "insert into empleado (apellido, nombre, dni, telefono, fechaIngreso, sexo, estado, domicilio_iddomicilio, cargo_idcargo, usuario_idusuario) values (?,?,?,?,?,?,?,?,?,?);";
            PreparedStatement st = conexion.getConnection().prepareStatement(query);
            st.setString(1, this.getApellido());
            st.setString(2, this.getNombre());
            st.setInt(3, this.getDni());
            st.setInt(4, this.getTelefono());
            st.setString(5, this.getFechaIngreso());
            st.setString(6, this.getSexo());
            st.setString(7, this.getEstado());
            st.setInt(8, this.getDomicilio().getIdDomicilio());
            st.setInt(9, this.getCargo().getIdCargo());
            st.setInt(10, this.getUsuario().getIdUsuario());

            st.execute();
            System.out.println("SE GRABO EL EMPLEADO EN LA BASE DE DATOS");
            st.close();
            conexion.desconectar();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    public ResultSet listarEmpleado(){
        ResultSet resultado = null;
         try {
            Conexion conexion = new Conexion();
             String query = "select empleado.idempleado, empleado.apellido, empleado.nombre, empleado.dni, empleado.telefono, date_format(empleado.fechaIngreso, '%d/%m/%Y') as fechaIngreso, TIMESTAMPDIFF(YEAR, empleado.fechaIngreso, CURDATE()) As antiguedad, empleado.sexo, empleado.estado, \n" +
                           "domicilio.iddomicilio, domicilio.calle, domicilio.numero, domicilio.piso, domicilio.departamento, concat(domicilio.calle, ' ',domicilio.numero) as domicilio, \n" +
                           "usuario.idusuario, usuario.nombreUsuario as usuario,\n" +
                           "perfil.idperfil, perfil.nombre as perfil, \n" +
                           "cargo.idcargo, cargo.cargo, cargo.basico\n" +
                           "from empleado\n" +
                           "inner join domicilio on domicilio.iddomicilio = empleado.domicilio_iddomicilio\n" +
                           "inner join cargo on cargo.idcargo = empleado.cargo_idcargo\n" +
                           "inner join usuario on usuario.idusuario = empleado.usuario_idusuario\n" +
                           "inner join perfil on perfil.idperfil = usuario.perfil_idperfil;" ;
            PreparedStatement st = conexion.getConnection().prepareStatement(query);
            resultado = st.executeQuery();
            conexion.desconectar();
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
         
         return resultado;
    }
    
    public ResultSet listarEmpleadoActivo(){
        ResultSet resultado = null;
         try {
            Conexion conexion = new Conexion();
             String query = "select empleado.idempleado, empleado.apellido, empleado.nombre, empleado.dni, empleado.telefono, empleado.fechaIngreso, TIMESTAMPDIFF(YEAR, empleado.fechaIngreso, CURDATE()) As antiguedad, empleado.sexo, empleado.estado, \n" +
                           "domicilio.iddomicilio, domicilio.calle, domicilio.numero, domicilio.piso, domicilio.departamento, concat(domicilio.calle, ' ',domicilio.numero) as domicilio, \n" +
                           "usuario.idusuario, usuario.nombreUsuario as usuario,\n" +
                           "perfil.idperfil, perfil.nombre as perfil, \n" +
                           "cargo.idcargo, cargo.cargo, cargo.basico\n" +
                           "from empleado\n" +
                           "inner join domicilio on domicilio.iddomicilio = empleado.domicilio_iddomicilio\n" +
                           "inner join cargo on cargo.idcargo = empleado.cargo_idcargo\n" +
                           "inner join usuario on usuario.idusuario = empleado.usuario_idusuario\n" +
                           "inner join perfil on perfil.idperfil = usuario.perfil_idperfil\n" +
                           "where empleado.estado = 'Activo' ";
            PreparedStatement st = conexion.getConnection().prepareStatement(query);
            resultado = st.executeQuery();
            conexion.desconectar();
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
         
         return resultado;
    }
    
    public ResultSet listarEmpleadoActivo(String cadena){
        ResultSet resultado = null;
         try {
            Conexion conexion = new Conexion();
             String query = "select empleado.idempleado, empleado.apellido, empleado.nombre, empleado.dni, empleado.telefono, empleado.fechaIngreso, TIMESTAMPDIFF(YEAR, empleado.fechaIngreso, CURDATE()) As antiguedad, empleado.sexo, empleado.estado, \n" +
                           "domicilio.iddomicilio, domicilio.calle, domicilio.numero, domicilio.piso, domicilio.departamento, concat(domicilio.calle, ' ',domicilio.numero) as domicilio, \n" +
                           "usuario.idusuario, usuario.nombreUsuario as usuario,\n" +
                           "perfil.idperfil, perfil.nombre as perfil, \n" +
                           "cargo.idcargo, cargo.cargo, cargo.basico\n" +
                           "from empleado\n" +
                           "inner join domicilio on domicilio.iddomicilio = empleado.domicilio_iddomicilio\n" +
                           "inner join cargo on cargo.idcargo = empleado.cargo_idcargo\n" +
                           "inner join usuario on usuario.idusuario = empleado.usuario_idusuario\n" +
                           "inner join perfil on perfil.idperfil = usuario.perfil_idperfil\n" +
                           "where empleado.apellido like '" + cadena + "%' or empleado.dni like '" + cadena + "%' or empleado.idempleado like '" + cadena + "%' and empleado.estado = 'Activo';";
            PreparedStatement st = conexion.getConnection().prepareStatement(query);
            resultado = st.executeQuery();
            conexion.desconectar();
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
         
         return resultado;
    }
    
    public ResultSet filtrarEmpleado(String cadena){
        ResultSet resultado = null;
         try {
            Conexion conexion = new Conexion();
            String query = "select empleado.idempleado, empleado.apellido, empleado.nombre, empleado.dni, empleado.telefono, empleado.fechaIngreso, empleado.sexo, empleado.estado, concat(domicilio.calle, ' ',domicilio.numero) as domicilio , usuario.nombreUsuario as usuario, perfil.nombre as perfil, cargo.cargo as cargo, cargo.basico \n" +
                           "from empleado\n" +
                           "inner join domicilio on domicilio.iddomicilio = empleado.domicilio_iddomicilio\n" +
                           "inner join cargo on cargo.idcargo = empleado.cargo_idcargo\n" +
                           "inner join usuario on usuario.idusuario = empleado.usuario_idusuario\n" +
                           "inner join perfil on perfil.idperfil = usuario.perfil_idperfil\n" +
                           "where empleado.apellido like '" + cadena + "%' or empleado.dni like '" + cadena + "%' or empleado.idempleado like '" + cadena + "%' ;";
            System.out.println(query);
            PreparedStatement st = conexion.getConnection().prepareStatement(query);
            resultado = st.executeQuery();
            conexion.desconectar();
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
         
         return resultado;
    }
    
    public ResultSet buscarEmpleadoId() {
        ResultSet resultado = null;
         try {
            Conexion conexion = new Conexion();
            String query = "select empleado.idempleado, empleado.apellido, empleado.nombre, empleado.dni, empleado.telefono, empleado.fechaIngreso, TIMESTAMPDIFF(YEAR, empleado.fechaIngreso, CURDATE()) As antiguedad, empleado.sexo, empleado.estado, \n" +
                           "domicilio.iddomicilio, domicilio.calle, domicilio.numero, domicilio.piso, domicilio.departamento, \n" +
                           "usuario.idusuario, usuario.nombreUsuario,\n" +
                           "perfil.idperfil, perfil.nombre as perfil, \n" +
                           "cargo.idcargo, cargo.cargo, cargo.basico,\n" +
                           "localidad.idlocalidad, localidad.nombre as localidad, localidad.codigoPostal,\n" +
                           "provincia.idprovincia, provincia.nombre as provincia\n" +
                           "from empleado\n" +
                           "inner join domicilio on domicilio.iddomicilio = empleado.domicilio_iddomicilio\n" +
                           "inner join cargo on cargo.idcargo = empleado.cargo_idcargo\n" +
                           "inner join usuario on usuario.idusuario = empleado.usuario_idusuario\n" +
                           "inner join perfil on perfil.idperfil = usuario.perfil_idperfil\n" +
                           "inner join localidad on localidad.idlocalidad = domicilio.localidad_idlocalidad\n" +
                           "inner join provincia on provincia.idprovincia = localidad.provincia_idprovincia\n" +
                           "where idempleado =" + this.getIdEmpleado();
            PreparedStatement st = conexion.getConnection().prepareStatement(query);
            resultado = st.executeQuery();
            conexion.desconectar();
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
         
         return resultado;
    }
    
    public ResultSet buscarEmpleado(String cadena) {
        ResultSet resultado = null;
         try {
            Conexion conexion = new Conexion();
            String query = "select empleado.idempleado, empleado.apellido, empleado.nombre, empleado.dni, empleado.telefono, empleado.fechaIngreso, TIMESTAMPDIFF(YEAR, empleado.fechaIngreso, CURDATE()) As antiguedad, empleado.sexo, empleado.estado, \n" +
                           "domicilio.iddomicilio, concat(domicilio.calle, ' ' ,domicilio.numero) as domicilio, domicilio.piso, domicilio.departamento, \n" +
                           "usuario.idusuario, usuario.nombreUsuario as usuario,\n" +
                           "perfil.idperfil, perfil.nombre as perfil, \n" +
                           "cargo.idcargo, cargo.cargo as cargo, cargo.basico\n" +
                           "from empleado\n" +
                           "inner join domicilio on domicilio.iddomicilio = empleado.domicilio_iddomicilio\n" +
                           "inner join cargo on cargo.idcargo = empleado.cargo_idcargo\n" +
                           "inner join usuario on usuario.idusuario = empleado.usuario_idusuario\n" +
                           "inner join perfil on perfil.idperfil = usuario.perfil_idperfil\n" +
                           "where empleado.apellido like '" + cadena + "%' or empleado.estado like '" + cadena + "%' or empleado.idempleado like '" + cadena + "%' ;";
            PreparedStatement st = conexion.getConnection().prepareStatement(query);
            resultado = st.executeQuery();
            conexion.desconectar();
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
         
         return resultado;
    }
    
    public ResultSet calcularAntiguedad(String fecha) {
        ResultSet resultado = null;
         try {
            Conexion conexion = new Conexion();
            String query = "select empleado.idempleado, TIMESTAMPDIFF(YEAR, empleado.fechaIngreso, '" + fecha + "') As antiguedad \n" +
                           "from empleado\n" +
                           "where idempleado =" + this.getIdEmpleado();
            System.out.println(query);
            PreparedStatement st = conexion.getConnection().prepareStatement(query);
            resultado = st.executeQuery();
            conexion.desconectar();
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
         
         return resultado;
    }
    
    public void eliminarEmpleado(){
        try {
            Conexion conexion = new Conexion();
            String query = "delete from empleado where idempleado = " + this.getIdEmpleado()+ ";";
            System.out.println(query);
            PreparedStatement st = conexion.getConnection().prepareStatement(query);
            st.execute();
            System.out.println("SE ELIMINO EL EMPLEADO DE LA BASE DE DATOS");
            st.close();
            conexion.desconectar();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    public void eliminarGrupoFamiliar(){
        try {
            Conexion conexion = new Conexion();
            String query = "delete from familia where empleado_idempleado = " + this.getIdEmpleado()+ ";";
            System.out.println(query);
            PreparedStatement st = conexion.getConnection().prepareStatement(query);
            st.execute();
            System.out.println("SE ELIMINO EL GRUPO FAMILIAR DE LA BASE DE DATOS");
            st.close();
            conexion.desconectar();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    public void editarEmpleado(){
        try {
            Conexion conexion = new Conexion();
            String query = "update empleado set apellido ='"+ this.getApellido()+ "', nombre = '" + this.getNombre() + "', dni = '" + this.getDni() +
                            "', telefono = '" + this.getTelefono() + "', sexo = '"+ this.getSexo()+"', estado = '" + this.getEstado() + 
                            "', cargo_idcargo = '" + this.getCargo().getIdCargo() + "', fechaIngreso = '" + this.getFechaIngreso() +  "' where idempleado =" + this.getIdEmpleado()+ ";";
            
            System.out.println(query);
            PreparedStatement st = conexion.getConnection().prepareStatement(query);
            st.executeUpdate();
            System.out.println("SE EDITO EL EMPLEADO EN LA BASE DE DATOS");
            st.close();
            conexion.desconectar();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    public void borradoLogicoEmpleado(){
        try {
            Conexion conexion = new Conexion();
            String query = "update empleado set estado = 'Inactivo' where idempleado =" + this.getIdEmpleado()+ ";";
            
            System.out.println(query);
            PreparedStatement st = conexion.getConnection().prepareStatement(query);
            st.executeUpdate();
            System.out.println("SE EDITO EL EMPLEADO EN LA BASE DE DATOS");
            st.close();
            conexion.desconectar();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public boolean tieneVentaCompraLiquidacion() {
        boolean bandera = false;
        ResultSet v = null;
        ResultSet p = null;
        ResultSet l = null;
        int resultadoV = 0;
        int resultadoP = 0;
        int resultadoL = 0;
        try {
            Conexion conexion1 = new Conexion();
            String queryVenta = "select count(*) as resultado from venta where venta.empleado_idempleado = " + this.getIdEmpleado()+ " ;";
            PreparedStatement st = conexion1.getConnection().prepareStatement(queryVenta);
            System.out.println(queryVenta);
            v = st.executeQuery();
            conexion1.desconectar();
            
            Conexion conexion2 = new Conexion();
            String queryPedido = "select count(*) as resultado from compra where compra.empleado_idempleado = " + this.getIdEmpleado()+ " ;";
            PreparedStatement st1 = conexion2.getConnection().prepareStatement(queryPedido);
            System.out.println(queryPedido);
            p = st1.executeQuery();
            conexion2.desconectar();
            
            Conexion conexion3 = new Conexion();
            String queryLiquidacion = "select count(*) as resultado from liquidacion where liquidacion.empleado_idempleado = " + this.getIdEmpleado()+ " ;";
            PreparedStatement st2 = conexion3.getConnection().prepareStatement(queryLiquidacion);
            System.out.println(queryLiquidacion);
            l = st2.executeQuery();
            conexion3.desconectar();
            
            //VERIFICO QUE NO TENGA LINEAS DE VENTA-COMPRA ASOCIADOS AL PRODUCTO
            while (v.next()) {                
                resultadoV = Integer.valueOf(v.getString("resultado"));
                System.out.println("VENTAS: " + resultadoV);
            }
//            
            while (p.next()) {                
                resultadoP = Integer.valueOf(p.getString("resultado"));
                System.out.println("COMPRAS: " + resultadoP);
            }
            
            while (l.next()) {                
                resultadoL = Integer.valueOf(l.getString("resultado"));
                System.out.println("LIQUIDACIONES: " + resultadoL);
            }
            
            if (resultadoV > 0) {
                bandera = true;
            }
            
            if (resultadoP > 0) {
                bandera = true;
            }
            
            if (resultadoL > 0) {
                bandera = true;
            }
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        
        return bandera;
    }

    
}
