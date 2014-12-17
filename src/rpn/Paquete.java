/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rpn;

/**
 *
 * @author Adrián
 */
public class Paquete {
    
    private String destino;
    private String origen;
    private float tamaño;
    private int fecha_limite; 
    private int fecha;

    public Paquete(String destino, String origen, float tamaño, int fecha_limite, int fecha) {
        this.destino = destino;
        this.origen = origen;
        this.tamaño = tamaño;
        this.fecha_limite = fecha_limite;
        this.fecha = fecha;
    }

    public String getDestino() {
        return destino;
    }

    public String getOrigen() {
        return origen;
    }

    public int getFecha_limite() {
        return fecha_limite;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public int getFecha() {
        return fecha;
    }

    public float getTamaño() {
        return tamaño;
    }
    
    
    
}
