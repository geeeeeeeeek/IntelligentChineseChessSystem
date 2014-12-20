import chess.Board;
import control.GameController;
import view.GameView;

/**
 * Created by Tong on 12.08.
 * Main process of Chinese Chess Game.
 */
public class ChessGame {
    private Board board;

    private GameController controller;
    private GameView view;

    public static void main(String[] args) throws InterruptedException {
        ChessGame game = new ChessGame();
        game.init();
        game.run();
    }

    public void init() {
        controller = new GameController();
        board = controller.playChess();

        view = new GameView(controller);
        view.init(board);
    }

    public void run() throws InterruptedException {
        while (controller.hasWin(board) == 'x') {
            /* User in. */
            while (board.player == 'r')
                Thread.sleep(1000);

            if (controller.hasWin(board) != 'x')
                System.exit(0);
            /* AI in. */
            controller.responseMoveChess(board, view);
        }
        System.exit(0);
    }
}