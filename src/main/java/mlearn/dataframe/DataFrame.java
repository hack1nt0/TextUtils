package mlearn.dataframe;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import syntax.csv.CSVBaseListener;
import syntax.csv.CSVLexer;
import syntax.csv.CSVParser;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author dy[jealousing@gmail.com] on 17-5-11.
 */
public class DataFrame {
    List<List> frames;
    List<String> colNames;

    public DataFrame(InputStream in) {
        this(in, true);
    }

    public DataFrame(InputStream in, boolean hasHeadLine) {
        try {
            Lexer lexer = new CSVLexer(CharStreams.fromStream(in));
            CSVParser parser = new CSVParser(new CommonTokenStream(lexer));
            CSVParser.CsvFileContext tree = parser.csvFile();
            CsvExtractor listener = new CsvExtractor();
            ParseTreeWalker.DEFAULT.walk(listener, tree);
            frames = listener.frames;
            colNames = listener.colNames;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public DataFrame(List... frames) {
        this.frames = Arrays.asList(frames);
    }

    public void setColNames(String... colNames) {
        this.colNames = Arrays.asList(colNames);
    }

    public void add(String name, List<String> frame) {
        colNames.add(name);
        frames.add(frame);
    }

    public List get(String name) {
        int index = colNames.indexOf(name);
        if (index < 0) throw new RuntimeException("no column names " + name);
        return frames.get(index);
    }

    public List<String> get(int i) {
        return frames.get(i);
    }

    public int rows() {
        return frames == null ? 0 : frames.get(0).size();
    }

    public int cols() {
        return frames.size();
    }

    public void toCsvFile(OutputStream outputStream) {
        PrintWriter out = new PrintWriter(new BufferedOutputStream(outputStream));
        if (colNames == null) {
            for (int i = 1; i <= cols(); ++i) {
                out.print("x." + i);
                out.print(i == cols() ? '\n' : ',');
            }
        } else {
            for (int i = 1; i <= cols(); ++i) {
                out.print(colNames.get(i - 1));
                out.print(i == cols() ? '\n' : ',');
            }
        }

        for (int i = 0; i < rows(); ++i) {
            for (int j = 0; j < cols(); ++j) {
                String field = frames.get(j).get(i).toString();
                field = field.replaceAll("\"", "\"\"");
                out.printf("%s%s%s", '"', field, '"');
                out.print(j == cols() - 1 ? '\n' : ',');
            }
        }
        out.flush();
    }

    static class CsvExtractor extends CSVBaseListener {
        private boolean visitedHdr = false;
        public List<List> frames = new ArrayList<>();
        public List<String> colNames = new ArrayList<>();

        @Override
        public void enterHdr(CSVParser.HdrContext ctx) {
            visitedHdr = true;
        }

        @Override
        public void exitHdr(CSVParser.HdrContext ctx) {
            visitedHdr = false;
        }

        @Override
        public void enterRow(CSVParser.RowContext ctx) {
            if (visitedHdr) {
                for (int i = 0; i < ctx.getChildCount(); i += 2) {
                    String colName = ctx.getChild(i).getText().trim();
                    if (colName.length() > 0 && colName.charAt(0) == '"') {
                        colName = colName.substring(1, colName.length() - 1);
                        colName = colName.replaceAll("\"\"", "\"");
                    }
                    colNames.add(colName);
                    frames.add(new ArrayList<String>());
                }
            } else {
                for (int i = 0; i < ctx.getChildCount(); i += 2) {
                    String field = ctx.getChild(i).getText().trim();
                    if (field.length() > 0 && field.charAt(0) == '"') {
                        field = field.substring(1, field.length() - 1);
                        field = field.replaceAll("\"\"", "\"");
                    }
                    frames.get(i / 2).add(field);
                }
            }
        }
    }

    @Override
    public String toString() {
        StringWriter stringWriter = new StringWriter();
        PrintWriter out = new PrintWriter(stringWriter);
        StringBuffer format = new StringBuffer();
        for (int i = 0; i < cols(); ++i) {
            int w = Math.max(colNames.get(i).length(), width(frames.get(i)) + 5);
            boolean isNumber = frames.get(i).get(0) instanceof Double ||
                    frames.get(i).get(0) instanceof Float ||
                    frames.get(i).get(0) instanceof Integer ||
                    frames.get(i).get(0) instanceof Long;
            format.append("%" + (isNumber ? "" : "-") + w + "s\t");
        }
        format.append("\n");
        out.printf(format.toString(), colNames.toArray(new String[0]));
        for (int i = 0; i < rows(); ++i) {
            String[] row = new String[cols()];
            for (int j = 0; j < cols(); ++j) row[j] = frames.get(j).get(i).toString();
            out.printf(format.toString(), row);
        }
        return stringWriter.toString();
    }

    private int width(List list) {
        int maxWidth = 0;
        for (int i = 0; i < list.size(); ++i) maxWidth = Math.max(maxWidth, list.get(i).toString().length());
        return maxWidth;
    }


    public static void main(String[] args) throws Exception {
        DataFrame dataFrame = new DataFrame(new FileInputStream("/Users/dy/TextUtils/data/train/spamsms.csv"));
        OutputStream outputStream = new FileOutputStream("/Users/dy/TextUtils/data/train/spamsms.csv.1");
        dataFrame.toCsvFile(outputStream);
        outputStream.close();
        System.out.println(dataFrame.colNames);
    }
}
