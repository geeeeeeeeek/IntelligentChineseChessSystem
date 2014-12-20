import chess.Board;
import chess.Piece;
import control.GameController;
import view.GameView;

import java.util.Map;

/**
 * Created by Tong on 12.08.
 * Main process of Chinese Chess Game.
 */
public class ChessGame {
    private Board board;

    private GameController controller;
    private GameView view;

    public static void main(String[] args) {
        ChessGame game = new ChessGame();

        game.init();
        game.run();
    }

    public void init() {
        controller = new GameController();
        Map<String, Piece> pieces = controller.initPieces();
        board = controller.initBoard();
        view = new GameView(controller);
        view.init(board);
    }

    public void run() {
        while (controller.hasWin(board) == 'x') {
            /* User in. */
            while (board.player == 'r') try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
//            controller.printBoard(board);
            /* AI in. */
            controller.responseMoveChess(board, view);
//            controller.printBoard(board);
        }
    }


}
