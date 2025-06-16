package boundary;

import controller.Controller;
import dto.DTOStudente;

import java.awt.EventQueue;

import javax.swing.*;
import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;


import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

/**
 * Classe che rappresenta la finestra GUI per la classifica punteggio.
 * <br>Qui un qualsiasi studente iscritto a una classe può visualizzare
 * la classifica degli studenti della classe a cui è iscritto.
 * <br>Lo studente potrà visualizzare la classifica relativa al
 * punteggio totale ottenuto delle soluzioni dei task che gli
 * sono stati valutati dal docente.
 */
public class GUIClassificaPunteggio extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel model; /////


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
            try {
                GUIClassificaPunteggio frame = new GUIClassificaPunteggio();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
	}

	/**
	 * Create the frame.
	 */
	public GUIClassificaPunteggio() {

		SessioneStudente studente = SessioneStudente.getInstance();
		List<DTOStudente> studenti = Controller.getClassificaPunteggio(studente.getPkClasse());

		//
		setResizable(false);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 650);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(213, 241, 247));
		contentPane.setBorder(new LineBorder(new Color(0, 0, 0)));
		//

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton aggiornaButton = new JButton("aggiorna");
		aggiornaButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				GUIClassificaPunteggio seconda = new GUIClassificaPunteggio(); /// Crea nuova finestra
				seconda.setVisible(true); /// Mostra nuova finestra
				dispose(); /// Chiude questa finestra
			}
		});
		aggiornaButton.setBounds(785, 11, 89, 23);
		contentPane.add(aggiornaButton);
		
		JButton indietroBtn = new JButton("indietro");
		indietroBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				GUIStudente seconda = new GUIStudente(); /// Crea nuova finestra
				seconda.setVisible(true); /// Mostra nuova finestra
				dispose(); /// Chiude questa finestra
			}
		});
		indietroBtn.setBounds(10, 11, 89, 23);
		contentPane.add(indietroBtn);
		
		JLabel lblTitolo = new JLabel("Classifica basata su punteggio totale");
		lblTitolo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitolo.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblTitolo.setBounds(124, 56, 600, 56);
		contentPane.add(lblTitolo);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(121, 132, 625, 389);
		contentPane.add(scrollPane);
		
		table = new JTable();
		model = new DefaultTableModel(
				new Object[][] {}, /// Inizia vuoto
				new String[] { "Nome","Cognome","Punteggio Totale" } /// Nome colonna
			){
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		table.setModel(model);
		if (studenti != null){
			for (DTOStudente s : studenti) {
				model.addRow(new Object[] { s.getNome(),s.getCognome(),s.getPunteggioTotaleOttenuto() });
			}
		}
		scrollPane.setViewportView(table);
	}
}