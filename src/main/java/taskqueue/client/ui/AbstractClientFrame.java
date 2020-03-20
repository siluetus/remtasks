package taskqueue.client.ui;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListModel;

import taskqueue.client.RunClient;
import taskqueue.client.ClientFile;

public abstract class AbstractClientFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2461245483838459699L;

	/**
	 * 
	 */
	protected RunClient runner; 
	protected JButton uploadButton;
	protected JButton signInButton;
	protected JButton signUpButton;
	protected JFilePicker fpicker;
	protected JTextField signinTextField;
	protected JButton filesRefresh;
	protected JButton worksRefresh;
	protected JButton fileRun;
	protected JList<ClientFile> filesList  = new JList<ClientFile>();
	protected JTable worksTable;
	
	public abstract void initFrame();
	
	public void initFrame2() {
		initFrame();
	}

	protected JTable tableOftasks;
	
	public JTable getTableOftasks() {
		return tableOftasks;
	}


	public void setTableOftasks(JTable tableOftasks) {
		this.tableOftasks = tableOftasks;
	}


	public void setRunner(RunClient runner) {
		this.runner = runner;
	}
	
	
	public JButton getUploadButton() {
		return this.uploadButton;
	}
	
	public JButton getSignInButton() {
		return this.signInButton;
	}
	
	public JButton getSignUpButton() {
		return this.signUpButton;
	}
	
	public JTextField getSigninTextField() {
		return this.signinTextField;
	}
	
	public JFilePicker getFilePicker() {
		return this.fpicker;
	}
	
	public JButton getWorksRefreshButton() {
		return worksRefresh;
	}
	
	public JButton getFilesRefreshButton() {
		return filesRefresh;
	}
	
	public JButton getFileRunButton() {
		return fileRun;
	}
	
	public JList<ClientFile> getFilesList(){
		return this.filesList;
	}

	public JTable getWorksTable() {
		return worksTable;
	}
	
	
}
