/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pojos;

/**
 *
 * @author javib
 */
public class Nino {

    private int id_nino;
    private String nombre_nino;
    private String apellidos_nino;
    private int edad_nino;
    private String email_nino;
    private int id_rey;

    public Nino(int id_nino, String nombre_nino, String apellidos_nino, int edad_nino, String email_nino,int id_rey) {
        this.id_nino = id_nino;
        this.nombre_nino = nombre_nino;
        this.apellidos_nino = apellidos_nino;
        this.edad_nino = edad_nino;
        this.email_nino = email_nino;
        this.id_rey = id_rey;
    }
    
    public Nino() {
    }

    public int getId_nino() {
        return id_nino;
    }

    public void setId_nino(int id_nino) {
        this.id_nino = id_nino;
    }

    public String getNombre_nino() {
        return nombre_nino;
    }

    public void setNombre_nino(String nombre_nino) {
        this.nombre_nino = nombre_nino;
    }

    public String getApellidos_nino() {
        return apellidos_nino;
    }

    public void setApellidos_nino(String apellidos_nino) {
        this.apellidos_nino = apellidos_nino;
    }

    public String getEmail_nino() {
        return email_nino;
    }

    public void setEmail_nino(String email_nino) {
        this.email_nino = email_nino;
    }

    public int getEdad_nino() {
        return edad_nino;
    }

    public void setEdad_nino(int edad_nino) {
        this.edad_nino = edad_nino;
    }

    public int getId_rey() {
        return id_rey;
    }

    public void setId_rey(int id_rey) {
        this.id_rey = id_rey;
    }
    

    @Override
    public String toString() {
        return this.nombre_nino +' '+apellidos_nino;
    }

}
