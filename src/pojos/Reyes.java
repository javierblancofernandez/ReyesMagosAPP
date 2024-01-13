/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pojos;

/**
 *
 * @author javib
 */
public class Reyes {
    private int id_rey;
    private String nombre_rey;
    private Long foto_rey;
    
    public Reyes(int id_rey, String nombre_rey, Long foto_rey) {
        this.id_rey = id_rey;
        this.nombre_rey = nombre_rey;
        this.foto_rey = foto_rey;
    }
    
    public int getId_rey() {
        return id_rey;
    }

    public void setId_rey(int id_rey) {
        this.id_rey = id_rey;
    }

    public String getNombre_rey() {
        return nombre_rey;
    }

    public void setNombre_rey(String nombre_rey) {
        this.nombre_rey = nombre_rey;
    }

    public Long getFoto_rey() {
        return foto_rey;
    }

    public void setFoto_rey(Long foto_rey) {
        this.foto_rey = foto_rey;
    }

    @Override
    public String toString() {
        return nombre_rey;
    }
    
    
}
