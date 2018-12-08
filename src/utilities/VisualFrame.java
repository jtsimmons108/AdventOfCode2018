package utilities;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import problems.Day6;

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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 800);
		contentPane = new JPanel() {

			@Override
			public void paint(Graphics g) {
				Random r = new Random();
				Day6 day = new Day6();
				day.getPart1Solution();
				int[][] grid = day.getGrid();
				
				Map<Integer, Color> colors = new HashMap<>();
				colors.put(-1, Color.BLACK);
				for(int x = 0; x < grid.length; x++) {
					for(int y = 0; y < grid.length; y++) {
						int val = grid[y][x];
						if (!colors.containsKey(val)) {
							colors.put(val, new Color(r.nextFloat(), r.nextFloat(), r.nextFloat()));
						}
						g.setColor(colors.get(val));
						g.fillRect(2*x, 2*y, 2, 2);
					}
				}
			}
			
		};
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
	}

}
