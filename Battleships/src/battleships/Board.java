package battleships;
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;

public class Board extends JPanel {

	public Game board;
	public Stats sPanel;
	public int hits;
	public int misses;

	// game board JPanel constructor
	public Board(Game b, Stats s) {
		board = b;
		sPanel = s;
		hits = 0;
		misses = 0;

		setPreferredSize(new Dimension(601, 601));

		addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				
				boardAttack(e.getX(), e.getY());
				repaint();

				if (board.checkDestroyed()) {
					JOptionPane.showMessageDialog(null, "Finished");
					System.exit(0);
				}
				if (misses > 34) {
					JOptionPane.showMessageDialog(null, "Game over");
					System.exit(0);
				}
			}
		});
	}
	
	public void paintComponent(Graphics g) {
		setBackground(Color.BLUE);
		super.paintComponent(g);

		drawLines(g);
		paintCells(g);
		if(misses > 34) {
			paintRemainderPointCells(g);
		}
	}

	public void drawLines(Graphics g) {
		g.setColor(Color.WHITE);

		for (int x = 0; x < 601; x += 601 / 10) {
			g.drawLine(x, 0, x, 601 - 1);
		}
		for (int y = 0; y < 601; y += 601 / 10) {
			g.drawLine(0, y, 601 - 1, y);
		}
	}

	public void paintCells(Graphics g) {

		for (int row = 0; row < 10; row++) {
			for (int col = 0; col < 10; col++) {

				int var = Game.board[row][col];

				if (var == board.hit) {
					g.setColor(Color.GREEN);
					g.fillRect(col * 60 + 1, row * 60 + 1, 59, 59);
				}
				if (var == board.miss) {
					g.setColor(Color.BLACK);
					g.fillRect(col * 60 + 1, row * 60 + 1, 59, 59);
				}
			}
		}
	}
	public void paintRemainderPointCells(Graphics g) {
		
		for(int row = 0; row < 10; row++) {
			for(int col = 0; col < 10; col++) {
				
				int var1 = Game.board[row][col];
				
				if(var1 == board.ship) {
					g.setColor(Color.RED);
					g.fillRect(col * 60 + 1, row * 60 + 1, 59, 59);
				}
			}
		}
	}

	//get mouse coordinates and turn to array indices
	public void boardAttack(int mouseX, int mouseY) {
		
		int yIndex = (int) Math.floor(mouseX / 60);
		int xIndex = (int) Math.floor(mouseY / 60);

		if (board.Cell(xIndex, yIndex) == Game.ship) {
			board.attack(xIndex, yIndex);
			hits+=1;
		} else if (board.Cell(xIndex, yIndex) == Game.hit) {
			return;
		} else {
			board.attack(xIndex, yIndex);
			misses+=1;
		}
		sPanel.setInfo(hits, misses);
		board.Display();
	}
}