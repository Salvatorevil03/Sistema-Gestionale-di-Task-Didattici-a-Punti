package boundary;

import controller.Controller;
import dto.DTOTask;
import exceptions.*;

import java.awt.EventQueue;

import javax.swing.*;
import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;


import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

/**
 * Classe che rappresenta la finestra GUI di gestione della classe del docente.
 * <br> Il docente accede a questa GUI selezionando una classe da lui creata
 * nella GUI {@link GUIDocente}.
* <br>Qui il docente può:
 * <ul>
 *     <ul>
 *         <li>Creare un nuovo Task</li>
 *         <li>Selezionare un Task dalla lista dei Task della classe selezionata
 *         per visualizzare le consegne relative a quel determinato Task nella GUI
 *         {@link GUIConsegna}</li>
 *         <li>Visualizzare la lista degli studenti iscritti alla classe accedendo
 *         alla GUI {@link GUIListaStudentiClasse}</li>
 *         <li>Tornare indietro alla GUI {@link GUIDocente} per effettuare ulteriori
 *         operazioni</li>
 *     </ul>
 * </ul>
 */
public class GUIClasseDocente extends JFrame {

	private static final long serialVersionUID = 1L;
	private static final String FONT_FAMILY = "Tahoma";
	private JPanel contentPane;
	private JTextField titoloField;
	private JTextField descField;
	private JTextField dataField;
	private JTextField maxField;
	private JTable table;
	private DefaultTableModel model; //

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
            try {
                GUIClasseDocente frame = new GUIClasseDocente();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
	}

	/**
	 * Create the frame.
	 */

	/**
	 *	Necessario passare la stringa nel formato yyyy-mm-dd per l'utilizzo corretto
	 *	dei metodi isValidDate e isDateInFuture che utilizzano i moduli LocalDate,
	 *	DateTimeFormatter e DateTimeParseException.
	 *
	 *	<p>Serve inoltre un formato omogeneo da passare al DB.</p>
	 *
	 *	<p>Il controllo viene fatto dalla GUIClasseDocente prima che la String data sia
	 *	passata a livello Controller</p>
	 */

