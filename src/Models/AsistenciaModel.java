/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

import java.sql.Timestamp;

/**
 *
 * @author henry
 */
public class AsistenciaModel {
    private String ced_usu;
    private Timestamp fec_hor;
    private String horario;

    public AsistenciaModel(String ced_usu, Timestamp fec_hor, String horario) {
        this.ced_usu = ced_usu;
        this.fec_hor = fec_hor;
        this.horario = horario;
    }

    public AsistenciaModel() {
    }
    
    public String getCed_usu() {
        return ced_usu;
    }

    public void setCed_usu(String ced_usu) {
        this.ced_usu = ced_usu;
    }

    public Timestamp getFec_hor() {
        return fec_hor;
    }

    public void setFec_hor(Timestamp fec_hor) {
        this.fec_hor = fec_hor;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }
    
    
}
