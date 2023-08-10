package com.example.mojprojekat;

import com.example.mojprojekat.database.Database;
import com.example.mojprojekat.database.Korisnik;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class AdminGUI extends JFrame {
    private DefaultTableModel tableModel;
    JPanel velikamatrica;
    List<Korisnik> korisnici;
    int prijavljeniAdmin;

    public AdminGUI(List<Korisnik> korisnici, int prijavljeniAdmin) {
        this.korisnici = korisnici;
        this.prijavljeniAdmin = prijavljeniAdmin;
        setTitle("Admin Panel");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        velikamatrica = new JPanel();
        GridLayout layout = new GridLayout(korisnici.size(), 4);
        velikamatrica.setLayout(layout);
        for (Korisnik korisnik : korisnici) {
            JLabel label = new JLabel(korisnik.getUsername());
            JLabel label2 = new JLabel(" - broj pobeda : ");
            Database db=Database.getConnection();
            int pobede = db.getPobede(korisnik.getId());
            JLabel label3 = new JLabel(String.valueOf(pobede));
            DugmeZaBrisanje dugme = new DugmeZaBrisanje(korisnik.getId());
            dugme.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Database db = Database.getConnection();
                    db.deleteUser(korisnik.getId());
                    velikamatrica.remove(dugme);
                    refreshData();
                }
            });
            velikamatrica.add(label);
            velikamatrica.add(label2);
            velikamatrica.add(label3);
            velikamatrica.add(dugme);

        }
        JButton logoutButton = new JButton("Logout");
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                LoginGUI loginGUI = new LoginGUI();
                loginGUI.setVisible(true);
            }
        });


        JScrollPane tableScrollPane = new JScrollPane(velikamatrica);

        add(tableScrollPane, BorderLayout.CENTER);
        add(logoutButton, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void refreshData()
    {
        velikamatrica.removeAll();
        velikamatrica.revalidate();
        velikamatrica.repaint();
        Database db = Database.getConnection();
        korisnici = db.korisnici(prijavljeniAdmin);
        for (Korisnik korisnik : korisnici) {
            JLabel label = new JLabel(korisnik.getUsername());
            DugmeZaBrisanje dugme = new DugmeZaBrisanje(korisnik.getId());
            dugme.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Database db = Database.getConnection();
                    db.deleteUser(korisnik.getId());
                    refreshData();
                }
            });
            velikamatrica.add(label);
            velikamatrica.add(dugme);
            velikamatrica.revalidate();
            velikamatrica.repaint();
        }
    }


}
