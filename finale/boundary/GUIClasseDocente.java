package taskdidatticiNEW;

import controller.Controller;
import DTO.DTOClasse;
import DTO.DTOStudente;
import DTO.DTOTask;

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
import javax.swing.table.DefaultTableModel;


import javax.swing.JSplitPane;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;
import javax.swing.JTable;

public class GUIClasseDocente extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField titoloField;
	private JTextField descField;
	private JTextField dataField;
	private JTextField maxField;
	private JTable table;
	private DefaultTableModel model; /////

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUIClasseDocente frame = new GUIClasseDocente();
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
	public GUIClasseDocente() { //Nome dato dalla GUI che lo crea
		SessioneDocente docente = SessioneDocente.getInstance();
		String nomeClasse = docente.getNomeClasseSelezionato();
		// Da eliminare
		//nomeClasse = "nomeClasse";
		ArrayList<DTOTask> tasks = Controller.getTasks(docente.getPkClasseSelezionata());
		// Da eliminare
		//tasks = new ArrayList<DTOTask>();
		//tasks.add(new DTOTask(33,"titolo","desc","1/1/1",90));


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
		
		JLabel nome_classe = new JLabel(nomeClasse);
		nome_classe.setHorizontalAlignment(SwingConstants.CENTER);
		nome_classe.setFont(new Font("Tahoma", Font.BOLD, 30));
		nome_classe.setBounds(316, 50, 255, 64);
		contentPane.add(nome_classe);
		
		JButton indietroBtn = new JButton("indietro");
		indietroBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		indietroBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				GUIDocente seconda = new GUIDocente(); // Crea nuova finestra
				seconda.setVisible(true); // Mostra nuova finestra
				dispose(); // Chiude questa finestra
			}
		});
		indietroBtn.setBounds(10, 11, 89, 23);
		contentPane.add(indietroBtn);
		
		JButton btnListaStudenti = new JButton("Lista Studenti");
		btnListaStudenti.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				GUIListaStudentiClasse seconda = new GUIListaStudentiClasse(); // Crea nuova finestra
				seconda.setVisible(true); // Mostra nuova finestra
				dispose(); // Chiude questa finestra
			}
		});
		btnListaStudenti.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnListaStudenti.setBounds(91, 483, 155, 75);
		contentPane.add(btnListaStudenti);
		
		JLabel lblCreaTask = new JLabel("Crea Task");
		lblCreaTask.setHorizontalAlignment(SwingConstants.CENTER);
		lblCreaTask.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblCreaTask.setBounds(45, 122, 208, 56);
		contentPane.add(lblCreaTask);
		
		JLabel lblTitolo = new JLabel("Titolo");
		lblTitolo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitolo.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblTitolo.setBounds(45, 201, 84, 14);
		contentPane.add(lblTitolo);
		
		titoloField = new JTextField();
		titoloField.setColumns(10);
		titoloField.setBounds(149, 199, 130, 20);
		contentPane.add(titoloField);
		
		JLabel lblDescrizione = new JLabel("Descrizione");
		lblDescrizione.setHorizontalAlignment(SwingConstants.CENTER);
		lblDescrizione.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblDescrizione.setBounds(45, 242, 84, 14);
		contentPane.add(lblDescrizione);
		
		descField = new JTextField();
		descField.setColumns(10);
		descField.setBounds(149, 240, 130, 20);
		contentPane.add(descField);
		
		JLabel lblData = new JLabel("Data Scadenza");
		lblData.setHorizontalAlignment(SwingConstants.CENTER);
		lblData.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblData.setBounds(28, 274, 121, 14);
		contentPane.add(lblData);

		JLabel lblDataFormat = new JLabel("(yyyy-mm-dd)");
		lblDataFormat.setHorizontalAlignment(SwingConstants.CENTER);
		lblDataFormat.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblDataFormat.setBounds(28, 294, 121, 14);
		contentPane.add(lblDataFormat);
		
		dataField = new JTextField();
		dataField.setColumns(10);
		dataField.setBounds(149, 282, 130, 20);
		contentPane.add(dataField);
		
		maxField = new JTextField();
		maxField.setColumns(10);
		maxField.setBounds(149, 331, 130, 20);
		contentPane.add(maxField);
		
		JLabel lblPunti = new JLabel("Max Punti");
		lblPunti.setHorizontalAlignment(SwingConstants.CENTER);
		lblPunti.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblPunti.setBounds(45, 333, 84, 14);
		contentPane.add(lblPunti);
		
		JButton btnCrea = new JButton("crea");
		btnCrea.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnCrea.setBounds(113, 380, 89, 23);
		btnCrea.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try{
					if (titoloField.getText().equals("") || descField.getText().equals("") || dataField.getText().equals("") || maxField.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "Alcuni campi sono vuoti");
					} else {
						String titolo = titoloField.getText();
						String descrizione = descField.getText();
						String data = dataField.getText();
						/*
							Necessario passare la stringa nel formato yyyy-mm-dd
							Il controllo non viene fatto qui ma a livello Entity, se la data non è formattata
							correttamente il metodo controller.creaTask(...) restituisce -1
						 */

						if (!data.matches("\\d{4}-\\d{2}-\\d{2}")){
							throw new Exception();
						}
						int punti = Integer.parseInt(maxField.getText());
						int res = Controller.creaTask(docente.getPkClasseSelezionata(), titolo, descrizione, data, punti);
						if (res == -1) {
							JOptionPane.showMessageDialog(null, "Errore nella creazione task", "Error", JOptionPane.ERROR_MESSAGE);
						} else {
							JOptionPane.showMessageDialog(null, "Task creato con successo", "Info", JOptionPane.INFORMATION_MESSAGE);
						}
					}
				}catch (Exception err){
					JOptionPane.showMessageDialog(null, "Uno o più campi con formato input errato");
				}
			}
		});
		contentPane.add(btnCrea);
		
		JLabel lblListaStudenti = new JLabel("Lista Studenti");
		lblListaStudenti.setHorizontalAlignment(SwingConstants.CENTER);
		lblListaStudenti.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblListaStudenti.setBounds(63, 416, 208, 56);
		contentPane.add(lblListaStudenti);
		
		JLabel lblListaTask = new JLabel("Lista Task");
		lblListaTask.setHorizontalAlignment(SwingConstants.CENTER);
		lblListaTask.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblListaTask.setBounds(534, 122, 208, 56);
		contentPane.add(lblListaTask);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(437, 201, 413, 293);
		contentPane.add(scrollPane);
		
		table = new JTable();
		model = new DefaultTableModel(
				new Object[][] {}, // Inizia vuoto
				new String[] { "ID","Titolo","Data Scadenza","Max Punteggio" } // Nome colonna
			){
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		table.setModel(model);
		if (tasks != null){
			for (DTOTask t : tasks) {
				model.addRow(new Object[] { t.getId(),t.getTitolo(),t.getDataScadenza(),t.getMaxPuntiAssegnabili() });
			}
		}
		scrollPane.setViewportView(table);
		
		JButton btnSeleziona = new JButton("seleziona");
		btnSeleziona.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int selectedRow = table.getSelectedRow();
				if (selectedRow != -1) {
					String pkTask = String.valueOf(model.getValueAt(selectedRow, 0));
					String nomeTaskSelezionato = String.valueOf(model.getValueAt(selectedRow, 1));
					docente.setPkTaskSelezionato(pkTask);
					docente.setNomeTaskSelezionato(nomeTaskSelezionato);
					System.out.println(pkTask);
					GUIConsegna seconda = new GUIConsegna(); // Crea nuova finestra
					seconda.setVisible(true); // Mostra nuova finestra
					dispose(); // Chiude questa finestra
				} else {
					JOptionPane.showMessageDialog(null, "Nessuna riga selezionata.");
				}
			}
		});
		btnSeleziona.setBounds(602, 512, 89, 23);
		contentPane.add(btnSeleziona);
		
		JButton aggiornaButton = new JButton("aggiorna");
		aggiornaButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				GUIClasseDocente seconda = new GUIClasseDocente(); // Crea nuova finestra
				seconda.setVisible(true); // Mostra nuova finestra
				dispose(); // Chiude questa finestra
			}
		});
		aggiornaButton.setBounds(785, 11, 89, 23);
		contentPane.add(aggiornaButton);
	}
}