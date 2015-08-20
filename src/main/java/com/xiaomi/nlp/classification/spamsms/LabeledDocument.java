package com.xiaomi.nlp.classification.spamsms;

import java.io.Serializable;

/**
 * Created by DY on 15/8/20.
 */

public class LabeledDocument extends Document implements Serializable {
    private double label;

    public LabeledDocument(long id, String text, double label) {
        super(id, text);
        this.label = label;
    }

    public double getLabel() { return this.label; }
    public void setLabel(double label) { this.label = label; }
}

