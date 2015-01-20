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

    

}
