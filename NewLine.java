public class NewLine extends Node {
    public NewLine(Tokens tokens) throws Exception {
        Token next = tokens.next();
        token = next;
        type = Type.NEWLINE;
        if (next.type != Lexicon.NEWLINE) {
            throwEx("Expected NEWLINE, but encountered " + next);
        }
    }
    @Override
    public String toString() {
        return "[NEWLINE: \\n]";
    }
}
