/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.sun.org.apache.xml.internal.serialize.OutputFormat;
import com.sun.org.apache.xml.internal.serialize.XMLSerializer;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.InputVerifier;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;
import rpn.Estadistica;

/**
 *
 * @author Adrián
 */
public class GUI extends javax.swing.JFrame {

    long semilla;
    int nZonas;
    int idZona;
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
     * Creates new form GUI
     */
    public GUI() {

        leer_XML();

        initComponents();

        SelDest.addItemListener(new ItemListener() {

            @Override
            public void itemStateChanged(ItemEvent e) {
                Tdestino.setText(String.valueOf(probabilidad_destinos.get(idZona).get(nombre_zonas.indexOf(SelDest.getItem(SelDest.getSelectedIndex())))));

            }
        });

        SelZona.addItemListener(new ItemListener() {

            @Override
            public void itemStateChanged(ItemEvent e) {

                idZona = nombre_zonas.indexOf(SelZona.getItem(SelZona.getSelectedIndex()));
                rellenar_Zona();

            }
        });

        idZona = 0;

        rellenar_GUI();

    }

    private void reiniciar_valores() {

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

    }

    private void escribir_XML() {

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
                System.err.println(ie.getMessage());
            }

        } catch (ParserConfigurationException pce) {
            System.err.println(pce.getMessage());
        }
    }

    private void leer_XML() {

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

            for (String itxt : arrayTXT) {
                
                if (!"".equals(itxt)) {
                    txt.add(itxt);
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

        } catch (Exception pce) {
            System.err.println(pce.getMessage());
        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        Tdias = new javax.swing.JTextField();
        Tsemillas = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        Tcapacidad = new javax.swing.JTextField();
        Tporcentaje = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        SelZona = new java.awt.Choice();
        jPanel2 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        Tnombre = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        TmGen = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        Tcam = new javax.swing.JTextField();
        TdGen = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        TmTam = new javax.swing.JTextField();
        TdTam = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        TmLim = new javax.swing.JTextField();
        TdLim = new javax.swing.JTextField();
        TmaxLim = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        SelDest = new java.awt.Choice();
        Tdestino = new javax.swing.JTextField();
        jSeparator3 = new javax.swing.JSeparator();
        bGuardar = new javax.swing.JButton();
        bCargar = new javax.swing.JButton();
        bDefault = new javax.swing.JButton();
        bIniciar = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Red Nacional de Paqueteria");
        setResizable(false);

        jPanel1.setMinimumSize(new java.awt.Dimension(0, 0));

        jLabel1.setText("Semilla");

        Tdias.setInputVerifier(new InputVerifier(){
            public boolean verify(JComponent tf) {
                String cadena = ((JTextField)tf).getText();
                // Aqui verificamos si cadena es correcta y devolvemos
                try {
                    limite_emulacion = Integer.parseInt(cadena);
                    return true;
                } catch (NumberFormatException nfe){
                    return false;
                }
            }
        }
    );

    Tsemillas.setInputVerifier(new InputVerifier(){
        public boolean verify(JComponent tf) {
            String cadena = ((JTextField)tf).getText();
            // Aqui verificamos si cadena es correcta y devolvemos
            try {
                semilla = Integer.parseInt(cadena);

                return true;
            } catch (NumberFormatException nfe){
                return false;
            }
        }
    });
    Tsemillas.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            TsemillasActionPerformed(evt);
        }
    });

    jLabel2.setText("Dias Simulacion");

    jLabel3.setText("Capacidad Camiones");

    Tcapacidad.setInputVerifier(new InputVerifier(){
        public boolean verify(JComponent tf) {
            String cadena = ((JTextField)tf).getText();
            // Aqui verificamos si cadena es correcta y devolvemos
            try {
                capacidad_camiones = Double.parseDouble(cadena);
                return true;
            } catch (NumberFormatException nfe){
                return false;
            }
        }
    });

    Tporcentaje.setInputVerifier(new InputVerifier(){
        public boolean verify(JComponent tf) {
            String cadena = ((JTextField)tf).getText();
            // Aqui verificamos si cadena es correcta y devolvemos
            try {
                porcentaje_minimo = Double.parseDouble(cadena);
                return true;
            } catch (NumberFormatException nfe){
                return false;
            }
        }
    });

    jLabel4.setText("Ocuapcion Mínima Camiones");

    jLabel5.setText("Zona");

    javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
    jPanel1.setLayout(jPanel1Layout);
    jPanel1Layout.setHorizontalGroup(
        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel1Layout.createSequentialGroup()
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addContainerGap()
                                    .addComponent(jLabel4))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGap(6, 6, 6)
                                    .addComponent(jLabel1)
                                    .addGap(130, 130, 130)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(Tporcentaje, javax.swing.GroupLayout.DEFAULT_SIZE, 108, Short.MAX_VALUE)
                                        .addComponent(Tsemillas))))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel3)
                                .addComponent(jLabel2))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(Tdias, javax.swing.GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE)
                                .addComponent(Tcapacidad))
                            .addGap(31, 31, 31))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel5)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(SelZona, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 588, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGap(0, 0, Short.MAX_VALUE))
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jSeparator2)))
            .addContainerGap())
    );
    jPanel1Layout.setVerticalGroup(
        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel1Layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(Tdias, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel2)
                .addComponent(Tsemillas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel1))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel3)
                .addComponent(Tcapacidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel4)
                .addComponent(Tporcentaje, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(SelZona, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel5))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
    );

    jLabel6.setText("Nombre");

    Tnombre.setInputVerifier(new InputVerifier(){
        public boolean verify(JComponent tf) {
            String cadena = ((JTextField)tf).getText();
            // Aqui verificamos si cadena es correcta y devolvemos
            if(cadena.replace(" ", "").length() == 0 || nombre_zonas.indexOf(cadena) >= 0){
                return false;
            }
            else{
                nombre_zonas.set(idZona, cadena);
                rellenar_GUI();
                return true;
            }
        }
    });
    Tnombre.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            TnombreActionPerformed(evt);
        }
    });

    jLabel7.setText("Camiones Espera");

    TmGen.setInputVerifier(new InputVerifier(){
        public boolean verify(JComponent tf) {
            String cadena = ((JTextField)tf).getText();
            // Aqui verificamos si cadena es correcta y devolvemos
            try {
                media_generacion_paquetes.set(idZona, Double.parseDouble(cadena));
                return true;
            } catch (NumberFormatException nfe){
                return false;
            }
        }
    });
    TmGen.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            TmGenActionPerformed(evt);
        }
    });

    jLabel8.setText("Media Generacion Paquetes");

    Tcam.setInputVerifier(new InputVerifier(){
        public boolean verify(JComponent tf) {
            String cadena = ((JTextField)tf).getText();
            // Aqui verificamos si cadena es correcta y devolvemos
            try {
                camiones_espera.set(idZona, Integer.parseInt(cadena));
                return true;
            } catch (NumberFormatException nfe){
                return false;
            }
        }
    });
    Tcam.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            TcamActionPerformed(evt);
        }
    });

    TdGen.setInputVerifier(new InputVerifier(){
        public boolean verify(JComponent tf) {
            String cadena = ((JTextField)tf).getText();
            // Aqui verificamos si cadena es correcta y devolvemos
            try {
                desvTip_generacion_paquetes.set(idZona, Double.parseDouble(cadena));
                return true;
            } catch (NumberFormatException nfe){
                return false;
            }
        }
    }
    );
    TdGen.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            TdGenActionPerformed(evt);
        }
    });

    jLabel9.setText("DesvTip Generacion Paquetes");

    jLabel11.setText("Media Tamaño Paquetes");

    jLabel12.setText("DesvTip Tamaño Paquetes");

    TmTam.setInputVerifier(new InputVerifier(){
        public boolean verify(JComponent tf) {
            String cadena = ((JTextField)tf).getText();
            // Aqui verificamos si cadena es correcta y devolvemos
            try {
                media_tam.set(idZona, Double.parseDouble(cadena));
                return true;
            } catch (NumberFormatException nfe){
                return false;
            }
        }
    });
    TmTam.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            TmTamActionPerformed(evt);
        }
    });

    TdTam.setInputVerifier(new InputVerifier(){
        public boolean verify(JComponent tf) {
            String cadena = ((JTextField)tf).getText();
            // Aqui verificamos si cadena es correcta y devolvemos
            try {
                desvTip_tam.set(idZona, Double.parseDouble(cadena));
                return true;
            } catch (NumberFormatException nfe){
                return false;
            }
        }
    });
    TdTam.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            TdTamActionPerformed(evt);
        }
    });

    jLabel13.setText("DesvTip Tiempo Lim Paquetes");

    jLabel14.setText("Media Tiempo Lim Paquetes");

    TmLim.setInputVerifier(new InputVerifier(){
        public boolean verify(JComponent tf) {
            String cadena = ((JTextField)tf).getText();
            // Aqui verificamos si cadena es correcta y devolvemos
            try {
                media_tiempo_limite.set(idZona, Double.parseDouble(cadena));
                return true;
            } catch (NumberFormatException nfe){
                return false;
            }
        }
    });
    TmLim.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            TmLimActionPerformed(evt);
        }
    });

    TdLim.setInputVerifier(new InputVerifier(){
        public boolean verify(JComponent tf) {
            String cadena = ((JTextField)tf).getText();
            // Aqui verificamos si cadena es correcta y devolvemos
            try {
                desvTip_tiempo_limite.set(idZona, Double.parseDouble(cadena));
                return true;
            } catch (NumberFormatException nfe){
                return false;
            }
        }
    });
    TdLim.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            TdLimActionPerformed(evt);
        }
    });

    TmaxLim.setInputVerifier(new InputVerifier(){
        public boolean verify(JComponent tf) {
            String cadena = ((JTextField)tf).getText();
            // Aqui verificamos si cadena es correcta y devolvemos
            try {
                max_tiempo_limite.set(idZona, Integer.parseInt(cadena));
                return true;
            } catch (NumberFormatException nfe){
                return false;
            }
        }
    });
    TmaxLim.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            TmaxLimActionPerformed(evt);
        }
    });

    jLabel15.setText("Maximo Tiempo Lim Paquetes");

    jLabel16.setText("Probabilidad Destinos");

    Tdestino.setInputVerifier(new InputVerifier(){
        public boolean verify(JComponent tf) {
            String cadena = ((JTextField)tf).getText();
            // Aqui verificamos si cadena es correcta y devolvemos
            try {
                probabilidad_destinos.get(idZona).set(nombre_zonas.indexOf(SelDest.getSelectedItem()), Double.parseDouble(cadena));
                return true;
            } catch (NumberFormatException nfe){
                return false;
            }
        }
    });
    Tdestino.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            TdestinoActionPerformed(evt);
        }
    });

    jSeparator3.setOrientation(javax.swing.SwingConstants.VERTICAL);

    javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
    jPanel2.setLayout(jPanel2Layout);
    jPanel2Layout.setHorizontalGroup(
        jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel2Layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(jLabel11)
                            .addComponent(jLabel12)
                            .addComponent(jLabel14))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(TmLim, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(TdTam, javax.swing.GroupLayout.DEFAULT_SIZE, 175, Short.MAX_VALUE)
                                    .addComponent(TdGen)
                                    .addComponent(TmTam)))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel6)
                            .addComponent(jLabel8))
                        .addGap(30, 30, 30)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Tnombre, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(Tcam, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(TmGen, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel13)
                        .addComponent(jLabel15)
                        .addComponent(jLabel16))
                    .addGap(21, 21, 21)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(Tdestino)
                        .addComponent(TdLim)
                        .addComponent(TmaxLim)
                        .addComponent(SelDest, javax.swing.GroupLayout.DEFAULT_SIZE, 173, Short.MAX_VALUE))))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );
    jPanel2Layout.setVerticalGroup(
        jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel2Layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel6)
                        .addComponent(Tnombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel7)
                        .addComponent(Tcam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel8)
                        .addComponent(TmGen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel9)
                        .addComponent(TdGen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel11)
                        .addComponent(TmTam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel12)
                        .addComponent(TdTam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel14)
                        .addComponent(TmLim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel13)
                        .addComponent(TdLim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel15)
                        .addComponent(TmaxLim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel16)
                        .addComponent(SelDest, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(Tdestino, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE))
                .addComponent(jSeparator3))
            .addContainerGap())
    );

    bGuardar.setText("Guardar Valores");
    bGuardar.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            bGuardarActionPerformed(evt);
        }
    });

    bCargar.setText("Cargar Valores");
    bCargar.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            bCargarActionPerformed(evt);
        }
    });

    bDefault.setText("Valores por Defecto");
    bDefault.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            bDefaultActionPerformed(evt);
        }
    });

    bIniciar.setText("Ejecutar Simulacion");
    bIniciar.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            bIniciarActionPerformed(evt);
        }
    });

    jButton1.setText("Generador Conguencial");
    jButton1.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButton1ActionPerformed(evt);
        }
    });

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(bDefault, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(bCargar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(bGuardar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(bIniciar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))))
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );
    layout.setVerticalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(layout.createSequentialGroup()
            .addContainerGap()
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 373, Short.MAX_VALUE)
                    .addGap(12, 12, 12))
                .addGroup(layout.createSequentialGroup()
                    .addComponent(bGuardar)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(bCargar)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(bDefault)
                    .addGap(99, 99, 99)
                    .addComponent(jButton1)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(bIniciar)
                    .addGap(28, 28, 28))))
    );

    pack();
    }// </editor-fold>//GEN-END:initComponents

    private void TnombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TnombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TnombreActionPerformed

    private void TmGenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TmGenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TmGenActionPerformed

    private void TcamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TcamActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TcamActionPerformed

    private void TdGenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TdGenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TdGenActionPerformed

    private void TmTamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TmTamActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TmTamActionPerformed

    private void TdTamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TdTamActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TdTamActionPerformed

    private void TmLimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TmLimActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TmLimActionPerformed

    private void TdLimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TdLimActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TdLimActionPerformed

    private void TmaxLimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TmaxLimActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TmaxLimActionPerformed

    private void TdestinoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TdestinoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TdestinoActionPerformed

    private void bGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bGuardarActionPerformed

        escribir_XML();

    }//GEN-LAST:event_bGuardarActionPerformed

    private void bCargarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bCargarActionPerformed

        leer_XML();

        idZona = 0;

        rellenar_GUI();

    }//GEN-LAST:event_bCargarActionPerformed

    private void bDefaultActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bDefaultActionPerformed

        reiniciar_valores();

        idZona = 0;

        rellenar_GUI();

    }//GEN-LAST:event_bDefaultActionPerformed

    private void bIniciarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bIniciarActionPerformed

        //Si la suma de probabilidades no da 1
        boolean continuar = true;

        for (int i = 0; i < probabilidad_destinos.size(); i++) {

            Double sum = 0.0;

            for (Double d : probabilidad_destinos.get(i)) {

                sum += d;

            }

            if (sum != 1.0 && continuar) {

                continuar = false;
                               
                JOptionPane.showMessageDialog(this, "La suma de probabilidades de la zona " + nombre_zonas.get(i) + " no es 1 ", "Error" , JOptionPane.WARNING_MESSAGE);
                
            }
        }

        if (continuar) {
            ArrayList<ArrayList<Double>> prob = new ArrayList<>();

            for (int i = 0; i < nZonas; i++) {

                prob.add(new ArrayList<>(probabilidad_destinos.get(i)));

            }

            new Resultados(new Estadistica(semilla, nZonas, new ArrayList<>(nombre_zonas), limite_emulacion,
                    new ArrayList<>(camiones_espera), prob, new ArrayList<>(media_generacion_paquetes),
                    new ArrayList<>(desvTip_generacion_paquetes), new ArrayList<>(media_tam), new ArrayList<>(desvTip_tam), new ArrayList<>(media_tiempo_limite),
                    new ArrayList<>(desvTip_tiempo_limite), new ArrayList<>(max_tiempo_limite), capacidad_camiones, porcentaje_minimo)).setVisible(true);

        }

    }//GEN-LAST:event_bIniciarActionPerformed

    private void TsemillasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TsemillasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TsemillasActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        
        Congruencial.getInstance().setVisible(true);
        
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new GUI().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private java.awt.Choice SelDest;
    private java.awt.Choice SelZona;
    private javax.swing.JTextField Tcam;
    private javax.swing.JTextField Tcapacidad;
    private javax.swing.JTextField TdGen;
    private javax.swing.JTextField TdLim;
    private javax.swing.JTextField TdTam;
    private javax.swing.JTextField Tdestino;
    private javax.swing.JTextField Tdias;
    private javax.swing.JTextField TmGen;
    private javax.swing.JTextField TmLim;
    private javax.swing.JTextField TmTam;
    private javax.swing.JTextField TmaxLim;
    private javax.swing.JTextField Tnombre;
    private javax.swing.JTextField Tporcentaje;
    private javax.swing.JTextField Tsemillas;
    private javax.swing.JButton bCargar;
    private javax.swing.JButton bDefault;
    private javax.swing.JButton bGuardar;
    private javax.swing.JButton bIniciar;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    // End of variables declaration//GEN-END:variables

    private void rellenar_GUI() {

        Tsemillas.setText(String.valueOf(semilla));
        Tdias.setText(String.valueOf(limite_emulacion));
        Tcapacidad.setText(String.valueOf(capacidad_camiones));
        Tporcentaje.setText(String.valueOf(porcentaje_minimo));

        SelZona.removeAll();

        for (int i = 0; i < nZonas; i++) {

            SelZona.add(nombre_zonas.get(i));

        }

        SelZona.select(idZona);

        rellenar_Zona();

    }

    private void rellenar_Zona() {

        Tnombre.setText(nombre_zonas.get(idZona));
        Tcam.setText(String.valueOf(camiones_espera.get(idZona)));
        TmGen.setText(String.valueOf(media_generacion_paquetes.get(idZona)));
        TdGen.setText(String.valueOf(desvTip_generacion_paquetes.get(idZona)));
        TmTam.setText(String.valueOf(media_tam.get(idZona)));
        TdTam.setText(String.valueOf(desvTip_tam.get(idZona)));
        TmLim.setText(String.valueOf(media_tiempo_limite.get(idZona)));
        TdLim.setText(String.valueOf(desvTip_tiempo_limite.get(idZona)));
        TmaxLim.setText(String.valueOf(max_tiempo_limite.get(idZona)));

        SelDest.removeAll();

        for (int i = 0; i < nZonas; i++) {

            if (i != idZona) {

                SelDest.add(nombre_zonas.get(i));

            }

        }

        SelDest.select(0);

        Tdestino.setText(String.valueOf(probabilidad_destinos.get(idZona).get(nombre_zonas.indexOf(SelDest.getSelectedItem()))));

    }
}
