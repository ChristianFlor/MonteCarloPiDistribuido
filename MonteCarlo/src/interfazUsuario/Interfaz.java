package interfazUsuario;

import java.awt.*;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;

public class Interfaz extends JPanel {


    private JLabel lblSeed, lblPoints, lblPi;
    private JTable tabla;

    public JTable getTabla() {
        return tabla;
    }

    /**
     * Create the frame
     */
    public Interfaz() {

//		datos[0] = header;

        setLayout(new BorderLayout());
        tabla = new JTable(new ModeloDatos());
        // La tabla se añade a un ScrollPane para que sea éste el
        // que controle automáticamente en tamaño de la tabla,
        // presentando una barra de desplazamiento cuando sea
        // necesario
        JScrollPane panel = new JScrollPane(tabla);
        add(panel, BorderLayout.CENTER);
    }

    // El Modelo de la Tabla es el que controla todos los
    // datos que se colocan en ella
    class ModeloDatos extends AbstractTableModel {
        String[][] datos;

        public ModeloDatos() {
            datos = new String[75][2];
        }

        // Devuelve el número de columnas de la tabla
        public int getColumnCount() {
            return (datos[0].length);
        }

        // Devuelve el número de filas de la tabla
        public int getRowCount() {
            return (datos.length);
        }

        // Indica si la casilla identificada por fila y columna es
        // editable
        public boolean isCellEditable(int fila, int col) {
            return (false);
        }

        // Devuelve el valor de una determinada casilla de la tabla
        // identificada mediante fila y columna
        public String getValueAt(int fila, int col) {
            return (datos[fila][col]);
        }

        // Cambia el valor que contiene una determinada casilla de
        // la tabla
        public void setValueAt(Object valor, int fila, int col) {
            datos[fila] = (String[]) valor;
            // Indica que se ha cambiado
            fireTableDataChanged();
        }
    }

    public void setInfo(String seed, String points, String pi) {

    }
}

//--------------------------------------------------------------
//		JFrame frame = new JFrame( "Simulacion Pi Montecarlo" );
//		frame.addWindowListener( new WindowAdapter() {
//			public void windowClosing( WindowEvent evt ) {
//				System.exit( 0 );
//			}
//		} );
//		frame.getContentPane().add( new InterfazPrueba(), BorderLayout.CENTER );
//		frame.setSize( 800,600 );
//		frame.setVisible( true );

//----------------------------------------

//		lblSeed.setText("Semilla: "+seed);
//		lblPoints.setText("Puntos: "+points);
//		lblPi.setText("Pi: "+pi);

//---------------------------------------------

//		setTitle("Simulador Pi Montecarlo");
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		setBounds(100, 100, 900, 430);
//		setLocationRelativeTo(null);
//
//		JPanel contentPane = new JPanel();
//		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
//		setContentPane(contentPane);
//		contentPane.setLayout(null);
//
//		JPanel panel = new JPanel();
//		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
//		panel.setBounds(15, 30, 393, 145);
//		contentPane.add(panel);
//		panel.setLayout(null);
//		panel.setVisible(true);
//
//		lblSeed = new JLabel("Semilla");
//		lblSeed.setHorizontalAlignment(SwingConstants.CENTER);
//		lblSeed.setBounds(10, 11, 118, 14);
//		panel.add(lblSeed);
//
//		lblPoints = new JLabel("Puntos");
//		lblPoints.setHorizontalAlignment(SwingConstants.CENTER);
//		lblPoints.setBounds(138, 11, 245, 14);
//		panel.add(lblPoints);
//
//		lblPi = new JLabel("Pi");
//		lblPi.setHorizontalAlignment(SwingConstants.CENTER);
//		lblPi.setBounds(138, 67, 245, 14);
//		panel.add(lblPi);

//---------------------------------------------------