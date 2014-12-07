package chess;

/**
 * Created by Tong on 12.03.
 * Chess > Piece entity
 */
public class Piece {
    public char color;
    public char owner;
    public int[] position = new int[2];

    public Piece(char color, char owner, int[] position) {
        this.color = color;
        this.owner = owner;
        this.position = position;
    }

    public boolean moveTo(int[] newPosition) {
        if (Board.isInside(newPosition)) {
            this.position = newPosition;
            return true;
        }
        return false;
    }
}
