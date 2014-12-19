package alogrithm;

import chess.Board;
import chess.Piece;
import chess.Rules;
import control.GameController;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by Tong on 12.08.
 * Alpha beta search.
 */
public class SearchModel {
    private Board board;

    private GameController controller = new GameController();

    public Node search(Board board) {
        this.board = (Board) board.clone();
        /* Return evaluation if reaching leaf node or any side won.*/
        ArrayList<Node> moves = new ArrayList<Node>();
        /* Generate all possible moves*/
        for (Map.Entry<String, Piece> stringPieceEntry : board.pieces.entrySet()) {
            Piece piece = stringPieceEntry.getValue();
            if (piece.color == 'r') continue;
            ArrayList<int[]> next = Rules.getNextMove(piece.key, piece.position, board);
            for (int[] nxt : next) {
                Node n = new Node(piece.key, piece.position, nxt, null);
//                n.value = alphaBeta(null, 3, Integer.MIN_VALUE, Integer.MAX_VALUE, false);
                moves.add(n);
            }
        }
        for (Node n : moves) {
            Piece eaten = board.updatePiece(n.piece, n.to);
            n.value = alphaBeta(n, 3, Integer.MIN_VALUE, Integer.MAX_VALUE, true);
            board.updatePiece(n.piece, n.from);
            if (eaten != null) {
                board.pieces.put(eaten.key, eaten);
                board.updatePiece(eaten.key, eaten.position);
                board.player = (board.player == 'r') ? 'b' : 'r';
            }
        }
        int max = Integer.MIN_VALUE;
        Node best = null;
        for (Node n : moves) {
            if (n.value > max) {
                max = n.value;
                best = n;
            }

        }
        return best;
    }

    private int alphaBeta(Node node, int depth, int alpha, int beta, boolean isMax) {
        /* Return evaluation if reaching leaf node or any side won.*/
        if (depth == 0 || controller.hasWin(board) != 'x')
            return new EvalModel().eval(board, node.piece.charAt(0));
        ArrayList<Node> moves = new ArrayList<Node>();
        /* Generate all possible moves*/
        for (Map.Entry<String, Piece> stringPieceEntry : board.pieces.entrySet()) {
            Piece piece = stringPieceEntry.getValue();
            if (isMax && piece.color == 'r') continue;
            if (!isMax && piece.color == 'b') continue;
            ArrayList<int[]> next = Rules.getNextMove(piece.key, piece.position, board);
            for (int[] nxt : next) {
                Node n = new Node(piece.key, piece.position, nxt, node);
                moves.add(n);
            }
        }
        if (isMax) {
            /* Maximizing player*/
            for (Node n : moves) {
                Piece eaten = board.updatePiece(n.piece, n.to);
                alpha = Math.max(alpha, alphaBeta(n, depth - 1, alpha, beta, false));
                board.updatePiece(n.piece, n.from);
                if (eaten != null) {
                    board.pieces.put(eaten.key, eaten);
                    board.updatePiece(eaten.key, eaten.position);
                    board.player = (board.player == 'r') ? 'b' : 'r';
                }
                if (beta <= alpha)
                    /* Beta cut-off */
                    break;
            }
            return alpha;
        } else {
            for (Node n : moves) {
                Piece eaten = board.updatePiece(n.piece, n.to);
                beta = Math.min(beta, alphaBeta(n, depth - 1, alpha, beta, true));
                board.updatePiece(n.piece, n.from);
                if (eaten != null) {
                    board.pieces.put(eaten.key, eaten);
                    board.updatePiece(eaten.key, eaten.position);
                    board.player = (board.player == 'r') ? 'b' : 'r';
                }
                if (beta <= alpha)
                    /* Alpha cut-off */
                    break;
            }
            return beta;
        }
    }
}
