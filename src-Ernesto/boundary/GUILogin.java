package boundary;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.SpringLayout;
import java.awt.BorderLayout;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import javax.swing.JList;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;

//import NomeP.GUI2;

import javax.swing.JSplitPane;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
		SessioneStudente studente = SessioneStudente.getInstance();
		studente.setNomeStudente("Salvatore");
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
		
		JLabel titoloLogin = new JLabel("LOGIN");
		titoloLogin.setHorizontalAlignment(SwingConstants.CENTER);
		titoloLogin.setFont(new Font("Tahoma", Font.BOLD, 30));
		titoloLogin.setBounds(351, 145, 158, 64);
		contentPane.add(titoloLogin);
		
		JLabel mail = new JLabel("MAIL");
		mail.setFont(new Font("Calibri", Font.PLAIN, 18));
		mail.setHorizontalAlignment(SwingConstants.CENTER);
		mail.setBounds(272, 277, 60, 20);
		contentPane.add(mail);
		
		JLabel password = new JLabel("PASSWORD");
		password.setFont(new Font("Calibri", Font.PLAIN, 18));
		password.setHorizontalAlignment(SwingConstants.CENTER);
		password.setBounds(251, 355, 116, 20);
		contentPane.add(password);
		
		mailField = new JTextField();
		mailField.setBounds(459, 276, 147, 20);
		contentPane.add(mailField);
		mailField.setColumns(10);
		
		passwordField = new JTextField();
		passwordField.setBounds(459, 354, 147, 20);
		contentPane.add(passwordField);
		passwordField.setColumns(10);
		
		JButton accessoBTN = new JButton("Accesso");
		accessoBTN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		accessoBTN.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (mailField.getText().equals("0")) {
					GUIStudente seconda = new GUIStudente(); // Crea nuova finestra
					seconda.setVisible(true); // Mostra nuova finestra
					dispose(); // Chiude questa finestra
				}else {
					if (mailField.getText().equals("1")) {
						GUIDocente seconda = new GUIDocente(); // Crea nuova finestra
						seconda.setVisible(true); // Mostra nuova finestra
						dispose(); // Chiude questa finestra
					}else {
						GUIIscrizioneClasse seconda = new GUIIscrizioneClasse(); // Crea nuova finestra
						seconda.setVisible(true); // Mostra nuova finestra
					}
				}
			}
		});
		accessoBTN.setFont(new Font("Tahoma", Font.PLAIN, 15));
		accessoBTN.setBounds(302, 452, 103, 29);
		contentPane.add(accessoBTN);
		
		JButton registrazioneBTN = new JButton("Registrazione");
		registrazioneBTN.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				GUIRegistrazione seconda = new GUIRegistrazione(); // Crea nuova finestra
				seconda.setVisible(true); // Mostra nuova finestra
				dispose(); // Chiude questa finestra
			}
		});
		registrazioneBTN.setFont(new Font("Tahoma", Font.PLAIN, 15));
		registrazioneBTN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		registrazioneBTN.setBounds(440, 452, 130, 29);
		contentPane.add(registrazioneBTN);
		
		JLabel or_label = new JLabel("OR");
		or_label.setHorizontalAlignment(SwingConstants.CENTER);
		or_label.setBounds(401, 461, 39, 14);
		contentPane.add(or_label);
	}
}
