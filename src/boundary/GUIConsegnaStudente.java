package taskdidatticiNEW;

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
import javax.swing.table.DefaultTableModel;

import NomeP.GUI2;

import javax.swing.JSplitPane;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class GUIConsegnaStudente extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUIConsegnaStudente frame = new GUIConsegnaStudente();
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
	public GUIConsegnaStudente() {
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
		
		JButton logoutBtn = new JButton("indietro");
		logoutBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		logoutBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				GUIStudente seconda = new GUIStudente(); // Crea nuova finestra
				seconda.setVisible(true); // Mostra nuova finestra
				dispose(); // Chiude questa finestra
			}
		});
		logoutBtn.setBounds(10, 11, 89, 23);
		contentPane.add(logoutBtn);
		
		JLabel lblBenvenuto = new JLabel("Nome_Task");
		lblBenvenuto.setHorizontalAlignment(SwingConstants.CENTER);
		lblBenvenuto.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblBenvenuto.setBounds(146, 52, 600, 56);
		contentPane.add(lblBenvenuto);
		
		JLabel lblNewLabel = new JLabel("Titolo :");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel.setBounds(75, 138, 46, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Descrizione :");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1.setBounds(75, 248, 101, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Data Scadenza :");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_2.setBounds(75, 174, 101, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Massimo Punteggio :");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_3.setBounds(75, 211, 140, 14);
		contentPane.add(lblNewLabel_3);
		
		JButton btnNewButton = new JButton("Consegna");
		btnNewButton.setBounds(388, 547, 101, 29);
		contentPane.add(btnNewButton);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(103, 301, 672, 218);
		contentPane.add(textArea);
		
		JLabel lblNewLabel_4 = new JLabel("New label");
		lblNewLabel_4.setBounds(130, 138, 627, 14);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("New label");
		lblNewLabel_5.setBounds(186, 174, 571, 14);
		contentPane.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("New label");
		lblNewLabel_6.setBounds(213, 211, 500, 14);
		contentPane.add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("New label");
		lblNewLabel_7.setBounds(172, 248, 592, 14);
		contentPane.add(lblNewLabel_7);
		
	}
}