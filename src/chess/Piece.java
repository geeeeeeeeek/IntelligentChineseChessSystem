package chess;

/**
 * Created by Tong on 12.03.
 * Chess > Piece entity
 */
public class Piece {
    public char color;
    public char owner;
    public char index;
    public int[] position = new int[2];

    public Piece(String name,int[] position) {
        this.color = name.charAt(0);
        this.owner = name.charAt(1);
        this.index = name.charAt(2);
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
