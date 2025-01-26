package main;

/**
 * This file is to be completed by you.
 *
 * @author s2029216
 */
public final class Controller
{
	private final Model model;
	private final TextView view;
	
	public Controller(Model model, TextView view)
	{
		this.model = model;
		this.view = view;
	}
	
	public void startSession()
	{   // Tell the user that the game has started.
		view.displayNewGameMessage();
		view.displayGiveUpHint();
		boolean loop;
		int count = 0;
		int winner = 0;
		int move = 0;

		//end the game if the the game cannot be win
		if (model.isRowColConnectValid(model.getNrRows(), model.getNrCols(), model.getNrConnect())) loop = true;
		else throw new IllegalArgumentException("The game must be able to be win.");

		int player = view.askForNPC();
		//end the game if not select between one and two players
		if (player != 1 && player != 2) throw new IllegalArgumentException("Only available for one player or two players.");

		while (loop){
			//display massage to indicate which player to play
			if (count % 2 == 0 && player == 2)  move = view.askPlayer1ForMove();
			else if (count % 2 == 1 && player == 2)move = view.askPlayer2ForMove();

			if (count % 2 == 0 && player ==1) move = view.askPlayer1ForMove();
			else if (count % 2 == 1 && player == 1) move = model.getNPCMove();

			//change the board status when a move is valid
			if (model.isMoveValid(move) && player == 2){
				model.makeMove(move);
				count++;
				view.displayBoard(model);
			}else if (model.isMoveValid(move) && player == 1){
				model.makeMove(move);
				count++;
				if (count % 2 == 0) view.displayBoard(model);
			}

			//end the game with a draw when the board is full
			if (count > model.getNrCols() * model.getNrRows()) loop = false;

			//allow the players to give up when inputting -1
			if (move == -1){
				if (count % 2 == 0) winner = 2;
				else winner = 1;
				loop = false;
			}

			//end the game when a player win
			if (model.isWin()){
				if (count % 2 == 0) winner = 2;
				else  winner = 1;
				loop = false;
			}
		}

		//show the result of the game
		if (winner == 0){
			view.displayBoard(model);
			view.declareADraw();
		}else if (winner == 1) view.declarePlayer1Win();
		else view.declarePlayer2Win();

		//ask user whether to restart a new game
		int start = view.askForRestart();
		if (start == 1) {
			model.wipeBoard();
			startSession();
		}
	}
}
