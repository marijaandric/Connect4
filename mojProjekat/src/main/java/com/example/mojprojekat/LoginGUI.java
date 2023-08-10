package com.example.mojprojekat;

import com.example.mojprojekat.database.Database;
import com.example.mojprojekat.database.Korisnik;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.io.File;


public class LoginGUI extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    public volatile Korisnik korisnik1 = null;

    public LoginGUI() {
        setTitle("Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel inputPanel = new JPanel(new GridLayout(2, 2));

        JLabel usernameLabel = new JLabel("Username:");
        JLabel passwordLabel = new JLabel("Password:");

        usernameField = new JTextField();
        passwordField = new JPasswordField();

        usernameField.setPreferredSize(new Dimension(200, 30));
        passwordField.setPreferredSize(new Dimension(200, 30));

        inputPanel.add(usernameLabel);
        inputPanel.add(usernameField);
        inputPanel.add(passwordLabel);
        inputPanel.add(passwordField);

        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                Database conn = Database.getConnection();
                Korisnik k = conn.login(username,password);
                System.out.println(k);
                if(k != null)
                {
                    korisnik1 = k;
                    dispose();
                }
                else{
                    JOptionPane.showMessageDialog(LoginGUI.this, "Login failed. Invalid username or password.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        JButton registerButton = new JButton("Register");
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Desktop.getDesktop().browse(new URI("http://localhost:8081/mojProjekat_war_exploded/"));
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        JButton helpButton = new JButton("Help?");
        helpButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String message = "Connect4 je aplikacija za igru koja se igra na tabli veličine 6x7.\n"
                        + "Cilj igre je da se postavi 4 žetona iste boje u nizu, horizontalno, vertikalno ili dijagonalno.\n"
                        + "Da biste započeli igru, morate biti registrovani korisnik.\n"
                        + "Kliknite na 'Register' dugme da biste se registrovali.";
                JOptionPane.showMessageDialog(null, message, "Connect4 - Help", JOptionPane.INFORMATION_MESSAGE);

            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.setPreferredSize(new Dimension(200, 50));
        buttonPanel.add(loginButton);
        buttonPanel.add(registerButton);
        buttonPanel.add(helpButton);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(inputPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        setContentPane(mainPanel);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }


}
