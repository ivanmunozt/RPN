/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rpn;

import com.sun.org.apache.xml.internal.serialize.OutputFormat;
import com.sun.org.apache.xml.internal.serialize.XMLSerializer;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

/**
 *
 * @author Adrián
 */
public class ReinicioValores {

    private static long semilla;
    private static int nZonas;
    private static int limite_emulacion;
    private static ArrayList<String> nombre_zonas;
    private static ArrayList<Integer> camiones_espera;
    private static ArrayList<ArrayList<Double>> probabilidad_destinos;
    private static ArrayList<Double> media_generacion_paquetes;
    private static ArrayList<Double> desvTip_generacion_paquetes;
    private static ArrayList<Double> media_tam;
    private static ArrayList<Double> desvTip_tam;
    private static ArrayList<Double> media_tiempo_limite;
    private static ArrayList<Double> desvTip_tiempo_limite;
    private static ArrayList<Integer> max_tiempo_limite;
    private static double capacidad_camiones;
    private static double porcentaje_minimo;

    public static void reiniciar() {

        semilla = 1234567890;
        nZonas = 7;
        limite_emulacion = 365;

        capacidad_camiones = 1000;
        porcentaje_minimo = 0.30;

        nombre_zonas = new ArrayList<>();
        nombre_zonas.add("Madrid");
        nombre_zonas.add("Cataluna");
        nombre_zonas.add("Valencia");
        nombre_zonas.add("Andalucia");
        nombre_zonas.add("Castilla");
        nombre_zonas.add("Pais_Vasco");
        nombre_zonas.add("Galicia");

        camiones_espera = new ArrayList<>();
        camiones_espera.add(7);
        camiones_espera.add(7);
        camiones_espera.add(7);
        camiones_espera.add(7);
        camiones_espera.add(7);
        camiones_espera.add(7);
        camiones_espera.add(7);

        probabilidad_destinos = new ArrayList<>();
        probabilidad_destinos.add(new ArrayList<>());
        probabilidad_destinos.add(new ArrayList<>());
        probabilidad_destinos.add(new ArrayList<>());
        probabilidad_destinos.add(new ArrayList<>());
        probabilidad_destinos.add(new ArrayList<>());
        probabilidad_destinos.add(new ArrayList<>());
        probabilidad_destinos.add(new ArrayList<>());

        probabilidad_destinos.get(0).add(.0);
        probabilidad_destinos.get(0).add(.20);
        probabilidad_destinos.get(0).add(.15);
        probabilidad_destinos.get(0).add(.20);
        probabilidad_destinos.get(0).add(.15);
        probabilidad_destinos.get(0).add(.15);
        probabilidad_destinos.get(0).add(.15);

        probabilidad_destinos.get(1).add(.20);
        probabilidad_destinos.get(1).add(.0);
        probabilidad_destinos.get(1).add(.15);
        probabilidad_destinos.get(1).add(.20);
        probabilidad_destinos.get(1).add(.15);
        probabilidad_destinos.get(1).add(.15);
        probabilidad_destinos.get(1).add(.15);

        probabilidad_destinos.get(2).add(.23);
        probabilidad_destinos.get(2).add(.20);
        probabilidad_destinos.get(2).add(.0);
        probabilidad_destinos.get(2).add(.18);
        probabilidad_destinos.get(2).add(.13);
        probabilidad_destinos.get(2).add(.13);
        probabilidad_destinos.get(2).add(.13);

        probabilidad_destinos.get(3).add(.20);
        probabilidad_destinos.get(3).add(.20);
        probabilidad_destinos.get(3).add(.15);
        probabilidad_destinos.get(3).add(.0);
        probabilidad_destinos.get(3).add(.15);
        probabilidad_destinos.get(3).add(.15);
        probabilidad_destinos.get(3).add(.15);

        probabilidad_destinos.get(4).add(.23);
        probabilidad_destinos.get(4).add(.20);
        probabilidad_destinos.get(4).add(.13);
        probabilidad_destinos.get(4).add(.18);
        probabilidad_destinos.get(4).add(.0);
        probabilidad_destinos.get(4).add(.13);
        probabilidad_destinos.get(4).add(.13);

        probabilidad_destinos.get(5).add(.23);
        probabilidad_destinos.get(5).add(.20);
        probabilidad_destinos.get(5).add(.13);
        probabilidad_destinos.get(5).add(.18);
        probabilidad_destinos.get(5).add(.13);
        probabilidad_destinos.get(5).add(.0);
        probabilidad_destinos.get(5).add(.13);

        probabilidad_destinos.get(6).add(.23);
        probabilidad_destinos.get(6).add(.20);
        probabilidad_destinos.get(6).add(.13);
        probabilidad_destinos.get(6).add(.18);
        probabilidad_destinos.get(6).add(.13);
        probabilidad_destinos.get(6).add(.13);
        probabilidad_destinos.get(6).add(.0);

        media_generacion_paquetes = new ArrayList<>();
        media_generacion_paquetes.add(1600.0);
        media_generacion_paquetes.add(1400.0);
        media_generacion_paquetes.add(1000.0);
        media_generacion_paquetes.add(1200.0);
        media_generacion_paquetes.add(1000.0);
        media_generacion_paquetes.add(1000.0);
        media_generacion_paquetes.add(1000.0);

        desvTip_generacion_paquetes = new ArrayList<>();
        desvTip_generacion_paquetes.add(250.0);
        desvTip_generacion_paquetes.add(250.0);
        desvTip_generacion_paquetes.add(250.0);
        desvTip_generacion_paquetes.add(250.0);
        desvTip_generacion_paquetes.add(250.0);
        desvTip_generacion_paquetes.add(250.0);
        desvTip_generacion_paquetes.add(250.0);

        media_tam = new ArrayList<>();
        media_tam.add(1.0);
        media_tam.add(1.0);
        media_tam.add(1.0);
        media_tam.add(1.0);
        media_tam.add(1.0);
        media_tam.add(1.0);
        media_tam.add(1.0);

        desvTip_tam = new ArrayList<>();
        desvTip_tam.add(0.25);
        desvTip_tam.add(0.25);
        desvTip_tam.add(0.25);
        desvTip_tam.add(0.25);
        desvTip_tam.add(0.25);
        desvTip_tam.add(0.25);
        desvTip_tam.add(0.25);

        media_tiempo_limite = new ArrayList<>();
        media_tiempo_limite.add(2.0);
        media_tiempo_limite.add(2.0);
        media_tiempo_limite.add(2.0);
        media_tiempo_limite.add(2.0);
        media_tiempo_limite.add(2.0);
        media_tiempo_limite.add(2.0);
        media_tiempo_limite.add(2.0);

        desvTip_tiempo_limite = new ArrayList<>();
        desvTip_tiempo_limite.add(0.5);
        desvTip_tiempo_limite.add(0.5);
        desvTip_tiempo_limite.add(0.5);
        desvTip_tiempo_limite.add(0.5);
        desvTip_tiempo_limite.add(0.5);
        desvTip_tiempo_limite.add(0.5);
        desvTip_tiempo_limite.add(0.5);

        max_tiempo_limite = new ArrayList<>();
        max_tiempo_limite.add(4);
        max_tiempo_limite.add(4);
        max_tiempo_limite.add(4);
        max_tiempo_limite.add(4);
        max_tiempo_limite.add(4);
        max_tiempo_limite.add(4);
        max_tiempo_limite.add(4);

        escribir_XML();

    }