	public GUIClasseDocente() {
		SessioneDocente docente = SessioneDocente.getInstance();
		String nomeClasse = docente.getNomeClasseSelezionato();
		List<DTOTask> tasks = Controller.getTasks(docente.getPkClasseSelezionata());


		setResizable(false);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 650);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(213, 241, 247));
		contentPane.setBorder(new LineBorder(new Color(0, 0, 0)));


		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel labelNomeClasse = new JLabel(nomeClasse);
        labelNomeClasse.setHorizontalAlignment(SwingConstants.CENTER);
        labelNomeClasse.setFont(new Font(FONT_FAMILY, Font.BOLD, 30));
        labelNomeClasse.setBounds(316, 50, 255, 64);
		contentPane.add(labelNomeClasse);

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

		JButton btnListaStudenti = new JButton("Lista Studenti");
		btnListaStudenti.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				GUIListaStudentiClasse seconda = new GUIListaStudentiClasse();
				seconda.setVisible(true);
				dispose();
			}
		});
		btnListaStudenti.setFont(new Font(FONT_FAMILY, Font.PLAIN, 18));
		btnListaStudenti.setBounds(91, 483, 155, 75);
		contentPane.add(btnListaStudenti);

		JLabel lblCreaTask = new JLabel("Crea Task");
		lblCreaTask.setHorizontalAlignment(SwingConstants.CENTER);
		lblCreaTask.setFont(new Font(FONT_FAMILY, Font.BOLD, 30));
		lblCreaTask.setBounds(45, 122, 208, 56);
		contentPane.add(lblCreaTask);

		JLabel lblTitolo = new JLabel("Titolo");
		lblTitolo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitolo.setFont(new Font(FONT_FAMILY, Font.PLAIN, 13));
		lblTitolo.setBounds(45, 201, 84, 14);
		contentPane.add(lblTitolo);

		titoloField = new JTextField();
		titoloField.setColumns(10);
		titoloField.setBounds(149, 199, 130, 20);
		contentPane.add(titoloField);

		JLabel lblDescrizione = new JLabel("Descrizione");
		lblDescrizione.setHorizontalAlignment(SwingConstants.CENTER);
		lblDescrizione.setFont(new Font(FONT_FAMILY, Font.PLAIN, 13));
		lblDescrizione.setBounds(45, 242, 84, 14);
		contentPane.add(lblDescrizione);

		descField = new JTextField();
		descField.setColumns(10);
		descField.setBounds(149, 240, 130, 20);
		contentPane.add(descField);

		JLabel lblData = new JLabel("Data Scadenza");
		lblData.setHorizontalAlignment(SwingConstants.CENTER);
		lblData.setFont(new Font(FONT_FAMILY, Font.PLAIN, 13));
		lblData.setBounds(28, 274, 121, 14);
		contentPane.add(lblData);

		JLabel lblDataFormat = new JLabel("(yyyy-mm-dd)");
		lblDataFormat.setHorizontalAlignment(SwingConstants.CENTER);
		lblDataFormat.setFont(new Font(FONT_FAMILY, Font.PLAIN, 13));
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
		lblPunti.setFont(new Font(FONT_FAMILY, Font.PLAIN, 13));
		lblPunti.setBounds(45, 333, 84, 14);
		contentPane.add(lblPunti);

		JButton btnCrea = new JButton("crea");
		btnCrea.setFont(new Font(FONT_FAMILY, Font.PLAIN, 14));
		btnCrea.setBounds(113, 380, 89, 23);
		btnCrea.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try{
					if (titoloField.getText().equals("") || descField.getText().equals("") || dataField.getText().equals("") || maxField.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "Alcuni campi sono vuoti", "Missing parameters", JOptionPane.ERROR_MESSAGE);

					} else {
						String titolo = titoloField.getText();
						String descrizione = descField.getText();
						String data = dataField.getText();

						if (!data.matches("\\d{4}-\\d{2}-\\d{2}")) {
							throw new FormattedDateException();
						}
						if(!isValidDate(data) || !isDateInFuture(data)){
							throw new InvalidDateException();
						}

						if(titolo.length()>25) {
							throw new TitleLengthException();
						}
						if(descrizione.length()>200) {
							throw new DescriptionLengthException();
						}
						int punti = Integer.parseInt(maxField.getText());
						if(punti<=0 || punti>100){
							throw new UnassignableTaskScoreException();
						}


						Controller.creaTask(docente.getPkClasseSelezionata(), titolo, descrizione, data, punti);
						JOptionPane.showMessageDialog(null, "Task creato con successo", "Info", JOptionPane.INFORMATION_MESSAGE);

					}
				}catch(FormattedDateException | InvalidDateException | TitleLengthException |
					   DescriptionLengthException | UnassignableTaskScoreException | TaskCreationException exception) {
					JOptionPane.showMessageDialog(null, exception.getMessage(),"Error", JOptionPane.ERROR_MESSAGE);
				}catch(NumberFormatException exception){
					JOptionPane.showMessageDialog(null, "È stato inserito un tipo non conforme per il punteggio del Task. Inserire un valore numerico intero","Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		contentPane.add(btnCrea);

		JLabel lblListaStudenti = new JLabel("Lista Studenti");
		lblListaStudenti.setHorizontalAlignment(SwingConstants.CENTER);
		lblListaStudenti.setFont(new Font(FONT_FAMILY, Font.BOLD, 30));
		lblListaStudenti.setBounds(63, 416, 208, 56);
		contentPane.add(lblListaStudenti);

		JLabel lblListaTask = new JLabel("Lista Task");
		lblListaTask.setHorizontalAlignment(SwingConstants.CENTER);
		lblListaTask.setFont(new Font(FONT_FAMILY, Font.BOLD, 30));
		lblListaTask.setBounds(534, 122, 208, 56);
		contentPane.add(lblListaTask);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(437, 201, 413, 293);
		contentPane.add(scrollPane);

		table = new JTable();
		model = new DefaultTableModel(
				new Object[][] {},
				new String[] { "ID","Titolo","Data Scadenza","Max Punteggio" }
			){
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		table.setModel(model);
		if (!tasks.isEmpty()){
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
					GUIConsegna seconda = new GUIConsegna();
					seconda.setVisible(true);
					dispose();
				} else {
					JOptionPane.showMessageDialog(null, "Nessuna riga selezionata.", "Row error", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		btnSeleziona.setBounds(602, 512, 89, 23);
		contentPane.add(btnSeleziona);

		JButton aggiornaButton = new JButton("aggiorna");
		aggiornaButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				GUIClasseDocente seconda = new GUIClasseDocente();
				seconda.setVisible(true);
				dispose();
			}
		});
		aggiornaButton.setBounds(785, 11, 89, 23);
		contentPane.add(aggiornaButton);
	}

	/**
     *	METODO DI VERIFICA DELLA FORMATTAZIONE DELLA DATA
	 *
	 * <p>1)Verifica formattazione della String date:
	 * <br>Se la data non è correttamente formattata solleva un DateTimeParseException</br></p>
	 *
	 * <p>2)Verifica che la data parsata corrisponda alla stringa originale</p>
     */
	private boolean isValidDate(String date) {
		try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate parsedDate = LocalDate.parse(date, formatter);

			String formattedBack = parsedDate.format(formatter);
			return date.equals(formattedBack);

		} catch (DateTimeParseException e) {
			return false;
		}
	}

    /**
     *METODO CHE VERIFICA CHE LA DATA SELEZIONATA NON SIA UNA DATA PASSATA
	 *
	 * <p>Verifica formattazione della String date.</p>
	 * <p>Se la data non è correttamente formattata solleva una
	 * DateTimeParseException.</p>
	 *
	 * <p>Successivamente, mediante il metodo LocalDate.now(), viene
	 * generata una LocalDate della data odierna definita a partire dal
	 * TimeZone del sistema (Italy: UTC+1)</p>
     */

	private boolean isDateInFuture(String dateString) {
		try {

			LocalDate inputDate = LocalDate.parse(dateString);
			LocalDate today = LocalDate.now();

			return inputDate.isAfter(today) || inputDate.isEqual(today);

		} catch (DateTimeParseException e) {
			return false; // Se la data non è parsable, consideriamo non valida
		}
	}
}