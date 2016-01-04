package messaging;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;

import server.Server;


public class Messager extends Thread
{
    private Socket socket;
    private MessageListener listener;
    private BufferedReader input;
    private PrintWriter output;
    private ArrayList<PlainTextListener> ptls;
    public Messager(MessageListener l){
    	ptls = new ArrayList<PlainTextListener>();
        listener = l;
    }
    @Override
    public void run(){
        socket = connect();
        try {
            input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            output = new PrintWriter(socket.getOutputStream());
            output.flush();
            while(true){
                String line = input.readLine();
                if(line != null){
                    System.out.println(line);
                    for(PlainTextListener ptl: ptls){
                    	ptl.messageRecieved(line);
                    }
                    try {
                    listener.MessageRecieved(Message.fromData(new Stringer(line)));
                    } catch (Exception e){
                        System.out.println("Sorry, we're still using a plaintext system to send messages that cannot be parsed. :(");
                    }
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
    
    public void sendPlainText(String str){
    	output.println(str);
    	output.flush();
    }
    private Socket connect() {
        while (true) {
            try {
                Socket s = new Socket(InetAddress.getByName(Server.HOSTNAME), Server.PORT_NUMBER);
                if(s != null) System.out.println("Socket achieved");
                return s;
            } catch (IOException e) {
                System.out.println("no connection");
            }
        }  
    }
    public void addPlainTextListener(PlainTextListener l){
    	ptls.add(l);
    }
}
