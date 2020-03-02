package taskqueue.client.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import taskqueue.client.RunClient;


public class Frame extends JFrame {
	int x = 0;
	int y = 0;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected Thread appThread;
	protected RunClient runner; 
	
	public void initFrame() {
		
		JPanel panel = new JPanel() {
			@Override
			public void paint(Graphics g) {
				super.paint(g);
				// setBackground(Color.YELLOW);
				// g.setColor( Color.yellow);
				g.fillRoundRect(x, y, 5, 5, 5, 5);
			}
		};
		JButton startTask1 = new JButton("Start task 1");
		startTask1.setLocation(10, 10);
		startTask1.setVisible(true);
		startTask1.setEnabled(true);
		startTask1.setMinimumSize(new Dimension(40, 20));

		JButton startTask2 = new JButton("Start task 2");
		startTask2.setLocation(10, 40);
		startTask2.setVisible(true);
		startTask2.setEnabled(true);
		startTask2.setMinimumSize(new Dimension(40, 20));

		JLabel exQueue = new JLabel();
		exQueue.setBackground(Color.CYAN);
		JLabel waitQueue = new JLabel();
		exQueue.setVisible(true);

		panel.setLayout(new FlowLayout(FlowLayout.LEFT));
		panel.setVisible(true);
		panel.setEnabled(true);

		setMinimumSize(new Dimension(500, 500));
		setPreferredSize(new Dimension(1000, 800));
		panel.setMinimumSize(new Dimension(500, 500));
		panel.add(startTask1);
		panel.add(startTask2);

		panel.add(exQueue);
		panel.add(waitQueue);
		panel.setBackground(Color.YELLOW);
		 x = 50;
		 y = 50;
		startTask1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//try {
					//dos.writeInt(1);
					repaint();
//				} catch (IOException ex) {
//					ex.printStackTrace();
//					;
//				}
			}
		});
		startTask2.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				//try {
					//dos.writeInt(2);
//				} catch (IOException ex) {
//					ex.printStackTrace();
//					;
//				}
			}
		});

		panel.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent e) {
			}

			public void mousePressed(MouseEvent e) {
				int x = e.getX();
				int y = e.getY();
				/*
				 * try { dos.writeInt(x); dos.writeInt(y); } catch (java.io.IOException ex) {
				 * ex.printStackTrace(); }
				 */

				repaint();
			}

			public void mouseReleased(MouseEvent e) {

			}

			public void mouseEntered(MouseEvent e) {

			}

			public void mouseExited(MouseEvent e) {

			}
		});

		add(panel);
	}
	
	public void setThread(Thread appThread) {
		this.appThread = appThread;
	}
	
	public void setRunner(RunClient runner) {
		this.runner = runner;
	}
	
}
