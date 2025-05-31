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

public class GUIDocente extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField nomeField;
	private JTable table;
	private DefaultTableModel model; /////

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUIDocente frame = new GUIDocente();
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
	public GUIDocente() {
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
		
		JLabel nome_docente = new JLabel("NOME_DOCENTE");
		nome_docente.setHorizontalAlignment(SwingConstants.CENTER);
		nome_docente.setFont(new Font("Tahoma", Font.BOLD, 30));
		nome_docente.setBounds(414, 41, 255, 64);
		contentPane.add(nome_docente);
		
		JButton logoutBtn = new JButton("logout");
		logoutBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				GUILogin seconda = new GUILogin(); // Crea nuova finestra
				seconda.setVisible(true); // Mostra nuova finestra
				dispose(); // Chiude questa finestra
			}
		});
		logoutBtn.setBounds(10, 11, 89, 23);
		contentPane.add(logoutBtn);
		
		JLabel lblBenvenuto = new JLabel("Benvenuto");
		lblBenvenuto.setHorizontalAlignment(SwingConstants.CENTER);
		lblBenvenuto.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblBenvenuto.setBounds(218, 45, 208, 56);
		contentPane.add(lblBenvenuto);
		
		JButton btnListaStudenti = new JButton("Lista Studenti");
		btnListaStudenti.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				GUIListaStudentiPiattaforma seconda = new GUIListaStudentiPiattaforma(); // Crea nuova finestra
				seconda.setVisible(true); // Mostra nuova finestra
				dispose(); // Chiude questa finestra
			}
		});
		btnListaStudenti.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnListaStudenti.setBounds(100, 396, 155, 75);
		contentPane.add(btnListaStudenti);
		
		JLabel lblCreaClasse = new JLabel("Crea Classe");
		lblCreaClasse.setHorizontalAlignment(SwingConstants.CENTER);
		lblCreaClasse.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblCreaClasse.setBounds(69, 116, 208, 56);
		contentPane.add(lblCreaClasse);
		
		JLabel lblNome = new JLabel("Nome");
		lblNome.setHorizontalAlignment(SwingConstants.CENTER);
		lblNome.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNome.setBounds(69, 189, 46, 14);
		contentPane.add(lblNome);
		
		nomeField = new JTextField();
		nomeField.setColumns(10);
		nomeField.setBounds(147, 187, 130, 20);
		contentPane.add(nomeField);
		
		JButton btnCrea = new JButton("crea");
		btnCrea.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnCrea.setBounds(119, 234, 89, 23);
		contentPane.add(btnCrea);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(401, 198, 452, 295);
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
		
		JLabel lblListaClassi = new JLabel("Lista Classi");
		lblListaClassi.setHorizontalAlignment(SwingConstants.CENTER);
		lblListaClassi.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblListaClassi.setBounds(520, 116, 208, 56);
		contentPane.add(lblListaClassi);
		
		JButton btnSeleziona = new JButton("seleziona");
		btnSeleziona.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int selectedRow = table.getSelectedRow();
				if (selectedRow != -1) {
					String nome = (String) model.getValueAt(selectedRow, 0);
					System.out.println(nome);
					GUIClasseDocente seconda = new GUIClasseDocente(); // Crea nuova finestra
					seconda.setVisible(true); // Mostra nuova finestra
					dispose(); // Chiude questa finestra
				} else {
					JOptionPane.showMessageDialog(null, "Nessuna riga selezionata.");
				}
			}
		});
		btnSeleziona.setBounds(589, 517, 89, 23);
		contentPane.add(btnSeleziona);
		
		JLabel lblListaStudenti = new JLabel("Lista Studenti");
		lblListaStudenti.setHorizontalAlignment(SwingConstants.CENTER);
		lblListaStudenti.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblListaStudenti.setBounds(69, 312, 208, 56);
		contentPane.add(lblListaStudenti);
		
		JButton aggiornaButton = new JButton("aggiorna");
		aggiornaButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				GUIDocente seconda = new GUIDocente(); // Crea nuova finestra
				seconda.setVisible(true); // Mostra nuova finestra
				dispose(); // Chiude questa finestra
			}
		});
		aggiornaButton.setBounds(785, 11, 89, 23);
		contentPane.add(aggiornaButton);
	}
}