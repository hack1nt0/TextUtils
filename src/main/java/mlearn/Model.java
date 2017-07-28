package mlearn;

import template.debug.PrintWriterUTF8;
import template.debug.ScannerUTF8;

/**
 * @author dy[jealousing@gmail.com] on 17-7-28.
 */
public abstract class Model {
    public abstract Object fit(Object...params);
    public abstract Object apply(Object...params);
    public abstract Model read(ScannerUTF8 in);
    public abstract void write(PrintWriterUTF8 in);
}
