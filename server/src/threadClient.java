/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Muhammad Izzuddin
 */
public class threadClient implements Runnable{
    private Socket sockcli;
    private final ArrayList<threadClient> alThread;
    private BufferedOutputStream bos = null;
    private DataInputStream dis = null;
    private DataOutputStream dos = null;
    private BufferedReader br = null;
    private BufferedWriter bw = null;
    private ObjectOutputStream oos = null;
    private ObjectInputStream ois = null;
    private final SocketAddress sa;
    private String username;
    public threadClient (Socket sockcli, ArrayList<threadClient> t){
        this.sockcli = sockcli;
        this.alThread = t;
        this.sa = this.sockcli.getRemoteSocketAddress();
    }
    @Override
    public void run(){
        try {
            dos = new DataOutputStream(this.getSockcli().getOutputStream());
            bos = new BufferedOutputStream(this.getSockcli().getOutputStream());
            oos = new ObjectOutputStream(this.getSockcli().getOutputStream());
            ois = new ObjectInputStream(this.getSockcli().getInputStream());
            dis = new DataInputStream(this.getSockcli().getInputStream());
            br = new BufferedReader(new InputStreamReader(dis));
            bw = new BufferedWriter(new OutputStreamWriter(oos));
            
            String msg;
            while ((msg = br.readLine())!= null){
                
            }
            bos.close();
            getSockcli().close();
            synchronized(this.alThread){
                this.alThread.remove(this);
            }
        } catch (IOException ex) {
            Logger.getLogger(threadClient.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    public void send(String msg) throws IOException
    {
        this.bos.write(msg.getBytes());
        this.bos.flush();
    }
    public synchronized void sendMessage(String msg) throws IOException
    {
        for (int i=0;i <this.alThread.size();i++){
            threadClient tc = this.alThread.get(i);
            tc.send(msg);
        }
    }

    /**
     * @return the sockcli
     */
    public Socket getSockcli() {
        return sockcli;
    }

    /**
     * @param sockcli the sockcli to set
     */
    public void setSockcli(Socket sockcli) {
        this.sockcli = sockcli;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }
}

