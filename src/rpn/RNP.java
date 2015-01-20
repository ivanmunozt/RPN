/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rpn;

import com.sun.org.apache.xml.internal.serialize.OutputFormat;
import com.sun.org.apache.xml.internal.serialize.XMLSerializer;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import jdk.internal.org.xml.sax.SAXException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

/**
 *
 * @author Adrián
 */
public class RNP {

    static long semilla;
    static int nZonas;
    static int limite_emulacion;
    static ArrayList<String> nombre_zonas;
    static ArrayList<Integer> camiones_espera;
    static ArrayList<ArrayList<Double>> probabilidad_destinos;
    static ArrayList<Double> media_generacion_paquetes;
    static ArrayList<Double> desvTip_generacion_paquetes;
    static ArrayList<Double> media_tam;
    static ArrayList<Double> desvTip_tam;
    static ArrayList<Double> media_tiempo_limite;
    static ArrayList<Double> desvTip_tiempo_limite;
    static ArrayList<Integer> max_tiempo_limite;
    static double capacidad_camiones;
    static double porcentaje_minimo;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        leer_XML();

        limite_emulacion = 365;

        Estadistica sim = new Estadistica(semilla, nZonas, nombre_zonas, limite_emulacion,
                camiones_espera, probabilidad_destinos, media_generacion_paquetes,
                desvTip_generacion_paquetes, media_tam, desvTip_tam, media_tiempo_limite,
                desvTip_tiempo_limite, max_tiempo_limite, capacidad_camiones, porcentaje_minimo);

        sim.iniciarSimulacion();

        String res = sim.toString();
        
