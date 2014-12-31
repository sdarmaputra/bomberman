/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chatserver;

import com.sun.xml.internal.messaging.saaj.soap.ver1_1.Message1_1Impl;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.SSLServerSocket;
import msg.Message;

/**
 *
 * @author baskoro
 */
public class ThreadListen extends Thread {
    private ServerSocket serverSock;
    private ArrayList<ThreadRead> alClient;
    private boolean done;
    
    public ThreadListen() throws IOException
    {
        this.serverSock = new ServerSocket(6060);
        this.alClient = new ArrayList<>();
        this.done = false;
    }
    
    @Override
    public void run()
    {
        while(!this.done)
        {
            try {
                Socket sock = this.serverSock.accept();
                //jalankan thread
                ThreadRead client = new ThreadRead(sock, this);
                client.start();
                //simpan thread & socket
                this.alClient.add(client);
            } catch (IOException ex) {
                Logger.getLogger(ThreadListen.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void sendMessage(Message msg)
    {
        System.out.println(msg.getDari() + ":" + msg.getIsi());
        for(int i=0; i<this.alClient.size(); i++)
        {
            //if(this.alClient.get(i).getNama().equals(msg.getUntuk()))
            {
                this.alClient.get(i).send(msg);
             //   return;
            }
        }
    }
}
