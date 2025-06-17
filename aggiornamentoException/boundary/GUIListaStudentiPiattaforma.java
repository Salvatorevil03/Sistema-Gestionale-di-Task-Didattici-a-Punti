package boundary;

import controller.Controller;
import dto.DTOClasse;
import dto.DTOStudente;
import exceptions.ClassEnrollmentException;

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
 * Classe che rappresenta la finestra GUI per la lista degli studenti non
 * iscritti ad alcuna classe.
 * <br>Il docente accederà a questa finestra a partire dalla GUI {@link GUIDocente}.
 * <br>Qui il docente potrà visualizzare e selezionare gli studenti non iscritti
 * ad alcuna classe e iscriverli a una sua classe, selezionando opportunamente
 * la classe a cui si desidera iscrivere lo studente.
 * <br>Una volta iscritto a una classe lo studente non potrà essere di nuovo
 * selezionato per essere iscritto a un'altra classe.
 */
public class GUIListaStudentiPiattaforma extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel model;
	private JTable table2;
	private DefaultTableModel model2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
            try {
                GUIListaStudentiPiattaforma frame = new GUIListaStudentiPiattaforma();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
	}

	/**
	 * Create the frame.
	 */
	public GUIListaStudentiPiattaforma() {

		SessioneDocente docente = SessioneDocente.getInstance();
		String pkDocente = String.valueOf(docente.getIdDocente());
		List<DTOStudente> studenti = Controller.getStudentiSenzaClasse();
		List<DTOClasse> classi = Controller.getClassi(pkDocente);

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
				GUIListaStudentiPiattaforma seconda = new GUIListaStudentiPiattaforma();
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
				GUIDocente seconda = new GUIDocente();
				seconda.setVisible(true);
				dispose();
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
				new Object[][] {},
				new String[] { "ID","Nome", "Cognome" }
			){
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		table.setModel(model);
		if(studenti != null ){
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
					try{
						Controller.iscrizione(idStudente, codiceClasse);
						JOptionPane.showMessageDialog(null, "Studente iscritto!", "Info", JOptionPane.INFORMATION_MESSAGE);
					}catch(ClassEnrollmentException exception){
						JOptionPane.showMessageDialog(null, exception.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(null, "Nessun studente o classe selezionati.", "Selection error",	JOptionPane.ERROR_MESSAGE);
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
				new Object[][] {},
				new String[] { "Codice", "Nome" }
			){
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		table2.setModel(model2);
		if (!classi.isEmpty()){
			for (DTOClasse c : classi) {
				model2.addRow(new Object[] { c.getCodice(),c.getNome() });
			}
		}
		scrollPane2.setViewportView(table2);
	}
}