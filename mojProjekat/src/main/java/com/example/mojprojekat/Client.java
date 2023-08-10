package com.example.mojprojekat;

import com.example.mojprojekat.database.Database;
import com.example.mojprojekat.database.Korisnik;
import com.example.mojprojekat.models.NaCekanju;
import com.example.mojprojekat.models.Poruka;
import com.example.mojprojekat.models.Potez;

import java.beans.XMLDecoder;
import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.List;

public class Client {
    public static void main(String args[]) {
        LoginGUI loginGUI = new LoginGUI();
        Korisnik korisnik;

        while(loginGUI.korisnik1 == null)
        {

        }
        korisnik = loginGUI.korisnik1;
        if(korisnik.getRole().equals("player")) {
            try {
                Socket socket = new Socket(InetAddress.getByName("localhost"), 6789);

                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                GUI gui = new GUI(false, -1, korisnik.getId());


                Thread fromServer = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {

                            int boja = -1;
                            String line = null;
                            line = br.readLine();// blokirajuci - xml string
                            XMLDecoder xmlDecoder = new XMLDecoder(new ByteArrayInputStream(line.getBytes()));
                            Object o = xmlDecoder.readObject();

                            if (o instanceof NaCekanju) {
                                NaCekanju n = (NaCekanju) o;
                                String x = n.getText();
                                if (x.equals("Cekanje")) {
                                    gui.boja = 1;
                                } else {
                                    gui.setButtonsEnabled(false);
                                }
                            }

                            while (true) {

                                line = br.readLine();// blokirajuci - xml string
                                xmlDecoder = new XMLDecoder(new ByteArrayInputStream(line.getBytes()));
                                o = xmlDecoder.readObject();

                                if (o instanceof Potez) {
                                    Potez p = (Potez) o;
                                    gui.protivnickiPotez(p.getX(), p.getY(), boja);
                                    gui.setButtonsEnabled(true);
                                } else if (o instanceof Poruka) {
                                    Poruka p = (Poruka) o;
                                    gui.protivnickaporuka(p.getText(), p.getFrom());
                                }
                            }

                        } catch (IOException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }

                    }
                });
                fromServer.start();
                while (true) {
                    if (gui.promena) {
                        Potez p = new Potez(gui.poslednjeX, gui.poslednjeY);
                        bw.write(p.toString() + "\n");
                        bw.flush();
                        gui.promena = false;
                        gui.setButtonsEnabled(false);
                        gui.ozveziGui();

                    }
                    if (gui.promenaPoruke) {
                        Poruka p = new Poruka(gui.poslednjaPoruka, korisnik.getUsername());
                        bw.write(p.toString() + "\n");
                        bw.flush();
                        gui.promenaPoruke = false;
                    }
                }


            } catch (Exception e) {

            }
        }
        else{
            Database db = Database.getConnection();
            List<Korisnik> korisnikList =db.korisnici(korisnik.getId());
            AdminGUI adminGUI = new AdminGUI(korisnikList,korisnik.getId());
        }
    }
}
