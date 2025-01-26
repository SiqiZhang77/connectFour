package Test;

import main.Model;
import main.TextView;
import main.Controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class NPCOpponentTest {

    private Model model;
    private TextView view;
    private Controller controller;

    @BeforeEach
    public void setUp() {
        // Initialize Model with test-specific constructor for 6x7 board and connect 4
        model = new Model(6, 7, 4);
        view = new TextView();
        controller = new Controller(model, view);
    }

    @Test
    public void testNPCMoveValidity() {
        // Simulate a single-player game with NPC
        int npcMove;

        // Let Player 1 make a move in column 1
        int player1Move = 1;
        assertTrue(model.isMoveValid(player1Move), "Player 1's move should be valid");
        model.makeMove(player1Move);

        // NPC makes a move
        npcMove = model.getNPCMove();
        assertTrue(model.isMoveValid(npcMove), "NPC's move should be valid");
        model.makeMove(npcMove);

        // Check if NPC's move is within bounds and adheres to the rules
        assertTrue(npcMove > 0 && npcMove <= model.getNrCols(), "NPC's move should be within valid column range");
        assertEquals('×', model.getChessboard()[model.getNrRows() - 1][npcMove - 1], "NPC's disc should be placed correctly on the board");
    }

    @Test
    public void testNPCLogicalDecision() {
        // Simulate scenarios to test NPC decision-making
        // Player 1 makes three consecutive moves in column 1
        model.makeMove(1);
        model.makeMove(1);
        model.makeMove(1);

        // NPC should ideally block Player 1 from winning by placing in column 1
        int npcMove = model.getNPCMove();
        model.makeMove(npcMove);

        assertEquals(1, npcMove, "NPC should block Player 1 from winning in column 1");
    }

    @Test
    public void testNPCBehaviorAcrossBoard() {
        // Simulate various board states and validate NPC behavior
        for (int col = 1; col <= model.getNrCols(); col++) {
            model.makeMove(col);
        }

        // NPC should make a valid move even if some columns are full
        int npcMove = model.getNPCMove();
        assertTrue(model.isMoveValid(npcMove), "NPC should avoid full columns and choose a valid column");
    }
}
