public class Comment extends Node {
    public Comment(Tokens tokens) throws Exception {
        Token next = tokens.next();
        if (next.type != Lexicon.COMMENT) {
            throwEx("Expected COMMENT but encountered: " + next);
            return;
        }
        token = next;
        type = Type.COMMENT;
    }
}
