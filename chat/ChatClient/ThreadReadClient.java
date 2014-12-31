/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatclient;

import java.awt.TextArea;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextArea;
import msg.Message;

/**
 *
 * @author baskoro
 */
public class ThreadReadClient extends Thread {
    private Socket sock;
    private ObjectInputStream ois;
    private JTextArea txtReceived;
    
    public ThreadReadClient(Socket sock, ObjectInputStream ois, JTextArea txtReceived)
    {
        this.sock = sock;
        this.ois = ois;
        this.txtReceived = txtReceived;
    }
    
    @Override
    public void run()
    {
        while(true)
        {
            try {
                Message msg = (Message) ois.readObject();
                this.txtReceived.append(msg.getDari() + ":" + msg.getIsi() + "\n");
            } catch (IOException ex) {
                Logger.getLogger(ThreadReadClient.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ThreadReadClient.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }
}
