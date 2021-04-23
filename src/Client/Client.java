package Client;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class Client {

    public static void main(String[] args) throws IOException {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            InetAddress ip = InetAddress.getByName("localhost");
            Socket socket = new Socket(ip, 4444);
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            DataInputStream in = new DataInputStream(socket.getInputStream());

            while (true) {
                System.out.println(in.readUTF());
                String tosend = br.readLine();
                out.writeUTF(tosend);

                if (tosend.equals("EXIT")) {
                    System.out.println("Closing Connection");
                    socket.close();
                    System.out.println("Connection Closed");
                    break;
                }

                String received = in.readUTF();
                System.out.println(received);
            }

            br.close();
            in.close();
            out.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

}
