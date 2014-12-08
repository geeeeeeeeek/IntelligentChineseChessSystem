package chess;

import java.util.Map;

/**
 * Created by Tong on 12.03.
 * Chess > Board entity
 */


public class Board {
    public final int BOARD_WIDTH = 9, BOARD_HEIGHT = 10;
    private Piece[][] cells = new Piece[BOARD_HEIGHT][BOARD_WIDTH];
    public Map<String, Piece> pieces;

/*    private Board() {
    }*/

    //TODO
    public boolean isInside(int[] position) {
        return (position[0] < 0 && position[0] >= BOARD_HEIGHT
                && position[1] < 0 && position[1] >= BOARD_WIDTH);
    }

/*    public static Board getBoardInstance() {
        return BoardInstanceHolder.board;
    }

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
    }*/

    public boolean update(Piece piece) {
        int[] pos = piece.position;
        cells[pos[0]][pos[1]] = piece;
        return true;
    }

    public boolean update(Piece piece, int[] newPos) {
        Piece inNewPos = getPiece(newPos);
        if (inNewPos != null) {
            /*
                If the new slot has been taken by another piece, then it will be killed.
             */
            pieces.remove(inNewPos.key);
        }
        /*
            Clear original slot and update new slot.
         */
        int[] origPos = piece.position;
        cells[origPos[0]][origPos[1]] = null;
        cells[newPos[0]][newPos[1]] = piece;
        return true;
    }

    public Piece getPiece(int[] pos) {
        return cells[pos[0]][pos[1]];
    }
//    private static class BoardInstanceHolder {
//        public static Board board = new Board();
//    }
}
