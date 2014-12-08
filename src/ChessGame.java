import chess.Board;
import chess.Piece;
import control.GameController;

import java.util.Map;

/**
 * Created by Tong on 12.08.
 * Main process of Chinese Chess Game.
 */
public class ChessGame {
    private Board board;
    private Map<String, Piece> pieces;
    private GameController controller;

    public static void main(String[] args) {
        ChessGame game = new ChessGame();
        game.init();
        game.run();
    }

    public void init() {
        controller = new GameController();
        pieces = controller.initPieces();
        board = controller.initBoard(pieces);
    }

    public void run() {
        while (controller.hasWin(pieces) == 'x') {
            //Do something
            System.out.println(controller.hasWin(pieces));
        }
    }
}
