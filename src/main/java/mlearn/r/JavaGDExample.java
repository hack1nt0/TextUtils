package mlearn.r;

import org.rosuda.JRI.Rengine;
import org.rosuda.javaGD.GDInterface;
import org.rosuda.javaGD.JGDBufferedPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author dy[jealousing@gmail.com] on 17-5-23.
 */

public class JavaGDExample extends JFrame implements ActionListener {

    private Rengine engine;
    private JButton btn;

    public JavaGDExample() {
        super();
        super.setTitle("My R Plot");

        btn = new JButton("show plot");
        btn.addActionListener(this);
        this.getContentPane().setLayout(new BorderLayout());
        this.getContentPane().add(btn, BorderLayout.PAGE_START);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == btn) {
            // initialize R
            engine = new Rengine(new String[] { "--vanilla" }, false, null);
            engine.eval("Sys.setenv('JAVAGD_CLASS_NAME'='template/ml/r/RjavaGDInterface')");
            engine.eval("library(JavaGD)");

            engine.eval("JavaGD()");
            engine.eval("a <- c(1,2,3,2,4)");
            engine.eval("plot(a,type=\"l\")");

            engine.end();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(JavaGDExample::new);
    }
}


class RjavaGDInterface extends GDInterface {

    JFrame f;

    @Override
    public void gdOpen(double w, double h) {
        super.gdOpen(w,h);
        f = new JFrame();
        f.setLayout(new BorderLayout());
        c = new JGDBufferedPanel(w, h);

        f.setTitle("R Plot");
        f.getContentPane().add((Component) c, BorderLayout.CENTER);
        f.getContentPane().add(buttonPanel(), BorderLayout.NORTH);

        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.pack();
        f.setVisible(true);

    }
    private JPanel buttonPanel(){
        JPanel p = new JPanel();
        p.setBackground(Color.pink);

        p.add(new JLabel("Options"));
        p.add(new JButton("option1"));
        return p;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(RjavaGDInterface::new);
    }
}



