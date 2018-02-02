public class Value extends Node {
    public Value(Tokens tokens) throws Exception {
        Token next = tokens.next();
        token = next;
        type = Type.VALUE;
        if (next.type != Lexicon.WORD) {
            throwEx("Expected VALUE but encountered " + next);
        }
        try {
            int n = Integer.parseInt(next.data);
        } catch (Exception e) {
            throwEx("Invalid unsigned integer VALUE: " + next);
        }
        if (next.data.contains("9")) {
            throwEx("Invalid octal VALUE: " + next);
        }
    }
}
