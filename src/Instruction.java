public class Instruction extends Node {
    public Instruction(Tokens tokens) throws Exception {
        type = Type.INSTRUCTION;
        Token next = tokens.next();
        token = next;
        switch (next.data.toUpperCase()) {
            case "MOVE":
            case "MOVEI":
                twoOperands(tokens);
                break;
            case "INC":
            case "DEC":
                children.add(new Location(tokens));
                break;
            case "SUB":
            case "MUL":
            case "DIV":
            case "ADD":
                threeOperands(tokens);
                break;
            case "BEQ":
            case "BLT":
            case "BGT":
                twoOperandsAndLabel(tokens);
                break;
            case "BR":
                children.add(new Location(tokens));
                break;
            default:
                throwEx("Invalid OP-code: " + next.data);
        }
    }
    public void eatComma(Tokens tokens) throws Exception {
        Token comma = tokens.next();
        if (comma.type != Lexicon.PUNCTUATION && comma.data != ",") throwEx("INSTRUCTION: No comma between operands. Instead: " + comma);
    }
    public void threeOperands(Tokens tokens) throws Exception {
        children.add(new Operand(tokens));
        eatComma(tokens);
        children.add(new Operand(tokens));
        eatComma(tokens);
        children.add(new Operand(tokens));
    }
    public void twoOperands(Tokens tokens) throws Exception  {
        children.add(new Operand(tokens));
        eatComma(tokens);
        children.add(new Operand(tokens));
    }
    public void twoOperandsAndLabel(Tokens tokens) throws Exception  {
        children.add(new Operand(tokens));
        eatComma(tokens);
        children.add(new Operand(tokens));
        eatComma(tokens);
        children.add(new Location(tokens));
    }
}
