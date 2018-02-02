public class Label extends Node {
    public Label(Tokens tokens) throws Exception {
        Token next = tokens.next();
        type = Type.LABEL;
        token = next;
        if (next.type != Lexicon.LABEL) {
            throwEx("Expected LABEL, encountered " + next.type);
            return;
        }
    }
}
