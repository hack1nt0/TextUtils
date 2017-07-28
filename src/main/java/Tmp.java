import template.debug.ScannerUTF8;

import java.io.*;

/**
 * @author dy[jealousing@gmail.com] on 17-7-18.
 */
public class Tmp {
    public static void main(String[] args) throws Exception {
//        Scanner in = new Scanner(new BufferedInputStream(new FileInputStream("data/train/spamsms.txt")));
//        PrintWriter out = new PrintWriter(new BufferedOutputStream(new FileOutputStream("data/train/spamsms.json")));
//        out.println('[');
//        while (in.hasNext()) {
//            out.printf("%s, \size", in.nextLine());
//        }
//        out.println(']');
//        in.close();
//        out.close();

        int v = 0x2F81A;
        String str = new String(new int[]{v}, 0, 1);
        ScannerUTF8 in = new ScannerUTF8(new StringBufferInputStream(str));
        while (!in.isExhausted()) {
            System.out.println(in.nextString());
        }
//        in.useDelimiter("");
//        while (in.hasNext()) {
//            System.out.println(in.next());
//        }
        in.close();
        System.out.println(Integer.toHexString("啊".charAt(0)));
    }
}
