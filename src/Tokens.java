import java.util.ArrayList;
import java.util.Stack;

public class Tokens {
    Stack<Token> tokens;
    public Tokens(ArrayList<Token> tokens) {
        this.tokens = new Stack<Token>();
        for (int i = tokens.size() - 1; i >= 0; i--) {
            this.tokens.add(tokens.get(i));
        }
    }
    public Token next() {
        return tokens.pop();
    }
    public Token peek() {
        return tokens.peek();
    }
    public void putBack(Token t) {
        tokens.push(t);
    }
    public boolean hasNext() {
        return !tokens.empty();
    }
}
