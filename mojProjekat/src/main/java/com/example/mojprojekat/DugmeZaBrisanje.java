package com.example.mojprojekat;

import com.example.mojprojekat.database.Database;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DugmeZaBrisanje extends JButton {
    public int id;

    public DugmeZaBrisanje(int id)
    {
        super("Delete user");
        this.id=id;
    }
}