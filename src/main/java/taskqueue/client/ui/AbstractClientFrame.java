package taskqueue.client.ui;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JTextField;

import taskqueue.client.RunClient;

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
	
	
}
