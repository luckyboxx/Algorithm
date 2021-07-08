package huffman;

public class Node {
    public int freq;
    public char alphabet;
    public Node leftNode;
    public Node rightNode;

    public Node(int freq, char alphabet) {
        this.freq = freq;
        this.alphabet = alphabet;
        leftNode = rightNode = null;
    }
}
