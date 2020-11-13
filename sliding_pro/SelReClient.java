import java.lang.System;
import java.net.*; 
import java.io.*;


public class SelReClient {
    static Socket connection;
    static DataInputStream dis;
    static DataOutputStream dos;
    public static void main(String args[]) throws SocketException {
        try {
            BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
            InetAddress addr = InetAddress.getByName("Localhost");//same network
            System.out.println(addr);
            connection = new Socket(addr, 8011);
            dis = new DataInputStream(connection.getInputStream());//Input 
            dos = new DataOutputStream(connection.getOutputStream());//Output
            System.out.print("Enter window size:"); //taking the window size
            int count=Integer.parseInt(br.readLine());
            int a[] = new int[count];
            for(int i=0;i<count;i++)
            {
            System.out.print("Enter data for frames no " +(i+1)+ ":");
            a[i]=Integer.parseInt(br.readLine());
            }
            System.out.println("Number of frames sent:" + a.length);
            int y = a.length;
            dos.write(y);
            dos.flush();

            for (int i = 0; i < a.length; i++) 
            {
                dos.write(a[i]);
                dos.flush();
            }
            
            int k = dis.read();
            System.out.println();
            System.out.println("Frame "+(k+1)+" has been lost or corrupted.");
            System.out.println("Retransmitting.");
            dos.write(a[k]); // retransmitting only the lost frame.
            dos.flush();
            

        }
        catch (IOException e)
        {
            System.out.println(e);
        }
        finally
        {
            try 
            {
                dis.close();
                dos.close();
                System.out.println("Client finished sending data.");
            }
            catch (IOException e) 
            {
                e.printStackTrace();
            }

        }

    }
}
