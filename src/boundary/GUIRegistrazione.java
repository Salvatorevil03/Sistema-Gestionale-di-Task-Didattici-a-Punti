package taskdidatticiNEW;

import controller.Controller;

import java.awt.EventQueue;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import javax.swing.border.LineBorder;


import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUIRegistrazione frame = new GUIRegistrazione();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GUIRegistrazione() {
		//
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 650);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(213, 241, 247));
		contentPane.setBorder(new LineBorder(new Color(0, 0, 0)));
		//

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel titoloRegistrazione = new JLabel("REGISTRAZIONE");
		titoloRegistrazione.setHorizontalAlignment(SwingConstants.CENTER);
		titoloRegistrazione.setFont(new Font("Tahoma", Font.BOLD, 30));
		titoloRegistrazione.setBounds(313, 58, 275, 64);
		contentPane.add(titoloRegistrazione);
		
		JLabel nome = new JLabel("NOME");
		nome.setFont(new Font("Calibri", Font.PLAIN, 18));
		nome.setHorizontalAlignment(SwingConstants.CENTER);
		nome.setBounds(251, 237, 116, 20);
		contentPane.add(nome);
		
		ruoloField = new JTextField();
		ruoloField.setBounds(516, 181, 147, 20);
		contentPane.add(ruoloField);
		ruoloField.setColumns(10);
		
		nomeField = new JTextField();
		nomeField.setBounds(516, 236, 147, 20);
		contentPane.add(nomeField);
		nomeField.setColumns(10);

		JButton registrazioneBTN = new JButton("Registrazione");
		registrazioneBTN.setFont(new Font("Tahoma", Font.PLAIN, 15));
		registrazioneBTN.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int res = Controller.registrazione(ruoloField.getText(), nomeField.getText(), cognomeField.getText(), mailField.getText(), passwordField.getText());
				if (res == 1){
					GUILogin seconda = new GUILogin(); /// Crea nuova finestra
					seconda.setVisible(true); /// Mostra nuova finestra
					dispose(); /// Chiude questa finestra
				}else{
					JOptionPane.showMessageDialog(null, "Registrazione fallita");
				}
			}
		});
		registrazioneBTN.setBounds(381, 480, 130, 29);
		contentPane.add(registrazioneBTN);
		
		JButton indietroButton = new JButton("indietro");
		indietroButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				GUILogin seconda = new GUILogin(); /// Crea nuova finestra
				seconda.setVisible(true); /// Mostra nuova finestra
				dispose(); /// Chiude questa finestra
			}
		});
		indietroButton.setBounds(10, 11, 89, 23);
		contentPane.add(indietroButton);
		
		cognomeField = new JTextField();
		cognomeField.setColumns(10);
		cognomeField.setBounds(516, 298, 147, 20);
		contentPane.add(cognomeField);
		
		JLabel cognome = new JLabel("COGNOME");
		cognome.setHorizontalAlignment(SwingConstants.CENTER);
		cognome.setFont(new Font("Calibri", Font.PLAIN, 18));
		cognome.setBounds(251, 299, 116, 20);
		contentPane.add(cognome);
		
		JLabel mail = new JLabel("MAIL");
		mail.setHorizontalAlignment(SwingConstants.CENTER);
		mail.setFont(new Font("Calibri", Font.PLAIN, 18));
		mail.setBounds(251, 356, 116, 20);
		contentPane.add(mail);
		
		mailField = new JTextField();
		mailField.setColumns(10);
		mailField.setBounds(516, 355, 147, 20);
		contentPane.add(mailField);
		
		JLabel password = new JLabel("PASSWORD");
		password.setHorizontalAlignment(SwingConstants.CENTER);
		password.setFont(new Font("Calibri", Font.PLAIN, 18));
		password.setBounds(251, 407, 116, 20);
		contentPane.add(password);
		
		passwordField = new JTextField();
		passwordField.setColumns(10);
		passwordField.setBounds(516, 406, 147, 20);
		contentPane.add(passwordField);
		
		JLabel ruolo = new JLabel("RUOLO");
		ruolo.setHorizontalAlignment(SwingConstants.CENTER);
		ruolo.setFont(new Font("Calibri", Font.PLAIN, 18));
		ruolo.setBounds(251, 182, 116, 20);
		contentPane.add(ruolo);
	}
}
