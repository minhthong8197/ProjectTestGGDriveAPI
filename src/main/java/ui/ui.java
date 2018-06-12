package ui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import mypack.Start;




public class ui {
	private JFrame mainFrame;
	private JLabel headerLabel;
	private JLabel statusLabel;
	private JPanel controlPanel;
	private JPanel controlPanel2;
	private JLabel resultLabel;
	private java.io.File file;

	public ui() {
		prepareGUI();
	}

	public static void main(String[] args) {
		ui main = new ui();
		main.showFileChooserDemo();
	}

	private void prepareGUI() {
		mainFrame = new JFrame("DriveUpfile");
		mainFrame.setSize(400, 500);
		mainFrame.setLayout(new GridLayout(5, 1));// 5 hang 1 cot

		mainFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowEvent) {
				System.exit(0);
			}
		});
		headerLabel = new JLabel("", JLabel.CENTER);
		statusLabel = new JLabel("", JLabel.CENTER);
		resultLabel = new JLabel("", JLabel.CENTER);
		statusLabel.setSize(350, 100);

		controlPanel = new JPanel();
		controlPanel.setLayout(new FlowLayout());

		controlPanel2 = new JPanel();
		controlPanel2.setLayout(new FlowLayout());

		mainFrame.add(headerLabel);
		mainFrame.add(controlPanel);
		mainFrame.add(statusLabel);
		mainFrame.add(controlPanel2);
		mainFrame.add(resultLabel);
		mainFrame.setVisible(true);
	}

	private void showFileChooserDemo() {
		headerLabel.setText("Select file for upload");
		final JFileChooser fileDialog = new JFileChooser();
		JButton showFileDialogButton = new JButton("Open File");
		final JButton uploadButton = new JButton("Upload");

		uploadButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Start.upload(file);
					resultLabel.setText("Da up xong");
				} catch (Exception e1) {
					resultLabel.setText("Tai len that bai");
				}
				
			}
		});
		
		showFileDialogButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int returnValue = fileDialog.showOpenDialog(mainFrame);

				if (returnValue == JFileChooser.APPROVE_OPTION) {
					file = fileDialog.getSelectedFile();
					statusLabel.setText("File Selected :" + file.getName());

					controlPanel2.add(uploadButton);
				} else {
					statusLabel.setText("No file was selected.");
				}
			}
		});
		controlPanel.add(showFileDialogButton);
		mainFrame.setVisible(true);
	}
}