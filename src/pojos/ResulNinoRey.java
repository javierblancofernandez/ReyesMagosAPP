/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pojos;

import java.util.Date;

/**
 *
 * @author javib
 */
public class ResulNinoRey {
    int idpedido;
    Date fecha;
    String nombreNino;
    String nombreRey;
    String emailNino;

    public ResulNinoRey(int idpedido, String nombreNino, String nombreRey, String emailNino) {
        this.idpedido = idpedido;
        this.nombreNino = nombreNino;
        this.nombreRey = nombreRey;
        this.emailNino = emailNino;
    }

    public int getIdpedido() {
        return idpedido;
    }
    
    public void setIdpedido(int idpedido) {
        this.idpedido = idpedido;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getNombreNino() {
        return nombreNino;
    }

    public void setNombreNino(String nombreNino) {
        this.nombreNino = nombreNino;
    }

    public String getNombreRey() {
        return nombreRey;
    }

    public void setNombreRey(String nombreRey) {
        this.nombreRey = nombreRey;
    }
    
    public String getEmailNino() {
        return emailNino;
    }

    public void setEmailNino(String emailNino) {
        this.emailNino = emailNino;
    }
    
}
