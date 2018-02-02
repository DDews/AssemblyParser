public class Token {
    Lexicon type;
    String data;
    String line;
    public Token(String line) {
        type = Lexicon.UNKNOWN;
        data = "";
        this.line = line;
    }
    public Token(int line, int c) {
        this(line + ":" + (c + 2));
    }
    public Token() {
        this("?:?");
    }
    public String toString() {
        return "[" + type.toString() + ": " + (data.equals("\n") ? "\\n" : data) + "]";
    }
}
