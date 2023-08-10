package com.example.mojprojekat;
import com.example.mojprojekat.database.Database;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

public class GUI extends JFrame {
    JFrame frame;
    JPanel mainPanel;
    JButton[][] buttonsMatrix;
    JPanel playerPanel;
    JTextArea chatTextArea;
    JLabel currentPlayerLabel;
    JTextField messageTextField;
    JButton sendButton;
    JLabel currentPlayerText;
    JButton dugmici[][] = new JButton[6][7];
    Connect4Game connect4Game = new Connect4Game();
    public int poslednjeX;
    public int poslednjeY;
    public String poslednjaPoruka;
    public static volatile boolean promenaPoruke=false;
    public static volatile boolean promena = false;
    public volatile int boja;
    public int red = -1;
    int idKorisnika;

    public GUI(boolean dvaIgraca, int boja,int id)
    {
        this.idKorisnika = id;
        this.boja = boja;
        prijavljenaDvaClienta();
    }

    public void prijavljenaDvaClienta()
    {
        setBounds(520,300,650,700);
        setTitle("Connect 4");

        frame = new JFrame("Connect 4");

        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        frame.add(mainPanel);


        getContentPane().setLayout(new BorderLayout());
        JPanel velikamatrica = new JPanel(new GridLayout(6,7));
        playerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        playerPanel.setBackground(Color.WHITE);
        currentPlayerLabel = new JLabel();
        currentPlayerLabel.setIcon(new ImageIcon("images//red_small.png"));
        currentPlayerText = new JLabel("Na redu je : Crveni ");
        Font font = currentPlayerText.getFont();
        Font velikiFont = font.deriveFont(font.getSize() + 5f);

        currentPlayerText.setFont(velikiFont);
        playerPanel.add(currentPlayerText, BorderLayout.CENTER);

        for(int i=0;i<6;i++)
        {
            for(int j=0;j<7;j++)
            {
                JButton dugme = new Dugme(i,j);
                dugmici[i][j] = dugme;
                dugme.setSize(120,150);


                dugme.addMouseListener(new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        if (dugme.isEnabled()) {
                        Dugme dugme = (Dugme) e.getSource();
                        if(connect4Game.makeMove(dugme.y)) {
                            poslednjeX = dugme.x;
                            poslednjeY = dugme.y;
                            promena = true;
                            ozveziGui();
                        }

                        if (connect4Game.checkWin()) {
                            int odgovor = new JOptionPane().showConfirmDialog(null, "Igra je završena! Pobedili ste!", "Kraj", JOptionPane.PLAIN_MESSAGE);
                            if (odgovor == JOptionPane.OK_OPTION) {
                                Database db = Database.getConnection();
                                db.updatePobede(idKorisnika);
                                System.exit(0);
                            }

                        }
                        }
                    }

                    @Override
                    public void mousePressed(MouseEvent e) {

                    }

                    @Override
                    public void mouseReleased(MouseEvent e) {

                    }

                    @Override
                    public void mouseEntered(MouseEvent e) {

                    }

                    @Override
                    public void mouseExited(MouseEvent e) {

                    }


                });
                dugmici[i][j] = dugme;
                velikamatrica.add(dugmici[i][j]);
            }

        }


        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        mainPanel.add(splitPane, BorderLayout.CENTER);

        JPanel chatPanel = new JPanel();
        chatPanel.setLayout(new BorderLayout());

        chatTextArea = new JTextArea();
        chatTextArea.setEditable(false);
        chatPanel.add(new JScrollPane(chatTextArea), BorderLayout.CENTER);

        JPanel inputPanel = new JPanel(new BorderLayout());
        chatPanel.add(inputPanel, BorderLayout.SOUTH);

        messageTextField = new JTextField();

        sendButton = new JButton("Send");

        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String message = messageTextField.getText();
                poslednjaPoruka=message;
                chatTextArea.append("You: " + message + "\n");
                messageTextField.setText("");
                promenaPoruke = true;
            }
        });
        JButton helpButton = new JButton("Help?");
        helpButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String message = "Connect4 je aplikacija za igru koja se igra na tabli veličine 6x7.\n"
                        + "Cilj igre je da se postavi 4 žetona iste boje u nizu, horizontalno, vertikalno ili dijagonalno.\n"
                        ;
                JOptionPane.showMessageDialog(null, message, "Connect4 - Help", JOptionPane.INFORMATION_MESSAGE);

            }
        });

        inputPanel.add(messageTextField, BorderLayout.CENTER);
        inputPanel.add(sendButton, BorderLayout.EAST);
        inputPanel.add(helpButton,BorderLayout.SOUTH);

        playerPanel.add(currentPlayerLabel);
        mainPanel.add(playerPanel, BorderLayout.NORTH);

        splitPane.setDividerLocation(650);
        splitPane.setLeftComponent(velikamatrica);
        splitPane.setRightComponent(chatPanel);

        frame.setSize(1000, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);


        ozveziGui();
    }

    public void protivnickaporuka(String text, String from)
    {
        chatTextArea.append(from+": " + text + "\n");
        messageTextField.setText("");
    }



    public void ozveziGui()
    {
        for(int i=0;i<6;i++)
        {
            for(int j=0;j<7;j++)
            {
                if(connect4Game.polje[i][j].igrac == 0)
                {
                    dugmici[i][j].setIcon(new ImageIcon("images//back.png"));
                    dugmici[i][j].setDisabledIcon(new ImageIcon("images//backDisable.png"));
                }
                else if(connect4Game.polje[i][j].igrac == 1)
                {
                    dugmici[i][j].setIcon(new ImageIcon("images//red.png"));
                    dugmici[i][j].setDisabledIcon(new ImageIcon("images//redDisable.png"));

                }
                else
                {
                    dugmici[i][j].setIcon(new ImageIcon("images//yellow.png"));
                    dugmici[i][j].setDisabledIcon(new ImageIcon("images//yellowDisable.png"));

                }
            }
        }
    }

    public void protivnickiPotez(int x,int y, int boja)
    {
        connect4Game.makeMove(y);
        ozveziGui();
        if(connect4Game.checkWin())
        {
            int odgovor = new JOptionPane().showConfirmDialog(null, "Igra je završena! Izgubili ste!", "Kraj", JOptionPane.PLAIN_MESSAGE);
            if (odgovor == JOptionPane.OK_OPTION) {
                System.exit(0);
            }
        }

    }

    public void setButtonsEnabled(boolean enabled) {
        for (JButton[] row : dugmici) {
            for (JButton button : row) {
                button.setEnabled(enabled);
            }
        }
        if(red*boja == 1)
        {
            currentPlayerLabel.setIcon(new ImageIcon("images//red_small.png"));
            currentPlayerText.setText("Na redu je : Crveni ");
            Font font = currentPlayerText.getFont();

        }
        else{
            currentPlayerLabel.setIcon(new ImageIcon("images//yellow_small.png"));
            currentPlayerText.setText("Na redu je : Žuti ");

        }

        red = red * (-1);

    }


}
