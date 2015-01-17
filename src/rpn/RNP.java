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
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

/**
 *
 * @author Adrián
 */
public class RNP {

    long semilla;
    int nZonas;
    int limite_emulacion;
    ArrayList<String> nombre_zonas;
    ArrayList<Integer> camiones_espera;
    ArrayList<ArrayList<Double>> probabilidad_destinos;
    ArrayList<Double> media_generacion_paquetes;
    ArrayList<Double> desvTip_generacion_paquetes;
    ArrayList<Double> media_tam;
    ArrayList<Double> desvTip_tam;
    ArrayList<Double> media_tiempo_limite;
    ArrayList<Double> desvTip_tiempo_limite;
    ArrayList<Integer> max_tiempo_limite;
    double capacidad_camiones;
    double porcentaje_minimo;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        ReinicioValores.reiniciar();

    }

    private void escribir_XML() {

        try {

            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

            org.w3c.dom.Document dom;

            DocumentBuilder db = dbf.newDocumentBuilder();

            dom = db.newDocument();

            Element rootEle = dom.createElement("Simulacion");
            dom.appendChild(rootEle);

            Element confEle = dom.createElement("Configuracion General");
            rootEle.appendChild(confEle);

            Element semillaEle = dom.createElement("Semilla");
            Text semillaText = dom.createTextNode(String.valueOf(semilla));
            semillaEle.appendChild(semillaText);
            confEle.appendChild(semillaEle);

            Element nZonasEle = dom.createElement("Numero Zonas");
            Text nZonasText = dom.createTextNode(String.valueOf(nZonas));
            nZonasEle.appendChild(nZonasText);
            confEle.appendChild(nZonasEle);

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
