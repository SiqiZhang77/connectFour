package main;

/**
 * This file is to be completed by you.
 *
 * @author s2029216
 */
public class Model
{
	// ===========================================================================
	// ================================ CONSTANTS ================================
	// ===========================================================================
	// The most common version of Connect Four has 6 rows and 7 columns.
	public static final int DEFAULT_NR_ROWS = 6;
	public static final int DEFAULT_NR_COLS = 7;
	
	// ========================================================================
	// ================================ FIELDS ================================
	// ========================================================================
	// The size of the board.
	private int nrRows;
	private int nrCols;
	private int nrConnect;
	private int player;
	private char[][] chessboard;
	
	// =============================================================================
	// ================================ CONSTRUCTOR ================================
	// =============================================================================
	public Model()
	{
		// Initialise the board size to its default values.
		nrRows = TextView.askForDesiredRow();
		nrCols = TextView.askForDesiredCol();
		nrConnect = TextView.askForDesiredConnect();
		chessboard = new char[nrRows][nrCols];
		player = 0;
		for (int i = 0; i < nrRows; i++){
			for (int j = 0; j < nrCols; j++){
				chessboard[i][j] = ' ';
			}
		}
	}
	//used to test
	public Model(int nrRows, int nrCols, int nrConnect) {
		this.nrRows = nrRows;
		this.nrCols = nrCols;
		this.nrConnect = nrConnect;
		chessboard = new char[nrRows][nrCols];
		player = 0;
		for (int i = 0; i < nrRows; i++) {
			for (int j = 0; j < nrCols; j++) {
				chessboard[i][j] = ' ';
			}
		}
	}

	// ====================================================================================
	// ================================ MODEL INTERACTIONS ================================
	// ====================================================================================
	public boolean isMoveValid(int move)
	{
		if (move > 0){
			if (move <= nrCols){
				if (chessboard[0][move-1] == ' '){
					return true;
				}
			}
		}
		return false;
	}

	//return false when the game cannot be win
	public boolean isRowColConnectValid(int nrRows, int nrCols, int nrConnect)
	{
		if (nrConnect <= nrRows || nrConnect <= nrCols) return true;
		else return false;
	}

	//change the state of the board by user input
	public void makeMove(int move)
	{
		if (move < 1 || move > nrCols) {
			throw new IllegalArgumentException("Invalid column number: " + move);
		}

		for (int i = 0; i < nrRows; i++){
			if (chessboard[nrRows-i-1][move-1] == ' '){
				if (player == 0){
					chessboard[nrRows-i-1][move-1] = '○';
					player = 1;
					break;
				}else {
					chessboard[nrRows-i-1][move-1] = '×';
					player = 0;
					break;
				}
			}
		}
	}

	//get the column for NPC to drop
	public int getNPCMove() {
		// Try to find a valid column within the range
		for (int attempts = 0; attempts < nrCols; attempts++) {
			int move = (int) (Math.random() * nrCols) + 1; // Random column between 1 and nrCols
			if (isMoveValid(move)) {
				return move;
			}
		}

		// If no valid move is found (edge case where all columns are full), throw an exception
		throw new IllegalStateException("No valid moves available for NPC.");
	}


	//initialise the array for new game
	public void wipeBoard()
	{
		for (int i = 0; i < nrRows; i++){
			for (int j = 0; j < nrCols; j++){
				chessboard[i][j] = ' ';
		}
	}
	}

	//return turn when a move is valid
	public boolean isWin()
	{
		int num = 1;

		//check if there is a streak in each row
		for (int row = 0; row < nrRows; row++){
			for (int col = 0; col < nrCols-1; col++){
				if (chessboard[row][col] != ' '){
					if (chessboard[row][col] == chessboard[row][col+1]){
						num++;
						if (num == nrConnect) return true;
					} else num = 1;
				}
			}
			num = 1;
		}

		//check if there is a streak in each column
		for (int col = 0; col < nrCols; col++){
			for (int row = 0; row < nrRows-1; row++){
				if (chessboard[row][col] != ' '){
					if (chessboard[row][col] == chessboard[row+1][col]){
						num++;
						if (num == nrConnect) return true;
					}else num = 1;
				}
			}
			num = 1;
		}

		//check if there is a streak in each slashDiagonal'/'
		for (int row = nrConnect-1; row < nrRows; row++){
			for (int col = 0; col < nrCols-(nrConnect-1); col++){
				for (int i = 1; i <= nrConnect-1; i++){
					if (chessboard[row][col] != ' '){
						if (chessboard[row][col] == chessboard[row-i][col+i]){
							num++;
							if (num == nrConnect) return true;
						}else num = 1;
					}
				}
				num = 1;
			}
			num = 1;
		}

		//check if there is a streak in each backslashDiagonal'\'
		for (int row = 0; row < nrRows-(nrConnect-1); row++){
			for (int col = 0; col < nrCols-(nrConnect-1); col++){
				for (int i = 0; i <= nrConnect-1; i++){
					if (chessboard[row][col] != ' '){
						if (chessboard[row][col] == chessboard[row+i][col+i]){
							num++;
							if (num == nrConnect) return true;
						}else num = 1;
					}
				}
				num = 1;
			}
			num = 1;
		}

		return false;
	}
	// =========================================================================
	// ================================ GETTERS ================================
	// =========================================================================
	public int getNrRows()
	{
		return nrRows;
	}
	
	public int getNrCols()
	{
		return nrCols;
	}

	public int getNrConnect(){return nrConnect;}

	public char[][] getChessboard() {return chessboard;}

	public int getPlayer() {
		return player;
	}

}
