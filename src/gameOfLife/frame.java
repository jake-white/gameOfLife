package gameOfLife;

import java.awt.Container;

import javax.swing.JFrame;

public class frame {
	public static JFrame frame = new JFrame("Conway's Game of Life");
	static cellPanel panel = new cellPanel();

	public static void main(String[] args) {

		frame.setSize(1300, 740);
		Container pane = frame.getContentPane();
		pane.add(panel);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panel.customConfig();
		frame.addKeyListener(panel);
		panel.addMouseListener(panel);
		panel.level.start();
	}
}
