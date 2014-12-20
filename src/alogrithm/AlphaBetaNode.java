package alogrithm;

/**
 * Created by Tong on 12.18.
 * Store piece move in alpha beta search.
 */
public class AlphaBetaNode {
    public String piece;
    public int[] from;
    public int[] to;
    public int value;

    public AlphaBetaNode(String piece, int[] from, int[] to) {
        this.piece = piece;
        this.from = from;
        this.to = to;
    }
}
