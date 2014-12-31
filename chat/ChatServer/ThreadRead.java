/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chatserver;

import com.sun.corba.se.impl.io.IIOPInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import msg.Message;

/**
 *
 * @author baskoro
 */
public class ThreadRead extends Thread {
    private Socket sock;
    private ObjectOutputStream ousClient;
    private ObjectInputStream oisClient;
    private String nama;
    private ThreadListen listen;
    
    public ThreadRead(Socket sock, ThreadListen listen) throws IOException
    {
        this.sock = sock;
        this.listen = listen;
        this.ousClient = new ObjectOutputStream(this.sock.getOutputStream());
        this.oisClient = new ObjectInputStream(this.sock.getInputStream());
    }
    
    public void run()
    {
        Message msg;
        /*try {
            msg = (Message) this.oisClient.readObject();
            this.setNama(msg.getIsi());
        } catch (IOException ex) {
            Logger.getLogger(ThreadRead.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ThreadRead.class.getName()).log(Level.SEVERE, null, ex);
        }*/
                
        while(true)
        {
            try {
                msg = (Message) this.oisClient.readObject();
                this.listen.sendMessage(msg);
            } catch (IOException ex) {
                Logger.getLogger(ThreadRead.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ThreadRead.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void send(Message msg)
    {
        try {
            this.ousClient.writeObject(msg);
            this.ousClient.flush();
            this.ousClient.reset();
        } catch (IOException ex) {
            Logger.getLogger(ThreadRead.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @return the nama
     */
    public String getNama() {
        return nama;
    }

    /**
     * @param nama the nama to set
     */
    public void setNama(String nama) {
        this.nama = nama;
    }
}
