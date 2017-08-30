//package mlearn;
//
//import template.debug.InputReader;
//
//import java.io.FileInputStream;
//import java.util.ArrayList;
//import java.util.Iterator;
//import java.util.List;
//import java.util.Spliterator;
//
///**
// * @author dy[jealousing@gmail.com] on 17-5-7.
// */
//public class FileUtils {
//
//    private final static char COMMA = ',';
//    private final static char DOUBLE_QUOTATION = '"';
//
//    public static List<String>[] readCsv(String path, boolean hasHead) {
//        try {
//            InputReader in = new InputReader(new FileInputStream(path));
//            return readCsv(in, hasHead);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        throw new RuntimeException();
//    }
//
//    public static List<String>[] readCsv(InputReader in, boolean hasHeadLine) {
//        List<String> colNames = null;
//        List<String>[] matrix = null;
//        if (hasHeadLine) {
//            colNames = readCSVLine(in.readLine());
//        }
//        while (!in.isExhausted()) {
//            List<String> row = readCSVLine(in.readLine());
//            if (colNames != null && row.capacity() != colNames.capacity())
//                throw new RuntimeException("parsing csv line error!");
//            if (matrix == null) {
//                matrix = new List[row.capacity()];
//                for (int i = 0; i < matrix.length; ++i) matrix[i] = new ArrayList<>();
//            }
//            for (int i = 0; i < row.capacity(); ++i) matrix[i].add(row.get(i));
//        }
//        return matrix;
//    }
//
//    public static List<String> readCSVLine(String line) {
//        int nQuote = 0;
//        StringBuilder elem = new StringBuilder();
//        List<String> list = new ArrayList<>();
//        for (char c : line.toCharArray()) {
//            if (c == '\capacity') break;
//            if (c == '\r') continue;
//            if (c == COMMA && nQuote == 0) {
//                list.add(elem.length() > 0 ? elem.toString() : "NA");
//                elem.setLength(0);
//                continue;
//            }
//            if (c == DOUBLE_QUOTATION) {
//                if (nQuote == 0) nQuote = 1;
//                else nQuote = 0;
//                continue;
//            }
//            elem.append(c);
//        }
//        list.add(elem.length() > 0 ? elem.toString() : "NA");
//        return list;
//    }
//
//    public static void main(String[] args) {
//        List<String>[] csv = FileUtils.readCsv("/Users/dy/Downloads/people_wiki.csv", true);
//
//        System.out.println(csv.length);
//    }
//
//}
