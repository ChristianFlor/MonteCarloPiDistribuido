package controladorMC;

import org.osoa.sca.annotations.*;
import servicios.*;
import repositorios.*;
import modelo.*;

import java.awt.*;
import java.util.*;
import java.io.*;
import java.util.List;
import java.util.Map.Entry;
import javax.swing.JFrame;
import java.awt.event.*;
import java.util.concurrent.Semaphore;

import interfazUsuario.Interfaz;


@Scope("COMPOSITE")
public class ControladorMC implements Runnable, ServicioMC, ServicioAsignarPuntos, ServicioRestarNodo, ServicioSumarNodo, ServicioRespuesta{

    private final static String COMA = ",";
    private final static long BATCH_PUNTOS = 100000;

    private Interfaz frame;

    private Queue<Test> tests = new LinkedList<>();

    private Test currentTest;

    private Resultado resultado;

    private boolean openToRequests = false;
    long timeBeforeTest = System.currentTimeMillis();
    long timeAfterTest= System.currentTimeMillis();

    public void run() {
        System.out.println("Run");
        try {
            JFrame frame = new JFrame("Simulacion Pi Montecarlo");
            frame.addWindowListener(new WindowAdapter() {
                public void windowClosing(WindowEvent evt) {
                    System.exit(0);
                }
            });
            frame.getContentPane().add(new Interfaz(), BorderLayout.CENTER);
            frame.setSize(800, 600);
            frame.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public long asignarPuntos(){
        long trabajo = 0;
        if(currentTest.getRemainingPoints() > 0){
            trabajo = BATCH_PUNTOS;
        }else{
            escribirResultados();
            timeAfterTest = System.currentTimeMillis();
        }
        return trabajo;
    }
    
    @Override
    public void restarNodo(){
        currentTest.reduceConnectedNodes();
    }

    @Override
    public void sumarNodo(){
        currentTest.addConnectedNodes();
    }

    @Override
    public void respuesta(long puntosAdentro){
        this.puntosAdentro += puntosAdentro;
        puntosRestantes -= BATCH_PUNTOS;
    }

    public void setupTest() {
        currentTest = tests.poll();
        if (currentTest != null){
            puntosTotales = currentTest.getPoints();
            puntosRestantes = puntosTotales;
            seed = currentTest.getSeed();
            openToRequests = true;
            currentTest.concatOutput(encabezado);
        }else{
            openToRequests = false;
        }
    }

    private void lecturaPruebas() {
        System.out.println(System.getProperty("user.dir"));
        FileInputStream fstream;
        try {
            String path = "MonteCarlo_Pi/data/test.txt";
            File file = new File(path);
            fstream = new FileInputStream(file);
            DataInputStream entrada = new DataInputStream(fstream);
            BufferedReader buffer = new BufferedReader(new InputStreamReader(entrada));
            String line = buffer.readLine();
            while (line != null && !line.isEmpty()) {
                String nums[] = line.split("-");
                puntosTotales = Long.parseLong(nums[0]);
                seed = Integer.parseInt(nums[1]);
                puntosRestantes = puntosTotales;
                Test test = new Test(nums[1], seed, puntosTotales, BATCH_PUNTOS);
                tests.add(test);
                line = buffer.readLine();
            }
            entrada.close();

        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
    }
    private void escribirResultado(){
        for (int j = 0; j < 10; j++) {
            long timeNow = System.currentTimeMillis();
            double pi = calcularPi(seed, totalPuntos);
            long timeAfter = System.currentTimeMillis();
            long totalTime = timeAfter - timeNow;

            average += totalTime;
            output += pi + COMA + totalTime + "\n";
            frame.getTabla().setValueAt(("Pi: " + pi + "-Tiempo Ejec (ms): " + totalTime).split("-"), filaComienzoTestActual + 2 + j, 0);
        }
        average = average / 10;
        output += "Promedio" + COMA + average + "\n";
        output += "Nodos" + COMA + "1\n\n";
        frame.getTabla().setValueAt(("Tiempo promedio (ms): " + average + "-Nodos: 1").split("-"), filaComienzoTestActual + 12, 0);
        escribirResultados(output);

        double pi = (puntosAdentro << 2) / puntosTotales;
    }
    public void escribirResultados() {
        System.out.println("Escribiendo");
        FileWriter fichero = null;
        PrintWriter pw;
        try {
            fichero = new FileWriter("MonteCarlo_Pi/data/resultados.csv");
            pw = new PrintWriter(fichero);
            pw.println(currentTest.getOutput());

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                // Nuevamente aprovechamos el finally para
                // asegurarnos que se cierra el fichero.
                if (null != fichero)
                    fichero.close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        System.out.println("Finish");
    }

    public void eventos(){

        frame.getBtnTests().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                lecturaPruebas();
                setupTest();
			}
		});

        frame.getBtnNewInput().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                puntosTotales = Long.parseLong(frame.getTextAreaPoints().getText());
                seed = Integer.parseInt(frame.getTextAreaSeed().getText());
			}
		});
    }
}
