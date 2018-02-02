import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Lexer {
    private static final int MAX_LABEL_LENGTH = 30;
    Scanner in;
    File file;
    Token current;
    LexerState state;
    ArrayList<Token> tokens;
    int lines;

    public Lexer(File file) {
        state = LexerState.START;
        lines = 0;
        try {
            this.file = file;
            in = new Scanner(file);
        } catch (Exception e) {
            System.err.println("Encountered error while parsing " + file);
            e.printStackTrace();
        }
    }
    public ArrayList<Token> parse() {
        tokens = new ArrayList<Token>();
        try {
            read(file);
        } catch (Exception e) {
            System.err.println("Encountered error when lexically parsing...");
            e.printStackTrace();
        }
        return tokens;
    }
    public void read(File file) throws Exception {
        current = new Token("0:0");
        while (in.hasNext()) {
            lines++;
            parseLine(in.nextLine() + "\n");
        }
    }
    public void parseLine(String line) throws Exception {
        for (int c = 0; c < line.length(); c++) {
            char x = line.charAt(c);
            switch (state) {
                case START:
                    if (Character.isLetter(x)) {
                        current.data += x;
                        switch (Character.toLowerCase(x)) {
                            case 'r':
                                current.type = Lexicon.REGISTER;
                                state = LexerState.REGISTER;
                                break;
                            default:
                                state = LexerState.WORD;
                                current.type = Lexicon.WORD;
                        }
                    }
                    else if (Character.isDigit(x)) {
                        current.data += x;
                        current.type = Lexicon.NUMBER;
                        state = LexerState.NUMBER;
                    } if (isPunctuation(x)){
                        current.data = x + "";
                        switch (x) {
                            case '\n':
                            case '\r':
                                current.type = Lexicon.NEWLINE;
                                break;
                            case ':':
                                current.type = Lexicon.LABEL;
                                state = LexerState.LABEL;
                                break;
                            default:
                                current.type = Lexicon.PUNCTUATION;
                        }
                        tokens.add(current);
                        current = new Token(lines,c - current.data.length());
                    } else if (Character.isWhitespace(x)) {
                    // skip whitespace
                    }
                    break;
                case NUMBER:
                    if (Character.isDigit(x)) {
                        current.data += x;
                    } else if (x != '\n' && Character.isWhitespace(x)) {
                        tokens.add(current);
                        current = new Token(lines,c - current.data.length());
                        state = LexerState.START;
                    } else if (isPunctuation(x)) {
                        tokens.add(current);
                        current = new Token(lines,c - current.data.length());
                        state = LexerState.START;
                        c--; // put char back
                    } else {
                        throw new Exception("Invalid character '\" + x + \"' when in parsing state '" + state + "' at line " + lines + ":" + c);
                    }
                    break;
                case WORD:
                    if (Character.isLetter(x)) {
                        current.data += x;
                    } else if (x != '\n' && Character.isWhitespace(x)) {
                        tokens.add(current);
                        current = new Token(lines,c - current.data.length());;
                        state = LexerState.START;
                    } else if (isPunctuation(x)) {
                        switch (x) {
                            case ':':
                                current.type = Lexicon.LABEL;
                                break;
                            default:
                                current.type = Lexicon.WORD;
                        }
                        tokens.add(current);
                        current = new Token(lines,c - current.data.length());
                        state = LexerState.START;
                        c--; // put back character
                    } else {
                        throw new Exception("Invalid character '" + x + "' when in parsing state '" + state + "' at line " + lines + ":" + c);
                    }
                    break;
                case REGISTER:
                    if (Character.isDigit(x) && current.data.length() < 4) {
                        current.data += x;
                        current.type = Lexicon.REGISTER;
                    } else if (Character.isWhitespace(x)) {
                        tokens.add(current);
                        current = new Token(lines,c - current.data.length());
                        state = LexerState.START;
                    } else if (isPunctuation(x)) {
                        tokens.add(current);
                        current = new Token(lines,c - current.data.length());
                        state = LexerState.START;
                        c--; // put char back
                    } else {
                            throw new Exception("Invalid character '" + x + "' when in parsing state '" + state + "'' at line " + lines + ":" + c);
                    }
                    break;
                default:
                    throw new Exception("Invalid state '" + state + "' in lexical parsing.");
            }
        }
    }
    public boolean isPunctuation(char x) {
        switch (x) {
            case ':':
            case ',':
            case '\n':
            case '\r':
                return true;
            default:
                return false;
        }
    }
}
