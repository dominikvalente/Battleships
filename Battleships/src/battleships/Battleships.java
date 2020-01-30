package battleships;
import java.awt.*;
import javax.swing.*;

public class Battleships extends JPanel {
	
	public static int row = 10;
	public static int column = 10;
	public static Stats s;
	public static Board b;
	public static Game game;

	public Battleships() {
		s = new Stats();
		b = new Board(new Game(row, column), s);
	}
	
	public void init() {
		try {
			SwingUtilities.invokeAndWait(new Runnable() {
				public void run() {
					setLayout(new BorderLayout());
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		Battleships app = new Battleships();
		app.init();

		JFrame frame = new JFrame("Battleships");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(601,601);
		frame.add(b, BorderLayout.CENTER);
		frame.add(s, BorderLayout.PAGE_END);
		frame.pack();
		frame.setVisible(true);
		System.out.println("retries " + game.retries);
	}
}
