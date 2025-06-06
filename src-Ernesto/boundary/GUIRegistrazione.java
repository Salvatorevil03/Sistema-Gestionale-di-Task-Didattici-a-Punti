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

public class GUIRegistrazione extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField mailField;
	private JTextField passwordField;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

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
		
		mailField = new JTextField();
		mailField.setBounds(516, 181, 147, 20);
		contentPane.add(mailField);
		mailField.setColumns(10);
		
		passwordField = new JTextField();
		passwordField.setBounds(516, 236, 147, 20);
		contentPane.add(passwordField);
		passwordField.setColumns(10);
		
		JButton registrazioneBTN = new JButton("Registrazione");
		registrazioneBTN.setFont(new Font("Tahoma", Font.PLAIN, 15));
		registrazioneBTN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		registrazioneBTN.setBounds(381, 480, 130, 29);
		contentPane.add(registrazioneBTN);
		
		JButton indietroButton = new JButton("indietro");
		indietroButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				GUILogin seconda = new GUILogin(); // Crea nuova finestra
				seconda.setVisible(true); // Mostra nuova finestra
				dispose(); // Chiude questa finestra
			}
		});
		indietroButton.setBounds(10, 11, 89, 23);
		contentPane.add(indietroButton);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(516, 298, 147, 20);
		contentPane.add(textField);
		
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
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(516, 355, 147, 20);
		contentPane.add(textField_1);
		
		JLabel password = new JLabel("PASSWORD");
		password.setHorizontalAlignment(SwingConstants.CENTER);
		password.setFont(new Font("Calibri", Font.PLAIN, 18));
		password.setBounds(251, 407, 116, 20);
		contentPane.add(password);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(516, 406, 147, 20);
		contentPane.add(textField_2);
		
		JLabel ruolo = new JLabel("RUOLO");
		ruolo.setHorizontalAlignment(SwingConstants.CENTER);
		ruolo.setFont(new Font("Calibri", Font.PLAIN, 18));
		ruolo.setBounds(251, 182, 116, 20);
		contentPane.add(ruolo);
	}
}
