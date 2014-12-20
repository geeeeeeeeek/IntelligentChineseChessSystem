package chess;

/**
 * Created by Tong on 12.03.
 * Chess > Piece entity
 */
public class Piece implements Cloneable {
    public String key;
    public char color;
    public char character;
    public char index;
    public int[] position = new int[2];

    public Piece(String name, int[] position) {
        this.key = name;
        this.color = name.charAt(0);
        this.character = name.charAt(1);
        this.index = name.charAt(2);
        this.position = position;
    }

}
