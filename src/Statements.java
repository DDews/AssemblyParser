public class Statements extends Node {
    public Statements(Tokens tokens) throws Exception {
        children.add(new Statement(tokens));
        type = Type.STATEMENTS;
        token = null;
        if (!tokens.hasNext()) {
            return;
        }
        children.add(new Statements(tokens));
    }
}
