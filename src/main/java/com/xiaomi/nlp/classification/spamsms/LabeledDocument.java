package com.xiaomi.nlp.classification.spamsms;

import java.io.Serializable;

/**
 * Created by DY on 15/8/20.
 */

public class LabeledDocument extends Document implements Serializable {
    private double label;
    private int nb_pred;
    private int gbt_pred;
    private double comm_char_ratio;

    public LabeledDocument(long id, String text, double label) {
        super(id, text);
        this.label = label;
    }

    public double getLabel() { return this.label; }
    public void setLabel(double label) {this.label = label; }

    public int getNb_pred() {
        return nb_pred;
    }

    public void setNb_pred(int nb_pred) {
        this.nb_pred = nb_pred;
    }

    public int getGbt_pred() {
        return gbt_pred;
    }

    public void setGbt_pred(int gbt_pred) {
        this.gbt_pred = gbt_pred;
    }

    public double getComm_char_ratio() {
        return comm_char_ratio;
    }

    public void setComm_char_ratio(double comm_char_ratio) {
        this.comm_char_ratio = comm_char_ratio;
    }
}

