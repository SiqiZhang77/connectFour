package main;

/**
 * This file is to be completed by you.
 *
 * @author s2029216
 */
public final class TextView
{
	public TextView()
	{
	
	}
	
	public final void displayNewGameMessage()
	{
		System.out.println("---- NEW GAME STARTED ----");
	}

	public final void displayGiveUpHint() {
		System.out.println("If you want to give up, enter [-1] in your turn.");
	}

	public final void declarePlayer1Win() {
		System.out.println("Player1 wins!");
	}

	public final void declarePlayer2Win() {
		System.out.println("Player2 wins!");
	}

	public final void declareADraw() {
		System.out.println("Game over. It is drawn!");
	}

	public final int askPlayer1ForMove()
	{
		System.out.print("Player1's turn to select a free column: ");
		return InputUtil.readIntFromUser();
	}

	public final int askPlayer2ForMove()
	{
		System.out.print("Player2's turn to select a free column: ");
		return InputUtil.readIntFromUser();
	}

	public static int askForDesiredRow()
	{
		System.out.print("Enter the number of the row: ");
		return InputUtil.readIntFromUser();
	}

	public static int askForDesiredCol()
	{
		System.out.print("Enter the number of the column: ");
		return InputUtil.readIntFromUser();
	}

	public static int askForDesiredConnect(){
		System.out.print("Enter the number for connect to win: ");
		return InputUtil.readIntFromUser();
	}

	public final int askForRestart() {
		System.out.print("Enter [1] to restart a new game: ");
		return InputUtil.readIntFromUser();
	}

	public final int askForNPC(){
		System.out.print("Enter [1] for one player, enter [2] for two player.");
		return InputUtil.readIntFromUser();
	}

	public final void displayBoard(Model model)
	{
		int nrRows = model.getNrRows();
		int nrCols = model.getNrCols();
		// Get your board representation.
		
		// This can be used to print a line between each row.
		String rowDivider = "-".repeat(4 * nrCols + 1);
		
		// A StringBuilder is used to assemble longer Strings more efficiently.
		StringBuilder sb = new StringBuilder();
		
		// show number of column when column is less than 10
		if (nrCols <= 10){
			for (int i = 0; i < nrCols; i++){
				sb.append("  " + (i+1) + " ");
			}
			sb.append("\n");
		}

		sb.append(rowDivider);
		for (int i = 0; i < nrRows; i++){
			sb.append("\n");
			for (int j = 0; j < nrCols; j++){
				sb.append("| " + model.getChessboard()[i][j] + " ");
			}
			sb.append("|");
			sb.append("\n");
			sb.append(rowDivider);
		}
		
		// Then print out the assembled String.
		System.out.println(sb);
	}
}
