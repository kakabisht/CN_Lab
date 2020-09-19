import java.io.*;
import java.net.*;
import java.util.*;

public class Server_3 {

    private static ServerSocket serverSocket;
    private static final int PORT = 1234;

    public static void main(String[] args)
    {
        System.out.println("Opening port...\n");
        try {
            serverSocket = new ServerSocket(PORT);
        }
        catch (IOException ioex){
            System.out.println("Unable to attach to port!");
            System.exit(1);
        }
          handleClient();

  }

    private static void handleClient()
    {
        Socket link = null; //Step 2
        try {
            link = serverSocket.accept(); //Step 2
            //Step 3
            Scanner input = new Scanner(link.getInputStream());
            PrintWriter output = new PrintWriter(link.getOutputStream(), true);
            int firstInt = input.nextInt();
            int answer;

            while (firstInt != 0 )
            {
                answer = 1162261467 % firstInt ;
                output.println(answer); //Server returns the sum here 4
                firstInt = input.nextInt();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try {
                System.out.println("Closing connection...");
                link.close();
            }
            catch (Exception ie)
            {
                System.out.println("Unable to close connection");
                System.exit(1);
            }
        }
    }
}