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
    int limite_ejecucion;
    ArrayList<Zona> zonas;

    ///-----------------Variables para la inicializacion------------------///
    int nZonas;
    ArrayList<String> nombre_zonas;

    ///-----------------Estadisticas------------------///
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

    public Estadistica(long semilla, int nZonas, ArrayList<String> nombre_zonas, int limite_emulacion,
            ArrayList<Integer> camiones_espera, ArrayList<ArrayList<Double>> probabilidad_destinos, ArrayList<Double> media_generacion_paquetes,
            ArrayList<Double> desvTip_generacion_paquetes, ArrayList<Double> media_tam, ArrayList<Double> desvTip_tam,
            ArrayList<Double> media_tiempo_limite, ArrayList<Double> desvTip_tiempo_limite, ArrayList<Integer> max_tiempo_limite,
            double capacidad_camiones, double porcentaje_minimo) {

        this.ran = new Random(semilla);
        this.dia = 0;
        this.limite_ejecucion = limite_emulacion;
        this.nZonas = nZonas;
        this.nombre_zonas = nombre_zonas;

        //Inicializacion de objetos
        Camion.capacidad = capacidad_camiones;
        Camion.porcentaje_minimo = capacidad_camiones;

        ArrayList<ArrayList<Camion>> camiones = new ArrayList<>();
        int idCam = 0;

        for (Integer i : camiones_espera) {

            ArrayList<Camion> auxCam = new ArrayList<>();

            for (int j = 0; j < i; j++) {

                auxCam.add(new Camion(idCam));
                idCam++;

            }

            camiones.add(auxCam);

        }

        zonas = new ArrayList<>();
        
        for (int i = 0; i < nZonas; i++) {

            zonas.add(new Zona(i, nZonas, camiones.get(i), probabilidad_destinos.get(i), this,
                    media_generacion_paquetes.get(i), desvTip_generacion_paquetes.get(i),
                    media_tam.get(i), desvTip_tam.get(i), media_tiempo_limite.get(i), desvTip_tiempo_limite.get(i), max_tiempo_limite.get(i)));

        }

    }

    double getNormal(double media, double desTip) {

        return ran.nextGaussian() * desTip + media; //Para convertir a una distribucion normal de media y desv tipica distinta de 0 y 1 respectivamente

    }

    double getNextDouble() {

        return ran.nextDouble();

    }

}
