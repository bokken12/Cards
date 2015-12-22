package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Stack;

import javax.swing.JFrame;

import messaging.Message;
import messaging.Messager;
import messaging.Stringer;
import clientStuff.State;
import clientStuff.StateMachine;
import server.Server;

public class ClientListener extends Thread
{
    private Socket socket;
    private Stack<ListenerState> states;
    private BufferedReader input;
    private PrintWriter output;
    public ClientListener(ListenerState ls, Socket s){
        states = new Stack<ListenerState>();
        setState(ls);
        socket = s;
        try {
            input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            output = new PrintWriter(socket.getOutputStream());
            while(true){
                getCurrentState().MessageRecieved(Message.fromData(new Stringer(input.readLine())));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void send(Message m){
        output.println(m.toString());
    }
    public ListenerState getCurrentState(){
        return states.peek();
    }
    public void back(){
        getCurrentState().onDestroy(this);
        states.pop();
        if(!(states.isEmpty())){
            getCurrentState().onBegin(this);
        }
    }
    public void setState(ListenerState ls){
        if(!(states.isEmpty())){
            getCurrentState().onLeave(this);
        }
        states.push(ls);
        getCurrentState().onInitialize(this);
        getCurrentState().onBegin(this);
    }
}
