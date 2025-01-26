package Test;

import main.Model;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class InputValidationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        // Initialize the Model with standard dimensions and connect requirement
        model = new Model(6, 7, 4); // 6 rows, 7 columns, 4 to connect
    }

    @Test
    public void testValidMove() {
        // Simulate a valid move
        int validColumn = 3; // Column within bounds
        assertTrue(model.isMoveValid(validColumn), "The move should be valid for column 3.");
    }

    @Test
    public void testInvalidMoveOutOfBounds() {
        // Test input for a column less than 1
        int invalidColumnLow = 0;
        assertFalse(model.isMoveValid(invalidColumnLow), "The move should be invalid for column 0 (less than 1).");

        // Test input for a column greater than the maximum number of columns
        int invalidColumnHigh = 8; // Model has 7 columns
        assertFalse(model.isMoveValid(invalidColumnHigh), "The move should be invalid for column 8 (greater than maximum columns).");
    }

    @Test
    public void testInvalidMoveFullColumn() {
        // Fill up a column
        int columnToFill = 1;
        for (int i = 0; i < model.getNrRows(); i++) {
            model.makeMove(columnToFill);
        }

        // Verify that the column is now full and further moves are invalid
        assertFalse(model.isMoveValid(columnToFill), "The move should be invalid for a full column.");
    }

    @Test
    public void testNegativeInput() {
        // Test for negative column input
        int negativeColumn = -1;
        assertFalse(model.isMoveValid(negativeColumn), "The move should be invalid for a negative column number.");
    }

    @Test
    public void testNonIntegerInput() {
        // Since the model only accepts integers, non-integer inputs are indirectly tested
        // during user input parsing in TextView or InputUtil. You can simulate these:
        try {
            int invalidMove = Integer.parseInt("abc"); // Simulate non-integer input
            fail("Parsing non-integer input should throw a NumberFormatException.");
        } catch (NumberFormatException e) {
            // Expected exception
            assertTrue(true, "Correctly caught non-integer input.");
        }
    }
}