    static private void escribir_XML() {

        try {

            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

            org.w3c.dom.Document dom;

            DocumentBuilder db = dbf.newDocumentBuilder();

            dom = db.newDocument();

            //Base XML
            Element rootEle = dom.createElement("Simulacion");
            dom.appendChild(rootEle);

            //Conf general
            Element confEle = dom.createElement("Configuracion");
            rootEle.appendChild(confEle);

            Element semillaEle = dom.createElement("Semilla");
            Text semillaText = dom.createTextNode(String.valueOf(semilla));
            semillaEle.appendChild(semillaText);
            confEle.appendChild(semillaEle);

            Element nZonasEle = dom.createElement("Numero_Zonas");
            Text nZonasText = dom.createTextNode(String.valueOf(nZonas));
            nZonasEle.appendChild(nZonasText);
            confEle.appendChild(nZonasEle);

            Element limiteEle = dom.createElement("Dias_limite");
            Text limiteText = dom.createTextNode(String.valueOf(limite_emulacion));
            limiteEle.appendChild(limiteText);
            confEle.appendChild(limiteEle);

            //Conf camiones
            Element camEle = dom.createElement("Camiones");
            rootEle.appendChild(camEle);

            Element capEle = dom.createElement("Capacidad");
            Text capText = dom.createTextNode(String.valueOf(capacidad_camiones));
            capEle.appendChild(capText);
            camEle.appendChild(capEle);

            Element porcEle = dom.createElement("Porcentaje_minimo");
            Text porcText = dom.createTextNode(String.valueOf(porcentaje_minimo));
            porcEle.appendChild(porcText);
            camEle.appendChild(porcEle);

            try {

                OutputFormat format = new OutputFormat(dom);
                format.setIndenting(true);

                XMLSerializer serializer = new XMLSerializer(
                        new FileOutputStream(new File("conf.xml")), format);

                serializer.serialize(dom);

            } catch (IOException ie) {
                ie.printStackTrace();
            }

        } catch (ParserConfigurationException pce) {
            System.out.println("Error while trying to instantiate DocumentBuilder " + pce);
            System.exit(1);
        }
    }

}