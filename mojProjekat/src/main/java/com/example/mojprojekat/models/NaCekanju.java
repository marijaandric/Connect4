package com.example.mojprojekat.models;

import java.beans.XMLEncoder;
import java.io.ByteArrayOutputStream;
import java.io.Serializable;

public class NaCekanju implements Serializable {
    private String text;

    public NaCekanju(){}
    public NaCekanju(String text){
        this.text=text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        XMLEncoder encoder = new XMLEncoder(baos);
        encoder.writeObject(this);
        encoder.close();

        String s = new String(baos.toByteArray());
        s = s.replace("\n", " ");
        return s;
    }

}
