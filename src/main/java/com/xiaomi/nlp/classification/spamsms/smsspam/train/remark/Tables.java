package com.xiaomi.nlp.classification.spamsms.smsspam.train.remark;

import com.xiaomi.nlp.classification.spamsms.smsspam.Corpus;
import com.xiaomi.nlp.classification.spamsms.smsspam.train.PCVals;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

class CheckBoxEditor extends DefaultCellEditor implements ItemListener {
    private static final long serialVersionUID = 1L;
    private JCheckBox button;
    
    public CheckBoxEditor(JCheckBox checkBox) {
        super(checkBox);
    }
  
    public Component getTableCellEditorComponent(JTable table, Object value,
            boolean isSelected, int row, int column) {
        if (value == null)
            return null;
        button = (JCheckBox) value;
        button.addItemListener(this);
        return (Component) value;
    }
  
    public Object getCellEditorValue() {
        button.removeItemListener(this);
        return button;
    }
  
    public void itemStateChanged(ItemEvent e) {
        super.fireEditingStopped();
    }
}

class CheckBoxRenderer implements TableCellRenderer {
    
    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {
        if (value == null)
            return null;
        return (Component) value;
    }
}

public class Tables implements MouseListener {
    JTable table = new JTable();
    private JButton btnAll;
    private JButton btnLoad;
    private JTextArea txtPhrase;
    private JTextArea txtSeq;
    private JFrame mFrame;

    private JTextArea txtModifiedPhrase;
    private JButton btnSave;

    List<Corpus> mMarkCorpus;
    List<Corpus> mPhrases;
    int mCurrentIndex = -1;

    Font font = new Font("宋体",Font.BOLD, 24);

