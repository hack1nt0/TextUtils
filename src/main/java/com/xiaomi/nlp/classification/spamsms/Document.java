package com.xiaomi.nlp.classification.spamsms;

import java.io.Serializable;

/**
 * Created by DY on 15/8/20.
 */
public class Document implements Serializable {
    private long id;
    private String text;

    public Document(long id, String text) {
        this.id = id;
        this.text = text;
    }

    public long getId() { return this.id; }
    public void setId(long id) { this.id = id; }

    public String getText() { return this.text; }
    public void setText(String text) { this.text = text; }
}
