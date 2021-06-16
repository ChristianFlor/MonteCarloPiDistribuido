package interfazUsuario;

import java.awt.*;

import javax.swing.*;

public class Interfaz extends JFrame {

    private JButton btnTests, btnNewInput;
    private JTextArea textAreaSeed, textAreaPoints;
    private JFrame frame;
    private JPanel panel;

    /**
     * Create the frame
     */
    public Interfaz() {
        setLayout(new BorderLayout());
        frame = new JFrame();
        panel = new JPanel();
//        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
//        add(panel, BorderLayout.CENTER);

        // Boton para correr pruebas
        Box vBox = Box.createVerticalBox();

        btnTests = new JButton("Usar Tests");
        // Boton para usar datos ingresados por el usuario
        btnNewInput = new JButton("Usar Nuevos datos");

        vBox.add(btnTests);
        vBox.add(btnNewInput);

        textAreaSeed = new JTextArea("0");
        textAreaSeed.setEditable(true);
        textAreaSeed.setBounds(138, 100, 245, 24);
        vBox.add(textAreaSeed);

        textAreaPoints = new JTextArea("0");
        textAreaPoints.setEditable(true);
        textAreaPoints.setBounds(138, 35, 245, 24);
        vBox.add(textAreaPoints);

        panel.add(vBox, BoxLayout.X_AXIS);

        frame.setSize(400, 400);
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