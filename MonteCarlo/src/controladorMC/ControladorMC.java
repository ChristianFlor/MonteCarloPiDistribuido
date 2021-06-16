package controladorMC;

import org.osoa.sca.annotations.*;
import servicios.*;
import modelo.*;

import java.awt.*;
import java.util.*;
import java.io.*;
import java.awt.event.*;

import interfazUsuario.Interfaz;

import javax.swing.*;

@Scope("COMPOSITE")
public class ControladorMC implements Runnable, ServicioAsignarPuntos, ServicioRestarNodo, ServicioSumarNodo, ServicioRespuesta {

    private final static long BATCH_PUNTOS = 100000;
    private final static int PRUEBAS_POR_TEST = 10;
    private final static int NODOS_POR_TEST = 4;

    private Interfaz frame;

    private Queue<Test> tests = new LinkedList<Test>();

    private Test currentTest;

    private Resultado resultado;

    private boolean openToRequests = false;

    public void run() {
        System.out.println("Run");
        try {
            frame = new Interfaz();
//            frame.setTitle("Simulacion Pi Montecarlo");
            frame.getFrame().addWindowListener(new WindowAdapter() {
                public void windowClosing(WindowEvent evt) {
                    System.exit(0);
                }
            });
//            frame.getFrame().getContentPane().add(new Interfaz(), BorderLayout.CENTER);
            frame.setSize(800, 600);
//            frame.setVisible(true);
            eventos();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public long[] asignarPuntos() {
        long[] trabajo = {0, 0};
        if (openToRequests) {
            System.out.println("entro al trabajo");
            if (currentTest.getRemainingPoints() > 0) {
                trabajo[0] = BATCH_PUNTOS;
                trabajo[1] = currentTest.getSeed();
            } else {
                double pi = currentTest.getPointsInside() / currentTest.getPoints();
                long execTime = currentTest.execTime(System.currentTimeMillis());
                resultado.agregarIteracion(pi, execTime);
                escribirResultados();
                setupTest();
            }
        } else {
            trabajo[0] = -1;
        }
        return trabajo;
    }

    public void setupTest() {
        currentTest = tests.poll();
        if (currentTest != null) {
            openToRequests = true;
            resultado = new Resultado(currentTest.getId(), currentTest.getSeed(), currentTest.getPoints(), PRUEBAS_POR_TEST, currentTest.getConnectedNodes());
            currentTest.setTimeBeforeTest(System.currentTimeMillis());
            System.out.println(openToRequests + "entro!");
        } else {
            openToRequests = false;
        }
    }

    public void escribirResultados() {
        System.out.println("Escribiendo");
        FileWriter fichero = null;
        PrintWriter pw;
        try {
            fichero = new FileWriter("MonteCarlo/data/resultados.csv");
            pw = new PrintWriter(fichero);
            pw.println(resultado.terminarTest());
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

    @Override
    public void restarNodo() {
        currentTest.reduceConnectedNodes();
    }

    @Override
    public void sumarNodo() {
        currentTest.addConnectedNodes();
        openToRequests = currentTest.getConnectedNodes() >= NODOS_POR_TEST;
        System.out.println("conectados: "+currentTest.getConnectedNodes());
    }

    @Override
    public void respuesta(long puntosAdentro) {
        currentTest.addPointsInside(puntosAdentro);
        currentTest.reduceRemainingPoints();
    }

    private void lecturaPruebas() {
//        System.out.println(System.getProperty("user.dir"));
        FileInputStream fstream;
        try {
            String path = "MonteCarlo/data/test.txt";
            File file = new File(path);
            fstream = new FileInputStream(file);
            DataInputStream entrada = new DataInputStream(fstream);
            BufferedReader buffer = new BufferedReader(new InputStreamReader(entrada));
            String line = buffer.readLine();
            int idTest = 1;
            while (line != null && !line.isEmpty()) {
                String[] nums = line.split("-");
                long puntosTotales = Long.parseLong(nums[0]);
                int seed = Integer.parseInt(nums[1]);
                for (int i = 0; i < PRUEBAS_POR_TEST; i++) {
                    Test test = new Test(idTest, seed, puntosTotales, BATCH_PUNTOS);
                    tests.add(test);
                }
                idTest++;
                line = buffer.readLine();
            }
            entrada.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void eventos() {
        frame.getBtnTests().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Test cargado.");
                lecturaPruebas();
                setupTest();
            }
        });

        frame.getBtnNewInput().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int seed = Integer.parseInt(frame.getTextAreaSeed().getText());
                long puntosTotales = Long.parseLong(frame.getTextAreaPoints().getText());
                for (int i = 0; i < PRUEBAS_POR_TEST; i++) {
                    Test test = new Test(1, seed, puntosTotales, BATCH_PUNTOS);
                    tests.add(test);
                }
                setupTest();
            }
        });
    }
}

