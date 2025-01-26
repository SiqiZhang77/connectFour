package Test;

import main.Model;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

public class BoardInitializationTest {

    @ParameterizedTest
    @CsvSource({
            "6, 7, 4",  // Standard Connect Four dimensions
            "8, 9, 3",  // Larger board, Connect Three
            "5, 5, 5"   // Square board, Connect Five
    })
    public void testBoardInitialization(int rows, int cols, int connect) {
        // Initialize the board with the given parameters
        Model model = new Model(rows, cols, connect);

        // Validate the dimensions
        assertEquals(rows, model.getNrRows(), "Board rows should match the input.");
        assertEquals(cols, model.getNrCols(), "Board columns should match the input.");
        assertEquals(connect, model.getNrConnect(), "Connect rule should match the input.");

        // Verify all positions on the board are empty
        for (int row = 0; row < model.getNrRows(); row++) {
            for (int col = 0; col < model.getNrCols(); col++) {
                assertEquals(' ', model.getChessboard()[row][col], "The board should initialize with empty cells.");
            }
        }
    }
}
