package battleships;

import java.util.Random;
import javax.swing.JOptionPane;

public class Game {

	public static int[][] board;
	public static int[][] shipPoints = new int[17][3];
	public static int[][] startlocs;
	public static int index = 5;
	public static int empty = 0;
	public static int ship = 1;
	public static int hit = 2;
	public static int miss = 3;
	public static int totalHits;
	public static int totalMiss;
	public static int retries = 0;

	// game constructor
	public Game(int row, int column) {
		createBoard();
		placeShips();
	}

	public void createBoard() {
		board = new int[10][10];

		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				board[i][j] = empty;
			}
		}
	}

	public static int[][] generateStartLocations() {
		Random rndm = new Random();

		int[][] startlocs = new int[5][3];

		for (int a = 0; a < 5; a++) {
			startlocs[a][0] = rndm.nextInt(10);
			startlocs[a][1] = rndm.nextInt(10);
			for (int b = 0; b < a; b++) {
				if (startlocs[a][0] == startlocs[b][0] && startlocs[a][1] == startlocs[a][1]) {
					a -= 1;
				}
			}
		}
		return startlocs;
	}

	public static void placeShips() {
		Random rndm = new Random();

		startlocs = generateStartLocations();

		startlocs[0][2] = 5;
		startlocs[1][2] = 4;
		startlocs[2][2] = 3;
		startlocs[3][2] = 3;
		startlocs[4][2] = 2;

		shipPoints[0] = startlocs[0];
		shipPoints[1] = startlocs[1];
		shipPoints[2] = startlocs[2];
		shipPoints[3] = startlocs[3];
		shipPoints[4] = startlocs[4];

		for (int i = 0; i < 5; i++) {

			boolean check = true;

			try {

				while (check) {

					int direction = rndm.nextInt(4);

					if (RemainderPoints(i, shipPoints[i][2], direction)) {
						check = false;
					}
				}

			} catch (Exception ArrayIndexOutOfBoundsException) {
				retries+=1;
				if(Checkpoint()) {
					i = 0;
					index = 5;
					placeShips();
				}
			};
		}
		
		/*print shipPoints array
		System.out.print("\nAll ship points");
		for (int w = 0; w < shipPoints.length; w++) {
			System.out.println();
			for (int e = 0; e < shipPoints[w].length; e++) {
				System.out.print(shipPoints[w][e] + " ");
			}
		}
		System.out.println();
		*/
		
		// replace empty element with ship element on board
		for (int o = 0; o < board.length; o++) {
			for (int k = 0; k < board[o].length; k++) {
				for (int v = 0; v < shipPoints.length; v++) {
					if (o == shipPoints[v][0] && k == shipPoints[v][1]) {
						board[o][k] = ship;
					}
				}
			}
		}
	}
	private static boolean Checkpoint() {
		
		int[] reflect = {5, 5, 5, 5, 4, 4, 4, 3, 3, 3, 3, 2};
		int[] tempships = new int[12];
		int c = 5;
		
		for(int a = 0; a < 12; a++) {
			for(int b = 0; b < 1; b++) {
				int var = shipPoints[c][2];
				tempships[a] = var;
				c+=1;
				if(tempships[a] != reflect[a]) {
					return true;
				}
			}
		}
		return false;
	}

	// RemainderPoints() gets remaining ship coordinates
	public static boolean RemainderPoints(int i, int j, int k) {
		// i = loop index
		// j = ship number
		// k = direction

		switch (k) {

		case 0:
			for (int b = 1; b < shipPoints[i][2]; b++) {
				if (shipPoints[i][1] + b < 0 || shipPoints[i][1] + b > 9) {
					return false;
				}
				if (collision(shipPoints[i][0], shipPoints[i][1] + b)) {
					return false;
				} else {
					int[] point = new int[3];
					point[0] = shipPoints[i][0];
					point[1] = shipPoints[i][1] + b;
					point[2] = shipPoints[i][2];
					shipPoints[index] = point;
					index += 1;
				}
			}
			break;

		case 1:
			for (int b = 1; b < shipPoints[i][2]; b++) {
				if (shipPoints[i][0] + b < 0 || shipPoints[i][0] + b > 9) {
					return false;
				}
				if (collision(shipPoints[i][0] + b, shipPoints[i][1])) {
					return false;
				} else {
					int[] point = new int[3];
					point[0] = shipPoints[i][0] + b;
					point[1] = shipPoints[i][1];
					point[2] = shipPoints[i][2];
					shipPoints[index] = point;
					index += 1;
				}
			}
			break;

		case 2:
			for (int b = 1; b < shipPoints[i][2]; b++) {
				if (shipPoints[i][1] - b < 0 || shipPoints[i][1] - b > 9) {
					return false;
				}
				if (collision(shipPoints[i][0], shipPoints[i][1] - b)) {
					return false;
				} else {
					int[] point = new int[3];
					point[0] = shipPoints[i][0];
					point[1] = shipPoints[i][1] - b;
					point[2] = shipPoints[i][2];
					shipPoints[index] = point;
					index += 1;
				}
			}
			break;

		case 3:
			for (int b = 1; b < shipPoints[i][2]; b++) {
				if (shipPoints[i][0] - b < 0 || shipPoints[i][0] - b > 9) {
					return false;
				}
				if (collision(shipPoints[i][0] - b, shipPoints[i][1])) {
					return false;
				} else {
					int[] point = new int[3];
					point[0] = shipPoints[i][0] - b;
					point[1] = shipPoints[i][1];
					point[2] = shipPoints[i][2];
					shipPoints[index] = point;
					index += 1;
				}
			}
			break;
		}
		return true;
	}

	public static boolean collision(int i, int j) {

		for (int a = 0; a < index; a++) {
			if (i == shipPoints[a][0] && j == shipPoints[a][1]) {
				return true;
			}
		}
		return false;
	}

	public boolean attack(int row, int col) {

		if (board[row][col] == ship) {
			board[row][col] = hit;
			return true;
		}
		if (board[row][col] == hit) {
			return true;
		} else {
			board[row][col] = miss;
			return false;
		}
	}

	// check if all ships are destroyed
	public boolean checkDestroyed() {

		totalHits = 0;

		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				if (board[i][j] == hit) {
					totalHits += 1;
				}
			}
		}
		if (totalHits == 17) {
			return true;
		}
		return false;
	}

	// returns the array's indices
	public int Cell(int row, int col) {
		if ((row >= 0) && (row < 10) && (col >= 0) && (col < 10)) {
			return board[row][col];
		} else {
			return -1;
		}
	}
	public void Display() {
		for (int i = 0; i < board.length; i++) {
			System.out.println();
			for (int j = 0; j < board[i].length; j++) {
				System.out.print(board[i][j] + " ");
			}
		}
		System.out.println();
	}
}