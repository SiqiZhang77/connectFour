package Test;


import main.Model;
import main.TextView;
import main.Controller;
import static org.junit.jupiter.api.Assertions.*;
import main.Model;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class WinConditionTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        // 初始化 Model 使用参数化构造函数，避免用户输入
        model = new Model(6, 7, 4); // 6 行 7 列，4 连胜
    }

    @Test
    public void testHorizontalWin() {
        model.makeMove(1); // Player 1
        model.makeMove(1); // Player 2
        model.makeMove(2); // Player 1
        model.makeMove(2); // Player 2
        model.makeMove(3); // Player 1
        model.makeMove(3); // Player 2
        model.makeMove(4); // Player 1 (winning move)

        assertTrue(model.isWin(), "Horizontal win condition failed."); // 验证是否获胜
    }

    @Test
    public void testVerticalWin() {
        model.makeMove(1); // Player 1
        model.makeMove(2); // Player 2
        model.makeMove(1); // Player 1
        model.makeMove(2); // Player 2
        model.makeMove(1); // Player 1
        model.makeMove(2); // Player 2
        model.makeMove(1); // Player 1 (winning move)

        assertTrue(model.isWin(), "Vertical win condition failed."); // 验证是否获胜
    }

    @Test
    public void testDiagonalWinSlash() {
        model.makeMove(1); // Player 1
        model.makeMove(2); // Player 2
        model.makeMove(2); // Player 1
        model.makeMove(3); // Player 2
        model.makeMove(3); // Player 1
        model.makeMove(4); // Player 2
        model.makeMove(3); // Player 1
        model.makeMove(4); // Player 2
        model.makeMove(3); // Player 1
        model.makeMove(4); // Player 2
        model.makeMove(4); // Player 1 (winning move)

        assertTrue(model.isWin(), "Diagonal '/' win condition failed.");
    }

    @Test
    public void testDiagonalWinBackslash() {
        model.makeMove(4); // Player 1
        model.makeMove(3); // Player 2
        model.makeMove(3); // Player 1
        model.makeMove(2); // Player 2
        model.makeMove(2); // Player 1
        model.makeMove(1); // Player 2
        model.makeMove(2); // Player 1
        model.makeMove(1); // Player 2
        model.makeMove(5); // Player 1
        model.makeMove(1); // Player 2
        model.makeMove(1); // Player 1 (winning move)

        assertTrue(model.isWin(), "Diagonal '\\' win condition failed.");
    }


}