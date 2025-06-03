package taskdidatticiNEW;

import controller.Controller;
import dto.DTOClasse;
import dto.DTOStudente;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JOptionPane;
import javax.swing.border.LineBorder;


import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class GUIListaStudentiPiattaforma extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel model; /////
	private JTable table2;
	private DefaultTableModel model2; /////

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUIListaStudentiPiattaforma frame = new GUIListaStudentiPiattaforma();
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
	public GUIListaStudentiPiattaforma() {

		SessioneDocente docente = SessioneDocente.getInstance();
		String pkDocente = String.valueOf(docente.getIdDocente());
		ArrayList<DTOStudente> studenti = Controller.getStudentiSenzaClasse();
		ArrayList<DTOClasse> classi = Controller.getClassi(pkDocente);
		// da cancellare
		//classi = new ArrayList<DTOClasse>();
		//classi.add(new DTOClasse(1,"INGSW",0));
		//classi.add(new DTOClasse(2,"INGSW2",0));
		//studenti = new ArrayList<DTOStudente>();
		//studenti.add(new DTOStudente(1,"Sasi","Bosco","gmail","pass",1,1,1));
		//studenti.add(new DTOStudente(1,"Erne","Bosco","gmail","pass",2,2,2));


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
				GUIListaStudentiPiattaforma seconda = new GUIListaStudentiPiattaforma(); // Crea nuova finestra
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
				GUIDocente seconda = new GUIDocente(); // Crea nuova finestra
				seconda.setVisible(true); // Mostra nuova finestra
				dispose(); // Chiude questa finestra
			}
		});
		indietroBtn.setBounds(10, 11, 89, 23);
		contentPane.add(indietroBtn);
		
		JLabel lblTitolo = new JLabel("Lista Studenti");
		lblTitolo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitolo.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblTitolo.setBounds(281, 25, 291, 56);
		contentPane.add(lblTitolo);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(83, 121, 265, 346);
		contentPane.add(scrollPane);
		
		table = new JTable();
		model = new DefaultTableModel(
				new Object[][] {}, // Inizia vuoto
				new String[] { "ID","Nome", "Cognome" } // Nome colonna
			){
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		table.setModel(model);
		if (studenti != null){
			for (DTOStudente s : studenti) {
				model.addRow(new Object[] { s.getId(),s.getNome(),s.getCognome() });
			}
		}
		scrollPane.setViewportView(table);
		
		JButton btnIscrivi = new JButton("iscrivi");
		btnIscrivi.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnIscrivi.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int selectedRow = table.getSelectedRow();
				int selectedRow2 = table2.getSelectedRow();
				if (selectedRow != -1 && selectedRow2 != -1) {
					String idStudente = String.valueOf(model.getValueAt(selectedRow, 0));
					String codiceClasse = String.valueOf(model2.getValueAt(selectedRow2, 0));
					System.out.println(idStudente);
					System.out.println(codiceClasse);
					int res = Controller.iscrizione(idStudente, codiceClasse);
					if (res == 1) {
						JOptionPane.showMessageDialog(null, "Studente iscritto!");
					}else {
						JOptionPane.showMessageDialog(null, "Problema nell'iscrizione");
					}
				} else {
					JOptionPane.showMessageDialog(null, "Nessun studente o classe selezionati.");
				}
			}
		});
		btnIscrivi.setBounds(389, 519, 89, 29);
		contentPane.add(btnIscrivi);
		
		JScrollPane scrollPane2 = new JScrollPane();
		scrollPane2.setBounds(524, 121, 265, 346);
		contentPane.add(scrollPane2);
		
		table2 = new JTable();
		model2 = new DefaultTableModel(
				new Object[][] {}, // Inizia vuoto
				new String[] { "Codice", "Nome" } // Nome colonna
			){
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		table2.setModel(model2);
		if (classi != null){
			for (DTOClasse c : classi) {
				model2.addRow(new Object[] { c.getCodice(),c.getNome() });
			}
		}
		scrollPane2.setViewportView(table2);
	}
}