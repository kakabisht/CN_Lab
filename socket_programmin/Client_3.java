import java.net.*;
import java.io.*;
import java.util.NoSuchElementException;
import java.util.Scanner;


public class Client_3 {

    private static InetAddress host;
    private static final int PORT = 1234;

    public static void main(String[] args) {
        try {
            host = InetAddress.getLocalHost();
        } catch (UnknownHostException uhEx) {
            System.out.println("Host ID not found!");
            System.exit(1);
        }
        accessServer();
    }

    private static void accessServer() {
        Socket link = null;    //Step 1
        try {
            link = new Socket(host, PORT); //Step 1
            //Step 2
            Scanner input = new Scanner(link.getInputStream());
            PrintWriter output = new PrintWriter(link.getOutputStream(), true);

            //Set up stream for keyboard entry
            Scanner userEntry = new Scanner(System.in);

            int firstInt, answer;
            do {
                System.out.print("Please input the number: ");
                firstInt = userEntry.nextInt();
                output.println(firstInt);
                answer = input.nextInt(); //getting the answer from the server
                if(answer==0){
                    System.out.println("\nSERVER> Power of 3");
                }
                else{
                    System.out.println("\nSERVER> Not Power of 3");
                }
            } while (firstInt != 0);
        } catch (IOException e) {
            e.printStackTrace();
        }
        catch (Exception ne){   //This exception may be raised when the server closes connection
            System.out.println("Connection closed");
        }
        finally {
            try {
                System.out.println("\n* Closing connection… *");
                link.close(); //Step 4.
            } catch (Exception ioEx) {
                System.out.println("Unable to disconnect!");
                System.exit(1);
            }
        }
    }
}