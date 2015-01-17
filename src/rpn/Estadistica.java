/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rpn;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 *
 * @author Adrián
 */
public class Estadistica {

    int dia;
    private ArrayList<ArrayList<ArrayList<Paquete>>> paquetes_espera;
    private ArrayList<ArrayList<Camion>> camiones;

    // Clave con formato 'origen-destino'
    private HashMap<String, Long> paquetes_enviados;

    //Tiempo de llegada acumulado => dia maximo entrega - dia entrega // Negativo si se entrega tarde...
    private HashMap<String, Long> tiempo_llegada_acum;

    //Tiempo de espera acumulado => dia salida zona - dia creacion paquete
    private HashMap<String, Long> tiempo_espera_acum;

    //Tiempo ocioso de camiones por zona //Cuando un camion deja una zona si ha pasado un dia ocioso se añade a la lista de esa zona el numero de dias ociosos
    private ArrayList<ArrayList<Integer>> tiempo_ocioso_acumulado;

    //Camiones enviados por 'origen-destino'
    private HashMap<String, Integer> camiones_enviados;

    //Ocupacion de los camiones enviados (porcentaje)
    private HashMap<String, ArrayList<Integer>> ocupacion_camiones;

    //Paquetes retrasados
    private final Random ran;

    public Estadistica(long semilla) {

        ran = new Random(semilla);

    }

    double getNormal(double media, double desTip) {

        return ran.nextGaussian() * desTip + media; //Para convertir a una distribucion normal de media y desv tipica distinta de 0 y 1 respectivamente

    }

    double getNextDouble() {

        return ran.nextDouble();

    }

}
