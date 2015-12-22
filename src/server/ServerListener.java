package server;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;

import acm.program.ConsoleProgram;
import server.Server.Handler;

public class ServerListener extends ConsoleProgram
{

    private static final int PORT_NUMBER = 0;
    private static final String HOSTNAME = null;
    private static ServerListener server;
    private ServerSocket listener;
    public static void main(String[] args)
    {
    }
    public ServerListener(){
        try {
            listener = new ServerSocket(PORT_NUMBER, 100, InetAddress.getByName(HOSTNAME));
            println("Waiting for a connection.");

            while (true) {
                new ClientListener(null, listener.accept()).start();
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
