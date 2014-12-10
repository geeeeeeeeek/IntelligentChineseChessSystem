package view;

import chess.Board;
import chess.Piece;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Tong on 12.10.
 */
public class GameView {
    Map<String, JLabel> pieceObjects = new HashMap<String, JLabel>();
    private static final int VIEW_WIDTH = 700, VIEW_HEIGHT = 712;
    private static final int PIECE_WIDTH = 67, PIECE_HEIGHT = 67;
    private static final int SY_COE = 68, SX_COE = 68;
    private static final int SX_OFFSET = 50, SY_OFFSET = 15;

    public void init(Board board) {
        final JFrame frame = new JFrame("Intelligent Chinese Chess - dev by zhongyi.tong");
        frame.setIconImage(new ImageIcon("res/img/rb.png").getImage());
        frame.setSize(VIEW_WIDTH, VIEW_HEIGHT + 40);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        JLayeredPane pane = new JLayeredPane();
        frame.add(pane);

        ImageIcon boardIcon = new ImageIcon("res/img/board.png");
        boardIcon.setImage(boardIcon.getImage().getScaledInstance(VIEW_WIDTH, VIEW_HEIGHT, Image.SCALE_DEFAULT));
        JLabel bgBoard = new JLabel(boardIcon);
        bgBoard.setLocation(0, 0);
        bgBoard.setSize(VIEW_WIDTH, VIEW_HEIGHT);
        bgBoard.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int[] sPos = new int[]{e.getXOnScreen() - frame.getX(), e.getYOnScreen() - frame.getY()};
                int[] pos = viewToModelConverter(sPos);
                JOptionPane.showMessageDialog(null, pos[0] + " " + pos[1], "alert", JOptionPane.ERROR_MESSAGE);
                super.mouseClicked(e);
            }
        });
        pane.add(bgBoard, 1);

        Map<String, Piece> pieces = board.pieces;
        for (Map.Entry<String, Piece> stringPieceEntry : pieces.entrySet()) {
            String key = stringPieceEntry.getKey();
            int[] pos = stringPieceEntry.getValue().position;
            ImageIcon pieceIcon = new ImageIcon("res/img/" + key.substring(0, 2) + ".png");
            pieceIcon.setImage(pieceIcon.getImage().getScaledInstance(PIECE_WIDTH, PIECE_HEIGHT, Image.SCALE_DEFAULT));
            JLabel bgpiece = new JLabel(pieceIcon);

            int[] sPos = modelToViewConverter(pos);
            bgpiece.setLocation(sPos[0], sPos[1]);
            bgpiece.setSize(PIECE_WIDTH, PIECE_HEIGHT);
            bgpiece.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    int[] sPos = new int[]{e.getXOnScreen() - frame.getX(), e.getYOnScreen() - frame.getY()};
                    int[] pos = viewToModelConverter(sPos);
                    JOptionPane.showMessageDialog(null, pos[0] + " " + pos[1], "alert", JOptionPane.ERROR_MESSAGE);
                    super.mouseClicked(e);
                }
            });
            pieceObjects.put(stringPieceEntry.getKey(), bgpiece);
            pane.add(bgpiece, 0);
        }
        frame.setVisible(true);
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
}
