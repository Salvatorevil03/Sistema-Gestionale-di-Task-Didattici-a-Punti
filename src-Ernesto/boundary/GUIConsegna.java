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
import javax.swing.JOptionPane;
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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class GUIConsegna extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel model; /////
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUIConsegna frame = new GUIConsegna();
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
	public GUIConsegna() {
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
		
		JLabel nome_task = new JLabel("NOME_TASK");
		nome_task.setHorizontalAlignment(SwingConstants.CENTER);
		nome_task.setFont(new Font("Tahoma", Font.BOLD, 30));
		nome_task.setBounds(330, 28, 255, 64);
		contentPane.add(nome_task);
		
		JButton indietroBtn = new JButton("indietro");
		indietroBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		indietroBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				GUIClasseDocente seconda = new GUIClasseDocente(); // Crea nuova finestra
				seconda.setVisible(true); // Mostra nuova finestra
				dispose(); // Chiude questa finestra
			}
		});
		indietroBtn.setBounds(10, 11, 89, 23);
		contentPane.add(indietroBtn);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(45, 183, 326, 295);
		contentPane.add(scrollPane);
		
		table = new JTable();
		model = new DefaultTableModel(
				new Object[][] {}, // Inizia vuoto
				new String[] { "Nome" } // Nome colonna
			){
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		table.setModel(model);
		model.addRow(new Object[] { "classeSasi" });
		scrollPane.setViewportView(table);
		
		JLabel lblBenvenuto_1 = new JLabel("Lista Consegne");
		lblBenvenuto_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblBenvenuto_1.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblBenvenuto_1.setBounds(52, 116, 290, 56);
		contentPane.add(lblBenvenuto_1);
		
		JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setBounds(458, 185, 367, 223);
		contentPane.add(textArea);
		textArea.setLineWrap(true);         // abilita il ritorno a capo
		textArea.setWrapStyleWord(true);    // va a capo tra parole (non a metà parola)
		textArea.setText("Seleziona una consegna per visualizzare qui l'elaborato");
		
		JLabel lblBenvenuto_1_1 = new JLabel("Elaborato");
		lblBenvenuto_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblBenvenuto_1_1.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblBenvenuto_1_1.setBounds(481, 116, 290, 56);
		contentPane.add(lblBenvenuto_1_1);
		
		JButton btnNewButton = new JButton("valuta");
		btnNewButton.setBounds(500, 436, 89, 23);
		contentPane.add(btnNewButton);
		
		textField = new JTextField();
		textField.setBounds(619, 437, 122, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("voto");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(653, 419, 46, 14);
		contentPane.add(lblNewLabel);
		
		JButton aggiornaButton = new JButton("aggiorna");
		aggiornaButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				GUIConsegna seconda = new GUIConsegna(); // Crea nuova finestra
				seconda.setVisible(true); // Mostra nuova finestra
				dispose(); // Chiude questa finestra
			}
		});
		aggiornaButton.setBounds(785, 11, 89, 23);
		contentPane.add(aggiornaButton);
		
		JButton btnSeleziona = new JButton("seleziona");
		btnSeleziona.setBounds(45, 489, 89, 23);
		contentPane.add(btnSeleziona);
	}
}