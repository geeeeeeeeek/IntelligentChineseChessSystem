package alogrithm;

import chess.Board;
import chess.Piece;
import chess.Rules;
import control.GameController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Tong on 12.08.
 * Alpha beta search.
 */
public class SearchModel {
    private Board board;

    private GameController controller = new GameController();

    public Map<String, int[]> search(Board board) {
        this.board = (Board) board.clone();
        /* Return evaluation if reaching leaf node or any side won.*/
        ArrayList<Node> moves = new ArrayList<Node>();
        /* Generate all possible moves*/
        for (Map.Entry<String, Piece> stringPieceEntry : board.pieces.entrySet()) {
            Piece piece = stringPieceEntry.getValue();
            ArrayList<int[]> next = Rules.getNextMove(piece.key, piece.position, board);
            for (int[] nxt : next) {
                Node n = new Node(piece.key, piece.position, nxt, null);
                n.value = alphaBeta(null, 3, Integer.MIN_VALUE, Integer.MAX_VALUE, true);
                moves.add(n);
            }
        }
        int max = Integer.MIN_VALUE;
        Map<String, int[]> piece = new HashMap<String, int[]>();
        for (Node n : moves) {
            max = Math.max(n.value, max);
            piece.clear();
            piece.put(n.piece, n.to);
        }
        return piece;
    }

    private int alphaBeta(Node node, int depth, int alpha, int beta, boolean isMax) {
        /* Return evaluation if reaching leaf node or any side won.*/
        if (depth == 0 || controller.hasWin(board) != 'x') return new EvalModel().eval(board,node.piece.charAt(0));
        ArrayList<Node> moves = new ArrayList<Node>();
        /* Generate all possible moves*/
        for (Map.Entry<String, Piece> stringPieceEntry : board.pieces.entrySet()) {
            Piece piece = stringPieceEntry.getValue();
            ArrayList<int[]> next = Rules.getNextMove(piece.key, piece.position, board);
            for (int[] nxt : next) {
                Node n = new Node(piece.key, piece.position, nxt, node);
                moves.add(n);
            }
        }
        if (isMax) {
            /* Maximizing player*/
            for (Node n : moves) {
                //TODO if updatePiece results in a deletion of another piece, the deleted piece has to be stored when rolling back!
                board.updatePiece(n.piece, n.to);
                alpha = Math.max(alpha, alphaBeta(n, depth - 1, alpha, beta, false));
                board.updatePiece(n.piece, n.from);
                if (beta <= alpha)
                    /* Beta cut-off */
                    break;
            }
            return alpha;
        } else {
            for (Node n : moves) {
                board.updatePiece(n.piece, n.to);
                beta = Math.min(beta, alphaBeta(n, depth - 1, alpha, beta, true));
                board.updatePiece(n.piece, n.from);
                if (beta <= alpha)
                    /* Alpha cut-off */
                    break;
            }
            return beta;
        }
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