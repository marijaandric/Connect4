package com.example.mojprojekat;

import com.example.mojprojekat.models.NaCekanju;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

public class Server {
    public static int PORT = 6789;
    public static void main(String args[])
    {
        try {
            ServerSocket serverSocket = new ServerSocket(Server.PORT);

            HashMap<String, BufferedWriter> clients = new HashMap<String, BufferedWriter>();

            while(true)
            {
                Socket client = serverSocket.accept();
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
                NaCekanju naCekanju = new NaCekanju("Cekanje");
                bw.write(naCekanju.toString()+"\n");
                bw.flush();
                Socket client2 = serverSocket.accept();
                BufferedWriter bw2 = new BufferedWriter(new OutputStreamWriter(client2.getOutputStream()));
                naCekanju = new NaCekanju("Pocetak");
                bw2.write(naCekanju.toString()+"\n");
                bw2.flush();
                ServerThread nit = new ServerThread(clients,client ,client2);
                nit.start();

            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
