package chess;

/**
 * Created by Tong on 12.03.
 * Chess > Board entity
 */


public class Board {
    public final int BOARD_WIDTH = 9, BOARD_HEIGHT = 10;
    private Piece[][] cells = new Piece[BOARD_HEIGHT][BOARD_WIDTH];

//    private Board() {
//    }

    //TODO
    public static boolean isInside(int[] position) {
        return true;
    }

//    public static Board getBoardInstance() {
//        return BoardInstanceHolder.board;
//    }

    public boolean initialize(Piece[] pieces) {
        cells = new Piece[BOARD_WIDTH][BOARD_HEIGHT];
        for (Piece piece : pieces) {
            update(piece);
        }
        return true;
    }

    public boolean backUp(Piece[] pieces) {
        cells = new Piece[BOARD_WIDTH][BOARD_HEIGHT];
        for (Piece piece : pieces) {
            update(piece);
        }
        return true;
    }

    public boolean update(Piece piece) {
        int[] pos = piece.position;
        cells[pos[0]][pos[1]] = piece;
        return true;
    }

//    private static class BoardInstanceHolder {
//        public static Board board = new Board();
//    }
}
