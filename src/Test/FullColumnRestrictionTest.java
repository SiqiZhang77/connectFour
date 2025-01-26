package Test;

import main.Model;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FullColumnRestrictionTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        // 初始化 Model，动态设置 6 行、7 列、4 连胜
        model = new Model(6, 7, 4);
    }

    @Test
    public void testFullColumnRestriction() {
        int column = 1; // 测试第一列
        int rows = model.getNrRows();

        // Step 1: 填满第一列
        for (int i = 0; i < rows; i++) {
            assertTrue(model.isMoveValid(column), "Move should be valid in column 1 before it is full");
            model.makeMove(column);
        }

        // Step 2: 验证第一列已满
        assertFalse(model.isMoveValid(column), "Move should not be valid in column 1 as it is full");

        // Step 3: 验证无效移动不会改变棋盘状态
        char[][] boardBeforeInvalidMove = copyBoard(model.getChessboard());
        model.makeMove(column); // 尝试无效移动
        char[][] boardAfterInvalidMove = copyBoard(model.getChessboard());
        assertArrayEquals(boardBeforeInvalidMove, boardAfterInvalidMove, "The board should not change after an invalid move");
    }


    /**
     * Helper method to create a deep copy of the chessboard array.
     *
     * @param original The original chessboard.
     * @return A deep copy of the chessboard.
     */
    private char[][] copyBoard(char[][] original) {
        char[][] copy = new char[original.length][];
        for (int i = 0; i < original.length; i++) {
            copy[i] = original[i].clone();
        }
        return copy;
    }
}
