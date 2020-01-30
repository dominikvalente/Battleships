package battleships;
import java.awt.*;
import javax.swing.*;

public class Stats extends JPanel {

	public int hits;
	public int misses;
	public JLabel totalHits;
	public JLabel totalShots;
	public JLabel totalMisses;

	//Stats JPanel constructor
	public Stats() {
		setPreferredSize(new Dimension(601, 40));
		setBackground(Color.BLUE);

		setLayout(new GridLayout(1, 0));

		// create a new label for the total number of shots, hit and miss marks
		totalShots = new JLabel("", JLabel.CENTER);
		totalShots.setFont(new Font("Consoles", Font.PLAIN, 24));
		totalShots.setForeground(Color.WHITE);

		totalHits = new JLabel("", JLabel.CENTER);
		totalHits.setFont(new Font("Consoles", Font.PLAIN, 24));
		totalHits.setForeground(Color.WHITE);
		
		totalMisses = new JLabel("", JLabel.CENTER);
		totalMisses.setFont(new Font("Consoles", Font.PLAIN, 24));
		totalMisses.setForeground(Color.WHITE);

		add(totalShots);
		add(totalMisses);
		add(totalHits);
	}

	public void setInfo(int hits, int misses) {
		this.hits = hits;
		this.misses = misses;
		updateGameInfo();
	}

	public void updateGameInfo() {
		totalShots.setText("Total Shots: " + (hits + misses));
		totalHits.setText("Total Hits: " + hits);
		totalMisses.setText("Total Misses: " + misses);
	}
}
