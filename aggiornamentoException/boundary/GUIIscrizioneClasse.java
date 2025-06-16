package boundary;

import controller.Controller;
import exceptions.ClassEnrollmentException;

import java.awt.EventQueue;

import javax.swing.*;
import java.awt.Color;
import javax.swing.border.LineBorder;


import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Classe che rappresenta la finestra GUI per l'iscrizione da parte di uno studente
 * a una classe mediante il codice della classe stessa.
 * <br>Lo studente accederà automaticamente a questa GUI dopo aver effettuato il login
 * nella GUI {@link GUILogin} nel caso in cui non sia ancora iscritto a una classe.
 */
public class GUIIscrizioneClasse extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField codiceField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
            try {
                GUIIscrizioneClasse frame = new GUIIscrizioneClasse();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
	}

	/**
	 * Create the frame.
	 */
	public GUIIscrizioneClasse() {
		SessioneStudente studente = SessioneStudente.getInstance();
		setResizable(false);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

		setBounds(100, 100, 500, 250);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(213, 241, 247));
		contentPane.setBorder(new LineBorder(new Color(0, 0, 0)));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel titolo = new JLabel("ISCRIVITI AD UNA CLASSE");
		titolo.setHorizontalAlignment(SwingConstants.CENTER);
		titolo.setFont(new Font("Tahoma", Font.BOLD, 30));
		titolo.setBounds(28, 8, 419, 45);
		contentPane.add(titolo);
		
		JLabel lblServizi = new JLabel("Per usare i servizi della piattaforma");
		lblServizi.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblServizi.setHorizontalAlignment(SwingConstants.CENTER);
		lblServizi.setBounds(86, 52, 299, 30);
		contentPane.add(lblServizi);
		
		JLabel lblCodice = new JLabel("codice");
		lblCodice.setHorizontalAlignment(SwingConstants.CENTER);
		lblCodice.setBounds(169, 93, 46, 14);
		contentPane.add(lblCodice);
		
		codiceField = new JTextField();
		codiceField.setBounds(179, 118, 103, 17);
		contentPane.add(codiceField);
		codiceField.setColumns(10);
		
		JButton iscrizioneButton = new JButton("iscrizione");
		iscrizioneButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try{
					Controller.iscrizione(String.valueOf(studente.getIdStudente()),codiceField.getText());
					dispose();
				}catch(ClassEnrollmentException exception){
					JOptionPane.showMessageDialog(null, exception.getMessage());
				}
			}
		});
		iscrizioneButton.setBounds(180, 152, 89, 23);
		contentPane.add(iscrizioneButton);
	}
}
