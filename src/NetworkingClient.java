/* SERVER - may be enhanced to work for multiple clients */
import java.net.*;
import java.io.*;

public class NetworkingClient {

    public static void main(String[] args) {

        ServerSocket server = null;
        Socket client;

        // Default port number we are going to use
        int portnumber = 8080;
        if (args.length >= 1) {
            portnumber = Integer.parseInt(args[0]);
        }

        for (int i=0; i<10; i++) {
            try {
                String msg = "";

                // Create a client socket
                client = new Socket(InetAddress.getLocalHost(), portnumber);
                System.out.println("Client socket is created " + client);

                // Create an output stream of the client socket
                OutputStream clientOut = client.getOutputStream();
                PrintWriter pw = new PrintWriter(clientOut, true);

                // Create an input steam of the client socket
                InputStream clientIn = client.getInputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(clientIn));

                // Create BufferedReader for a standard input
                BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));

                System.out.println("Enter your name. Type Bye to exit. ");

                // Read data from standard input device and write it
                // to the output stram of the client socket.
                msg = stdIn.readLine().trim();
                pw.println(msg);

                // Read data from the input stream of the client
                System.out.println("Message returned from the server = " +
                        br.readLine());

                pw.close();
                br.close();
                client.close();

                // Stop the operation
                if (msg.equalsIgnoreCase("Bye")) {
                    break;
                }
            } catch (IOException ie) {
                System.out.println("I/O error " + ie);
            }
        }

    }
}