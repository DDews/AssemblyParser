import java.util.ArrayList;

public class Parser {
    Node head;
    public Parser(ArrayList<Token> tokens) {
        Tokens stack = new Tokens(tokens);
        try {
            head = new Statements(stack);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(head);
    }
}
