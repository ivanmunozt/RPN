/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rpn;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Adrián
 */
class Zona {

    int idZona;
    int nZonas;
    ArrayList<Camion> camiones_espera;
    ArrayList<Double> probabilidad_destinos;
    double media_generacion_paquetes;
    double desvTip_generacion_paquetes;
    double media_tam;
    double desvTip_tam;
    double media_tiempo_limite;
    double desvTip_tiempo_limite;
    int max_tiempo_limite;
    ArrayList<ArrayList<Paquete>> paquetes_espera;
    Estadistica est;

    Zona(int idZona, int nZonas, ArrayList<Camion> camiones_espera, ArrayList<Double> probabilidad_destinos,
            Estadistica est, double media_generacion_paquetes, double desvTip_generacion_paquetes,
            double media_tam, double desvTip_tam, double media_tiempo_limite,
            double desvTip_tiempo_limite, int max_tiempo_limite) {

        this.idZona = idZona;
        this.nZonas = nZonas;
        this.camiones_espera = camiones_espera;
        this.probabilidad_destinos = probabilidad_destinos;
        this.est = est;
        this.media_generacion_paquetes = media_generacion_paquetes;
        this.desvTip_generacion_paquetes = desvTip_generacion_paquetes;
        this.media_tam = media_tam;
        this.desvTip_tam = desvTip_tam;
        this.media_tiempo_limite = media_tiempo_limite;
        this.desvTip_tiempo_limite = desvTip_tiempo_limite;
        this.max_tiempo_limite = max_tiempo_limite;
        this.paquetes_espera = new ArrayList<>();

        for (int i = 0; i < nZonas; i++) {

            this.paquetes_espera.add(new ArrayList<>());

        }

        this.probabilidad_destinos.set(idZona, 0.0); //nos aseguramos de que no es posible enviar a la misma zona de origen

        for (int i = 1; i < nZonas; i++) {//Acumulamos las probabilidades para facilitar calculos

            this.probabilidad_destinos.set(i, this.probabilidad_destinos.get(i) + this.probabilidad_destinos.get(i - 1));

        }

    }

    int generacion_paquetes() {

        int nPaq = (int) Math.abs(Math.round(est.getNormal(media_generacion_paquetes, media_generacion_paquetes)));
        int origen = idZona;
        int destino;
        double tamaño;
        int dia_limite;
        int fecha = est.dia;
        double aux;

        for (int i = 0; i < nPaq; i++) {

            //calculo dia limite 
            dia_limite = (int) Math.abs(Math.round(est.getNormal(media_tiempo_limite, desvTip_tiempo_limite)));
            dia_limite = dia_limite < 1 ? 1 : dia_limite;
            dia_limite = dia_limite > max_tiempo_limite ? max_tiempo_limite : dia_limite;
            dia_limite += fecha;

            //calculo destino
            aux = est.getNextDouble();
            destino = 0;
            while (probabilidad_destinos.get(destino) < aux) {
                destino++;
            }

            //calculo tamaño
            tamaño = Math.abs(est.getNormal(media_tam, desvTip_tam)); //el absoluto es por si sale negativo

            paquetes_espera.get(destino).add(new Paquete(destino, origen, tamaño, dia_limite, fecha));

        }

        for (ArrayList<Paquete> lista : paquetes_espera) {

            Collections.sort(lista);

        }

        return nPaq;

    }

    ArrayList<Camion> enviar_camiones() {

        boolean continuar = true;
        ArrayList<Camion> envios = new ArrayList<>();
        int prox_destino;

        Collections.sort(camiones_espera); // enviamos al camion que lleva mas tiempo esperando

        while (continuar) {

            if (camiones_espera.isEmpty()) {

                continuar = false;

            } else {
                prox_destino = siguiente_destino();

                if (prox_destino == -1) {

                    continuar = false;

                } else {

                    ArrayList<Paquete> paq = paquetes_espera.get(prox_destino);
                    double capacidad = Camion.capacidad;
                    ArrayList<Integer> ind_paq = new ArrayList<>();
                    camiones_espera.get(0).destino = prox_destino;

                    for (int i = paq.size() - 1; i >= 0; i--) {

                        if (paq.get(i).tamaño <= capacidad) {
                            capacidad -= paq.get(i).tamaño;
                            ind_paq.add(i);
                            camiones_espera.get(0).carga.add(paq.get(i));
                        }
                    }

                    for (Integer ind_paq1 : ind_paq) {
                        //Como hemos recorrido el array de antesde final a inicio, podemos borrar los indices sin problema.
                        paq.remove((int) ind_paq1);
                    }

                    envios.add(camiones_espera.get(0));
                    camiones_espera.remove(0);
                }

            }

        }

        //Los camiones no enviados esperan un dia mas
        for (int i = 0; i < camiones_espera.size(); i++) {

            camiones_espera.get(i).tiempo_espera++;

        }

        return envios;
    }

    private int siguiente_destino() {

        //////////////-----------------------provisional------------ los destinos con mas paquetes para 'caducar' se mandan antes en caso contrario se priorizan los destinos con mas paquetes
        ArrayList<Double> pesos = new ArrayList<>();
        boolean dia_limite = false;
        int cont = Integer.MAX_VALUE, aux;
        double peso;

        //Comprobacion: que destino tiene paquetes con fechas limites mas bajas
        for (ArrayList<Paquete> lista : paquetes_espera) {

            if (!lista.isEmpty()) {
                aux = lista.get(lista.size() - 1).fecha_limite;
                cont = aux < cont ? aux : cont;
            }
        }

        if (cont == Integer.MAX_VALUE) {

            return -1; // no hay paquetes

        }

        if (cont <= est.dia + 2) { //si hay algun paquete cuya fecha limite es en dos dias o menos o ya ha pasado, el destino es el que mas paquetes tenga en ese dia

            for (ArrayList<Paquete> lista : paquetes_espera) {

                aux = lista.size() - 1;
                peso = 0;

                while (aux >= 0 && lista.get(aux).fecha_limite == cont) {

                    peso++;
                    aux--;

                }

                pesos.add(peso);

            }

            return pesos.indexOf(Collections.max(pesos));

        } else {//sino se coge el destino con mas paquetes en total y si llega al minimo de capacidad de camion

            for (ArrayList<Paquete> lista : paquetes_espera) {

                peso = 0;

                for (Paquete paq : lista) {

                    peso += paq.tamaño;

                }

                if (peso < Camion.capacidad * Camion.porcentaje_minimo) { //No llega al minimo

                    peso = -1;

                }

                pesos.add(peso);

            }

            if (Collections.max(pesos) == -1) { //Si ningun camion llega al minimo

                return -1;

            } else {

                return pesos.indexOf(Collections.max(pesos));

            }
        }
    }

    void llegada_camion(Camion ent) {

        ent.tiempo_espera = 0;
        ent.carga.clear();
        ent.destino = -1;

        camiones_espera.add(ent);

        Collections.sort(camiones_espera);

    }

}
