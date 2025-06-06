package boundary;

import controller.Controller;
import dto.DTOConsegna;

import java.awt.EventQueue;

import javax.swing.*;
import java.awt.Color;
import javax.swing.border.LineBorder;


import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.table.DefaultTableModel;

import java.util.List;

/**
 * Classe che rappresenta la finestra GUI per la valutazione delle consegne da parte
 * di un docente.
 * <br> Al docente comparirà questa GUI dopo che avrà selezionato un task nella GUI
 * {@link GUIClasseDocente}.
 * <br> Qui comparirà la lista delle consegne del task selezionato da parte degli
 * studenti iscritti alla classe. Il docente potrà selezionare una consegna dalla
 * lista e valutarla
 */
public class GUIConsegna extends JFrame {

	private static final long serialVersionUID = 1L;
	private static final String FONT_FAMILY = "Tahoma";
	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel model; /////
	private JTextField votoField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
            try {
                GUIConsegna frame = new GUIConsegna();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
	}

	/**
	 * Create the frame.
	 */
	public GUIConsegna() {
		SessioneDocente docente = SessioneDocente.getInstance();
		String pkTask = docente.getPkTaskSelezionato();
		String nomeTaskSelezionato = docente.getNomeTaskSelezionato();
		final List<DTOConsegna> consegne = Controller.getConsegne(pkTask);

		///
		setResizable(false);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 650);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(213, 241, 247));
		contentPane.setBorder(new LineBorder(new Color(0, 0, 0)));
		///

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel nomeTask = new JLabel(nomeTaskSelezionato);
		nomeTask.setHorizontalAlignment(SwingConstants.CENTER);
		nomeTask.setFont(new Font(FONT_FAMILY, Font.BOLD, 30));
		nomeTask.setBounds(330, 28, 255, 64);
		contentPane.add(nomeTask);
		
		JButton indietroBtn = new JButton("indietro");
		indietroBtn.addActionListener(e -> {
        });
		indietroBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				GUIClasseDocente seconda = new GUIClasseDocente(); /// Crea nuova finestra
				seconda.setVisible(true); /// Mostra nuova finestra
				dispose(); /// Chiude questa finestra
			}
		});
		indietroBtn.setBounds(10, 11, 89, 23);
		contentPane.add(indietroBtn);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(45, 183, 326, 295);
		contentPane.add(scrollPane);
		
		table = new JTable();
		model = new DefaultTableModel(
				new Object[][] {}, /// Inizia vuoto
				new String[] { "ID" } /// Nome colonna
			){
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		table.setModel(model);
		if (!consegne.isEmpty()){
			for (DTOConsegna c : consegne) {
				if (c.getPunteggio()==-1){
					model.addRow(new Object[] { c.getId() });
				}
			}
		}
		scrollPane.setViewportView(table);
		
		JLabel lblListaConsegna = new JLabel("Lista Consegne");
		lblListaConsegna.setHorizontalAlignment(SwingConstants.CENTER);
		lblListaConsegna.setFont(new Font(FONT_FAMILY, Font.BOLD, 30));
		lblListaConsegna.setBounds(52, 116, 290, 56);
		contentPane.add(lblListaConsegna);
		
		JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setBounds(458, 185, 367, 223);
		contentPane.add(textArea);
		textArea.setLineWrap(true);         /// abilita il ritorno a capo
		textArea.setWrapStyleWord(true);    /// va a capo tra parole (non a metà parola)
		textArea.setText("Seleziona una consegna per visualizzare qui l'elaborato");
		
		JLabel lblElaborato = new JLabel("Elaborato");
		lblElaborato.setHorizontalAlignment(SwingConstants.CENTER);
		lblElaborato.setFont(new Font(FONT_FAMILY, Font.BOLD, 30));
		lblElaborato.setBounds(481, 116, 290, 56);
		contentPane.add(lblElaborato);
		
		JButton btnValuta = new JButton("valuta");
		btnValuta.setBounds(500, 436, 89, 23);
		btnValuta.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try{
					int voto = Integer.parseInt(votoField.getText());
					if (voto<0) throw new Exception();
					int selectedRow = table.getSelectedRow();
					if (selectedRow != -1 && btnValuta.isEnabled()) {
						int res = Controller.valutaConsegna(pkTask,String.valueOf(model.getValueAt(selectedRow, 0)),voto);
						if(res==1){
							JOptionPane.showMessageDialog(null, "Valutazione fatta");
						} else {
							JOptionPane.showMessageDialog(null, "Errore nella valutazione");
						}
					}else {
						JOptionPane.showMessageDialog(null, "Nessuna riga selezionata.");
					}
				}catch (Exception err){
					JOptionPane.showMessageDialog(null, "Inserisci input numerico non negativo.");
				}
			}
		});
		btnValuta.setEnabled(false);
		contentPane.add(btnValuta);
		
		votoField = new JTextField();
		votoField.setBounds(619, 437, 122, 20);
		contentPane.add(votoField);
		votoField.setColumns(10);
		
		JLabel lblVoto = new JLabel("voto");
		lblVoto.setFont(new Font(FONT_FAMILY, Font.PLAIN, 14));
		lblVoto.setHorizontalAlignment(SwingConstants.CENTER);
		lblVoto.setBounds(653, 419, 46, 14);
		contentPane.add(lblVoto);
		
		JButton aggiornaButton = new JButton("aggiorna");
		aggiornaButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				GUIConsegna seconda = new GUIConsegna(); /// Crea nuova finestra
				seconda.setVisible(true); /// Mostra nuova finestra
				dispose(); /// Chiude questa finestra
			}
		});
		aggiornaButton.setBounds(785, 11, 89, 23);
		contentPane.add(aggiornaButton);
		
		JButton btnSeleziona = new JButton("seleziona");
		btnSeleziona.setBounds(45, 489, 89, 23);
		btnSeleziona.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int selectedRow = table.getSelectedRow();
				if (selectedRow != -1) {
					String soluzione = consegne.get(selectedRow).getSoluzione();
					textArea.setText(soluzione);
					btnValuta.setEnabled(true);
				}else {
					JOptionPane.showMessageDialog(null, "Nessuna riga selezionata.");
				}
			}
		});
		contentPane.add(btnSeleziona);
	}
}