        System.out.println(res);
        escribir_TXT(res);

    }

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

    private static void escribir_XML() {

        try {

            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

            Document dom;

            DocumentBuilder db = dbf.newDocumentBuilder();

            dom = db.newDocument();

            //Base XML
            Element rootEle = dom.createElement("Simulacion");
            dom.appendChild(rootEle);

            Element semillaEle = dom.createElement("Semilla");
            Text semillaText = dom.createTextNode(String.valueOf(semilla));
            semillaEle.appendChild(semillaText);
            rootEle.appendChild(semillaEle);

            Element nZonasEle = dom.createElement("Numero_zonas");
            Text nZonasText = dom.createTextNode(String.valueOf(nZonas));
            nZonasEle.appendChild(nZonasText);
            rootEle.appendChild(nZonasEle);

            Element limiteEle = dom.createElement("Dias_limite");
            Text limiteText = dom.createTextNode(String.valueOf(limite_emulacion));
            limiteEle.appendChild(limiteText);
            rootEle.appendChild(limiteEle);

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

            //Conf Zonas
            Element zonasEle = dom.createElement("Zonas");
            rootEle.appendChild(zonasEle);

            for (int i = 0; i < nZonas; i++) {

                Element zEle = dom.createElement("Zona_" + i);

                Element nomEle = dom.createElement("Nombre");
                Text nomText = dom.createTextNode(nombre_zonas.get(i));
                nomEle.appendChild(nomText);

                Element camEspEle = dom.createElement("Camiones_espera");
                Text camEspText = dom.createTextNode(String.valueOf(camiones_espera.get(i)));
                camEspEle.appendChild(camEspText);

                Element probEle = dom.createElement("Probabilidad_destinos");

                for (int j = 0; j < nZonas; j++) {

                    Element pdEle = dom.createElement("Zona_" + j);
                    Text pdText = dom.createTextNode(String.valueOf(probabilidad_destinos.get(i).get(j)));
                    pdEle.appendChild(pdText);
                    probEle.appendChild(pdEle);

                }

                Element medGenEle = dom.createElement("Media_generacion_paquetes");
                Text medGenText = dom.createTextNode(String.valueOf(media_generacion_paquetes.get(i)));
                medGenEle.appendChild(medGenText);

                Element desGenEle = dom.createElement("Desviacion_tipica_generacion_paquetes");
                Text desGenText = dom.createTextNode(String.valueOf(desvTip_generacion_paquetes.get(i)));
                desGenEle.appendChild(desGenText);

                Element medTamEle = dom.createElement("Media_tamano_paquetes");
                Text medTamText = dom.createTextNode(String.valueOf(media_tam.get(i)));
                medTamEle.appendChild(medTamText);

                Element desTamEle = dom.createElement("Desviacion_tipica_tamano_paquetes");
                Text desTamText = dom.createTextNode(String.valueOf(desvTip_tam.get(i)));
                desTamEle.appendChild(desTamText);

                Element medTiEle = dom.createElement("Media_tiempo_limite");
                Text medTiText = dom.createTextNode(String.valueOf(media_tiempo_limite.get(i)));
                medTiEle.appendChild(medTiText);

                Element desTiEle = dom.createElement("Desviacion_tipica_tiemp_limite");
                Text desTiText = dom.createTextNode(String.valueOf(desvTip_tiempo_limite.get(i)));
                desTiEle.appendChild(desTiText);

                Element maxTiEle = dom.createElement("Tiempo_limite_maximo");
                Text maxTiText = dom.createTextNode(String.valueOf(max_tiempo_limite.get(i)));
                maxTiEle.appendChild(maxTiText);

                zonasEle.appendChild(zEle);
                zEle.appendChild(nomEle);
                zEle.appendChild(camEspEle);
                zEle.appendChild(probEle);
                zEle.appendChild(medGenEle);
                zEle.appendChild(desGenEle);
                zEle.appendChild(medTamEle);
                zEle.appendChild(desTamEle);
                zEle.appendChild(medTiEle);
                zEle.appendChild(desTiEle);
                zEle.appendChild(maxTiEle);

            }

            try {

                OutputFormat format = new OutputFormat(dom);
                format.setIndenting(true);

                XMLSerializer serializer = new XMLSerializer(
                        new FileOutputStream(new File("conf.xml")), format);

                serializer.serialize(dom);

            } catch (IOException ie) {
                Logger.getLogger(RNP.class.getName()).log(Level.SEVERE, null, ie);
            }

        } catch (ParserConfigurationException pce) {
            Logger.getLogger(RNP.class.getName()).log(Level.SEVERE, null, pce);
        }
    }

    private static void leer_XML() {

        nombre_zonas = new ArrayList<>();
        camiones_espera = new ArrayList<>();
        probabilidad_destinos = new ArrayList<>();
        media_generacion_paquetes = new ArrayList<>();
        desvTip_generacion_paquetes = new ArrayList<>();
        media_tam = new ArrayList<>();
        desvTip_tam = new ArrayList<>();
        media_tiempo_limite = new ArrayList<>();
        desvTip_tiempo_limite = new ArrayList<>();
        max_tiempo_limite = new ArrayList<>();

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

        Document dom;

        try {

            //En un principio ibamos a usar un lector de xml, pero falla al llegar al 3 nivel.
            DocumentBuilder db = dbf.newDocumentBuilder();

            dom = db.parse("conf.xml");

            Element docEle = dom.getDocumentElement();

            String[] arrayTXT = docEle.getTextContent().replace(" ", "").split("\n");
            int cont = 0;
            ArrayList<String> txt = new ArrayList<>();

            for (int i = 0; i < arrayTXT.length; i++) {

                if (!"".equals(arrayTXT[i])) {
                    txt.add(arrayTXT[i]);
                }

            }

            semilla = Integer.parseInt(txt.get(cont));
            cont++;
            nZonas = Integer.parseInt(txt.get(cont));
            cont++;
            limite_emulacion = Integer.parseInt(txt.get(cont));
            cont++;
            capacidad_camiones = Double.parseDouble(txt.get(cont));
            cont++;
            porcentaje_minimo = Double.parseDouble(txt.get(cont));
            cont++;

            //System.out.println(semilla + " - " + nZonas + " - " + limite_emulacion + " - " + capacidad_camiones + " - " + porcentaje_minimo);
            for (int i = 0; i < nZonas; i++) {

                nombre_zonas.add(txt.get(cont++));
                camiones_espera.add(Integer.parseInt(txt.get(cont++)));
                probabilidad_destinos.add(new ArrayList<>());

                for (int j = 0; j < nZonas; j++) {

                    probabilidad_destinos.get(i).add(Double.parseDouble(txt.get(cont++)));

                }

                media_generacion_paquetes.add(Double.parseDouble(txt.get(cont++)));
                desvTip_generacion_paquetes.add(Double.parseDouble(txt.get(cont++)));
                media_tam.add(Double.parseDouble(txt.get(cont++)));
                desvTip_tam.add(Double.parseDouble(txt.get(cont++)));
                media_tiempo_limite.add(Double.parseDouble(txt.get(cont++)));
                desvTip_tiempo_limite.add(Double.parseDouble(txt.get(cont++)));
                max_tiempo_limite.add(Integer.parseInt(txt.get(cont++)));

//                System.out.println(nombre_zonas.get(i) + " - " + camiones_espera.get(i)
//                        + " - " + media_generacion_paquetes.get(i) + " - " + desvTip_generacion_paquetes.get(i)
//                        + " - " + media_tam.get(i) + " - " + desvTip_tam.get(i)
//                        + " - " + media_tiempo_limite.get(i) + " - " + desvTip_tiempo_limite.get(i)
//                        + " - " + max_tiempo_limite.get(i));
            }

        } catch (ParserConfigurationException | IOException | org.xml.sax.SAXException pce) {
            Logger.getLogger(RNP.class.getName()).log(Level.SEVERE, null, pce);
        }

    }

    static void escribir_TXT(String res) {

        FileWriter fichero = null;
        PrintWriter pw;
        try {
            fichero = new FileWriter("res.txt");
            pw = new PrintWriter(fichero);

            for (int i = 0; i < 10; i++) {
                pw.println("Linea " + i);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {

                if (null != fichero) {
                    fichero.close();
                }
            } catch (Exception e2) {
                System.out.println(e2.getMessage());
            }
        }

    }
}
