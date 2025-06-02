package taskdidatticiNEW;

import controller.Controller;
import DTO.DTOTask;

import java.awt.EventQueue;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;


import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GUIConsegnaStudente extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUIConsegnaStudente frame = new GUIConsegnaStudente();
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
	public GUIConsegnaStudente() {

		SessioneStudente studente = SessioneStudente.getInstance();
		DTOTask task = Controller.getTask(studente.getPkTask());
		// da eliminare
		//task = new DTOTask(1,"titolo di task1","descrizione", "12/08/09", 20);

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
		
		JButton indietroBtn = new JButton("indietro");
		indietroBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		indietroBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				GUIStudente seconda = new GUIStudente(); // Crea nuova finestra
				seconda.setVisible(true); // Mostra nuova finestra
				dispose(); // Chiude questa finestra
			}
		});
		indietroBtn.setBounds(10, 11, 89, 23);
		contentPane.add(indietroBtn);
		
		JLabel lblNomeTask = new JLabel(task.getTitolo());
		lblNomeTask.setHorizontalAlignment(SwingConstants.CENTER);
		lblNomeTask.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblNomeTask.setBounds(146, 52, 600, 56);
		contentPane.add(lblNomeTask);
		
		JLabel lblTitolo = new JLabel("Titolo :");
		lblTitolo.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblTitolo.setBounds(75, 138, 46, 14);
		contentPane.add(lblTitolo);
		
		JLabel lblDesc = new JLabel("Descrizione :");
		lblDesc.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDesc.setBounds(75, 248, 101, 14);
		contentPane.add(lblDesc);
		
		JLabel lblData = new JLabel("Data Scadenza :");
		lblData.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblData.setBounds(75, 174, 101, 14);
		contentPane.add(lblData);
		
		JLabel lblMax = new JLabel("Massimo Punteggio :");
		lblMax.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblMax.setBounds(75, 211, 140, 14);
		contentPane.add(lblMax);

		JTextArea textArea = new JTextArea();
		textArea.setBounds(103, 301, 672, 218);
		contentPane.add(textArea);

		JButton btnNewButton = new JButton("Consegna");
		btnNewButton.setBounds(388, 547, 101, 29);
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String soluzione = textArea.getText();
				if (soluzione.equals("")) {
					JOptionPane.showMessageDialog(null, "La soluzione è vuota");
				} else {
					int res = Controller.consegnaSoluzione(String.valueOf(studente.getIdStudente()),studente.getPkTask(),soluzione);
					if(res == 1) {
						JOptionPane.showMessageDialog(null, "Soluzione consegnata con successo");
						btnNewButton.setEnabled(false);
					}else {
						JOptionPane.showMessageDialog(null, "Errore nella consegna");
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