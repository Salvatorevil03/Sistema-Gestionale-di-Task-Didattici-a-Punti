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
 * Classe che rappresenta la finestra GUI per la classifica sul numero di task completati.
 * <br>Qui un qualsiasi studente iscritto a una classe può visualizzare
 * la classifica degli studenti della classe a cui è iscritto.
 * <br>Lo studente potrà visualizzare la classifica relativa al numero dei task completati
 * per la classe a cui è iscritto.
 */
public class GUIClassificaTask extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel model;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
            try {
                GUIClassificaTask frame = new GUIClassificaTask();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
	}

	/**
	 * Create the frame.
	 */
	public GUIClassificaTask() {
		SessioneStudente studente = SessioneStudente.getInstance();
		List<DTOStudente> studenti = Controller.getClassificaTask(studente.getPkClasse());

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
				GUIClassificaTask seconda = new GUIClassificaTask();
				seconda.setVisible(true);
				dispose();
			}
		});
		aggiornaButton.setBounds(785, 11, 89, 23);
		contentPane.add(aggiornaButton);
		
		JButton indietroBtn = new JButton("indietro");
		indietroBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				GUIStudente seconda = new GUIStudente();
				seconda.setVisible(true);
				dispose();
			}
		});
		indietroBtn.setBounds(10, 11, 89, 23);
		contentPane.add(indietroBtn);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(116, 131, 664, 395);
		contentPane.add(scrollPane);
		
		table = new JTable();
		model = new DefaultTableModel(
				new Object[][] {},
				new String[] { "Nome","Cognome","Task Completati" }
			){
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		table.setModel(model);
		if (studenti != null){
			for (DTOStudente s : studenti) {
				model.addRow(new Object[] { s.getNome(),s.getCognome(),s.getNumTaskCompletati() });
			}
		}
		scrollPane.setViewportView(table);
		
		JLabel lblClassificaBasataSu = new JLabel("Classifica basata su task completati");
		lblClassificaBasataSu.setHorizontalAlignment(SwingConstants.CENTER);
		lblClassificaBasataSu.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblClassificaBasataSu.setBounds(127, 54, 600, 56);
		contentPane.add(lblClassificaBasataSu);
	}
}