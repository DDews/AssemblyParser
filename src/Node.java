import java.util.ArrayList;

public class Node {
    Type type;
    Token token;
    ArrayList<Node> children;
    public Node() {
        type = Type.UNKNOWN;
        token = new Token();
        children = new ArrayList<Node>();
    }
    public void throwEx(String msg) throws Exception {
        System.err.println("Exception when parsing Token " + token + " in line " + token.line);
        throw new Exception(msg);
    }
    public String toString() {
        return "[" + type + " " + token + ": " + children + "]";
    }
}
