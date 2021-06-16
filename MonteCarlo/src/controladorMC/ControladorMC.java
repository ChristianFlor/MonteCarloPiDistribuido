package controladorMC;

import org.osoa.sca.annotations.*;
import servicios.*;
import modelo.*;

import java.util.*;
import java.io.*;
import java.awt.event.*;

import interfazUsuario.Interfaz;

@Scope("COMPOSITE")
public class ControladorMC implements Runnable, ServicioAsignarPuntos, ServicioRestarNodo, ServicioSumarNodo, ServicioRespuesta {

    private static long batchPuntos = 100000;
    private static int pruebasPorTest = 10;
    private static int nodosPorTest = 4;

    private Interfaz frame;

    private Queue<Test> tests = new LinkedList<Test>();

    private Test currentTest;

    private Resultado resultado;
    private String output = "";

    private boolean openToRequests = true;
    private int connectedNodes = 0;

    public void run() {
        System.out.println("Run");
        try {
            frame = new Interfaz();
            frame.setTitle("Simulacion Pi Montecarlo");
            frame.getFrame().addWindowListener(new WindowAdapter() {
                public void windowClosing(WindowEvent evt) {
                    System.exit(0);
                }
            });
            frame.setSize(800, 600);
            eventos();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public long[] asignarPuntos() {
        long[] trabajo = {0, 0};
        if (openToRequests) {
            if (connectedNodes >= nodosPorTest) {
                if (currentTest.getRemainingPoints() > 0) {
                    trabajo[0] = batchPuntos;
                    trabajo[1] = currentTest.getSeed();
                } else {
                    double pi = 4 * (double) currentTest.getPointsInside() / (double) currentTest.getPoints();
                    long execTime = currentTest.execTime(System.currentTimeMillis());
                    currentTest.addIterations();
                    resultado.agregarIteracion(pi, execTime);

                    frame.getLblPiFinal().setText(String.valueOf(pi));
                    if (currentTest.getIterations() == pruebasPorTest) {
                        output = output.concat(resultado.terminarTest());
                        setupTest();
                    } else {
                        currentTest.setRemainingPoints(batchPuntos);
                    }
                }
            }
        } else {
            trabajo[0] = -1;
        }
        return trabajo;
    }

    public void setupTest() {
        System.out.println("Tests pendientes: " + tests.size());
        currentTest = tests.poll();
        if (currentTest != null) {
            openToRequests = true;
            resultado = new Resultado(currentTest.getId(), currentTest.getSeed(), currentTest.getPoints(), pruebasPorTest, connectedNodes);
            currentTest.setTimeBeforeTest(System.currentTimeMillis());
        } else {
            openToRequests = false;
            escribirResultados();
        }
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
                Test test = new Test(idTest, seed, puntosTotales, batchPuntos);
                tests.add(test);
                idTest++;
                line = buffer.readLine();
            }
            entrada.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void escribirResultados() {
        System.out.println("Escribiendo resultados...");
        FileWriter fichero = null;
        PrintWriter pw;
        try {
            fichero = new FileWriter("MonteCarlo/data/resultados.csv");
            pw = new PrintWriter(fichero);
            pw.println(output);
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
        System.out.println("Terminado.");
    }

    @Override
    public void restarNodo() {
        connectedNodes--;
    }

    @Override
    public void sumarNodo() {
        connectedNodes++;
        System.out.println("Nodos Conectados: " + connectedNodes);
        if (connectedNodes == nodosPorTest) {
            System.out.println("\nProcesando...");
            setupTest();
        }
    }

    @Override
    public void respuesta(long puntosAdentro) {
        if (currentTest != null) {
            currentTest.addPointsInside(puntosAdentro);
        }
    }

    public void eventos() {
        frame.getBtnTests().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Test cargado.");
                lecturaPruebas();
            }
        });

        // Solo se admiten puntos multiplos de 100.
        frame.getBtnNewInput().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int seed = Integer.parseInt(frame.getTextAreaSeed().getText());
                long puntosTotales = Long.parseLong(frame.getTextAreaPoints().getText());
                int nodos = Integer.parseInt(frame.getTextAreaNodes().getText());
                puntosTotales = puntosTotales / 100;
                puntosTotales = puntosTotales * 100;
                batchPuntos = puntosTotales / 10;
                nodosPorTest = nodos;
                pruebasPorTest = 1;
                Test test = new Test(1, seed, puntosTotales, batchPuntos);
                tests.add(test);
                System.out.println("Test cargado.");
            }
        });
    }
}

