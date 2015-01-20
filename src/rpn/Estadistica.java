/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rpn;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
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
    private HashMap<String, ArrayList<Integer>> tiempo_llegada_acum;

    //Tiempo de espera acumulado => dia salida zona - dia creacion paquete
    private HashMap<String, ArrayList<Integer>> tiempo_espera_acum;

    //Tiempo ocioso de camiones por zona //Cuando un camion deja una zona si ha pasado un dia ocioso se añade a la lista de esa zona el numero de dias ociosos
    private ArrayList<ArrayList<Integer>> tiempo_ocioso_acumulado;

    //Camiones enviados por 'origen-destino'
    private HashMap<String, Integer> camiones_enviados;

    //Ocupacion de los camiones enviados (porcentaje)
    private HashMap<String, ArrayList<Double>> ocupacion_camiones;

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
        paquetes_enviados = new HashMap<>();
        tiempo_llegada_acum = new HashMap<>();
        tiempo_espera_acum = new HashMap<>();
        tiempo_ocioso_acumulado = new ArrayList<>();
        camiones_enviados = new HashMap<>();
        ocupacion_camiones = new HashMap<>();

        for (int i = 0; i < nZonas; i++) {

            tiempo_ocioso_acumulado.add(new ArrayList<>());

        }

        for (int i = 0; i < nZonas; i++) {

            for (int j = 0; j < nZonas; j++) {

                if (i != j) {

                    String s = i + "-" + j;

                    paquetes_enviados.put(s, (long) 0);
                    camiones_enviados.put(s, 0);
                    tiempo_llegada_acum.put(s, new ArrayList<>());
                    tiempo_espera_acum.put(s, new ArrayList<>());
                    ocupacion_camiones.put(s, new ArrayList<>());

                }
            }
        }
    }

    public void reanudarSimulacion(int dias) {

        limite_ejecucion += dias;
        iniciarSimulacion();

    }

    public void iniciarSimulacion() {

        for (int i = dia; i < limite_ejecucion; i++) {

            this.simularDia();

        }

    }

    private void simularDia() {

        for (int i = 0; i < nZonas; i++) {

            zonas.get(i).generacion_paquetes();

        }

        dia++;

        for (int i = 0; i < nZonas; i++) {

            ArrayList<Camion> pendientes = zonas.get(i).enviar_camiones();

            for (int j = 0; j < pendientes.size(); j++) {

                Camion camion = pendientes.get(j);
                String s = i + "-" + camion.destino;

                camiones_enviados.put(s, camiones_enviados.get(s) + 1);
                tiempo_ocioso_acumulado.get(i).add(camion.tiempo_espera);

                int nPaq = camion.carga.size();
                ArrayList<Paquete> carga = camion.carga;
                double cargaTot = 0;

                for (int k = 0; k < nPaq; k++) {

                    cargaTot += carga.get(k).tamaño;

                    tiempo_llegada_acum.get(s).add(carga.get(k).fecha_limite - dia);
                    tiempo_espera_acum.get(s).add((dia - 1) - carga.get(k).fecha);

                }

                ocupacion_camiones.get(s).add((cargaTot * 100) / Camion.capacidad);
                paquetes_enviados.put(s, paquetes_enviados.get(s) + nPaq);

                zonas.get(camion.destino).llegada_camion(camion);

            }

        }

    }

    double getNormal(double media, double desTip) {

        return ran.nextGaussian() * desTip + media; //Para convertir a una distribucion normal de media y desv tipica distinta de 0 y 1 respectivamente

    }

    double getNextDouble() {

        return ran.nextDouble();

    }

    private double mediaInt(ArrayList<Integer> lista) {

        if (lista.isEmpty()) {

            return -1.0;

        }

        double ret = 0;

        for (int i = 0; i < lista.size(); i++) {

            ret += lista.get(i);

        }

        return ret / lista.size();

    }

    private double mediaDouble(ArrayList<Double> lista) {

        if (lista.isEmpty()) {

            return -1.0;

        }

        double ret = 0;

        for (int i = 0; i < lista.size(); i++) {

            ret += lista.get(i);

        }

        return ret / lista.size();

    }

    private double desvTipInt(ArrayList<Integer> lista, double media) {

        double ret = 0;

        for (int i = 0; i < lista.size(); i++) {

            ret += Math.pow(lista.get(i), 2);

        }

        ret /= lista.size();

        ret -= Math.pow(media, 2);

        return Math.sqrt(ret);

    }

    private double desvTipDouble(ArrayList<Double> lista, double media) {

        double ret = 0;

        for (int i = 0; i < lista.size(); i++) {

            ret += Math.pow(lista.get(i), 2);

        }

        ret /= lista.size();

        ret -= Math.pow(media, 2);

        return Math.sqrt(ret);

    }

    private int getTardios(int orig, int dest) {

        int ret = 0;
        String s;
        ArrayList<Integer> aux;

        if (dest < 0) {

            if (orig < 0) {

                for (int i = 0; i < nZonas; i++) {

                    for (int j = 0; j < nZonas; j++) {

                        if (i != j) {

                            s = i + "-" + j;
                            aux = tiempo_llegada_acum.get(s);

                            for (int k = 0; k < aux.size(); k++) {

                                if (aux.get(k) < 0) {

                                    ret++;

                                }

                            }

                        }
                    }

                }

            } else {
                for (int i = 0; i < nZonas; i++) {

                    if (i != orig) {

                        s = orig + "-" + i;
                        aux = tiempo_llegada_acum.get(s);

                        for (int j = 0; j < aux.size(); j++) {

                            if (aux.get(j) < 0) {

                                ret++;

                            }

                        }

                    }

                }
            }

        } else {

            s = orig + "-" + dest;
            aux = tiempo_llegada_acum.get(s);

            for (int j = 0; j < aux.size(); j++) {

                if (aux.get(j) < 0) {

                    ret++;

                }

            }

        }

        return ret;

    }

    @Override
    public String toString() {

        int auxi, mini, maxi;
        long auxl;
        double auxd, media, desvTip, mind, maxd;
        String s;
        String ret = "Dias pasados:\t" + dia + "\n";

        /////////----------------------------Global
        ret += "\n\033[1mEstadisticas Globales:\033[0m\n\n";

        auxl = 0;

        for (Long l : paquetes_enviados.values()) {

            auxl += l;

        }
        ret += "Paquetes enviados:\t" + auxl + "\n";

        int tar = getTardios(-1, -1);
        ret += "Paquetes Tardíos:\t" + tar + "\n";

        ret += "Paquetes Tardíos %:\t" + (tar * 100.0) / auxl + "\n";

        auxl = 0;

        for (Integer in : camiones_enviados.values()) {

            auxl += in;

        }
        ret += "Camiones enviados:\t" + auxl + "\n";

        ret += "Estadisticas:\tmin\tmax\tmedia\tdestip\n";

        ArrayList<Integer> toat = new ArrayList<>();
        for (int i = 0; i < nZonas; i++) {

            toat.addAll(tiempo_ocioso_acumulado.get(i));

        }

        media = mediaInt(toat);
        desvTip = desvTipInt(toat, media);
        mini = Collections.min(toat);
        maxi = Collections.max(toat);
        ret += "Dias Ocio Cam:\t" + mini + "\t" + maxi + "\t" + media + "\t" + desvTip + "\n";

        ArrayList<Double> tot_ocup = new ArrayList<>();

        for (ArrayList<Double> arDo : ocupacion_camiones.values()) {

            tot_ocup.addAll(arDo);

        }

        media = mediaDouble(tot_ocup);
        desvTip = desvTipDouble(tot_ocup, media);
        mind = Collections.min(tot_ocup);
        maxd = Collections.max(tot_ocup);
        ret += "Ocupacion Cam:\t" + mind + "\t" + maxd + "\t" + media + "\t" + desvTip + "\n";

        ArrayList<Integer> tot_esp = new ArrayList<>();

        for (ArrayList<Integer> arIn : tiempo_espera_acum.values()) {

            tot_esp.addAll(arIn);

        }

        media = mediaInt(tot_esp);
        desvTip = desvTipInt(tot_esp, media);
        mini = Collections.min(tot_esp);
        maxi = Collections.max(tot_esp);
        ret += "Espera Paq:\t" + mini + "\t" + maxi + "\t" + media + "\t" + desvTip + "\n";

        ArrayList<Integer> tot_lleg = new ArrayList<>();

        for (ArrayList<Integer> arIn : tiempo_llegada_acum.values()) {

            tot_lleg.addAll(arIn);

        }

        media = mediaInt(tot_lleg);
        desvTip = desvTipInt(tot_lleg, media);
        mini = Collections.min(tot_lleg);
        maxi = Collections.max(tot_lleg);
        ret += "Llegada Paq:\t" + mini + "\t" + maxi + "\t" + media + "\t" + desvTip + "\n";

        /////////----------------------------Zonas
        ret += "\n\033[1mEstadisticas por Zona:\033[0m\n\n";

        for (int i = 0; i < nZonas; i++) {

            ret += "\033[1m" + nombre_zonas.get(i) + "\033[0m\n";

            auxl = 0;

            for (int j = 0; j < nZonas; j++) {

                if (i != j) {
                    s = i + "-" + j;

                    auxl += paquetes_enviados.get(s);
                }
            }

            ret += "Paquetes enviados:\t" + auxl + "\n";

            tar = getTardios(i, -1);
            ret += "Paquetes Tardíos:\t" + tar + "\n";

            ret += "Paquetes Tardíos %:\t" + (tar * 100.0) / auxl + "\n";

            auxl = 0;

            for (int j = 0; j < nZonas; j++) {

                if (i != j) {
                    s = i + "-" + j;

                    auxl += camiones_enviados.get(s);
                }
            }

            ret += "Camiones enviados:\t" + auxl + "\n";

            ret += "\033[1mEstadisticas:\tmin\tmax\tmedia\tdestip\033[0m\n";

            media = mediaInt(tiempo_ocioso_acumulado.get(i));
            desvTip = desvTipInt(tiempo_ocioso_acumulado.get(i), media);
            mini = Collections.min(tiempo_ocioso_acumulado.get(i));
            maxi = Collections.max(tiempo_ocioso_acumulado.get(i));
            ret += "Dias Ocio Cam:\t" + mini + "\t" + maxi + "\t" + media + "\t" + desvTip + "\n";

            tot_ocup = new ArrayList<>();

            for (int j = 0; j < nZonas; j++) {

                if (i != j) {
                    s = i + "-" + j;

                    tot_ocup.addAll(ocupacion_camiones.get(s));

                }
            }

            media = mediaDouble(tot_ocup);
            desvTip = desvTipDouble(tot_ocup, media);
            mind = Collections.min(tot_ocup);
            maxd = Collections.max(tot_ocup);
            ret += "Ocupacion Cam:\t" + mind + "\t" + maxd + "\t" + media + "\t" + desvTip + "\n";

            tot_esp = new ArrayList<>();

            for (int j = 0; j < nZonas; j++) {

                if (i != j) {
                    s = i + "-" + j;

                    tot_esp.addAll(tiempo_espera_acum.get(s));

                }
            }

            media = mediaInt(tot_esp);
            desvTip = desvTipInt(tot_esp, media);
            mini = Collections.min(tot_esp);
            maxi = Collections.max(tot_esp);
            ret += "Espera Paq:\t" + mini + "\t" + maxi + "\t" + media + "\t" + desvTip + "\n";

            tot_lleg = new ArrayList<>();

            for (int j = 0; j < nZonas; j++) {

                if (i != j) {
                    s = i + "-" + j;

                    tot_lleg.addAll(tiempo_llegada_acum.get(s));

                }
            }

            media = mediaInt(tot_lleg);
            desvTip = desvTipInt(tot_lleg, media);
            mini = Collections.min(tot_lleg);
            maxi = Collections.max(tot_lleg);
            ret += "Llegada Paq:\t" + mini + "\t" + maxi + "\t" + media + "\t" + desvTip + "\n\n";

            /////////----------------------------Zona a Zona
            for (int j = 0; j < nZonas; j++) {
                if (i != j) {
                    s = i + "-" + j;

                    tar = getTardios(i, j);

                    ret += "\t\033[1m" + nombre_zonas.get(j) + "\033[0m   \tPaq: " + paquetes_enviados.get(s) + "\tCam: " + camiones_enviados.get(s) + "\n"
                            + "\t\t\tTard: " + tar + "\t" + tar / paquetes_enviados.get(s) + "\n"
                            + "\033[1m\t\t\tmin\tmax\tmedia\tdestip\033[0m\n";

                    tot_ocup = ocupacion_camiones.get(s);
                    media = mediaDouble(tot_ocup);
                    desvTip = desvTipDouble(tot_ocup, media);
                    mind = Collections.min(tot_ocup);
                    maxd = Collections.max(tot_ocup);
                    ret += "\tOcupacion Cam:\t" + mind + "\t" + maxd + "\t" + media + "\t" + desvTip + "\n";

                    tot_esp = tiempo_espera_acum.get(s);
                    media = mediaInt(tot_esp);
                    desvTip = desvTipInt(tot_esp, media);
                    mini = Collections.min(tot_esp);
                    maxi = Collections.max(tot_esp);
                    ret += "\tEspera Paq:\t" + mini + "\t" + maxi + "\t" + media + "\t" + desvTip + "\n";

                    tot_lleg = tiempo_llegada_acum.get(s);
                    media = mediaInt(tot_lleg);
                    desvTip = desvTipInt(tot_lleg, media);
                    mini = Collections.min(tot_lleg);
                    maxi = Collections.max(tot_lleg);
                    ret += "\tLlegada Paq:\t" + mini + "\t" + maxi + "\t" + media + "\t" + desvTip + "\n\n";

                }
            }

            ret += "\n";

        }

        return ret;
    }

}
