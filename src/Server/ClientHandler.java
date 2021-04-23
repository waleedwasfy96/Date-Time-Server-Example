package Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

class ClientHandler extends Thread {

    DateFormat fordate=new SimpleDateFormat("yy/MM/dd");
    DateFormat fortime=new SimpleDateFormat("hh:mm:ss");
    final DataOutputStream out;
    final DataInputStream in;
    final Socket socket;

    public ClientHandler(Socket socket, DataInputStream in, DataOutputStream out) {
        this.socket=socket;
        this.out=out;
        this.in=in;
    }

    public void run(){
        String received;
        String toreturn;
        while(true){
            try{
                out.writeUTF("What do you want?[Date | Time]..\n"+
                        "Type EXIT to terminate connection.");
                received=in.readUTF();
                if(received.equals("EXIT")){
                    System.out.println("Client " + this.socket + " sends exit...");
                    System.out.println("Closing this connection.");
                    socket.close();
                    System.out.println("Connection Closed");
                    break;
                }
                Date date=new Date();

                switch(received){
                    case "Date":
                        toreturn=fordate.format(date);
                        out.writeUTF(toreturn);
                        break;
                    case "Time":
                        toreturn=fortime.format(date);
                        out.writeUTF(toreturn);
                        break;
                    default:
                        out.writeUTF("Invalid Input");
                }
            }
            catch(IOException e){
                e.printStackTrace();
            }

        }
        try{
            this.in.close();
            this.out.close();
        }catch(IOException e){
            e.printStackTrace();
        }

    }
}
