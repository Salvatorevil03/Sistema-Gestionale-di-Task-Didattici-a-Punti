package boundary;

import controller.Controller;
import dto.DTOClasse;

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
 * Classe che rappresenta la GUI del docente.
 * <br> Il docente accederà a questa GUI dopo aver effettuato il login nella
 * GUI {@link GUILogin}.
 * <br> Qui il docente potrà:
 * <ul>
 *     <ul>
 *         <li>Creare una nuova classe</li>
 *         <li>Selezionare una classe dalla lista delle classi da
 *         lui create per entrare nella GUI {@link GUIClasseDocente}</li>
 *         <li>Accedere alla GUI {@link GUIListaStudentiPiattaforma} dove potrà iscrivere
 *         uno studente senza classe ad una delle sue classi</li>
 *     </ul>
 * </ul>
 */
public class GUIDocente extends JFrame {

	private static final long serialVersionUID = 1L;
	private static final String FONT_FAMILY = "Tahoma";
	private JPanel contentPane;
	private JTextField nomeField;
	private JTable table;
	private DefaultTableModel model; /////

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
            try {
                GUIDocente frame = new GUIDocente();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
	}

	/**
	 * Create the frame.
	 */
	public GUIDocente() {
		SessioneDocente docente = SessioneDocente.getInstance();
		String nomeDocente = docente.getNomeDocente();

		List<DTOClasse> classi = Controller.getClassi(String.valueOf(docente.getIdDocente()));

		setResizable(false);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 650);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(213, 241, 247));
		contentPane.setBorder(new LineBorder(new Color(0, 0, 0)));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel labelNomeDocente = new JLabel(nomeDocente);
		labelNomeDocente.setHorizontalAlignment(SwingConstants.LEFT);
		labelNomeDocente.setFont(new Font(FONT_FAMILY, Font.BOLD, 30));
		labelNomeDocente.setBounds(414, 41, 255, 64);
		contentPane.add(labelNomeDocente);
		
		JButton logoutBtn = new JButton("logout");
		logoutBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				GUILogin seconda = new GUILogin(); /// Crea nuova finestra
				seconda.setVisible(true); /// Mostra nuova finestra
				dispose(); /// Chiude questa finestra
			}
		});
		logoutBtn.setBounds(10, 11, 89, 23);
		contentPane.add(logoutBtn);
		
		JLabel lblBenvenuto = new JLabel("Benvenuto");
		lblBenvenuto.setHorizontalAlignment(SwingConstants.CENTER);
		lblBenvenuto.setFont(new Font(FONT_FAMILY, Font.BOLD, 30));
		lblBenvenuto.setBounds(218, 45, 208, 56);
		contentPane.add(lblBenvenuto);
		
		JButton btnListaStudenti = new JButton("Lista Studenti");
		btnListaStudenti.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				GUIListaStudentiPiattaforma seconda = new GUIListaStudentiPiattaforma(); /// Crea nuova finestra
				seconda.setVisible(true); /// Mostra nuova finestra
				dispose(); /// Chiude questa finestra
			}
		});
		btnListaStudenti.setFont(new Font(FONT_FAMILY, Font.PLAIN, 18));
		btnListaStudenti.setBounds(100, 396, 155, 75);
		contentPane.add(btnListaStudenti);
		
		JLabel lblCreaClasse = new JLabel("Crea Classe");
		lblCreaClasse.setHorizontalAlignment(SwingConstants.CENTER);
		lblCreaClasse.setFont(new Font(FONT_FAMILY, Font.BOLD, 30));
		lblCreaClasse.setBounds(69, 116, 208, 56);
		contentPane.add(lblCreaClasse);
		
		JLabel lblNome = new JLabel("Nome");
		lblNome.setHorizontalAlignment(SwingConstants.CENTER);
		lblNome.setFont(new Font(FONT_FAMILY, Font.PLAIN, 13));
		lblNome.setBounds(69, 189, 46, 14);
		contentPane.add(lblNome);
		
		nomeField = new JTextField();
		nomeField.setColumns(10);
		nomeField.setBounds(147, 187, 130, 20);
		contentPane.add(nomeField);
		
		JButton btnCrea = new JButton("crea");
		btnCrea.setFont(new Font(FONT_FAMILY, Font.PLAIN, 14));
		btnCrea.setBounds(119, 234, 89, 23);
		btnCrea.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String nomeClasse = nomeField.getText();
				if (nomeClasse.equals("")) {
					JOptionPane.showMessageDialog(null, "Inserisci un nome");
				}else {
					int res = Controller.creaClasse(nomeClasse,String.valueOf(docente.getIdDocente()));
					if (res == 1) {
						JOptionPane.showMessageDialog(null, "Classe creata");
					}else {
						JOptionPane.showMessageDialog(null, "Errore nella creazione della classe");
					}
				}
			}
		});
		contentPane.add(btnCrea);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(401, 198, 452, 295);
		contentPane.add(scrollPane);
		
		table = new JTable();
		model = new DefaultTableModel(
				new Object[][] {}, /// Inizia vuoto
				new String[] { "Codice","Nome" } /// Nome colonna
			){
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		table.setModel(model);
		if (!classi.isEmpty()) {
			for (DTOClasse c : classi) {
				model.addRow(new Object[] { c.getCodice(),c.getNome() });
			}
		}
		scrollPane.setViewportView(table);
		
		JLabel lblListaClassi = new JLabel("Lista Classi");
		lblListaClassi.setHorizontalAlignment(SwingConstants.CENTER);
		lblListaClassi.setFont(new Font(FONT_FAMILY, Font.BOLD, 30));
		lblListaClassi.setBounds(520, 116, 208, 56);
		contentPane.add(lblListaClassi);
		
		JButton btnSeleziona = new JButton("seleziona");
		btnSeleziona.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int selectedRow = table.getSelectedRow();
				if (selectedRow != -1) {
					String pkClasse = String.valueOf(model.getValueAt(selectedRow, 0));
					String nomeClasse = String.valueOf(model.getValueAt(selectedRow, 1));
					docente.setPkClasseSelezionata(pkClasse);
					docente.setNomeClasseSelezionato(nomeClasse);
					GUIClasseDocente seconda = new GUIClasseDocente(); /// Crea nuova finestra
					seconda.setVisible(true); /// Mostra nuova finestra
					dispose(); /// Chiude questa finestra
				} else {
					JOptionPane.showMessageDialog(null, "Nessuna riga selezionata.");
				}
			}
		});
		btnSeleziona.setBounds(589, 517, 89, 23);
		contentPane.add(btnSeleziona);
		
		JLabel lblListaStudenti = new JLabel("Lista Studenti");
		lblListaStudenti.setHorizontalAlignment(SwingConstants.CENTER);
		lblListaStudenti.setFont(new Font(FONT_FAMILY, Font.BOLD, 30));
		lblListaStudenti.setBounds(69, 312, 208, 56);
		contentPane.add(lblListaStudenti);
		
		JButton aggiornaButton = new JButton("aggiorna");
		aggiornaButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				GUIDocente seconda = new GUIDocente(); /// Crea nuova finestra
				seconda.setVisible(true); /// Mostra nuova finestra
				dispose(); /// Chiude questa finestra
			}
		});
		aggiornaButton.setBounds(785, 11, 89, 23);
		contentPane.add(aggiornaButton);
	}
}