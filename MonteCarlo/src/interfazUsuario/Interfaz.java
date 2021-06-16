package interfazUsuario;

import java.awt.*;

import javax.swing.*;

public class Interfaz extends JFrame {

    private JButton btnTests, btnNewInput;
    private JTextArea textAreaSeed, textAreaPoints, textAreaNodes;
    private JFrame frame;
    private JPanel panel;
    private JLabel lblPiFinal, lblNodesNum;

    /**
     * Create the frame
     */
    public Interfaz() {
        setLayout(new BorderLayout());
        frame = new JFrame();
        panel = new JPanel();

        // Boton para correr pruebas
        Box vBox = Box.createVerticalBox();

        btnTests = new JButton("Usar Tests");
        // Boton para usar datos ingresados por el usuario
        btnNewInput = new JButton("Usar Nuevos datos");
        vBox.add(btnTests);

        textAreaSeed = new JTextArea("Semilla");
        textAreaSeed.setEditable(true);
        textAreaSeed.setBounds(138, 100, 245, 24);
        vBox.add(textAreaSeed);

        textAreaPoints = new JTextArea("Puntos");
        textAreaPoints.setEditable(true);
        textAreaPoints.setBounds(138, 35, 245, 24);
        vBox.add(textAreaPoints);

        textAreaNodes = new JTextArea("Nodos");
        textAreaNodes.setEditable(true);
        textAreaNodes.setBounds(138, 35, 245, 24);
        vBox.add(textAreaNodes);
        vBox.add(btnNewInput);

        JLabel lblPi = new JLabel("Valor Pi:");
        lblPiFinal = new JLabel();
        vBox.add(lblPi);
        vBox.add(lblPiFinal);

        JLabel lblNodes = new JLabel("Nodos conectados:");
        lblNodesNum = new JLabel();
        vBox.add(lblNodes);
        vBox.add(lblNodesNum);

        panel.add(vBox, BoxLayout.X_AXIS);

        frame.setSize(400, 200);
        frame.getContentPane().add(BorderLayout.CENTER, panel);
        frame.setVisible(true);
    }

    public JFrame getFrame() {
        return frame;
    }

    public JButton getBtnTests() {
        return btnTests;
    }

    public JButton getBtnNewInput() {
        return btnNewInput;
    }

    public JTextArea getTextAreaSeed() {
        return textAreaSeed;
    }

    public JTextArea getTextAreaPoints() {
        return textAreaPoints;
    }

    public JLabel getLblPiFinal() {
        return lblPiFinal;
    }

    public JLabel getLblNodesNum() {
        return lblPiFinal;
    }

    public JTextArea getTextAreaNodes() {
        return textAreaNodes;
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