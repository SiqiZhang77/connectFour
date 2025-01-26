package Test;

import main.Model;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GameRestartTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        // Initialize Model with specific parameters (e.g., 6 rows, 7 columns, 4 connections to win)
        model = new Model(6, 7, 4);
    }

    @Test
    public void testGameRestartOption() {
        // Simulate the game progress
        model.makeMove(1); // Player 1's move in column 1
        model.makeMove(2); // Player 2's move in column 2

        // Verify the board state before restarting
        assertEquals('○', model.getChessboard()[model.getNrRows() - 1][0], "Player 1 should have placed a piece in column 1");
        assertEquals('×', model.getChessboard()[model.getNrRows() - 1][1], "Player 2 should have placed a piece in column 2");

        // Restart the game and clear the board
        model.wipeBoard();

        // Verify the board is cleared after restart
        for (int row = 0; row < model.getNrRows(); row++) {
            for (int col = 0; col < model.getNrCols(); col++) {
                assertEquals(' ', model.getChessboard()[row][col], "The board should be reset to empty after restarting");
            }
        }

        // Simulate a new move after restarting the game
        model.makeMove(1); // Player 1's move in column 1 on the new board
        assertEquals('○', model.getChessboard()[model.getNrRows() - 1][0], "Player 1 should be able to play on the reset board");
    }

    @Test
    public void testNoResidualDataAfterRestart() {
        // Simulate some gameplay
        model.makeMove(1); // Player 1
        model.makeMove(2); // Player 2

        // Verify the board is not empty before restarting
        assertEquals('○', model.getChessboard()[model.getNrRows() - 1][0], "Player 1 should have placed a piece in column 1");
        assertEquals('×', model.getChessboard()[model.getNrRows() - 1][1], "Player 2 should have placed a piece in column 2");

        // Restart the game
        model.wipeBoard();

        // Verify the board is cleared
        for (int row = 0; row < model.getNrRows(); row++) {
            for (int col = 0; col < model.getNrCols(); col++) {
                assertEquals(' ', model.getChessboard()[row][col], "The board should be reset to empty");
            }
        }

        // Verify all other state variables are reset
        assertEquals(0, model.getPlayer(), "Player turn should reset to the initial state");
    }
}
