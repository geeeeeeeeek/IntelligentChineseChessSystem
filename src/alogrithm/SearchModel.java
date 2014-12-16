package alogrithm;

import chess.Board;
import chess.Piece;
import chess.Rules;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by Tong on 12.08.
 */
public class SearchModel {
    private Board board;
    private EvalModel evalModel = new EvalModel(board, 'b');

    public SearchModel(Board board) {
        this.board = (Board) board.clone();
        alphaBeta(null, 3, Integer.MAX_VALUE, Integer.MIN_VALUE, true);
    }

    private int alphaBeta(Node node, int depth, int alpha, int beta, boolean isMax) {
        if (depth == 0) return evalModel.eval();
        ArrayList<Node> moves = new ArrayList<Node>();
        for (Map.Entry<String, Piece> stringPieceEntry : board.pieces.entrySet()) {
            Piece piece = stringPieceEntry.getValue();
            ArrayList<int[]> next = Rules.getNextMove(piece.key, piece.position, board);
            for (int[] nxt : next) {
                Node n = new Node(piece.key, piece.position, nxt, node);
            }

        }
        return 0;
    }
}

class Node {
    String piece;
    int[] from;
    int[] to;
    int value;
    Node parent;

    Node(String piece, int[] from, int[] to, Node parent) {
        this.piece = piece;
        this.from = from;
        this.to = to;
        this.parent = parent;
    }


}