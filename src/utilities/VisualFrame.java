package utilities;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;

import problems.Day10;

public class VisualFrame extends JFrame {

	private JPanel contentPane;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VisualFrame frame = new VisualFrame();
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
	public VisualFrame() {
		final int WIDTH = 400, HEIGHT = 300;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(50, 50, WIDTH, HEIGHT);
		contentPane = new MovingPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
	}
	
	private class MovingPanel extends JPanel{
		private List<MovingPoint> points = new Day10().points;
		private Timer timer;
		int time = 0;
		
		public MovingPanel() {
			for(MovingPoint p : points) {
				p.move(10775);
			}
		}
		
		
		@Override
		public void paint(Graphics g) {
			for(MovingPoint p : points) {
				drawPoint(p, g);
			}
		}
		public void movePoints(int times) {
			for(MovingPoint p : points) {
				p.move(times);
			}
		}
		public void drawPoint(MovingPoint p, Graphics g) {
			
			g.fillRect(p.x, p.y, 2,2);
		}
	}

}
