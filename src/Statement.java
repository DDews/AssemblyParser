public class Statement extends Node {
    public Statement(Tokens tokens) throws Exception {
        Token current = tokens.peek();
        type = Type.STATEMENT;
        switch (current.type) {
            case PUNCTUATION:
                children.add(new Comment(tokens));
                children.add(new NewLine(tokens));
                break;
            case WORD:
                children.add(new Instruction(tokens));
                children.add(new NewLine(tokens));
                break;
            case LABEL:
                children.add(new Label(tokens));
                break;
            default:
                throwEx("Expected COMMENT or INSTRUCTION, but encountered: " + current);
        }
    }
}
