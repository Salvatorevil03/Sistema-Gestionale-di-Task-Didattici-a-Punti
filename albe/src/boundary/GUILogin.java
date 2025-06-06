package boundary;

import java.awt.EventQueue;
import javax.swing.*;
import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.MatteBorder;

import controller.Controller;
import dto.DTOStudente;
import dto.DTOUtente;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Classe che rappresenta la finestra GUI per l'accesso al sistema.
 * <br>L'utente accederà a questa GUI all'avvio del sistema.
 * <br>Qui l'utente potrà:
 * <ul>
 *     <ul>
 *         <li>Effettuare il login e accedere alla GUI {@link GUIDocente} o {@link GUIStudente}
 *         contestualmente al ruolo che l'utente ricopre.
 *         <br>Il sistema verificherà automaticamente le credenziali dell'utente attraverso
 *         il DB e lo farò accedere alla GUI corrispondente una volta validate le credenziali</br></li>
 *         <li>Accedere alla GUI {@link GUIRegistrazione} per registrarsi presso la piattaforma
 *         di Gestione dei Task Didattici</li>
 *     </ul>
 * </ul>
 */
public class GUILogin extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField mailField;
	private JTextField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUILogin frame = new GUILogin();
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
	public GUILogin() {
		//
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 650);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(213, 241, 247));
		contentPane.setBorder(new LineBorder(new Color(0, 0, 0)));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel titoloLogin = new JLabel("LOGIN");
		titoloLogin.setHorizontalAlignment(SwingConstants.CENTER);
		titoloLogin.setFont(new Font("Segoe UI", Font.BOLD, 36));
		titoloLogin.setForeground(new Color(30, 60, 90));
		titoloLogin.setBounds(351, 130, 158, 64);
		contentPane.add(titoloLogin);

		JLabel mail = new JLabel("MAIL");
		mail.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		mail.setHorizontalAlignment(SwingConstants.CENTER);
		mail.setBounds(272, 277, 60, 20);
		contentPane.add(mail);

		JLabel password = new JLabel("PASSWORD");
		password.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		password.setHorizontalAlignment(SwingConstants.CENTER);
		password.setBounds(251, 355, 116, 20);
		contentPane.add(password);

		mailField = new JTextField();
		mailField.setBounds(459, 276, 180, 28);
		mailField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		mailField.setBorder(new CompoundBorder(
				new LineBorder(new Color(180, 200, 230), 1, true),
				new EmptyBorder(4, 8, 4, 8)
		));
		contentPane.add(mailField);
		mailField.setColumns(10);

		passwordField = new JTextField();
		passwordField.setBounds(459, 354, 180, 28);
		passwordField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		passwordField.setBorder(new CompoundBorder(
				new LineBorder(new Color(180, 200, 230), 1, true),
				new EmptyBorder(4, 8, 4, 8)
		));
		contentPane.add(passwordField);
		passwordField.setColumns(10);

		JButton accessoBTN = new JButton("Accesso");
		accessoBTN.setFont(new Font("Segoe UI", Font.BOLD, 15));
		accessoBTN.setForeground(Color.WHITE);
		accessoBTN.setBackground(new Color(51, 153, 255));
		accessoBTN.setFocusPainted(false);
		accessoBTN.setBorder(new LineBorder(new Color(51, 153, 255), 1, true));
		accessoBTN.setBounds(302, 452, 120, 35);

		accessoBTN.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				accessoBTN.setBackground(new Color(30, 130, 230));
			}
			public void mouseExited(MouseEvent e) {
				accessoBTN.setBackground(new Color(51, 153, 255));
			}
			public void mouseClicked(MouseEvent e) {
				DTOUtente utente = Controller.login(mailField.getText(), passwordField.getText());
				if (utente != null) {
					if (utente instanceof DTOStudente) {
						int id_classe = Controller.getClasseID(String.valueOf(utente.getId()));
						if (id_classe > 0) {
							SessioneStudente studente = SessioneStudente.getInstance();
							studente.setNomeStudente(utente.getNome());
							studente.setIdStudente(utente.getId());
							studente.setPkClasse(String.valueOf(id_classe));
							GUIStudente seconda = new GUIStudente();
							seconda.setVisible(true);
							dispose();
						} else {
							SessioneStudente studente = SessioneStudente.getInstance();
							studente.setIdStudente(utente.getId());
							GUIIscrizioneClasse seconda = new GUIIscrizioneClasse();
							seconda.setVisible(true);
						}
					} else {
						SessioneDocente docente = SessioneDocente.getInstance();
						docente.setNomeDocente(utente.getNome());
						docente.setIdDocente(utente.getId());
						GUIDocente seconda = new GUIDocente();
						seconda.setVisible(true);
						dispose();
					}
				} else {
					JOptionPane.showMessageDialog(null, "Credenziali non valide");
				}
			}
		});
		contentPane.add(accessoBTN);

		JButton registrazioneBTN = new JButton("Registrazione");
		registrazioneBTN.setFont(new Font("Segoe UI", Font.BOLD, 15));
		registrazioneBTN.setForeground(Color.WHITE);
		registrazioneBTN.setBackground(new Color(100, 180, 100));
		registrazioneBTN.setFocusPainted(false);
		registrazioneBTN.setBorder(new LineBorder(new Color(100, 180, 100), 1, true));
		registrazioneBTN.setBounds(440, 452, 140, 35);

		registrazioneBTN.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				registrazioneBTN.setBackground(new Color(80, 160, 80));
			}
			public void mouseExited(MouseEvent e) {
				registrazioneBTN.setBackground(new Color(100, 180, 100));
			}
			public void mouseClicked(MouseEvent e) {
				GUIRegistrazione seconda = new GUIRegistrazione();
				seconda.setVisible(true);
				dispose();
			}
		});
		registrazioneBTN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {}
		});
		contentPane.add(registrazioneBTN);
	}
}
