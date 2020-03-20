package taskqueue.client.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.io.File;


import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import taskqueue.client.RunClient;



public class Frame extends AbstractClientFrame {
	int x = 0;
	int y = 0;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	

	public void initFrame() {
		
		// Prepairing main tab
		  // File uploading box
		fpicker = new JFilePicker("Pick a task", "...");
		fpicker.setMode(JFilePicker.MODE_OPEN);
		uploadButton = new JButton("Upload");
		uploadButton.setLocation(10, 10);
		uploadButton.setVisible(true);
		uploadButton.setEnabled(true);
		uploadButton.setMinimumSize(new Dimension(40, 20));
		JPanel uploadBox = new JPanel(new FlowLayout(FlowLayout.LEFT));
		uploadBox.add(fpicker);
		uploadBox.add(uploadButton);
		
		  // Login box 
		JLabel signin = new JLabel("Authorize");
		signinTextField = new JTextField();
		signinTextField.setText("Enter id here");
		signinTextField.setColumns(28);
		signinTextField.setMinimumSize(new Dimension(1300,signinTextField.getMinimumSize().height));
		signInButton = new JButton();
		signInButton.setText("Log in");
		signUpButton = new JButton();
		signUpButton.setText("Register");
		JPanel loginButtons = new JPanel(new FlowLayout(FlowLayout.LEFT));
		loginButtons.add(signin);
		loginButtons.add(signinTextField);
		loginButtons.add(signInButton);
		loginButtons.add(signUpButton);
		
		JPanel mainTabBox = new JPanel();
		mainTabBox.setLayout(new BoxLayout(mainTabBox,BoxLayout.Y_AXIS));
		mainTabBox.add(loginButtons);
		mainTabBox.add(uploadBox);
		mainTabBox.setBackground(Color.YELLOW);
		JPanel mainTabPanel = new JPanel(new BorderLayout());
		mainTabPanel.add(mainTabBox,BorderLayout.NORTH);
		
		
		// Preparing Works tab
		String [] columnNames = {"id", "File", "Status"};
		
		Object [][] data = {
				{"f3e22111-6204-4b2d-8d54-098597fcb06c", "test1.jar", "Run"}, 
				{"f3e22111-6204-4b2d-8d54-098597fcb06c", "test2.jar", "Run"}
		};
		DefaultTableModel  worksModel = new DefaultTableModel(data, columnNames);
	    worksTable = new JTable(worksModel);
	    //worksTable.setMo
	    
	    worksTable.setPreferredScrollableViewportSize(worksTable.getPreferredSize());
        JScrollPane scrollPane = new JScrollPane(worksTable);
		JPanel workButtons = new JPanel();
		workButtons.setLayout(new FlowLayout(FlowLayout.LEFT));
		worksRefresh = new JButton("Refresh");
		workButtons.add(worksRefresh);
        JPanel worksPan = new JPanel();
        worksPan.setLayout(new BorderLayout());
        worksPan.add(scrollPane);
        worksPan.add(workButtons, BorderLayout.NORTH);
        
		
		// Preparing files tab
        //filesList = new DefaultListModel<String>();
        //JList<String> taskList = new JList<String>(filesList);
        //filesList = new new JList<ClientFile>();
		JScrollPane filescroll = new JScrollPane(filesList);
		JPanel filebuttons = new JPanel();
		filebuttons.setLayout(new FlowLayout(FlowLayout.LEFT));
		filesRefresh = new JButton("Refresh");
		fileRun = new JButton("Run");
		filebuttons.add(filesRefresh);
		filebuttons.add(fileRun);
		JPanel panelFiles = new JPanel();
		panelFiles.setLayout(new BorderLayout());
		panelFiles.add(filebuttons,BorderLayout.NORTH);
		panelFiles.add(filescroll);
		
		// Creating Tabs
        JTabbedPane tabbed = new JTabbedPane();
        tabbed.add("Main",mainTabPanel);
        tabbed.add("Files",panelFiles);
        tabbed.add("Works",worksPan);
        
        add(tabbed);
        
		setMinimumSize(new Dimension(600, 300));
		//setPreferredSize(new Dimension(1000, 800));

        
	}

}
