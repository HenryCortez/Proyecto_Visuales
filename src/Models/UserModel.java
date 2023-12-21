
package Models;

public class UserModel {
    private String cedula;
    private String nombre;
    private String apellido;
    private String password;
    private String tipo;
    private Double sueldo_actual;
    private int ID;
    
    
    public UserModel(){}
    
    public UserModel(String cedula, String nombre, String apellido, String password, String tipo) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.password = password;
        this.tipo = tipo;
    }
    
    public UserModel(String cedula, int ID) {
        this.cedula = cedula;
        this.ID = ID;
    }
    
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Double getSueldo_actual() {
        return sueldo_actual;
    }

    public void setSueldo_actual(Double sueldo_actual) {
        this.sueldo_actual = sueldo_actual;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
   
    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
    
}
