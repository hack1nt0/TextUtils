import mlearn.DocumentTermMatrix;
import mlearn.bayesian.LatentDirichletAllocation;
import mlearn.kmeans.KmeansX;
import mlearn.tokenizer.LmHmmTokenizer;
import template.debug.ScannerUTF8;

import java.io.IOException;

/**
 * @author dy[jealousing@gmail.com] on 17-7-18.
 */
public class Main {
    public static void main(String[] args) throws IOException {
        if (args.length < 1) {
            System.err.println("Options:");
            System.err.println("-chars(List chars, for utf-8 testing)");
            System.err.println("-t(tokenizer)");
            System.err.println("-dtm(DocumentTermMatrix)");
            System.err.println("-kmeans");
            System.err.println("-lda");
            return;
        }
        if (args[0].equals("-chars")) {
            ScannerUTF8.main(null);
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
        if (args[0].equals("-kmeans")) {
            KmeansX.main(new String[]{args[1], args[2]});
            return;
        }
        if (args[0].equals("-lda")) {
            LatentDirichletAllocation.main(new String[]{args[1], args[2], args[3]});
        }
    }
}
