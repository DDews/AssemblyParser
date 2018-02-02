import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter source filename: ");
        String filename = in.nextLine();
        ArrayList<Token> tokens = new Lexer(new File(filename)).parse();
        System.out.println("Tokens:");
        System.out.println(tokens);
        Parser parser = new Parser(tokens);
    }
}
