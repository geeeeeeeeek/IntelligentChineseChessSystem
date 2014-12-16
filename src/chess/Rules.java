package chess;

import java.util.ArrayList;

/**
 * Created by Tong on 12.15.
 * Define moving rules of pieces in the board.
 */
public class Rules {

    public static ArrayList<int[]> getNext(String piece, int[] pos, Board board) {
        if (piece.charAt(1) == 'p') {
            return pRules(pos, board);
        }
        return null;
    }

//    private static int[][] pRules = new int[]{{}};

    private static ArrayList<int[]> pRules(int[] pos, Board board) {
        ArrayList<int[]> moves = new ArrayList<int[]>();
        int[] xOffsets = new int[]{1, 2, 3, 4, 5, 6, 7, 8};
        int[] yOffsets = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
        boolean rr = false, ll = false, uu = false, dd = false;
        for (int offset : xOffsets) {
            int[] rMove = new int[]{pos[0] + offset, pos[1]};
            if (!board.isInside(rMove)) break;
            boolean e = board.isEmpty(rMove);
            if (!rr) {
                if (e) moves.add(rMove);
                else rr = true;
            } else if (!e) {
                moves.add(rMove);
                break;
            }
        }
        for (int offset : xOffsets) {
            int[] lMove = new int[]{pos[0] - offset, pos[1]};
            if (!board.isInside(lMove)) break;
            boolean e = board.isEmpty(lMove);
            if (!ll) {
                if (e) moves.add(lMove);
                else ll = true;
            } else if (!e) {
                moves.add(lMove);
                break;
            }
        }
        for (int offset : yOffsets) {
            int[] uMove = new int[]{pos[0], pos[1] - offset};
            if (!board.isInside(uMove)) break;
            boolean e = board.isEmpty(uMove);
            if (!uu) {
                if (e) moves.add(uMove);
                else uu = true;
            } else if (!e) {
                moves.add(uMove);
                break;
            }
        }
        for (int offset : yOffsets) {
            int[] dMove = new int[]{pos[0], pos[1] + offset};
            if (!board.isInside(dMove)) break;
            boolean e = board.isEmpty(dMove);
            if (!dd) {
                if (e) moves.add(dMove);
                else dd = true;
            } else if (!e) {
                moves.add(dMove);
                break;
            }
        }

        return moves;
    }
}
