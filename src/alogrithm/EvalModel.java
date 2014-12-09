package alogrithm;

import chess.Board;
import chess.Piece;

import java.util.Map;

/**
 * Created by Tong on 12.08.
 */
public class EvalModel {
    private Board board;
    private char player;
    /*  [red, black] >> [PieceValue, PiecePosition, PieceControl, PieceFlexible, PieceProtect, PieceFeature]*/
    private int[][] values = new int[2][6];

    public EvalModel(Board board, char player) {
        this.board = board;
        this.player = player;
    }

    public int eval() {
        for (Map.Entry<String, Piece> stringPieceEntry : board.pieces.entrySet()) {
            Piece piece = stringPieceEntry.getValue();
            int[] reversePosition = new int[]{10 - piece.position[0], piece.position[1]};
            if (piece.character == 'b') {
                if (piece.color == 'r') {
                    values[0][0] += evalPieceValue(0);

                } else {
                    values[1][0] += evalPieceValue(0);

                }
            }
            if (piece.character == 's') {
                if (piece.color == 'r') {
                    values[0][0] += evalPieceValue(1);
                } else {
                    values[1][0] += evalPieceValue(1);
                }
            }
            if (piece.character == 'x') {
                if (piece.color == 'r') {
                    values[0][0] += evalPieceValue(2);
                } else {
                    values[1][0] += evalPieceValue(2);
                }
            }
            if (piece.character == 'm') {
                if (piece.color == 'r') {
                    values[0][0] += evalPieceValue(3);
                    values[0][1] += evalPiecePosition(3, piece.position);
                } else {
                    values[1][0] += evalPieceValue(3);
                    values[1][1] += evalPiecePosition(3, reversePosition);
                }
            }
            if (piece.character == 'j') {
                if (piece.color == 'r') {
                    values[0][0] += evalPieceValue(4);
                    values[0][1] += evalPiecePosition(4, piece.position);
                } else {
                    values[1][0] += evalPieceValue(4);
                    values[1][1] += evalPiecePosition(4, reversePosition);
                }
            }
            if (piece.character == 'p') {
                if (piece.color == 'r') {
                    values[0][0] += evalPieceValue(5);
                    values[0][1] += evalPiecePosition(5, piece.position);
                } else {
                    values[1][0] += evalPieceValue(5);
                    values[1][1] += evalPiecePosition(5, reversePosition);
                }
            }
            if (piece.character == 'z') {
                if (piece.color == 'r') {
                    values[0][0] += evalPieceValue(6);
                    values[0][1] += evalPiecePosition(6, piece.position);
                } else {
                    values[1][0] += evalPieceValue(6);
                    values[1][1] += evalPiecePosition(6, reversePosition);
                }
            }
        }
        int sumRed = 0, sumBlack = 0;
        for (int i=0;i<6 ;i++) {
            sumRed += values[0][i];
            sumBlack += values[1][i];
        }
        if (player == 'r') return sumRed - sumBlack;
        else if (player == 'b') return sumBlack - sumRed;
        return -1;
    }


    private int evalPieceValue(int p) {
        // b | s | x | m | j | p | z
        int[] pieceValue = new int[]{10000, 110, 110, 300, 600, 300, 70};
        return pieceValue[p];
    }

    private int evalPiecePosition(int p, int[] pos) {
        int[][] pPosition = new int[][]{
                {6, 4, 0, -10, -12, -10, 0, 4, 6},
                {2, 2, 0, -4, -14, -4, 0, 2, 2},
                {2, 2, 0, -10, -8, -10, 0, 2, 2},
                {0, 0, -2, 4, 10, 4, -2, 0, 0},
                {0, 0, 0, 2, 8, 2, 0, 0, 0},
                {-2, 0, 4, 2, 6, 2, 4, 0, -2},
                {0, 0, 0, 2, 4, 2, 0, 0, 0},
                {4, 0, 8, 6, 10, 6, 8, 0, 4},
                {0, 2, 4, 6, 6, 6, 4, 2, 0},
                {0, 0, 2, 6, 6, 6, 2, 0, 0}
        };
        int[][] mPosition = new int[][]{
                {4, 8, 16, 12, 4, 12, 16, 8, 4},
                {4, 10, 28, 16, 8, 16, 28, 10, 4},
                {12, 14, 16, 20, 18, 20, 16, 14, 12},
                {8, 24, 18, 24, 20, 24, 18, 24, 8},
                {6, 16, 14, 18, 16, 18, 14, 16, 6},
                {4, 12, 16, 14, 12, 14, 16, 12, 4},
                {2, 6, 8, 6, 10, 6, 8, 6, 2},
                {4, 2, 8, 8, 4, 8, 8, 2, 4},
                {0, 2, 4, 4, -2, 4, 4, 2, 0},
                {0, -4, 0, 0, 0, 0, 0, -4, 0}
        };
        int[][] jPosition = new int[][]{
                {14, 14, 12, 18, 16, 18, 12, 14, 14},
                {16, 20, 18, 24, 26, 24, 18, 20, 16},
                {12, 12, 12, 18, 18, 18, 12, 12, 12},
                {12, 18, 16, 22, 22, 22, 16, 18, 12},
                {12, 14, 12, 18, 18, 18, 12, 14, 12},
                {12, 16, 14, 20, 20, 20, 14, 16, 12},
                {6, 10, 8, 14, 14, 14, 8, 10, 6},
                {4, 8, 6, 14, 12, 14, 6, 8, 4},
                {8, 4, 8, 16, 8, 16, 8, 4, 8},
                {-2, 10, 6, 14, 12, 14, 6, 10, -2}
        };
        int[][] zPosition = new int[][]{
                {0, 3, 6, 9, 12, 9, 6, 3, 0},
                {18, 36, 56, 80, 120, 80, 56, 36, 18},
                {14, 26, 42, 60, 80, 60, 42, 26, 14},
                {10, 20, 30, 34, 40, 34, 30, 20, 10},
                {6, 12, 18, 18, 20, 18, 18, 12, 6},
                {2, 0, 8, 0, 8, 0, 8, 0, 2},
                {0, 0, -2, 0, 4, 0, -2, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0}
        };
        if (p == 3) return mPosition[pos[0]][pos[1]];
        if (p == 4) return jPosition[pos[0]][pos[1]];
        if (p == 5) return pPosition[pos[0]][pos[1]];
        if (p == 6) return zPosition[pos[0]][pos[1]];
        return -1;
    }

    private int evalPieceControl() {
        return 0;
    }

    private int evalPieceFlexible(int p) {
        // b | s | x | m | j | p | z
        int[] pieceFlexible = new int[]{0, 1, 1, 13, 7, 300, 15};
        return 0;
    }

    private int evalPieceProtect() {
        return 0;
    }

    private int evalPieceFeature() {
        return 0;
    }
}