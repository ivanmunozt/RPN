/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rpn;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Adrián
 */
public class Estadistica {

    private int dia;
    private HashMap<String,HashMap<String, Paquete>> paquetes_espera;
    private HashMap<String,ArrayList<Camion>> camiones;
    
    // Clave con formato 'origen-destino'
    private HashMap<String,Long> paquetes_enviados;
    
    //Tiempo de llegada acumulado
    private HashMap<String,Long> tiempo_llegada_acum;
    
    //Tiempo de espera acumulado
    private HashMap<String,Long> tiempo_espera_acum;
    
    
    
}
