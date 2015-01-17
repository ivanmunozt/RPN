/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rpn;

import java.util.ArrayList;

/**
 *
 * @author Adrián
 */
class Camion implements Comparable<Camion> {

    int id;
    int tiempo_espera;
    ArrayList<Paquete> carga;
    static double porcentaje_minimo;
    static double capacidad;

    Camion(int id, double capacidad, double porcentaje_minimo) {

        this.id = id;
        this.capacidad = capacidad;
        this.porcentaje_minimo = porcentaje_minimo;
        this.carga = new ArrayList<>();
        this.tiempo_espera = 0;

    }

    @Override
    public int compareTo(Camion o) { //ponemos primero los camiones que tienen mas tiempo de espera

        return o.tiempo_espera - this.tiempo_espera;

    }

}
