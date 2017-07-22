package syntax.wikipedia;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTree;

/**
 * @author dy[jealousing@gmail.com] on 17-7-9.
 */

public class TestHello {
    public static void main(String[] args) throws Exception {
        CharStream input = CharStreams.fromString("hello world.");
        Lexer lexer = new HelloLexer(input);
        TokenStream tokens = new CommonTokenStream(lexer);
        HelloParser parser = new HelloParser(tokens);
        ParseTree tree = parser.r();
        System.out.println(tree.toStringTree(parser));
    }
}
