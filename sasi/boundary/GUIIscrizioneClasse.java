package taskdidatticiNEW;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.SpringLayout;
import java.awt.BorderLayout;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import javax.swing.JList;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;


import javax.swing.JSplitPane;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GUIIscrizioneClasse extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField codiceField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUIIscrizioneClasse frame = new GUIIscrizioneClasse();
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
	public GUIIscrizioneClasse() {
		//
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//MOLTO IMPORTANTE
		setBounds(100, 100, 500, 250);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(213, 241, 247));
		contentPane.setBorder(new LineBorder(new Color(0, 0, 0)));
		//

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel titolo = new JLabel("ISCRIVITI AD UNA CLASSE");
		titolo.setHorizontalAlignment(SwingConstants.CENTER);
		titolo.setFont(new Font("Tahoma", Font.BOLD, 30));
		titolo.setBounds(28, 8, 419, 45);
		contentPane.add(titolo);
		
		JLabel lblServizi = new JLabel("Per usare i servizi della piattaforma");
		lblServizi.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblServizi.setHorizontalAlignment(SwingConstants.CENTER);
		lblServizi.setBounds(86, 52, 299, 30);
		contentPane.add(lblServizi);
		
		JLabel lblCodice = new JLabel("codice");
		lblCodice.setHorizontalAlignment(SwingConstants.CENTER);
		lblCodice.setBounds(169, 93, 46, 14);
		contentPane.add(lblCodice);
		
		codiceField = new JTextField();
		codiceField.setBounds(179, 118, 103, 17);
		contentPane.add(codiceField);
		codiceField.setColumns(10);
		
		JButton iscrizioneButton = new JButton("iscrizione");
		iscrizioneButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
			}
		});
		iscrizioneButton.setBounds(180, 152, 89, 23);
		contentPane.add(iscrizioneButton);
	}
}
