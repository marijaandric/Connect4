package com.example.mojprojekat;

import com.example.mojprojekat.models.Poruka;
import com.example.mojprojekat.models.Potez;

import java.beans.XMLDecoder;
import java.io.*;
import java.net.Socket;
import java.util.HashMap;

public class ServerThread extends Thread{
    HashMap<String, BufferedWriter> clients;
    Socket client1;
    Socket client2;

    public ServerThread(HashMap<String, BufferedWriter> clients,Socket client1, Socket client2)
    {
        this.clients = clients;
        this.client1 = client1;
        this.client2 = client2;
    }

    public void run()
    {
        try{
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(client1.getOutputStream()));
            BufferedReader br = new BufferedReader(new InputStreamReader(client1.getInputStream()));
            BufferedWriter bw2 = new BufferedWriter(new OutputStreamWriter(client2.getOutputStream()));
            BufferedReader br2 = new BufferedReader(new InputStreamReader(client2.getInputStream()));

            Thread chatClient1 = new Thread(new Runnable() {
                @Override
                public void run() {
                    try{
                        while(true)
                        {
                            String line = br.readLine();
                            XMLDecoder xmlDecoder = new XMLDecoder(new ByteArrayInputStream(line.getBytes()));
                            Object o = xmlDecoder.readObject();

                            if(o instanceof Poruka)
                            {
                                Poruka p = (Poruka) o;
                                bw2.write(p.toString()+"\n");
                                bw2.flush();
                            }
                            else if(o instanceof Potez)
                            {
                                Potez p = (Potez)o;
                                bw2.write(p.toString()+"\n");
                                bw2.flush();
                            }
                        }
                    }
                    catch (Exception e){}
                }
            });
            chatClient1.start();

            Thread chatClient2 = new Thread(new Runnable() {
                @Override
                public void run() {
                    try{
                        while(true)
                        {
                            String line = br2.readLine();
                            XMLDecoder xmlDecoder = new XMLDecoder(new ByteArrayInputStream(line.getBytes()));
                            Object o = xmlDecoder.readObject();

                            if(o instanceof Poruka)
                            {
                                Poruka p = (Poruka) o;
                                bw.write(p.toString()+"\n");
                                bw.flush();
                            }
                            else if(o instanceof Potez)
                            {
                                Potez p = (Potez)o;
                                bw.write(p.toString()+"\n");
                                bw.flush();
                            }
                        }
                    }
                    catch (Exception e){}
                }
            });
            chatClient2.start();


        }
        catch(Exception e){}
    }
}