    private String mOrigFile;
    public Tables(String markFile, String origFile) {
        mOrigFile = origFile;
        mMarkCorpus= PCVals.readSMS(markFile);

        mFrame = new JFrame("sjh");
        mFrame.setLayout(null);
  
        txtPhrase = new JTextArea();
        txtPhrase.setSize(1500, 40);
        txtPhrase.setLocation(0, 0);
        txtPhrase.setFont(font);
        mFrame.add(txtPhrase);
        
        txtSeq = new JTextArea();
        txtSeq.setSize(140, 40);
        txtSeq.setLocation(txtPhrase.getWidth() + 10, 0);
        txtSeq.setEnabled(false);
        txtSeq.setBackground(Color.BLACK);
        txtSeq.setFont(font);
        mFrame.add(txtSeq);
        
        table = this.gettable();
        table.addMouseListener(this);
        table.setRowHeight(24);
        table.setFont(new Font("Menu.font", Font.PLAIN, 18));
        JScrollPane src = new JScrollPane(table);
        src.setBounds(0, txtPhrase.getHeight() + 10, 1800, 800);
        mFrame.setSize(new Dimension(1800, 1000));
        mFrame.add(src);

        btnLoad = new JButton("Load");
        btnLoad.setSize(80,30);
        btnLoad.setLocation(txtPhrase.getWidth() + 156, 5);
        btnLoad.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
//                load();
            }
        });

        btnSave = new JButton("Save");
        btnSave.setSize(80,40);
        btnSave.setLocation((src.getWidth()) - 140, src.getHeight() + 100);
        btnSave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                doneAndSave();
            }
        });


        btnAll = new JButton("All");
        btnAll.setSize(80,40);
        btnAll.setLocation((src.getWidth()) - 340, src.getHeight() + 100);
        btnAll.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                selectAll();
            }
        });


        txtModifiedPhrase = new JTextArea();
        txtModifiedPhrase.setSize(160, 40);
        txtModifiedPhrase.setLocation(100,  src.getHeight() + 100);
        txtModifiedPhrase.setFont(font);
        mFrame.add(txtModifiedPhrase);


        mFrame.add(btnLoad);
        mFrame.add(btnSave);
        mFrame.add(btnAll);

        load();
        mFrame.setVisible(true);
    }

    private boolean mLastAll = false;

    private void selectAll(){
        if(mLastWord != null){
            for(int i = 0; i < mCurrentSmses.size(); ++i){
                JCheckBox cb = (JCheckBox)(mData[i][2]);
                cb.setSelected(mLastAll);
                Corpus cps = mCurrentSmses.get(i);
                cps.setMarked(true);

                boolean markSpam = cb.isSelected();
                if(markSpam != cps.isSpam()){
                    cps.setCatChanged(true);
                }
                cps.setSpam(markSpam);

            }
            mLastAll = !mLastAll;
        }

        updateTable();
    }

    private void doneAndSave(){
        try{

            for(int i = 0; i < mCurrentSmses.size(); ++i){
                JCheckBox cb = (JCheckBox)(mData[i][2]);
                Corpus cps = mCurrentSmses.get(i);
                cps.setMarked(true);
                boolean spam = cps.isSpam();
                boolean markSpam = cb.isSelected();
                if(spam != markSpam){
                    cps.setSpam(markSpam);
                    cps.setCatChanged(true);
                    System.out.println("mark one to:" + Boolean.toString(markSpam));
                }
            }

            List<Corpus> origCorpus = PCVals.readSMS(mOrigFile);
            final String OUT_FILE = mOrigFile + ".remarked";
            File f = new File(OUT_FILE);
            if(f.exists()){
                f.delete();
            }

            int remarkCount = 0;
            for(int i = 0; i < mMarkCorpus.size(); ++i){
                Corpus cps = mMarkCorpus.get(i);
                if(cps.isSpam() != origCorpus.get(cps.getId() - 1).isSpam()){
                    origCorpus.get(cps.getId() - 1).setSpam(cps.isSpam());
                    remarkCount++;
                }
            }

            PCVals.saveSmses(OUT_FILE, origCorpus);

            System.out.println("Total " + remarkCount + " smses are marked differently");
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    private List<Corpus> mCurrentSmses = new ArrayList<Corpus>();
    Object[][] mData;
    private String mLastWord;

    private void load(){
        for(Corpus s : mMarkCorpus){
            mCurrentSmses.add(s);
        }

        updateTable();
        txtSeq.setText("Cnt:" + mCurrentSmses.size());
    }

    private void updateTable(){
        mData = new Object[mCurrentSmses.size()][3];
        for(int i = 0; i < mCurrentSmses.size(); ++i){
            mData[i] = new Object[3];
            mData[i][0] = mCurrentSmses.get(i).getOrigBody();
            mData[i][1] = mCurrentSmses.get(i).getAddress();
            JCheckBox cb = new JCheckBox();
            cb.setSelected(mCurrentSmses.get(i).isSpam());
            mData[i][2] = cb;
        }
        


        mDm.setDataVector(mData, COLOMN);
        table.getColumn(SPAM).setCellEditor( new CheckBoxEditor(new JCheckBox()));
        table.getColumn(SPAM).setCellRenderer(new CheckBoxRenderer());

        table.getColumn(ADDRESS).setPreferredWidth(200);
        table.getColumn(ADDRESS).setMaxWidth(260);
        table.getColumn(ADDRESS).setMinWidth(100);
        
        table.getColumn(SPAM).setPreferredWidth(160);
        table.getColumn(SPAM).setMaxWidth(160);
        table.getColumn(SPAM).setMinWidth(160);
        mDm.fireTableDataChanged();
    }

    private static final String CONTENT = "内容";
    private static final String ADDRESS = "地址";
    private static final String SPAM = "类别";
    private static Object[] COLOMN = {CONTENT, ADDRESS, SPAM};

    DefaultTableModel mDm = new DefaultTableModel();
    public JTable gettable() {
        mDm = new DefaultTableModel();
          JTable table = new JTable(mDm) {
            private static final long serialVersionUID = 1L;

            public void tableChanged(TableModelEvent e) {
                super.tableChanged(e);
                repaint();
            }
        };
        return table;
    }

    public void mouseClicked(MouseEvent arg0) {
        System.out.println("mouseClicked:");
    }
  
    public void mouseEntered(MouseEvent arg0) {
    }
  
    public void mouseExited(MouseEvent arg0) {
    }
  
    public void mousePressed(MouseEvent arg0) {
    }
  
    public void mouseReleased(MouseEvent arg0) {
    }
}
