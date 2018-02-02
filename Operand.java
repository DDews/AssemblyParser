public class Operand extends Node {
    public Operand(Tokens tokens) throws Exception {
        Token next = tokens.peek();
        token = next;
        type = Type.OPERAND;
        if (next.type == Lexicon.LABEL) {
            children.add(new Label(tokens));
        } else {
            children.add(new Location(tokens));
        }
    }
}
