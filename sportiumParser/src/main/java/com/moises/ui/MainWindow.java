package com.moises.ui;

import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;

import org.json.JSONObject;

import com.moises.sportiumParser.EventDetector;
import com.moises.sportiumParser.EventParser;

public class MainWindow {

	private JFrame frame;
	private JTextField txtInputResultHere;
	private JButton btnNewButton;
	private JTextPane textPane;
	
	private EventDetector detector;
	private EventParser parser;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		this.detector = new EventDetector();
		this.parser = new EventParser();
		frame = new JFrame();
		frame.setBounds(100, 100, 700, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{88, 406, 97, 0};
		gridBagLayout.rowHeights = new int[]{23, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
		frame.getContentPane().setLayout(gridBagLayout);
		
		txtInputResultHere = new JTextField();
		txtInputResultHere.setToolTipText("Input result here");
		txtInputResultHere.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_txtInputResultHere = new GridBagConstraints();
		gbc_txtInputResultHere.fill = GridBagConstraints.BOTH;
		gbc_txtInputResultHere.insets = new Insets(0, 0, 5, 5);
		gbc_txtInputResultHere.gridx = 1;
		gbc_txtInputResultHere.gridy = 1;
		frame.getContentPane().add(txtInputResultHere, gbc_txtInputResultHere);
		txtInputResultHere.setColumns(50);
		
		btnNewButton = new JButton("Submit Result");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
			}
		});
		btnNewButton.setToolTipText("Press to parse the result");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String textToParse = txtInputResultHere.getText();
				String event = detector.getEventTypeFromString(textToParse);
				JSONObject json = parser.toJSON(event, textToParse);
				//String json = parser.toJSON(event, textToParse);
				textPane.setVisible(true);
				textPane.setText(json.toString());
			}
		});
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton.anchor = GridBagConstraints.NORTHWEST;
		gbc_btnNewButton.gridx = 2;
		gbc_btnNewButton.gridy = 1;
		frame.getContentPane().add(btnNewButton, gbc_btnNewButton);
		
		textPane = new JTextPane();
		textPane.setVisible(false);
		GridBagConstraints gbc_textPane = new GridBagConstraints();
		gbc_textPane.gridwidth = 3;
		gbc_textPane.insets = new Insets(0, 0, 0, 5);
		gbc_textPane.fill = GridBagConstraints.BOTH;
		gbc_textPane.gridx = 0;
		gbc_textPane.gridy = 2;
		frame.getContentPane().add(textPane, gbc_textPane);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
	}

}
