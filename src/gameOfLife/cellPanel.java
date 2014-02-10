package gameOfLife;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;
import javax.swing.Timer;

public class cellPanel extends JPanel implements KeyListener, MouseListener {
	Rectangle[] cell = new Rectangle[7000]; // 7000 10x10 cells, 100 per row/70
											// per column
	boolean[] alive = new boolean[7000];
	boolean[] next_alive = new boolean[7000];
	public boolean started = false;
	int[] n = new int[7000];
	public Timer checklife = new Timer(50, new checker());
	public Timer level = new Timer(10, new leveleditor());
	public Point mouse;
	public int sum, startnum;

	public void customConfig() {
		for (int i = 0; i < 7000; ++i) {
			double division_x = (i % 100) * 10;
			double division_y = ((i / 100) - (i % 70) / 70) * 10;
			cell[i] = new Rectangle((int) Math.round(division_x),
					(int) Math.round(division_y), 10, 10);
		}
	}

	public void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		if(!started)
		{
			for(int i = 0; i <= 100; ++i)
			{
				g2d.drawLine(i * 10, 0, i * 10, 1000);
			}
			for(int i = 0; i <= 70; ++i)
			{
				g2d.drawLine(0, i * 10, 1000, i * 10);
			}
		}
		sum = 0;
		g2d.setColor(Color.BLACK);
		g2d.drawString("Click on cells to design your pattern.", 1050, 10);
		g2d.drawString("Press ENTER to start/stop the automation!", 1050, 30);
		for (int i = 0; i < 7000; ++i) {
			if (alive[i]) {
				++sum;
				g2d.setColor(Color.BLACK);
				try {
					g2d.fill(cell[i]);
					g2d.setColor(Color.RED);
					// g2d.setFont(g.getFont().deriveFont(7.0F));
					// g2d.drawString("" + i, cell[i].x, cell[i].y);
					// g2d.drawString("" + n[i], cell[i].x, cell[i].y);
				} catch (Exception e) {

				}
			}
		}
		g2d.drawString("Started with: " + startnum, 1050, 50);
		g2d.drawString("Currently have: " + sum, 1050, 70);
	}

	public class checker implements ActionListener {

		public void actionPerformed(ActionEvent arg0) {
			for (int i = 0; i < 7000; ++i) {
				next_alive[i] = alive[i];
			}
			for (int i = 0; i < 7000; ++i) {
				n[i] = 0;
				try {
					if (alive[i + 1] && (i + 1) % 100 != 0)
						++n[i];
				} catch (Exception e) {

				}
				try {
					if (alive[i - 1] && i % 100 != 0)
						++n[i];
				} catch (Exception e) {

				}
				try {
					if (alive[i - 100])
						++n[i];
				} catch (Exception e) {
				}
				try {
					if (alive[i + 100])
						++n[i];
				} catch (Exception e) {

				}
				try {
					if (alive[i + 99] && i % 100 != 0)
						++n[i];
				} catch (Exception e) {

				}
				try {
					if (alive[i + 101] && (i + 1) % 100 != 0)
						++n[i];
				} catch (Exception e) {

				}
				try {
					if (alive[i - 99] && (i + 1) % 100 != 0)
						++n[i];
				} catch (Exception e) {

				}
				try {
					if (alive[i - 101] && i % 100 != 0)
						++n[i];
				} catch (Exception e) {

				}
				if (n[i] < 2)
					next_alive[i] = false;
				if ((n[i] == 2 || n[i] == 3) && alive[i])
					next_alive[i] = true;
				if (n[i] == 3 && !alive[i]) {
					next_alive[i] = true;
				}
				if (n[i] > 3)
					next_alive[i] = false;
			}
			for (int i = 0; i < 7000; ++i) {
				alive[i] = next_alive[i];
			}
			frame.frame.repaint();
		}
	}

	public class leveleditor implements ActionListener {

		public void actionPerformed(ActionEvent arg0) {
			frame.frame.repaint();
		}
	}

	public void keyPressed(KeyEvent arg0) {
		if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {
			if(started)
			{
				started = false;
				startnum = 0;
				for(int i = 0; i < 7000; ++i)
				{
					alive[i] = false;
				}
				checklife.stop();
				level.start();
			}
			else
			{
			started = true;
			level.stop();
			checklife.start();
			}

		}

	}

	public void keyReleased(KeyEvent arg0) {

	}

	public void keyTyped(KeyEvent arg0) {

	}

	public void mouseClicked(MouseEvent arg0) {
		if (!started) {
			mouse = arg0.getPoint();
			for (int i = 0; i < 7000; ++i) {
				try {
					if (cell[i].contains(mouse))
					{
						alive[i] = !alive[i];
						if(alive[i])
							++startnum;
						else
							--startnum;
					}
				} catch (Exception e) {

				}
			}
		}

	}

	public void mouseEntered(MouseEvent arg0) {

	}

	public void mouseExited(MouseEvent arg0) {

	}

	public void mousePressed(MouseEvent arg0) {

	}

	public void mouseReleased(MouseEvent arg0) {
		mouse = null;

	}
}
