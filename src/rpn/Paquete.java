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
class Paquete implements Comparable<Paquete> {

    int destino;
    int origen;
    double tamaño;
    int fecha_limite;
    int fecha;

    Paquete(int destino, int origen, double tamaño, int fecha_limite, int fecha) {

        this.destino = destino;
        this.origen = origen;
        this.tamaño = tamaño;
        this.fecha_limite = fecha_limite;
        this.fecha = fecha;

    }

    @Override
    public int compareTo(Paquete o) { //ordena de mayor a menor, pone al final los paquetes mas proximos a llegar a la fecha limite y con menos peso

        Integer a = 0;

        if (o.fecha_limite == this.fecha_limite) {

            if (o.tamaño > this.tamaño) {

                return 1;

            } else if (o.tamaño < this.tamaño) {

                return -1;

            } else {

                return 0;

            }
        } else {

            return o.fecha_limite - this.fecha_limite;

        }

    }

}
