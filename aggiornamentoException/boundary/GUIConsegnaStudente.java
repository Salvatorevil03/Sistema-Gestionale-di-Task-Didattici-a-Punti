package boundary;

import controller.Controller;
import dto.DTOTask;
import exceptions.StudentException;
import exceptions.SubmissionException;

import java.awt.EventQueue;

import javax.swing.*;
import java.awt.Color;
import javax.swing.border.LineBorder;


import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Classe che rappresenta la finestra GUI per la consegna di una soluzione da parte di
 * uno studente.
 * <br> Allo studente comparirà questa GUI dopo aver selezionato un task da consegnare
 * all'interno della GUI {@link GUIStudente}.
 * <br> Lo studente fornirà una propria soluzione e potrà consegnarla in attesa della
 * valutazione del docente.
 * <br> Allo studente comparirà un messaggio di consegna confermata, se è la prima volta
 * che invia una consegna per quel task, o un messaggio di errore, nel caso in cui avesse
 * già effettuato una consegna per quel task o nel caso in cui violi i parametri imposti
 * per la consegna
 */
public class GUIConsegnaStudente extends JFrame {

	private static final long serialVersionUID = 1L;
	private static final String FONT_FAMILY = "Tahoma";
	private JPanel contentPane;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
            try {
                GUIConsegnaStudente frame = new GUIConsegnaStudente();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
	}

	/**
	 * Create the frame.
	 */
	public GUIConsegnaStudente() {

		SessioneStudente studente = SessioneStudente.getInstance();
		DTOTask task = Controller.getTask(studente.getPkTask());

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
		
		JButton indietroBtn = new JButton("indietro");
		indietroBtn.addActionListener(e -> {
        });
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
		
		JLabel lblNomeTask = new JLabel(task.getTitolo());
		lblNomeTask.setHorizontalAlignment(SwingConstants.CENTER);
		lblNomeTask.setFont(new Font(FONT_FAMILY, Font.BOLD, 30));
		lblNomeTask.setBounds(146, 52, 600, 56);
		contentPane.add(lblNomeTask);
		
		JLabel lblTitolo = new JLabel("Titolo :");
		lblTitolo.setFont(new Font(FONT_FAMILY, Font.BOLD, 11));
		lblTitolo.setBounds(75, 138, 46, 14);
		contentPane.add(lblTitolo);
		
		JLabel lblDesc = new JLabel("Descrizione :");
		lblDesc.setFont(new Font(FONT_FAMILY, Font.BOLD, 11));
		lblDesc.setBounds(75, 248, 101, 14);
		contentPane.add(lblDesc);
		
		JLabel lblData = new JLabel("Data Scadenza :");
		lblData.setFont(new Font(FONT_FAMILY, Font.BOLD, 11));
		lblData.setBounds(75, 174, 101, 14);
		contentPane.add(lblData);
		
		JLabel lblMax = new JLabel("Massimo Punteggio :");
		lblMax.setFont(new Font(FONT_FAMILY, Font.BOLD, 11));
		lblMax.setBounds(75, 211, 140, 14);
		contentPane.add(lblMax);

		JTextArea textArea = new JTextArea();
		textArea.setBounds(103, 301, 672, 218);
		contentPane.add(textArea);
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);

		JButton btnNewButton = new JButton("Consegna");
		btnNewButton.setBounds(388, 547, 101, 29);
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String soluzione = textArea.getText();
				if (soluzione.equals("")) {
					JOptionPane.showMessageDialog(null, "La soluzione è vuota");
				}else if (soluzione.length() > 10000){
					JOptionPane.showMessageDialog(null, "La soluzione è troppo lunga");
				} else {
					if (btnNewButton.isEnabled()){
						try{
							Controller.consegnaSoluzione(String.valueOf(studente.getIdStudente()),studente.getPkTask(),soluzione);
							JOptionPane.showMessageDialog(null, "Soluzione consegnata con successo");
							btnNewButton.setEnabled(false);
						}catch(SubmissionException | StudentException exception) {
							JOptionPane.showMessageDialog(null, exception.getMessage());
						}
					} else {
						JOptionPane.showMessageDialog(null, "Soluzione già consegnata");
					}
				}
			}
		});
		contentPane.add(btnNewButton);

		
		JLabel lblTitoloOut = new JLabel(task.getTitolo());
		lblTitoloOut.setBounds(130, 138, 627, 14);
		contentPane.add(lblTitoloOut);
		
		JLabel lblDataOut = new JLabel(task.getDataScadenza());
		lblDataOut.setBounds(186, 174, 571, 14);
		contentPane.add(lblDataOut);
		
		JLabel lblMaxOut = new JLabel(String.valueOf(task.getMaxPuntiAssegnabili()));
		lblMaxOut.setBounds(213, 211, 500, 14);
		contentPane.add(lblMaxOut);
		
		JLabel lblDescOut = new JLabel(task.getDescrizione());
		lblDescOut.setBounds(172, 248, 592, 14);
		contentPane.add(lblDescOut);
		
	}
}