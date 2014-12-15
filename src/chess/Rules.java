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
        int[] rPos = new int[]{pos[0] + 1, pos[1]};
        int[] lPos = new int[]{pos[0] - 1, pos[1]};
        int[] uPos = new int[]{pos[0], pos[1] - 1};
        int[] dPos = new int[]{pos[0], pos[1] + 1};
        boolean isR = board.isEmpty(rPos), isL = board.isEmpty(rPos), isU = board.isEmpty(uPos), isD = board.isEmpty(dPos);
        do {
            if (isR) {
                moves.add(rPos);
                isR = board.isEmpty(new int[]{rPos[0] + 1, rPos[1]});
            }
            if (isL) {
                moves.add(lPos);
                isL = board.isEmpty(new int[]{lPos[0] - 1, lPos[1]});
            }
            if (isU) {
                moves.add(uPos);
                isU = board.isEmpty(new int[]{uPos[0], uPos[1] - 1});
            }
            if (isD) {
                moves.add(dPos);
                isD = board.isEmpty(new int[]{dPos[0], dPos[1] + 1});
            }
        } while (isR || isL || isU || isD);
        int[] rrPos = new int[]{+rPos[0] * 2 - pos[0], pos[1]};
        int[] llPos = new int[]{-lPos[0] * 2 + pos[0], pos[1]};
        int[] uuPos = new int[]{pos[0], -dPos[1] * 2 + dPos[1]};
        int[] ddPos = new int[]{pos[0], +dPos[1] * 2 - dPos[1]};
        boolean isRR = board.isEmpty(rrPos), isLL = board.isEmpty(rrPos),
                isUU = board.isEmpty(uuPos), isDD = board.isEmpty(ddPos);
        do {
            if (isRR) {
                isR = board.isEmpty(new int[]{rrPos[0] + 1, rrPos[1]});
            }
            if (isLL) {
                isL = board.isEmpty(new int[]{llPos[0] - 1, llPos[1]});
            }
            if (isUU) {
                isU = board.isEmpty(new int[]{uuPos[0], uuPos[1] - 1});
            }
            if (isDD) {
                isD = board.isEmpty(new int[]{ddPos[0], ddPos[1] + 1});
            }
        } while (isRR || isLL || isUU || isDD);
        if (board.isInside(rrPos)) {
            moves.add(rrPos);
        }
        if (board.isInside(llPos)) {
            moves.add(llPos);
        }
        if (board.isInside(uuPos)) {
            moves.add(uuPos);
        }
        if (board.isInside(ddPos)) {
            moves.add(ddPos);
        }
        return moves;
    }
}
