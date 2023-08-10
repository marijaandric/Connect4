package com.example.mojprojekat.models;

import java.beans.XMLEncoder;
import java.io.ByteArrayOutputStream;
import java.io.Serializable;

public class Potez implements Serializable {
    private int x;
    private int y;

    public Potez(){}
    public Potez(int x,int y){
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
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
