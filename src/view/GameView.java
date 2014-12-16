package view;

import chess.Board;
import chess.Piece;
import chess.Rules;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Tong on 12.10.
 * Dealing with graphics logic in the chess game. Render with j2d.
 */
public class GameView {
    private Map<String, JLabel> pieceObjects = new HashMap<String, JLabel>();
    private Board board;
    private static final int VIEW_WIDTH = 700, VIEW_HEIGHT = 712;
    private static final int PIECE_WIDTH = 67, PIECE_HEIGHT = 67;
    private static final int SY_COE = 68, SX_COE = 68;
    private static final int SX_OFFSET = 50, SY_OFFSET = 15;
    private String selectedPieceKey;
    private JFrame frame;
    private JLayeredPane pane;

    public void init(final Board board) {
        this.board = board;
        frame = new JFrame("Intelligent Chinese Chess - Dev by Zhongyi.TONG");
        frame.setIconImage(new ImageIcon("res/img/icon.png").getImage());
        frame.setSize(VIEW_WIDTH, VIEW_HEIGHT + 40);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        pane = new JLayeredPane();
        frame.add(pane);

        /* Initialize chess board and listeners on each slot.*/
        JLabel bgBoard = new JLabel(new ImageIcon("res/img/board.png"));
        bgBoard.setLocation(0, 0);
        bgBoard.setSize(VIEW_WIDTH, VIEW_HEIGHT);
        bgBoard.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (selectedPieceKey != null) {
                    int[] sPos = new int[]{e.getXOnScreen() - frame.getX(), e.getYOnScreen() - frame.getY()};
                    int[] pos = viewToModelConverter(sPos);
                    int[] selectedPiecePos = board.pieces.get(selectedPieceKey).position;
                    for (int[] each : Rules.getNext(selectedPieceKey, selectedPiecePos, board)) {
                        if (Arrays.equals(each, pos)) {
                            board.updatePiece(selectedPieceKey, pos);
                            movePieceFromModel(selectedPieceKey, pos);
                            break;
                        }
                    }

                }
            }
        });
        pane.add(bgBoard, 1);


        /* Initialize chess pieces and listeners on each piece.*/
        Map<String, Piece> pieces = board.pieces;
        for (Map.Entry<String, Piece> stringPieceEntry : pieces.entrySet()) {
            String key = stringPieceEntry.getKey();
            int[] pos = stringPieceEntry.getValue().position;
            int[] sPos = modelToViewConverter(pos);
            JLabel lblPiece = new JLabel(new ImageIcon("res/img/" + key.substring(0, 2) + ".png"));

            lblPiece.setLocation(sPos[0], sPos[1]);
            lblPiece.setSize(PIECE_WIDTH, PIECE_HEIGHT);
            lblPiece.addMouseListener(new PieceOnClickListener(key));
            pieceObjects.put(stringPieceEntry.getKey(), lblPiece);
            pane.add(lblPiece, 0);
        }
        frame.setVisible(true);
    }

    public void movePieceFromModel(String pieceKey, int[] to) {
        JLabel pieceObject = pieceObjects.get(pieceKey);
        int[] sPos = modelToViewConverter(to);
        pieceObject.setLocation(sPos[0], sPos[1]);
        /* Clear 'from' and 'to' info on the board */
        selectedPieceKey = null;
    }

    private int[] modelToViewConverter(int pos[]) {
        int sx = pos[1] * SX_COE + SX_OFFSET, sy = pos[0] * SY_COE + SY_OFFSET;
        return new int[]{sx, sy};
    }

    private int[] viewToModelConverter(int sPos[]) {
        /* To make things right, I have to put an 'additional sy offset'. God knows why. */
        int ADDITIONAL_SY_OFFSET = 25;
        int y = (sPos[0] - SX_OFFSET) / SX_COE, x = (sPos[1] - SY_OFFSET - ADDITIONAL_SY_OFFSET) / SY_COE;
        return new int[]{x, y};
    }

    public class PieceOnClickListener extends MouseAdapter {
        private String key;

        public PieceOnClickListener(String key) {
            this.key = key;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            if (selectedPieceKey != null && key.charAt(0) != board.player) {
                int[] pos = board.pieces.get(key).position;
                int[] selectedPiecePos = board.pieces.get(selectedPieceKey).position;
                /* If an enemy piece already has been selected.*/
                for (int[] each : Rules.getNext(selectedPieceKey, selectedPiecePos, board)) {
                    if (Arrays.equals(each, pos)) {
                        // Kill self and move that piece.
                        pane.remove(pieceObjects.get(key));
                        pieceObjects.remove(key);
                        board.updatePiece(selectedPieceKey, pos);
                        movePieceFromModel(selectedPieceKey, pos);
                        break;
                    }
                }
            } else if (key.charAt(0) == board.player) {
                /* Select the piece.*/
                selectedPieceKey = key;
            }


        }
    }

}
