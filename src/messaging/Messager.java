package messaging;

import java.net.Socket;

public class Messager extends Thread
{
    private Socket socket;
    public Messager(){
        socket = new Socket();
    }
    public void send(Message m){
        
    }
}
