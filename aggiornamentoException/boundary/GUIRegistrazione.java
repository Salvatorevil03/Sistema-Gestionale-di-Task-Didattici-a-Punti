package boundary;

import controller.Controller;
import exceptions.RoleNotValidException;

import java.awt.EventQueue;

import javax.swing.*;
import java.awt.Color;
import javax.swing.border.LineBorder;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import exceptions.*;

/**
 * Classe che rappresenta la finestra GUI per la registrazione di un nuovo utente
 * alla piattaforma.
 * <br>L'utente accederà a questa GUI dopo aver scelto di registrarsi nella GUI
 * {@link GUILogin}.
 * <br>Qui l'utente dovrà fornire varie informazioni per la registrazione tra cui:
 * <ul>
 *     <ul>
 *         <li>Nome</li>
 *         <li>Cognome</li>
 *         <li>Mail</li>
 *         <li>Password</li>
 *         <li>Ruolo (Docente o Studente)</li>
 *     </ul>
 * </ul>
 * <br>Il sistema verificherà che non vi siano altri utenti iscritti con la stessa mail
 * essendo essa una mail istituzionale.
 */
public class GUIRegistrazione extends JFrame {

	private static final long serialVersionUID = 1L;
	private static final String FONT_FAMILY = "Segoe UI";
	private JPanel contentPane;
	private JTextField ruoloField;
	private JTextField nomeField;
	private JTextField cognomeField;
	private JTextField mailField;
	private JTextField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				GUIRegistrazione frame = new GUIRegistrazione();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GUIRegistrazione() {
		//
		setResizable(false);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 650);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(213, 241, 247));
		contentPane.setBorder(new LineBorder(new Color(0, 0, 0)));
		//

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel titoloRegistrazione = new JLabel("REGISTRAZIONE");
		titoloRegistrazione.setHorizontalAlignment(SwingConstants.CENTER);
		titoloRegistrazione.setFont(new Font(FONT_FAMILY, Font.BOLD, 32));
		titoloRegistrazione.setForeground(new Color(40, 75, 99));
		titoloRegistrazione.setBounds(313, 58, 275, 64);
		contentPane.add(titoloRegistrazione);

		JLabel nome = new JLabel("NOME");
		nome.setFont(new Font(FONT_FAMILY, Font.PLAIN, 18));
		nome.setHorizontalAlignment(SwingConstants.RIGHT);
		nome.setBounds(251, 237, 116, 20);
		contentPane.add(nome);

		ruoloField = new JTextField();
		ruoloField.setBounds(516, 181, 147, 28);
		ruoloField.setFont(new Font(FONT_FAMILY, Font.PLAIN, 15));
		ruoloField.setBorder(BorderFactory.createLineBorder(new Color(180, 180, 180)));
		contentPane.add(ruoloField);
		ruoloField.setColumns(10);

		nomeField = new JTextField();
		nomeField.setBounds(516, 236, 147, 28);
		nomeField.setFont(new Font(FONT_FAMILY, Font.PLAIN, 15));
		nomeField.setBorder(BorderFactory.createLineBorder(new Color(180, 180, 180)));
		contentPane.add(nomeField);
		nomeField.setColumns(10);

		JButton registrazioneBTN = new JButton("Registrazione");
		registrazioneBTN.setFont(new Font(FONT_FAMILY, Font.BOLD, 15));
		registrazioneBTN.setBackground(new Color(100, 149, 237));
		registrazioneBTN.setForeground(Color.WHITE);
		registrazioneBTN.setFocusPainted(false);
		registrazioneBTN.setBounds(381, 480, 130, 36);
		contentPane.add(registrazioneBTN);
		registrazioneBTN.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try{
					Controller.registrazione(ruoloField.getText(), nomeField.getText(), cognomeField.getText(), mailField.getText(), passwordField.getText());
					GUILogin seconda = new GUILogin();
					seconda.setVisible(true);
					dispose();
				}catch(RoleNotValidException | MailAlreadyUsedException | MailNotValidException exception){
					JOptionPane.showMessageDialog(null, exception.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		JButton indietroButton = new JButton("Indietro");
		indietroButton.setFont(new Font(FONT_FAMILY, Font.PLAIN, 13));
		indietroButton.setBackground(new Color(220, 220, 220));
		indietroButton.setBounds(10, 11, 89, 28);
		indietroButton.setFocusPainted(false);
		contentPane.add(indietroButton);
		indietroButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				GUILogin seconda = new GUILogin();
				seconda.setVisible(true);
				dispose();
			}
		});

		cognomeField = new JTextField();
		cognomeField.setColumns(10);
		cognomeField.setFont(new Font(FONT_FAMILY, Font.PLAIN, 15));
		cognomeField.setBorder(BorderFactory.createLineBorder(new Color(180, 180, 180)));
		cognomeField.setBounds(516, 298, 147, 28);
		contentPane.add(cognomeField);

		JLabel cognome = new JLabel("COGNOME");
		cognome.setHorizontalAlignment(SwingConstants.RIGHT);
		cognome.setFont(new Font(FONT_FAMILY, Font.PLAIN, 18));
		cognome.setBounds(251, 299, 116, 20);
		contentPane.add(cognome);

		JLabel mail = new JLabel("MAIL");
		mail.setHorizontalAlignment(SwingConstants.RIGHT);
		mail.setFont(new Font(FONT_FAMILY, Font.PLAIN, 18));
		mail.setBounds(251, 356, 116, 20);
		contentPane.add(mail);

		mailField = new JTextField();
		mailField.setColumns(10);
		mailField.setFont(new Font(FONT_FAMILY, Font.PLAIN, 15));
		mailField.setBorder(BorderFactory.createLineBorder(new Color(180, 180, 180)));
		mailField.setBounds(516, 355, 147, 28);
		contentPane.add(mailField);

		JLabel password = new JLabel("PASSWORD");
		password.setHorizontalAlignment(SwingConstants.RIGHT);
		password.setFont(new Font(FONT_FAMILY, Font.PLAIN, 18));
		password.setBounds(251, 407, 116, 20);
		contentPane.add(password);

		passwordField = new JTextField();
		passwordField.setColumns(10);
		passwordField.setFont(new Font(FONT_FAMILY, Font.PLAIN, 15));
		passwordField.setBorder(BorderFactory.createLineBorder(new Color(180, 180, 180)));
		passwordField.setBounds(516, 406, 147, 28);
		contentPane.add(passwordField);

		JLabel ruolo = new JLabel("RUOLO");
		ruolo.setHorizontalAlignment(SwingConstants.RIGHT);
		ruolo.setFont(new Font(FONT_FAMILY, Font.PLAIN, 18));
		ruolo.setBounds(251, 182, 116, 20);
		contentPane.add(ruolo);
	}
}
