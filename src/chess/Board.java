package chess;

/**
 * Created by Tong on 12.03.
 * Chess > Board entity
 */


public class Board {
    public final int BOARD_WIDTH = 10, BOARD_HEIGHT = 10;
    private Piece[][] cells = new Piece[BOARD_WIDTH][BOARD_HEIGHT];

    private Board() {
    }

    //TODO
    public static boolean isInside(int[] position) {
        return true;
    }

    public Board getBoardInstance() {
        return BoardInstanceHolder.board;
    }

    public boolean initialize(Piece[] pieces) {
        for (Piece piece : pieces) {
            update(piece);
        }
        return true;
    }

    public boolean update(Piece piece) {
        int[] pos = piece.position;
        getBoardInstance().cells[pos[0]][pos[1]] = piece;
        return true;
    }

    private static class BoardInstanceHolder {
        public static Board board = new Board();
    }
}
