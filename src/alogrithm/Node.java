package alogrithm;

/**
 * Created by Tong on 12.18.
 */
public class Node {
    public String piece;
    int[] from;
    public int[] to;
    int value;
    Node parent;

    public Node(String piece, int[] from, int[] to, Node parent) {
        this.piece = piece;
        this.from = from;
        this.to = to;
        this.parent = parent;
    }


}
