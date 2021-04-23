package Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Server {

    public static void main(String[] args) throws IOException {
        int portNumber=4444;
        ServerSocket serverSocket=new ServerSocket(portNumber);

        while(true){
            Socket socket=null;
            try{
                socket= serverSocket.accept();
                System.out.println("A new Client is connected :"+socket);
                DataInputStream in=new DataInputStream(socket.getInputStream());
                DataOutputStream out=new DataOutputStream(socket.getOutputStream());
                System.out.println("Assigning new Thread to client");
                Thread t=new ClientHandler(socket,in,out);
                t.start();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}

