package taskdidatticiNEW;

import controller.Controller;
import dto.DTOTask;

import java.awt.EventQueue;

import javax.swing.*;
import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;


import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.time.LocalDate;

public class GUIStudente extends JFrame {

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
					GUIStudente frame = new GUIStudente();
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
	public GUIStudente() {

		SessioneStudente studente = SessioneStudente.getInstance();
		String nome = studente.getNomeStudente();
		ArrayList<Integer> statistiche = Controller.getStatistiche(String.valueOf(studente.getIdStudente()));
		// da concellare
		//statistiche = new ArrayList<Integer>();
		//statistiche.add(0);
		//statistiche.add(0);
		//statistiche.add(0);


		ArrayList<DTOTask> tasks = Controller.getTasks(studente.getPkClasse());
		// da cancellare
		//tasks = new ArrayList<DTOTask>();
		//tasks.add(new DTOTask(1,"titolo di task1","descrizione", studente.getNomeStudente(), studente.getIdStudente()));
		//tasks.add(new DTOTask(2,"titolo di task2","descrizione", studente.getNomeStudente(), studente.getIdStudente()));


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
		aggiornaButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				GUIStudente seconda = new GUIStudente(); // Crea nuova finestra
				seconda.setVisible(true); // Mostra nuova finestra
				dispose(); // Chiude questa finestra
			}
		});
		aggiornaButton.setBounds(785, 11, 89, 23);
		contentPane.add(aggiornaButton);
		
		JButton logoutBtn = new JButton("logout");
		logoutBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				SessioneStudente.logout();
				GUILogin seconda = new GUILogin(); // Crea nuova finestra
				seconda.setVisible(true); // Mostra nuova finestra
				dispose(); // Chiude questa finestra
			}
		});
		logoutBtn.setBounds(10, 11, 89, 23);
		contentPane.add(logoutBtn);
		
		JLabel lblBenvenuto = new JLabel("Benvenuto ");
		lblBenvenuto.setHorizontalAlignment(SwingConstants.CENTER);
		lblBenvenuto.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblBenvenuto.setBounds(200, 48, 208, 56);
		contentPane.add(lblBenvenuto);
		

		JLabel nome_docente = new JLabel(nome);
		nome_docente.setHorizontalAlignment(SwingConstants.LEFT);
		nome_docente.setFont(new Font("Tahoma", Font.BOLD, 30));
		nome_docente.setBounds(394, 44, 324, 64);
		contentPane.add(nome_docente);
		
		JLabel lblListaTask = new JLabel("Lista Task");
		lblListaTask.setHorizontalAlignment(SwingConstants.CENTER);
		lblListaTask.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblListaTask.setBounds(551, 130, 208, 56);
		contentPane.add(lblListaTask);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(468, 197, 383, 227);
		contentPane.add(scrollPane);
		
		table = new JTable();
		model = new DefaultTableModel(
				new Object[][] {}, // Inizia vuoto
				new String[] { "ID","Titolo" } // Nome colonna
			) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		table.setModel(model);
		if (tasks != null){
			LocalDate dataOggi = java.time.LocalDate.now();
			for (DTOTask t : tasks) {
				//LocalDate taskDate = LocalDate.parse(t.getDataScadenza());
				//if(taskDate.isAfter(dataOggi)){
					model.addRow(new Object[] { t.getId(),t.getTitolo() });
				//}

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
					System.out.println(pkTask);
					studente.setPkTask(String.valueOf(pkTask));
					GUIConsegnaStudente seconda = new GUIConsegnaStudente(); // Crea nuova finestra
					seconda.setVisible(true); // Mostra nuova finestra
					dispose(); // Chiude questa finestra
				} else {
					JOptionPane.showMessageDialog(null, "Nessun task selezionato");
				}
			}
		});
		btnSeleziona.setBounds(615, 435, 89, 23);
		contentPane.add(btnSeleziona);
		
		JLabel lblStatistiche = new JLabel("Statistiche");
		lblStatistiche.setHorizontalAlignment(SwingConstants.CENTER);
		lblStatistiche.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblStatistiche.setBounds(75, 130, 208, 56);
		contentPane.add(lblStatistiche);
		
		JLabel lblMedia = new JLabel("Media voti ottenuti :");
		lblMedia.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblMedia.setHorizontalAlignment(SwingConstants.LEFT);
		lblMedia.setBounds(61, 192, 132, 23);
		contentPane.add(lblMedia);
		
		JLabel lblPunteggioTotaleOttenuto = new JLabel("Punteggio totale ottenuto :");
		lblPunteggioTotaleOttenuto.setHorizontalAlignment(SwingConstants.CENTER);
		lblPunteggioTotaleOttenuto.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPunteggioTotaleOttenuto.setBounds(51, 226, 186, 23);
		contentPane.add(lblPunteggioTotaleOttenuto);
		
		JLabel lblTotaleTaskAssegnati = new JLabel("Totale Task assegnati :");
		lblTotaleTaskAssegnati.setHorizontalAlignment(SwingConstants.CENTER);
		lblTotaleTaskAssegnati.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTotaleTaskAssegnati.setBounds(61, 265, 160, 23);
		contentPane.add(lblTotaleTaskAssegnati);

		JLabel lblOutMedia = new JLabel("numero1");
		lblOutMedia.setBounds(210, 198, 73, 14);
		contentPane.add(lblOutMedia);
		if (statistiche.get(1) == 0){
			lblOutMedia.setText("0");
		}else{
			lblOutMedia.setText(Float.toString(statistiche.get(2) / statistiche.get(1)));
		}

		JLabel lblOutPunteggio = new JLabel("numero1");
		lblOutPunteggio.setBounds(247, 232, 73, 14);
		contentPane.add(lblOutPunteggio);
		lblOutPunteggio.setText(statistiche.get(2).toString());

		JLabel lblOutTask = new JLabel("numero1");
		lblOutTask.setBounds(230, 271, 73, 14);
		contentPane.add(lblOutTask);
		lblOutTask.setText(statistiche.get(0).toString());
		
		JLabel lblClassifiche = new JLabel("Classifiche");
		lblClassifiche.setHorizontalAlignment(SwingConstants.CENTER);
		lblClassifiche.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblClassifiche.setBounds(75, 308, 208, 56);
		contentPane.add(lblClassifiche);
		
		JButton btnListaStudenti = new JButton("Punteggio totale");
		btnListaStudenti.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				GUIClassificaPunteggio seconda = new GUIClassificaPunteggio(); // Crea nuova finestra
				seconda.setVisible(true); // Mostra nuova finestra
				dispose(); // Chiude questa finestra
			}
		});
		btnListaStudenti.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnListaStudenti.setBounds(105, 380, 155, 56);
		contentPane.add(btnListaStudenti);
		
		JButton btnListaStudenti_1 = new JButton("Task completati");
		btnListaStudenti_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				GUIClassificaTask seconda = new GUIClassificaTask(); // Crea nuova finestra
				seconda.setVisible(true); // Mostra nuova finestra
				dispose(); // Chiude questa finestra
			}
		});
		btnListaStudenti_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnListaStudenti_1.setBounds(105, 449, 155, 56);
		contentPane.add(btnListaStudenti_1);
	}
}