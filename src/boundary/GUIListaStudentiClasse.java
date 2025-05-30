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
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;

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
import javax.swing.table.DefaultTableModel;

public class GUIListaStudentiClasse extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel model; /////

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUIListaStudentiClasse frame = new GUIListaStudentiClasse();
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
	public GUIListaStudentiClasse() {
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
		
		
		JButton aggiornaButton = new JButton("aggiorna");
		aggiornaButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		aggiornaButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				GUIListaStudentiClasse seconda = new GUIListaStudentiClasse(); // Crea nuova finestra
				seconda.setVisible(true); // Mostra nuova finestra
				dispose(); // Chiude questa finestra
			}
		});
		aggiornaButton.setBounds(785, 11, 89, 23);
		contentPane.add(aggiornaButton);
		
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
		
		JLabel lblBenvenuto = new JLabel("Lista Studenti");
		lblBenvenuto.setHorizontalAlignment(SwingConstants.CENTER);
		lblBenvenuto.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblBenvenuto.setBounds(334, 69, 208, 56);
		contentPane.add(lblBenvenuto);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(83, 151, 713, 377);
		contentPane.add(scrollPane);
		
		table = new JTable();
		model = new DefaultTableModel(
				new Object[][] {}, // Inizia vuoto
				new String[] { "Nome", "Cognome" } // Nome colonna
			){
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		table.setModel(model);
		model.addRow(new Object[] { "Salvatore","Bosco" });
		scrollPane.setViewportView(table);
	}
}