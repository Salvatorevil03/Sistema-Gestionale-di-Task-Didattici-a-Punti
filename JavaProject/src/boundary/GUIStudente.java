package boundary;

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
import java.time.LocalDate;
import java.util.List;

/**
 * Classe che rappresenta la finestra GUI dello studente.
 * <br>Lo studente accederà a questa GUI dopo aver effettuato il login nella
 * GUI {@link GUILogin}.
 * <br>Qui lo studente potrà:
 * <ul>
 *     <ul>
 *         <li>Visualizzare alcune sue statistiche come il punteggio totale ottenuto
 *         per la valutazione dei Task da lui consegnati e valutati dal docente, la
 *         media dei voti ottenuti per i Task valutati, il numero di task assegnati
 *         nella classe</li>
 *         <li>Visualizzare la classifica della propria classe relativamente al
 *         punteggio totale ottenuto nella GUI {@link GUIClassificaPunteggio} o
 *         al numero dei task completati nella GUI {@link GUIClassificaTask}</li>
 *         <li>Selezionare un Task per accedere alla GUI {@link GUIConsegnaStudente}
 *         per fornire una propria soluzione relativamente al Task selezionato</li>
 *     </ul>
 * </ul>
 */
public class GUIStudente extends JFrame {

	private static final long serialVersionUID = 1L;
	private static final String FONT_FAMILY = "Tahoma";
	private static final String LABEL_NAME = "numero1";
	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel model;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
            try {
                GUIStudente frame = new GUIStudente();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
	}

