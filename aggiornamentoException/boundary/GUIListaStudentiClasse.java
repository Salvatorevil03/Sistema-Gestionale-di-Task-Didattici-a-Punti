package boundary;

import controller.Controller;
import dto.DTOStudente;

import java.awt.EventQueue;

import javax.swing.*;
import java.awt.Color;
import javax.swing.border.LineBorder;


import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 * Classe che rappresenta la finestra GUI per la lista dei studenti della classe.
 * <br>Il docente che ha creato la classe potrà accedere a questa GUI a partire
 * dalla GUI {@link GUIClasseDocente}.
 * <br>In questa finestra il docente potrà visualizzare la lista dei studenti che
 * si sono iscritti alla classe selezionata o in maniera autonoma o mediante la
 * funzione di iscrizione a una classe che si trova nella GUI {@link GUIDocente}.
 */
public class GUIListaStudentiClasse extends JFrame {

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
                GUIListaStudentiClasse frame = new GUIListaStudentiClasse();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
	}

	/**
	 * Create the frame.
	 */
	public GUIListaStudentiClasse() {
		SessioneDocente docente = SessioneDocente.getInstance();
		String pkClasseSelezionata = docente.getPkClasseSelezionata();
		List<DTOStudente> studenti = Controller.getStudenti(pkClasseSelezionata);

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
		aggiornaButton.addActionListener(e -> {

        });
		aggiornaButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				GUIListaStudentiClasse seconda = new GUIListaStudentiClasse();
				seconda.setVisible(true);
				dispose();
			}
		});
		aggiornaButton.setBounds(785, 11, 89, 23);
		contentPane.add(aggiornaButton);
		
		JButton indietroBtn = new JButton("indietro");
		indietroBtn.addActionListener(e -> {
        });
		indietroBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				GUIClasseDocente seconda = new GUIClasseDocente();
				seconda.setVisible(true);
				dispose();
			}
		});
		indietroBtn.setBounds(10, 11, 89, 23);
		contentPane.add(indietroBtn);
		
		JLabel lblBenvenuto = new JLabel("Lista Studenti della classe");
		lblBenvenuto.setHorizontalAlignment(SwingConstants.CENTER);
		lblBenvenuto.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblBenvenuto.setBounds(66, 69, 708, 56);
		contentPane.add(lblBenvenuto);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(83, 151, 713, 377);
		contentPane.add(scrollPane);
		
		table = new JTable();
		model = new DefaultTableModel(
				new Object[][] {},
				new String[] { "ID","Nome", "Cognome" }
			){
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		table.setModel(model);
		if (!studenti.isEmpty()){
			for (DTOStudente s : studenti) {
				model.addRow(new Object[] { s.getId(),s.getNome(),s.getCognome() });
			}
		}
		scrollPane.setViewportView(table);
	}
}