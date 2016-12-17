package server;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;

import acm.program.ConsoleProgram;
//TODO Causes lag and intense cpu usage when players start a game
public class ServerListener extends ConsoleProgram
{

    public static final int PORT_NUMBER = 5002;
    public static final String HOSTNAME = "127.0.0.1";
    private static ServerListener server;
    private ServerSocket listener;
    public static void main(String[] args)
    {
        (server = new ServerListener()).start();
    }
    public void run(){
        try {
            for(int i = 0; i < MenuState.NUM_RANKS; i++){
                MenuState.listeners.add(null);
            }
            listener = new ServerSocket(PORT_NUMBER, 100, InetAddress.getByName(HOSTNAME));
            println("Waiting for a connection.");

            while (true) {
                (new ClientListener(new LoginState(), listener.accept())).start();
                printLine("Got a connection!");
            }
        } catch (IOException e) {
            //  Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                listener.close();
            } catch (IOException e) {
                //  Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
    public static void printLine(String str){
        server.println(str);
    }

}
