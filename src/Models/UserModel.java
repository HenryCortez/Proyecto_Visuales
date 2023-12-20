
package Models;

public class UserModel {
    private String cedula;
    private String nombre;
    private String apellido;
    private String fec_nac;
    private String telefono;
    private int ID;
    
    public UserModel(){}
    
    public UserModel(String cedula, String nombre, String apellido, String fec_nac, String telefono) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fec_nac = fec_nac;
        this.telefono = telefono;
    }

    public UserModel(String cedula, int ID) {
        this.cedula = cedula;
        this.ID = ID;
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

    public String getFec_nac() {
        return fec_nac;
    }

    public void setFec_nac(String fec_nac) {
        this.fec_nac = fec_nac;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    
}