	/**
	 * Create the frame.
	 */
	public GUIStudente() {

		SessioneStudente studente = SessioneStudente.getInstance();
		String nome = studente.getNomeStudente();
		List<Integer> statistiche = Controller.getStatistiche(String.valueOf(studente.getIdStudente()));
		List<DTOTask> tasks = Controller.getTasks(studente.getPkClasse());

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
				GUIStudente seconda = new GUIStudente();
				seconda.setVisible(true);
				dispose();
			}
		});
		aggiornaButton.setBounds(785, 11, 89, 23);
		contentPane.add(aggiornaButton);
		
		JButton logoutBtn = new JButton("logout");
		logoutBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				SessioneStudente.logout();
				GUILogin seconda = new GUILogin();
				seconda.setVisible(true);
				dispose();
			}
		});
		logoutBtn.setBounds(10, 11, 89, 23);
		contentPane.add(logoutBtn);
		
		JLabel lblBenvenuto = new JLabel("Benvenut* ");
		lblBenvenuto.setHorizontalAlignment(SwingConstants.CENTER);
		lblBenvenuto.setFont(new Font(FONT_FAMILY, Font.BOLD, 30));
		lblBenvenuto.setBounds(200, 48, 208, 56);
		contentPane.add(lblBenvenuto);
		

		JLabel labelNomeDocente = new JLabel(nome);
		labelNomeDocente.setHorizontalAlignment(SwingConstants.LEFT);
		labelNomeDocente.setFont(new Font(FONT_FAMILY, Font.BOLD, 30));
		labelNomeDocente.setBounds(394, 44, 324, 64);
		contentPane.add(labelNomeDocente);
		
		JLabel lblListaTask = new JLabel("Lista Task");
		lblListaTask.setHorizontalAlignment(SwingConstants.CENTER);
		lblListaTask.setFont(new Font(FONT_FAMILY, Font.BOLD, 30));
		lblListaTask.setBounds(551, 130, 208, 56);
		contentPane.add(lblListaTask);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(468, 197, 383, 227);
		contentPane.add(scrollPane);
		
		table = new JTable();
		model = new DefaultTableModel(
				new Object[][] {},
				new String[] { "ID","Titolo" }
			) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		table.setModel(model);
		if (!tasks.isEmpty()){
			LocalDate dataOggi = LocalDate.now();
			for (DTOTask t : tasks) {
				LocalDate taskDate = LocalDate.parse(t.getDataScadenza());
				if(taskDate.isAfter(dataOggi)){
					model.addRow(new Object[] { t.getId(),t.getTitolo() });
				}

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
					studente.setPkTask(String.valueOf(pkTask));
					GUIConsegnaStudente seconda = new GUIConsegnaStudente();
					seconda.setVisible(true);
					dispose();
				} else {
					JOptionPane.showMessageDialog(null, "Nessun task selezionato", "Task error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnSeleziona.setBounds(615, 435, 89, 23);
		contentPane.add(btnSeleziona);
		
		JLabel lblStatistiche = new JLabel("Statistiche");
		lblStatistiche.setHorizontalAlignment(SwingConstants.CENTER);
		lblStatistiche.setFont(new Font(FONT_FAMILY, Font.BOLD, 30));
		lblStatistiche.setBounds(75, 130, 208, 56);
		contentPane.add(lblStatistiche);
		
		JLabel lblMedia = new JLabel("Media voti ottenuti :");
		lblMedia.setFont(new Font(FONT_FAMILY, Font.PLAIN, 14));
		lblMedia.setHorizontalAlignment(SwingConstants.LEFT);
		lblMedia.setBounds(61, 192, 132, 23);
		contentPane.add(lblMedia);
		
		JLabel lblPunteggioTotaleOttenuto = new JLabel("Punteggio totale ottenuto :");
		lblPunteggioTotaleOttenuto.setHorizontalAlignment(SwingConstants.CENTER);
		lblPunteggioTotaleOttenuto.setFont(new Font(FONT_FAMILY, Font.PLAIN, 14));
		lblPunteggioTotaleOttenuto.setBounds(51, 226, 186, 23);
		contentPane.add(lblPunteggioTotaleOttenuto);
		
		JLabel lblTotaleTaskAssegnati = new JLabel("Totale Task completati :");
		lblTotaleTaskAssegnati.setHorizontalAlignment(SwingConstants.CENTER);
		lblTotaleTaskAssegnati.setFont(new Font(FONT_FAMILY, Font.PLAIN, 14));
		lblTotaleTaskAssegnati.setBounds(61, 265, 160, 23);
		contentPane.add(lblTotaleTaskAssegnati);

		JLabel lblOutMedia = new JLabel(LABEL_NAME);
		lblOutMedia.setBounds(210, 198, 73, 14);
		contentPane.add(lblOutMedia);
		if (statistiche.get(1) == 0){
			lblOutMedia.setText("0");
		}else{
			lblOutMedia.setText(Float.toString((float) statistiche.get(2) / statistiche.get(1)));
		}

		JLabel lblOutPunteggio = new JLabel(LABEL_NAME);
		lblOutPunteggio.setBounds(247, 232, 73, 14);
		contentPane.add(lblOutPunteggio);
		lblOutPunteggio.setText(statistiche.get(2).toString());

		JLabel lblOutTask = new JLabel(LABEL_NAME);
		lblOutTask.setBounds(230, 271, 73, 14);
		contentPane.add(lblOutTask);
		lblOutTask.setText(statistiche.get(0).toString());
		
		JLabel lblClassifiche = new JLabel("Classifiche");
		lblClassifiche.setHorizontalAlignment(SwingConstants.CENTER);
		lblClassifiche.setFont(new Font(FONT_FAMILY, Font.BOLD, 30));
		lblClassifiche.setBounds(75, 308, 208, 56);
		contentPane.add(lblClassifiche);
		
		JButton btnListaStudenti = new JButton("Punteggio totale");
		btnListaStudenti.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				GUIClassificaPunteggio seconda = new GUIClassificaPunteggio();
				seconda.setVisible(true);
				dispose();
			}
		});
		btnListaStudenti.setFont(new Font(FONT_FAMILY, Font.BOLD, 14));
		btnListaStudenti.setBounds(105, 380, 155, 56);
		contentPane.add(btnListaStudenti);
		
		JButton btnOtherListaStudenti = new JButton("Task completati");
		btnOtherListaStudenti.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				GUIClassificaTask seconda = new GUIClassificaTask();
				seconda.setVisible(true);
				dispose();
			}
		});
		btnOtherListaStudenti.setFont(new Font(FONT_FAMILY, Font.BOLD, 15));
		btnOtherListaStudenti.setBounds(105, 449, 155, 56);
		contentPane.add(btnOtherListaStudenti);
	}
}