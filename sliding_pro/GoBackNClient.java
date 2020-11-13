import java.lang.System;
import java.net.*; 
import java.io.*;

public class GoBackNClient {
    static Socket connection;
    static DataInputStream dis;
    static DataOutputStream dos;
    public static void main(String args[]){
        try {
            BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
            InetAddress addr = InetAddress.getByName("Localhost");//local host as they are on the same network
            System.out.println(addr);
            connection = new Socket(addr, 8011);//8011 is port
            dis = new DataInputStream(connection.getInputStream());// for input stream
            dos = new DataOutputStream(connection.getOutputStream());//for output stream
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
                dos.write(a[i]); // sending all the data 
                dos.flush();
            }
            
            int k = dis.read(); // reading which packet is lost 
            System.out.println();
            System.out.println("Frame "+(k+1)+" has been lost or corrupted.");
            System.out.println("Retransmitting.");
            for (int j = k; j < a.length; j++){ 
                //retransmitting all frames including and after the currupted frame.
            dos.write(a[j]);
            dos.flush();
            }

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
