import mlearn.DocumentTermMatrix;
import mlearn.tokenizer.LmHmmTokenizer;

/**
 * @author dy[jealousing@gmail.com] on 17-7-18.
 */
public class Main {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Options:");
            System.out.println("-t(tokenizer)");
            System.out.println("-dtm(DocumentTermMatrix)");
            return;
        }
        if (args[0].equals("-t")) {
            LmHmmTokenizer.main(null);
            return;
        }
        if (args[0].equals("-dtm")) {
            DocumentTermMatrix.main(null);
            return;
        }
    }
}
