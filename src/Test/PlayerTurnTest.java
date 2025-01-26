
package Test;
import main.Model;
import main.TextView;
import main.Controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerTurnTest {

    private Model model;
    private TextView view;
    private Controller controller;

    @BeforeEach
    public void setUp() {
        // Initialize Model with test-specific constructor
        model = new Model(6, 7, 4); // Set the board to 6 rows, 7 columns, and 4 consecutive discs to win
        view = new TextView();
        controller = new Controller(model, view);
    }

    @Test
    public void testPlayerTurnManagement() {
        System.out.println("Testing Player Turn Management...");

        // Initialize player turn and validate alternating turns
        int player1Move = 1;
        int player2Move = 2;

        // Player 1 makes a valid move
        assertTrue(model.isMoveValid(player1Move), "Player 1's move should be valid.");
        model.makeMove(player1Move);
        System.out.println("Player 1 moved to column: " + player1Move);
        assertEquals('○', model.getChessboard()[model.getNrRows() - 1][player1Move - 1], "Player 1's disc should be placed correctly.");
        printBoardState();

        // Player 2 (or NPC) makes a valid move
        assertTrue(model.isMoveValid(player2Move), "Player 2's move should be valid.");
        model.makeMove(player2Move);
        System.out.println("Player 2 moved to column: " + player2Move);
        assertEquals('×', model.getChessboard()[model.getNrRows() - 1][player2Move - 1], "Player 2's disc should be placed correctly.");
        printBoardState();

        // Repeat for additional moves
        player1Move = 3;
        assertTrue(model.isMoveValid(player1Move), "Player 1's move should be valid.");
        model.makeMove(player1Move);
        System.out.println("Player 1 moved to column: " + player1Move);
        assertEquals('○', model.getChessboard()[model.getNrRows() - 1][player1Move - 1], "Player 1's disc should be placed correctly.");
        printBoardState();

        player2Move = 4;
        assertTrue(model.isMoveValid(player2Move), "Player 2's move should be valid.");
        model.makeMove(player2Move);
        System.out.println("Player 2 moved to column: " + player2Move);
        assertEquals('×', model.getChessboard()[model.getNrRows() - 1][player2Move - 1], "Player 2's disc should be placed correctly.");
        printBoardState();

        // Ensure turns alternate correctly
        assertEquals(0, model.getPlayer() % 2, "Player turn should alternate correctly.");
    }

    private void printBoardState() {
        System.out.println("Current board state:");
        for (char[] row : model.getChessboard()) {
            System.out.println(Arrays.toString(row));
        }
    }
}