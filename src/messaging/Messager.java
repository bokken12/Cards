package messaging;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

import server.ServerListener;

public class Messager extends Thread
{
    private Socket socket;
    private MessageListener listener;
    private BufferedReader input;
    private PrintWriter output;
    public Messager(MessageListener l){
        listener = l;
    }
    @Override
    public void run(){
        socket = connect();
        try {
            input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            output = new PrintWriter(socket.getOutputStream());
            while(true){
                String line = input.readLine();
                if(line != null){
                    listener.MessageRecieved(Message.fromData(new Stringer(line)));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void send(Message m){
        output.println(m.toString());
        output.flush();
    }
    private Socket connect() {
        while (true) {
            try {
                Socket s = new Socket(InetAddress.getByName(ServerListener.HOSTNAME), ServerListener.PORT_NUMBER);
                if(s != null) System.out.println("Socket achieved");
                return s;
            } catch (IOException e) {
                System.out.println("no connection");
            }
        }  
    }
}
