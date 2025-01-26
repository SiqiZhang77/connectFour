package Test;

import main.Model;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;

public class ConnectFourSystemTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        // Override the Model class to fix NPC behavior for testing
        model = new Model(6, 7, 4) {
            @Override
            public int getNPCMove() {
                // NPC always moves in column 2
                return 5;
            }
        };
    }

    @Test
    public void testEndToEndGameFlow() {
        System.out.println("Testing End-to-End Game Flow...");

        // Step 1: Verify board initialization
        System.out.println("Initializing board...");
        assertEquals(6, model.getNrRows(), "The board should have 6 rows.");
        assertEquals(7, model.getNrCols(), "The board should have 7 columns.");
        for (int row = 0; row < model.getNrRows(); row++) {
            for (int col = 0; col < model.getNrCols(); col++) {
                assertEquals(' ', model.getChessboard()[row][col], "The board should initialize with empty cells.");
            }
        }

        // Step 2: Simulate a full game
        int player1Move = 1;

        for (int i = 0; i < 3; i++) {
            // Player 1 makes a move
            assertTrue(model.isMoveValid(player1Move), "Player 1's move should be valid.");
            model.makeMove(player1Move);
            System.out.println("Player 1 moved to column " + player1Move);
            printBoardState();

            // NPC makes a move
            int npcMove = model.getNPCMove();
            assertTrue(model.isMoveValid(npcMove), "NPC's move should be valid.");
            model.makeMove(npcMove);
            System.out.println("NPC moved to column " + npcMove);
            printBoardState();

            player1Move++;
        }

        // Player 1's final (winning) move
        assertTrue(model.isMoveValid(player1Move), "Player 1's move should be valid.");
        model.makeMove(player1Move);
        System.out.println("Player 1 moved to column " + player1Move + " (Winning move)");
        printBoardState();

        // Step 3: Verify win condition
        System.out.println("Checking win condition...");
        assertTrue(model.isWin(), "Player 1 should win with a horizontal connect 4.");

        // Step 4: Validate game reset
        System.out.println("Resetting board...");
        model.wipeBoard();
        for (int row = 0; row < model.getNrRows(); row++) {
            for (int col = 0; col < model.getNrCols(); col++) {
                assertEquals(' ', model.getChessboard()[row][col], "The board should be reset to empty.");
            }
        }
        System.out.println("Board reset successfully.");
    }

    private void printBoardState() {
        System.out.println("Current board state:");
        for (char[] row : model.getChessboard()) {
            System.out.println(Arrays.toString(row));
        }
    }
}
