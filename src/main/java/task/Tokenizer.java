package task;

import mlearn.tokenizer.LmHmmTokenizer;

import java.util.Scanner;
import java.io.PrintWriter;

public class Tokenizer {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        in.useDelimiter("\\A");
        String text = in.next();
        in.useDelimiter("\\p{javaWhitespace}+");
        System.out.println(text);
        System.out.println(new LmHmmTokenizer().split(text));
    }
}
